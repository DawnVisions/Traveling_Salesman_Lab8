import java.awt.*;

public class Vertex extends Point {

    int number;



    public Vertex(int x, int y, int number) {
        super(x, y);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Point getPoint() {
        return super.getLocation();
    }
}
