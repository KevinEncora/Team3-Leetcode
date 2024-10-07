import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {
    private final Map<Integer, Integer> address;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.address = new LinkedHashMap<>(capacity, 0.75f, true) {
            // Funcion para que al momento de que queramos ingresar un nuevo valor si este supera el limite, quitamos el ultimo
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        // Buscamos si existe la llave, en caso contrario regresamos -1
        return address.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        address.put(key, value);
    }
}
