package game.enemyhard.attributes;

import base.AttributeObject;
import base.Vector2D;
import game.enemyhard.EnemyHard;

public class EnemyHardMove implements AttributeObject<EnemyHard> {

    public Vector2D velocity = new Vector2D();


    @Override
    public void run(EnemyHard gameObject) {
        if (gameObject.position.x <= 0) this.velocity.x = Math.abs(this.velocity.x);
        if (gameObject.position.x >= 400) this.velocity.x = -this.velocity.x;
         gameObject.position.addUp(this.velocity);
    }
}
