# Software de gestion de aulas 

### Gitbook
Para acceder a información amplicada consultar el siguiente gitbook:
https://ssf-proyecto.gitbook.io/ssf/

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

# Controlador

La clase `AulasController` es el controlador principal de la aplicación y se encarga de coordinar la comunicación entre la vista y el modelo.


Las variables `view`, `aulasService` y `equiposService` almacenan respectivamente la ventana gráfica, el servicio de gestión de aulas y el servicio de gestión de equipos.


El constructor `AulasController` crea la ventana gráfica y las instancias necesarias de los servicios para que el controlador pueda trabajar con los datos y la interfaz.


El método `iniciar` es el encargado de arrancar la aplicación. Primero crea las tablas de la base de datos mediante `TablasSQL`. Después carga las aulas en el `ComboBox`, actualiza la lista de equipos, configura los eventos de los botones y finalmente muestra la ventana al usuario.


El método `configurarBotones` asigna una acción a cada componente gráfico. Cuando el usuario cambia de aula se actualizan los detalles y la lista de equipos. Los botones permiten agregar equipos, modificar la memoria RAM, clausurar aulas y crear nuevas aulas.


El método `cargarComboBox` obtiene todas las aulas almacenadas en la base de datos mediante
`AulasService`. Una vez recuperadas, elimina los elementos existentes del `ComboBox` y añade las nuevas aulas para que puedan seleccionarse desde la interfaz.


El método `actualizarTabla` actualiza el contenido de la lista de equipos. Obtiene el aula seleccionada, elimina los elementos existentes y consulta todos los equipos asociados a esa aula mediante `EquiposService`.


El método `detallesAula` muestra la información del aula seleccionada. Copia los datos del objeto aula a los campos de texto correspondientes. Si no existe ninguna selección, limpia todos los campos.


El método `accionAnadirEquipo` controla el proceso de inserción de un nuevo equipo. Comprueba que exista un aula seleccionada y que los campos obligatorios estén completos. Después genera un código de equipo, recoge los datos introducidos por el usuario, crea un objeto `Equipos` y solicita a
`EquiposService` que lo almacene en la base de datos. Finalmente actualiza la lista y limpia el formulario.
El método `accionModificarRam` permite cambiar la cantidad de memoria RAM de un equipo. Obtiene el equipo seleccionado, solicita el nuevo valor al usuario y utiliza `EquiposService` para guardar el cambio en la base de datos.


El método `clausurarAula` elimina un aula seleccionada. Para ello obtiene el código del aula y llama al servicio correspondiente. Una vez completada la operación actualiza la interfaz para reflejar los cambios.


El método `accionNuevaAula` crea una nueva aula. Solicita al usuario el nombre, la ubicación y la capacidad. Después genera un código identificador, crea un objeto `Aulas` y lo inserta mediante
`AulasService`. Finalmente actualiza la vista.


El método `limpiarCampos` borra todos los campos utilizados para introducir datos de nuevos equipos.


# Vista


La clase `VentanaAulas` representa la interfaz gráfica de usuario.


Las variables `comboAulas`, `txtMarcaModelo`, `txtProcesador`, `txtIP`, `txtOP`, `txtRAM`,
`txtAlmacenamiento`, `txtCodAula`, `txtNombreAula`, `txtUbicacion` y `txtCapacidad` almacenan los distintos componentes de texto y selección utilizados por la interfaz.


Las variables `listaEquipos` y `modelListaEquipos` gestionan la lista donde se muestran los equipos pertenecientes al aula seleccionada.


Las variables `btnAgregar`, `btnModificarRam`, `btnClausurarAula` y `btnNuevaAula` representan los botones disponibles para el usuario.


El constructor `VentanaAulas` construye toda la interfaz gráfica. Organiza la ventana en diferentes zonas, crea los formularios, la lista de equipos y los botones necesarios para interactuar con la aplicación. Su única responsabilidad es mostrar información y recoger datos del usuario.


# Modelo
## Clase `Aulas`


La clase `Aulas` representa una entidad aula dentro del sistema.


Las variables `Cod_aula`, `Nombre_aula`, `Ubicacion` y `Capacidad_Alumnos` almacenan la información principal de cada aula.


El constructor `Aulas` inicializa todos estos atributos al crear un objeto.


### Métodos


    • `getCapacidad_Alumnos` y `setCapacidad_Alumnos`: permiten consultar y modificar la capacidad del aula.
    • `getCod_aula` y `setCod_aula`: permiten consultar y modificar el código identificador.
    • `getNombre_aula` y `setNombre_aula`: permiten consultar y modificar el nombre.
    • `getUbicacion` y `setUbicacion`: permiten consultar y modificar la ubicación.
    • `toString`: devuelve el nombre del aula para facilitar su visualización en el `ComboBox`.


## Clase `Equipos`


La clase `Equipos` representa una entidad equipo informático.


Las variables `Cod_equipo`, `Marca_Modelo`, `Procesador`, `Direccion_IP`, `Sistema_Operativo`,
`RAM`, `Almacenamiento` y `Cod_aula` almacenan la información de cada equipo.


El constructor `Equipos` inicializa todos estos atributos.


### Métodos


Los métodos `get` y `set` correspondientes permiten consultar y modificar cada uno de los atributos del equipo.
El método `toString` devuelve una representación simplificada del equipo mostrando su código, marca y memoria RAM.


## Clase `Conexion`


La clase `Conexion` gestiona la comunicación con la base de datos.


Las variables `connection`, `url`, `base`, `usuario` y `password` almacenan la información necesaria para establecer la conexión.


### Métodos


    • `getConnection`: crea la conexión con MySQL, crea la base de datos si no existe y selecciona dicha base de datos para trabajar con ella.
    • `closeConnection`: cierra una conexión abierta cuando ya no es necesaria.


## Clase `AulasService`


La clase `AulasService` contiene la lógica de negocio relacionada con las aulas.


### Métodos


    • `generarCodigo`: genera automáticamente un código para una nueva aula utilizando las primeras letras de su nombre y un número secuencial.
    • `insertarAulas`: inserta una nueva aula en la base de datos.
    • `obtenerAulas`: recupera todas las aulas almacenadas y las devuelve en una colección.
    • `clausurarAula`: comprueba si existe algún equipo con una memoria RAM igual o superior a 32 gigabytes. Si existe, cancela la eliminación. Si no existe, elimina primero los equipos asociados y posteriormente el aula.


## Clase `EquiposService`


La clase `EquiposService` contiene la lógica de negocio relacionada con los equipos.
### Métodos


    • `generarCodigoAleatorio`: crea códigos aleatorios para identificar equipos.
    • `obtenerEquipos`: recupera todos los equipos pertenecientes a un aula concreta.
    • `modificarRAM`: actualiza la memoria RAM de un equipo en la base de datos.
    • `insertarEquipo`: comprueba cuántos equipos existen en el aula seleccionada. Si se ha alcanzado el límite permitido, cancela la inserción. En caso contrario almacena el nuevo equipo en la base de datos.


## Clase `TablasSQL`


La clase `TablasSQL` se encarga de la creación de la estructura de la base de datos.


### Métodos


    • `generarCodigoAleatorio`: genera códigos de prueba utilizados durante el desarrollo.
    • `createTable`: crea las tablas `aulas` y `equipos` si todavía no existen. Además inserta algunos registros iniciales para facilitar las pruebas de la aplicación.


## Metodologia de trabajo
El gitflow del proyecto es el siguiente

| Branches   |             Explicacion              |
|:-----------|:------------------------------------:|
| Produccion |     Versiones publicas probadas      |
| Desarrollo | Integracion de features en fase beta |
| Feature    |  Desarrollo de funciones concretas   |
| Hotfix | Corregir bugs puntuales directamente en produccion |

Se espera por ende que se desarrollen varias pequeñas features, se junten para su testeo en desarrollo y cucando haya una cantidad relevante de fueares nueva sacar una version publica a produccion.
