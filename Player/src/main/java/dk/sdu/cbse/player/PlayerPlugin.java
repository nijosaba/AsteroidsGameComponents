package dk.sdu.cbse.player;


import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entities player;

    private Entities createPlayerShip(GameData gameData) {
        Player playerShip = new Player();
        playerShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5); // trekant første punkt (-5,-5), andet punkt (10,0), tredje punkt (-5,5) = trekant
        playerShip.setX(gameData.getDisplayWidth() / 2); //spawnpoint X og Y nedenunder
        playerShip.setY(gameData.getDisplayHeight() / 4);
        playerShip.setHealthPoint(3); //standard entity er hp=1

        return playerShip; //return pga vi skal bruge playerShip i start metoden med brug af setters værdier.

    }

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayerShip(gameData);
        world.addEntity(player);

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

}
