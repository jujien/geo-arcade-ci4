package game.player.bullet;

import base.GameObjectManager;
import game.player.Player;

public class PlayerShoot {

    private int count = 0;

    public void run(Player player) {
        if (this.count >= 30) {
            Bullet bullet = GameObjectManager.instance.recycle(Bullet.class); //tai sd nhung con bullet da chet hoac la sinh moi
            bullet.position.set(player.position);
            bullet.velocity.set(0, -4);
            this.count = 0;
        } else {
            this.count += 1;
        }
    }
}
