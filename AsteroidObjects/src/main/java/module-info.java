module AsteroidObjects {

    requires Common;
    requires javafx.graphics;
    exports dk.sdu.cbse.asteroid;

    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.asteroid.AsteroidControlSystem;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
}