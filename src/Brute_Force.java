import java.awt.*;
import java.util.Arrays;

public class Brute_Force {

    public static Path TSP(CostMatrix costMatrix)
    {
        //  Initialize path 0 -> 1 -> 2 -> ... -> n -> 0
        int[] path = new int[costMatrix.numberVertices+1];
        for(int i = 0; i< costMatrix.numberVertices; i++)
        {
            path[i] = i;
        }
        path[costMatrix.numberVertices] = 0;
        int[] currentShortest = path.clone();

        //  Initialize cost to cost of the incremental path
        double cost = calcuatePathCost(path, costMatrix);

        //  Heap's iterative algorithm to get all path permutations
        //  Changes made to not swap the first or last vertex
        //       Path always starts and ends at 0
        int[] indexes = new int[costMatrix.numberVertices];
        Arrays.fill(indexes, 1);
        int i = 0;
        while (i < costMatrix.numberVertices)
        {
            if (indexes[i] < i) {
                swap(path, i % 2 != 0 ?  1: indexes[i], i);
                //  Check if cost of the current permutation is less that the current cost
                if (calcuatePathCost(path, costMatrix) < cost)
                {
                    currentShortest = path.clone();
                }
                indexes[i]++;
                i = 0;
            }
            else {
                indexes[i] = 1;
                i++;
            }
        }
        return new Path(currentShortest, Math.floor(cost*100)/100);
    }

    private static double calcuatePathCost(int[] path, CostMatrix costMatrix) {
        double cost = 0;
        for ( int i = 0; i<path.length-1; i++)
        {
            cost += costMatrix.matrix[path[i]][path[i+1]];
        }
        return cost;
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

}
