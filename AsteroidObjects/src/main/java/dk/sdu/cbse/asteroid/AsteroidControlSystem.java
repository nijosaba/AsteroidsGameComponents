package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {

    private Random random = new Random();


    @Override
    public void process(GameData gameData, World world) {


        for (Entities entities : world.getEntities(Asteroid.class)) {
            if (entities instanceof Asteroid) {
                Asteroid asteroid = (Asteroid) entities;

                if (asteroid.getRotation() == 0) {
                    asteroid.setRotation(random.nextInt(360));
                }

                //opdatere position
                double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
                double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
                asteroid.setX(asteroid.getX() + changeX * asteroid.getSpeed());
                asteroid.setY(asteroid.getY() + changeY * asteroid.getSpeed());

                // Wrap around screen
                asteroid.wrapAroundScreen(asteroid, gameData);


            }
        }
    }




}
