package game.enemyhard;

import base.GameObject;
import base.Vector2D;
import game.enemyhard.bullet.EnemyHardShoot;
import renderer.ImageRenderer;
import utils.Utils;

public class EnemyHard extends GameObject {

    public Vector2D velocity;
    private EnemyHardShoot enemyHardShoot;

    public EnemyHard() {
        this.renderer = new ImageRenderer("resources/square/enemy_square_medium.png");
        this.velocity = new Vector2D();
        this.enemyHardShoot = new EnemyHardShoot();
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.enemyHardShoot.run(this);
    }
}
