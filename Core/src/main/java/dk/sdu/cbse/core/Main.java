package dk.sdu.cbse.core;

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
        System.out.println("JavaFX Application starting...");

        // Configure game window
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.setStyle("-fx-background-color: black;");

        // Set up scene
        Scene scene = new Scene(gameWindow);
        stage.setTitle("Asteroid Game");
        stage.setScene(scene);
        stage.setResizable(false);

        // Set up keyboard input
        setupInput(scene);

        // Start all game plugins
        loadPlugins();

        // Create polygons for all entities
        for (Entities entity : world.getEntities()) {
            System.out.println("Creating initial polygon for: " + entity.getClass().getName());
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygon.setStroke(Color.LIME); // Bright color against black
            polygon.setFill(Color.TRANSPARENT);
            polygon.setStrokeWidth(2.0);
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }

        // Set up game loop
        setupGameLoop();

        // Draw a debug reference point in the center
        Polygon debugCenter = new Polygon(0,0, 5,0, 5,5, 0,5);
        debugCenter.setFill(Color.RED);
        debugCenter.setTranslateX(gameData.getDisplayWidth()/2);
        debugCenter.setTranslateY(gameData.getDisplayHeight()/2);
        gameWindow.getChildren().add(debugCenter);

        System.out.println("About to show stage...");
        stage.show();
        System.out.println("Stage shown");
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
        System.out.println("Loading game plugins...");
        ServiceLoader<IGamePluginService> plugins = ServiceLoader.load(IGamePluginService.class);
        int count = 0;

        for (IGamePluginService plugin : plugins) {
            count++;
            System.out.println("Starting plugin: " + plugin.getClass().getName());
            plugin.start(gameData, world);
        }

        System.out.println("Loaded " + count + " plugins");
        System.out.println("World now has " + world.getEntities().size() + " entities");

        // Print details of each entity for debugging
        for (Entities entity : world.getEntities()) {
            System.out.println("Entity: " + entity.getClass().getName() +
                    " at position (" + entity.getX() + "," + entity.getY() + ")");
            System.out.println("Polygon coords: " + java.util.Arrays.toString(entity.getPolygonCoordinates()));
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
                Polygon polygon = polygons.get(entity);
                gameWindow.getChildren().remove(polygon);
                return true;
            }
            return false;
        });

        // Add new entities or update existing ones
        for (Entities entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                System.out.println("Creating new polygon for: " + entity.getClass().getName());
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygon.setStroke(Color.LIME); // Bright color against black
                polygon.setFill(Color.TRANSPARENT);
                polygon.setStrokeWidth(2.0);
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}