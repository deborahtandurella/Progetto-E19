package test;

import flappyEntities.logic.bird.BirdLogicComponent;
import flappyEntities.logic.obstacles.pipes.PipeLogicComponent;
import flappyEntities.logic.obstacles.rocket.RocketLogicComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolidGameElementTest {
    @Test
     void testStaticCollisionDidntHappen(){
        BirdLogicComponent bird = new BirdLogicComponent(0, 0.5);
        PipeLogicComponent pipe= new PipeLogicComponent(0, 0.5); //centery è il centro del doppio tubo
        assertFalse(bird.collide(pipe));
    }
    @Test
     void testStaticCollisionHappened(){
        BirdLogicComponent bird = new BirdLogicComponent(0, 0.8);
        RocketLogicComponent rocket = new RocketLogicComponent(0, 0.8);
        assertTrue(bird.collide(rocket));
    }

    @Test
     void testDinamicCollisionHappened(){
        boolean collisionHappened=false;
        BirdLogicComponent bird = new BirdLogicComponent(0, 0.5);
        PipeLogicComponent pipe = new PipeLogicComponent(1, 0.5);
        for (int i = 0; i <2000; i++){
            bird.update(1);
            pipe.update(1);
            if (bird.collide(pipe)){
                collisionHappened = true;
            }
        }
        assertTrue(collisionHappened);
    }

    @Test
     void testDinamicCollisionDidntHappen(){
        boolean collisionHappened = false;
        BirdLogicComponent bird = new BirdLogicComponent(0, 0.5);
        PipeLogicComponent pipe = new PipeLogicComponent(1, 0.5);
        for (int i = 0; i <2000; i++){
            pipe.update(1);
            if (bird.collide(pipe)){
                collisionHappened= true;
            }
        }
        assertFalse(collisionHappened);
    }

}
