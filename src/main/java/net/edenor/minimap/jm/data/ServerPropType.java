package net.edenor.minimap.jm.data;

import java.util.HashMap;
import java.util.Map;

public enum ServerPropType {
    GLOBAL(1),
    DEFAULT(2),
    DIMENSION(3);

    private final int id;
    private static final Map<Integer, ServerPropType> map = new HashMap<>();

    ServerPropType(int id) {
        this.id = id;
    }

    public static ServerPropType getFromType(int id) {
        return map.get(id);
    }

    public int getId() {
        return this.id;
    }

    static {
        ServerPropType[] types = values();

        for (ServerPropType propertyType : types) {
            map.put(propertyType.id, propertyType);
        }

    }
}
