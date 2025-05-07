package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.asteroid.IAsteroidSplitter;

import java.util.Random;

public class AsteroidSplitterSystem implements IAsteroidSplitter {

    private Random random = new Random();
    private static double SPLIT_ASTEROID_RADIUS = 8.0;

    private Asteroid createSmallerAsteroid(Entities originalAsteroid) {
        Asteroid newAsteroid = new Asteroid();
        newAsteroid.setPolygonCoordinates(-6.0, -6.0,6.0, -6.0, 6.0,  6.0, -6.0,  6.0);
        newAsteroid.setCollisionRadius(8.0);

        double positionOffset = SPLIT_ASTEROID_RADIUS * 3.5; // leg med radius for splittet asteroider
        double angleOffset = random.nextDouble() * Math.PI * 2;
        newAsteroid.setX(originalAsteroid.getX() + Math.cos(angleOffset) * positionOffset);
        newAsteroid.setY(originalAsteroid.getY() + Math.sin(angleOffset) * positionOffset);
        newAsteroid.setSpeed(originalAsteroid.getSpeed()-0.1); //leg med speed
        newAsteroid.setRotation(random.nextDouble() * 360.0);
        return newAsteroid;
    }


    @Override
    public void createSplitAsteroid(Entities originalAsteroid, World world) {
        Asteroid splitAsteroid1 = createSmallerAsteroid(originalAsteroid);
        Asteroid splitAsteroid2 = createSmallerAsteroid(originalAsteroid);

        world.addEntity(splitAsteroid1);
        world.addEntity(splitAsteroid2);

    }
}