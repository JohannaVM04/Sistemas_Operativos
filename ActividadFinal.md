# Ejercicio 1: Concepto y noción de archivo real y virtual
## 1. Define el concepto de archivo real y archivo virtual

Primero debemos saber que un archivo en S.O. es una forma de representar y gestionar los datos de un sistema. Un **archivo real** es 
un archivo que existe físicamente en el almacenamiento del sistema, contiene datos, programas o cualquier otro tipo de información que se almacena en un disco duro o cualquier otro medio de almacenamiento,
tiene tamaño fijo en bytes y ocupa un espacio físico en el dispositivo de almacenamiento. Por otro lado, un **archivo virtual** es una representación lógica de un archivo que puede no existir físicamente en
el almacenamiento. En lugar de referirse a un espacio físico específico, un archivo virtual se refiere a un conjunto de datos que el sistema operativo presenta al usuario o a las aplicaciones como si fuera 
un archivo único. Los archivos virtuales pueden ser creados mediante la combinación de múltiples archivos reales o mediante la asignación dinámica de espacio de almacenamiento.

## 2. Proporciona ejemplos de cómo los sistemas operativos manejan archivos reales y virtuales
### 1. Archivos Temporales
 - **Archivo Real:** Cuando un programa necesita almacenar datos temporalmente, puede crear un archivo real en el disco duro. Este archivo existe físicamente y se elimina una vez que ya no es necesario.
 - **Archivo Virtual:** Algunos sistemas operativos permiten el uso de archivos virtuales temporales que se almacenan en la memoria RAM y no en el disco duro, mejorando el rendimiento al evitar operaciones de E/S (entrada/salida) en disco.
### 2. Paginación de memoria
 - **Archivo Real:** La paginación es una técnica de gestión de memoria en la que el sistema operativo mueve páginas de datos entre la RAM y el almacenamiento secundario (disco duro). 
Los archivos de paginación en el disco duro son archivos reales que contienen estas páginas.
 - **Archivo Virtual:** El sistema operativo presenta estos segmentos de memoria como un espacio de direcciones virtual continuo a las aplicaciones, aunque físicamente pueden estar
dispersos en el disco duro y la RAM.
### 3. Montaje de dispositivos
 - **Archivo Real:** Un dispositivo de almacenamiento como un disco duro externo tiene archivos reales que se pueden acceder directamente.
 - **Archivo Virtual:** Algunos sistemas operativos permiten montar dispositivos o recursos de red como directorios virtuales. Por ejemplo, se puede montar un recurso compartido de red SMB como una carpeta en el sistema local, creando una vista virtual de los archivos que realmente residen en otro servidor.
### 4. Sistemas de archivos virtuales
 - **Archivo Real:** Los archivos almacenados en un disco duro o una unidad SSD son archivos reales que existen físicamente.
 - **Archivo Virtual:** El VFS permite a los sistemas operativos acceder a diferentes tipos de sistemas de archivos (como ext4, NTFS, FAT32) de manera uniforme. Los archivos virtuales en un VFS son abstracciones que el sistema operativo trata de forma consistente, sin importar dónde se almacenen físicamente.

## 3. Explica un caso práctico donde un archivo virtual sea más util que un archivo real

Suponiendo que en una empresa se necesitan usar varios sistemas operativos y diferentes tipos de almacenamiento. Algunos servidores trabajan con Linux(ext4), con Windows(NTFS) y además con dispositivos externos con sistemas FAT32. La gestión de datos y archivos a través de estos distintos sistemas puede ser compleja y propensa a sufrir errores.

Si hacemos esto con archivos reales sin un VFS, cada sistema operativo tiene que gestionar directamente los archivos reales en cada sistema de archivos diferentes, lo cual se traduce en utilizar diferentes comandos, herramientas, gestionar permisos y características específicas en cada sistema de archivos, aparte de que se aumentaría la complejidad para sincronizar datos entre sistemas diferentes.

Esto se soluciona con archivos virtuales, ya que permite a los administradores crear una capa de abstracción que permite acceder a archivos de diferentes sistemas de archivos de manera uniforme. Es más simple ya que los usuarios y aplicaciones pueden acceder a los archivos sin preocuparse por el tipo de sistema de archivos en el que están almacenado físicamente. También es más flexible ya que puede montar dispositivos de almacenamiento con diferentes sistemas de archivos de forma trasparente y es más eficaz, porque el VFS puede optimizar el acceso y la gestión de archivos. Otras ventajas de usar archivos virtuales es la uniformidad en la interfaz y minimiza la posibilidad de errores al unificar la forma en que se manejan los archivos

# Ejercicio 2: Componentes de un sistema de archivos
## 1. Identifica los componentes clave de un sistema de archivos(por ejemplo metadatos, tablas de asignación, etc).

1. **Metadatos:** en un contexto de sistemas de archivos, son datos sobre datos, incluyen nombre del archivo, tamaño, fecha de creación, modificación, permisos de acceso y propietario. Los metadatos no contienen los datos reales del archivo, pero cuentan con información suficiente para poder manejarlo.
   
2. **Tablas de asignación:**
- Tablas de asignación de archivos(FAT): Es una tabla que mapea clústeres (bloques de almacenamiento) en el disco a archivos específicos además de registrar qué bloques están libres y cuales en uso; ejemplos de estos son FAT16 y FAT32.
- Tablas de Asignación de Inodos: los inodos contienen toda la información sobre un archivo excepto su nombre y los datos reales. Se utiliza para encontrar los datos asociados a un archivo. Estos se utilizan en sistemas como ext4 para Linux.
  
3. **Inodos:** podemos definirlos como una estructura de datos que almacena información sobre un archivo o directorio. Estos contienen información como el tamaño, permisos, quién es el propietario, marcas de tiempo u punteros a los bloques, generalmente utilizados en Unix/Linux
   
4. **Directorios:** son estructuras jerárquicas que organizan los archivos en el sistema. También puede ser un tipo de dato especial que referencia a otros archivos e inodos. Cada directorio contiene una lista de archivos y subdirectorios, permitiendo una organización estructurada y lógica de la información

5. **Bloques de datos:** unidades básicas de almacenamiento en un sistema de archivos, se encarga de la asignación y gestión de bloques de datos, asegurando que estos serán guardados y recuperados sin fallas.

6. **Mecanismos de integridad:** existen diferentes mecanismos como las sumas de verificación, el Cyclic Redundancy Check y algoritmos de detección de errores que buscan que los datos no se corrompan durante un proceso de almacenamiento o la trasferencia 

7. **Administración de Almacenamiento secundario:** son técnicas y algoritmos que se utilizan para gestionar el espacio en disco; se encarga de la asignación de bloques a archivos, recuperación de bloques libres y fragmentación.

8. **Métodos de acceso:** es la forma en que se acceden y recuperan datos en los sistemas de archivo y pueden ser de dos formas:
- Acceso secuencial: los datos se leen/escriben en orden específico
- Acceso aleatorio: se leen/escriben en cualquier orden (por lo general esto hace que sea más rápido y flexible)
  
## 2. Crea un cuadro comparativo de cómo estos componentes funcionan en sistemas como EXT4 y NTFS
| Componente | EXT4 | NTFS |
|----------|----------|----------|
|Metadatos|Utiliza inodos para almacenar información sobre archivos|Utiliza MFT (Master File Table) para almacenar metadatos.|
|Tablas de asiganción|Tabla de inodos para mapear archivos a bloques de datos.|MFT con registros detallados para cada archivo.|
|Inodos|Cada archivo tiene un inodo único con información detallada.|NTFS no utiliza inodos; toda la información se almacena en la MFT.|
|Directorios|Estructura jerárquica con entradas de directorio apuntando a inodos|Estructura jerárquica con entradas de directorio en la MFT.|
|Bloques de datos|Tamaño configurable (por ejemplo, 4KB).|Usa clústeres|
|Mecanismos de integridad|Journaling para minimizar la corrupción de datos.|Journaling y Recovery Log para recuperación de fallos.|
|Administrador de almacenamiento|Extents para asignación de bloques continuos y reducir la fragmentación.|Extents y B-trees para manejar grandes archivos y reducir la fragmentación.|
|Métodos de acceso|Soporte para acceso secuencial y aleatorio.|Soporte para acceso secuencial y aleatorio, con características avanzadas como la compresión y el cifrado.|

## 3. Escribe las ventajas y desventajas de cada sistema basado en sus componentes
### EXT4
 Ventajas:
 - Journaling: Mejora la integridad de los datos al registrar las operaciones antes de aplicarlas.
 - Extents: Reduce la fragmentación y mejora el rendimiento al asignar bloques continuos a archivos grandes.
 - Compatibilidad: Ampliamente soportado en sistemas basados en Linux.
 - Eficiencia: Buena gestión del espacio y rápido acceso a los datos.
   
 Desventajas:
 - Journaling: Puede añadir una sobrecarga de E/S, lo que puede afectar el rendimiento en algunos casos.
 - Tamaño Máximo del Archivo: Aunque soporta archivos grandes, tiene límites comparados con otros sistemas de archivos más avanzados.
 - Fragmentación: A pesar de los extents, puede sufrir fragmentación con el tiempo, aunque menos que sistemas más antiguos como ext3.

### NTFS
 Ventajas:
 - Seguridad: Soporte avanzado para permisos y cifrado de archivos.
 - Journaling: Minimiza la corrupción de datos y facilita la recuperación tras fallos.
 - Compresión y Cifrado: Funciones integradas que permiten optimizar el uso del espacio y asegurar los datos.
 - Flexibilidad: Soporta grandes volúmenes de almacenamiento y archivos de gran tamaño.
   
 Desventajas:
 - Compatibilidad: Menos soporte nativo en sistemas que no sean Windows.
 - Complejidad: Mayor complejidad en la administración de permisos y características avanzadas.
 - Sobrecarga: Las funcionalidades avanzadas como el journaling y el cifrado pueden introducir una sobrecarga adicional en el sistema.

# Ejercicio 3: Organización lógica y física de archivos
## 1. Diseña un árbol jerárquico que represente la organización lógica de directorios y subdirectorios

- /
  - bin
    - listar
    - consola
      
  - arranque
    - núcleo_linux
    - imagen_init
      
  - dispositivos
    - disco_sda
    - terminal_tty
      
  - configuración
    - contraseñas
    - tabla_sistema_archivos
      
  - usuarios
    - usuario1
      - documentos
      - imágenes
        
    - usuario2
      - documentos
      - imágenes
        
  - librerías
    - libc.so.6
    - libm.so.6
      
  - medios
    - cdrom
    - usb
  - montaje
    - respaldo
    - datos
      
  - opcional
    - software1
    - software2
      
  - procesos
    - 1
    - 2
      
  - raíz
    - documentos
      
  - ejecución
    - usuarios_activos
      
  - binarios de sistema
    - configurar_red
    - apagar_sistema
      
  - servicios
    - web
    - ftp
  - temporal
    
  - usuarios_sistema
    - bin
      - editor_vim
      - compilador_gcc
        
    - librerías
      - libpython2.7.so
      - libperl.so
    
    - local
      - bin
      - librerías
        
    - compartir
      - documentos
      - manuales
        
  - variable
    - registros
    - correo
    - cola
   
## 2. Explica cómo se traduce la dirección lógica a la dirección física en un disco
1. Paso 1: Dirección Lógica
La dirección lógica es una referencia abstracta a una ubicación de datos utilizada por los programas y el sistema operativo. Esta dirección no está vinculada directamente a una ubicación física en el disco. Las direcciones lógicas son manejadas por el sistema de archivos y proporcionan una forma conveniente y unificada de referirse a los datos.

2. Paso 2: Sistema de Archivos y Tabla de Asignación
El sistema de archivos utiliza estructuras de datos como la Tabla de Asignación de Archivos (FAT) en FAT32 o la Tabla de Inodos en ext4 para mapear las direcciones lógicas a direcciones físicas. Estas tablas contienen información sobre qué bloques de datos en el disco corresponden a cada archivo o dirección lógica.

3. Paso 3: Dirección Física
La dirección física es la ubicación real en el disco donde se almacenan los datos. Esta dirección se refiere a un cilindro, pista y sector específicos en el disco, o a un clúster en un SSD.

4. Paso 4: Proceso de Traducción
 - Solicitudes de Lectura/Escritura: Cuando una aplicación necesita acceder a datos, envía una solicitud al sistema operativo utilizando una dirección lógica.
 - Sistema de Archivos: El sistema de archivos recibe la solicitud y consulta la Tabla de Asignación o Tabla de Inodos para determinar qué bloques físicos contienen los datos correspondientes a la dirección lógica.
 - Controlador de Disco: Una vez identificados los bloques físicos, el sistema de archivos envía la solicitud al controlador de disco, que se encarga de interactuar con el hardware del disco.
 - Acceso al Disco: El controlador de disco traduce la dirección física a comandos específicos para el hardware, indicando al disco que lea o escriba en las ubicaciones físicas especificadas.

## 3. Proporciona un ejemplo práctico de cómo un archivo se almacena físicamente

1. Dirección Lógica: Un archivo tiene la dirección lógica 1000.
2. Tabla de Asignación: La tabla muestra que la dirección lógica 1000 corresponde a los bloques físicos Cilindro 4, Pista 5, Sector 6 en un disco HDD.
3. Controlador de Disco: El controlador traduce estos valores a comandos para posicionar el cabezal de lectura/escritura en el lugar correcto del disco.
4. Acceso al Disco: Los datos se leen o escriben en la ubicación física especificada, completando la operación solicitada por la aplicación.

# Ejercicio 4: Mecanismo de acceso a los archivos
## 1. Define los diferentes mecanismos de acceso
Los mecanismos de acceso son utilizados para el manejo de la seguridad y gestión de los sistemas. 

1. Control de Acceso Discrecional (DAC): es un mecanismo que teniendo un propietario de un recurso (esto puede ser un archivo o directorio) decide a quien le va a dar permisos y quien puede acceder a él. Este mecanismo es flexible y puedes tener un control por parte del propietario, el problema es que es difícil de manejar cuando hay muchos usuarios y recursos que manejar.
2. Control de acceso basado en roles (RBAC): En este, los permisos se asignan a los usuarios dependiendo qué rol se les fue asignado. Usarlo simplifica el cómo se dan los permisos y es más útil cuando tenemos muchos usuarios; el problema que puede presentar es que ya tiene los roles predefinidos, por lo que es menos flexible que el DAC
3. Control de acceso Obligatorio (MAC): en este mecanismo, los accesos se van dando según las políticas de seguridad y ya no se pueden modificar por los propietarios; utilizar este mecanismo tiene un alto nivel de seguridad y consistencia en la aplicación de políticas, el problema es que tiene menor flexibilidad, además de que es más complejo de implementar y gestionar.
4. Control de Acceso Biométrico: utilizamos características físicas de una persona como huellas dactilares para poder autentificar su identidad. Este mecanismo es de alta precisión y dificultad de falsificación, lo malo es que puede ser costoso y generar preocupaciones sobre la privacidad.
5. Control de acceso por tarjeta RFID: se utilizan tarjetas RFID para identificar a los usuarios, es mucho más sencillo de usar y se puede integrar con otros sistemas de seguridad, lo malo es que es vulnerable a la clonación o robo.
6. Control de acceso por contraseña: en este mecanismo utilizamos contraseñas para autentificar a los usuarios, es simple de usar, pero las contraseñas son vulnerables a ataques.

## 2. Escribe un pseudocógigo que muestre cómo acceder a:
 - Un archivo secuencialmente
    ```plaintext
    Abrir archivo en modo lectura como archivo
    Mientras archivo NO haya llegado al final
    Leer línea del archivo
    Mostrar línea
    FinMientras
    Cerrar archivo
    ```
 - Un archivo mediantemente su posición
     ```plaintext
     Abrir archivo en modo lectura como archivo
     Posicionar cursor del archivo en la posición P (por ejemplo, byte P)
     Leer N bytes desde la posición actual
     Mostrar datos leídos
     Cerrar archivo
    ```
 - Un archivo utilizando un índice
    ```plaintext
       Abrir archivo de índice en modo lectura como índice
       Abrir archivo de datos en modo lectura como archivo
       Leer índice y encontrar la posición P correspondiente al registro deseado
       Posicionar cursor del archivo de datos en posición P
       Leer registro desde la posición P
       Mostrar datos del registro
       Cerrar archivo de índice
       Cerrar archivo de datos
    ```

## 3. Compara las ventajas de cada mecanismo dependiendo del caso de uso

| Mecanismo                 | Ventajas                                                                               | Casos de Uso                                                                               |
|---------------------------|----------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| **Acceso Secuencial**     | Simplicidad, eficiencia en lecturas completas, bajo costo de configuración              | Procesamiento de logs, lectura de archivos completos, análisis de datos históricos          |
| **Acceso por Posición**   | Acceso rápido a datos específicos, flexibilidad                                         | Bases de datos de registros, edición de archivos, lectura de índices o tablas de contenidos |
| **Acceso Utilizando un Índice** | Eficiencia en búsqueda y recuperación, gestión eficiente de datos                     | DBMS, aplicaciones de búsqueda de datos, grandes volúmenes de datos                        |

# Ejercicio 5 : Modelo jerárquico y mecanismos de recuperación en caso de falla
