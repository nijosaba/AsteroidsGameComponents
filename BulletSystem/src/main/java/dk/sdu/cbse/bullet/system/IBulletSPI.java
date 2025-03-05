package dk.sdu.cbse.bullet.system;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;

public interface IBulletSPI {
    Entities createBullet(Entities bullet, GameData gameData);
}
