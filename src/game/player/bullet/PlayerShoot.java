package game.player.bullet;

import base.FrameCounter;
import base.GameObjectManager;
import game.player.Player;

public class PlayerShoot {

    private FrameCounter frameCounter = new FrameCounter(20);

    public void run(Player player) {
        if (this.frameCounter.run()) {
            Bullet bullet = GameObjectManager.instance.recycle(Bullet.class); //tai sd nhung con bullet da chet hoac la sinh moi
            bullet.position.set(player.position);
            bullet.velocity.set(0, -4);
            this.frameCounter.reset();
        }
    }
}
