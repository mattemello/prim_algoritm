package src4;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {

    private Integer i1, i2, i3, i4, i5, l1, l2, l3, l4, l5;

    /*--------------------No Directed No Lable---------------------------------------------*/

    private Graph<Integer, Integer> graphNN;

    @Before
    public void CreateGraph() {
        i1 = 2;
        i2 = 4;
        i3 = 8;
        i4 = 10;
        i5 = 14;
        l1 = 2;
        l2 = 4;
        l3 = 8;
        l4 = 10;
        l5 = 14;

        graphNN = new Graph<>(false, false);
    }

    @Test
    public void makeOneNode() throws GraphExpectation {
        graphNN.addNode(i1);
        assertTrue(graphNN.containsNode(i1));
    }

    @Test
    public void makeTwoNode() throws GraphExpectation {
        graphNN.addNode(i1);

        assertTrue(graphNN.addNode(i2));
    }

    @Test
    public void makeTwoNodeConnected() throws GraphExpectation {
        graphNN.addNode(i1);
        graphNN.addNode(i2);

        graphNN.addEdge(i1, i2, null);

        assertTrue(graphNN.containsEdge(i1, i2));
    }

    @Test
    public void makeFiveNodeConnected() throws GraphExpectation {
        graphNN.addNode(i1);
        graphNN.addNode(i4);
        graphNN.addNode(i2);
        graphNN.addNode(i3);
        graphNN.addNode(i5);

        graphNN.addEdge(i1, i2, null);
        graphNN.addEdge(i1, i3, null);
        graphNN.addEdge(i2, i4, null);
        graphNN.addEdge(i4, i5, null);
        graphNN.addEdge(i4, i1, null);
        graphNN.addEdge(i5, i2, null);

        graphNN.removeEdge(i4, i1);

        ArrayList<AbstractEdge<Integer, Integer>> thisGraph = new ArrayList<>(graphNN.getEdges());

        ArrayList<Edge<Integer, Integer>> contains = new ArrayList<>();
        contains.add(new Edge<Integer, Integer>(i1, i2, null, 0));
        contains.add(new Edge<Integer, Integer>(i2, i1, null, 0));
        // ----------
        contains.add(new Edge<Integer, Integer>(i1, i3, null, 1));
        contains.add(new Edge<Integer, Integer>(i3, i1, null, 1));
        // ----------
        contains.add(new Edge<Integer, Integer>(i4, i2, null, 2));
        contains.add(new Edge<Integer, Integer>(i2, i4, null, 2));
        // ----------
        contains.add(new Edge<Integer, Integer>(i4, i5, null, 2));
        contains.add(new Edge<Integer, Integer>(i5, i4, null, 2));
        // ----------
        contains.add(new Edge<Integer, Integer>(i4, i1, null, 2));
        contains.add(new Edge<Integer, Integer>(i1, i4, null, 2));
        // ----------
        contains.add(new Edge<Integer, Integer>(i5, i2, null, 2));
        contains.add(new Edge<Integer, Integer>(i2, i5, null, 2));
        // ----------

        int countTrue = 0;
        boolean isContained = false;

        for (Edge<Integer, Integer> edge : contains) {
            for (int i = 0; i < thisGraph.size(); i++) {
                if (thisGraph.get(i).getEnd().equals(edge.getEnd())
                        && thisGraph.get(i).getStart().equals(edge.getStart())) {

                    isContained = true;
                }
            }

            if (isContained) {
                countTrue += 1;
            }
            isContained = false;
        }

        boolean finalResult = (countTrue == thisGraph.size());

        assertTrue(finalResult);
    }

    @Test
    public void makeThreeNodeConnected() throws GraphExpectation {
        graphNN.addNode(i1);
        graphNN.addNode(i2);
        graphNN.addNode(i3);

        graphNN.addEdge(i1, i2, null);
        graphNN.addEdge(i1, i3, null);
        graphNN.addEdge(i2, i3, null);

        // graph.getEdges().forEach(prova -> System.out
        // .println(prova.getStart() + "------> " + prova.getEnd() + "lable: " +
        // prova.getLabel()));

        ArrayList<AbstractEdge<Integer, Integer>> thisGraph = new ArrayList<>(graphNN.getEdges());

        ArrayList<Edge<Integer, Integer>> contains = new ArrayList<>();
        contains.add(new Edge<Integer, Integer>(i1, i2, null, 0));
        contains.add(new Edge<Integer, Integer>(i2, i1, null, 0));
        contains.add(new Edge<Integer, Integer>(i1, i3, null, 1));
        contains.add(new Edge<Integer, Integer>(i3, i1, null, 1));
        contains.add(new Edge<Integer, Integer>(i2, i3, null, 2));
        contains.add(new Edge<Integer, Integer>(i3, i2, null, 2));

        int countTrue = 0;
        boolean isContained = false;

        for (Edge<Integer, Integer> edge : contains) {
            for (int i = 0; i < thisGraph.size(); i++) {
                if (thisGraph.get(i).getEnd().equals(edge.getEnd())
                        && thisGraph.get(i).getStart().equals(edge.getStart())) {

                    isContained = true;
                }
            }

            if (isContained) {
                countTrue += 1;
            }
            isContained = false;
        }

        boolean finalResult = (countTrue == thisGraph.size());

        assertTrue(finalResult);
    }

    @Test
    public void makeThreeNodeConnectedRemove() throws GraphExpectation {
        graphNN.addNode(i1);
        graphNN.addNode(i2);
        graphNN.addNode(i3);

        graphNN.addEdge(i1, i2, null);
        graphNN.addEdge(i1, i3, null);
        graphNN.addEdge(i2, i3, null);

        graphNN.removeEdge(i1, i3);

        ArrayList<AbstractEdge<Integer, Integer>> thisGraph = new ArrayList<>(graphNN.getEdges());

        ArrayList<Edge<Integer, Integer>> contains = new ArrayList<>();
        contains.add(new Edge<Integer, Integer>(i1, i2, null, 0));
        contains.add(new Edge<Integer, Integer>(i2, i1, null, 0));
        contains.add(new Edge<Integer, Integer>(i2, i3, null, 1));
        contains.add(new Edge<Integer, Integer>(i3, i2, null, 1));

        int countTrue = 0;
        boolean isContained = false;

        for (Edge<Integer, Integer> edge : contains) {
            for (int i = 0; i < thisGraph.size(); i++) {
                if (thisGraph.get(i).getEnd().equals(edge.getEnd())
                        && thisGraph.get(i).getStart().equals(edge.getStart())) {

                    isContained = true;
                }
            }

            if (isContained) {
                countTrue += 1;
            }
            isContained = false;
        }

        boolean finalResult = (countTrue == thisGraph.size());

        assertTrue(finalResult);

    }

    /*-------------------------------------Directed No Lable--------------------------------------------------*/

    private Graph<Integer, Integer> graphDir;

    @Before
    public void CreateGraphDir() {
        i1 = 2;
        i2 = 4;
        i3 = 8;
        i4 = 10;
        i5 = 14;
        l1 = 2;
        l2 = 4;
        l3 = 8;
        l4 = 10;
        l5 = 14;

        graphDir = new Graph<>(true, false);
    }

    @Test
    public void makeOneNodeDir() throws GraphExpectation {
        graphDir.addNode(i1);
        Collection<Integer> nodo = graphDir.getNodes();
        assertTrue(graphDir.containsNode(i1));
    }

    @Test
    public void makeTwoNodeDir() throws GraphExpectation {
        graphDir.addNode(i1);

        assertTrue(graphDir.addNode(i2));
    }

    @Test
    public void makeTwoNodeConnectedDir() throws GraphExpectation {
        graphDir.addNode(i1);
        graphDir.addNode(i2);

        graphDir.addEdge(i1, i2, null);

        assertTrue(graphDir.containsEdge(i1, i2));
    }

    @Test
    public void makeThreeNodeConnectedDir() throws GraphExpectation {
        graphDir.addNode(i1);
        graphDir.addNode(i2);
        graphDir.addNode(i3);

        graphDir.addEdge(i1, i2, null);
        graphDir.addEdge(i1, i3, null);
        graphDir.addEdge(i2, i3, null);

        ArrayList<Edge<Integer, Integer>> contains = new ArrayList<>();
        contains.add(new Edge<Integer, Integer>(i1, i2, null, 0));
        contains.add(new Edge<Integer, Integer>(i1, i3, null, 1));
        contains.add(new Edge<Integer, Integer>(i2, i3, null, 2));

        ArrayList<AbstractEdge<Integer, Integer>> thisGraph = new ArrayList<>(graphDir.getEdges());

        int countTrue = 0;
        boolean isContained = false;

        for (Edge<Integer, Integer> edge : contains) {
            for (int i = 0; i < thisGraph.size(); i++) {
                if (thisGraph.get(i).getEnd().equals(edge.getEnd())
                        && thisGraph.get(i).getStart().equals(edge.getStart())) {

                    isContained = true;
                }
            }

            if (isContained) {
                countTrue += 1;
            }
            isContained = false;
        }

        boolean finalResult = (countTrue == thisGraph.size());

        assertTrue(finalResult);
    }

    @Test
    public void makeThreeNodeConnectedRemoveDir() throws GraphExpectation {
        graphDir.addNode(i1);
        graphDir.addNode(i2);
        graphDir.addNode(i3);

        graphDir.addEdge(i1, i2, null);
        graphDir.addEdge(i1, i3, null);
        graphDir.addEdge(i2, i3, null);

        graphDir.removeEdge(i1, i3);

        ArrayList<Edge<Integer, Integer>> contains = new ArrayList<>();
        contains.add(new Edge<Integer, Integer>(i1, i2, null, 0));
        contains.add(new Edge<Integer, Integer>(i2, i3, null, 2));

        ArrayList<AbstractEdge<Integer, Integer>> thisGraph = new ArrayList<>(graphDir.getEdges());

        int countTrue = 0;
        boolean isContained = false;

        for (Edge<Integer, Integer> edge : contains) {
            for (int i = 0; i < thisGraph.size(); i++) {
                if (thisGraph.get(i).getEnd().equals(edge.getEnd())
                        && thisGraph.get(i).getStart().equals(edge.getStart())) {

                    isContained = true;
                }
            }

            if (isContained) {
                countTrue += 1;
            }
            isContained = false;
        }

        boolean finalResult = (countTrue == thisGraph.size());

        assertTrue(finalResult);
    }

    /*-------------------------------------No Directed Lable--------------------------------------------------*/

    private Graph<Integer, Integer> graphNoDirL;

    @Before
    public void CreateGraphLable() {
        i1 = 2;
        i2 = 4;
        i3 = 8;
        i4 = 10;
        i5 = 14;
        l1 = 2;
        l2 = 4;
        l3 = 8;
        l4 = 10;
        l5 = 14;

        graphNoDirL = new Graph<>(false, true);
    }

    @Test
    public void makeOneNodeLable() throws GraphExpectation {
        graphNoDirL.addNode(i1);
        Collection<Integer> nodo = graphNoDirL.getNodes();
        assertTrue(graphNoDirL.containsNode(i1));
    }

    @Test
    public void makeTwoNodeLable() throws GraphExpectation {
        graphNoDirL.addNode(i1);

        assertTrue(graphNoDirL.addNode(i2));
    }

    @Test
    public void makeTwoNodeConnectedLable() throws GraphExpectation {
        graphNoDirL.addNode(i1);
        graphNoDirL.addNode(i2);

        graphNoDirL.addEdge(i1, i2, l1);

        assertTrue(graphNoDirL.containsEdge(i1, i2));
    }

    @Test
    public void controllGetLabel() throws GraphExpectation {
        graphNoDirL.addNode(i1);
        graphNoDirL.addNode(i2);
        graphNoDirL.addNode(i3);

        graphNoDirL.addEdge(i1, i2, l1);
        graphNoDirL.addEdge(i1, i3, l2);
        graphNoDirL.addEdge(i2, i3, l3);

        assertTrue(graphNoDirL.getLabel(i3, i1) == l2);
    }

    @Test
    public void makeThreeNodeConnectedLable() throws GraphExpectation {
        graphNoDirL.addNode(i1);
        graphNoDirL.addNode(i2);
        graphNoDirL.addNode(i3);

        graphNoDirL.addEdge(i1, i2, l1);
        graphNoDirL.addEdge(i1, i3, l2);
        graphNoDirL.addEdge(i2, i3, l3);

        ArrayList<Edge<Integer, Integer>> contains = new ArrayList<>();
        contains.add(new Edge<Integer, Integer>(i1, i2, l1, 0));
        contains.add(new Edge<Integer, Integer>(i2, i1, l1, 0));
        contains.add(new Edge<Integer, Integer>(i1, i3, l2, 0));
        contains.add(new Edge<Integer, Integer>(i3, i1, l2, 0));
        contains.add(new Edge<Integer, Integer>(i2, i3, l3, 2));
        contains.add(new Edge<Integer, Integer>(i3, i2, l3, 2));

        ArrayList<AbstractEdge<Integer, Integer>> thisGraph = new ArrayList<>(graphNoDirL.getEdges());

        int countTrue = 0;
        boolean isContained = false;

        for (Edge<Integer, Integer> edge : contains) {
            for (int i = 0; i < thisGraph.size(); i++) {
                if (thisGraph.get(i).getEnd().equals(edge.getEnd())
                        && thisGraph.get(i).getStart().equals(edge.getStart())
                        && thisGraph.get(i).getLabel().equals(edge.getLabel())) {

                    isContained = true;
                }
            }

            if (isContained) {
                countTrue += 1;
            }
            isContained = false;
        }

        boolean finalResult = (countTrue == thisGraph.size());

        assertTrue(finalResult);
    }

    @Test
    public void makeThreeNodeConnectedRemoveLable() throws GraphExpectation {
        graphNoDirL.addNode(i1);
        graphNoDirL.addNode(i2);
        graphNoDirL.addNode(i3);

        graphNoDirL.addEdge(i1, i2, l1);
        graphNoDirL.addEdge(i1, i3, l2);
        graphNoDirL.addEdge(i2, i3, l3);

        graphNoDirL.removeEdge(i1, i3);

        ArrayList<Edge<Integer, Integer>> contains = new ArrayList<>();
        contains.add(new Edge<Integer, Integer>(i1, i2, l1, 0));
        contains.add(new Edge<Integer, Integer>(i2, i1, l1, 0));
        contains.add(new Edge<Integer, Integer>(i2, i3, l3, 2));
        contains.add(new Edge<Integer, Integer>(i3, i2, l3, 2));

        ArrayList<AbstractEdge<Integer, Integer>> thisGraph = new ArrayList<>(graphNoDirL.getEdges());

        int countTrue = 0;
        boolean isContained = false;

        for (Edge<Integer, Integer> edge : contains) {
            for (int i = 0; i < thisGraph.size(); i++) {
                if (thisGraph.get(i).getEnd().equals(edge.getEnd())
                        && thisGraph.get(i).getStart().equals(edge.getStart())
                        && thisGraph.get(i).getLabel().equals(edge.getLabel())) {

                    isContained = true;
                }
            }

            if (isContained) {
                countTrue += 1;
            }
            isContained = false;
        }

        boolean finalResult = (countTrue == thisGraph.size());

        assertTrue(finalResult);
    }

    /*-------------------------------------Directed Lable--------------------------------------------------*/

    private Graph<Integer, Integer> graphDirLab;

    @Before
    public void CreateGraphDirLable() {
        i1 = 2;
        i2 = 4;
        i3 = 8;
        i4 = 10;
        i5 = 14;
        l1 = 2;
        l2 = 4;
        l3 = 8;
        l4 = 10;
        l5 = 14;

        graphDirLab = new Graph<>(true, true);
    }

    @Test
    public void makeOneNodeDirLable() throws GraphExpectation {
        graphDirLab.addNode(i1);
        Collection<Integer> nodo = graphDirLab.getNodes();
        assertTrue(graphDirLab.containsNode(i1));
    }

    @Test
    public void makeTwoNodeDirLable() throws GraphExpectation {
        graphDirLab.addNode(i1);

        assertTrue(graphDirLab.addNode(i2));
    }

    @Test
    public void makeTwoNodeConnectedDirLable() throws GraphExpectation {
        graphDirLab.addNode(i1);
        graphDirLab.addNode(i2);

        graphDirLab.addEdge(i1, i2, l1);

        assertTrue(graphDirLab.containsEdge(i1, i2));
    }

    @Test
    public void controllGetLabelInDirect() throws GraphExpectation {
        graphDirLab.addNode(i1);
        graphDirLab.addNode(i2);
        graphDirLab.addNode(i3);

        graphDirLab.addEdge(i1, i2, l1);
        graphDirLab.addEdge(i1, i3, l2);
        graphDirLab.addEdge(i2, i3, l3);

        assertTrue(graphDirLab.getLabel(i1, i3) == l2);
    }

    @Test
    public void makeThreeNodeConnectedDirLable() throws GraphExpectation {
        graphDirLab.addNode(i1);
        graphDirLab.addNode(i2);
        graphDirLab.addNode(i3);

        graphDirLab.addEdge(i1, i2, l1);
        graphDirLab.addEdge(i1, i3, l2);
        graphDirLab.addEdge(i2, i3, l3);

        ArrayList<Edge<Integer, Integer>> contains = new ArrayList<>();
        contains.add(new Edge<Integer, Integer>(i1, i2, l1, 0));
        contains.add(new Edge<Integer, Integer>(i1, i3, l2, 1));
        contains.add(new Edge<Integer, Integer>(i2, i3, l3, 2));

        ArrayList<AbstractEdge<Integer, Integer>> thisGraph = new ArrayList<>(graphDirLab.getEdges());

        int countTrue = 0;
        boolean isContained = false;

        for (Edge<Integer, Integer> edge : contains) {
            for (int i = 0; i < thisGraph.size(); i++) {
                if (thisGraph.get(i).getEnd().equals(edge.getEnd())
                        && thisGraph.get(i).getStart().equals(edge.getStart())
                        && thisGraph.get(i).getLabel().equals(edge.getLabel())) {

                    isContained = true;
                }
            }

            if (isContained) {
                countTrue += 1;
            }
            isContained = false;
        }

        boolean finalResult = (countTrue == thisGraph.size());

        assertTrue(finalResult);
    }

    @Test
    public void makeThreeNodeConnectedRemoveDirLable() throws GraphExpectation {
        graphDirLab.addNode(i1);
        graphDirLab.addNode(i2);
        graphDirLab.addNode(i3);

        graphDirLab.addEdge(i1, i2, l1);
        graphDirLab.addEdge(i1, i3, l2);
        graphDirLab.addEdge(i2, i3, l3);

        graphDirLab.removeEdge(i1, i3);

        ArrayList<Edge<Integer, Integer>> contains = new ArrayList<>();
        contains.add(new Edge<Integer, Integer>(i1, i2, l1, 0));
        contains.add(new Edge<Integer, Integer>(i2, i3, l3, 2));

        ArrayList<AbstractEdge<Integer, Integer>> thisGraph = new ArrayList<>(graphDirLab.getEdges());

        int countTrue = 0;
        boolean isContained = false;

        for (Edge<Integer, Integer> edge : contains) {
            for (int i = 0; i < thisGraph.size(); i++) {
                if (thisGraph.get(i).getEnd().equals(edge.getEnd())
                        && thisGraph.get(i).getStart().equals(edge.getStart())
                        && thisGraph.get(i).getLabel().equals(edge.getLabel())) {

                    isContained = true;
                }
            }

            if (isContained) {
                countTrue += 1;
            }
            isContained = false;
        }

        boolean finalResult = (countTrue == thisGraph.size());

        assertTrue(finalResult);
    }
}
