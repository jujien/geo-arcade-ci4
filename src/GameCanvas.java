import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends JPanel {

    BufferedImage backgroud;
    BufferedImage player;
    int positionPlayerX;
    int positionPlayerY;

    public GameCanvas() {
        this.setSize(400, 600);
        this.setVisible(true);

        //Load Images
        try {
            this.backgroud = ImageIO.read(new File("resources/background/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.player = ImageIO.read(new File("resources/player/straight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Draw images
        g.drawImage(this.backgroud, 0, 0, null);
        g.drawImage(this.player, this.positionPlayerX, this.positionPlayerY, null);
    }
}
