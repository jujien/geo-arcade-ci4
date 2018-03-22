package game.square.circle;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.square.Square;
import game.square.attributes.SquareMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by huynq on 3/15/18.
 */
public class CircleSquare extends GameObject {

    private Random random;
    public Vector2D velocity;
    private List<Square> squares;
    public int radius;

    public CircleSquare() {
        this.random = new Random();
        this.velocity = new Vector2D();
        this.squares = new ArrayList<>();
    }

    public void create() {
        //this.radius = this.random.nextInt(50) + 40;
        for (double angle = 0.0; angle < 360.0; angle+=360.0/15) {
            Square square = GameObjectManager.instance.recycle(Square.class);
            square.position.set((new Vector2D(0, 1)).rotate(angle).multiply(this.radius)).addUp(this.position.x, this.radius);
            square.getAttribute(SquareMove.class).velocity.set(this.velocity);
            this.squares.add(square);
        }
    }

    @Override
    public void run() {
        super.run();

        if (this.position.x <= 0) {
            this.velocity.x = Math.abs(this.velocity.x);
        }
        if (this.position.x >= 400 - this.radius) {
            this.velocity.x = -this.velocity.x;
        }
        if (this.position.y <= 0) {
            this.velocity.y = Math.abs(this.velocity.y);
        }
        if (this.position.y >= 600 - this.radius) {
            this.velocity.y = -this.velocity.y;
        }
        this.position.addUp(this.velocity);

        this.squares.forEach(square -> square.getAttribute(SquareMove.class).velocity.set(velocity));
        this.squares.removeIf(square -> !square.isAlive);
        this.isAlive = !this.squares.isEmpty();
    }
}
