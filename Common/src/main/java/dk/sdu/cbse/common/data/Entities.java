package dk.sdu.cbse.common.data;


import java.io.Serializable;
import java.util.Random;
import java.util.UUID;
import javafx.scene.paint.Color;

public class Entities implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private double speed; // for nu, kun til asteroid
    private long lastShotTime = 0; // til player
    private int healthPoint = 1; // default hp
    private Color color = Color.WHITE;

    private Random random = new Random();
    private double spawnerX; // skal laves til enemy og asteroid
    private double spawnerY;
    private int spawnCount;
    private boolean isHit = false;


    public void wrapAroundScreen(Entities entities, GameData gameData) {
        if (entities.getX() < 0) entities.setX(gameData.getDisplayWidth());
        if (entities.getX() > gameData.getDisplayWidth()) entities.setX(0);
        if (entities.getY() < 0) entities.setY(gameData.getDisplayHeight());
        if (entities.getY() > gameData.getDisplayHeight()) entities.setY(0);
    }

    public String getID() {
        return ID.toString();
    }

    public void spawnAtEdge(GameData gameData) {
        int edge = random.nextInt(4); // 0 = venstre, 1 = højre, 2 = top, 3 = bund
        int x,y;

        switch (edge) {
            case 0: //left edge
                x = 0;
                y = random.nextInt(gameData.getDisplayHeight());
                break;
            case 1: // right edge
                x = gameData.getDisplayWidth();
                y = random.nextInt(gameData.getDisplayHeight());
                break;
            case 2: // top edge
                x = random.nextInt(gameData.getDisplayWidth());
                y = 0;
                break;
            case 3: // bottom edge
                x = random.nextInt(gameData.getDisplayWidth());
                y = gameData.getDisplayHeight();
                break;
            default:
                throw new IllegalStateException("Edge error" + edge);
        }
        this.x = x;
        this.y = y;
    }


    public void setPolygonCoordinates(double... coordinates) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }


    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }


    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return this.speed;
    }

    public long getLastShotTime() {
        return lastShotTime;
    }

    public void setLastShotTime(long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public int getSpawnCount() {
        return spawnCount;
    }

    public void setSpawnCount(int spawnCount) {
        this.spawnCount = spawnCount;
    }
}
