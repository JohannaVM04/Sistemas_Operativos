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

## 1. Explica el concepto de protección basada en el lenguaje

Es un mecanismo de seguridad que utiliza el lenguaje de programación para determinar y controlar los permisos de acceso 
a recursos del sistema, esto implica que el sistema operativo y las aplicaciones que se ejecutan en él pueden restringir el acceso a ciertos recursos (como archivos, memoria, dispositivos) según el lenguaje en el que se escribió el código. Esto se logra mediante la implementación de políticas de seguridad que definen qué lenguajes tienen permisos para acceder a qué recursos. Este enfoque se basa en la idea de que ciertos lenguajes de programación son más seguros que otros debido a sus características y capacidades de control de acceso.

## 2. Proporciona un ejemplo de cómo un lenguaje como Java o Rust asegura la memoria y evita accesos no autorizados

### Gestión de la memoria en Java:
Java utiliza un recolector de basura para manejar la memoria de manera automática. El recolector de basura identifica y elimina los objetos que ya no son accesibles por el programa, liberando así la memoria ocupada por estos objetos.
 - Los objetos se crean en la memoria heap.
 - Durante la ejecución del programa, el recolector de basura monitorea los objetos.
 - Cuando un objeto ya no tiene referencias activas que lo apunten, el recolector de basura marca el objeto como basura.
 - En un ciclo posterior, el recolector de basura libera la memoria ocupada por estos objetos.

```java
public class Example {
      public static void main(String[] args) {
      Example example = new Example(); }
```
### Accesos no autorizados en Java
Los modificadores como private, protected y public controlan el acceso a variables y métodos.Esto restringe el acceso a miembros sensibles del objeto.

```java
public class Auto {
    private int idAuto;
    
    public int getIdAuto() {
        return idAuto;
    }
    
    public void setIdAuto(int numero) {
        this.IdAuto = numero;
    }
}
```

### Gestión de la memoria en Rust
Rust es conocido por su enfoque en la seguridad de la memoria sin necesidad de un recolector de basura como en Java, Rust utiliza un sistema de propiedad que garantiza que cada valor en el programa tiene un único propietario en un punto. Este sistema asegura que la memoria sea liberada de manera segura cuando ya no es necesaria. Cada valor en Rust tiene un propietario, solo puede haber un propietario a la vez, cuando el propietario sale de alcance, el valor se libera automáticamente.
```rust
fn main() {
let s = String::from("Hello, Rust!");}
```

Rust garantiza la seguridad del tipo en tiempo de compilación, evitando muchos errores comunes en otros lenguajes. 
 - Verificación en Tiempo de Compilación: Rust comprueba en tiempo de compilación que todas las operaciones sean seguras con respecto a los tipos.
 - Sistema de Tipos: Asegura que las variables solo se utilicen de maneras que sean seguras según su tipo declarado.
```rust
   fn main() {
   let x: i32 = 10;
   let y: f64 = 3.14;
}
```
## 3. Compara este enfoque con otros mecanismos de protección en sistemas operativos
| Característica                     | **Rust**                                        | **Control de Acceso Discrecional (DAC)**          | **Control de Acceso Obligatorio (MAC)**            | **Control de Acceso Basado en Roles (RBAC)**       |
|------------------------------------|-------------------------------------------------|---------------------------------------------------|----------------------------------------------------|----------------------------------------------------|
| **Seguridad de Memoria**           | Sistema de propiedad y préstamos previenen errores comunes y aseguran la memoria. | Depende de la implementación y del control del usuario propietario del recurso. | Políticas centralizadas y estrictas de acceso controlan la seguridad de la memoria. | No está directamente relacionado con la memoria, se enfoca en permisos basados en roles. |
| **Control de Acceso**              | Propiedad y préstamos aseguran que los recursos solo se acceden de manera segura. | El propietario del recurso decide los permisos de acceso. | Las políticas de acceso son determinadas por una autoridad central y no por los propietarios. | Los permisos se asignan a roles y los usuarios se asignan a esos roles. |
| **Prevención de Errores**          | Compilador fuerza reglas estrictas para evitar condiciones de carrera y otros errores de memoria. | La flexibilidad puede llevar a errores si no se gestiona adecuadamente. | Menor flexibilidad pero alta seguridad al seguir políticas centralizadas. | Simplifica la gestión de permisos pero depende de la correcta definición de roles. |
| **Flexibilidad**                   | Alta flexibilidad en el manejo de la memoria sin comprometer la seguridad. | Alta flexibilidad pero requiere una gestión cuidadosa. | Menor flexibilidad debido a políticas estrictas. | Moderada flexibilidad, bien adaptado para grandes organizaciones. |
| **Facilidad de Uso**               | Requiere aprendizaje del modelo de propiedad y préstamos, pero es potente una vez dominado. | Fácil de usar pero requiere manejo cuidadoso de permisos. | Más complejo de implementar y gestionar debido a políticas centralizadas. | Fácil de gestionar en entornos grandes con muchos usuarios y roles. |
| **Aplicación Típica**              | Desarrollo de software seguro, sistemas embebidos, aplicaciones de alta concurrencia. | Sistemas operativos, aplicaciones con múltiples usuarios. | Entornos de alta seguridad como gubernamentales y militares. | Grandes organizaciones, sistemas empresariales con múltiples niveles de acceso. |


# Ejercicio 6: Validación y amenazas al sistema
## 1. Investiga y describe al menos tres tipos de amenazas comúnes (por ejemplo, malware, ataques de fuerza bruta, inyección de código)

### 1. Malware
Es una abreviación para Malicious software, es un término que utilizamos para referirnos a software que puede causar daño a un sistema, dispositivo o red. Existen varios tipos pero los más comúnes de malware son los **virus**, son programas que se replican y se van insertando en otros programas u otros archivos. Los **troyanos**, son programas que parecen archivos legítimos pero cuando se ejcutan, trabajan con funcionan maliciosas. Y por último el **ransomware**, que es un malware que bloque el acceso a los datos o al sistema y empieza a pedir un rescate para restaurar ela cceso al sistema.

Un ejemplo clásico de un troyano es que te manden un correo electrónico con un archivo pdf y sea un troyano ( broma entre amigos).

### 2. Ataques de Fuerza Bruta
Es un método de fuerza bruta que en el que el atacante intenta acceder al sistema probando combinaciones de posibles credenciales hasta encontrar la correcta. Normalmente estos ataques se hacen con scripts o herramientas automatizadas que prueban las combinaciones, y, aunque es lento, también es muy efectivo si las contraseñas son cortas y sencillas.

El ejemplo puede ser que un atacante use una herramienta de fuerza bruta para acceder a la cuenta de usuario en un sitio web, hasta que encuentre la combinacion de usuario y contraseña correcta.

### Inyección de Código
La inyección de código es una técnica donde el atacante modifica el código, agregando líneas que van a cambiar el comportamiento de este.
Las dos inyecciones de código más comúnes son para bases de datos y páginas web:

 - Inyección en SQL: el atacante genera una consulta SQL para acceder o manipular la base de datos.
 - XSS: el atacante inyecta scripts maliciosos en páginas web vistas por otros usuarios.

Un atacante puede utilizar vulnerabilidad de SQl para acceder a base de datos de un sitio web y extraer información sensible de los usuarios

## 2. Explica los mecanismos de validación como autenticación multifactor y control de integridad.

La Autentificación Multifactor (MFA) es un proceso de seguridad que requiere que los usuarios hagan dos o más verificaciónes antes de acceder a la cuenta o sistema (por ejemplo, en la autentificación de Google, se manda un número a tu celular para que lo ingreses después de ingresar tu correo y contraseña). Este método añade una capa adicional de seguridad.

El control de integridad son las medidas o procesos que se agregan a un sistema para asegurar que los datos y sistemas permanezcan en un estado consistente y sin alteraciones no autorizadas. Esto incluye la protección contra modificaciones, eliminaciones y la corrupción de datos. Los métodos más comúnes son el **Checksums y Hshes**, estos utilizan algoritmos para generar un valor único basado en los datos originales. Si los datos cambian, el valor generado tambíen cambiará indicando una posible alteración, otro método es la **copia de seguridad y restauración**, se van haciendo copias de seguridad regulares de datos y sistemas para poder restaurarlos en caso de corrupción o pérdida (como la copia de seguridad de google fotos); tambíen está el **monitoreo constante**, estos implementan sistemas que vigilan constantemente los cambios en los datos y alertan sobre actividades sospechosas(como los cambios de contraseña, movimientos del banco).

## 3. Diseña un esquema de validación para un sistema operativo con múltiples usuarios.
```plaintext
procedimiento Principal()
    mientras Verdadero
        usuario <- ObtenerEntradaUsuario("Ingrese su nombre de usuario: ")
        contrasena <- ObtenerEntradaUsuario("Ingrese su contraseña: ")
        
        si ValidarCredenciales(usuario, contrasena) entonces
            si RealizarAutenticacionMultifactor(usuario) entonces
                sesion <- CrearSesion(usuario)
                Mostrar("Bienvenido, " + usuario)
                mientras sesion.estaActiva
                    comando <- ObtenerEntradaUsuario("Ingrese un comando: ")
                    si ValidarComando(comando) entonces
                        EjecutarComando(sesion, comando)
                    sino
                        Mostrar("Comando no válido")
                    fin si
                fin mientras
                TerminarSesion(sesion)
            sino
                Mostrar("Autenticación multifactor fallida.")
            fin si
        sino
            Mostrar("Credenciales inválidas.")
        fin si
    fin mientras
fin procedimiento

procedimiento ValidarCredenciales(usuario, contrasena)
    contrasenaAlmacenada <- ObtenerContrasenaAlmacenada(usuario)
    return Hash(contrasena) == contrasenaAlmacenada
fin procedimiento

procedimiento RealizarAutenticacionMultifactor(usuario)
    otp <- GenerarOTP(usuario)
    EnviarOTPAlUsuario(usuario, otp)
    otpIngresado <- ObtenerEntradaUsuario("Ingrese el código de autenticación: ")
    return otp == otpIngresado
fin procedimiento

procedimiento CrearSesion(usuario)
    sesion <- Nueva Sesion(usuario)
    sesion.estaActiva <- Verdadero
    sesion.tiempoInicio <- TiempoActual()
    return sesion
fin procedimiento

procedimiento TerminarSesion(sesion)
    sesion.estaActiva <- Falso
    sesion.tiempoFin <- TiempoActual()
    RegistrarDetallesDeSesion(sesion)
fin procedimiento
```
# Ejercicio 7 : Cifrado
## 1. Define los conceptos de cifrado simétrico y asimétrico

El **cifrado simétrico** es una técnica donde se utiliza una única clave tanto para cifrar y descifrar la información. Esto quiere decir que el emisor y receptor debe tener la misma clave y mantenerla en secreto para asegurar la comunicación. Esta técnica es más rápida que un cifrado asimétrico, pero es menos segura porque necesita existir un alto nivel de confidencilidad, haciendo el sistema vulnerable. 

El **cifrado asimétrico** utiliza dos claves, una clave única y una privada. La clave pública se usa para cifrar los datos y la clave privada corresponde para descifrarlos. 
En esta técnica, la clave privada debe mantenerse en secreto, por lo general se utiliza para la transmición de datos seguros y en la autenticación de usuarios, pero no es práctico si estamos trabajando con alto volúmenes de datos, además de que es lento y exige muchos recursos en comparación del cifrado simétrico;ejemplos del cifrado 
asimétrico es el RSA, DSA y ECC.

## 2. Proporciona un ejemplo práctico de cada tipo de cifrado aplicado de sistemas operativos.

**Bitlocker** es una característica de cifrado de disco completo para Windows, utiliza **cifrado simétrico** para proteger los datos almacenados en disco duro y memorias USB. Al inciiarlo, el sistema genera una clave simétrica,todos los datos se cifran utilizando AES, con una clave de 128 a 256 bits, para acceder a los datos cifrados, se necesita de una clave de recuperación o una autentificación multifactor como una tarjeta inteligente o una contraseña.

**SSH** es un protocolo que permite acceder de manera más segura a un sistema remoto. Utiliza cifrado asimétrico para establecer una conexión segura. El usuario genera un par de clave usa un comando "ssh-keygen" El suaurio copia la clave pública al servidor remoto donde están las contraseñas, después, al inciar una sesión SSH, el cliente usa la clave pública para cifrar una clave de sesión que solo puede ser descifrada por la clave privada del usuario.

## 3. Simula el proceso de cifrado y descifrado de un archivo con una clave dada

### Proceso de cifrado
```plaintext
procedimiento CifrarArchivo(rutaArchivoEntrada, rutaArchivoSalida, clave)
    datos <- LeerArchivo(rutaArchivoEntrada)
    iv <- GenerarIVAleatorio()
    datosCifrados <- AES_Cifrar(datos, clave, iv)
    EscribirArchivo(rutaArchivoSalida, iv + datosCifrados)
fin procedimiento

procedimiento AES_Cifrar(datos, clave, iv)
    cifrado <- AES_InicializarCifrador(clave, iv, "CBC")
    datosCifrados <- cifrado.Cifrar(datos)
    retornar datosCifrados
fin procedimiento
```

### Proceso de descifrado
```plaintext
procedimiento DescifrarArchivo(rutaArchivoEntrada, rutaArchivoSalida, clave)
    contenidoCifrado <- LeerArchivo(rutaArchivoEntrada)
    iv <- ExtraerIV(contenidoCifrado)
    datosCifrados <- ExtraerDatosCifrados(contenidoCifrado)
    datos <- AES_Descifrar(datosCifrados, clave, iv)
    EscribirArchivo(rutaArchivoSalida, datos)
fin procedimiento

procedimiento AES_Descifrar(datosCifrados, clave, iv)
    descifrado <- AES_InicializarDescifrador(clave, iv, "CBC")
    datos <- descifrado.Descifrar(datosCifrados)
    
    retornar datos
fin procedimiento
```
