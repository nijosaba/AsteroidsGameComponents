package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {

    void process(GameData gameData, World world); //opdatere verdenen
}


/**
 * Interface for post-entity processing services.
 * Implementations of this interface are responsible for processing entities after the main processing has occurred.
 */

/**
 * Processes the entities in the game world after the main processing.
 * Precondition: The game data and world are not null.
 * Postcondition: Entities are processed according to the post-processing logic.
 */