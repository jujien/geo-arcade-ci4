package game.player.bullet;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExplosionBullet extends GameObject {

    private Random random;
    private List<ParticleBullet> particleBullets;

    public ExplosionBullet() {
        this.random = new Random();
        this.particleBullets = new ArrayList<>();
    }

    public void create(Vector2D position) {
        for (double angle = 0.0; angle <= 360; angle += 21) {
            ParticleBullet particleBullet = GameObjectManager.instance.recycle(ParticleBullet.class);
            particleBullet.position.set(position);
            particleBullet.velocity.set((new Vector2D(0, 1)).rotate(angle)).multiply(random.nextFloat() * 2);
            particleBullet.frameCounter.setMax(this.random.nextInt(15) + 5);
            this.particleBullets.add(particleBullet);
        }
    }

    @Override
    public void run() {
        super.run();
        this.particleBullets.removeIf(particleBullet -> !particleBullet.isAlive);
        if (this.particleBullets.isEmpty()) {
            this.isAlive = false;
        }
    }
}
