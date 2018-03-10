public class Bullet extends GameObject {

    public Vector2D velocity;
    private BoxCollider boxCollider;

    public Bullet() {
        this.image = Utils.loadImage("resources/player/player_bullet.png");
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(10, 10);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
    }
}
