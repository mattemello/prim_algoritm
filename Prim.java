import java.lang.Number;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Collection;

import src3.PriorityQueue;
import src3.PriorityQueueExpectation;

import src4.Graph;
import src4.GraphExpectation;
import src4.AbstractEdge;
import src4.Edge;

/**
 * 
 * @author: Massimello Matteo
 */
public class Prim<V, L extends Number> {
    public static int numberOfNode = 0, numberOfLabel = 0;
    public static Integer IntegerMinPath = 0;
    public static Float FloatMinPath = 0.0f;
    public static Double DoubleMinPath = 0.0;
    public static Short ShortMinPath = 0;
    public static Long LongMinPath = 0l;
    public static Byte ByteMinPath = 0;

    /**
     * IntegerComparator
     */
    class FloatComparator implements Comparator<AbstractEdge<V, L>> {

        @Override
        public int compare(AbstractEdge<V, L> arg0, AbstractEdge<V, L> arg1) {
            Float Value1 = arg0.getLabel().floatValue();
            Float Value2 = arg1.getLabel().floatValue();

            return Value1.compareTo(Value2);
        }

    }

    /**
     * 
     * extract the minimum in the Queue
     * 
     * @param <V>      String in this case
     * @param <L>      Integer in this case
     * @param QueueKey the Queue where to search
     * @return return the AbstractEdge with the minimum label
     * @throws GraphExpectation
     * @throws PriorityQueueExpectation
     */
    private static <V, L extends Number> AbstractEdge<V, L> extractMin(PriorityQueue<AbstractEdge<V, L>> QueueKey)
            throws GraphExpectation, PriorityQueueExpectation {

        AbstractEdge<V, L> minElem = QueueKey.getElem(0);
        for (int i = 0; i < QueueKey.dimensionQueue(); i++) {
            if (QueueKey.getElem(i).getPosition() != 1) {
                if (minElem.getPosition() != 1) {
                    if (QueueKey.comparator.compare(QueueKey.getElem(i), minElem) == -1) {
                        minElem = QueueKey.getElem(i);
                    }
                } else {
                    minElem = QueueKey.getElem(i);
                }
            }
        }

        QueueKey.remove(minElem);

        return minElem;
    }

    /**
     * 
     * update the minimum label in the Queue
     * 
     * @param <V>      in this case String
     * @param <L>      in this case Integer
     * @param value    the value that is connected to end and is not already done
     * @param end      the node that we taken
     * @param label    the distance from end to value
     * @param toRemove the precedent node
     * @param QueueKey the Queue
     * @throws PriorityQueueExpectation
     */
    private static <V, L extends Number> void decreaseKey(V value, V end, L label, AbstractEdge<V, L> toRemove,
            PriorityQueue<AbstractEdge<V, L>> QueueKey) throws PriorityQueueExpectation {

        QueueKey.remove(toRemove);
        QueueKey.push(new Edge<V, L>(value, end, label, 0));
    }

    /**
     * mst-prim(graph, .., ..)
     * for all node app graph
     * node.neightbor = nil
     * node.lable = inf
     * end for
     * graph2 = void
     * ..
     * Q = makeprioritiqueue(graph.nodi)
     * while Q != null
     * u = extract-min(Q)
     * graph2 = graph1.add(u.neightbor, u)
     * for all nodo app Adiacenti di [u]
     * if ( nodo app Q )
     * if nodo.lable > lable(u, v)
     * nodo.lable = lable(u, v)
     * nodo.neightbor = u
     * Decrease-Key(v, lable(u, v))
     * 
     * @param <V>           in this case String
     * @param <L>           in this case Integer
     * @param graph         the graph where do the prim alg
     * @param comparator    the comparator for the L
     * @param graphtoreturn
     */
    private static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> MST_Prim(Graph<V, L> graph,
            Comparator<? super AbstractEdge<V, L>> comparator) throws PriorityQueueExpectation, GraphExpectation {

        PriorityQueue<AbstractEdge<V, L>> QueueKey = new PriorityQueue<>(comparator);
        ArrayList<V> nodeInGraph = new ArrayList<>(graph.getNodes());
        Graph<V, L> graph2 = new Graph<>(false, true);

        for (V node : nodeInGraph) {
            QueueKey.push(new Edge<V, L>(node, null, null, 1));
            graph2.addNode(node);
        }

        while (!QueueKey.empty()) {
            AbstractEdge<V, L> node = extractMin(QueueKey);
            graph2.addEdge(node.getStart(), node.getEnd(), node.getLabel());

            Add(node.getLabel());

            ArrayList<V> neighbours = new ArrayList<>(graph.getNeighbours(node.getStart()));
            for (V v : neighbours) {

                for (int i = 0; i < QueueKey.dimensionQueue(); i++) {

                    if (QueueKey.getElem(i).getStart().equals(v)) {
                        if (QueueKey.getElem(i).getPosition() == 1) {
                            decreaseKey(v, node.getStart(), graph.getLabel(v, node.getStart()), QueueKey.getElem(i),
                                    QueueKey);
                        } else if (QueueKey.comparator.compare(graph.getTheEdge(v, node.getStart()),
                                QueueKey.getElem(i)) == -1) {

                            decreaseKey(v, node.getStart(), graph.getLabel(v, node.getStart()),
                                    QueueKey.getElem(i), QueueKey);
                        }

                    }

                }
            }
        }

        numberOfNode = graph2.numNodes();
        numberOfLabel = graph2.numEdges();

        return graph2.getEdges();
    }

    public static <L extends Number> void Add(L value) {
        if (value instanceof Integer) {
            IntegerMinPath += value.intValue();
        } else if (value instanceof Float) {
            FloatMinPath += value.floatValue();
        } else if (value instanceof Double) {
            DoubleMinPath += value.doubleValue();
        } else if (value instanceof Short) {
            ShortMinPath = (short) (ShortMinPath + value.shortValue());
        } else if (value instanceof Long) {
            LongMinPath += value.longValue();
        } else if (value instanceof Byte) {
            ByteMinPath = (byte) (ByteMinPath + value.byteValue());
        }
    }

    /**
     * 
     * @param <V>:   in this case String
     * @param <L>:   in this case Integer
     * @param graph: the graph that we want to know the minimal path
     * @return the minimum spannig forest
     */
    public static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(
            Graph<V, L> graph, Comparator<? super AbstractEdge<V, L>> comparatore)
            throws GraphExpectation, PriorityQueueExpectation {

        return MST_Prim(graph, comparatore);
    }

    public static <V, L extends Number> void printForest(ArrayList<AbstractEdge<V, L>> forest) {
        System.out.println("> start of the printng "); 
        L Number = forest.get(0).getLabel();
        for (AbstractEdge<V, L> abstractEdge : forest) {
            System.out.println(
                    abstractEdge.getStart() + " --> " + abstractEdge.getEnd() + " [" + abstractEdge.getLabel() + "]");
        }

        if (Number instanceof Integer) {
            System.err.print("min path: " + IntegerMinPath);
        } else if (Number instanceof Float) {
            System.err.printf("min path: %.3f km", FloatMinPath / 1000);
            // System.err.printf("min path: %f km", FloatMinPath);
        } else if (Number instanceof Double) {
            System.err.print("min path: " + DoubleMinPath);
        } else if (Number instanceof Short) {
            System.err.print("min path: " + ShortMinPath);
        } else if (Number instanceof Long) {
            System.err.print("min path: " + LongMinPath);
        } else if (Number instanceof Byte) {
            System.err.print("min path: " + ByteMinPath);
        }
        System.err.println(" number of node: " + numberOfNode + " number of Label: " + numberOfLabel);
    }

    /**
     * 
     * the main function of the prim class, create a graph and pass it to
     * minimumSpanningForest
     * 
     * @param args: the argumets passed in the command line
     * @throws GraphExpectation
     */
    public static void main(String[] args) throws GraphExpectation, PriorityQueueExpectation {

        if (args.length < 1) {
            System.err.println("too less arguments, you have to pass a file to make this work");
            return;
        }

        Graph<String, Float> graph = new Graph<>(false, true);

        System.out.println("> start of save the graph from the file"); 

        try {
            File theFile = new File(args[0]);

            Scanner reader = new Scanner(theFile);

            String[] arrayString;

            while (reader.hasNextLine()) {
                String date = reader.nextLine();

                arrayString = date.split(",");

                if (!graph.containsNode(arrayString[0])) {
                    graph.addNode(arrayString[0]);
                }

                if (!graph.containsNode(arrayString[1])) {
                    graph.addNode(arrayString[1]);
                }

                if (!graph.containsEdge(arrayString[0], arrayString[1])) {
                    graph.addEdge(arrayString[0], arrayString[1], Float.valueOf(arrayString[2]));
                }

                Arrays.fill(arrayString, null);
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.err.println("file not founded");
            e.printStackTrace();
            return;
        }

        System.out.println("> evriting done "); 
        System.out.println("> start of the calculation of the minimum spanning forest"); 

        ArrayList<AbstractEdge<String, Float>> forest = new ArrayList<>(minimumSpanningForest(graph,
                new Prim<String, Float>().new FloatComparator()));

        System.out.println("> done! "); 

        printForest(forest);

    }
}
