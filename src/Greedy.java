import java.util.ArrayList;

public class Greedy {

    public static Path TSP(CostMatrix costMatrix)
    {
        //  Initialize path
        Path path = new Path();

        //  toVisit list contains vertices to visit
        ArrayList<Integer> toVisit = new ArrayList<>();
        for(int i = 1; i<costMatrix.numberVertices; i++)
        {
            toVisit.add(i);
        }

        //  For each current vertex, find the nextVertex out of toVist
        //      that has the smallest cost
        int currentVertex = 0;
        path.vertices.add(currentVertex);
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
            path.vertices.add(nextVertex);
            path.cost += costMatrix.matrix[currentVertex][nextVertex];
            toVisit.remove(indexOfNext);
            currentVertex = nextVertex;
        }
        path.vertices.add(0);
        path.cost += costMatrix.matrix[currentVertex][0];

        return path;
    }


}
