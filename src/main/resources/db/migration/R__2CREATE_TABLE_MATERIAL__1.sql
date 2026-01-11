CREATE TABLE if NOT EXISTS unidade_medida (
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
CREATE TABLE IF NOT EXISTS material (
    id serial PRIMARY KEY,
    nome varchar(100) NOT NULL,
    valor numeric(10,2) NOT NULL,
    unidade_medida int NOT NULL,
    quantidade numeric(10,2),
    descricao text,
    validade date,
    idempresa int,
    CONSTRAINT fk_unidade_medida__material foreign key(unidade_medida) references unidade_medida(id),
    CONSTRAINT fk_material_empresa FOREIGN KEY(idempresa) REFERENCES empresa(id)
);