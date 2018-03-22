package game.player.bullet;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.enemy.Enemy;
import game.enemy.bullet.BulletEnemy;
import game.enemyhard.EnemyHard;
import game.enemyhard.bullet.BulletEnemyHard;
import game.player.bullet.attributes.BulletMove;
import game.square.Square;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import utils.Utils;

import java.util.Arrays;

public class Bullet extends GameObject implements PhysicBody, HitObject {

    private BoxCollider boxCollider;
    private RunHitObject runHitObject;

    public Bullet() {
        this.renderer = new ImageRenderer("resources/player/player_bullet.png");
        this.attributeObjects = Arrays.asList(
                new BulletMove()
        );
        this.boxCollider = new BoxCollider(10, 10);
        this.runHitObject = new RunHitObject(
                Square.class,
                Enemy.class,
                EnemyHard.class,
                BulletEnemy.class,
                BulletEnemyHard.class
        );
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position); //cho box di chuyen theo
        this.runHitObject.run(this);
    }

    @Override
    public void getHit(GameObject gameObject) { // paramter la mot con gameObject bi va cham
        this.isAlive = false; // nhung con chet van con tren vector gameObject -> lan chiem bo nho
        ExplosionBullet explosionBullet = GameObjectManager.instance.recycle(ExplosionBullet.class);
        explosionBullet.create(this.position);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
