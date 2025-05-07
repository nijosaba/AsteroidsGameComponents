package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CollisionUnitTest {

    //unit test, tester om collision virker
    private Collision collision;
    private Entities entity1;
    private Entities entity2;

    @BeforeEach
    void setUp() {
        collision = new Collision();

        entity1 = new Entities();
        entity1.setCollisionRadius(10);
        entity1.setX(100);
        entity1.setY(100);

        entity2 = new Entities();
        entity2.setCollisionRadius(10);
    }

    @Test
    void testIsColliding_whenEntitiesTouch() {
        entity2.setX(100);
        entity2.setY(110);

        assertTrue(collision.isColliding(entity1, entity2), "Entities should just be colliding");
    }

    @Test
    void testIsCollidingFalse() {
        entity2.setX(100);
        entity2.setY(140);

        assertFalse(collision.isColliding(entity1, entity2), "Entities should not be colliding");
    }


}
