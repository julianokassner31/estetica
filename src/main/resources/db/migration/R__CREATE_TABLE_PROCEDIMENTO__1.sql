CREATE TABLE IF NOT EXISTS procedimento (
     id serial PRIMARY KEY,
     nome varchar(50),
     valor numeric(8,2),
     descricao text
);

CREATE TABLE IF NOT EXISTS procedimento_material (
     id serial PRIMARY KEY,
     id_procedimento int not null,
     id_material int not null
);

ALTER TABLE procedimento_material ADD COLUMN qtd_material numeric(8,2) not null;