package dk.sdu.cbse.enemy;


import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.commonbullet.IBulletSPI;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    private Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Entities enemy : world.getEntities(Enemy.class)) { //Entity Enemy
            if (random.nextFloat() < 0.01) {
                enemy.setRotation(random.nextFloat() * 360);
            }

            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);

            if (random.nextFloat() < 0.01) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(enemy, gameData)));
            }
            // Skærmkant-teleportering (wrap around)
            // Wrap around screen, dvs ingen collision, men kommer ud på anden side af skærmen
            enemy.wrapAroundScreen(enemy, gameData);
        }

    }
    private Collection<? extends IBulletSPI> getBulletSPIs() {
        return ServiceLoader.load(IBulletSPI.class).stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
