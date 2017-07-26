import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Square2 extends Square {


    public Square2(Square s) {
        super(s);
        // 平子
        List <Point>relative = new ArrayList<>();
        relative.add(new Point(positionX(),  0));
        relative.add(new Point(positionX() + iconWid ,     0));

        relative.add(new Point(positionX() + iconWid ,     iconWid));
        relative.add(new Point(positionX() ,     iconWid));

        setRelative(relative);
    }

    @Override
    void change(List<Point> save) {
    }
}
