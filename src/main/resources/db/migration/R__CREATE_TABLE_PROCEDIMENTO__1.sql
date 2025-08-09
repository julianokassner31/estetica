CREATE TABLE procedimento (
     id serial PRIMARY KEY,
     nome varchar(50),
     valor numeric(8,2),
     descricao text
);

CREATE TABLE procedimento_material (
     id serial PRIMARY KEY,
     id_procedimento int not null,
     id_material int not null
);