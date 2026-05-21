INSERT INTO genero (nombre) VALUES
    ('Acción'),
    ('Comedia'),
    ('Terror'),
    ('Drama'),
    ('Ciencia Ficción');

INSERT INTO pelicula (titulo, anyo, director, genero_id) VALUES
    ('Mad Max: Fury Road',     2015, 'George Miller',       1),
    ('John Wick',              2014, 'Chad Stahelski',      1),
    ('The Hangover',           2009, 'Todd Phillips',       2),
    ('Superbad',               2007, 'Greg Mottola',        2),
    ('El Conjuro',             2013, 'James Wan',           3),
    ('Hereditary',             2018, 'Ari Aster',           3),
    ('El Padrino',             1972, 'Francis Ford Coppola',4),
    ('La Lista de Schindler',  1993, 'Steven Spielberg',    4),
    ('Interstellar',           2014, 'Christopher Nolan',   5),
    ('The Matrix',             1999, 'The Wachowskis',      5);