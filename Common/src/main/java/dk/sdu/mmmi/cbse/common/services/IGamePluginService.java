package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}




/** void start metoden tager gameData og world som parametre, og starter game plugin service, og tilføjer entities til world.
 * Starts the game plugin.
 * Precondition: The game data and world are not null.
 * Postcondition: Entities are added to the world.
 */

/** void stop metoden tager gameData og world som parametre, og stopper game plugin service, og fjerner entities fra world.
 * Stops the game plugin.
 * Precondition: The game data and world are not null.
 * Postcondition: Entities are removed from the world.
 */







/*
The IGamePluginService interface is used to define the methods that a game plugin service must implement.
The start method is used to start the game plugin service, and the stop method is used to stop the game plugin service.
These methods take the game data and world as parameters, allowing the game plugin service to interact with the game data
and world during its execution.
 */