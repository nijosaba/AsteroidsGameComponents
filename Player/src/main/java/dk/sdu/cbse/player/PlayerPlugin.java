package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entities player;


    private Entities createPlayerShip(GameData gameData) {
        Player playerShip = new Player();
        playerShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        playerShip.setX(gameData.getDisplayWidth() / 4);
        playerShip.setY(gameData.getDisplayHeight() / 4);
        playerShip.setRadius(8);

        playerShip.setHealthPoint(3);

        return playerShip; //return pga vi skal bruge playerShip i start metoden med setters værdier.

    }

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayerShip(gameData);
        world.addEntity(player);
        System.out.println("player creaated" + player.getX() + " " + player.getY());
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

}
