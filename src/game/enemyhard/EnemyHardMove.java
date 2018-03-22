package game.enemyhard;

import base.Vector2D;

public class EnemyHardMove {

     public Vector2D velocity = new Vector2D();

    public void run(EnemyHard enemyHard) {
        if (enemyHard.position.x <= 0) this.velocity.x = Math.abs(this.velocity.x);
        if (enemyHard.position.x >= 400) this.velocity.x = -this.velocity.x;
        enemyHard.position.addUp(this.velocity);
    }
}
