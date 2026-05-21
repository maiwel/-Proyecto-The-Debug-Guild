DROP TABLE IF EXISTS pelicula;
DROP TABLE IF EXISTS genero;

CREATE TABLE genero (
    id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE pelicula (
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    titulo    VARCHAR(100) NOT NULL,
    anyo      INTEGER      NOT NULL,
    director  VARCHAR(100) NOT NULL,
    genero_id INTEGER      NOT NULL,
    FOREIGN KEY (genero_id) REFERENCES genero(id)
);