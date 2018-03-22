import base.GameObjectManager;
import game.background.Background;
import game.enemy.EnemySqawner;
import game.enemyhard.EnemyHard;
import game.player.Player;
import game.square.circle.CircleSquare;
import game.square.matrix.MatrixSquare;
import game.square.SquareSpawner;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

//Co rat nhieu thang su dung den chuot

public class GameCanvas extends JPanel {

    BufferedImage backBuffered;
    Graphics graphics;

    public GameCanvas() {
        this.setup();
        this.setupBackBuffered();
        this.setupBackground();
        this.setupPlayer();
        this.setupSquare();
        this.setupEnemey();
    }

    private void setup() {
        this.setSize(400, 600);
        this.setVisible(true);
    }

    private void setupEnemey() {
        EnemySqawner enemySqawner = GameObjectManager.instance.recycle(EnemySqawner.class);
        enemySqawner.create();
        enemySqawner.createHard();
    }

    private void setupSquare() {
        SquareSpawner squareSpawner = GameObjectManager.instance.recycle(SquareSpawner.class);
        squareSpawner.createNormal();
        squareSpawner.createHard();
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(400, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupPlayer() {
        Player player = GameObjectManager.instance.recycle(Player.class);
        player.position.set(200, 300);
    }

    private void setupBackground() {
        Background background = new Background();
        background.position.set(200, 300);
        GameObjectManager.instance.add(background);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void runAll() {
        GameObjectManager.instance.runAll();
    }

    public void renderAll() {
        GameObjectManager.instance.renderAll(this.graphics);
        this.repaint();
    }
}
