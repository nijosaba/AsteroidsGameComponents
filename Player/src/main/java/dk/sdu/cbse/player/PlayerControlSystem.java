package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class PlayerControlSystem implements IEntityProcessingService {


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

    @Override
    public void process(GameData gameData, World world) {
        for (Entities entity : world.getEntities()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                handleMovement(player, gameData);
                updateShape(player);
                wrapPosition(player, gameData);
            }
        }
    }
}
