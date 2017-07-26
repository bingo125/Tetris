import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
    SquareManager m  = null;

    public GamePanel(SquareManager manager) {
        m = manager;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (m == null) {
                    return ;
                }
                m.onTick();
                repaint();
            }
        }, 0,1000);


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        m.paint(g);
    }
    Timer timer = null;
}
