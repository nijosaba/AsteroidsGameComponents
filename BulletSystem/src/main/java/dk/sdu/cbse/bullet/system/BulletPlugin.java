package dk.sdu.cbse.bullet.system;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.commonbullet.Bullet;

public class BulletPlugin implements IGamePluginService {


    @Override
    public void start(GameData gameData, World world) {
        //bullets created dynamically when shooting
        //nothing to initialize at startup obviously..
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities(Bullet.class).forEach(world::removeEntity);
    }
}
