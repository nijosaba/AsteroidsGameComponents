package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;


public interface IPostEntityProcessingService {

    void process(GameData gameData, World world); //opdatere verdenen
}


/**
 * Service interface for post-processing game entities after main processing.
 * Handles cleanup, collision detection, or other post-update operations.
 */
/**
 * Interface for post-entity processing services.
 * Implementations of this interface are responsible for processing entities after the main processing has occurred.
 */

/**
 * Processes the entities in the game world after the main processing.
 * Precondition: @pre Main entity processing is completed
 * Postcondition: @post Post-processing operations are applied to relevant entities
 * Performs post-processing operations on entities.
 * gameData Contains game state and timing information
 * world Contains all game entities
 */