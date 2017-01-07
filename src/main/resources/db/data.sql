/*H2*/
INSERT INTO SUPPLIER VALUES(1,'address','company','name',88997744),(2,'address','company','name',88997744),(3,'address','company','name',88997744);
INSERT INTO CATEGORY VALUES(1,'description','name'),(2,'description','name'),(3,'description','name');
INSERT INTO PRODUCT VALUES(1,'description1','name1',250.25,40,1),(2,'description2','name2',250.25,40,2),(3,'description3','name3',250.25,40,3);
INSERT INTO PRODUCT_SUPPLIERS  VALUES(1,1),(2,2),(3,3),(1,2),(2,1),(3,1);

/*POSTGRES*/
INSERT INTO supplier(id, address, company, name, phone) VALUES 
(1,'San Rafael Arriba','AMSO','Juan Vargas',88997744),
(2,'Curridabat','PPLU','Carlos Mora',65472136),
(3,'San Carlos','ZZMA','Ana Rosales',78456589);

INSERT INTO category(id, description, name) VALUES 
(1,'Artículos deportivos','Deportes'),
(2,'Anteojos, gafas y accesorios','Lentes'),
(3,'Teléfonos celulares y accesorios','Celulares'),
(4,'Equipo de cómputo y accesorios','Cómputo');

INSERT INTO product(id, description, name, unit_price, units_in_stock, category_id) VALUES
(1,'Sony. Procesador 1.5 GHZ, Memoria RAM 2 GB, Memoria Interna 32 GB, Camara 8 Mpx','Celular',180000.00,24,3),
(2,'Adidas Brazuca 2014','Balón de futbol',35000.00,60,1),
(3,'Gafas de sol','Gafas',25000.00,20,2),
(4,'Western Digital Capacidad 2 TB','Disco duro externo',80000.00,10,4);

INSERT INTO "product_suppliers"(products_id,suppliers_id)VALUES
(1,1),
(2,1),
(1,2),
(2,2),
(1,3),
(2,3),
(3,3),
(4,3);
