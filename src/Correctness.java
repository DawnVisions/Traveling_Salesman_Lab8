import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Correctness {

    @Test
    void circularCorrectnessForBruteForce() {
        CostMatrix costMatrix = Testing.GenerateCircularGraphCostMatrix(12, 100);
        System.out.println("Cost matrix: ");
        costMatrix.printMatrix();
        Path expectedPath = costMatrix.correctForCircular;
        Path actualPath = Brute_Force.TSP(costMatrix);
        System.out.print("Expected path: ");
        expectedPath.printPath();
        System.out.print("Actual path:   ");
        actualPath.printPath();
        Boolean firstCheck = expectedPath.vertices.equals(actualPath.vertices);
        Collections.reverse(expectedPath.vertices);
        Boolean reversedCheck = expectedPath.vertices.equals(actualPath.vertices);
        assertTrue(firstCheck || reversedCheck);
    }

    @Test
    void checkBruteForceAgainstStaticMatrix() {
        CostMatrix matrix = new CostMatrix(6);
        matrix.matrix[0][1] = 2;
        matrix.matrix[0][2] = 8;
        matrix.matrix[0][3] = 12;
        matrix.matrix[0][4] = 6;
        matrix.matrix[0][5] = 1;
        matrix.matrix[1][0] = 2;
        matrix.matrix[1][2] = 15;
        matrix.matrix[1][3] = 6;
        matrix.matrix[1][4] = 4;
        matrix.matrix[1][5] = 2;
        matrix.matrix[2][0] = 8;
        matrix.matrix[2][1] = 15;
        matrix.matrix[2][3] = 6;
        matrix.matrix[2][4] = 20;
        matrix.matrix[2][5] = 3;
        matrix.matrix[3][0] = 9;
        matrix.matrix[3][1] = 6;
        matrix.matrix[3][2] = 6;
        matrix.matrix[3][4] = 4;
        matrix.matrix[3][5] = 3;
        matrix.matrix[4][0] = 6;
        matrix.matrix[4][1] = 4;
        matrix.matrix[4][2] = 20;
        matrix.matrix[4][3] = 4;
        matrix.matrix[4][5] = 7;
        matrix.matrix[5][0] = 1;
        matrix.matrix[5][1] = 2;
        matrix.matrix[5][2] = 3;
        matrix.matrix[5][3] = 3;
        matrix.matrix[5][4] = 7;

        ArrayList<Integer> expectedPath = new ArrayList<>(Arrays.asList(0, 5, 2, 3, 4, 1, 0));
        ArrayList<Integer> reversedExpectedPath = new ArrayList<>(Arrays.asList(0, 1, 4, 3, 2, 5, 0));
        Path actualPath = Brute_Force.TSP(matrix);

        assertTrue(expectedPath.equals(actualPath.vertices) || reversedExpectedPath.equals(actualPath.vertices));
    }

    @Test
    void checkGreedyAgainstStaticMatrix() {
        CostMatrix matrix = new CostMatrix(6);
        matrix.matrix[0][1] = 2;
        matrix.matrix[0][2] = 8;
        matrix.matrix[0][3] = 12;
        matrix.matrix[0][4] = 6;
        matrix.matrix[0][5] = 1;
        matrix.matrix[1][0] = 2;
        matrix.matrix[1][2] = 15;
        matrix.matrix[1][3] = 6;
        matrix.matrix[1][4] = 4;
        matrix.matrix[1][5] = 2;
        matrix.matrix[2][0] = 8;
        matrix.matrix[2][1] = 15;
        matrix.matrix[2][3] = 6;
        matrix.matrix[2][4] = 20;
        matrix.matrix[2][5] = 3;
        matrix.matrix[3][0] = 9;
        matrix.matrix[3][1] = 6;
        matrix.matrix[3][2] = 6;
        matrix.matrix[3][4] = 4;
        matrix.matrix[3][5] = 3;
        matrix.matrix[4][0] = 6;
        matrix.matrix[4][1] = 4;
        matrix.matrix[4][2] = 20;
        matrix.matrix[4][3] = 4;
        matrix.matrix[4][5] = 7;
        matrix.matrix[5][0] = 1;
        matrix.matrix[5][1] = 2;
        matrix.matrix[5][2] = 3;
        matrix.matrix[5][3] = 3;
        matrix.matrix[5][4] = 7;

        ArrayList<Integer> expectedPath = new ArrayList<>(Arrays.asList(0, 5, 1, 4, 3, 2, 0));
        assertEquals(expectedPath, Greedy.TSP(matrix).vertices);
    }

    @Test
    void circularCorrectnessForDynamicProgramming() {
        CostMatrix costMatrix = Testing.GenerateCircularGraphCostMatrix(12, 100);
        System.out.println("Cost matrix: ");
        costMatrix.printMatrix();
        Path expectedPath = costMatrix.correctForCircular;
        System.out.print("Expected path: ");
        expectedPath.printPath();
        System.out.print("Actual path:   ");
        Dynamic_Programming dp = new Dynamic_Programming();
        Path actualPath = dp.TSP(costMatrix);
        actualPath.printPath();
        Boolean firstCheck = expectedPath.vertices.equals(actualPath.vertices);
        Collections.reverse(expectedPath.vertices);
        Boolean reversedCheck = expectedPath.vertices.equals(actualPath.vertices);
        assertTrue(firstCheck || reversedCheck);
    }
    @Test
    void checkDynamicProgrammingAgainstStaticMatrix() {
        CostMatrix matrix = new CostMatrix(6);
        matrix.matrix[0][1] = 2;
        matrix.matrix[0][2] = 8;
        matrix.matrix[0][3] = 12;
        matrix.matrix[0][4] = 6;
        matrix.matrix[0][5] = 1;
        matrix.matrix[1][0] = 2;
        matrix.matrix[1][2] = 15;
        matrix.matrix[1][3] = 6;
        matrix.matrix[1][4] = 4;
        matrix.matrix[1][5] = 2;
        matrix.matrix[2][0] = 8;
        matrix.matrix[2][1] = 15;
        matrix.matrix[2][3] = 6;
        matrix.matrix[2][4] = 20;
        matrix.matrix[2][5] = 3;
        matrix.matrix[3][0] = 9;
        matrix.matrix[3][1] = 6;
        matrix.matrix[3][2] = 6;
        matrix.matrix[3][4] = 4;
        matrix.matrix[3][5] = 3;
        matrix.matrix[4][0] = 6;
        matrix.matrix[4][1] = 4;
        matrix.matrix[4][2] = 20;
        matrix.matrix[4][3] = 4;
        matrix.matrix[4][5] = 7;
        matrix.matrix[5][0] = 1;
        matrix.matrix[5][1] = 2;
        matrix.matrix[5][2] = 3;
        matrix.matrix[5][3] = 3;
        matrix.matrix[5][4] = 7;

        ArrayList<Integer> expectedPath = new ArrayList<>(Arrays.asList(0, 5, 2, 3, 4, 1, 0));
        ArrayList<Integer> reversedExpectedPath = new ArrayList<>(Arrays.asList(0, 1, 4, 3, 2, 5, 0));
        Dynamic_Programming dp = new Dynamic_Programming();
        Path actualPath = dp.TSP(matrix);

        assertEquals(reversedExpectedPath, actualPath.vertices);
    }

    @Test
    void circularCorrectnessForGreedy() {
        CostMatrix costMatrix = Testing.GenerateCircularGraphCostMatrix(40, 100);
        System.out.println("Cost matrix: ");
        costMatrix.printMatrix();
        Path expectedPath = costMatrix.correctForCircular;
        Path actualPath = Greedy.TSP(costMatrix);
        System.out.print("Expected path: ");
        expectedPath.printPath();
        System.out.print("Actual path:   ");
        actualPath.printPath();
        Boolean firstCheck = expectedPath.vertices.equals(actualPath.vertices);
        Collections.reverse(expectedPath.vertices);
        Boolean reversedCheck = expectedPath.vertices.equals(actualPath.vertices);
        assertTrue(firstCheck || reversedCheck);
    }

    @Test
    void checkExactAlgorithmsAgainstEachother()
    {
        for(int i = 2; i< 14; i++)
        {
            CostMatrix costMatrix = Testing.GenerateRandomCostMatrix(i,25);
            Path brutePath = Brute_Force.TSP(costMatrix);
            System.out.print("Brute Force:         ");
            brutePath.printPath();
            Dynamic_Programming dp = new Dynamic_Programming();
            Path dynamicPath = dp.TSP(costMatrix);
            System.out.print("Dynamic Programming: ");
            dynamicPath.printPath();
            Boolean firstCheck = dynamicPath.vertices.equals(brutePath.vertices);
            Collections.reverse(dynamicPath.vertices);
            Boolean reversedCheck = dynamicPath.vertices.equals(brutePath.vertices);
            System.out.println();
            assertTrue(firstCheck || reversedCheck);
        }
    }
}
