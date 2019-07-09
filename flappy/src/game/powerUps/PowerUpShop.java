package game.powerUps;

import logic.player.MultiModePlayer;

public class PowerUpShop {
    public static PowerUp buy(PowerUpType powerUpType, MultiModePlayer player){
        PowerUp pu= PowerUpType.newInstance(powerUpType);
        int balance = player.getCoins();
        if(pu.getPrice() < balance){
            player.setCoins(balance - pu.getPrice());
            return pu;
        } else {
            return null;
        }
    }
}
