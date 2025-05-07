
module Enemy {

    requires Common;
    requires javafx.graphics;
    requires CommonBullet;
    exports dk.sdu.cbse.enemy;

    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.enemy.EnemyControlSystem;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
    uses dk.sdu.cbse.commonbullet.IBulletSPI;
}