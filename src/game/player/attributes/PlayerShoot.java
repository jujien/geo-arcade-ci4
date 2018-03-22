package game.player.attributes;

import base.AttributeObject;
import base.FrameCounter;
import base.GameObjectManager;
import game.player.Player;
import game.player.bullet.Bullet;
import game.player.bullet.attributes.BulletMove;

public class PlayerShoot implements AttributeObject<Player> {

    private FrameCounter frameCounter = new FrameCounter(15);

    @Override
    public void run(Player player) {
        if (this.frameCounter.run()) {
            Bullet bullet = GameObjectManager.instance.recycle(Bullet.class);
            bullet.position.set(player.position);
            bullet.getAttribute(BulletMove.class).velocity.set(0, -4);
            this.frameCounter.reset();
        }
    }
}
