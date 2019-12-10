import java.util.ArrayList;

public class Dynamic_Programming {
    CostMatrix costMatrix;
    double matrix[][];
    Path solutionTable[][];

    public Path TSP(CostMatrix costMatrix)
    {
        this.costMatrix = costMatrix;
        this.matrix = costMatrix.matrix;
        solutionTable = new Path[costMatrix.numberVertices][(int)Math.pow(2,costMatrix.numberVertices)];

        ArrayList<Integer> tourNodes = new ArrayList<>();
        for(int i = 1; i< costMatrix.numberVertices; i++)
        {
            tourNodes.add(i);
        }
        Path path = recursiveTSP(0,tourNodes);
/*        for(int i = 0; i< costMatrix.numberVertices; i++)
        {
            System.out.print(i+ ": ");
            for(int j = 0; j< Math.pow(2,costMatrix.numberVertices); j++)
            {
                if(solutionTable[i][j] == null)
                    System.out.print(" 0.0 ");
                else
                    System.out.print(solutionTable[i][j].cost + " ");
            }

            System.out.println();
        }*/
        path.vertices.add(0);
        path.vertices.add(0,0);
        path.printPath();
        return path;
    }

    Path recursiveTSP(int startNode, ArrayList<Integer> tourNodes) {
        //        if ( subSolutionTable contains a solution for the startNode, tourNodes ) then
        //        return the solution from the table
        if (solutionTable[startNode][setToInteger(tourNodes)] != null) {
            double cost = solutionTable[startNode][setToInteger(tourNodes)].cost;
            Path path = new Path(tourNodes,cost);
            return path;
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
                    tempPath.vertices.add(0,node);
                    bestSoFar.vertices = tempPath.vertices;
                    bestSoFar.cost = tempPath.cost;
                }
            }
            //        save solution in table (indexed by startNode, tourNodes set)
            //        return solution
            solutionTable[startNode][setToInteger(tourNodes)] = bestSoFar;
            return bestSoFar;
        }
    }

    static int setToInteger(ArrayList<Integer> set)
    {
        int number = 0;
        for(Integer node:set)
        {
            number += Math.pow(2, node-1);
        }

        return number;
    }

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
