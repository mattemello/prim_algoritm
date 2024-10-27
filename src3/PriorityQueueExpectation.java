package src3;

/**
 * 
 * @author: Massimello Matteo
 */
public class PriorityQueueExpectation extends Exception{

    /**
     * it throws the error
     * @param message: the message to be printed
     */
    public PriorityQueueExpectation(String message){
        super("--! ERROR ProrityQueue: " + message);
    } //PriorityQueueExpectation
}
