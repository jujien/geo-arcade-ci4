package game.square;

import action.*;
import base.GameObject;
import base.GameObjectManager;

import java.util.Random;

public class SquareSpawner extends GameObject {

//    private FrameCounter frameCounter;
    private Random random = new Random();


    public SquareSpawner() {
//        this.frameCounter = new FrameCounter(60);
    }

    public void create() {
        Action create = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                // Tao ra square
                Square square = GameObjectManager.instance.recycle(Square.class);
                square.position.set(random.nextInt(400), 0);
                square.velocity.set(0, random.nextInt(3) + 2);
                return true;
            }

            @Override
            public void reset() {

            }
        };
        this.add(
                new ActionAdapter() {
                    private int count = 0;
                    private Action action = new SequenceAction(
                            new WaitAction(60),
                            create
                    );
                    @Override
                    public boolean run(GameObject owner) {
                        if (this.count == 10) {
                            return true;
                        } else {
                            if (this.action.run(owner)) {
                                this.action.reset();
                                this.count += 1;
                            }
                            return false;
                        }

                    }

                    @Override
                    public void reset() {
                        //this.action.reset();
                    }
                }
        );
    }

//    @Override
//    public void run() {
//        super.run();
//        if (this.frameCounter.run()) {
//            Square square = new Square();
//            square.position.set(random.nextInt(400), 0);
//            square.velocity.set(0, random.nextInt(3) + 2);
//            GameObjectManager.instance.add(square);
//            this.frameCounter.reset();
//        }
//    }
}
