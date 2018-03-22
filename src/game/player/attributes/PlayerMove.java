package game.player.attributes;

import base.AttributeObject;
import game.player.Player;
import input.MouseMotionInput;

public class PlayerMove implements AttributeObject<Player> {

    @Override
    public void run(Player gameObject) {
        gameObject.position.set(MouseMotionInput.instance.position);
        if (gameObject.position.x < 0) gameObject.position.x = 0;
        if (gameObject.position.x > 400) gameObject.position.x = 400 - 40;
        if (gameObject.position.y < 0) gameObject.position.y = 0;
        if (gameObject.position.y > 600) gameObject.position.y = 600 - 40;
    }
}
