package game.player;

import base.GameObject;
import game.enemy.Enemy;
import game.enemy.bullet.BulletEnemy;
import game.enemyhard.EnemyHard;
import game.enemyhard.bullet.BulletEnemyHard;
import game.player.attributes.PlayerMove;
import game.player.attributes.PlayerRenderer;
import game.player.attributes.PlayerShoot;
import game.square.Square;
import input.MouseMotionInput;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import physic.RunHitObject;

import java.util.Arrays;

public class Player extends GameObject implements PhysicBody, HitObject {

    private BoxCollider boxCollider = new BoxCollider(40, 40);
    private RunHitObject runHitObject;

    public Player() {
        this.attributeObjects = Arrays.asList(
                new PlayerRenderer(),
                new PlayerShoot(),
                new PlayerMove()
        );
        this.runHitObject = new RunHitObject(
                Enemy.class,
                EnemyHard.class,
                BulletEnemy.class,
                Square.class,
                BulletEnemyHard.class
        );
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position);
        this.runHitObject.run(this);
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.getAttribute(PlayerRenderer.class).switchRender = true;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
