package base;

import game.player.Player;
import game.square.Square;
import physic.BoxCollider;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class GameObject {
    public BufferedImage image;
    public Vector2D position;
    public boolean isAlive;

    private static Vector<GameObject> vector = new Vector<>();
    private static Vector<GameObject> temp = new Vector<>();

    public static void runAll() {
        vector
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.run());
        vector.addAll(temp);
        temp.clear();
    }

    public GameObject() {
        this.position = new Vector2D();
        this.isAlive = true;
    }

    public static void renderAll(Graphics graphics) {
        vector
                .stream()
                .filter(gameObject -> gameObject.isAlive) //loc object dang song
                .forEach(gameObject -> gameObject.render(graphics));
    }

    public static void add(GameObject gameObject) {
        temp.add(gameObject);
    }

    public void run() {

    }

    public void render(Graphics graphics) {
        if (this.image != null) {
            graphics.drawImage(this.image, (int)this.position.x - this.image.getWidth() / 2, (int)this.position.y - this.image.getHeight() / 2, null);
        }
    }

    public static Player getPlayer() {
        return (Player) vector
                .stream()
                .filter(gameObject -> gameObject instanceof Player) //duyet tat ca phan tu sao cho thoa dien kien truyen (thuc chat true || false) vao thi lay
                .findFirst() //phan tu dau tien
                .orElse(null); // neu ko lay dc tra null
    }

    public static Square checkCollsion(BoxCollider other) {
        return (Square) vector
                .stream()
                .filter(gameObject -> gameObject.isAlive) //lay object con song
                .filter(gameObject -> gameObject instanceof Square) //Lay con Square
                .filter(gameObject -> {
                    BoxCollider boxCollider = ((Square) gameObject).boxCollider; //Lay BoxCollider
                    return boxCollider.checkCollider(other); // lay square dang co box chong len nhau
                })
                .findFirst()
                .orElse(null);
    }
}
