package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entities;
import javafx.scene.paint.Color;

public class Enemy extends Entities {

    public Enemy() {
        setColor(Color.RED);
        setCollisionRadius(10);
        setHealthPoint(2);


    }

}
