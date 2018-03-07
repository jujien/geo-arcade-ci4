import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Enemy {
    public BufferedImage image;
    public int x;
    public int y;
    public int dx;
    public int dy;
    private Vector<BulletEnemy> bulletEnemies;
    private int count = 0;


    public Enemy(BufferedImage image, int x, int y, int dx, int dy) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.bulletEnemies = new Vector<>();
    }

    public void run() {
        this.x += this.dx;
        this.y += this.dy;
        this.shoot();
    }

    private void shoot() {
        if (this.count >= 10) {
            try {
                BulletEnemy bulletEnemy = new BulletEnemy(ImageIO.read(new File("resources/square/enemy_square_bullet.png")), this.x, this.y, 0, 7);
                this.bulletEnemies.add(bulletEnemy);
                this.count = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.count += 1;
        }

        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.run());
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x, this.y, null);
    }
}
