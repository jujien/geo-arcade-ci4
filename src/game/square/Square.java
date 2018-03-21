package game.square;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import game.square.effect.ExplosionSquare;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import utils.Utils;

/**
 * Square no implements interface PhysicBody
 * Square bat buoc phai implements tat ca method trong PhysicBody
 * Tuong tu nhu class. khi Square implements tu interface thi no cung co quan he Is-A. tuc la Square cung la PhysicBody
 * -> Hoan toan co the ep kieu square ve PhysicBody
 * Trong PhysicBody lai co ham get dc BoxCollider
 * -> Chi can ep dc ve kieu PhysicBody la hoan toan lay dc BoxCollider cua 1 con gameObject nao do
 * -> Nhung con nao co BoxCollider thi phai implements interface PhysicBody va nguoc lai*/
public class Square extends GameObject implements PhysicBody, HitObject {

    public Vector2D velocity;
    private BoxCollider boxCollider;
    private RunHitObject runHitObject;

    public Square() {
        this.renderer = new ImageRenderer("resources/square/enemy_square_small.png");
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(20, 20);
        this.runHitObject = new RunHitObject(Player.class);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position);
        this.runHitObject.run(this);
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
