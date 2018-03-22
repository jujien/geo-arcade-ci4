package game.enemy.bullet.attributes;

import base.AttributeObject;
import base.Vector2D;
import game.enemy.bullet.BulletEnemy;

public class BulletEnemyMove implements AttributeObject<BulletEnemy> {

    public Vector2D velocity;

    public BulletEnemyMove() {
        this.velocity = new Vector2D();
    }

    @Override
    public void run(BulletEnemy gameObject) {
        gameObject.position.addUp(this.velocity);
        gameObject.isAlive = gameObject.position.y <= 600;
    }
}
