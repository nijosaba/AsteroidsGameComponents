package dk.sdu.cbse.player;

import dk.sdu.cbse.bullet.system.IBullet;
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
            float dx = (float) Math.cos(radians);
            float dy = (float) Math.sin(radians);

            // Apply acceleration
            player.setX(player.getX() + dx);
            player.setY(player.getY() + dy);
        }
    }

    private void updateShape(Player player) {
        // Optional: Rotate polygon coordinates if you want to visually update the ship direction
    }

    private void wrapPosition(Player player, GameData gameData) {
        double x = player.getX();
        double y = player.getY();
        int width = gameData.getDisplayWidth();
        int height = gameData.getDisplayHeight();

        // Wrap around screen edges
        if (x > width) {
            player.setX(0);
        } else if (x < 0) {
            player.setX(width);
        }

        if (y > height) {
            player.setY(0);
        } else if (y < 0) {
            player.setY(height);
        }
    }

    public void process(GameData gameData, World world) {
        for (Entities entity : world.getEntities()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                handleMovement(player, gameData);
                updateShape(player);
                wrapPosition(player, gameData);

                // Change to isDown with cooldown
                if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - player.getLastShotTime() >= SHOOTING_COOLDOWN) {
                        System.out.println("Attempting to fire bullet");
                        Collection<? extends IBullet> services = getBulletSPIs();
                        System.out.println("Found " + services.size() + " bullet services");
                        services.stream().findFirst().ifPresent(
                                spi -> world.addEntity(spi.createBullet(player, gameData))
                        );
                        player.setLastShotTime(currentTime);
                    }
                }
            }
        }
    }
    private Collection<? extends IBullet> getBulletSPIs() {
        return ServiceLoader.load(IBullet.class).stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
