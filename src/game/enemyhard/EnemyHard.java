package game.enemyhard;

import base.GameObject;
import base.GameObjectManager;
import game.enemyhard.attributes.EnemyHardMove;
import game.enemyhard.attributes.EnemyHardShoot;
import game.square.effect.ExplosionSquare;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import renderer.ImageRenderer;

import java.util.Arrays;

public class EnemyHard extends GameObject implements PhysicBody, HitObject {
    private BoxCollider boxCollider;

    public EnemyHard() {
        this.renderer = new ImageRenderer("resources/square/enemy_square_medium.png");
        this.attributeObjects = Arrays.asList(
                new EnemyHardMove(),
                new EnemyHardShoot()
        );
        this.boxCollider = new BoxCollider(40, 40);
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position);
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.isAlive = false;
        ExplosionSquare explosionSquare = GameObjectManager.instance.recycle(ExplosionSquare.class);
        explosionSquare.create(this.position);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
