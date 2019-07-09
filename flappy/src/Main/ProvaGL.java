package Main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class ProvaGL extends AppGameContainer {
    public ProvaGL(Game game) throws SlickException {
        super(game);
    }

    public ProvaGL(Game game, int width, int height, boolean fullscreen) throws SlickException {
        super(game, width, height, fullscreen);
        SGL renderer = Renderer.get();



    }
}
