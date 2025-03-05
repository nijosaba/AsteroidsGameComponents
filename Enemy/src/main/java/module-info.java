import dk.sdu.cbse.bullet.system.IBulletSPI;

module Enemy {

    requires Common;
    requires BulletSystem;
    exports dk.sdu.cbse.enemy;

    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.enemy.EnemyControlSystem;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;

    uses IBulletSPI;
}