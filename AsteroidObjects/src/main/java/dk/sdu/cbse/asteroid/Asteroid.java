package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import javafx.scene.paint.Color;

import java.util.Random;

public class Asteroid extends Entities {

    Random random = new Random();
    private boolean isSplit = false;

    public Asteroid(GameData gameData) {
        setColor(Color.MAGENTA);
        spawnAtEdge(gameData); //nødvendigt da asteroider skal skal kendes skærmens dimensioner for at kunne spawne i kanterne
        setSpeed(random.nextFloat() +0.5); // random mellem 0.5 og 1.5
        setCollisionRadius(20);
        setSpawnCount(4);
        setHealthPoint(1);
        this.isSplit = false;
    }

    // til splitting
    public Asteroid() {
        setColor(Color.MAGENTA);
        setHealthPoint(1); // Små asteroider har også 1 HP før de fjernes
        this.isSplit = true; // flagget denne som en split-asteroide
    }
    // Getter for isSplit flag
    public boolean isSplit() {
        return isSplit;
    }


}
