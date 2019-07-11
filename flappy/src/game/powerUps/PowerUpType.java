package game.powerUps;

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
    public abstract PowerUp create();
}

