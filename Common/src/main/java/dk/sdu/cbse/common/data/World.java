package dk.sdu.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The World class is a container for all entities in the game.
 * It provides methods to add, remove, and retrieve entities.
 */
public class World {

    private final Map<String, Entities> entityMap = new ConcurrentHashMap<>();

    public String addEntity(Entities entities) {
        entityMap.put(entities.getID(), entities);
        return entities.getID();
    }


    public void removeEntity(Entities entities) {
        entityMap.remove(entities.getID());
    }

    public Collection<Entities> getEntities() {
        return entityMap.values();
    }

    public <E extends Entities> List<Entities> getEntities(Class<E>... entityTypes) {
        List<Entities> r = new ArrayList<>();
        for (Entities e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public Entities getEntity(String ID) {
        return entityMap.get(ID);
    }

}
