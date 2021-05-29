# BAZ-Peliculas-y-series
This is an Android application who show recent and popular movies from The Movie DataBase API, it shows movies title, description and if video is  available it will be shown, enjoy!


Uno de los feature que me faltaron realizar es el funcionamiento offline, lo realizaría con alguna librería para persistencia, por ejemplo Room, en el cuál al consumir el servicio de la API se guarde en base de datos local, cuando se recarguen los datos comprobar los objectos y verificar si alguno cambió, para así después actualizarlo, logré implementar en la interfaz cuando es fallido el llamada a la API, cuando se produjera un error cargar en la vista los datos de la base de datos.
