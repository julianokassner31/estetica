CREATE TABLE PACIENTE (
     id serial PRIMARY KEY,
     nome varchar(100) NOT NULL,
     cpf varchar(11) NOT NULL,
     nascimento date,
     celular varchar(13) NOT NULL,
     telefone varchar(13),
     email varchar(100) NOT NULL,
     rua varchar(100) NOT NULL,
     numero varchar(50) NOT NULL,
     bairro varchar(100) NOT NULL,
     cidade varchar(100) NOT NULL
)