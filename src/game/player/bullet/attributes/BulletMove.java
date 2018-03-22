package game.player.bullet.attributes;

import base.AttributeObject;
import base.Vector2D;
import game.player.bullet.Bullet;

public class BulletMove implements AttributeObject<Bullet> {

    public Vector2D velocity;

    public BulletMove() {
        this.velocity = new Vector2D();
    }

    @Override
    public void run(Bullet gameObject) {
        gameObject.position.addUp(this.velocity);
        gameObject.isAlive = gameObject.position.y > 0;
    }
}
