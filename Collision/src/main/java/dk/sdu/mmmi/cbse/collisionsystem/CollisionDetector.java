package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;                    
                }

                // CollisionDetection
                if (this.collides(entity1, entity2)) {
                    entity1.setHealthPoint(entity1.getHealthPoint() -1);
                    entity2.setHealthPoint(entity2.getHealthPoint() -1);

                    if (entity1.getHealthPoint() <= 0) {
                        world.removeEntity(entity1);
                    }
                    if (entity2.getHealthPoint() <= 0) {
                        world.removeEntity(entity2);
                    }

                }
            }
        }

    }
/*
Implement a simple collision detection system based on Pythagoras and the provided
IPostEntityProcessorService interface.
allerede gjort
 */
    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX(); // (float) da entity1.getX() er en double konverteres det til float
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}
