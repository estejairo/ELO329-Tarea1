# Tarea 1: Semáforos como objetos de software

Este repositorio contiene codigo fuente que simula el comportamiento de los semaforos presentes en una intersección vial. Se separa en 4 etapas incrementales, donde la primera etapa corresponde a un semaforo simle de 3 luces; la segunda etapa representa un semaforo peatonal; la tercera etapa simula una luz de giro; la cuarta etapa es la integracion de todos los semaforos en la interseccion vial.

### Prerequisitos

El código está diseñado para ser compilado y ejecutado en sistemas operativos Linux.
Las librerias utilizadas corresponden a las disponibles en Java 7.
Se recomienda utilizar el código en el mismo entorno que fue diseñado, el cual tiene las siguientes características:

- Linux: OS Debian 7.11 (Wheezy) 64Bits
- OpenJDK Runtime Environment (IcedTea 2.6.11) (7u151-2.6.11-2~deb7u3) 64Bits


### Compilación

Cada etapa es compilada y ejecutada de manera independiente. Para compilar una etapa, es necesario ejecutar el comando "Make" en consola.

## Ejecución

La ejecución de cada etapa (luego de haberla compilado) se adjunta a continuación:<p>

Stage 1:<br>
java TestStage1 {tiempo total del semaforo} {tiempo en verde}<br>

Stage 2:<br>
java TestStage2 {entrada.csv}<br>
(entrada.csv corresponde a lineas con 1 y 0 que simulan el presionar de un boton del cruce peatonal)<br>

Stage 4:<br>
java TestStage3 {entrada.csv}<br>
(entrada.csv corresponde a lineas con 1 y 0 que simulan el accionar de un sensor de cruce vehicular)<br>

Stage 4:

## Autores

* **Paula Amigo** - *201504013-3* - paula.amigo@sansano.usm.cl
* **Luis Bahamondes** - *201421077-9* - luis.bahamondes.14@sansano.usm.cl
* **Jairo Gonzáez** - *201304502-2* - jairo.gonzalez.13@sansano.usm.cl


## Ayudante Corrector
* **Gonzalo Rojas** - gonzalo.rojass@alumnos.usm.cl

## Profesor a Cargo
* **Cristobal Nettle** - cristobal.nettle@gmail.com
