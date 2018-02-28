import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends JPanel {

    BufferedImage backgroud;

    public GameCanvas() {
        this.setSize(600, 800);
        this.setVisible(true);

        //Load Images
        try {
            this.backgroud = ImageIO.read(new File("resources/background/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Draw images
        g.drawImage(this.backgroud, 0, 0, null);
    }
}
