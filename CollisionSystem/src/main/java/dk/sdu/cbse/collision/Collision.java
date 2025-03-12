package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class Collision implements IPostEntityProcessingService {

    public Collision() {

    }

    //phytagoras
    private boolean isColliding(Entities entity1, Entities entity2) {
        double dx = entity1.getX() - entity2.getX();
        double dy = entity1.getY() - entity2.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entities entity1 : world.getEntities()) {
            for (Entities entity2 : world.getEntities()) {

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                // CollisionDetection
                if (this.isColliding(entity1, entity2)) {
                    entity1.setHealthPoint(entity1.getHealthPoint() - 1);
                    entity2.setHealthPoint(entity2.getHealthPoint() - 1);

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
}
