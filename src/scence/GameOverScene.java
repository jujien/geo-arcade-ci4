package scence;

import base.GameObjectManager;
import game.background.Background;

public class GameOverScene implements Scene {
    @Override
    public void init() {
        this.setupBackground();
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
    }

    private void setupBackground() {
        Background background = new Background();
        background.position.set(200, 300);
        GameObjectManager.instance.add(background);
    }
}
