CREATE TABLE IF NOT EXISTS permissao (
    id serial PRIMARY KEY,
    nome varchar(20),
    descricao varchar(100)
);

CREATE TABLE IF NOT EXISTS usuario_permissao(
    id serial PRIMARY KEY,
    id_usuario int not null,
    id_permissao int not null,
    CONSTRAINT fk_up_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    CONSTRAINT fk_up_permissao FOREIGN KEY (id_permissao) REFERENCES permissao(id)
);

INSERT INTO permissao (nome, descricao)
    VALUES ('ADMINISTRADOR', 'Pode fazer qualquer alteração no sistema.'),
           ('ATENDIMENTO', 'Pode iniciar um atendimento.'),
           ('CADASTRO', 'Pode CRUD, produtos, material, atendimento, etc...'),
           ('LEITURA', 'Pode ler cadastros e atendimentos.'),
           ('USUARIO', 'Cliente das empresa, pode solicitar atendimento');