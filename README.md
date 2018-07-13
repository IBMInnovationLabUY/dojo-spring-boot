# dojo-spring-boot
En este dojo se trabajara con Spring-boot para crear localmente un microservicio y también luego poder realizar el despliegue en IBM Cloud (Bluemix) y que este accesible en la web.

### 1. Objetivo 1 - Crear localmente una aplicación web con spring-boot starter 
###### 1.	*Requisitos:
			* Conocimientos previos: ninguno.
			* Pc con sistema operativo Linux o windows indistintamente.
			* Tener instalado en el PC:
			* java jdk 1.8
			* Maven
			* Eclipse (opcionalmente)
				
###### 2.	* Ir a el asistente web http://start.spring.io/ para generar el fuente base. 
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

### 2. Objetivo 2 - Generar una imagen docker con la aplicación y publicar container en Docker. 
###### 	1.	*Requisitos:
			* Conocimientos previos: docker, containers (básico).
			* Haber realizado el Objetivo 1
			* Pc con sistema operativo Linux o windows indistintamente.
			* Tener instalado en el PC:
			* java jdk 1.8
			* Maven
			* Eclipse (opcionalmente)
			* Docker
				
###### 	2.	* Crear archivo Dockerfile en el directorio raiz del proyecto (ver ej.).
		* Crear y etiquetar la nueva imagen. Ej.: docker build -t registry.ng.bluemix.net/mybxnamespace/demomicro:1
		* Podemos publicar localmente para verificar que funciona. Por ej.: docker run -d -p 8084:8080 registry.ng.bluemix.net/mybxnamespace/demomicro:1
		* Ingresamos a las urls (puerto 8084 en este caso) para verificar que la app funciona correctamente.

### 3. Objetivo 3 - Publicar la app en un cluster Kubernetes en IBM Cloud.	
###### 	1.	*Requisitos:
			* Conocimientos previos: docker, containers, Kubernetes (básico).
			* Haber realizado el Objetivo 1 y 2
			* Pc con sistema operativo Linux preferentemente.
			* Cuenta en IBM Cloud (ex-Bluemix)
			* Tener instalado en el PC:
				* java jdk 1.8
				* Maven
				* Eclipse (opcionalmente)
				* Docker
				* kubectl
				* Cli IDT (IBM Developer Tool) y plugin dev
				
###### 	2.	* Enviamos nuesta imagen creada en el objetivo 2 al repositorio en IBM Cloud. 
			Ej.: docker push registry.ng.bluemix.net/mybxnamespace/demomicro:1
		* Creamos el container en IBM Cloud y lo ejecutamos. 
			Ej.: kubectl run demomicro-deployment --image=registry.ng.bluemix.net/mybxnamespace/demomicro:1
		* Creamos el servicio y lo exponemos en la web.
			Ej.: kubectl expose deployment/demomicro-deployment --type=NodePort --port=8080 --name=demomicro-service --target-port=8080
		* Buscamos los datos de conexión y verificamos nuestra app expuesta en la web. 
			Para esto usamos los comandos por ej.: 
				1. bx cs workers mybxcluster
				2. kubectl describe service demomicro-service | grep NodePort
				3. En el navegador web: http://[IP_Public_Ip]:[PUERTO_NodePort]

### 4. Objetivo 4 - Utilizar IBM Developer Tool para generar y desplegar apps spring-boot 		
###### 	1.	*Requisitos:
			* Conocimientos previos:uso básico de IBM Cloud (ex-Bluemix)
			* Pc con sistema operativo Linux preferentemente.
			* Cuenta en IBM Cloud 
			* Tener instalado en el PC:
				* java jdk 1.8
				* Maven
				* Cli IDT (IBM Developer Tool) y plugin dev		
###### 	2.	* Crear otra aplicación web con Cli IDT (IBM Developer Tool)
			Desde linea de comandos ejecutar: bx dev create  
			Seleccionar las siguientes opciones:
				1. Backend Service / Web App
				2. Java - Spring
				3. Microservice - Java Spring Microservice
				4. Nombre: demomicro
				5. Servicios: No
				6. DevOp: (No)		
			**Nota** : Esto generara un proyecto base maven-spring y varios archivos de configuracion.
				  Esta tarea ejecutada desde windows 10 me ha dado problemas, aconsejo utilizar linux (Ubuntu por ej.)
				  Puede lanzar un error "The application successfully generated, but there was an error unzipping the archived file.", en ese caso es necesario descomprimir a mano el .zip generado.
		* Movernos dentro del nuevo directorio creado.
		* Ejecutar: bx dev build
			Esto realizara la construccion en base a los archivos de configuracion creados (cli-config.yml)
		* Ejecutar: bx dev run
			Esto ejecutara la aplicacion localmente
		* Verificar que la aplicación está desplegada ingresando a http://localhost:8080 , http://localhost:8080/healt y  http://localhost:8080/v1
		* Ejecutar: bx dev deploy 
			Esto desplegará la aplicación en IBM Cloud (en la cuenta que estemos logueados) en una runtime Tomcat.
		*Ingresar a la cuenta de IBM Cloud y verificar que la aplicación esta desplegada y accesible.
	
