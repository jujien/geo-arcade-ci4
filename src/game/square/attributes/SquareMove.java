package game.square.attributes;

import base.AttributeObject;
import base.Vector2D;
import game.square.Square;

public class SquareMove implements AttributeObject<Square> {

    public Vector2D velocity;

    public SquareMove() {
        this.velocity = new Vector2D();
    }

    @Override
    public void run(Square gameObject) {
        gameObject.position.addUp(this.velocity);
        if (gameObject.position.x < -200 || gameObject.position.x > 600 || gameObject.position.y < -200 || gameObject.position.y > 800) {
            gameObject.isAlive = false;
        }
    }
}
