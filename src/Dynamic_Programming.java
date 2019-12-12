import java.util.ArrayList;

public class Dynamic_Programming {
    CostMatrix costMatrix;
    double matrix[][];
    Path solutionTable[][];

    public Path TSP(CostMatrix costMatrix)
    {
        //  Initialize cost matrix and solution table
        this.costMatrix = costMatrix;
        this.matrix = costMatrix.matrix;
        solutionTable = new Path[costMatrix.numberVertices][(int)Math.pow(2,costMatrix.numberVertices)];

        //  Initialize list of tourNodes to visit  1 -> 2 -> 3 -> ... -> N
        ArrayList<Integer> tourNodes = new ArrayList<>();
        for(int i = 1; i< costMatrix.numberVertices; i++)
        {
            tourNodes.add(i);
        }

        //  Call recursive function to find shortest path
        Path path = recursiveTSP(0,tourNodes);

        //  Print solution table for testing purposes
/*        for(int i = 0; i< costMatrix.numberVertices; i++)
        {
            System.out.print(i+ ": ");
            for(int j = 0; j< Math.pow(2,costMatrix.numberVertices); j++)
            {
                if(solutionTable[i][j] == null)
                    System.out.print(" 0.0 ");
                else
                    System.out.printf("%4.1f ", solutionTable[i][j].cost);
            }

            System.out.println();
        }*/

        //  Add 0 to the beginning and end of the path, return shortest path
        path.vertices.add(0);
        path.vertices.add(0,0);
        return path;
    }

    Path recursiveTSP(int startNode, ArrayList<Integer> tourNodes) {
        //        if ( subSolutionTable contains a solution for the startNode, tourNodes ) then
        //        return the solution from the table
        if (solutionTable[startNode][setToInteger(tourNodes)] != null) {
            Path path = solutionTable[startNode][setToInteger(tourNodes)];
            return new Path((ArrayList<Integer>) path.vertices.clone(), path.cost);
        }
        if (tourNodes.size() == 1) {
            //        cost: edgeCosts[startNode][tourNode] + edgeCosts[tourNode][endNode]
            //        save solution in the table (indexed by startNode, tourNodes set)
            //        return solution
            double cost = matrix[startNode][tourNodes.get(0)] + matrix[tourNodes.get(0)][0];
            solutionTable[startNode][setToInteger(tourNodes)] = new Path(tourNodes,cost);
            return new Path(tourNodes,cost);
        }
        else {
            //    consider that k as step from the startNode
            //        {tmpPath,tmpCost} = MctBruteForce(k, endNode, tourNodes-{k}, edgeCosts)
            //        cost of best tour starting with k is c = tmpCost + edgeCosts[startNode][k]
            Path bestSoFar = new Path();
            bestSoFar.cost = Double.POSITIVE_INFINITY;
            for (Integer node : tourNodes)
            {
                ArrayList<Integer> restOfTheNodes = (ArrayList<Integer>) tourNodes.clone();
                restOfTheNodes.remove(node);
                Path tempPath = recursiveTSP(node, restOfTheNodes);
                tempPath.cost += matrix[startNode][node];
                //        if c < best cost so far, then
                //        make c the best cost so far,
                //            make best path so far { k --> tmpPath }
                if (tempPath.cost < bestSoFar.cost)
                {
                    bestSoFar.vertices = (ArrayList<Integer>) tempPath.vertices.clone();
                    bestSoFar.vertices.add(0,node);
                    bestSoFar.cost = tempPath.cost;
                }
            }
            //        save solution in table (indexed by startNode, tourNodes set)
            //        return solution
            solutionTable[startNode][setToInteger(tourNodes)] = bestSoFar;
            return new Path(bestSoFar.vertices, bestSoFar.cost);
        }
    }

    //  Method which turns a set of numbers into an integer
    //  Uses binary digit 1 at a given index to represent that the number is in the set
    static int setToInteger(ArrayList<Integer> set)
    {
        int number = 0;
        for(Integer node:set)
        {
            number += Math.pow(2, node-1);
        }
        return number;
    }

    //   Opposite of setToInteger
    static ArrayList<Integer> binaryIntegerToSet(int number)
    {
        ArrayList<Integer> set = new ArrayList<>();
        for(int i = 32; i> 0; i--) {
            if (number >= Math.pow(2, i-1))
            {
                set.add(0, i);
                number -= Math.pow(2, i-1);
            }
        }
        return set;
    }
}
