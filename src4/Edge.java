package src4;

public class Edge<V, L> implements AbstractEdge<V, L> {
    private V startEdge;
    private V endEdge;
    private L label;
    public Integer position;

    /**
     *
     * the costructor of the edge
     * 
     * @param start    the node of start
     * @param end      the node of end
     * @param label    the label of the node (it can be null)
     * @param position the position in your container, if you want to insert it
     */
    public Edge(V start, V end, L label, Integer position) {
        (this).startEdge = start;
        (this).endEdge = end;
        (this).label = label;
        (this).position = position;
    }

    /**
     *
     * controll if the edge is equal to a given edge
     * 
     * @param control the edge to controll
     * @return true if they are equals, false if not
     */
    public boolean Equal(Edge<V, L> control) {
        return (this).startEdge.equals(control.getStart()) && (this).endEdge.equals(control.getEnd())
                && (this).label.equals(control.getLabel());
    }

    public int getPosition() {
        return (this).position;
    }

    // il nodo di partenza dell'arco
    public V getStart() {
        return (this).startEdge;
    }

    // il nodo di arrivo dell'arco
    public V getEnd() {
        return (this).endEdge;
    }

    // l'etichetta dell'arco
    public L getLabel() {
        return (this).label;
    }
}
