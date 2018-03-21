package game.square.effect;

import base.FrameCounter;
import base.GameObject;
import base.Vector2D;
import renderer.ImageRenderer;

import java.util.Random;

/** Mo ta hat nho
 * Hinh anh
 * thoi gian song
 * di chuyen*/
public class ParticleSquare extends GameObject {

    private String[] urls;
    private Random random;
    public Vector2D velocity;
    public FrameCounter frameCounter;

    public ParticleSquare() {
        this.random = new Random();
        this.urls = new String[] {
                "resources/square/explosion/enemy_square_explosion_particle_1.png",
                "resources/square/explosion/enemy_square_explosion_particle_2.png",
                "resources/square/explosion/enemy_square_explosion_particle_3.png",
                "resources/square/explosion/enemy_square_explosion_particle_4.png",
                "resources/square/explosion/enemy_square_explosion_particle_5.png"
        };

        this.renderer = new ImageRenderer(this.urls[this.random.nextInt(this.urls.length)]);
        this.velocity = new Vector2D();
        this.frameCounter = new FrameCounter(this.random.nextInt(40) + 30);
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
