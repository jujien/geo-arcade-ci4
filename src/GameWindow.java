import constant.GeoArcade;
import input.MouseMotionInput;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {

    GameCanvas gameCanvas;
    private long lastTime;
    public String name;

    public GameWindow() {
        this.setup();
        this.setupCanvas();
        this.listener();
        this.setVisible(true);
    }

    private void setup() {
        this.setSize(GeoArcade.Window.WIDTH, GeoArcade.Window.HEIGHT);
    }

    private void setupCanvas() {
        this.gameCanvas = new GameCanvas();
        this.add(this.gameCanvas);
    }

    private void listener() {
        this.mouseMotionListener();
        this.windowListener();
    }

    private void mouseMotionListener() {
        this.addMouseMotionListener(MouseMotionInput.instance);
    }

    private void windowListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    public void gameLoop() {
        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - this.lastTime >= GeoArcade.Window.DELAY_TIME) {
                this.gameCanvas.runAll();
                this.gameCanvas.renderAll();
                this.lastTime = currentTime;
            }
        }
    }
}
