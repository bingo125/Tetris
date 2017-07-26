/**
 * Created by Administrator on 2017/7/19 0019.
 */
public class StateMachine {
    final int start  = 1;
    final int pause  = 2;
    final int newSquare  = 3;
    final int end  = 4;
    final int resume  = 5;

    private TetrisService service =  null;
    private int mState;

    public StateMachine(int mState, TetrisService service) {
        this.mState = mState;
        this.service = service;
    }

    public void setmState(int mState) {
        this.mState = mState;
        handle();
    }

    public int getStart() {
        return start;
    }

    private void handle() {
        switch (mState) {
            case start:
                service.start();
            break;
            case pause:
                service.pause();
            break;
            case resume:
                service.resume();
                break;
            case newSquare:
                service.nextSquare();
            break;
            case end:
                service.failure();
            break;
        }
    }
}
