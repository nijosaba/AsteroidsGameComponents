package dk.sdu.cbse.common.asteroid;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.World;

public interface IAsteroidSplitter {

    void createSplitAsteroid(Entities originalAsteroid, World world);
}
