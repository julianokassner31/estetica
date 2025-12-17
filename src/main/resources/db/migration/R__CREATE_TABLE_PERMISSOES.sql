CREATE TABLE permissao (
    id serial PRIMARY KEY,
    nome varchar(20),
    descricao varchar(100)
);

CREATE TABLE usuario_permissao(
    id serial PRIMARY KEY,
    id_usuario int not null,
    id_permissao int not null,
    CONSTRAINT fk_up_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    CONSTRAINT fk_up_permissao FOREIGN KEY (id_permissao) REFERENCES permissao(id)
)