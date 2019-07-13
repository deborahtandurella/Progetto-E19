package graphics.HUD;

import graphics.Canvas;
import logic.player.MultiModePlayer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.PathHandler;
import resources.ResourcePacks;
import resources.Resources;

public class MultiplayerHud extends Hud {
    private Image coinImage;
    private static float COIN_SIZE = 0.065f;
    public MultiplayerHud(MultiModePlayer player, Canvas canvas) throws SlickException {
        super(player, canvas);
        coinImage= new Image(PathHandler.getInstance().getPath(ResourcePacks.SPRITES, Resources.COIN));
    }

    @Override
    public void render() {
        super.render();
        getCanvas().drawImage(coinImage, COIN_SIZE*0.5f, COIN_SIZE*0.5f, COIN_SIZE, COIN_SIZE );
        getCanvas().drawString( String.valueOf(((MultiModePlayer)getPlayer()).getCoins()),
                getFont(),
                COIN_SIZE*2, 0.05f);
    }
}
