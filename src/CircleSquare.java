/**
 * Created by huynq on 3/15/18.
 */
public class CircleSquare extends GameObject {

    public CircleSquare() {

    }

    public void create() {
        for (double angle = 0.0; angle < 360.0; angle+=360.0/15) {
            Square square = new Square();
            Vector2D vector2D = new Vector2D(0, 80); //vector goc
            Vector2D rotate = vector2D.rotate(angle); //quay vector goc
            square.position.set(rotate).addUp(100, 100); //gan rotate vao position
            GameObject.add(square);
        }
    }
}
