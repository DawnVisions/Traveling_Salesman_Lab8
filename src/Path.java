import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Path {

    ArrayList<Integer> vertices;
    public double cost;

    public Path()
    {
        vertices = new ArrayList<>();
    }

    public Path(int numberVertices)
    {
        vertices = new ArrayList<>();
        for(Integer i = 0; i<numberVertices; i++)
        {
            vertices.add(i);
        }
    }

    public Path(ArrayList<Integer> vertices, CostMatrix matrix) {
        this.vertices = vertices;
        cost = this.calculatePathCost(matrix);
    }

    public void printPath() {
        System.out.print("{ " + vertices.get(0));
        for (int i = 1; i < vertices.size(); i++)
        {
            System.out.print(", " + vertices.get(i));
        }
        System.out.println(" } Cost = " + cost);
    }

    public void rotatePath()
    {
        this.printPath();
        int locationOfZero = vertices.indexOf(0);
        System.out.println(locationOfZero);
        Collections.rotate(vertices, locationOfZero*(-1));
        vertices.add(0);
        this.printPath();
    }

    public double calculatePathCost(CostMatrix costMatrix) {
        double cost = 0;
        for ( int i = 0; i<vertices.size()-1; i++)
        {
            cost += costMatrix.matrix[vertices.get(i)][vertices.get(i)];
        }
        this.cost = Math.floor(cost*100)/100;
        return this.cost;
    }
}
