module BulletSystem {
    requires Common;
    exports dk.sdu.cbse.bullet.system;

    provides dk.sdu.cbse.common.services.IEntityProcessingService
            with dk.sdu.cbse.bullet.system.BulletControlSystem;
    provides dk.sdu.cbse.common.services.IGamePluginService
            with dk.sdu.cbse.bullet.system.BulletPlugin;
    provides dk.sdu.cbse.bullet.system.IBullet
            with dk.sdu.cbse.bullet.system.BulletControlSystem;
}