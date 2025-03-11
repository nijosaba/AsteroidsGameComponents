package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;
import javafx.scene.paint.Color;

import java.util.Random;

public class Asteroid extends Entities {
    private GameData gameData;

    private Random random = new Random();

    public Asteroid(GameData gameData) {
        this.gameData = gameData;
        setColor(Color.MAGENTA);
        spawnAtEdge(gameData); //nødvendigt da asteroider skal skal kendes skærmens dimensioner for at kunne spawne i kanterne
        setSpeed(random.nextFloat() +1 ); // skal sættes som random for asteroider
    }
}
