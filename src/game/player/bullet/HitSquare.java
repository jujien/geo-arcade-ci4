package game.player.bullet;

import base.GameObjectManager;
import game.enemy.Enemy;
import game.square.Square;
import physic.BoxCollider;

/**
 * Created by huynq on 3/15/18.
 */
public class HitSquare {

    public void run(Bullet bullet) {
        BoxCollider boxCollider = bullet.getBoxCollider();
        Square square = GameObjectManager.instance.checkCollision(boxCollider, Square.class);
        if (square != null) {
            square.isAlive = false;
            bullet.getHit();
        }
    }
}
