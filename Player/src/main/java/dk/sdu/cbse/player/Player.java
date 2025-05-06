package dk.sdu.cbse.player;


import dk.sdu.cbse.common.data.Entities;
import javafx.scene.paint.Color;

public class Player extends Entities {


    public Player() {
        setColor(Color.LIME);
        setCollisionRadius(10);
        setHealthPoint(3);


    }

}
