# Actividad 1: Listar dispositivos conectados
## Ejecucion de los comandos

johanna-velasco@johanna-velasco-VirtualBox:~$ lsblk
NAME   MAJ:MIN RM   SIZE RO TYPE MOUNTPOINTS
loop0    7:0    0     4K  1 loop /snap/bare/5
loop1    7:1    0 316,5M  1 loop /snap/code/173
loop2    7:2    0 316,5M  1 loop /snap/code/174
loop3    7:3    0  63,7M  1 loop /snap/core20/2434
loop4    7:4    0  73,9M  1 loop /snap/core22/1663
loop5    7:5    0  73,9M  1 loop /snap/core22/1722
loop6    7:6    0 269,8M  1 loop /snap/firefox/4793
loop7    7:7    0 273,6M  1 loop /snap/firefox/5187
loop8    7:8    0  11,1M  1 loop /snap/firmware-updater/147
loop9    7:9    0 505,1M  1 loop /snap/gnome-42-2204/176
loop10   7:10   0  10,7M  1 loop /snap/firmware-updater/127
loop11   7:11   0  10,7M  1 loop /snap/snap-store/1218
loop12   7:12   0  10,8M  1 loop /snap/snap-store/1244
loop13   7:13   0  44,3M  1 loop /snap/snapd/23258
loop14   7:14   0  38,8M  1 loop /snap/snapd/21759
loop15   7:15   0  91,7M  1 loop /snap/gtk-common-themes/1535
loop16   7:16   0   568K  1 loop /snap/snapd-desktop-integration/253
loop17   7:17   0   500K  1 loop /snap/snapd-desktop-integration/178
sda      8:0    0    25G  0 disk 
├─sda1   8:1    0     1M  0 part 
└─sda2   8:2    0    25G  0 part /
sr0     11:0    1  1024M  0 rom  
johanna-velasco@johanna-velasco-VirtualBox:~$ lsusb
Bus 001 Device 001: ID 1d6b:0001 Linux Foundation 1.1 root hub
Bus 001 Device 002: ID 80ee:0021 VirtualBox USB Tablet
Bus 002 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub
johanna-velasco@johanna-velasco-VirtualBox:~$ lspci
00:00.0 Host bridge: Intel Corporation 440FX - 82441FX PMC [Natoma] (rev 02)
00:01.0 ISA bridge: Intel Corporation 82371SB PIIX3 ISA [Natoma/Triton II]
00:01.1 IDE interface: Intel Corporation 82371AB/EB/MB PIIX4 IDE (rev 01)
00:02.0 VGA compatible controller: VMware SVGA II Adapter
00:03.0 Ethernet controller: Intel Corporation 82540EM Gigabit Ethernet Controller (rev 02)
00:04.0 System peripheral: InnoTek Systemberatung GmbH VirtualBox Guest Service
00:05.0 Multimedia audio controller: Intel Corporation 82801AA AC'97 Audio Controller (rev 01)
00:06.0 USB controller: Apple Inc. KeyLargo/Intrepid USB
00:07.0 Bridge: Intel Corporation 82371AB/EB/MB PIIX4 ACPI (rev 08)
00:0b.0 USB controller: Intel Corporation 82801FB/FBM/FR/FW/FRW (ICH6 Family) USB2 EHCI Controller
00:0d.0 SATA controller: Intel Corporation 82801HM/HEM (ICH8M/ICH8M-E) SATA Controller [AHCI mode] (rev 02)
johanna-velasco@johanna-velasco-VirtualBox:~$ lspci
00:00.0 Host bridge: Intel Corporation 440FX - 82441FX PMC [Natoma] (rev 02)
00:01.0 ISA bridge: Intel Corporation 82371SB PIIX3 ISA [Natoma/Triton II]
00:01.1 IDE interface: Intel Corporation 82371AB/EB/MB PIIX4 IDE (rev 01)
00:02.0 VGA compatible controller: VMware SVGA II Adapter
00:03.0 Ethernet controller: Intel Corporation 82540EM Gigabit Ethernet Controller (rev 02)
00:04.0 System peripheral: InnoTek Systemberatung GmbH VirtualBox Guest Service
00:05.0 Multimedia audio controller: Intel Corporation 82801AA AC'97 Audio Controller (rev 01)
00:06.0 USB controller: Apple Inc. KeyLargo/Intrepid USB
00:07.0 Bridge: Intel Corporation 82371AB/EB/MB PIIX4 ACPI (rev 08)
00:0b.0 USB controller: Intel Corporation 82801FB/FBM/FR/FW/FRW (ICH6 Family) USB2 EHCI Controller
00:0d.0 SATA controller: Intel Corporation 82801HM/HEM (ICH8M/ICH8M-E) SATA Controller [AHCI mode] (rev 02)
johanna-velasco@johanna-velasco-VirtualBox:~$ dmesg | grep usb
dmesg: fallo al leer el «buffer» del núcleo: Operación no permitida
## Preguntas

- ¿Qué tipos de dispositivos se muestran en la salida de `lsblk`?
  El comando lsblk muestra información sobre los dispositivos de bloques en el sistema. En la salida proporcionada, se pueden ver los siguientes tipos de dispositivos:
loop: Dispositivos de bucle, generalmente utilizados para montar imágenes de archivos y sistemas de archivos temporales (e.g., /snap/...).
sda: Dispositivo de disco duro o SSD principal (/dev/sda) con dos particiones (sda1 y sda2).
sr0: Dispositivo de CD-ROM o unidad de disco óptico.

- ¿Cuál es la diferencia entre `lsusb` y `lspci`?
 lsusb: Este comando lista todos los dispositivos USB conectados al sistema. Muestra detalles como el ID del fabricante y el ID del producto, así como el tipo de dispositivo USB (e.g., hubs, ratones, teclados).
  lspci: Este comando lista todos los dispositivos PCI conectados al sistema. Muestra detalles como el nombre del fabricante, el tipo de dispositivo y su función (e.g., controladores gráficos, tarjetas de red).
  
- ¿Qué información adicional proporciona `dmesg | grep usb`?
  Proporciona detalles sobre la detección y el estado de los dispositivos USB, problemas de hardware, errores y otros eventos importantes durante el arranque y el funcionamiento del sistema.

# Actividad 2: Verificar dispositivos de almacenamiento

## Comandos 
johanna-velasco@johanna-velasco-VirtualBox:~$ fdisk -l
fdisk: no se puede abrir /dev/loop0: Permiso denegado
fdisk: no se puede abrir /dev/loop1: Permiso denegado
fdisk: no se puede abrir /dev/loop2: Permiso denegado
fdisk: no se puede abrir /dev/loop3: Permiso denegado
fdisk: no se puede abrir /dev/loop4: Permiso denegado
fdisk: no se puede abrir /dev/loop5: Permiso denegado
fdisk: no se puede abrir /dev/loop6: Permiso denegado
fdisk: no se puede abrir /dev/loop7: Permiso denegado
fdisk: no se puede abrir /dev/sda: Permiso denegado
fdisk: no se puede abrir /dev/loop8: Permiso denegado
fdisk: no se puede abrir /dev/loop9: Permiso denegado
fdisk: no se puede abrir /dev/loop10: Permiso denegado
fdisk: no se puede abrir /dev/loop11: Permiso denegado
fdisk: no se puede abrir /dev/loop12: Permiso denegado
fdisk: no se puede abrir /dev/loop14: Permiso denegado
fdisk: no se puede abrir /dev/loop13: Permiso denegado
fdisk: no se puede abrir /dev/loop15: Permiso denegado
fdisk: no se puede abrir /dev/loop16: Permiso denegado
fdisk: no se puede abrir /dev/loop17: Permiso denegado
johanna-velasco@johanna-velasco-VirtualBox:~$ blkid
/dev/sda2: UUID="f21037f8-28f8-4863-ab61-1ec612570a4b" BLOCK_SIZE="4096" TYPE="ext4" PARTUUID="ef3221ac-6115-4aa5-bbe1-9244ccf21157"
johanna-velasco@johanna-velasco-VirtualBox:~$ df -h
S.ficheros     Tamaño Usados  Disp Uso% Montado en
tmpfs            1,1G   1,6M  1,1G   1% /run
/dev/sda2         25G    13G   12G  53% /
tmpfs            5,2G      0  5,2G   0% /dev/shm
tmpfs            5,0M   8,0K  5,0M   1% /run/lock
tmpfs            1,1G   132K  1,1G   1% /run/user/1000
johanna-velasco@johanna-velasco-VirtualBox:~$ 

## Preguntas 

 - ¿Qué dispositivos de almacenamiento están conectados a su sistema?
   sda: Este es el dispositivo de disco principal de 25 GB
 - ¿Qué particiones están montadas actualmente?
   sda2 y tmpfs
 - ¿Qué tipo de sistemas de archivos se usan en las particiones?
   /dev/sda2: Sistema de archivos ext4.
   tmpfs: Sistema de archivos temporal 

# Actividad 3: Explorar dispositivos de entrada
## Comandos
root@johanna-velasco-VirtualBox:~# evtest
No device specified, trying to scan all of /dev/input/event*
Available devices:
/dev/input/event0:	Power Button
/dev/input/event1:	Sleep Button
/dev/input/event2:	AT Translated Set 2 keyboard
/dev/input/event3:	Video Bus
/dev/input/event4:	VirtualBox USB Tablet
/dev/input/event5:	ImExPS/2 Generic Explorer Mouse
/dev/input/event6:	VirtualBox mouse integration


## Preguntas

 - ¿Qué eventos genera cada dispositivo al interactuar con ellos?
   En orden:
   Encender, apagar o suspender el sistema
   Poner el sistema en modo de suspensión o hibernación.
   Gestión de la configuración y eventos de video.
   Emulación de una tableta táctil en entornos virtualizados
   Interacción con el sistema a través del ratón.
   Mejora de la integración y sincronización del puntero del ratón en entornos virtualizados.
  
   
 - ¿Cómo se identifican los dispositivos en `/proc/bus/input/devices`?
   I: Información del bus y del dispositivo, incluyendo el identificador del bus, el proveedor y el producto.
   N: Nombre del dispositivo.
   P: Dirección física del dispositivo.
   S: Ruta en el sistema de archivos sysfs.
   U: Información única del dispositivo, si la hay.
   H: Controladores asociados con el dispositivo (por ejemplo, kbd para teclado, mouse para ratón).
   B: Capacidades del dispositivo en términos de propiedades (PROP), eventos (EV), teclas (KEY), etc.
   
# Actividad 4: Examinar dispositivos de salida
## Comandos
root@johanna-velasco-VirtualBox:~# sudo xrandr
Can't open display 
root@johanna-velasco-VirtualBox:~# sudo aplay -l
**** Lista de PLAYBACK dispositivos hardware ****
tarjeta 0: I82801AAICH [Intel 82801AA-ICH], dispositivo 0: Intel ICH [Intel 82801AA-ICH]
  Subdispositivos: 1/1
  Subdispositivo #0: subdevice #0
root@johanna-velasco-VirtualBox:~# sudo lsof/dev/snd/*
sudo: lsof/dev/snd/*: orden no encontrada
root@johanna-velasco-VirtualBox:~# sudo lsof /dev/snd/*
lsof: WARNING: can't stat() fuse.portal file system /run/user/1000/doc
      Output information may be incomplete.
lsof: WARNING: can't stat() fuse.gvfsd-fuse file system /run/user/1000/gvfs
      Output information may be incomplete.
COMMAND    PID            USER   FD   TYPE DEVICE SIZE/OFF NODE NAME
pipewire  1929 johanna-velasco   59u   CHR  116,1      0t0  429 /dev/snd/seq
pipewire  1929 johanna-velasco   60u   CHR  116,1      0t0  429 /dev/snd/seq
wireplumb 1934 johanna-velasco   35u   CHR  116,5      0t0  719 /dev/snd/controlC0

## Preguntas

 - ¿Qué salidas de video están disponibles en su sistema?
   No hubo ninguna ya que no había un entorno conectado
 - ¿Qué dispositivos de sonido se detectaron?
   Intel ICH [Intel 82801AA-ICH] con un subdispositivo disponible
 - ¿Qué procesos están usando la tarjeta de sonido?
   pipewire (PID 1929): Utiliza /dev/snd/seq y wireplumber (PID 1934): Utiliza /dev/snd/controlC0

# Actividad 5: Crear un script de resumen

## Comandos
root@johanna-velasco-VirtualBox:~# bash dispositivos.sh
Dispositivos de bloque:
NAME   MAJ:MIN RM   SIZE RO TYPE MOUNTPOINTS
loop0    7:0    0     4K  1 loop /snap/bare/5
loop1    7:1    0 316,5M  1 loop /snap/code/173
loop2    7:2    0 316,5M  1 loop /snap/code/174
loop3    7:3    0  63,7M  1 loop /snap/core20/2434
loop4    7:4    0  73,9M  1 loop /snap/core22/1663
loop5    7:5    0  73,9M  1 loop /snap/core22/1722
loop6    7:6    0 269,8M  1 loop /snap/firefox/4793
loop7    7:7    0 273,6M  1 loop /snap/firefox/5187
loop8    7:8    0  11,1M  1 loop /snap/firmware-updater/147
loop9    7:9    0 505,1M  1 loop /snap/gnome-42-2204/176
loop10   7:10   0  10,7M  1 loop /snap/firmware-updater/127
loop11   7:11   0  10,7M  1 loop /snap/snap-store/1218
loop12   7:12   0  10,8M  1 loop /snap/snap-store/1244
loop13   7:13   0  44,3M  1 loop /snap/snapd/23258
loop14   7:14   0  38,8M  1 loop /snap/snapd/21759
loop15   7:15   0  91,7M  1 loop /snap/gtk-common-themes/1535
loop16   7:16   0   568K  1 loop /snap/snapd-desktop-integration/253
loop17   7:17   0   500K  1 loop /snap/snapd-desktop-integration/178
sda      8:0    0    25G  0 disk 
├─sda1   8:1    0     1M  0 part 
└─sda2   8:2    0    25G  0 part /
sr0     11:0    1  1024M  0 rom  
Dispositivos USB:
Bus 001 Device 001: ID 1d6b:0001 Linux Foundation 1.1 root hub
Bus 001 Device 002: ID 80ee:0021 VirtualBox USB Tablet
Bus 002 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub
Dispositivos PCI:
00:00.0 Host bridge: Intel Corporation 440FX - 82441FX PMC [Natoma] (rev 02)
00:01.0 ISA bridge: Intel Corporation 82371SB PIIX3 ISA [Natoma/Triton II]
00:01.1 IDE interface: Intel Corporation 82371AB/EB/MB PIIX4 IDE (rev 01)
00:02.0 VGA compatible controller: VMware SVGA II Adapter
00:03.0 Ethernet controller: Intel Corporation 82540EM Gigabit Ethernet Controller (rev 02)
00:04.0 System peripheral: InnoTek Systemberatung GmbH VirtualBox Guest Service
00:05.0 Multimedia audio controller: Intel Corporation 82801AA AC'97 Audio Controller (rev 01)
00:06.0 USB controller: Apple Inc. KeyLargo/Intrepid USB
00:07.0 Bridge: Intel Corporation 82371AB/EB/MB PIIX4 ACPI (rev 08)
00:0b.0 USB controller: Intel Corporation 82801FB/FBM/FR/FW/FRW (ICH6 Family) USB2 EHCI Controller
00:0d.0 SATA controller: Intel Corporation 82801HM/HEM (ICH8M/ICH8M-E) SATA Controller [AHCI mode] (rev 02)
Dispositivos de entrada:
I: Bus=0019 Vendor=0000 Product=0001 Version=0000
N: Name="Power Button"
P: Phys=LNXPWRBN/button/input0
S: Sysfs=/devices/LNXSYSTM:00/LNXPWRBN:00/input/input0
U: Uniq=
H: Handlers=kbd event0 
B: PROP=0
B: EV=3
B: KEY=8000 10000000000000 0

I: Bus=0019 Vendor=0000 Product=0003 Version=0000
N: Name="Sleep Button"
P: Phys=LNXSLPBN/button/input0
S: Sysfs=/devices/LNXSYSTM:00/LNXSLPBN:00/input/input1
U: Uniq=
H: Handlers=kbd event1 
B: PROP=0
B: EV=3
B: KEY=4000 0 0

I: Bus=0011 Vendor=0001 Product=0001 Version=ab41
N: Name="AT Translated Set 2 keyboard"
P: Phys=isa0060/serio0/input0
S: Sysfs=/devices/platform/i8042/serio0/input/input2
U: Uniq=
H: Handlers=sysrq kbd event2 leds 
B: PROP=0
B: EV=120013
B: KEY=402000000 3803078f800d001 feffffdfffefffff fffffffffffffffe
B: MSC=10
B: LED=7

I: Bus=0019 Vendor=0000 Product=0006 Version=0000
N: Name="Video Bus"
P: Phys=LNXVIDEO/video/input0
S: Sysfs=/devices/LNXSYSTM:00/LNXSYBUS:00/PNP0A03:00/LNXVIDEO:00/input/input4
U: Uniq=
H: Handlers=kbd event3 
B: PROP=0
B: EV=3
B: KEY=3e000b00000000 0 0 0

I: Bus=0003 Vendor=80ee Product=0021 Version=0110
N: Name="VirtualBox USB Tablet"
P: Phys=usb-0000:00:06.0-1/input0
S: Sysfs=/devices/pci0000:00/0000:00:06.0/usb1/1-1/1-1:1.0/0003:80EE:0021.0001/input/input6
U: Uniq=
H: Handlers=mouse0 event4 js0 
B: PROP=0
B: EV=1f
B: KEY=1f0000 0 0 0 0
B: REL=1940
B: ABS=3
B: MSC=10

I: Bus=0011 Vendor=0002 Product=0006 Version=0000
N: Name="ImExPS/2 Generic Explorer Mouse"
P: Phys=isa0060/serio1/input0
S: Sysfs=/devices/platform/i8042/serio1/input/input5
U: Uniq=
H: Handlers=mouse1 event5 
B: PROP=1
B: EV=7
B: KEY=1f0000 0 0 0 0
B: REL=143

I: Bus=0001 Vendor=80ee Product=cafe Version=0000
N: Name="VirtualBox mouse integration"
P: Phys=
S: Sysfs=/devices/pci0000:00/0000:00:04.0/input/input7
U: Uniq=
H: Handlers=mouse2 event6 js1 
B: PROP=0
B: EV=b
B: KEY=10000 0 0 0 0
B: ABS=3

Salidas de video:
Can't open display 
Tarjetas de sonido:
**** Lista de PLAYBACK dispositivos hardware ****
tarjeta 0: I82801AAICH [Intel 82801AA-ICH], dispositivo 0: Intel ICH [Intel 82801AA-ICH]
  Subdispositivos: 1/1
  Subdispositivo #0: subdevice #0
## Preguntas

 - ¿Qué ventajas tiene usar un script para recopilar esta información?
   La información se puede obtener de forma automatizada, además de que es fácil de usarse y es consistente si lo comparamos con la información si la buscamos directamente ejecutando comandos en la consola, es eficiente y además podemos incluir comentarios al ascript para recordar qué hace cada comando (documentación)
 - ¿Qué cambios realizaría para personalizar el script?
   Podemos agregarle un menú interactivo para seleccionar que cúal es la información que queremos solicitar en ese momento y no que solamente imrprima todo cada vez que se abre

# Actividad 6: Reflexión y discusión
## Preguntas 

 - ¿Qué comando encontró más útil y por qué?
Considero que el comando más util es el lsblk, ya que es el que tiene la mejor estructura al mostrar los dispositivos, aparte de que da información sobre cada partición y nos muestra la información suficiente para encontrar problemas en el almacenamiento
 - ¿Qué tan importante es conocer los dispositivos conectados al sistema?
   Porque esto nos ayuda a saber, por ejemplo, por qué el rendimiento de la computadora es menor (probablemente muchos procesos y pocos recursos para todos), para mantenimiento y por seguridad, ya que pueden entrar dispositivos sospechosos o malware
 - ¿Cómo podrían estos conocimientos aplicarse en la administración de sistemas?
   Podríamos hacer una optimización de recursos, sabiendo cómo usamos los dispositivos para posibles actualizaciones futuras de hardware.
