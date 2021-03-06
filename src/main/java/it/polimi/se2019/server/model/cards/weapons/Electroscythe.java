package it.polimi.se2019.server.model.cards.weapons;

import it.polimi.se2019.server.controller.WeaponShot;
import it.polimi.se2019.server.model.cards.Shot;
import it.polimi.se2019.server.model.game.Cubes;
import it.polimi.se2019.server.model.player.Player;

public class Electroscythe extends AbstractWeapon {
    public void applyEffect(WeaponShot weaponShot){
        if(weaponShot.getNameEffect().equals("Optional1")){
            for(Player target : weaponShot.getDamagingPlayer().getMatch().getAllPlayers()){
                if(target.getPosition() == weaponShot.getDamagingPlayer().getPosition()  &&
                        !target.equals(weaponShot.getDamagingPlayer()))
                    target.getPlayerBoard().dealDamage(weaponShot.getDamagingPlayer(),1);
            }
        }
        else if(weaponShot.getNameEffect().equals("Optional2")){
            for(Player target : weaponShot.getDamagingPlayer().getMatch().getAllPlayers()) {
                if (target.getPosition() == weaponShot.getDamagingPlayer().getPosition() &&
                        !target.equals(weaponShot.getDamagingPlayer()))
                    target.getPlayerBoard().dealDamage(weaponShot.getDamagingPlayer(), 2);
            }
        }
        this.setLoad(false);
    }
    public  Electroscythe(Weapon weapon){
        this.type = weapon.getType();
        this.load = weapon.getLoad();
        this.buyingCost = new Cubes(weapon.getBuyingCost().getReds(), weapon.getBuyingCost().getYellows(), weapon.getBuyingCost().getBlues());
        this.reloadCost = new Cubes(weapon.getReloadCost().getReds(), weapon.getReloadCost().getYellows(), weapon.getReloadCost().getBlues());
        int length = weapon.getEffect().length;
        this.effect = new Shot[length];
        for(int i = 0; i < length; i++)
            this.effect[i] = new Shot(weapon.getEffect()[i]);
    }
}
