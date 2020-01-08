Universidad de Las Palmas, Ingeniería del Software II, enero 2020

##### Estructura

La aplicación usa el estilo arquitectónico MVP. La interfaz del usuario se creó en Swing. La comunicación entre el View y el Presenter procede a través de ActionListeners. Se ha implementado ImageLoader como interfaz así que la implementación concreta se puede elegir al inicializar sin cambios adicionales. 

##### Imágenes

El ImagesSet es un buffer cíclico que guarda las rutas a los imágenes. El imágen proyectado cambia de tamaño para cuadrar dentro del espacio desponible. Los imágenes anterior y posterior respecto al actual están guardados en el View en una forma lista para proyectar, lo que incrementa la velocidad del procesamiento. No se controla el overflow al guardar los imágenes en formato Image porque el espacio disponible limita el tamaño y es adecuado, así que no se espera tal caso. 

El proyecto contiene imágenes ejemplares de muy poco almacenamiento.
