import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GameCanvas extends JPanel {

    BufferedImage background;
    BufferedImage player;
    Vector<Square> squareVector;
    BufferedImage backBuffered;
    Graphics graphics;

    int positionPlayerX;
    int positionPlayerY;

    int countSquare = 0;

    public GameCanvas() {
        this.setSize(400, 600);
        this.setVisible(true);

        this.backBuffered = new BufferedImage(400, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
        //Load Images
        try {
            this.background = ImageIO.read(new File("resources/background/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.player = ImageIO.read(new File("resources/player/straight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.squareVector = new Vector<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Draw image
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void runAll() {
        if (this.countSquare >= 30) {
            try {
                Square square = new Square(ImageIO.read(new File("resources/square/enemy_square_small.png")), 20, 0, 0, 4);
                this.squareVector.add(square);
                this.countSquare = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.countSquare += 1;
        }

        this.squareVector.forEach(square -> square.run());
    }

    public void renderAll() {
        this.graphics.drawImage(this.background, 0, 0, null);
        this.graphics.drawImage(this.player, this.positionPlayerX, this.positionPlayerY, null);
        this.squareVector.forEach(square -> square.render(graphics));
        this.repaint();
    }
}
