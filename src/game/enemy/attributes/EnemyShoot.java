package game.enemy.attributes;

import base.AttributeObject;
import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import game.enemy.Enemy;
import game.enemy.bullet.BulletEnemy;
import game.enemy.bullet.attributes.BulletEnemyMove;

public class EnemyShoot implements AttributeObject<Enemy> {

    private FrameCounter frameCounter = new FrameCounter(40);

    @Override
    public void run(Enemy gameObject) {
        if (this.frameCounter.run()) {
            BulletEnemy bulletEnemy = GameObjectManager.instance.recycle(BulletEnemy.class);
            bulletEnemy.position.set(gameObject.position);
            bulletEnemy.getAttribute(BulletEnemyMove.class).velocity.set(0, 4);
            this.frameCounter.reset();
        }
    }
}
