package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {


    void process(GameData gameData, World world);
}


/**
 * Interface for entity processing services.
 * Implementations of this interface are responsible for processing entities in the game world.
 */

/**
 * Processes the entities in the game world.
 * Precondition: The game data and world are not null.
 * Postcondition: Entities are processed according to the game logic.
 */