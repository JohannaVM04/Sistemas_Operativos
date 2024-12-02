# 3.1 Política y Filosofía

## 1. ¿Cuál es la diferencia entre la fragmentación interna y externa? Explica cómo cada una afecta el rendimiento de la memoria

La principal diferencia entre una y otra es en dónde y cómo se da el desperdicio de la memoria: 

- **Fragmentación interna**: asigna bloques de memoria para los procesos. Esto ocurre cuando se le da más espacio de memoria del necesario, dejando "huecos" o memoria sin utilizar en cada bloque.
- **Fragmentación externa**: sucede cuando la memoria tiene suficiente espacio total para llevar a cabo los procesos, pero ese espacio está dividido en bloques pequeños que no pueden usarse eficazmente. Esto ocurre cuando la memoria se asigna de forma dinámica.

**Impacto en el rendimiento**:
- **Fragmentación interna**: No representa un problema significativo en sistemas pequeños con pocos procesos, pero puede afectar sistemas más grandes con muchos procesos pequeños, al acumular huecos en los bloques.
- **Fragmentación externa**: Afecta más al rendimiento, ya que aunque haya espacio suficiente en total, se requiere compactar la memoria. Esto consume más tiempo, aumenta la sobrecarga del sistema y puede ocasionar fallos en la asignación de memoria, afectando la velocidad y estabilidad del sistema.

---

## 2. Investigar y explicar las políticas de reemplazo de páginas en sistemas operativos. ¿Cuál consideras más eficiente y por qué?

### 1. **FIFO (Primero en entrar, primero en salir)**
Este algoritmo, inspirado en las estructuras de datos, reemplaza la primera página cargada en memoria cuando se necesita espacio para una nueva página. 

- **Funcionamiento**: Mantiene un orden secuencial según el momento de carga. Al producirse un fallo de página, la página en el frente de la cola es reemplazada.
- **Ventajas**: Simplicidad de implementación.
- **Desventajas**: No considera el uso o frecuencia de las páginas, puede presentar anomalías.

---

### 2. **LRU (Menos recientemente utilizada)**
Considerado uno de los algoritmos más eficientes, se basa en reemplazar las páginas que no han sido utilizadas recientemente.

- **Funcionamiento**: Usa un conteo del tiempo de acceso a cada página. En caso de fallo de página, se reemplaza la menos recientemente utilizada.
- **Ventajas**: Alta eficiencia en muchos escenarios.
- **Desventajas**: Complejidad de implementación (manejo de contadores y listas doblemente enlazadas) y potencial sobrecarga por operaciones adicionales.

---

### 3. **Second Chance (Reloj)**
Versión mejorada del algoritmo FIFO, utiliza un bit de referencia para saber si una página ha sido utilizada recientemente, dándole "otra oportunidad".

- **Funcionamiento**: 
  - Cada página tiene un bit de referencia que se pone en `1` cuando es accedida.
  - Al producirse un fallo de página, si el bit de la primera página en la cola es `1`, se pone en `0` y se mueve al final de la cola. Este proceso se repite hasta encontrar una página con bit `0`, que es la que se reemplaza.
- **Ventajas**: Más eficiente que FIFO.
- **Desventajas**: Menos eficiente que LRU y también propenso a sobrecarga del sistema.

---

### 4. **MFU (Menor frecuencia de uso)**
Se basa en la idea opuesta al algoritmo LRU, reemplazando la página menos frecuentemente utilizada.

- **Funcionamiento**: Cada página tiene un contador que registra cuántas veces ha sido accedida. La página con el contador más bajo es reemplazada.
- **Ventajas**: Útil en ciertos casos específicos.
- **Desventajas**: Se basa en suposiciones, lo que puede aumentar fallos de página. Es difícil de implementar por la cantidad de datos que debe procesar.

---

### 5. **OPT (Óptimo)**
Este algoritmo reemplaza la página que no será utilizada durante el mayor tiempo.

- **Funcionamiento**: Predice el futuro uso de las páginas para tomar decisiones óptimas.
- **Ventajas**: Es el algoritmo más eficiente en teoría.
- **Desventajas**: Imposible de implementar en la práctica porque requiere conocer de antemano el futuro uso de las páginas.

En mi opinión, la más eficiente es la LRU, ya que analiza cuáles páginas son las utilizadas recientemente y es aplicable a la vida real pese a ser complejo.
---
# 3.2 Memoria Real

1. Escribe un programa en C o Python que simule la administración de memoria mediante particiones fijas.

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_PARTICIONES 100

typedef struct {
    int id;
    int tamaño;
    int ocupado;
} Particion;

void mostrarEstado(Particion particiones[], int numParticiones) {
    printf("\nEstado de la memoria:\n");
    printf("===================================\n");
    for (int i = 0; i < numParticiones; i++) {
        printf("Partición %d: Tamaño = %d, %s\n",
               i + 1,
               particiones[i].tamaño,
               particiones[i].ocupado ? "Ocupado" : "Libre");
    }
    printf("===================================\n");
}

int main() {
    int tamañoMemoria;
    int numParticiones;
    Particion particiones[MAX_PARTICIONES];
    
    printf("Ingrese el tamaño total de la memoria: ");
    scanf("%d", &tamañoMemoria);

    printf("Ingrese el número de particiones: ");
    scanf("%d", &numParticiones);

    if (numParticiones > MAX_PARTICIONES) {
        printf("El número máximo de particiones permitidas es %d.\n", MAX_PARTICIONES);
        return 1;
    }

    int tamañoParticion = tamañoMemoria / numParticiones;

    for (int i = 0; i < numParticiones; i++) {
        particiones[i].id = i + 1;
        particiones[i].tamaño = tamañoParticion;
        particiones[i].ocupado = 0;
    }

    int opcion;
    do {
        printf("\nMenú:\n");
        printf("1. Asignar un proceso a una partición\n");
        printf("2. Liberar una partición\n");
        printf("3. Mostrar estado de la memoria\n");
        printf("4. Salir\n");
        printf("Seleccione una opción: ");
        scanf("%d", &opcion);

        switch (opcion) {
            case 1: {
                int idProceso, tamañoProceso;
                printf("Ingrese el ID del proceso: ");
                scanf("%d", &idProceso);
                printf("Ingrese el tamaño del proceso: ");
                scanf("%d", &tamañoProceso);

                int asignado = 0;
                for (int i = 0; i < numParticiones; i++) {
                    if (!particiones[i].ocupado && particiones[i].tamaño >= tamañoProceso) {
                        particiones[i].ocupado = 1;
                        printf("Proceso %d asignado a la partición %d.\n", idProceso, i + 1);
                        asignado = 1;
                        break;
                    }
                }
                if (!asignado) {
                    printf("No hay particiones disponibles para el proceso %d.\n", idProceso);
                }
                break;
            }
            case 2: {
                int idParticion;
                printf("Ingrese el número de la partición a liberar: ");
                scanf("%d", &idParticion);

                if (idParticion < 1 || idParticion > numParticiones) {
                    printf("Número de partición inválido.\n");
                } else if (!particiones[idParticion - 1].ocupado) {
                    printf("La partición %d ya está libre.\n", idParticion);
                } else {
                    particiones[idParticion - 1].ocupado = 0;
                    printf("Partición %d liberada.\n", idParticion);
                }
                break;
            }
            case 3:
                mostrarEstado(particiones, numParticiones);
                break;
            case 4:
                printf("Saliendo del programa.\n");
                break;
            default:
                printf("Opción no válida.\n");
        }
    } while (opcion != 4);

    return 0;
    
```
}


2. Diseña un algoritmo para calcular qué procesos pueden ser asignados a un sistema con memoria real limitada utilizando el algoritmo de "primera cabida".

```c

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_PARTICIONES 100

typedef struct {
    int id;
    int tamaño;
    int ocupado;
} Particion;

void mostrarEstado(Particion particiones[], int numParticiones) {
    printf("\nEstado de la memoria:\n");
    printf("===================================\n");
    for (int i = 0; i < numParticiones; i++) {
        printf("Partición %d: Tamaño = %d, %s\n",
               i + 1,
               particiones[i].tamaño,
               particiones[i].ocupado ? "Ocupado" : "Libre");
    }
    printf("===================================\n");
}

int main() {
    int tamañoMemoria;
    int numParticiones;
    Particion particiones[MAX_PARTICIONES];
    
    printf("Ingrese el tamaño total de la memoria: ");
    scanf("%d", &tamañoMemoria);

    printf("Ingrese el número de particiones: ");
    scanf("%d", &numParticiones);

    if (numParticiones > MAX_PARTICIONES) {
        printf("El número máximo de particiones permitidas es %d.\n", MAX_PARTICIONES);
        return 1;
    }

    int tamañoParticion = tamañoMemoria / numParticiones;

    for (int i = 0; i < numParticiones; i++) {
        particiones[i].id = i + 1;
        particiones[i].tamaño = tamañoParticion;
        particiones[i].ocupado = 0;
    }

    int opcion;
    do {
        printf("\nMenú:\n");
        printf("1. Asignar un proceso a una partición (Primera cabida)\n");
        printf("2. Liberar una partición\n");
        printf("3. Mostrar estado de la memoria\n");
        printf("4. Salir\n");
        printf("Seleccione una opción: ");
        scanf("%d", &opcion);

        switch (opcion) {
            case 1: {
                int idProceso, tamañoProceso;
                printf("Ingrese el ID del proceso: ");
                scanf("%d", &idProceso);
                printf("Ingrese el tamaño del proceso: ");
                scanf("%d", &tamañoProceso);

                int asignado = 0;
                for (int i = 0; i < numParticiones; i++) {
                    if (!particiones[i].ocupado && particiones[i].tamaño >= tamañoProceso) {
                        particiones[i].ocupado = 1;
                        printf("Proceso %d asignado a la partición %d.\n", idProceso, i + 1);
                        asignado = 1;
                        break;  
                    }
                }
                if (!asignado) {
                    printf("No hay particiones disponibles para el proceso %d.\n", idProceso);
                }
                break;
            }
            case 2: {
                int idParticion;
                printf("Ingrese el número de la partición a liberar: ");
                scanf("%d", &idParticion);

                if (idParticion < 1 || idParticion > numParticiones) {
                    printf("Número de partición inválido.\n");
                } else if (!particiones[idParticion - 1].ocupado) {
                    printf("La partición %d ya está libre.\n", idParticion);
                } else {
                    particiones[idParticion - 1].ocupado = 0;
                    printf("Partición %d liberada.\n", idParticion);
                }
                break;
            }
            case 3:
                mostrarEstado(particiones, numParticiones);
                break;
            case 4:
                printf("Saliendo del programa.\n");
                break;
            default:
                printf("Opción no válida.\n");
        }
    } while (opcion != 4);

    return 0;
   
```
}

# 3.3 Organización de memoria virtual

## 1. **Investiga y explica el concepto de "paginación" y "segmentación". ¿Cuáles son las ventajas y desventajas de cada técnica?**

La **paginación** es una técnica para la administración del espacio de la memoria. Consiste en dividir la memoria en bloques de un tamaño determinado llamados *páginas* y dividir la memoria física en bloques que se denominan *marcos de página*. La existencia de estas dos partes permite que las páginas no necesariamente estén contiguas en la memoria física. 

Esta técnica elimina la **fragmentación externa**, ya que cualquier marco de página puede utilizarse para cualquier página del proceso (siempre y cuando esté libre). Además, mejora la gestión y eficiencia del espacio de memoria. Sin embargo, la paginación presenta el problema de la **fragmentación interna** y, para mantener el mapeo entre páginas y marcos, consume un espacio considerable en memoria, lo que puede generar una sobrecarga y afectar directamente al rendimiento del sistema.

La **segmentación** es otra técnica de administración de memoria que divide esta en espacios de tamaños variables. Cada segmento cuenta con un área de la memoria física, y todas estas partes van organizadas contiguamente. Esta técnica establece una correspondencia lógica entre unidades lógicas y la memoria física, facilitando la protección de los segmentos y procesos. También permite un uso eficiente de la memoria, y cada segmento puede compartirse entre varios procesos.

Una desventaja de la segmentación es que recae en la **fragmentación externa**. Puede ocurrir que exista suficiente espacio para un proceso en su totalidad, pero este no esté continuo, impidiendo su uso. Además, combinar fragmentos para solucionar este problema puede generar una sobrecarga de administración, junto con los procesos adicionales necesarios para dividir la memoria según el espacio necesario para cada uno.


## 2. **Escribe un programa que simule una tabla de páginas para procesos con acceso aleatorio a memoria virtual.**

```python
import random
from time import sleep

TAMANO_MEMORIA_VIRTUAL = 1024
TAMANO_MEMORIA_FISICA = 512
TAMANO_PAGINA = 64

NUM_PAGINAS = TAMANO_MEMORIA_VIRTUAL 
NUM_MARCOS = TAMANO_MEMORIA_FISICA 

tabla_paginas = {}
marcos_disponibles = list(range(NUM_MARCOS))
random.shuffle(marcos_disponibles)

def acceso_memoria_virtual():
    direccion_virtual = random.randint(0, TAMANO_MEMORIA_VIRTUAL - 1)
    numero_pagina = direccion_virtual 
    desplazamiento = direccion_virtual % TAMANO_PAGINA

    print(f"\nAccediendo a la dirección virtual: {direccion_virtual}")
    print(f"Número de página: {numero_pagina}, Desplazamiento: {desplazamiento}")

    if numero_pagina in tabla_paginas:
        marco_pagina = tabla_paginas[numero_pagina]
        print(f"La página {numero_pagina} está en el marco {marco_pagina}.")
    else:
        if marcos_disponibles:
            marco_pagina = marcos_disponibles.pop()
            tabla_paginas[numero_pagina] = marco_pagina
            print(f"Asignando página {numero_pagina} al marco {marco_pagina}.")
        else:
            print("No hay marcos disponibles para asignar la página.")
            return

    direccion_fisica = (marco_pagina * TAMANO_PAGINA) + desplazamiento
    print(f"Dirección física correspondiente: {direccion_fisica}")

NUM_ACCESOS = 10

for _ in range(NUM_ACCESOS):
    acceso_memoria_virtual()
    sleep(1)


---

```
# 3.4 Administración de memoria virtual

## 1. **Escribe un código que implemente el algoritmo de reemplazo de página "Least Recently Used" (LRU).**

```python
from collections import OrderedDict

class LRUCache:
    def __init__(self, capacidad):
        self.cache = OrderedDict()
        self.capacidad = capacidad

    def acceder_pagina(self, pagina):
        if pagina in self.cache:
            self.cache.pop(pagina)
            self.cache[pagina] = True
            print(f"Página {pagina} accedida, ya está en caché.")
        else:
            if len(self.cache) >= self.capacidad:
                pagina_mas_antigua, _ = self.cache.popitem(last=False)
                print(f"La caché está llena. Eliminando la página menos recientemente usada {pagina_mas_antigua}.")
            self.cache[pagina] = True
            print(f"Página {pagina} añadida a la caché.")
        self.imprimir_cache()

    def imprimir_cache(self):
        print("Estado actual de la caché:")
        for pagina in self.cache:
            print(pagina, end=' ')
        print('\n')

if __name__ == '__main__':
    capacidad = int(input("Ingrese la capacidad de la caché: "))
    secuencia_paginas = input("Ingrese la secuencia de páginas separadas por espacios: ")
    secuencia_paginas = list(map(int, secuencia_paginas.strip().split()))

    lru_cache = LRUCache(capacidad)

    for pagina in secuencia_paginas:
        lru_cache.acceder_pagina(pagina)

        
```

## 2. **Diseña un diagrama que represente el proceso de traducción de direcciones virtuales a físicas en un sistema con memoria virtual.**
(a forma de pseudocodigo porque no se hace diagramas en marktext)

```plaintext
Inicio
Estructura TablaDePaginas
    entrada[N] // N es el número de entradas en la tabla de páginas
FinEstructura

Estructura EntradaTablaDePaginas
    marcoDePagina // Marco de página correspondiente
    bitDeValidez  // Indica si la entrada es válida o inválida
FinEstructura

// Variables
direccionVirtual // Dirección virtual que se desea traducir
direccionFisica  // Dirección física resultante
numeroDePagina   // Número de página extraído de la dirección virtual
desplazamiento   // Desplazamiento dentro de la página
entradaTabla     // Entrada de la tabla de páginas correspondiente al número de página

// Proceso de traducción
Función traducirDireccion(direccionVirtual)
    // Extraer el número de página de la dirección virtual
    numeroDePagina = extraerNumeroDePagina(direccionVirtual)
    
    // Extraer el desplazamiento de la dirección virtual
    desplazamiento = extraerDesplazamiento(direccionVirtual)
    
    // Obtener la entrada de la tabla de páginas correspondiente al número de página
    entradaTabla = TablaDePaginas.entrada[numeroDePagina]
    
    // Comprobar si la entrada de la tabla de páginas es válida
    Si entradaTabla.bitDeValidez == 0 Entonces
        Error "Fallo de página: la página no está en memoria"
        FinFunción
    FinSi
    
    // Calcular la dirección física
    direccionFisica = (entradaTabla.marcoDePagina * tamañoDePagina) + desplazamiento
    
    // Devolver la dirección física
    Retornar direccionFisica
FinFunción

// Función para extraer el número de página de la dirección virtual
Función extraerNumeroDePagina(direccionVirtual)
    Retornar (direccionVirtual / tamañoDePagina)
FinFunción

// Función para extraer el desplazamiento de la dirección virtual
Función extraerDesplazamiento(direccionVirtual)
    Retornar (direccionVirtual % tamañoDePagina)
FinFunción

Fin
```

# Integración

1. Analiza un sistema operativo moderno (por ejemplo, Linux o Windows) e identifica cómo administra la memoria virtual.
## Administración de la Memoria Virtual en Linux y Windows

## Linux
En Linux, la memoria virtual se gestiona mediante la Unidad de Administración de Memoria (MMU), que traduce las direcciones virtuales en direcciones físicas. Esto permite que los programas utilicen más memoria de la que físicamente tienen disponible, aprovechando el espacio de intercambio (swap) en el disco duro.

Linux utiliza la paginación para dividir la memoria en bloques de tamaño fijo llamados páginas. Cada proceso tiene su propio espacio de direcciones virtuales, que se asignan a marcos de página en la memoria física. Esto significa que las páginas de un proceso no tienen que estar contiguas en la memoria física, lo que facilita la gestión y el uso eficiente de la memoria.Cada proceso tiene una tabla de páginas que mantiene el mapeo entre las páginas virtuales y los marcos de página físicos. La MMU utiliza esta tabla para traducir las direcciones virtuales a físicas en tiempo real.

### Memoria de Intercambio (Swap)
Cuando la memoria física se llena, el sistema operativo mueve las páginas inactivas al espacio de intercambio en el disco duro. Esto libera memoria física para los procesos activos y permite que el sistema siga funcionando de manera eficiente, Linux tiene varias configuraciones para gestionar el uso del espacio de intercambio, como la posibilidad de ajustar la propensión al intercambio (swappiness). Un valor alto de swappiness hará que el sistema utilice más el espacio de intercambio, mientras que un valor bajo preferirá mantener las páginas en la memoria física siempre que sea posible. Los comandos `swapon` y `swapoff` permiten habilitar y deshabilitar dispositivos de intercambio según sea necesario.

## Windows
En Windows, la memoria virtual también permite que los programas utilicen más memoria de la disponible físicamente. La administración de memoria en Windows se realiza a través de un archivo de paginación (paging file), que actúa como una extensión de la memoria RAM.El archivo de paginación en Windows se utiliza para almacenar las páginas de memoria que no caben en la RAM. Este archivo se encuentra en el disco duro y Windows puede ajustar su tamaño automáticamente según las necesidades del sistema.Los usuarios pueden configurar manualmente el tamaño del archivo de paginación a través de la configuración avanzada del sistema.

### Asignación de Memoria
Cuando un proceso necesita más memoria de la disponible en la RAM, Windows mueve las páginas menos utilizadas al archivo de paginación, dejando libre espacio en la memoria física para las páginas más activas.Windows tiene herramientas como el Administrador de Tareas y el Monitor de Recursos para supervisar y gestionar la utilización de la memoria. Estas herramientas proporcionan información detallada sobre el uso de la memoria física y virtual, permitiendo a los usuarios identificar problemas de rendimiento y optimizar la configuración del sistema.Windows ajusta automáticamente el tamaño del archivo de paginación según las necesidades del sistema.

2. Realiza una simulación en cualquier lenguaje de programación que emule el *swapping* de procesos en memoria virtual.

```py

import random
from collections import OrderedDict
from time import sleep

TAMANO_MEMORIA_VIRTUAL = 1024  
TAMANO_MEMORIA_FISICA = 512    
TAMANO_PAGINA = 64             

NUM_PAGINAS = TAMANO_MEMORIA_VIRTUAL 
NUM_MARCOS = TAMANO_MEMORIA_FISICA 

marcos_disponibles = list(range(NUM_MARCOS))

class LRUCache:
    def __init__(self, capacidad, marcos):
        self.cache = OrderedDict()
        self.capacidad = capacidad
        self.marcos_disponibles = marcos  

    def acceder_pagina(self, pagina):
        if pagina in self.cache:
            marco = self.cache.pop(pagina)
            self.cache[pagina] = marco
            print(f"Página {pagina} accedida, ya está en memoria en el marco {marco}.")
        else:
            if len(self.cache) >= self.capacidad:
                pagina_vieja, marco_liberado = self.cache.popitem(last=False)
                print(f"La memoria está llena. Reemplazando la página {pagina_vieja} del marco {marco_liberado} con la página {pagina}.")
                self.cache[pagina] = marco_liberado
            else:
                if not self.marcos_disponibles:
                    print("No hay marcos disponibles para asignar.")
                    return None
                marco_asignado = self.marcos_disponibles.pop(0)
                self.cache[pagina] = marco_asignado
                print(f"Página {pagina} cargada en memoria en el marco {marco_asignado}.")
        self.imprimir_cache()
        return self.cache[pagina]

    def imprimir_cache(self):
        print("Estado actual de la memoria:")
        for pagina, marco in self.cache.items():
            print(f"Página {pagina} en marco {marco}")
        print('\n')

    def obtener_marco(self, pagina):
        return self.cache.get(pagina, None)

lru_cache = LRUCache(NUM_MARCOS, marcos_disponibles)

def acceso_memoria_virtual():
    direccion_virtual = random.randint(0, TAMANO_MEMORIA_VIRTUAL - 1)
    numero_pagina = direccion_virtual 
    desplazamiento = direccion_virtual % TAMANO_PAGINA

    print(f"\nAccediendo a la dirección virtual: {direccion_virtual}")
    print(f"Número de página: {numero_pagina}, Desplazamiento: {desplazamiento}")

    marco_pagina = lru_cache.acceder_pagina(numero_pagina)

    if marco_pagina is not None:
        direccion_fisica = (marco_pagina * TAMANO_PAGINA) + desplazamiento
        print(f"Dirección física correspondiente: {direccion_fisica}")
    else:
        print("Error: No se pudo asignar un marco a la página.")


NUM_ACCESOS = 15  

for _ in range(NUM_ACCESOS):
    acceso_memoria_virtual()
    sleep(1)

```

# Administración de Entrada/Salida

## 4.1 Dispositivos y manejadores de dispositivos

1. **Explica la diferencia entre dispositivos de bloque y dispositivos de carácter. Da un ejemplo de cada uno.**

**Dispositivos de Bloques:** Estos dispositivos gestionan los datos en bloques de tamaño fijo y permiten el acceso aleatorio, lo que significa que se puede leer o escribir datos en cualquier orden, sin necesidad de seguir una secuencia específica. Características:
La transferencia de datos se realiza en bloques o sectores, lo que los hace ideales para el almacenamiento masivo,pueden acceder directamente a cualquier bloque sin necesidad de leerlos en secuencia.Se utilizan típicamente para dispositivos de almacenamiento. Ejemplo: Disco duro (HDD), unidad de estado sólido (SSD) o unidades USB. Estos dispositivos almacenan datos y permiten que el sistema acceda a cualquier parte del dispositivo directamente.

**Dispositivos de Caracteres:** Estos dispositivos gestionan datos como un flujo continuo de bytes o caracteres, con acceso secuencial, lo que significa que los datos deben leerse o escribirse en el orden en que se reciben.La transferencia de datos se realiza byte a byte o carácter a carácter, además de que no permiten acceso aleatorio; los datos se procesan de manera lineal y son ideales para dispositivos que producen o consumen datos de manera secuencial. Ejemplo: Teclado, mouse, impresora o terminal serie. Por ejemplo, al escribir en un teclado, los caracteres se envían secuencialmente al sistema operativo.

2. **Diseña un programa que implemente un manejador de dispositivos sencillo para un dispositivo virtual de entrada.**

```py

class DispositivoEntradaVirtual:
    def __init__(self):
        self.buffer = []

    def leer_entrada(self, datos):
        self.buffer.append(datos)
        print(f"Los datos '{datos}' han sido leídos y almacenados en el buffer.")

    def obtener_entrada(self):
        if self.buffer:
            return self.buffer.pop(0)
        else:
            print("No hay datos disponibles en el buffer.")
            return None

    def limpiar_buffer(self):
        self.buffer = []
        print("Buffer limpio.")

dispositivo = DispositivoEntradaVirtual()

dispositivo.leer_entrada("Stream al album")
dispositivo.leer_entrada("de Seokjin")

datos1 = dispositivo.obtener_entrada()
print(f"Datos obtenidos: {datos1}")

datos2 = dispositivo.obtener_entrada()
print(f"Datos obtenidos: {datos2}")
datos3 = dispositivo.obtener_entrada()

dispositivo.limpiar_buffer()

```
---

## 4.2 Mecanismos y funciones de los manejadores de dispositivos

1. **Investiga qué es la interrupción por E/S y cómo la administra el sistema operativo. Escribe un ejemplo en pseudocódigo para simular este proceso.**

**Interrupción por E/S (Entrada/Salida)**  
La interrupción por E/S es un mecanismo que permite al sistema operativo gestionar la comunicación entre el procesador y los dispositivos de E/S, como discos, impresoras, teclados, entre otros. Cuando un dispositivo necesita la atención del procesador (por ejemplo, tras completar una operación de lectura o escritura), se genera una interrupción que detiene momentáneamente la ejecución del programa para que el sistema operativo atienda la solicitud.
Cuando un dispositivo de E/S está listo para enviar o recibir datos (por ejemplo, al finalizar una operación de lectura o escritura), envía una señal al procesador a través del hardware de interrupción. Esta señal es gestionada por un controlador de interrupciones. Al recibir la señal de interrupción, el procesador guarda el estado actual de la ejecución (es decir, el contexto del proceso en curso). Luego, cambia a una rutina especial llamada *manejador de interrupciones* o *rutina de servicio de interrupciones* (ISR). El sistema operativo, mediante el manejador de interrupciones, procesa la solicitud del dispositivo de E/S (por ejemplo, leer datos de un disco) y una vez completada la operación, dejando que el proceso continúe con su ejecución normal.Finalmente la solicitud de E/S, el sistema operativo restaura el contexto del proceso interrumpido, permitiendo que el proceso retome su ejecución desde el punto exacto donde fue interrumpido.

### Pseudocódigo para la Interrupción por E/S

```plaintext
Iniciar ejecución
Mientras (proceso en ejecución):
    Hacer algo (procesar datos, realizar cálculos)
    Si (hay una interrupción de E/S):
        Llamar a la rutina de interrupción
        Guardar el estado del proceso actual (contexto)
Identificar el dispositivo de E/S que genera la interrupción
Si (el dispositivo está listo para enviar/recibir datos):
    Procesar la operación de E/S (leer/escribir)
FinSi
Restaurar el estado del proceso interrumpido
Reanudar ejecución del proceso interrumpido
Retornar al proceso principal
Si (operación de E/S está completa):
    Enviar una señal de interrupción
FinSi

```

2. **Escribe un programa que utilice el manejo de interrupciones en un sistema básico de simulación.**

```c
#include <stdio.h>
#include <stdbool.h>
#include <unistd.h>

bool dispositivo_listo = false;
void dispositivo_es() {
    printf("Dispositivo: Manejando operaciones de E/S...\n");
    sleep(2); 
    dispositivo_listo = true; 
    printf("Dispositivo: Operación de E/S completada.\n");
}

void rutina_interrupcion() {
    printf("Interrupción recibida: Procesando E/S...\n");
    if (dispositivo_listo) {
        printf("Datos procesados para la E/S.\n");
        dispositivo_listo = false; 
    } else {
        printf("No hay datos para procesar.\n");
    }
}

int main() {
    printf("Iniciando simulación del sistema...\n");
    for (int i = 0; i < 5; i++) {
        printf("Proceso principal: Ejecutando tarea %d...\n", i + 1);
        sleep(1); 

        if (i == 2) {
            printf("Proceso principal: Iniciando operación de E/S.\n");
            dispositivo_es();
        }
        if (dispositivo_listo) {
            rutina_interrupcion();
        }
    }
    printf("Simulación finalizada.\n");
    return 0;
}

```
---

## 4.3 Estructuras de datos para manejo de dispositivos

### 1. **Investiga y explica qué es una cola de E/S. Diseña una simulación de una cola con prioridad.**

La **interrupción por E/S** (Entrada/Salida) es un mecanismo que permite al sistema operativo gestionar la comunicación entre el procesador y los dispositivos de E/S (como discos, impresoras, teclados, etc.). Cuando un dispositivo de E/S requiere la atención del procesador (por ejemplo, al completar una operación de lectura o escritura), se genera una interrupción que interrumpe la ejecución normal del programa para que el sistema operativo pueda atender la solicitud.
   - Cuando un dispositivo de E/S está listo para enviar o recibir datos (por ejemplo, al finalizar una operación de lectura o escritura), envía una señal al procesador a través del hardware de interrupción. Esta señal es gestionada por un controlador de interrupciones.
   - Al recibir la señal de interrupción, el procesador guarda el estado actual de la ejecución (es decir, el contexto del proceso en ejecución) y cambia a una rutina especial conocida como manejador de interrupciones o **rutina de servicio de interrupciones (ISR)**.
   - El sistema operativo, a través del manejador de interrupciones, procesa la solicitud del dispositivo de E/S (por ejemplo, leer los datos del disco) y permite que el proceso continúe con la ejecución una vez completada la operación.
   - Después de que la solicitud de E/S ha sido atendida, el sistema operativo restaura el contexto del proceso interrumpido y permite que continúe su ejecución desde el punto en que fue interrumpido.

```plaintext

    Iniciar ejecución
    Mientras (proceso en ejecución):
        Hacer algo (procesar datos, realizar cálculos)
        Si (hay una interrupción de E/S):
            Llamar a la rutina de interrupción
    Guardar el estado del proceso actual (contexto)
    Identificar el dispositivo de E/S que genera la interrupción
    Si (el dispositivo está listo para enviar/recibir datos):
        Procesar la operación de E/S (leer/escribir)
    FinSi
    Restaurar el estado del proceso interrumpido
    Reanudar ejecución del proceso interrumpido
    Retornar al proceso principal

    Si (operación de E/S está completa):
        Enviar una señal de interrupción
    FinSi
```

### 2. **Escribe un programa que simule las operaciones de un manejador de dispositivos utilizando una tabla de estructuras.**

```c

#include <stdio.h>
#include <string.h>

#define MAX_DISPOSITIVOS 100
typedef struct {
    int id;
    char nombre[50];
    char tipo[50];
} Dispositivo;

Dispositivo tablaDispositivos[MAX_DISPOSITIVOS];
int cantidadDispositivos = 0;

void agregarDispositivo(int id, const char *nombre, const char *tipo) {
    if (cantidadDispositivos < MAX_DISPOSITIVOS) {
        tablaDispositivos[cantidadDispositivos].id = id;
        strcpy(tablaDispositivos[cantidadDispositivos].nombre, nombre);
        strcpy(tablaDispositivos[cantidadDispositivos].tipo, tipo);
        cantidadDispositivos++;
        printf("Dispositivo agregado exitosamente.\n");
    } else {
        printf("La tabla de dispositivos está llena.\n");
    }
}

void eliminarDispositivo(int id) {
    int i, encontrado = 0;
    for (i = 0; i < cantidadDispositivos; i++) {
        if (tablaDispositivos[i].id == id) {
            encontrado = 1;
            break;
        }
    }
    
    if (encontrado) {
        for (int j = i; j < cantidadDispositivos - 1; j++) {
            tablaDispositivos[j] = tablaDispositivos[j + 1];
        }
        cantidadDispositivos--;
        printf("Dispositivo eliminado exitosamente.\n");
    } else {
        printf("Dispositivo no encontrado.\n");
    }
}

void mostrarDispositivos() {
    if (cantidadDispositivos == 0) {
        printf("No se encontraron dispositivos.\n");
    } else {
        printf("Lista de dispositivos:\n");
        for (int i = 0; i < cantidadDispositivos; i++) {
            printf("ID: %d, Nombre: %s, Tipo: %s\n", tablaDispositivos[i].id, tablaDispositivos[i].nombre, tablaDispositivos[i].tipo);
        }
    }
}

int main() {
    agregarDispositivo(1, "Impresora", "Salida");
    agregarDispositivo(2, "Teclado", "Entrada");
    agregarDispositivo(3, "Monitor", "Salida");
    
    printf("\n");
    mostrarDispositivos();
    
    printf("\nEliminando dispositivo con ID 2.\n");
    eliminarDispositivo(2);
    
    printf("\n");
    mostrarDispositivos();
    
    return 0;
}

```
## 4.4 Operaciones de Entrada/Salida

### 1. **Diseña un flujo que describa el proceso de lectura de un archivo desde un disco magnético. Acompáñalo con un programa básico que simule el proceso.**
**Solicitud de Lectura:**
- El sistema operativo recibe una solicitud para leer un archivo específico.
**Determinación de la Ubicación:**
- El sistema operativo consulta la tabla de archivos (File Allocation Table - FAT) para determinar la ubicación física del archivo en el disco.
**Posicionamiento del Cabezal:**
- El controlador de disco mueve el cabezal de lectura/escritura a la pista correcta en el disco magnético.
**Lectura de Datos:**
- El cabezal de lectura/escritura lee los datos desde el sector especificado en la pista.
**Transferencia de Datos:**
- Los datos leídos se transfieren al búfer de entrada/salida del sistema operativo.

**Procesamiento de Datos:**
- El sistema operativo procesa los datos y los entrega a la aplicación que hizo la solicitud de lectura.
```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char nombre[50];
    int pista;
    int sector;
    char contenido[256];
} Archivo;

typedef struct {
    Archivo archivos[10];
    int cantidadArchivos;
} Disco;

void leerArchivo(Disco *disco, const char *nombreArchivo) {
    for (int i = 0; i < disco->cantidadArchivos; i++) {
        if (strcmp(disco->archivos[i].nombre, nombreArchivo) == 0) {
            printf("Leyendo archivo: %s\n", disco->archivos[i].nombre);
            printf("Pista: %d, Sector: %d\n", disco->archivos[i].pista, disco->archivos[i].sector);
            printf("Contenido: %s\n", disco->archivos[i].contenido);
            return;
        }
    }
    printf("Archivo no encontrado.\n");
}

int main() {
    Disco disco;
    disco.cantidadArchivos = 2;
    strcpy(disco.archivos[0].nombre, "archivo1.txt");
    disco.archivos[0].pista = 1;
    disco.archivos[0].sector = 10;
    strcpy(disco.archivos[0].contenido, "Contenido del archivo 1");

    strcpy(disco.archivos[1].nombre, "archivo2.txt");
    disco.archivos[1].pista = 2;
    disco.archivos[1].sector = 20;
    strcpy(disco.archivos[1].contenido, "Contenido del archivo 2");
    leerArchivo(&disco, "archivo1.txt");

    return 0;
}

```


### 2. **Implementa un programa en Python, C o Java que realice operaciones de entrada/salida asíncronas usando archivos.**

```c
import asyncio
import aiofiles

async def leer_archivo(ruta_archivo):
    async with aiofiles.open(ruta_archivo, mode='r') as archivo:
        contenido = await archivo.read()
        print(f"Contenido del archivo {ruta_archivo}:\n{contenido}")

async def escribir_archivo(ruta_archivo, contenido):
    async with aiofiles.open(ruta_archivo, mode='w') as archivo:
        await archivo.write(contenido)
        print(f"Contenido escrito en el archivo {ruta_archivo}")

async def main():
    ruta_archivo = 'archivo.txt'
    contenido_escribir = "\n¡Hola Mundo Asíncrono!"

    # Escribir en el archivo
    await escribir_archivo(ruta_archivo, contenido_escribir)

    # Leer del archivo
    await leer_archivo(ruta_archivo)

# Ejecutar el bucle de eventos principal
asyncio.run(main())

```

## Integración

### 1. **Escribe un programa que implemente el algoritmo de planificación de discos "Elevator (SCAN)".**

```c 
#include <stdio.h>
#include <stdlib.h>

#define MAX 100
int encontrarProximo(int solicitudes[], int n, int cabezal, int direccion) {
    int indice = -1;
    int distanciaMinima = 999999;

    for (int i = 0; i < n; i++) {
        int distancia = abs(solicitudes[i] - cabezal);

        if (((direccion == 1 && solicitudes[i] >= cabezal) || (direccion == -1 && solicitudes[i] <= cabezal)) &&
            distancia < distanciaMinima) {
            distanciaMinima = distancia;
            indice = i;
        }
    }

    return indice;
}

void scan(int solicitudes[], int n, int cabezal, int direccion) {
    int distancia = 0;
    int actual = cabezal;

    printf("Orden de servicio: %d", cabezal);

    for (int i = 0; i < n; i++) {
        int indice = encontrarProximo(solicitudes, n, actual, direccion);

        if (indice == -1) {
            direccion = -direccion;
            indice = encontrarProximo(solicitudes, n, actual, direccion);
        }

        distancia += abs(solicitudes[indice] - actual);
        actual = solicitudes[indice];
        solicitudes[indice] = 999999; 
        printf(" -> %d", actual);
    }

    printf("\nDistancia total recorrida: %d\n", distancia);
}

int main() {
    int solicitudes[MAX], n, cabezal, direccion;

    printf("Introduce el número de solicitudes: ");
    scanf("%d", &n);

    printf("Introduce las solicitudes de pista: ");
    for (int i = 0; i < n; i++) {
        scanf("%d", &solicitudes[i]);
    }

    printf("Introduce la posición inicial del cabezal: ");
    scanf("%d", &cabezal);

    printf("Introduce la dirección inicial (1 para ascendente, -1 para descendente): ");
    scanf("%d", &direccion);

    scan(solicitudes, n, cabezal, direccion);

    return 0;
}

```

### 2. **Diseña un sistema que maneje múltiples dispositivos simulados (disco duro, impresora, teclado) y muestra cómo se realiza la comunicación entre ellos.**

```plaintext 
Inicio

Estructura Dispositivo
    nombre
    estado

Estructura DiscoDuro hereda de Dispositivo
    contenido

Estructura Impresora hereda de Dispositivo

Estructura Teclado hereda de Dispositivo

Función leerDisco(DiscoDuro disco)
    Imprimir "Leyendo disco: " + disco.nombre + " Contenido: " + disco.contenido
FinFunción

Función escribirDisco(DiscoDuro disco, contenido)
    disco.contenido = contenido
    Imprimir "Escribiendo en el disco: " + disco.nombre + " Nuevo contenido: " + disco.contenido
FinFunción

Función imprimirDocumento(Impresora impresora, documento)
    Imprimir "Imprimiendo en la impresora: " + impresora.nombre + " Documento: " + documento
FinFunción

Función recibirEntradaTeclado(Teclado teclado, entrada)
    Imprimir "Teclado " + teclado.nombre + " recibió la entrada: " + entrada
FinFunción

Lista dispositivos
cantidadDispositivos = 0

Función agregarDispositivo(dispositivo)
    Si cantidadDispositivos < 10 Entonces
        Añadir dispositivo a la lista dispositivos
        cantidadDispositivos = cantidadDispositivos + 1
        Imprimir "Dispositivo " + dispositivo.nombre + " agregado."
    Sino
        Imprimir "No se pueden agregar más dispositivos."
    FinSi
FinFunción

Función manejarComunicacion()
    // Crear instancias de dispositivos
    disco = Crear DiscoDuro con nombre "Disco Duro 1" y estado 1 y contenido "Archivo.txt"
    impresora = Crear Impresora con nombre "Impresora 1" y estado 1
    teclado = Crear Teclado con nombre "Teclado 1" y estado 1

    agregarDispositivo(disco)
    agregarDispositivo(impresora)
    agregarDispositivo(teclado)

    escribirDisco(disco, "Datos importantes")
    leerDisco(disco)
    imprimirDocumento(impresora, disco.contenido)
    recibirEntradaTeclado(teclado, "Hola Mundo!")
FinFunción

Función principal()
    manejarComunicacion()
FinFunción

Fin


Estructuras de los Dispositivos:Define estructuras para simular diferentes dispositivos.
Funciones para Operaciones de Dispositivos:Implementa funciones para realizar operaciones específicas de cada dispositivo.
Tabla de Dispositivos:Crea una tabla para mantener la información de todos los dispositivos conectados.
Manejo de Comunicación entre Dispositivos:Implementa una función de manejo central para coordinar la comunicación y las operaciones entre los dispositivos.

```

## Avanzados

### 1. Explica cómo los sistemas operativos modernos optimizan las operaciones de entrada/salida con el uso de memoria caché.

Optimización de E/S con Memoria Caché

Almacenamiento Temporal de Datos
La memoria caché es una memoria de alta velocidad que almacena temporalmente los datos más frecuentemente accedidos o recientemente utilizados. Esto reduce la necesidad de acceder repetidamente a los dispositivos de almacenamiento más lentos, como discos duros o SSDs.

 Lectura Anticipada (*Read-Ahead*)
Los sistemas operativos pueden predecir qué datos se necesitarán próximamente basándose en los patrones de acceso. Estos datos se leen de antemano y se almacenan en la caché, lo que permite acceder a ellos rápidamente cuando sean requeridos por el sistema o las aplicaciones.

Escritura Diferida (*Write-Back*)
En lugar de escribir los datos inmediatamente en el disco, se almacenan primero en la memoria caché. Las escrituras diferidas agrupan múltiples operaciones de escritura en una sola operación, reduciendo el número total de accesos al disco y mejorando la eficiencia.

Algoritmos de Reemplazo de Caché
Para gestionar eficientemente la memoria caché, los sistemas operativos emplean algoritmos de reemplazo como **LRU** (*Least Recently Used*) o **LFU** (*Least Frequently Used*). Estos algoritmos determinan qué datos deben ser eliminados de la caché cuando esta se llena, priorizando la retención de los datos más útiles.

Buffer Caché
El *buffer caché* se utiliza para almacenar temporalmente los datos que se están leyendo o escribiendo en el disco. Esto permite realizar operaciones de E/S de manera más eficiente al minimizar el tiempo de espera entre las operaciones de lectura y escritura.

Beneficios del Uso de Memoria Caché

**Reducción de la Latencia**
La memoria caché, al ser más rápida que los dispositivos de almacenamiento, reduce significativamente la latencia en las operaciones de E/S, proporcionando un acceso más rápido a los datos.

**Mejora del Rendimiento**
Al disminuir el número de accesos directos a los dispositivos de almacenamiento, la caché mejora el rendimiento general del sistema, permitiendo que las aplicaciones funcionen de manera más fluida y rápida.

**Mayor Eficiencia de los Recursos**
La caché optimiza el uso de los recursos del sistema al reducir el desgaste de los discos duros y disminuir el consumo de energía asociado con las operaciones de E/S.

