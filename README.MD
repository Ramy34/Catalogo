# Catálogo de Productos (Android App)

Aplicación nativa en Android desarrollada en Java que consume un servicio web (API REST) para mostrar un catálogo de productos. La aplicación presenta un listado mediante vistas personalizadas y permite ver el detalle completo de cada artículo, así como interactuar con sus imágenes de forma dinámica.

## Características Principales

* **Listado de Productos:** Muestra un catálogo de productos obtenidos dinámicamente mediante formato JSON.
* **Vistas Personalizadas:** Utiliza un adaptador personalizado (`Adaptador.java`) para mapear cada elemento de la lista con su imagen, nombre, precio, proveedor y costo de envío.
* **Carga de Imágenes Asíncrona:** Implementación de la popular librería externa **Picasso** para la carga eficiente en caché de las miniaturas de los productos.
* **Detalle del Producto:** Al seleccionar un producto del listado, la aplicación consume un segundo servicio web para mostrar la descripción completa del producto en una nueva actividad.
* **Visualización de Imágenes Dedicada:** Al tocar directamente la miniatura de un producto desde el listado del catálogo, se abre una vista dedicada (`Main3Activity`) para la visualización ampliada de la imagen.

## Características Adicionales (Extras)

* **Créditos del Desarrollador:** Se ha agregado un *Action Button* para mostrar la información del desarrollador de la aplicación.
* **Navegación Intuitiva:** Implementación de la opción de regreso seguro al listado principal.
* **Títulos Dinámicos:** El *Action Bar* de la actividad de detalles se actualiza automáticamente para reflejar el nombre del producto visualizado.
* **Personalización Visual e Interactiva:**
  * Ícono de la aplicación personalizado.
  * Música de fondo reproduciéndose armónicamente en el menú principal de la aplicación.

## Endpoints de la API REST

La aplicación se conecta a los siguientes servicios web para obtener la información mostrada:

1. **Catálogo de Productos:**
   * **URL:** `https://www.serverbpw.com/cm/2020-2/products.php`
   * **Formato:** Arreglo JSON
   * **Datos:** `id`, `name`, `thumbnail_url`, `price`, `provider`, `delivery`.

2. **Detalle del Producto:**
   * **URL:** `https://www.serverbpw.com/cm/2020-2/product_detail.php?id={ID}`
   * **Formato:** Objeto JSON
   * **Datos:** `name`, `imag_url`, `desc`.

## Stack Tecnológico y Dependencias

* **Lenguaje:** Java
* **Framework:** Android SDK
* **Componentes UI:** ListView / BaseAdapter (para el manejo de colecciones)
* **Librerías Externas:**
  * Picasso - *Para la descarga y renderizado de imágenes remoto.*