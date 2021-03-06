package mutation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import ribbonnodes.RibbonEdge;
import ribbonnodes.RibbonNode;
import strand.Strand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * @author Jeffrey Helgers.
 *         Test the Mutations class.
 */
public class MutationsTest {

	/**
	 * The tested mutations object.
	 */
    private Mutations mutations;
    
    /**
     * The present ribbon nodes.
     */
    private ArrayList<RibbonNode> nodes;
    
    /**
     * Ribbon node.
     */
    private RibbonNode node1;
    
    /**
     * Ribbon node.
     */
    private RibbonNode node2;
    
    /**
     * Ribbon node.
     */
    private RibbonNode node3;
    
    /**
     * Ribbon node.
     */
    private RibbonNode node4;
    
    /**
     * Nodes that do have a SNP.
     */
    private ArrayList<Strand> withSNP;
    
    /**
     * Nodes that don't have a SNP.
     */
    private ArrayList<Strand> withoutSNP;
    
    /**
     * ArgumentCaptor.
     */
    private ArgumentCaptor<AbstractMutation> captor;

    /**
     * Set up the mutation object.
     *
     * @throws Exception if fail.
     */
    @Before
    public void setUp() throws Exception {
        node1 = Mockito.mock(RibbonNode.class);
        node2 = Mockito.mock(RibbonNode.class);
        node3 = Mockito.mock(RibbonNode.class);
        node4 = Mockito.mock(RibbonNode.class);
        nodes = new ArrayList<>(Arrays.asList(node1, node2, node3, node4));
        mutations = new Mutations(nodes, null);
        captor = new ArgumentCaptor<>();

        Strand strandSNP = Mockito.mock(Strand.class);
        Mockito.when(strandSNP.getSequence()).thenReturn("A");
        Strand strandNoSNP = Mockito.mock(Strand.class);
        Mockito.when(strandNoSNP.getSequence()).thenReturn("AA");

        withSNP = new ArrayList<>(Arrays.asList(strandSNP));
        withoutSNP = new ArrayList<>(Arrays.asList(strandNoSNP));

        Mockito.when(node1.getGenomes()).thenReturn(new HashSet<>());
        Mockito.when(node2.getGenomes()).thenReturn(new HashSet<>());
        Mockito.when(node3.getGenomes()).thenReturn(new HashSet<>());
        Mockito.when(node4.getGenomes()).thenReturn(new HashSet<>());

        Mockito.when(node1.getId()).thenReturn(1);
        Mockito.when(node2.getId()).thenReturn(2);
        Mockito.when(node3.getId()).thenReturn(3);
        Mockito.when(node4.getId()).thenReturn(4);
    }

    /**
     * Test a graph with no mutation.
     */
    @SuppressWarnings("CPD-START")
    @Test
    public void testNoMutation() {
        RibbonEdge e1 = new RibbonEdge(node1, node2);
        RibbonEdge e2 = new RibbonEdge(node1, node3);
        Mockito.when(node1.getOutEdges()).thenReturn(new ArrayList<>(
                Arrays.asList(e1, e2)));
        Mockito.when(node2.getStrands()).thenReturn(withoutSNP);
        mutations.computeAllMutations();
        Mockito.verify(node1, Mockito.never()).addMutation(Matchers.any());
    }

    /**
     * Test a graph with an indel.
     */
    @Test
    public void testMutationIndel() {
        RibbonEdge e1 = new RibbonEdge(node1, node2);
        RibbonEdge e2 = new RibbonEdge(node1, node3);
        RibbonEdge e3 = new RibbonEdge(node2, node3);
        Mockito.when(node1.getOutEdges()).thenReturn(new ArrayList<>(
                Arrays.asList(e1, e2)));
        Mockito.when(node2.getOutEdges()).thenReturn(
                new ArrayList<>(Arrays.asList(e3)));
        Mockito.when(node2.getStrands()).thenReturn(withoutSNP);

        mutations.computeAllMutations();
        
        Mockito.verify(node2).addMutation(captor.capture());
        assertEquals(captor.getValue().getMutationType(), MutationType.INDEL);
    }

    /**
     * Test a graph with a SNP.
     */
    @Test
    public void testMutationSNP() {
        RibbonEdge e1 = new RibbonEdge(node1, node2);
        RibbonEdge e2 = new RibbonEdge(node1, node3);
        RibbonEdge e3 = new RibbonEdge(node2, node4);
        RibbonEdge e4 = new RibbonEdge(node3, node4);
        Mockito.when(node1.getOutEdges()).thenReturn(new ArrayList<>(
                Arrays.asList(e1, e2)));
        Mockito.when(node2.getOutEdges()).thenReturn(
                new ArrayList<>(Arrays.asList(e3)));
        Mockito.when(node3.getOutEdges()).thenReturn(
                new ArrayList<>(Arrays.asList(e4)));
        Mockito.when(node2.getStrands()).thenReturn(withSNP);
        Mockito.when(node3.getStrands()).thenReturn(withSNP);

        mutations.computeAllMutations();

        Mockito.verify(node2).addMutation(captor.capture());
        assertEquals(captor.getValue().getMutationType(), MutationType.SNP);
    }
}
