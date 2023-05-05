use `pruebatecnica`;

/*SERVICIO LAVADO*/
INSERT INTO `pruebatecnica`.`servicio` VALUES (1, sysdate(), 2000,  sysdate());
INSERT INTO `pruebatecnica`.`servicio` VALUES (2, sysdate(), 2500,  sysdate());
INSERT INTO `pruebatecnica`.`servicio` VALUES (3, sysdate(), 4500,  sysdate());
INSERT INTO `pruebatecnica`.`lavado` VALUES (0, 1);
INSERT INTO `pruebatecnica`.`lavado` VALUES (1, 2);
INSERT INTO `pruebatecnica`.`lavado` VALUES (2, 3);

/*SERVICIO ACEITE Y FILTRO*/
INSERT INTO `pruebatecnica`.`servicio` VALUES (4, sysdate(), 3000,  sysdate());
INSERT INTO `pruebatecnica`.`servicio` VALUES (5, sysdate(), 3500,  sysdate());
INSERT INTO `pruebatecnica`.`aceite_y_filtro` VALUES (0, 4);
INSERT INTO `pruebatecnica`.`aceite_y_filtro` VALUES (1, 5);

/*SERVICIO ALINEACION Y BALANCEO*/
INSERT INTO `pruebatecnica`.`servicio` VALUES (6, sysdate(), 500,  sysdate());
INSERT INTO `pruebatecnica`.`servicio` VALUES (7, sysdate(), 1100,  sysdate());
INSERT INTO `pruebatecnica`.`alineacion_y_balanceo` VALUES (0, 6);
INSERT INTO `pruebatecnica`.`alineacion_y_balanceo` VALUES (1, 7);