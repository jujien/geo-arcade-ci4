package game.enemyhard.bullet;

import base.GameObject;
import base.GameObjectManager;
import game.square.effect.ExplosionSquare;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import renderer.ImageRenderer;

public class BulletEnemyHard extends GameObject implements PhysicBody, HitObject {

    private BulletEnemyHardMove bulletEnemyHardMove;
    private BoxCollider boxCollider;

    public BulletEnemyHard() {
        this.renderer = new ImageRenderer("resources/square/square_deadly_bullet.png");
        this.bulletEnemyHardMove = new BulletEnemyHardMove();
        this.boxCollider = new BoxCollider(30, 30);
    }

    @Override
    public void run() {
        super.run();
        this.bulletEnemyHardMove.run(this);
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
