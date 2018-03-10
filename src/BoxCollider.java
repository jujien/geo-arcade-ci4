public class BoxCollider {
    public Vector2D position;
    private float width;
    private float height;

    public BoxCollider(float width, float height) {
        this.position = new Vector2D();
        this.width = width;
        this.height = height;
    }
}
