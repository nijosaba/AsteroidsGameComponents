import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module CollisionSystem {
    requires Common;
    provides IPostEntityProcessingService with dk.sdu.cbse.collision.Collision;
}