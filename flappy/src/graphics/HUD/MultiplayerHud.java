package graphics.HUD;

import graphics.Canvas;
import logic.player.MultiModePlayer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;

public class MultiplayerHud extends AbstractHud {
    private Image coinImage;
    private static float COIN_SIZE = 0.65f;
    public MultiplayerHud(MultiModePlayer player, Canvas canvas) throws SlickException {
        super(player, canvas);
        coinImage= new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES, PathKeys.COIN));
    }

    @Override
    public void render() {
        super.render();
        getCanvas().drawImage(coinImage, COIN_SIZE, COIN_SIZE, COIN_SIZE, COIN_SIZE );
        getCanvas().drawString( String.valueOf(((MultiModePlayer)getPlayer()).getCoins()),
                getFont(),
                COIN_SIZE*4, COIN_SIZE*1.5f);
    }
}
