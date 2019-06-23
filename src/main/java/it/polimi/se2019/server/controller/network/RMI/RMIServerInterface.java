package it.polimi.se2019.server.controller.network.RMI;

import it.polimi.se2019.client.controller.GUIControllerInterface;
import it.polimi.se2019.server.controller.InfoShot;
import it.polimi.se2019.server.controller.PowerUpShot;
import it.polimi.se2019.server.model.cards.Ammo;
import it.polimi.se2019.server.model.cards.weapons.Weapon;
import it.polimi.se2019.server.model.game.Match;
import it.polimi.se2019.server.model.map.Square;
import it.polimi.se2019.server.model.player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//ritorno
public interface RMIServerInterface extends Remote {


    boolean checkUsername(String username) throws Exception;

    void register(String username, GUIControllerInterface guiController,int mapId) throws RemoteException;

    ArrayList<Square> reachableSquares(int position, int steps) throws RemoteException;

    Square[] getAllSquares() throws  RemoteException;

    void pickUpAmmo(String username) throws RemoteException;

    Ammo showLastAmmo() throws  RemoteException;

    void setNewPosition(String username, int newPosition) throws RemoteException;

    boolean isSpawnPoint(int position) throws RemoteException;

    void pickUpWeapon(String username, int indexToPickUp)throws RemoteException;

    void pickUpWeapon(String username, int indexToPickUp, int indexToDrop)throws RemoteException;

    void restoreMap() throws RemoteException;

    Match getMatch() throws RemoteException;

    boolean checkNumberAction(String username) throws RemoteException;

    void giftAction(String username) throws RemoteException;

    void useAction(String username) throws RemoteException;

    void setActivePlayer(String usernameLastPlayer) throws RemoteException;

    String getActivePlayer() throws RemoteException;

    void resetActionNumber(String username) throws RemoteException;

    ArrayList<Player> getKillShotTrack() throws RemoteException;

    ArrayList<InfoShot> verifyWeapons(String username) throws RemoteException;

    void applyEffectWeapon(InfoShot infoShot) throws RemoteException;

    void tradeCube(int index) throws  RemoteException;

    void discardAndSpawn(String username,int index) throws RemoteException;

    ArrayList<Weapon> getReloadableWeapons(String username) throws RemoteException;

    void reloadWeapon(String username, int index) throws RemoteException;

    boolean checkSizeWeapon(String username) throws RemoteException;

    void usePowerUp(String username, int index, PowerUpShot powerUpShot) throws RemoteException;

    boolean deathPlayer() throws RemoteException;

    void respawnPlayer() throws RemoteException;

    void notifyAllClientMovement(String username, Integer newPosition) throws RemoteException;

    void payCubes(String username,int reds,int yellows,int blues) throws RemoteException;
}