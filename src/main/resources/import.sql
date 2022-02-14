INSERT INTO Beverage (id, name) values (1, 'Cafe');
INSERT INTO Beverage (id, name) values (2, 'Cerveza');
INSERT INTO Beverage (id, name) values (3, 'Vino');
INSERT INTO Beverage (id, name) values (4, 'Te');
INSERT INTO Beverage (id, name) values (5, 'Mojito');

INSERT INTO Department (id, name) values (1, 'Sistemas');
INSERT INTO Department (id, name) values (2, 'Ventas');
INSERT INTO Department (id, name) values (3, 'Auditoria');
INSERT INTO Department (id, name) values (4, 'RRHH');

INSERT INTO users (username, password, enabled, completed) values('user','password', true, false);
INSERT INTO users (username, password, enabled, completed) values('admin','password', true, false);

INSERT INTO authorities(username, authority) values('user','ROLE_USER');
INSERT INTO authorities(username, authority) values('admin','ROLE_ADMIN');


--Office Depot users
INSERT INTO users (username, password, enabled, completed) values ('991148', '991148', true, false);
INSERT INTO users (username, password, enabled, completed) values ('991149', '991149', true, false);
INSERT INTO users (username, password, enabled, completed) values ('991150', '991150', true, false);


INSERT INTO authorities(username, authority) values ('991148', 'ROLE_USER');
INSERT INTO authorities(username, authority) values ('991149', 'ROLE_USER');
INSERT INTO authorities(username, authority) values ('991150', 'ROLE_USER');

