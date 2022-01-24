
INSERT INTO Manufacturer (id, name, nationality)  VALUES (1, 'Heineken', 'Netherlands');
INSERT INTO Manufacturer (id, name, nationality)  VALUES (2, 'Damm Brewery', 'Spain');
INSERT INTO Manufacturer (id, name, nationality)  VALUES (3, 'Guinness Brewery', 'Ireland');
INSERT INTO Manufacturer (id, name, nationality)  VALUES (4, 'Paulaner brewery', 'Germany');
INSERT INTO Manufacturer (id, name, nationality)  VALUES (5, 'Brewdog', 'Scotland');

INSERT INTO Beer (id, name, graduation, "TYPE", description, manufacturerid) VALUES (1, 'Heineken', 5.5, 'Pale Lager', 'description Heineken', 1);
INSERT INTO Beer (id, name, graduation, "TYPE", description, manufacturerid) VALUES (2, 'Estrella Damm', 4.7, 'Lager', 'description Estrella', 2);
INSERT INTO Beer (id, name, graduation, "TYPE", description, manufacturerid) VALUES (3, 'Guinness', 8.9, 'Stout', 'Guinness Brewery', 3);
INSERT INTO Beer (id, name, graduation, "TYPE", description, manufacturerid) VALUES (4, 'Paulaner', 6.1, 'Witbier', 'description Paulaner', 4);
