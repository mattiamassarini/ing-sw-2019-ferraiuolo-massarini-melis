package it.polimi.se2019.server.model.player;

import it.polimi.se2019.server.controller.Controller;
import it.polimi.se2019.server.model.cards.Ammo;
import it.polimi.se2019.server.model.cards.PowerUp;
import it.polimi.se2019.server.model.game.Cubes;
import it.polimi.se2019.server.model.game.Match;
import it.polimi.se2019.server.model.map.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {
    private Controller controller;
    private String clientName;
    private String color;
    private int position;
    private int score;
    private boolean firstPlayer;
    private boolean suspended;
    private Hand playerHand;
    private PlayerBoard playerBoard;
    private Match match;

    public Player(String clientName, String color, Match match) {
        this.clientName = clientName;
        this.color = color;
        this.match = match;
        this.suspended = false;
        this.score = 0;
        this.firstPlayer = false;
        this.playerBoard = new PlayerBoard(this);
        this.playerHand = new Hand();
    }

    //return player name
    public String getClientName(){
        return this.clientName;
    }

    public void setClientName(String clientName){
        this.clientName= clientName;
    }

    //return color figure,
    public String getColor(){
        return this.color;
    }

    //return player score
    //N.B. each score can be seen only by it's owner
    public int getScore(){
        return this.score;
    }

    public int getPosition(){
        return this.position;
    }

    public Hand getHand(){
        return this.playerHand;
    }

    public PlayerBoard getPlayerBoard(){
        return this.playerBoard;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public void pickUpAmmo(Square currentSquare, Match currentMatch) {
        //Check if the player is in an ammo square
        if(!(currentSquare.isSpawnPoint())) {
            //check if there is an ammo in the current square
            if(currentSquare.isFull()) {
                //Set the square to empty
                currentSquare.setFull(false);
                //Create an ammo object where i put the ammo I've picked up
                Ammo currentAmmo = currentMatch.pickUpAmmoStack();
                //Add the current ammo into the discardedAmmos list
                currentMatch.discardAmmo(currentAmmo);
                //If the ammo I've picked up has a power up -> pick a power up card from ArrayList
                if(currentAmmo.getPowerUpCard()) {
                    //Add a power up to player's hand
                    playerHand.addPowerUp(currentMatch.pickUpPowerUp());
                }
                //Create an object cubes that contains the cubes in the ammo that I've picked up
                Cubes currentCubes = currentAmmo.getAmmoCubes();
                //Change the value of the cubes I own
                playerBoard.setAmmoCubes(currentCubes);
            }
        }
        else{
            System.out.println("You are in a spawn point, you can't pick up an ammo");
        }
    }

    //trade the current powerup in a cube of the matching color as a Cubes object
    public void tradeCube(){
        int index = this.playerHand.indexToDiscard();
        PowerUp powerUp = this.playerHand.chooseToDiscard(index);
        Cubes cubeObtained;
        switch(powerUp.getColor()) {
            case "red": {
                cubeObtained = new Cubes(1, 0, 0);
                //powerup discarded goes into discardedPowerUps
                this.match.getDiscardedPowerUps().add(powerUp);
                break;
            }
            case "blue": {
                cubeObtained = new Cubes(1, 0, 1);
                //powerup discarded goes into discardedPowerUps
                this.match.getDiscardedPowerUps().add(powerUp);
                break;

            }
            case "yellow": {
                cubeObtained = new Cubes(0, 1, 0);
                //powerup discarded goes into discardedPowerUps
                this.match.getDiscardedPowerUps().add(powerUp);
                break;
            }
            default: {
                //if an error occurs, no cubes are returned and the powerup cards goes back
                //in playerHand
                cubeObtained = null;
                this.playerHand.addPowerUp(powerUp);
                break;
            }
        }
        //the cube is added to ammoCubes field in playerBoard
        this.playerBoard.setAmmoCubes(cubeObtained);
    }

    //Return true if the amount of damage is 11 or 12, false otherwise
    public boolean checkDeath() {
        if (this.playerBoard.getDamage().size() == 11 || this.playerBoard.getDamage().size() == 12) {
            return true;
        }
        else {
            return false;
        }
    }

    //Sort the enemyDamages ArrayList by damage amount in descendending order


    public void addPoints(int pointsGained){
        this.score = this.score + pointsGained;
    }
    public String toString(){
        return (this.clientName + " " + this.color + " " + this.getPosition());
    }

    public Match getMatch() {
        return match;
    }
//    public void usePowerUp(PowerUp powerUpToUse) {
//        if (powerUpToUse.getType().equals("targeting scope")) {
//            Player targetingPlayer = controller.chooseTargetingPlayer();
//            powerUpToUse.effect(this, targetingPlayer);
//        }
//        if (powerUpToUse.getType().equals("newton")) {
//        }
//        if (powerUpToUse.getType().equals("tagback grenade")) {
//
//        }
//        if (powerUpToUse.getType().equals("teleporter")) {
//            int newPosition = controller.chooseNewPosition();
//            powerUpToUse.effect(this , newPosition);
//        }
//    }
}