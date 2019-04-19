package graphics;

import logic.gameElements.Bird;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpriteDrawer{
    private Image bird;

    {
        try {
            bird = new Image("res/bird.png");

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static drawBird(Bird bird, Graphics graphics){
        graphics.drawImage(bird.getX(), bird.getY(),);

    }
}
