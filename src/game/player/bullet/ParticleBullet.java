package game.player.bullet;

import base.FrameCounter;
import base.GameObject;
import base.Vector2D;
import renderer.ImageRenderer;

public class ParticleBullet extends GameObject {

    public Vector2D velocity;
    public FrameCounter frameCounter;

    public ParticleBullet() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/player/player_bullet_explosion.png");
        this.frameCounter = new FrameCounter(10);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        if (this.frameCounter.run()) {
            this.isAlive = false;
            this.frameCounter.reset();
        }
    }
}
