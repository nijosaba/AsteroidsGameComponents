
module Player {
    requires Common;
    requires javafx.graphics;
    requires CollisionSystem;
    requires CommonBullet;
    exports dk.sdu.cbse.player;

    provides dk.sdu.cbse.common.services.IEntityProcessingService
            with dk.sdu.cbse.player.PlayerControlSystem;
    provides dk.sdu.cbse.common.services.IGamePluginService
            with dk.sdu.cbse.player.PlayerPlugin;
    uses dk.sdu.cbse.commonbullet.IBulletSPI;
}
//provides eks: dk.sdu.cbse.player.PlayerPlugin tilbyder en implementering af IGamePluginService
//provides gør at andre moduler kan bruge denne service, og with gør at det er denne klasse der implementerer servicen
//Player modulet leverer implementeringer af de specificerede service interfaces, så andre moduler kan bruge disse implementeringer.