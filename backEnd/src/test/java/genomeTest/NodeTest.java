package genomeTest;
import org.junit.Before;
import org.junit.Test;

import genome.Node;

import static org.junit.Assert.assertEquals;;

/**
 * Created by Matthijs on 24-4-2016.
 */
public class NodeTest {

	private Node node;
	
	/**
	 * Sets up a node with whom we test.
	 */
    @Before
    public void setUp() {
    	String[] genomes = {"ref1", "ref2"};
    	node = new Node(1, "AA", genomes, "ref1", 0);
    }
    
    /**
     * Tests whether we can change the x coordinate.
     */
    @Test
    public void testGetXCoordinate() {
    	node.setxCoordinate(10.0);
    	assertEquals(node.getxCoordinate(), 10.0, 0.001);
    }

    /**
     * Tests whether we can change the x coordinate.
     */
    @Test
    public void testGetYCoordinate() {
    	node.setyCoordinate(10.0);
    	assertEquals(node.getyCoordinate(), 10.0, 0.001);
    }
    
    /**
     * Tests getting the id of the node.
     */
    @Test
    public void testGetId() {
    	assertEquals(node.getId(), 1);
    }
    
    /**
     * Tests getting the sequence of the node.
     */
    @Test
    public void testGetSequence() {
    	assertEquals(node.getSequence(), "AA");
    }
    
    /**
     * Tests getting the genomes of the node.
     */
    @Test
    public void testGetGenomes() {
    	assertEquals(node.getGenomes()[0], "ref1");
    	assertEquals(node.getGenomes()[1], "ref2");
    }
    
    /**
     * Tests getting the reference genome of the node.
     */
    @Test
    public void testGetReferenceGenome() {
    	assertEquals(node.getRefrenceGenome(), "ref1");
    }
    
    /**
     * Tests getting the reference coordinate of the node.
     */
    @Test
    public void testGetReferenceCoordinate() {
    	assertEquals(node.getRefrenceCoordinate(), 0);
    }
    
    /**
     * Tests getting the weight of the node.
     */
    @Test
    public void testGetWeight() {
    	assertEquals(node.getWeight(), 2);
    }
    
    /**
     * Tests updating the x coordinate.
     */
    @Test
    public void testUpdateXCoordinate() {
    	String[] genomes = {"ref1", "ref2"};
    	node = new Node(1, "AA", genomes, "ref1", 0);
    	node.updatexCoordinate(2);
    	assertEquals(node.getxCoordinate(), 2, 0.001);
    }
}