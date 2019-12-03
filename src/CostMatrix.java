import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class CostMatrix {

    int numberVertices;
    double matrix[][];
    Point[] vertices;

    public CostMatrix(int numberVertices) {
        this.numberVertices = numberVertices;
        matrix = new double[numberVertices][numberVertices];
    }

    public void fillRandomMatrix(int maxEdgeCost)
    {
        for(int i = 0; i<numberVertices; i++)
        {
            for (int j = i+1; j < numberVertices; j++)
            {
                double rand = randomInteger(1, maxEdgeCost);
                matrix[i][j] = rand;
                matrix[j][i] = rand;
            }
        }
    }

    public static int randomInteger(int min, int max)
    {
        return (int) (min + Math.random()*(max-min));
    }

    public void fillEuclideanMatrix(int maxCoordinate)
    {
        //  Create points with random x,y coordinates and store in vertices array
        vertices = new Point[numberVertices];
        for (int i = 0; i<numberVertices; i++)
        {
            vertices[i] = new Point(randomInteger(0, maxCoordinate), randomInteger(0, maxCoordinate));
        }
        //  Calculate the point-to-point distance between each vertex
        pointToPointDistancesToMatrix();

    }

    private void pointToPointDistancesToMatrix() {
        for(int i = 0; i<numberVertices; i++)
        {
            for (int j = i+1; j < numberVertices; j++)
            {
                double distance = vertices[i].distance(vertices[j]);
                matrix[i][j] = Math.floor(distance*100)/100;
                matrix[j][i] = Math.floor(distance*100)/100;
            }
        }
    }

    public void fillCircularMatrix(int radius)
    {
        //  Find the angle needed to place each vertex equally around the circle
        double angle = 360/numberVertices;
        System.out.println(angle);
        //  Use sin and cos identities to get x,y coordinates for each angle around the circle
        double currentAngle = 0;
        ArrayList<Point> sortedVertices = new ArrayList<Point>();
        for (int i = 0; i<numberVertices; i++)
        {
            double radian = currentAngle*Math.PI/180;
            double x = Math.cos(radian)*radius;
            double y = Math.sin(radian)*radius;
           sortedVertices.add(new Point((int) Math.round(x), (int)Math.round(y)));
            currentAngle += angle;
        }
        //  Shuffle the vertices so they are in a random order around the circle
        Collections.shuffle(sortedVertices);
        //  Store the shuffled result in the vertices array
        vertices = new Point[numberVertices];
        for(int i = 0; i<numberVertices; i++)
        {
            vertices[i] = sortedVertices.get(i);
        }
        //  Calculate the distance between each vertex
        pointToPointDistancesToMatrix();
    }

    public void printMatrix()
    {
        System.out.print("  ");
        for(int i = 0; i< numberVertices; i++)
        {
            System.out.printf("%5d", i);
        }
        System.out.println();
        String line = "-";
        System.out.println(line.repeat(numberVertices*6));
        for (int i = 0; i< numberVertices; i++)
        {
            System.out.print(i + " | ");
            for(int j = 0; j<numberVertices;j++)
            {
                System.out.printf("%4.1f ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}