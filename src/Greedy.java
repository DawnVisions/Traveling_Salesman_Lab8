import java.util.ArrayList;

public class Greedy {

    public static Path TSP(CostMatrix costMatrix)
    {
        //  Initialize path
        int[] path = new int[costMatrix.numberVertices+1];

        //  toVisit list contains vertices to visit
        ArrayList<Integer> toVisit = new ArrayList<>();
        for(int i = 1; i<costMatrix.numberVertices; i++)
        {
            toVisit.add(i);
        }

        //  For each current vertex, find the nextVertex out of toVist
        //      that has the smallest cost
        int currentVertex = 0;
        for( int i = 1; i < costMatrix.numberVertices; i++)
        {
            int indexOfNext = 0;
            int nextVertex = toVisit.get(indexOfNext);
            for(int j = 1; j<toVisit.size(); j++)
            {
                if(costMatrix.matrix[currentVertex][toVisit.get(j)] < costMatrix.matrix[currentVertex][nextVertex])
                {
                    indexOfNext = j;
                    nextVertex = toVisit.get(j);
                }
            }
            path[i] = nextVertex;
            toVisit.remove(indexOfNext);
            currentVertex = nextVertex;
        }

        //   Calculate cost and return path
        double cost = calcuatePathCost(path, costMatrix);
        return new Path(path, Math.floor(cost*100)/100);
    }

    private static double calcuatePathCost(int[] path, CostMatrix costMatrix) {
        double cost = 0;
        for ( int i = 0; i<path.length-1; i++)
        {
            cost += costMatrix.matrix[path[i]][path[i+1]];
        }
        return cost;
    }
}
