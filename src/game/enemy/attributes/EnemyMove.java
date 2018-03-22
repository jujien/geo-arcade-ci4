package game.enemy.attributes;

import base.AttributeObject;
import base.Vector2D;
import game.enemy.Enemy;

public class EnemyMove implements AttributeObject<Enemy> {

    public Vector2D velocity;
    public Direction direction;

    public EnemyMove() {
        this.velocity = new Vector2D();
    }

    @Override
    public void run(Enemy gameObject) {
        switch (this.direction) {
            case UP_DOWN:
                this.velocity.x = 0;
                break;
            case LEFT_TO_RIGHT:
                if (gameObject.position.x <= 0) this.velocity.x = Math.abs(this.velocity.x);
                if (gameObject.position.x >= 400) this.velocity.x = -this.velocity.x;
                this.velocity.y = 0;
                break;
        }
        gameObject.position.addUp(this.velocity);
        gameObject.isAlive = gameObject.position.y <= 600;
    }
}
