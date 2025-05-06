import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module CollisionSystem {
    requires Common;
    requires CommonAsteroid;
    provides IPostEntityProcessingService with dk.sdu.cbse.collision.Collision;

    uses dk.sdu.cbse.common.asteroid.IAsteroidSplitter;
}