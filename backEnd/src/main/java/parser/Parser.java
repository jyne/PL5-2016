package parser;

import com.opencsv.CSVReader;

import datatree.DataNode;
import datatree.DataTree;
import genome.GenomeGenerator;
import genome.GenomeGraph;
import genomefeature.GenomicFeature;
import metadata.GenomeMetadata;
import strand.Strand;
import strand.StrandEdge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Jeffrey on 24-4-2016.
 * This class is used to read the all the data from given files.
 */
public final class Parser {

	/**
	 * Constructor to create a parser.
	 * Private because this should not be possible.
	 */
	private Parser() {
	}
	
    /**
     * Reads the file as a graph in to an Controller.
     *
     * @param file The file that is read.
     * @return The graph in the file.
     */
    @SuppressWarnings("checkstyle:methodlength")
    public static GenomeGraph parse(String file) {
        BufferedReader reader;
        String line;
        GenomeGraph genomeGraph = new GenomeGraph();
        try {
            InputStream in = Parser.class.getClassLoader().getResourceAsStream(file);
            reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            reader.readLine();
            String[] genomeIds = reader.readLine().split("\t")[1].split(":")[2].split(";");
            genomeIds = removeEnding(".fasta", genomeIds);
            line = reader.readLine();
            while (line != null) {
                String[] splittedLine = line.split("\t");
                String temp = splittedLine[0];
                if (temp.equals("S")) {
                    Strand strand = createNode(splittedLine);
                    genomeGraph.addStrand(strand);
                }
                line = reader.readLine();
            }
            in = Parser.class.getClassLoader().getResourceAsStream(file);
            reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            reader.readLine();
            reader.readLine();
            line = reader.readLine();
            while (line != null) {
                String[] splittedLine = line.split("\t");
                String temp = splittedLine[0];
                if (temp.equals("L")) {
                    StrandEdge edge = createEdge(splittedLine, genomeGraph);
                    genomeGraph.getStrand(edge.getStart().getId()).addEdge(edge);
                    genomeGraph.getStrand(edge.getEnd().getId()).addEdge(edge);
                }
                line = reader.readLine();
            }
            reader.close();
            genomeGraph.setGenomes(GenomeGenerator.generateGenomes(genomeIds, genomeGraph));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return genomeGraph;
    }

    /**
     * Creates an StrandEdge from input data.
     *
     * @param splittedLine A line that contains an edge read from the file.
     * @return An StrandEdge.
     */
    private static StrandEdge createEdge(String[] splittedLine, GenomeGraph graph) {
        int startId = Integer.parseInt(splittedLine[1]);
        int endId = Integer.parseInt(splittedLine[3]);
        return new StrandEdge(graph.getStrand(startId), graph.getStrand(endId));
    }

    /**
     * Creates a Strand from the input data.
     *
     * @param splittedLine A line that contains a node read from the file.
     * @return A Strand.
     */
    private static Strand createNode(String[] splittedLine) {
        int nodeId = Integer.parseInt(splittedLine[1]);
        String sequence = splittedLine[2];
        splittedLine[4] = splittedLine[4].substring(6, splittedLine[4].length());
        String[] genomeIds = splittedLine[4].split(";");
        genomeIds = removeEnding(".fasta", genomeIds);
        String referenceGenome = splittedLine[5].substring(6, splittedLine[5].length());
        String ref = splittedLine[8].substring(8, splittedLine[8].length());
        int referenceCoordinate = Integer.parseInt(ref);
        HashSet<String> genomeSet = new HashSet<String>();
        Collections.addAll(genomeSet, genomeIds);
        return new Strand(nodeId, sequence, genomeSet, referenceGenome, referenceCoordinate);
    }

    /**
     * Remove the end of the string.
     * @param string The string that needs to be shortened.
     * @param genomeIds The genomeIds.
     * @return Shortened string.
     */
    private static String[] removeEnding(String string, String[] genomeIds) {
        String[] trimmedGenomeIds = new String[genomeIds.length];
        for (int i = 0; i < genomeIds.length; i++) {
            String genomeId = genomeIds[i];
            if (genomeId.endsWith(string)) {
                trimmedGenomeIds[i] = genomeId.substring(0, genomeId.length() - 6);
            }
        }
        return trimmedGenomeIds;
    }

    /**
     * Parses the genome metadata.
     *
     * @param filePath the file path
     * @return the hash map
     */
    public static List<GenomicFeature> parseAnnotations(String filePath) {
        List<GenomicFeature> list = new ArrayList<GenomicFeature>();
        InputStream in = Parser.class.getClassLoader().getResourceAsStream(filePath);
        CSVReader reader = new CSVReader(new InputStreamReader(in), '\t');
        String[] nextLine;
        try {
            while ((nextLine = reader.readNext()) != null) {
                int start = Integer.parseInt(nextLine[3]);
                int end = Integer.parseInt(nextLine[4]);
                String temp = "";
                for (int i = 8; i < nextLine.length; i++) {
                    temp = temp + nextLine[i];
                }
                String[] attributes = temp.split(";");
                String displayName = attributes[attributes.length - 1];
                displayName = displayName.substring(12, displayName.length());
                list.add(new GenomicFeature(start, end, displayName));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


    /**
     * Parses the genome metadata.
     *
     * @param filePath the file path
     * @return the hash map
     */
    public static HashMap<String, GenomeMetadata> parseGenomeMetadata(String filePath) {
        HashMap<String, GenomeMetadata> hmap = new HashMap<String, GenomeMetadata>();
        InputStream in = Parser.class.getClassLoader().getResourceAsStream(filePath);
        CSVReader reader = new CSVReader(new InputStreamReader(in, StandardCharsets.UTF_8), ';');
        String[] nextLine;
        try {
            while ((nextLine = reader.readNext()) != null) {
                String genomeId = nextLine[0];
                hmap.put(genomeId, new GenomeMetadata(nextLine));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hmap;
    }
    
    /**
     * Read the strands that are present in the nodes in the datatree.
     *
     * @param tree    The data tree where the strands are added.
     * @param strands All the strands.
     * @param file    The file.
     */
    public static void readDataTree(DataTree tree, HashMap<Integer, Strand> strands, String file) {
        BufferedReader reader;
        try {
            InputStream in = Parser.class.getClassLoader().getResourceAsStream(file);
            reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line = reader.readLine();
            while (line != null) {
                String[] splitted = line.split(" ");
                int nodeId = Integer.parseInt(splitted[0]);
                DataNode current = tree.getRoot().getNode(nodeId);
                String[] strandIDs = splitted[1].split(";");
                ArrayList<Strand> strandsInNode = new ArrayList<Strand>();
                for (String strandID : strandIDs) {
                    strandsInNode.add(strands.get(Integer.parseInt(strandID)));
                }
                current.setStrands(strandsInNode);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}