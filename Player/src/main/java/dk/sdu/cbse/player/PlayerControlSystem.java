package dk.sdu.cbse.player;

import dk.sdu.cbse.commonbullet.IBulletSPI;
import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class PlayerControlSystem implements IEntityProcessingService {

    private static final long SHOOTING_COOLDOWN = 300; // milliseconds

    private void handleMovement(Player player, GameData gameData) {
        GameKeys keys = gameData.getKeys();

        // Rotation
        if (keys.isDown(GameKeys.LEFT)) {
            player.setRotation(player.getRotation() - 5);
        }
        if (keys.isDown(GameKeys.RIGHT)) {
            player.setRotation(player.getRotation() + 5);
        }

        // Acceleration
        if (keys.isDown(GameKeys.UP)) {
            // Convert degrees to radians
            double radians = Math.toRadians(player.getRotation());

            // Calculate acceleration vector
            float changeX = (float) Math.cos(radians);
            float changeY = (float) Math.sin(radians);

            // Apply acceleration
            player.setX(player.getX() + changeX); //speed = 1unit pr frame
            player.setY(player.getY() + changeY);
        }
    }


    @Override
    public void process(GameData gameData, World world) {
        for (Entities entity : world.getEntities()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                handleMovement(player, gameData);
                shootingCooldown(player, gameData, world);
                //player.wrapAroundScreen(player, gameData);
                player.antiWrapAround(player, gameData);
            }
        }

    }
    public void shootingCooldown(Player player, GameData gameData, World world) {
        if(gameData.getKeys().isDown(GameKeys.SPACE)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - player.getLastShotTime() >= SHOOTING_COOLDOWN) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(player, gameData))
                );
                player.setLastShotTime(currentTime);
            }
        }

    }

    private Collection<? extends IBulletSPI> getBulletSPIs() {
        return ServiceLoader.load(IBulletSPI.class).stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

}
