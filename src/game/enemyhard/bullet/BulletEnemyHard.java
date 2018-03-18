package game.enemyhard.bullet;

import base.GameObject;
import renderer.ImageRenderer;

public class BulletEnemyHard extends GameObject {

    private BulletEnemyHardMove bulletEnemyHardMove;

    public BulletEnemyHard() {
        this.renderer = new ImageRenderer("resources/square/square_deadly_bullet.png");
        this.bulletEnemyHardMove = new BulletEnemyHardMove();
    }

    @Override
    public void run() {
        super.run();
        this.bulletEnemyHardMove.run(this);
    }
}
