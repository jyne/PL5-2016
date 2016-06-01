package phylogenetictree;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Matthijs on 4-5-2016.
 * Test tree: https://en.wikipedia.org/wiki/File:NewickExample.svg
 */
public class PylogeneticTreeTest {

    /**
     * The tested tree.
     */
    private PhylogeneticTree tree;

    /**
     * Setting up the testing.
     */
    @Before
    public void setUp() {
        tree = new PhylogeneticTree();
        ArrayList<String> genomes = new ArrayList<>(
        		Arrays.asList("A.fasta", "C.fasta", "D.fasta"));
        tree.parseTree("testFile", genomes);
    }

    /**
     * Test the number of children.
     */
    @Test
    public void testRootSize() {
        assertEquals(tree.getRoot().getChildren().size(), 2);
    }

    /**
     * Test the getNodeWithLabelMethod, as well as distance on an end node.
     */
    @Test
    public void testGetNodeWithLabelEnd() {
        assertEquals(tree.getRoot().getNodeWithLabel("D.fasta").getDistance(), 0.4, 0.001);
    }

    /**
     * Test the getNodeWithLabelMethod, as well as distance on a middle node.
     */
    @Test
    public void testGetNodeWithLabelMiddle() {
        assertEquals(tree.getRoot().getNodeWithLabel("").getDistance(), 0.0, 0.001);
    }

    /**
     * Test the getNode, as well as distance on an end node.
     */
    @Test
    public void testGetNodeEnd() {
        assertEquals(tree.getRoot().getNode(2).getDistance(), 0.5, 0.001);

    }

    /**
     * Test the getNode, as well as distance on a middle node.
     */
    @Test
    public void testGetNodeMiddle() {
        assertEquals(tree.getRoot().getNode(1).getDistance(), 0.1, 0.001);
    }

    /**
     * Test if Parent is set correctly.
     *
     * @throws Exception if fail.
     */
    @Test
    public void testParent() throws Exception {
        assertEquals(tree.getRoot(), tree.getRoot().getChildren().get(0).getParent());
        assertEquals(null, tree.getRoot().getParent());
    }

    /**
     * Test if the nodes contain the correct genomes.
     *
     * @throws Exception if fail.
     */
    @Test
    public void testContainsGenomes() throws Exception {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("C.fasta");
        testList.add("D.fasta");

        assertEquals(tree.getRoot().getChildren().get(1).getGenomes(), testList);
    }

    /**
     * Test the default constructor.
     *
     * @throws Exception if fail.
     */
    @Test
    public void testPylogeneticTreeConstructor() throws Exception {
        PhylogeneticNode testRoot = new PhylogeneticNode(null, 0);
        PhylogeneticTree testTree = new PhylogeneticTree(testRoot);
        assertEquals(testRoot, testTree.getRoot());
    }
}
