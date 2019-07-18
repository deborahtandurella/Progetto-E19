package game;

import game.player.Player;

public interface Game {
    void update(int delta);
    void render();
    Player getPlayer();
    void playerJump();
}
