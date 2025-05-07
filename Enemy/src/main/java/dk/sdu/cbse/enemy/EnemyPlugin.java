package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entities enemy;

    private Entities createEnemyShip (GameData gameData) {
        Enemy enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5); // trekant første punkt (-5,-5), andet punkt (10,0), tredje punkt (-5,5) = trekant
        enemyShip.setX(gameData.getDisplayWidth() / 4);
        enemyShip.setY(gameData.getDisplayHeight() / 4);
        return enemyShip;
    }

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
