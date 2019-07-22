package test;

import flappyEntities.logic.Hitbox;
import org.junit.jupiter.api.Test;
import org.newdawn.slick.geom.Rectangle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HitboxTest {
    @Test
    void collisionDidntHappenTest(){
        Hitbox hb1= new Hitbox();
        hb1.addShape(new Rectangle(0, 0, 1, 1));
        hb1.addShape(new Rectangle( 3, 3, 1, 1 ));
        Hitbox hb2= new Hitbox();
        hb2.addShape(new Rectangle(2, 2, 0.5f, 0.5f));
        assertFalse(hb1.collides(hb2));
        assertFalse(hb2.collides(hb1));

    }

    @Test
    void collisionHappenedTest(){
        Hitbox hb1= new Hitbox();
        hb1.addShape(new Rectangle(0, 0, 1, 1));
        hb1.addShape(new Rectangle( 3, 3, 1, 1 ));
        Hitbox hb2= new Hitbox();
        hb2.addShape(new Rectangle(2, 2, 2, 2));
        assertTrue(hb1.collides(hb2));
        assertTrue(hb2.collides(hb1));
    }
}
