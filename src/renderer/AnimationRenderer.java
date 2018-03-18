package renderer;

import base.FrameCounter;
import base.Vector2D;
import utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Se co hai loai Animtion:
 * - Animation chay mot lan. tuc la chi ve lien tiep cac buc anh mot lan duy nhat
 * - Animation chay vo han. tuc la sau khi ve het cac buc anh thi lap lai*/
public class AnimationRenderer implements Renderer {

    private List<BufferedImage> images;
    private boolean isRepeat; // neu true: animation chay vo han, false chi mot lan
    private FrameCounter frameCounter; // khoang delay giua hai lan buc anh.
    private int currentIndex; //dang ve o buc anh thu may trong list image
    private boolean disable = false;

    public AnimationRenderer(boolean isRepeat, int max, String... paths) {
        this.isRepeat = isRepeat;
        this.frameCounter = new FrameCounter(max);
        this.images = new ArrayList<>();
        Arrays.asList(paths).forEach(path -> images.add(Utils.loadImage(path)));
        this.currentIndex = 0;
    }

    public AnimationRenderer(int max, String... paths) { //animation chay vo han
        this(true, max, paths); //goi constructor phia tren
    }

    @Override
    public void render(Graphics graphics, Vector2D position) {
        if (this.disable) return;
        BufferedImage bufferedImage = this.images.get(this.currentIndex);
        if (bufferedImage != null) {
            graphics.drawImage(bufferedImage, (int)position.x - bufferedImage.getWidth() / 2, (int)position.y - bufferedImage.getHeight() / 2, null);
        }
        if (this.currentIndex == this.images.size() - 1 && !this.isRepeat) {
            this.disable = true;
        } else {
            if (this.frameCounter.run()) {
                if (this.currentIndex >= this.images.size() - 1) {
                    this.currentIndex = 0;
                } else {
                    this.currentIndex += 1;
                }
                this.frameCounter.reset();
            }
        }
    }
}
