package base;

import action.Action;
import renderer.Renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GameObject {
    public Renderer renderer;
    public Vector2D position;
    public boolean isAlive;
    public List<AttributeObject> attributeObjects;
    public List<Action> actions;

    public GameObject() {
        this.position = new Vector2D();
        this.isAlive = true;
        this.actions = new ArrayList<>();
        this.attributeObjects = new ArrayList<>();
    }


    public void run() {
        this.actions.removeIf(action -> action.run(this));
        this.attributeObjects.forEach(attributeObject -> attributeObject.run(this));
    }

    public void add(Action action) {
        this.actions.add(action);
    }

    public void add(AttributeObject attributeObject) {
        this.attributeObjects.add(attributeObject);
    }

    public <T extends GameObject, A extends AttributeObject<T>> A getAttribute(Class<A> cls) {
        return (A) this.attributeObjects
                .stream()
                .filter(attributeObject -> cls.isInstance(attributeObject))
                .findFirst()
                .orElse(null);
    }

    public void render(Graphics graphics) {
        if (this.renderer != null) {
            this.renderer.render(graphics, this.position);
        }
    }

}
