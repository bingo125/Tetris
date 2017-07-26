import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Administrator on 2017/7/18 0018.
 */
public class TetrisFrame extends JFrame {
    private JLabel Next;
    private GamePanel mGamePanel;
    private JPanel mGameInfo;
    private TetrisService mService;
    private JLabel start;
    private JLabel resume;
    private JLabel pause;


    public TetrisFrame() throws HeadlessException {
        SquareManager manager = UIinit();
        mService = new TetrisService(manager);
        ListenerInit();
    }

    private SquareManager UIinit() {
        this.setTitle("俄罗斯方块");
        mGameInfo = new JPanel();
        mGameInfo.setBackground(Color.GRAY);

        BoxLayout toolPanelLayout = new BoxLayout(mGameInfo, BoxLayout.Y_AXIS);

        //鍒嗘暟
        mGameInfo.setLayout(toolPanelLayout);
        mGameInfo.setBorder(new EtchedBorder());
        Box scoreBox = Box.createVerticalBox();
        JLabel scoreInfo = new JLabel("分      数 ");
        JLabel score = new JLabel("         0");
        scoreBox.add(scoreInfo);
        scoreBox.add(score);


        Box levelBox = Box.createVerticalBox();
        JLabel levelInfo = new JLabel("等  级");
        JLabel level = new JLabel("         0");
        levelBox.add(levelInfo);
        levelBox.add(level);


        Box pauseBox = Box.createVerticalBox();
        pause = new JLabel();
        pause.setIcon(new ImageIcon("Tetris/images/button-bg-pause.gif"));
        pause.setPreferredSize(new Dimension(75, 30));
        pauseBox.add(pause);


        Box resumeBox = Box.createVerticalBox();
        resume = new JLabel();
        resume.setIcon(new ImageIcon("Tetris/images/button-bg-resume.gif"));
        resume.setPreferredSize(new Dimension(75, 30));
        resumeBox.add(resume);


        Box startBox = Box.createVerticalBox();
        start = new JLabel();
        ImageIcon icon = new ImageIcon("Tetris/images/button-bg-start.gif");
        start.setIcon(new ImageIcon("Tetris/images/button-bg-start.gif"));
        start.setPreferredSize(new Dimension(75, 30));
        startBox.add(start);


        Box NextBox = Box.createVerticalBox();
        JLabel NextInfo = new JLabel("下一个");
        Next = new JLabel();
        NextBox.add(NextInfo);
        NextBox.add(Next);


        mGameInfo.add(scoreBox);
        mGameInfo.add(Box.createVerticalStrut(15));
        mGameInfo.add(levelBox);
        mGameInfo.add(Box.createVerticalStrut(18));
        mGameInfo.add(pauseBox);
        mGameInfo.add(Box.createVerticalStrut(18));
        mGameInfo.add(resumeBox);
        mGameInfo.add(Box.createVerticalStrut(18));
        mGameInfo.add(startBox);
        mGameInfo.add(Box.createVerticalStrut(20));
        mGameInfo.add(NextBox);

        SquareManager manager= new SquareManager();
        mGamePanel = new GamePanel(manager);

        this.setLayout(new BorderLayout());
        this.add(mGameInfo, BorderLayout.EAST);
        this.add(mGamePanel, BorderLayout.CENTER);
        this.pack();
        this.setLocation(new Point(360, 260));
        this.setSize(new Dimension(485, 669));
        this.setResizable(false);
        return manager;
    }

    private void ListenerInit() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        mService.right();
                        break;
                    case KeyEvent.VK_LEFT:
                        mService.left();
                        break;
                    case KeyEvent.VK_DOWN:
                        mService.down();
                        break;
                    case KeyEvent.VK_UP:
                        mService.change();
                        break;
                }
                repaint();
            }
        });

        pause.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pause.setIcon(new ImageIcon("Tetris/images/button-bg-pause-on.gif"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pause.setIcon(new ImageIcon("Tetris/images/button-bg-pause.gif"));
            }
        });
        resume.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                resume.setIcon(new ImageIcon("Tetris/images/button-bg-resume-on.gif"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                resume.setIcon(new ImageIcon("Tetris/images/button-bg-resume.gif"));
            }
        });
        start.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                start.setIcon(new ImageIcon("Tetris/images/button-bg-start-on.gif"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                start.setIcon(new ImageIcon("Tetris/images/button-bg-start.gif"));
            }
        });
    }
}