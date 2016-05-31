package mutation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import controller.GenomeGraph;
import genome.Strand;
import genome.StrandEdge;

/**
 * 
 * @author Jeffrey Helgers.
 * Test the Mutations class.
 */
public class MutationsTest {

	private Mutations mutations;
	private GenomeGraph graph;
	private HashMap<Integer, Strand> strands;
	private	Strand strand1;
	private Strand strand2;
	private Strand strand3;
	
	/**
	 * Set up the mutation object.
	 * @throws Exception if fail.
	 */
	@Before
	public void setUp() throws Exception {
		graph = mock(GenomeGraph.class);
		mutations = new Mutations(graph);
		strand1 = mock(Strand.class);
		strand2 = mock(Strand.class);
		strand3 = mock(Strand.class);
		strands = new HashMap<>();
		strands.put(0, strand1);
		strands.put(1, strand2);
		strands.put(2, strand3);
		when(graph.getStrandNodes()).thenReturn(strands);
		when(strand1.getId()).thenReturn(0);
		when(strand2.getId()).thenReturn(1);
		when(strand3.getId()).thenReturn(2);
	}

	/**
	 * Test a graph with no mutation.
	 */
	@Test
	public void testNoMutation() {
		when(strand1.getEdges()).thenReturn(new ArrayList<>(
				Arrays.asList(new StrandEdge(0, 1), new StrandEdge(0, 2))));
		when(strand2.getEdges()).thenReturn(new ArrayList<>());
		when(strand3.getEdges()).thenReturn(new ArrayList<>());
		mutations.computeAllMutations();
		assertEquals(mutations.getMutation().size(), 0);
	}
	
	/**
	 * Test a graph with a insertion.
	 */
	@Test
	public void testMutationDeletion() {
		when(strand1.getEdges()).thenReturn(new ArrayList<>(
				Arrays.asList(new StrandEdge(0, 1), new StrandEdge(0, 2))));
		when(strand2.getEdges()).thenReturn(new ArrayList<>(Arrays.asList(new StrandEdge(1, 2))));
		when(strand3.getEdges()).thenReturn(new ArrayList<>());
		mutations.computeAllMutations();
		assertEquals(mutations.getMutation().size(), 1);
		assertTrue(mutations.getMutation().get(0).getMutationType().equals(MutationType.DELETION));
	}
	
	/**
	 * Test a graph with a deletion.
	 */
	@Test
	public void testMutationInsertion() {
		when(strand1.getEdges()).thenReturn(new ArrayList<>(
				Arrays.asList(new StrandEdge(0, 1), new StrandEdge(0, 2))));
		when(strand2.getEdges()).thenReturn(new ArrayList<>());
		when(strand3.getEdges()).thenReturn(new ArrayList<>(Arrays.asList(new StrandEdge(2, 1))));
		mutations.computeAllMutations();
		assertEquals(mutations.getMutation().size(), 1);
		assertTrue(mutations.getMutation().get(0).getMutationType().equals(MutationType.INSERTION));

	}
}