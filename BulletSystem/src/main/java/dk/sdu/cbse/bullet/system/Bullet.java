package dk.sdu.cbse.bullet.system;

import dk.sdu.cbse.common.data.Entities;
import javafx.scene.paint.Color;

public class Bullet extends Entities {
public Bullet() {
    setSpeed(5.0f);
    setPolygonCoordinates(-1,-1, 1,-1, 1,1, -1,1);
    setRadius(5.0f);
    setColor(Color.YELLOW);
}
}
