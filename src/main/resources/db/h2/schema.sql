CREATE TABLE IF NOT EXISTS Manufacturer (
  id INTEGER PRIMARY KEY,
  name VARCHAR(256),
  country VARCHAR(256)
);


CREATE TABLE IF NOT EXISTS Beer (
  id INTEGER PRIMARY KEY,
  name VARCHAR(256),
  graduation REAL,
  "TYPE" VARCHAR(256),
  description VARCHAR(256),
  manufacturer_id INTEGER,
  FOREIGN KEY(manufacturer_id) REFERENCES manufacturer(id)
);



