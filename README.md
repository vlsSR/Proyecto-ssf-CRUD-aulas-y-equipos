# Software de gestion de aulas 

### Objetivo
Nuestro software permite hacer un CRUD de gestión de aulas con equipos informáticos en ella. Siguiendo las siguientes normas:

- Las aulas tienen un limite de 31 equipos
- No se puede clausar un aula con algun equipo con 32GB o mas de ram

### Caracteristicas
El pograma cuenta con las siguientes feataures

- Ventana Swing con la lista de quipos, seleccionar de aulas y botones para agregar aulas, equipos, modificarlos y clausar aulas
- Aulas con codigo autogenerado, nombre, ubicacion y capacidad
- Equios con modelo, procesador, IP, OS, RAM y almacenamiento
- Base de datos relacional SQL para almacenar los datos

### Uso de la aplicacion
La aplicación cuenta con varias funcionalidades:

1.Creación de aulas:

El usuario podrá crear las aulas que quiera pero con la condición de que
rellenará los datos correspondientes,**el nombre del aula**,**la ubicación**,
y **la capacidad del aula**.


Otros aspectos:

- El codigo del aula se generará de manera automatica.
- Los datos deberán ser los correctos.
- Aparecerá la información por pantalla del aula seleccionada.
- Se podrá borrar aulas,seleccionando el aula y dándole al botón *Clausurar*.

2.Creación de equipos:

El usuario deberá colocar primero los datos correspondiente
en la parte izquierda de la aplicación, luego una vez confirmado los
datos se procederá a darle al botón agregar, si todo esta correcto,
se guardará en la lista de equipo.

Otros aspectos:

- Si la suma total de los equipos supera la capacidad del aula,
  no podrá seguir añadiendo equipos.
- La información deberá ser la correcta para ser guardada.

3.Modificar equipos

Cuando se modifica el equipo, el usuario solo podrá modificar la
memoria RAM con su correspondiente dato que se le será preguntado.