package dk.sdu.cbse.bullet.system;

import dk.sdu.cbse.common.data.Entities;
import dk.sdu.cbse.common.data.GameData;

public interface BulletSPI {
    Entities createBullet(Entities bullet, GameData gameData);
}
