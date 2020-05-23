## Ejercicio 3
Realizar una aplicación que se conecte a un servicio para obtener un catálogo de productos. Los productos se deberán presentar en un listado con un ListView o un contenedor similar.

Se debe crear también un prototipo de celda personalizado definido por el programador tomando en cuenta los elementos que devuelve la conexión.

La conexión para los productos será la siguiente:

https://www.serverbpw.com/cm/2020-2/products.php

Con dicha conexión se obtendrá un arreglo en formato JSON con los siguientes datos:

id: Identificador del producto

name: Nombre del producto

thumbnail_url: Url para la imagen de vista previa del producto

price: Precio del producto

provider: Proveedor del producto

delivery: Costo de envío


Al dar clic en algún elemento del listado se deberá ir a otra Activity para mostrar la descripción del producto.

La conexión para esta segunda parte será:

https://www.serverbpw.com/cm/2020-2/product_detail.php?id=ID

Donde ID es el identificador de producto:

Ejemplo: https://www.serverbpw.com/cm/2020-2/product_detail.php?id=6541

Con dicha conexión se obtendrá una respuesta en formato JSON con los siguientes datos:

name: Nombre del producto

imag_url: Url para la imagen del producto

desc: Descripción del producto

Como se ha manejado previamente, ustedes deben definir su interfaz y su prototipo de celda para el listado.

Consideraciones adicionales:

Se debe implementar una clase Producto con las propiedades que consideren para usarse en la aplicación.

Pueden definir todos los elementos gráficos que deseen para hacer más llamativa la interfaz.

## Extras
Se agrega un Action Button que se muestra quien desarrollo la App