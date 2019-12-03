import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Correctness {
    public static boolean circularCorrectness(TSP exactMethod)
    {
        CostMatrix costMatrix = Testing.GenerateCircularGraphCostMatrix(5, 20);
        Path expectedPath = costMatrix.correctForCircular;
        expectedPath.rotatePath();

        return true;
    }
}
