package src4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Massimello Matteo
 */
public class Graph<V, L> implements AbstractGraph<V, L> {
    // private ArrayList<V> nodeElement;
    private HashMap<V, Integer> hashVert;
    private ArrayList<ArrayList<Edge<V, L>>> neighbours;
    private boolean isDirect;
    private boolean isLable;
    private Integer numberNode;
    private Integer numberEds;

    /**
     *
     * graph constructor
     * 
     * @param isDirect: say if the graph is directed
     * @param isLable:  say if the graph is labeled
     */
    public Graph(boolean isDirect, boolean isLable) {
        (this).isDirect = isDirect;
        (this).isLable = isLable;
        (this).numberNode = 0;
        (this).numberEds = 0;

        (this).hashVert = new HashMap<>();
        // (this).nodeElement = new ArrayList<>();
        (this).neighbours = new ArrayList<>();
    }

    /**
     *
     * if you want to print the graph
     */
    public void printGraph() {
        if ((this).isLable) {
            for (Map.Entry<V, Integer> node : this.hashVert.entrySet()) {
                System.out.print(node.getKey() + "[" + node.getValue() + "] ");
                (this).neighbours.get(node.getValue())
                        .forEach(edge -> System.out.print(
                                edge.getStart() + " -- " + edge.getLabel() + " --> " + edge.getEnd() + "    /    "));
                System.out.println();
            }
        } else {
            for (Map.Entry<V, Integer> node : this.hashVert.entrySet()) {
                System.out.print(node.getKey() + "[" + node.getValue() + "] ");
                (this).neighbours.get(node.getValue())
                        .forEach(edge -> System.out.print(edge.getStart() + " ---> " + edge.getEnd() + "    /    "));
                System.out.println();
            }
        }
    }

    private int findIndex(V a) {
        return (this.hashVert).get(a); // 1
    }

    public boolean isDirected() {
        return (this).isDirect; // 1
    }

    public boolean isLabelled() {
        return (this).isLable; // 1
    }

    public boolean addNode(V a) throws GraphExpectation {
        if (containsNode(a)) {
            throw new GraphExpectation("the node do exist");
        }

        (this).hashVert.put(a, (this).numberNode);
        (this).numberNode += 1;

        ArrayList<Edge<V, L>> list = new ArrayList<>();
        (this).neighbours.add(list);

        if (this.hashVert.containsKey(a)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addEdge(V a, V b, L l) throws GraphExpectation {
        if (!(this).isDirect) {
            if (!containsNode(a) || !containsNode(b)) {
                return false;
            }
            if ((this).isLable) {
                if (l == null) {
                    throw new GraphExpectation("the label cannot be null");
                }
                if (!containsEdge(a, b) && !containsEdge(b, a)) {
                    int lenght = findIndex(a);
                    int lenght2 = findIndex(b);
                    (this).numberEds += 1;
                    int dimEdge = (this).neighbours.get(lenght).size();
                    int dimEdge2 = (this).neighbours.get(lenght2).size();
                    return (this).neighbours.get(lenght).add(new Edge<>(a, b, l, dimEdge))
                            && (this).neighbours.get(lenght2).add(new Edge<>(b, a, l, dimEdge2));

                } else {
                    throw new GraphExpectation("the edge is present int the node");
                }

            } else {

                if (!containsEdge(a, b)) {
                    int lenght = findIndex(a);
                    int lenght2 = findIndex(b);
                    (this).numberEds += 1;
                    int dimEdge = (this).neighbours.get(lenght).size();
                    int dimEdge2 = (this).neighbours.get(lenght2).size();
                    return (this).neighbours.get(lenght).add(new Edge<>(a, b, null, dimEdge))
                            && (this).neighbours.get(lenght2).add(new Edge<>(b, a, null, dimEdge2));
                } else {
                    throw new GraphExpectation("the edge is present int the node");
                }
            }
        } else {
            if (!containsNode(a)) {
                return false;
            }
            if ((this).isLable) {
                if (l == null) {
                    throw new GraphExpectation("the label cannot be null");
                }
                if (!containsEdge(a, b)) {
                    int lenght = findIndex(a);
                    (this).numberEds += 1;
                    int dimEdge = (this).neighbours.get(lenght).size();
                    return (this).neighbours.get(lenght).add(new Edge<>(a, b, l, dimEdge));
                } else {
                    throw new GraphExpectation("the edge is present int the node");
                }
            } else {
                if (!containsEdge(a, b)) {
                    int lenght = findIndex(a);
                    (this).numberEds += 1;
                    int dimEdge = (this).neighbours.get(lenght).size();
                    return (this).neighbours.get(lenght).add(new Edge<>(a, b, null, dimEdge));
                } else {
                    throw new GraphExpectation("the edge is present int the node");
                }
            }
        }
    }

    public boolean containsNode(V a) {
        return (this).hashVert.containsKey(a);
    }

    public boolean containsEdge(V a, V b) throws GraphExpectation {
        if (containsNode(a)) {
            int lenght = findIndex(a);
            ArrayList<Edge<V, L>> nodoVicini = ((this).neighbours.get(lenght));
            for (int i = 0; i < nodoVicini.size(); i++) {
                if (nodoVicini.get(i).getEnd().equals(b)) {
                    return true;
                }
            }
            return false;
        } else {
            throw new GraphExpectation("The node where see if the edge exist do not exist");
        }
    }

    public boolean removeNode(V a) throws GraphExpectation {
        if (containsNode(a)) {

            int indexRemove = findIndex(a);
            (this.hashVert).forEach((key, value) -> {
                if (value > indexRemove)
                    (this.hashVert).put(key, value - 1);
            });
            (this.hashVert).remove(a);

            if ((this).isDirect) {
                for (int i = 0; i < (this).neighbours.get(indexRemove).size(); i++) {
                    Edge<V, L> node = (this).neighbours.get(indexRemove).get(i);
                    (this).removeEdge(node.getEnd(), a);
                }
            } else {
                for (Map.Entry<V, Integer> node : this.hashVert.entrySet()) {
                    removeEdge(node.getKey(), a);
                }
            }

            (this.neighbours).remove(indexRemove);
            (this.numberNode) -= 1;

            return !containsNode(a);
        } else {
            return true;
            // throw new GraphExpectation("The node to remove do not exist");
        }
    }

    public boolean removeEdge(V a, V b) throws GraphExpectation {
        if (containsNode(a)) {
            if (containsEdge(a, b)) {
                if (!(this).isDirect) {
                    int idxr = findIndex(b);
                    int edtorm = -1;

                    for (Edge<V, L> arrayList : (this).neighbours.get(idxr)) {
                        if (arrayList.getEnd().equals(a)) {
                            edtorm = arrayList.position;
                        }
                    }

                    for (Edge<V, L> arrayList : (this).neighbours.get(idxr)) {
                        if (arrayList.position > edtorm) {
                            arrayList.position -= 1;
                        }
                    }

                    (this).neighbours.get(idxr).remove(edtorm);
                }

                int indexRemove = findIndex(a);
                int edgeToRemove = -1;

                for (Edge<V, L> arrayList : (this).neighbours.get(indexRemove)) {
                    if (arrayList.getEnd().equals(b)) {
                        edgeToRemove = arrayList.position;
                    }
                }

                for (Edge<V, L> arrayList : (this).neighbours.get(indexRemove)) {
                    if (arrayList.position > edgeToRemove) {
                        arrayList.position -= 1;
                    }
                }

                (this).neighbours.get(indexRemove).remove(edgeToRemove);
                return containsEdge(a, b);

            } else {
                return true;
                // throw new GraphExpectation("The edge to remove do not exist");
            }
        } else {
            throw new GraphExpectation("The node where remove the edge do not exist");
        }
    }

    public int numNodes() {
        return (this).numberNode;
    }

    public int numEdges() {
        return (this).numberEds;
    }

    public Collection<V> getNodes() {
        ArrayList<V> nodeToReturn = new ArrayList<>();
        for (Map.Entry<V, Integer> node : this.hashVert.entrySet()) {
            nodeToReturn.add(node.getKey());
        }
        return nodeToReturn;
    }

    public Collection<? extends AbstractEdge<V, L>> getEdges() {
        ArrayList<Edge<V, L>> returnValue = new ArrayList<>();

        (this).neighbours.forEach((node) -> returnValue.addAll(node));

        return returnValue;
    }

    public Collection<V> getNeighbours(V a) {
        ArrayList<V> Neighbours = new ArrayList<>();

        (this).neighbours.get((this).findIndex(a)).forEach((node) -> Neighbours.add(node.getEnd()));

        return Neighbours;
    }

    public L getLabel(V a, V b) throws GraphExpectation {
        if ((this).isLable) {
            if (containsEdge(a, b)) {
                int posA = findIndex(a);

                for (Edge<V, L> arrayList : (this).neighbours.get(posA)) {
                    if (arrayList.getEnd().equals(b)) {
                        return arrayList.getLabel();
                    }
                }

                return null;
            } else {
                throw new GraphExpectation("The edge where to take the label did not exist");
            }
        } else {
            throw new GraphExpectation("The node where to take the label did not exist");
        }
    }

    /**
     *
     * it take the edge a -> b if exist
     * 
     * @param a the node of start
     * @param b the node of end
     * @return it return the edge of a -> b
     */
    public AbstractEdge<V, L> getTheEdge(V a, V b) throws GraphExpectation {
        if (containsEdge(a, b)) {
            int posA = findIndex(a);
            for (Edge<V, L> edge : (this).neighbours.get(posA)) {
                if (edge.getEnd().equals(b)) {
                    return edge;
                }
            }
            return null;
        } else {
            throw new GraphExpectation("The edge where to take the label did not exist");
        }
    }
}
