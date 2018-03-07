import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class EnemySqawner {
    private Vector<Enemy> enemies;
    private int count = 0;
    private Random random;

    public EnemySqawner() {
        this.enemies = new Vector();
        this.random = new Random();
    }

    public void run() {
        if (this.count >= 40) {
            try {
                Enemy enemy = new Enemy(ImageIO.read(new File("resources/square/enemy_square_medium.png")), this.random.nextInt(400), 0, 0, random.nextInt(3) + 2);
                this.enemies.add(enemy);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.count = 0;
        } else {
            this.count += 1;
        }

        this.enemies.forEach(enemy -> enemy.run());
    }

    public void render(Graphics graphics) {
        this.enemies.forEach(enemy -> enemy.render(graphics));
    }
}
