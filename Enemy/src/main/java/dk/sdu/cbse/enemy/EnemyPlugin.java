package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entities enemy;

    private Entities createEnemyShip (GameData gameData) {
        Entities enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        enemyShip.setX(gameData.getDisplayWidth() / 4);
        enemyShip.setY(gameData.getDisplayHeight() / 4);
        enemyShip.setRadius(8);
        enemyShip.setHealthPoint(1);

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
