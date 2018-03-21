package action;

import base.FrameCounter;
import base.GameObject;

/**
 * Delay thoi gian
 * sau mot khoang action tiep moi thuc hien*/
public class WaitAction implements Action {

    private FrameCounter frameCounter;

    public WaitAction(int max) {
        this.frameCounter = new FrameCounter(max);
    }

    @Override
    public boolean run(GameObject owner) {
        //neu true -> action thuc hien xong
        //new false -> action dang chua thuc hien xong
        return this.frameCounter.run();
    }

    @Override
    public void reset() {
        this.frameCounter.reset();
    }
}
