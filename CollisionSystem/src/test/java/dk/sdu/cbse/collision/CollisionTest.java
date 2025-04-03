package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entities;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {

    @Test
    void testIsColliding_whenEntitiesTouch() {
        Collision collision = new Collision();

        Entities entity1 = new Entities();
        entity1.setCollisionRadius(10);
        entity1.setX(100);
        entity1.setY(100);

        Entities entity2 = new Entities();
        entity2.setCollisionRadius(10);
        entity2.setX(100);
        entity2.setY(110);

        assertTrue(collision.isColliding(entity1, entity2), "Entities should just be colliding");

    }

    @Test
    void testIsCollidingFalse () {
        Collision collision = new Collision();

        Entities entity1 = new Entities();
        entity1.setCollisionRadius(10);
        entity1.setX(100);
        entity1.setY(100);

        Entities entity2 = new Entities();
        entity2.setCollisionRadius(10);
        entity2.setX(100);
        entity2.setY(140);

        assertFalse(collision.isColliding(entity1, entity2), "Entities should not be colliding");
    }


}
