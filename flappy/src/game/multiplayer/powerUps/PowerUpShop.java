package game.multiplayer.powerUps;

import game.player.MultiModePlayer;

public class PowerUpShop {

    public static PowerUp buy(PowerUpType powerUpType, MultiModePlayer player){
        PowerUp powerUp= powerUpType.create();
        int balance = player.getCoins();
        if(powerUp.getPrice() <= balance){
            player.setCoins(balance - powerUp.getPrice());
            return powerUp;
        } else
            return null;
    }
}
