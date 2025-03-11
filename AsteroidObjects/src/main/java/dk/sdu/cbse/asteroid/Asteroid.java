package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import javafx.scene.paint.Color;

import java.util.Random;

public class Asteroid extends Entities {

    public Asteroid(GameData gameData) {
        setColor(Color.MAGENTA);
        spawnAtEdge(gameData); //nødvendigt da asteroider skal skal kendes skærmens dimensioner for at kunne spawne i kanterne
        Random random = new Random();
        setSpeed(random.nextFloat() +0.5); // random mellem 0.5 og 1.5
        setSpawnCount(4);
    }
}
