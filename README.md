# cirtock
para coneccion a la base de datos agregar al repositorio maven de sus pc la dependecia postgresql-9.3-1103-jdbc3 para la coneccion a postgres. cualquier otra version fucionaria
luego al archivo pom.xml, dentro de la etiqueta <dependencies> agregar la dependencia que instalaron en el repositorio maven
	
	 <dependency>
	  <groupId>org.postgresql</groupId>
	  <artifactId>postgresql</artifactId>
	  <version>9.3-1103-jdbc3</version> --version de la dependencia que instalaron 
	 </dependency>
	 
	 
 para la base de datos cirtock

CREATE ROLE cirtock WITH ENCRYPTED PASSWORD 'cirtock';


BEGIN TRANSACTION;

CREATE SCHEMA cirtock;

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

 GRANT SELECT, UPDATE ON SEQUENCE cirtock.seq_herramienta TO cirtock;
 GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE cirtock.herramienta TO cirtock;

 END TRANSACTION;
