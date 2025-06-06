package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionIntegrationTest {

    private Collision collision;
    private World world;
    private GameData gameData;
    private Entities entity1;
    private Entities entity2;

    @BeforeEach
    void setUp() {
        collision = new Collision();
        world = new World();
        gameData = new GameData();

        entity1 = new Entities();
        entity1.setCollisionRadius(10);
        entity1.setX(100);
        entity1.setY(100);
        entity1.setHealthPoint(10);

        entity2 = new Entities();
        entity2.setCollisionRadius(10);
        entity2.setX(100);
        entity2.setY(110);
        entity2.setHealthPoint(10);

        world.addEntity(entity1);
        world.addEntity(entity2);
    }

    @Test
    void testWhenEntitiesCollide() {                                                                        //-1 vil faile pga. når entities rammer noget = -1 og når de bliver ramt = -1 så det = -2, en bug der bør fixes
        collision.process(gameData, world); // processer collision og reducerer health point med 1

        Entities player = world.getEntity(entity1.getID());
        Entities enemy = world.getEntity(entity2.getID());

        assertEquals(8, player.getHealthPoint(), "player should lose 1 health point");
        assertEquals(8, enemy.getHealthPoint(), "enemy should lose 1 health point");
    }

    @Test
    void testWhenEntityHealthReachesZero() {
        entity1.setHealthPoint(1); // fra oprindelig 10 til 1 for at teste at den forsvinder

        collision.process(gameData, world); //reducere entity1's health point til 0 i selve metoden

        Entities player = world.getEntity(entity1.getID());
        Entities enemy = world.getEntity(entity2.getID());

        assertNull(player, "player should be removed from the world");
        assertNotNull(enemy, "enemy should still exist in the world");
    }
}