package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IEntityProcessingService {


    void process(GameData gameData, World world);
}



/**
 * Service interface for processing game entities during the main game loop.
 * Handles entity behavior, movement, and state changes.
 */
/**
 * Interface for entity processing services.
 * Implementations of this interface are responsible for processing entities in the game world.
 */

/**
 * Processes the entity's logic for a single game update.
 *
 *  gameData Contains game state and timing information
 *  world Contains all game entities

/**
 * Processes the entities in the game world.
 * Precondition: The game data and world are not null.
 * Postcondition:Entity states are updated according to game logic
 */