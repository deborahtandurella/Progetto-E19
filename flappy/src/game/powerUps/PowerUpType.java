package game.powerUps;

enum PowerUpType {
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
    private PowerUp create() {
        return null;
    }
    public static PowerUp newInstance(PowerUpType powerUpType) {
        return powerUpType.create();
    }
}

