package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class Collision implements IPostEntityProcessingService {

    public Collision() {

    }

    //phytagoras mere i txtFilesMappe
    protected boolean isColliding(Entities entity1, Entities entity2) {
        double dx = entity1.getX() - entity2.getX(); //dx = difference in x mellem de 2 entities
        double dy = entity1.getY() - entity2.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= (entity1.getCollisionRadius() + entity2.getCollisionRadius());
    }

    @Override
    public void process(GameData gameData, World world) {
        // Opret lister til at holde entities der skal fjernes og asteroider der skal splittes
        List<Entities> entitiesToRemove = new ArrayList<>();
        List<Entities> asteroidsToSplit = new ArrayList<>();

        detectCollisions(world, entitiesToRemove, asteroidsToSplit);
        processAsteroidSplitting(asteroidsToSplit, world, entitiesToRemove);
        removeEntities(entitiesToRemove, world);
    }

    private void detectCollisions(World world, List<Entities> entitiesToRemove, List<Entities> asteroidsToSplit) {
        for (Entities entity1 : world.getEntities()) {
            for (Entities entity2 : world.getEntities()) {
                // Spring over hvis samme entity
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                // Tjek for kollision
                if (this.isColliding(entity1, entity2)) {
                    entity1.setHealthPoint(entity1.getHealthPoint() - 1);
                    entity2.setHealthPoint(entity2.getHealthPoint() - 1);

                    // Marker døde entities til fjernelse
                    processEntityHealth(entity1, entitiesToRemove, asteroidsToSplit);
                    processEntityHealth(entity2, entitiesToRemove, asteroidsToSplit);
                }
            }
        }
    }


    private void processEntityHealth(Entities entity, List<Entities> entitiesToRemove, List<Entities> asteroidsToSplit) {
        if (entity.getHealthPoint() <= 0) {
            if (!entitiesToRemove.contains(entity)) {
                if (entity.getClass().getSimpleName().equals("Asteroid") &&
                        !isAsteroidSplit(entity)) {
                    asteroidsToSplit.add(entity);
                } else {
                    entitiesToRemove.add(entity);
                }
            }
        }
    }


    private void processAsteroidSplitting(List<Entities> asteroidsToSplit, World world, List<Entities> entitiesToRemove) {
        for (Entities asteroid : asteroidsToSplit) {
            ServiceLoader<IAsteroidSplitter> loader = ServiceLoader.load(IAsteroidSplitter.class);
            for (IAsteroidSplitter splitter : loader) {
                splitter.createSplitAsteroid(asteroid, world);
                break; // Brug kun den første
            }
            entitiesToRemove.add(asteroid);
        }
    }

    private void removeEntities(List<Entities> entitiesToRemove, World world) {
        for (Entities entity : entitiesToRemove) {
            world.removeEntity(entity);
        }
    }


    private boolean isAsteroidSplit(Entities asteroid) {
        try {
            Method isSplitMethod = asteroid.getClass().getMethod("isSplit");
            return (boolean) isSplitMethod.invoke(asteroid);
        } catch (Exception e) {
            return false;
        }


    }
}

