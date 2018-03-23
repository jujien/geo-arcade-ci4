package game.player.bullet;

import base.GameObjectManager;
import game.player.Player;
import utils.AudioUtils;

import javax.sound.sampled.Clip;

public class PlayerShoot {

    private int count = 0;
    private Clip clip;

    public PlayerShoot() {
        this.clip = AudioUtils.instance.loadSound("resources/sound/sfx/player_shoot.wav"); //load nhac
    }

    public void run(Player player) {
        if (this.count >= 30) {
            Bullet bullet = GameObjectManager.instance.recycle(Bullet.class); //tai sd nhung con bullet da chet hoac la sinh moi
            bullet.position.set(player.position);
            bullet.velocity.set(0, -4);
            this.clip.loop(1); // yeu cau dau vao la lap bao nhieu lan
            this.clip.start();
            this.count = 0;
        } else {
            this.count += 1;
        }
    }
}
