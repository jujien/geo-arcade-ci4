package renderer;

import base.Vector2D;

import java.awt.*;

/**
 * Chi biet khai bao render
 * con render theo nao la nhieu vu cua cac class implements interface nay*/
public interface Renderer {
    void render(Graphics graphics, Vector2D position);
}
