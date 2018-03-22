package game.enemyhard.attributes;

import base.AttributeObject;
import base.FrameCounter;
import base.GameObjectManager;
import game.enemyhard.EnemyHard;
import game.enemyhard.bullet.BulletEnemyHard;

public class EnemyHardShoot implements AttributeObject<EnemyHard> {

    private FrameCounter frameCounter = new FrameCounter(400);

    @Override
    public void run(EnemyHard gameObject) {
        if (this.frameCounter.run()) {
            BulletEnemyHard bulletEnemy = GameObjectManager.instance.recycle(BulletEnemyHard.class);
            bulletEnemy.position.set(gameObject.position);
            this.frameCounter.reset();
        }
    }
}
