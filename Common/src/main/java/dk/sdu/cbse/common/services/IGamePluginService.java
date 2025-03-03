package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IGamePluginService {

    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}



/**
 * Service interface for game plugins that can be loaded and unloaded during runtime.
 * Plugins can add or remove game entities and initialize game components.
 */

/** void start metoden tager gameData og world som parametre, og starter game plugin service, og tilføjer entities til world.
 * Starts the game plugin and creates neccesary entities.
 * Precondition: @pre The game world is initialized and ready to accept new entities
 * Postcondition:@post The plugin's entities are created and added to the game world
 */

/** void stop metoden tager gameData og world som parametre, og stopper game plugin service, og fjerner entities fra world.
 * Stops the plugin and removes its entities from the game.
 * Precondition:  @pre The plugin has been started
 * Postcondition: @post All entities created by this plugin are removed from the game world
 */







/*
The IGamePluginService interface is used to define the methods that a game plugin service must implement.
The start method is used to start the game plugin service, and the stop method is used to stop the game plugin service.
These methods take the game data and world as parameters, allowing the game plugin service to interact with the game data
and world during its execution.
 */