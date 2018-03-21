package game.square.effect;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExplosionSquare extends GameObject {

    private Random random;
    private List<ParticleSquare> particleSquares;

    public ExplosionSquare() {
        this.random = new Random();
        this.particleSquares = new ArrayList<>();
    }

    public void create(Vector2D position) {
        for (double angle = 0.0; angle <= 360.0; angle += 360.0 / 37) {
            ParticleSquare particleSquare = GameObjectManager.instance.recycle(ParticleSquare.class);
            particleSquare.position.set(position);
            Vector2D vector2D = new Vector2D(0, 1);
            Vector2D rotate = vector2D.rotate(angle);
            particleSquare.velocity.set(rotate).multiply(this.random.nextFloat() * 3);
            particleSquare.frameCounter.setMax(this.random.nextInt(20) + 5);
            this.particleSquares.add(particleSquare);
        }
    }

    @Override
    public void run() {
        super.run();
        //chay qua tat ca phan tu, neu phan tu nao thoa man dk truyen vao thi xoa
        this.particleSquares.removeIf(particleSquare -> !particleSquare.isAlive);
        if (this.particleSquares.isEmpty()) {
            this.isAlive = false;
        }
    }
}
