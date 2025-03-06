package dk.sdu.cbse.core;

import javafx.scene.paint.Color;
import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

public class Main extends Application {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entities, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();

    @Override
    public void start(Stage stage) {
        // Configure game window
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(gameWindow);
        stage.setTitle("Asteroid Game");
        stage.setScene(scene);
        stage.setResizable(false);
        setupInput(scene); //keyboard input
        loadPlugins();// Start all game plugins

        // Create polygons for all entities
        for (Entities entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygon.setStroke(entity.getColor());
            polygon.setFill(Color.TRANSPARENT);
            polygon.setStrokeWidth(2.0);
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
        setupGameLoop();
        stage.show();
    }

    private void setupInput(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
        });
    }

    private void loadPlugins() {
        ServiceLoader<IGamePluginService> plugins = ServiceLoader.load(IGamePluginService.class);
        int count = 0;
        for (IGamePluginService plugin : plugins) {
            count++;
            plugin.start(gameData, world);
        }

    }

    private void setupGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Update game state
                gameData.getKeys().update();
                // Process entities
                for (IEntityProcessingService processor : ServiceLoader.load(IEntityProcessingService.class)) {
                    processor.process(gameData, world);
                }
                // Process post-entity services
                for (IPostEntityProcessingService postProcessor :
                        ServiceLoader.load(IPostEntityProcessingService.class)) {
                    postProcessor.process(gameData, world);
                }
                // Update visual representation
                updateVisuals();
            }
        }.start();
    }

    private void updateVisuals() {
        // Remove entities that are no longer in the world
        polygons.keySet().removeIf(entity -> {
            if (!world.getEntities().contains(entity)) {
                gameWindow.getChildren().remove(polygons.get(entity));
                return true;
            }
            return false;
        });

        // Add new entities or update existing ones
        for (Entities entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygon.setStroke(entity.getColor());
                polygon.setStrokeWidth(2.0);
                polygon.setFill(Color.TRANSPARENT);
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }

            // Update existing polygon properties
            polygon.setStroke(entity.getColor());
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}