# Traders-App

Traders app developed in Spring, Angular JS and using Postgres database.

**Instructions:**
* Set property: spring.jpa.hibernate.ddl-auto=create in application.properties. First time you run, then change it to spring.jpa.hibernate.ddl-auto=validate.
* Set postgres username and password in application.properties.
* Insert some data in the database running the script data.sql or create your own.

**Credits:**
* [dlbunker - ps-spring-boot-resources](https://github.com/dlbunker/ps-spring-boot-resources) angular JS client and integration with Spring boot.
* [jagdeepsingh91 - Angular Multiple Select](https://www.npmjs.com/package/angular-multiple-select) to Handle multiple selection in the client.

**Version 1 (Released):**

This version includes a REST API with all CRUD operations over entities Supplier, Category and Product. Paths:

* /api/v1/suppliers
* /api/v1/categories
* /api/v1/products

**Version 2 (Released):**

- Paths:

	/api/v2/suppliers	
	/api/v2/categories
	/api/v2/products	
	/api/v2/users

- User registration and authentication implemented.
- Browse through users registered in the app.
- API require authentication to get data.
- Only admin users can add users and edit his role but registration is open.


**App demo:** [tradersapp-jordanec.herokuapp.com](https://tradersapp-jordanec.herokuapp.com/)

![alt tag](src/main/resources/public/images/app-screenshots/home.jpg?raw=true)

![alt tag](src/main/resources/public/images/app-screenshots/not logged in.jpg?raw=true)

![alt tag](src/main/resources/public/images/app-screenshots/registration.jpg?raw=true)

![alt tag](src/main/resources/public/images/app-screenshots/log in.jpg?raw=true)

![alt tag](src/main/resources/public/images/app-screenshots/users.jpg?raw=true)

![alt tag](src/main/resources/public/images/app-screenshots/products.jpg?raw=true)

![alt tag](src/main/resources/public/images/app-screenshots/product.jpg?raw=true)

![alt tag](src/main/resources/public/images/app-screenshots/product add.jpg?raw=true)

![alt tag](src/main/resources/public/images/app-screenshots/product edit.jpg?raw=true)


