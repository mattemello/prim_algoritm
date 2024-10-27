package src3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;

public class PriorityQueue<E> implements AbstractQueue<E> {

    private ArrayList<E> arrayElement = null;
    private HashMap<E, Integer> hashElement = null;
    public Comparator<? super E> comparator;
    private int index;

    public PriorityQueue(Comparator<? super E> comparatorGiven) {
        this.comparator = comparatorGiven;
        this.arrayElement = new ArrayList<>();
        this.hashElement = new HashMap<>();
        this.index = 0;
    }

    public boolean empty() {
        return (this.arrayElement).isEmpty();
    }

    /**
     *
     * function to add an element
     * 
     * @param element: the element to add at the array
     */
    @Override
    public boolean push(E element) throws PriorityQueueExpectation {
        if (element == null) {
            throw new PriorityQueueExpectation(" the element to add can not be null!");
        }
        (this.hashElement).put(element, index);
        (this.index) += 1;
        return (this.arrayElement).add(element);
    }

    /**
     *
     * function that control if the element is in the array
     * 
     * @param element: the element to control is exist
     */
    @Override
    public boolean contains(E element) {
        return (this.hashElement).containsKey(element);
    }

    private int findPosition(E element) throws PriorityQueueExpectation {
        if (!contains(element)) {
            throw new PriorityQueueExpectation("the element to be removed did not exsist");
        }
        return (this.hashElement).get(element);
    }

    private void removeIndex(int indexRemove, E element) {
        (this.arrayElement).remove(indexRemove);
        (this.hashElement).remove(element);

        (this.hashElement).forEach((key, value) -> {
            if (value > indexRemove)
                (this.hashElement).put(key, value - 1);
        });

        (this.index) -= 1;
    }

    /**
     *
     * function to return the element at the top of the array
     * 
     * @return: the element at the top
     */
    public E top() {
        return (this.arrayElement).get(0);
    }

    /**
     *
     * the function to delite the element at the top of the array
     */
    public void pop() {
        removeIndex(0, (this.arrayElement).get(0));
    }

    /**
     *
     * the function to remove an element of the array
     * 
     * @param element: the element to be removed
     */
    public boolean remove(E element) throws PriorityQueueExpectation {
        if (element == null)
            throw new PriorityQueueExpectation("the element for the remove is null, it can not be null");

        removeIndex(findPosition(element), element);

        return !contains(element);
    }

    public E getElem(int index) {
        return (this).arrayElement.get(index);
    }

    public int dimensionQueue() {
        return (this).index;
    }

    /**
     *
     * a function to print the queue
     */
    public void printQueue() {
        System.out.println(":-------------:");
        for (int i = 0; i < (this).arrayElement.size(); i++) {
            System.out.println("[" + i + "] -> " + (this).arrayElement.get(i));
        }
        System.out.println(":-------------:");
    }
}
