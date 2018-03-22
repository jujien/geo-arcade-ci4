package game.enemyhard;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.enemyhard.bullet.EnemyHardShoot;
import game.square.effect.ExplosionSquare;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import renderer.ImageRenderer;
import utils.Utils;

public class EnemyHard extends GameObject implements PhysicBody, HitObject {

    public EnemyHardShoot enemyHardShoot;
    private BoxCollider boxCollider;
    public EnemyHardMove enemyHardMove;

    public EnemyHard() {
        this.renderer = new ImageRenderer("resources/square/enemy_square_medium.png");
        this.enemyHardShoot = new EnemyHardShoot();
        this.boxCollider = new BoxCollider(40, 40);
    }

    @Override
    public void run() {
        super.run();
        this.enemyHardMove.run(this);
        this.enemyHardShoot.run(this);
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
