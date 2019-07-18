package game.multiplayer.powerUps;

/**
 * Tipologie di PowerUp, classe utilizzata anche come Factory per generare i PowerUp
 */
public enum PowerUpType {
    ROCKET {
        public PowerUp create() {
            return new RocketPowerUp();
        }
    },
    SPEED {
        public PowerUp create() {
            return new SpeedPowerUP();
        }
    },
    GRAVITY {
        public PowerUp create() {
            return new GravityPowerUp();
        }
    };

    /**
     * Crea un PowerUp del tipo scelto
     */
    public abstract PowerUp create();
}

