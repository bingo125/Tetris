import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2017/7/19 0019.
 */
public class SquareManager {
    Square current = null;
    Random random = null;
    private Square mSquare;

    public SquareManager() {
        mSquare = new Square(mSquare) {
            @Override
            void change(List<Point> save) {

            }
        };
        mSquare.setRelative(new ArrayList<>());

        for (int i = 640 -Square.lenOfSize; i > 0; i-= Square.lenOfSize) {
            for (int j = 400 -Square.lenOfSize; j > 0; j-= Square.lenOfSize) {
                Point p = new Point(j, i);
                mSquare.getRelative().add(p);
            }
            if(i < 400){
                break;
            }
        }
        random = new Random();
        current = getNewSquare();
    }

    private List<Integer> sum() {
        List<Point> p = mSquare.getRelative();
        List<Integer> disappear = new ArrayList<>();
        Point start  = null;
        Point end  = null;
        int start_idx = -1;
        try {
            start = (Point) p.get(0).clone();
            end = (Point) p.get(0).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        start.setX(0);
        end.setX(384);

        int idxStart = 0;

        for (int i = idxStart; i < p.size(); i++) {
            Point tmp = p.get(i);
            if (start.equals(tmp)) {
                 start_idx  = i;
            } else if (end.equals(tmp)) {
                if (start_idx == -1) {
                    i = nextLine(start, end, p, i);
                    if (i == -1) {
                        break;
                    }else{
                        i --;
                        start_idx = -1;
                    }
                }else{
                    int count = i - start_idx +1;
                    if (count == Square.panWid/Square.lenOfSize ) {
                        disappear.add(tmp.getY());
                        i = nextLine(start, end, p,i);
                        if (i == -1) {
                            break;
                        }else{
                            i --;
                            start_idx = -1;
                        }
                    }
                }

            }else if (tmp.isBig(start) && end.isBig(tmp)){

                if (start_idx == -1) {
                    i = nextLine(start, end, p, i);
                    if (i == -1) {
                        break;
                    }else{
                        i --;
                    }
                }
            }else{
                i = nextLine(start, end, p, i);
                if (i == -1) {
                    break;
                }else{
                    i --;
                }
            }
        }
        if (disappear.isEmpty()) {
            return null;
        }
        return disappear;
    }

// 进入下一行的首部
    private int nextLine(Point start, Point end, List<Point> p, int cur) {
        int y ;
        y = start.getY() + Square.lenOfSize;
        start.setY(y);
        end.setY(y);
        for (int i = cur; i < p.size(); i++) {
            if (p.get(i).getY() == y) {
                return i ;
            }
        }
        return -1;
    }


    private Square getNewSquare() {
        Square tmp = null;
        int idx = random.nextInt(5) + 1;
// FIXME: 2017/7/21 0021
        String classname = "Square%d";
        try {
            Class c = Class.forName(String.format(classname, idx));
            Constructor constructor = c.getConstructor(Square.class);
            tmp = (Square) constructor.newInstance(mSquare);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public Image getNextSquare() {
        return null;
    }

    public void paint(Graphics graphics) {

        if (!current.isAlive()) {
            mSquare.getRelative().addAll(current.getRelative());
            Collections.sort(mSquare.getRelative());
            List<Integer> disapear = sum();
            if(disapear != null){
                Iterator<Point> it = mSquare.getRelative().iterator();
                while (it.hasNext()) {

                    Point p= it.next();
                    for (int i = 0; i < disapear.size(); i++) {
                        if (disapear.get(i) == p.getY()) {
                            it.remove();
                        }else if(p.getY() < disapear.get(i)){
                            p.setY(p.getY() + Square.lenOfSize);
                        }
                    }
                }
            }
            current = getNewSquare();
        }
        current.draw(graphics);
        mSquare.draw(graphics);
    }

    public void onTick() {
        if (current == null) {
            return;
        }
        current.downBytime();
    }

    public void left() {
        current.hMov(-1);
    }

    public void right() {
        current.hMov(1);
    }

    public void down() {
        current.down();
    }

    public void change() {
        current.change(null);
    }


}
