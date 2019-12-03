public class Path {

    public int[] vertices;
    public double cost;

    public Path(int[] vertices, double cost) {
        this.vertices = vertices;
        this.cost = cost;
    }

    public void printPath() {
        System.out.print("{ " + vertices[0]);
        for (int i = 1; i < vertices.length; i++)
        {
            System.out.print(", " + vertices[i]);
        }
        System.out.println(" } Cost = " + cost);
    }
}
