package graphics.HUD;

import game.player.SingleModePlayer;
import graphics.Canvas;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

/**
 *  HUD del giocatore in Singleplayer, mostra anche i cuori del giocatore
 */
public class SinglePlayerHud extends PlayerHud {
    private Image heartImage;
    private static final float HEART_SIZE = 0.05f;
    public SinglePlayerHud(SingleModePlayer player, Canvas canvas) throws SlickException {
        super(player, canvas);
        heartImage= new Image(PathHandler.getInstance().getPath(ResourcePack.SPRITES, Resource.LIFE));
    }

    @Override
    public void render() {
        super.render();
        for (int i=0; i< ((SingleModePlayer)getPlayer()).getHearts(); i++)
           getCanvas().drawImage(heartImage, (i*1.2f+0.5f)*HEART_SIZE, HEART_SIZE/2f, HEART_SIZE, HEART_SIZE  );
    }
}
