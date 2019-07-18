package Main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;
import states.FlappyStateGame;
import utilities.DisplayModeManager;

public class Launcher {
    public static void main(String[] argv) {
     /*   System.setProperty("java.library.path", "natives" );
        System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
        try{
            Field fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
            fieldSysPath.setAccessible( true );
            fieldSysPath.set( null, null );
        } catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }*/
        try {
            AppGameContainer container = new AppGameContainer(new FlappyStateGame());
            container.setUpdateOnlyWhenVisible(false);
            container.setSmoothDeltas(false);
            container.setTargetFrameRate(200);
            container.setVSync(false);
            DisplayMode bestMode = DisplayModeManager.getBiggestWithRatio(4, 3, 60);
            container.setDisplayMode(bestMode.getWidth(),bestMode.getHeight(),false);
            PathHandler pathHandler= PathHandler.getInstance();
            container.setIcons(new String[]{pathHandler.getPath(ResourcePack.VARIOUS, Resource.ICON32),pathHandler.getPath(ResourcePack.VARIOUS, Resource.ICON16)});
            container.start();
        } catch (SlickException | LWJGLException e) {
            e.printStackTrace();
        }
    }
}
