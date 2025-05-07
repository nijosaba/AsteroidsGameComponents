package dk.sdu.cbse.bullet.system;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.commonbullet.Bullet;
import dk.sdu.cbse.commonbullet.IBulletSPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, IBulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        for (Entities bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * bullet.getSpeed());
            bullet.setY(bullet.getY() + changeY * bullet.getSpeed());
        }
    }

    @Override
    public Entities createBullet(Entities shooter, GameData gameData) {
        Bullet bullet = new Bullet();
        bullet.setPolygonCoordinates(-1, -1, 1, -1, 1, 1, -1, 1);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 10);
        bullet.setY(shooter.getY() + changeY * 10);
        bullet.setRotation(shooter.getRotation());
        bullet.setSpeed(5.0f);
        bullet.setCollisionRadius(1);
        return bullet;
    }
}