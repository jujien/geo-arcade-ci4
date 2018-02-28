import javax.swing.*;

public class GameWindow extends JFrame {

    GameCanvas gameCanvas;

    public GameWindow() {
        this.setSize(600, 800);
        this.setVisible(true);
        this.gameCanvas = new GameCanvas();
        this.add(this.gameCanvas);
    }
}
