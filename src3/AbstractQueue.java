package src3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author: Massimello Matteo
 * @param<E>: type element of the class
 */
public interface AbstractQueue<E> {

    /**
     *
     * to see if the array is ampty
     */
    public boolean empty();

    /**
     *
     * function to add an element
     * 
     * @param element: the element to add at the array
     */
    public boolean push(E element) throws PriorityQueueExpectation;

    /**
     *
     * function that control if the element is in the array
     * 
     * @param element: the element to control is exist
     */
    public boolean contains(E element);

    /**
     *
     * function to return the element at the top of the array
     * 
     * @return: the element at the top
     */
    public E top();

    /**
     *
     * the function to delite the element at the top of the array
     */
    public void pop();

    /**
     *
     * the function to remove an element of the array
     * 
     * @param element: the element to be removed
     */
    public boolean remove(E element) throws PriorityQueueExpectation;
}
