package dk.sdu.cbse.bullet.system;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, IBulletSPI {


    private void moveBullet (Bullet bullet) { //opdatere bullet position i gameloopet

        double radians = Math.toRadians(bullet.getRotation());
        float dx = (float) Math.cos(radians) *(float) bullet.getSpeed();
        float dy = (float) Math.sin(radians) *(float) bullet.getSpeed();

        bullet.setX(bullet.getX() + dx);
        bullet.setY(bullet.getY() + dy);

    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entities entity : world.getEntities()) {
            if (entity instanceof Bullet) {
                Bullet bullet = (Bullet) entity;
                moveBullet(bullet);
                //wrapPosition(bullet, gameData);
            }
        }
    }

    @Override
    public Entities createBullet(Entities shooter, GameData gameData) { //initializere ny bullet via SPI(serviceproviderinterface mechanisme)
        Bullet bullet = new Bullet();

        // Use shooter's position and rotation
        double radians = Math.toRadians(shooter.getRotation());
        double offsetX = Math.cos(radians) * shooter.getRadius();
        double offsetY = Math.sin(radians) * shooter.getRadius();

        bullet.setX(shooter.getX() + offsetX);
        bullet.setY(shooter.getY() + offsetY);
        bullet.setRotation(shooter.getRotation());

        return bullet;
    }

    /*
    private void wrapPosition(Bullet bullet, GameData gameData) {
        double x = bullet.getX();
        double y = bullet.getY();
        int width = gameData.getDisplayWidth();
        int height = gameData.getDisplayHeight();

        // Wrap around screen edges
        if (x > width) {
            bullet.setX(0);
        } else if (x < 0) {
            bullet.setX(width);
        }

        if (y > height) {
            bullet.setY(0);
        } else if (y < 0) {
            bullet.setY(height);
        }
    }

     */
}
