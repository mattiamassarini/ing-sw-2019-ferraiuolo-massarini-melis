package it.polimi.se2019.server.model.cards.powerUp;


import it.polimi.se2019.server.controller.PowerUpShot;
import it.polimi.se2019.server.model.player.Player;

import java.io.Serializable;

public class TargetingScope extends PowerUp implements Serializable {

    public TargetingScope(PowerUp powerUp) {
        super();
        this.type = powerUp.getType();
        this.color = powerUp.getColor();
    }

    public TargetingScope(String type, String color) {
        //Needed for resuming a powerup from saved match
        super();
        this.type = type;
        this.color = color;
    }

    @Override
    public void applyEffect(PowerUpShot powerUpShot) {
        Player targetPlayer = powerUpShot.getTargetingPlayer();
        Player damagingPlayer = powerUpShot.getDamagingPlayer();
        targetPlayer.getPlayerBoard().dealDamage(damagingPlayer, 1);
    }
}
