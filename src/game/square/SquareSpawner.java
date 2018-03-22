package game.square;

import action.*;
import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import game.square.attributes.SquareMove;
import game.square.circle.CircleSquare;
import game.square.matrix.MatrixSquare;

import java.util.Random;

public class SquareSpawner extends GameObject {

    private Random random = new Random();

    public SquareSpawner() {
    }

    public void createNormal() {
        Action create = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                // Tao ra square
                Square square = GameObjectManager.instance.recycle(Square.class);
                square.position.set(random.nextInt(400), 0);
                square.getAttribute(SquareMove.class).velocity.set(0, random.nextInt(3) + 2);
                return true;
            }

            @Override
            public void reset() {

            }
        };
        Action wait = new ActionAdapter() {
            private int count = 0;
            private FrameCounter frameCounter = new FrameCounter(80);
            @Override
            public boolean run(GameObject owner) {
                if (this.frameCounter.run()) {
                    this.count += 1;
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void reset() {
                this.frameCounter.reset();
                if (80 - this.count > 0) {
                    this.frameCounter.setMax(80 - this.count);
                }
            }
        };


        this.add(
                new ActionAdapter() {
                    private int count = 0;
                    private Action action = new SequenceAction(
                            wait,
                            create
                    );
                    @Override
                    public boolean run(GameObject owner) {
                        if (this.count == 80) {
                            return true;
                        } else {
                            if (this.action.run(owner)) {
                                this.action.reset();
                                this.count += 1;
                            }
                            return false;
                        }

                    }
                }
        );
    }


    public void createHard() {
        Action createMatrix = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                MatrixSquare matrixSquare = GameObjectManager.instance.recycle(MatrixSquare.class);
                matrixSquare.position.set(20, 20);
                matrixSquare.velocity.set(1, 0);
                matrixSquare.create();
                return true;
            }
        };
        Action createCircle = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                CircleSquare circleSquare = GameObjectManager.instance.recycle(CircleSquare.class);
                circleSquare.radius = random.nextInt(50) + 40;
                circleSquare.position.set(random.nextInt(200) + circleSquare.radius, 0);
                circleSquare.velocity.set(2,1);
                circleSquare.create();
                return true;
            }
        };
        this.add(new SequenceAction(
                new WaitAction(900),
                createMatrix,
                new WaitAction(600),
                createCircle,
                new WaitAction(600),
                createCircle,
                new WaitAction(600),
                createMatrix,
                createCircle
        ));
    }
}
