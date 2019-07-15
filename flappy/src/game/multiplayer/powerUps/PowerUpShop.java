package game.multiplayer.powerUps;

import game.player.MultiModePlayer;

public class PowerUpShop {

    /**
     * @param powerUpType il PowerUp che si desidera acquistare
     * @param player il giocatore che desidera che acquistare il PowerUp
     * @return il PowerUp acquistato se il giocatore ha abbastanza monete, null nel caso contrario
     */
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
