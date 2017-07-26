import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Square3 extends Square {


    public Square3(Square s) {
        super(s);
        List <Point>relative = new ArrayList<>();
        relative.add(new Point(positionX() -iconWid,  0));
        relative.add(new Point(positionX() ,     0));
        relative.add(new Point(positionX() + iconWid , 2*0));
        relative.add(new Point(positionX() , iconWid));
        setRelative(relative);
    }

    @Override
    void change(List<Point> save) {
        List<Point> c = pointsClone();
        int x = c.get(1).getX();
        int y = c.get(1).getY();
        if (chgState == 0) {

            c.get(0).setX(x);
            c.get(0).setY(y - lenOfSize);

            c.get(2).setX(x);
            c.get(2).setY(y +lenOfSize );

            c.get(3).setX(x - lenOfSize);
            c.get(3).setY(y);
            if(bound(c, save)){
                setRelative(c);
                chgState =1;
            }
        }else if(chgState ==1){
            c.get(0).setX(x - lenOfSize);
            c.get(0).setY(y);

            c.get(2).setX(x + lenOfSize);
            c.get(2).setY(y);

            c.get(3).setX(x);
            c.get(3).setY(y- lenOfSize);
            if(bound(c, save)){
                setRelative(c);
                chgState =2;
            }
        }else if(chgState == 2){
            c.get(0).setX(x);
            c.get(0).setY(y - lenOfSize);

            c.get(2).setX(x);
            c.get(2).setY(y +lenOfSize );

            c.get(3).setX(x + lenOfSize);
            c.get(3).setY(y);
            if(bound(c, save)){
                setRelative(c);
                chgState =3;
            }
        } else if (chgState == 3) {
            c.get(0).setX(x - lenOfSize);
            c.get(0).setY(y);

            c.get(2).setX(x  + lenOfSize);
            c.get(2).setY(y);

            c.get(3).setX(x);
            c.get(3).setY(y + lenOfSize);
            if(bound(c, save)){
                setRelative(c);
                chgState =0;
            }
        }
    }
}
