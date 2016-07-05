# cirtock
## Para conexión a la base de datos
Agregar al repositorio maven de sus pc la dependecia postgresql-9.3-1103.jdbc3 para la coneccion a postgres.
```
	-Es necesario tener [maven](https://maven.apache.org/) instalados en sus pc.
	1- Ubicar el jar postgresql-9.3-1103.jdbc3 que se encuentra en \src\main\webapp\WEB-INF\Libraries ruta del proyecto a un ruta copiarlo a una ruta, copiarlo a una ruta de facil acceso desde la consola, yo copie por ejemplo en mi carpeta personal.
	2- Abrir una consola y agregar el siguiente comando: 
	mvn install:install-file -Dfile="C:\Users\Usuario\postgresql-9.3-1103.jdbc3.jar" -DgroupId=org.postgresql -DartifactId=postgresql -Dversion=9.3-1103.jdbc3 -Dpackaging=jar

	-Dfile: Correponde a la ruta donde esta el archivo postgresql-9.3-1103.jdbc3.jar
``` 
	 
## Para la base de datos cirtock:

Crear la base de datos cirtock y conectarse para luego ejecutar el siguiente script 

```
CREATE ROLE cirtock WITH ENCRYPTED PASSWORD 'cirtock';

BEGIN TRANSACTION;

CREATE SCHEMA cirtock;

GRANT USAGE ON SCHEMA cirtock TO GROUP cirtock;

CREATE SEQUENCE cirtock.seq_herramienta;
CREATE TABLE cirtock.herramienta (
    id             integer
                   not null
                   default nextval('cirtock.seq_herramienta'),
    nombre         text
                   not null,
    descripcion    text
                   not null,
    cantidad       integer
                   not null
                   check(cantidad >= 0),

   CONSTRAINT pk_herramienta
     PRIMARY KEY (id)
);

CREATE SEQUENCE cirtock.seq_insumo;
CREATE TABLE cirtock.insumo (
    id             integer
                   not null
                   default nextval('cirtock.seq_insumo'),
    nombre         text
                   not null,
    descripcion    text
                   not null,
    cantidad       integer
                   not null
                   check(cantidad >= 0),

   CONSTRAINT pk_insumo
     PRIMARY KEY (id)
);
CREATE TABLE cirtock.usuario(
  usuario text
    not null,
  password varchar[20]
    not null

);
GRANT SELECT, UPDATE ON SEQUENCE cirtock.seq_insumo TO cirtock;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE cirtock.insumo TO cirtock;

GRANT SELECT, UPDATE ON SEQUENCE cirtock.seq_herramienta TO cirtock;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE cirtock.herramienta TO cirtock;

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE cirtock.usuario TO cirtock;

END TRANSACTION;
```
