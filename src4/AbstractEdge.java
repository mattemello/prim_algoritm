package src4;

public interface AbstractEdge<V, L> {

    /**
     *
     * take the node where the arc start
     * 
     * @return the node of start
     */
    public V getStart(); // il nodo di partenza dell'arco

    /**
     *
     * take the node where the arc end
     * 
     * @return the node of end
     */
    public V getEnd(); // il nodo di arrivo dell'arco

    /**
     *
     * get the label of the arc
     * 
     * @return the value of the label
     */
    public L getLabel(); // l'etichetta dell'arco

    /**
     *
     * get the position of the edge in your struct if you impost it
     */
    public int getPosition();
}
