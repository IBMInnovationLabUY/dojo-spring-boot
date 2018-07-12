# dojo-spring-boot
En este dojo se trabajara con Spring-boot para crear localmente un microservicio y también luego poder realizar el despliegue en IBM Cloud (Bluemix) y que este accesible en la web.

1. **Requisitos previos** 
	* Pc con sistema operativo Linux preferentemente 
	* Cuenta en IBM Cloud (ex-Bluemix)
    * Tener instalado en el PC:
	* Cli IDT (IBM Developer Tool) y plugin dev
	* Maven
	* Eclipse
	* java jdk 1.8

2. **Crear una aplicación web con spring starter** 
	* Ir a el asistente web http://start.spring.io/ para generar el fuente base. 
		Seleccionar las siguientes opciones:
		Generate: MavenProject 
		with: java 
		Spring Boot: 2.0.3
		Group: com.example 
		Artifact: demo
		Dependencies: web		
     * Descargar y descomprimir en un directorio
	 * Modificamos la clase java src\main\java\com\example\demo\DemoApplication.java para agregarle el import "import org.springframework.context.annotation.ComponentScan;" y la anotación "@ComponentScan(basePackages="com.example")" luego dela anotación @SpringBootApplication
	 * Agregamos una nueva clase java en el mismo package llamada Controlador 
	 * Dentro del directorio ejecutar : mvn package
	 * Dentro del directorio ejecutar: java -jar target/demo-0.0.1-SNAPSHOT.jar 
			El comando anterior publicará la aplicacón por defecto en el puerto 8080, en caso de que ese puerto no este disponible se le podrá agregar al final del comando anterior por ej.: "--server.port=8083" para publicar en el puerto indicado.
	 * Verificamos con un browser las siguientes urls: http://localhost:8083 y http://localhost:8083/dos que son las que sisponibilzamos en el controlador
	 * Ejecutamos: mvn eclipse:eclipse , para poder continuar editando con IDE Eclipse.
	 * Importar desde Eclipse como proyecto Maven.

3. **Crear otra aplicación web con Cli IDT (IBM Developer Tool)** 
	* Desde linea de comandos ejecutar: bx dev create. 
		Seleccionar las siguientes opciones:
		1. Backend Service / Web App
		2. Java - Spring
		2. Microservice - Java Spring Microservice
		Nombre: demomicro
		Servicios: No
		DevOp: (No)
		
		Nota: Esto generara un proyecto base maven-spring y varios archivos de configuracion.
			  Esta tarea ejecutada desde windows 10 me ha dado problemas, aconsejo utilizar linux (Ubuntu por ej.)
			  Puede lanzar un error "The application successfully generated, but there was an error unzipping the archived file.", en ese caso es necesario descomprimir a mano el .zip generado.
	* Movernos dentro del nuevo directorio creado.
	* Ejecutar: bx dev build
		Esto realizara la construccion en base a los archivos de configuracion creados (cli-config.yml)
	* Ejecutar: bx dev run
		Esto ejecutara la aplicacion localmente
	* Verificar que la aplicación está desplegada ingresando a http://localhost:8080 , http://localhost:8080/healt y  http://localhost:8080/v1
	* Ejecutar: bx dev deploy 
		Esto desplegará la aplicación en IBM Cloud (en la cuenta que estemos logueados)
	*Ingresar a la cuenta de IBM Cloud y verificar que la aplicación esta desplegada y accesible.
	