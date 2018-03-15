package game.player.bullet;

import base.GameObject;
import game.square.Square;
import physic.BoxCollider;

/**
 * Created by huynq on 3/15/18.
 */
public class HitSquare {

    public void run(Bullet bullet) {
        BoxCollider boxCollider = bullet.boxCollider;
        Square square = GameObject.checkCollsion(boxCollider);
        if (square != null) {
            square.isAlive = false;
            bullet.getHit();
        }
    }
}
