package src4;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GraphTestRunner {

    /**
     *
     * @param args: the command line arguments
     */
    public static void main(String[] args) {
        Result rec = JUnitCore.runClasses(GraphTest.class);

        for (Failure fail : rec.getFailures()) {
            System.out.println(fail.toString());
        }

        System.out.println("Esito test: " + rec.wasSuccessful());
    }
}
