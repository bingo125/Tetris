import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Square1 extends Square {


    public Square1(Square s) {
        super(s);
        List <Point>relative = new ArrayList<>();
        relative.add(new Point(positionX(),  0));
        relative.add(new Point(positionX() ,     iconWid));
        relative.add(new Point(positionX() , 2*iconWid));
        relative.add(new Point(positionX() , 3*iconWid));
        setRelative(relative);
        chgState = 0;
    }

    @Override
    void change(List<Point> save ) {
        List<Point> s  = pointsClone();

        if (chgState == 0) {
            int y = s.get(1).getY();
            int x = s.get(1).getX();
            int x1 = x + lenOfSize;
            for (int i = 0; i < s.size(); i++) {
                s.get(i).setX(x1 - i * lenOfSize);
                s.get(i).setY(y);
            }
            if(bound(s, null)){

                chgState = 1;
                setRelative(s);
            }
        }else{
            int y = s.get(1).getY();
            int x = s.get(1).getX();
            int y1 = y - lenOfSize;
            for (int i = 0; i < s.size(); i++) {
                s.get(i).setX(x);
                s.get(i).setY(y +  i * lenOfSize );
            }
            if(bound(s, null)) {
                chgState = 0;
                setRelative(s);
            }
        }
        s = null;
    }
}
