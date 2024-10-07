class LRUCache {
    private address: Map<number, { key: number, value: number }>;
    private values: number;

    // Creamos la cache y declaramos la capacidad
    constructor(capacity: number) {
        this.address = new Map();
        this.values = capacity;
    }

    get(key: number): number {
        if (!this.address.has(key)) {
            return -1;
        }
        
        const { value } = this.address.get(key)!;
        // Movemos hasta adelante el valor recientemente leido
        this.address.delete(key);
        this.address.set(key, { key, value });
        
        return value;
    }

    put(key: number, value: number): void {
        if (this.address.has(key)) {
            // Eliminamos del map el valor que vamos a poner ya que vamos a hacer update
            this.address.delete(key);
            this.address.set(key, { key, value });
        } else {
            if (this.address.size >= this.values) {
                // En caso de que al momento de insertar un nuevo valor sea mayor, eliminamos el menos usado
                const oldestKey = this.address.keys().next().value;
                this.address.delete(oldestKey);
            }

            this.address.set(key, { key, value });
        }
    }
}