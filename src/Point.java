import java.awt.*;


/**
 * Created by Administrator on 2017/7/18 0018.
 */
public class Point implements Cloneable,Comparable<Point>{
    private int x;
    private int y;
    private static int xcount = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        xcount = Square.panWid/Square.lenOfSize;
    }

    @Override
    public int compareTo(Point o) {
        return  (y * xcount + x)- (o.y * xcount + o.x );
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object p1) {
        Point p = (Point) p1;
        if (this.x == p.x && this.y == p.y) {
            return true;
        }else
            return false;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean isBig(Point p) {
        int ret ;
        ret = (y * xcount + x)- (p.y * xcount + p.x );
        if (ret > 0) {
            return true;
        }else{
            return false;
        }
    }
}
