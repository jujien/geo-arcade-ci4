package game.enemy;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.enemy.attributes.Direction;
import game.enemy.attributes.EnemyMove;
import game.enemy.attributes.EnemyShoot;
import game.square.effect.ExplosionSquare;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import renderer.ImageRenderer;

import java.util.Arrays;

public class Enemy extends GameObject implements PhysicBody, HitObject {

    private BoxCollider boxCollider;

    public Enemy() {
        this.renderer = new ImageRenderer("resources/square/enemy_square_medium.png");
        this.attributeObjects = Arrays.asList(
                new EnemyMove(),
                new EnemyShoot()
        );
        this.boxCollider = new BoxCollider(40, 40);
    }

    @Override
    public void run() {
        super.run();
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
