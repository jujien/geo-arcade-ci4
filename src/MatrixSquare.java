import java.util.Vector;

public class MatrixSquare extends GameObject{

    private Vector<Square> squares = new Vector<>();
    public int dx;
    public int dy;

    public void create() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                Square square = new Square();
                square.x = this.x + j * (20 + 20);
                square.y = this.y + i * (20 + 20);
                square.dx = this.dx;
                square.dy = this.dy;
                this.squares.add(square);
                GameObject.add(square);
            }
        }
    }

    @Override
    public void run() {
        super.run();
        if (this.x > 0 && this.x < 400 - 5 * 20 - 4 * 20) {

        } else if (this.x <= 0) {
            this.dx = Math.abs(this.dx);
        } else {
            this.dx = -this.dx;
        }
        this.x += this.dx;
        this.y += this.dy;
        this.squares.forEach(square -> {
            square.dx = dx;
            square.dy = dy;
        });
    }
}
