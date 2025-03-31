package dk.sdu.cbse.core;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

public class Game {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entities, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();

    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServiceList;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;

    Game(List<IGamePluginService> gamePluginServices, List<IEntityProcessingService> entityProcessingServiceList, List<IPostEntityProcessingService> postEntityProcessingServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServiceList = entityProcessingServiceList;
        this.postEntityProcessingServices = postEntityProcessingServices;
    }

    public void start(Stage stage) {
        // Configure game window
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(gameWindow);
        stage.setTitle("Asteroid Game");
        stage.setScene(scene);
        stage.setResizable(false);
        setupInput(scene); //keyboard input

        for (IGamePluginService iGamePlugin : getGamePluginServices()) {// Start all game plugins
            iGamePlugin.start(gameData, world);
        }

        // Create polygons for all entities
        for (Entities entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygon.setStroke(entity.getColor());
            polygon.setFill(Color.TRANSPARENT);
            polygon.setStrokeWidth(2.0);
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
        render();
        //setupGameLoop();
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

/*
    public void setupGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameData.getKeys().update();

                for (IEntityProcessingService processor : ServiceLoader.load(IEntityProcessingService.class)) {
                    processor.process(gameData, world);
                }

                for (IPostEntityProcessingService postProcessor :
                        ServiceLoader.load(IPostEntityProcessingService.class)) {
                    postProcessor.process(gameData, world);
                }

                updateVisuals();
            }
        }.start();
    }

 */


    //ikke nødvendig da setupgameloop sørger for animationtimer
    private void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                updateVisuals();
                gameData.getKeys().update();
            }

        }.start();
    }
    //ikke nødvendig uden render metoden
    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
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



    public List<IGamePluginService> getGamePluginServices() {
        return gamePluginServices;
    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return entityProcessingServiceList;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postEntityProcessingServices;
    }

}
