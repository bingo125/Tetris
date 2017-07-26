/**
 * Created by Administrator on 2017/7/18 0018.
 */
public class TetrisService {

    public TetrisService(SquareManager mSquareMananger) {
        this.mSquareMananger = mSquareMananger;
    }

    private SquareManager mSquareMananger;

    public void start(){
        // TODO: 2017/7/19 0019
    }
    public void pause(){
        // TODO: 2017/7/19 0019
    }

    public void restart(){
        // TODO: 2017/7/19 0019
    }

    public void failure(){
        // TODO: 2017/7/19 0019
    }

    public void nextSquare() {
    }

    public void resume() {
    }

    public void left() {
        mSquareMananger.left();
    }
    public void right() {
        mSquareMananger.right();
    }

    public void down() {
        mSquareMananger.down();
    }

    public void change() {
        mSquareMananger.change();
    }
}
