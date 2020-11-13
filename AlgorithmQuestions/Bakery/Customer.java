package third;

import java.util.Comparator;

public class Customer{

    private int x;
    private int y;
    private int p;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "x=" + x +
                ", y=" + y +
                ", p=" + p +
                '}';
    }
}
