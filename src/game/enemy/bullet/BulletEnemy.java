package game.enemy.bullet;

import base.GameObject;
import base.Vector2D;
import game.enemy.bullet.attributes.BulletEnemyMove;
import game.player.bullet.attributes.BulletMove;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import renderer.ImageRenderer;
import utils.Utils;

import java.util.Arrays;

public class BulletEnemy extends GameObject implements PhysicBody, HitObject {
    private BoxCollider boxCollider;

    public BulletEnemy() {
        this.attributeObjects = Arrays.asList(
                new BulletEnemyMove()
        );
        this.renderer = new ImageRenderer("resources/square/enemy_square_bullet.png");
        this.boxCollider = new BoxCollider(10, 10);

    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position);
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.isAlive = false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
