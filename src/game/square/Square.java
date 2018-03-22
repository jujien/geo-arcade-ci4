package game.square;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import game.square.attributes.SquareMove;
import game.square.effect.ExplosionSquare;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import utils.Utils;

import java.util.Arrays;

public class Square extends GameObject implements PhysicBody, HitObject {

    private BoxCollider boxCollider;

    public Square() {
        this.renderer = new ImageRenderer("resources/square/enemy_square_small.png");
        this.attributeObjects = Arrays.asList(
                new SquareMove()
        );
        this.boxCollider = new BoxCollider(20, 20);
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position);
    }

    @Override
    public void getHit(GameObject gameObject) {
        ExplosionSquare explosionSquare = GameObjectManager.instance.recycle(ExplosionSquare.class);
        explosionSquare.create(this.position);
        this.isAlive = false;

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider; //return BoxCollider cua Square
    }
}
