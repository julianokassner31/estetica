CREATE TABLE IF NOT EXISTS material (
     id serial PRIMARY KEY,
     nome varchar(100) NOT NULL,
     valor numeric(8,2) NOT NULL,
     unidade_medida int NOT NULL,
     quantidade numeric(8,2),
     descricao text,
     CONSTRAINT fk_unidade_medida__material foreign key(unidade_medida) references unidade_medida(id)
);

alter table material add column validade date;