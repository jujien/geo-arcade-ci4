package game.player.attributes;

import base.AttributeObject;
import base.FrameCounter;
import game.player.Player;
import renderer.AnimationRenderer;
import renderer.ImageRenderer;
import renderer.Renderer;

public class PlayerRenderer implements AttributeObject<Player>  {

    private Renderer imageRenderer;
    private Renderer animationRenderer;
    public boolean switchRender = false;
    private FrameCounter frameCounter;

    public PlayerRenderer() {
        this.imageRenderer = new ImageRenderer("resources/player/straight.png");
        this.animationRenderer = new AnimationRenderer(
                5,
                "resources/player/straight.png",
                "resources/player/straight_white.png",
                "resources/player/straight.png",
                "resources/player/straight_white.png",
                "resources/player/straight.png"
        );
        this.frameCounter = new FrameCounter(50);
    }

    @Override
    public void run(Player gameObject) {
        if (!this.switchRender) {
            gameObject.renderer = this.imageRenderer;
        } else {
            if (this.frameCounter.run()) {
                this.switchRender = false;
                gameObject.renderer = this.imageRenderer;
                this.frameCounter.reset();
            }
        }
    }
}
