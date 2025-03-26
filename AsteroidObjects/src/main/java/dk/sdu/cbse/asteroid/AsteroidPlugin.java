package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private Random random = new Random();
    private Entities asteroids;

    private Entities createAsteroid(GameData gameData) {
        Entities asteroid = new Asteroid(gameData);
        asteroid.setPolygonCoordinates(-15, -15, 15, -15, 15, 15, -15, 15); //første punkt (-10,-10), andet punkt (10,-10), tredje punkt (10,10), fjerde punkt (-10,10) = firkant
        asteroid.setX(random.nextInt(gameData.getDisplayWidth()));
        asteroid.setY(random.nextInt(gameData.getDisplayHeight()));
        asteroid.setHealthPoint(1);

        return asteroid;
    }



    @Override
    public void start(GameData gameData, World world) {
        Entities tempAsteroid = new Asteroid(gameData);
        int spawnCount = tempAsteroid.getSpawnCount();
        for (int i = 0; i < spawnCount; i++) {
            asteroids = createAsteroid(gameData);
            world.addEntity(asteroids);
        }

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroids);

    }
}
