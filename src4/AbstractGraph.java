package src4;

import java.util.Collection;

/**
 *
 * @author: Massimello Matteo
 */
public interface AbstractGraph<V, L> {
    /**
     *
     * it say if the graph is directed or not
     */
    public boolean isDirected(); // dice se il grafo è diretto o meno -- O(1)

    /**
     *
     * it say if the graph have the label or not
     */
    public boolean isLabelled(); // dice se il grafo è etichettato o meno -- O(1)

    /**
     *
     * add a node in the graph
     * 
     * @param a: the element to add in the graph
     */
    public boolean addNode(V a) throws GraphExpectation; // aggiunge un nodo -- O(1)

    /**
     *
     * add a edge on a node (in case of labelled also the label)
     * 
     * @param a: the node where add the the edge
     * @param b: the edge to add at the node
     * @param l: the value of the connection from a to b
     */
    public boolean addEdge(V a, V b, L l) throws GraphExpectation; // aggiunge un arco dati estremi ed etichetta -- O(1)
                                                                   // (*)

    /**
     *
     * return true if a is contained in the graph
     * 
     * @param a: the value to search
     */
    public boolean containsNode(V a); // controlla se un nodo è nel grafo -- O(1)

    /**
     *
     * return true if b is contained in the a Edge
     * 
     * @param a: the node where to search b
     * @param b: the value to search in a
     */
    public boolean containsEdge(V a, V b) throws GraphExpectation; // controlla se un arco è nel grafo -- O(1) (*)

    /**
     *
     * remove a node
     * 
     * @param a: the value to remove from the graph
     */
    public boolean removeNode(V a) throws GraphExpectation; // rimuove un nodo dal grafo -- O(N)

    /**
     *
     * remove a edge
     * 
     * @param a: the node where remove the edge
     * @param b: the edge to remove
     */
    public boolean removeEdge(V a, V b) throws GraphExpectation; // rimuove un arco dal grafo -- O(1) (*)

    /**
     *
     * return the number of node
     */
    public int numNodes(); // numero di nodi -- O(1)

    /**
     *
     * return the value of edge
     */
    public int numEdges(); // numero di archi -- O(N)

    /**
     *
     * return all the node of the graph
     */
    public Collection<V> getNodes(); // recupero dei nodi del grafo -- O(N)

    /**
     *
     * return all the edge in the graph
     */
    public Collection<? extends AbstractEdge<V, L>> getEdges(); // recupero degli archi del grafo -- O(N)

    /**
     *
     * return all the edge of a specific node
     * 
     * @param a: the specific node
     */
    public Collection<V> getNeighbours(V a); // recupero dei nodi adiacenti ad un dato nodo -- O(1) (*)

    /**
     *
     * return the label of an arch
     * 
     * @param a: the node of start
     * @parma b: the node to end
     */
    public L getLabel(V a, V b) throws GraphExpectation; // recupero dell'etichetta di un arco -- O(1) (*)

}
