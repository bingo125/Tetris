import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/7/18 0018.
 */
public abstract class Square {
    // FIXME: 2017/7/18 0018
    public static final int lenOfSize = 16;


    public Square mStroe;
    private Point xy = null;

    protected int chgState = 0;

    public boolean isAlive() {
        return isBreath;
    }

    private  boolean isBreath =true;
    public int positionX () {
        return ((panWid/lenOfSize)/2) *lenOfSize;
    }

    public List<Point> getRelative() {
        return relative;
    }

    private List<Point> relative = null;

    public  static int panWid = 400;
    public  static int panHeigt = 640;
    public  static int iconWid = lenOfSize;
    public  static int iconHeigt = lenOfSize;

    final static Image[]  images = new Image[7];
    final String pathFormat = "images/square%d.jpg";
    private int images_idx;


    public Square(Square stroe) {

        if(images[0] == null ) {
            for (int i = 0; i < 7; i++) {
                String path = String.format(pathFormat, i);
                try {
                    images[i] = ImageIO.read(new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        this.mStroe = stroe;
        Random random = new Random();
        images_idx = random.nextInt(7);
    }

    public void setRelative(List<Point> relative) {
        this.relative = relative;
    }


    public void draw(Graphics graphics) {
        for (Point point : relative) {
            graphics.drawImage(images[images_idx], point.getX(), point.getY(), null);
        }
    }

    public void downBytime() {
        down();
    }

    abstract void change(List<Point> save);

    public void hMov(int ori ) {
        List<Point> newState = new ArrayList<>();
        for (Point point : relative) {
            Point p = null;
            try {
                p = (Point) point.clone();

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            int x = p.getX();
            if(ori < 0){
                x -= lenOfSize;
            }else{
                x += lenOfSize;
            }
            p.setX(x);
            newState.add(p);
        }
        if (bound(newState, mStroe.getRelative())) {
            relative = newState;
        }
    }

    public void down() {
        List<Point> newState = new ArrayList<>();

        for (Point point : relative) {
            Point p = null;
            try {
                 p = (Point) point.clone();

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            int y = p.getY();
            y += lenOfSize;
            p.setY(y);
            newState.add(p);
        }

        if(bound(newState, mStroe.getRelative())){
            relative = newState;
        }
    }

    public int getLenOfSize() {
        return lenOfSize;
    }

    public boolean bound(List<Point>newState, List<Point> save) {
        for (int i = 0; i < newState.size(); i++) {
            int x = newState.get(i).getX();
            int y = newState.get(i).getY();
            if (x < 0 || x > panWid - lenOfSize) {
                return false;
            }
            if(y <= 0) {
                return false;
            }
            if (y >= panHeigt) {
                isBreath = false;
                return false;
            }
            if( save != null &&save.contains(newState.get(i))){
                isBreath = false;
                return false;
            }
        }
        return true;
    }

    public List<Point> pointsClone() {
        List<Point> tmp = new ArrayList<>();
        for (int i = 0; i < relative.size(); i++) {
            try {
                tmp.add((Point) relative.get(i).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return tmp;
    }
}
