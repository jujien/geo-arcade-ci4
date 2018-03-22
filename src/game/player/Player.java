package game.player;

import base.FrameCounter;
import base.GameObject;
import game.enemy.Enemy;
import game.enemy.bullet.BulletEnemy;
import game.enemyhard.EnemyHard;
import game.enemyhard.bullet.BulletEnemyHard;
import game.player.bullet.PlayerShoot;
import game.square.Square;
import input.MouseMotionInput;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.AnimationRenderer;
import renderer.ImageRenderer;
import renderer.Renderer;
import utils.Utils;

public class Player extends GameObject implements PhysicBody, HitObject {

    private PlayerShoot playerShoot;
    private Renderer imageRenderer;
    private Renderer animationRenderer;
    private BoxCollider boxCollider = new BoxCollider(40, 40);
    private FrameCounter frameCounter;
    private boolean isAnimation;
    private RunHitObject runHitObject;

    public Player() {
        this.imageRenderer = new ImageRenderer("resources/player/straight.png");
        this.animationRenderer = new AnimationRenderer(
                5,
                "resources/player/straight.png",
                "resources/player/straight_white.png",
                "resources/player/straight.png",
                "resources/player/straight_white.png",
                "resources/player/straight.png"

        );
        this.frameCounter = new FrameCounter(50);
        this.renderer = this.imageRenderer;
        this.playerShoot = new PlayerShoot();
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
        this.playerShoot.run(this);
        this.position.set(MouseMotionInput.instance.position); //chinh lai dieu kien de ngan player ra ngoai window
        this.boxCollider.position.set(this.position);
        if (this.isAnimation) {
            if (this.frameCounter.run()) {
                this.isAnimation = false;
                this.renderer = this.imageRenderer;
                this.frameCounter.reset();
            }
        }
        this.runHitObject.run(this);
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.renderer = this.animationRenderer;
        this.isAnimation = true;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
