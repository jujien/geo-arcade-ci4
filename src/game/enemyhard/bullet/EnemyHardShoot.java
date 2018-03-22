package game.enemyhard.bullet;

import base.FrameCounter;
import base.GameObjectManager;
import game.enemyhard.EnemyHard;

public class EnemyHardShoot {

    private FrameCounter frameCounter = new FrameCounter(400);

    public void run(EnemyHard enemy) {
        if (this.frameCounter.run()) {
            BulletEnemyHard bulletEnemy = GameObjectManager.instance.recycle(BulletEnemyHard.class);
            bulletEnemy.position.set(enemy.position);
            this.frameCounter.reset();
        }
    }
}
