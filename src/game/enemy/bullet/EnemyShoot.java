package game.enemy.bullet;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import game.enemy.Enemy;

public class EnemyShoot {

    private FrameCounter frameCounter = new FrameCounter(40);

    public void run(Enemy enemy) {
        if (this.frameCounter.run()) {
            BulletEnemy bulletEnemy = GameObjectManager.instance.recycle(BulletEnemy.class);
            bulletEnemy.position.set(enemy.position);
            bulletEnemy.velocity.set(0, 4);
            this.frameCounter.reset();
        }
    }
}
