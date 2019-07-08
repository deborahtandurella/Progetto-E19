package Main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;

public class ProvaGL extends AppGameContainer {
    public ProvaGL(Game game) throws SlickException {
        super(game);
    }

    public ProvaGL(Game game, int width, int height, boolean fullscreen) throws SlickException {
        super(game, width, height, fullscreen);
        SGL renderer = Renderer.get();



    }
}
