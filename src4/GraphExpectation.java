package src4;

/**
 * 
 * @author: Massimello Matteo
 */
public class GraphExpectation extends Exception{

    /**
      * 
     * @param message: the message display when a exception is throw
     */
    public GraphExpectation(String message){
        super("-> Error Graph: " + message);
    }
}
