package game.square.matrix;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.square.Square;
import game.square.attributes.SquareMove;

import java.util.Vector;

public class MatrixSquare extends GameObject {

    private Vector<Square> squares;
    public Vector2D velocity;
    private int count;

    public MatrixSquare() {
       this.squares = new Vector<>();
       this.velocity = new Vector2D();
    }

    public void create() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                Square square = new Square();
                square.position.set(this.position.add(j * (20 + 20), i * (20 + 20)));
                square.getAttribute(SquareMove.class).velocity.set(this.velocity);
                this.squares.add(square);
                GameObjectManager.instance.add(square);
            }
        }
    }

    @Override
    public void run() {
        super.run();
        if (this.position.x <= 0 ) {
            if (this.count >= 10) {
                this.count = 0;
                this.velocity.set(1, 0);
            } else {
                this.velocity.set(0, 1);
                this.count += 1;
            }

        }
        if (this.position.x >= 400 - 20 * 5 - 20 * 4){
            if (this.count >= 10) {
                this.count = 0;
                this.velocity.set(-1, 0);
            } else {
                this.velocity.set(0, 1);
                this.count += 1;
            }

        }
        this.position.addUp(this.velocity);
        this.squares.forEach(square -> square.getAttribute(SquareMove.class).velocity.set(velocity));
        this.squares.removeIf(square -> !square.isAlive);
        this.isAlive = !this.squares.isEmpty();
    }
}
