use `pruebatecnica`;

/*SERVICIO LAVADO*/
INSERT INTO `pruebatecnica`.`servicio` VALUES (1, sysdate(), "Lavado-Basico", 2000,  sysdate());
INSERT INTO `pruebatecnica`.`servicio` VALUES (2, sysdate(), "Lavado-Completo", 2500,  sysdate());
INSERT INTO `pruebatecnica`.`servicio` VALUES (3, sysdate(), "Lavado-Premium", 4500,  sysdate());
INSERT INTO `pruebatecnica`.`lavado` VALUES (0, 1);
INSERT INTO `pruebatecnica`.`lavado` VALUES (1, 2);
INSERT INTO `pruebatecnica`.`lavado` VALUES (2, 3);

/*SERVICIO ACEITE Y FILTRO*/
INSERT INTO `pruebatecnica`.`servicio` VALUES (4, sysdate(), "Aceite y Filtro - Basico", 3000,  sysdate());
INSERT INTO `pruebatecnica`.`servicio` VALUES (5, sysdate(), "Aceite y Filtro - Alto Rendimiento", 3500,  sysdate());
INSERT INTO `pruebatecnica`.`aceite_y_filtro` VALUES (0, 4);
INSERT INTO `pruebatecnica`.`aceite_y_filtro` VALUES (1, 5);

/*SERVICIO ALINEACION Y BALANCEO*/
INSERT INTO `pruebatecnica`.`servicio` VALUES (6, sysdate(), "Alineacion y Balanceo - Sin cambio", 500,  sysdate());
INSERT INTO `pruebatecnica`.`servicio` VALUES (7, sysdate(), "Alineacion y Balanceo - Con cambio", 1100,  sysdate());
INSERT INTO `pruebatecnica`.`alineacion_y_balanceo` VALUES (0, 6);
INSERT INTO `pruebatecnica`.`alineacion_y_balanceo` VALUES (1, 7);

/*MARCAS*/
INSERT INTO `pruebatecnica`.`marca` (id_marca, createdat, marca, updatedat) VALUES (1, sysdate(), 'Ford', sysdate() );
INSERT INTO `pruebatecnica`.`marca` (id_marca, createdat, marca, updatedat) VALUES (2, sysdate(), 'Chevrolet', sysdate() );
INSERT INTO `pruebatecnica`.`marca` (id_marca, createdat, marca, updatedat) VALUES (3, sysdate(), 'Toyota', sysdate() );