class LRUCache:
    
    def __init__(self, capacity: int):
        # Declaramos el dictionario para los valores y la capacidad total de la cache
        self.address = OrderedDict()
        self.values = capacity

    def get(self, key: int) -> int:
        if key not in self.address:
            return -1
        else:
            # Al momento de leer el valor se vuelve el mas usado, por lo que lo movemos hasta adelante
            self.address.move_to_end(key, last=False)
            return self.address[key]

    def put(self, key: int, value: int) -> None:
        if key in self.address:
            # Hacemos update del valor que ya se tiene ademas de que lo movemos al principio
            self.address.move_to_end(key, last=False)
        else:
            if len(self.address) >= self.values:
                # Quitamos el dato menos ocupado en caso de que se quiera agregar un valor mas de la capacidad
                self.address.popitem(last=True)
        
        self.address[key] = value
        self.address.move_to_end(key, last=False)

