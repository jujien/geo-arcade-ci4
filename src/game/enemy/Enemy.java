package game.enemy;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.enemy.bullet.EnemyShoot;
import game.square.effect.ExplosionSquare;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import renderer.ImageRenderer;
import utils.Utils;

public class Enemy extends GameObject implements PhysicBody, HitObject {
    public Vector2D velocity;
    private EnemyShoot enemyShoot;
    private BoxCollider boxCollider;
    public Direction direction;

    public Enemy() {
        this.renderer = new ImageRenderer("resources/square/enemy_square_medium.png");
        this.enemyShoot = new EnemyShoot();
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(40, 40);
    }

    @Override
    public void run() {
        super.run();
        switch (this.direction) {
            case UP_DOWN:
                this.velocity.x = 0;
                break;
            case LEFT_TO_RIGHT:
                if (this.position.x <= 0) this.velocity.x = Math.abs(this.velocity.x);
                if (this.position.x >= 400) this.velocity.x = -this.velocity.x;
                this.velocity.y = 0;
                break;
        }
        this.position.addUp(this.velocity);
        this.enemyShoot.run(this);
        this.boxCollider.position.set(this.position);
        if (this.position.y >= 600) {
            this.isAlive = false;
        }
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
