package dk.sdu.cbse.commonbullet;

import dk.sdu.cbse.common.data.Entities;
import javafx.scene.paint.Color;

public class Bullet extends Entities {
public Bullet() {
    setSpeed(5.0f); //usikker på om det virker endnu
    setPolygonCoordinates(-1,-1, 1,-1, 1,1, -1,1);
    setColor(Color.YELLOW);
}
}
