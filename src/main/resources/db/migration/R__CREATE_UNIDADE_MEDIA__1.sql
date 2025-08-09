CREATE TABLE unidade_medida (
     id serial PRIMARY KEY,
     descricao varchar(20),
     abreviacao varchar(2),
     unique (abreviacao)
);

INSERT INTO unidade_medida (descricao, abreviacao) values
('Kilograma', 'kg'),
('Grama', 'gr'),
('Litro', 'lt'),
('Miligrama', 'ml');