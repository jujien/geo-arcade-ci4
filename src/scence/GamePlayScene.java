package scence;

import base.GameObjectManager;
import game.background.Background;
import game.enemy.EnemySqawner;
import game.enemyhard.EnemyHard;
import game.player.Player;
import game.square.SquareSpawner;
import game.square.circle.CircleSquare;
import game.square.matrix.MatrixSquare;
import utils.AudioUtils;

import javax.sound.sampled.Clip;

public class GamePlayScene implements Scene {

    private Clip clip;

    @Override
    public void init() {
        this.clip = AudioUtils.instance.loadSound("resources/sound/bgm/bgmwav.wav");
        this.clip.loop(-1);
        this.setupBackground();
        this.setupPlayer();
        this.setupSquare();
        this.setCircleSquare();
        this.setupMatrix();
        this.setupEnemyHard();
    }

    @Override
    public void deinit() {
        this.clip.stop();
        GameObjectManager.instance.clear();
    }

    private void setupMatrix() {
        MatrixSquare matrixSquare = new MatrixSquare();
        matrixSquare.position.set(20, 20);
        matrixSquare.velocity.set(3, 0);
        matrixSquare.create();
        GameObjectManager.instance.add(matrixSquare);
    }

    private void setCircleSquare() {
        CircleSquare circleSquare = new CircleSquare();
        circleSquare.position.set(100, 100);
        circleSquare.create();
        GameObjectManager.instance.add(circleSquare);
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

    private void setupEnemyHard() {
        EnemyHard enemyHard = new EnemyHard();
        enemyHard.velocity.set(0, 1);
        enemyHard.position.set(0, 50);
        GameObjectManager.instance.add(enemyHard);
    }

    private void setupSquare() {
        SquareSpawner squareSpawner = GameObjectManager.instance.recycle(SquareSpawner.class);
        squareSpawner.create();
        GameObjectManager.instance.add(new EnemySqawner());
    }
}
