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


