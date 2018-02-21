--PRECISO VER UMA FORMA DE SO PRECISAR PASSAR ESSE ROLE E NAO FICAR ATUALIZADNO O GRUPO NUNCA MAIS
insert into acao(id, nome, descricao, controller, ativo) values (1,'ROLE_ADMIN','DEUS DO SISTEMA !!!', 'ADMIN', 'S');

insert into acao(id, nome, descricao, controller, ativo) values (2,'ROLE_GRUPO_LISTAR','Acao cadastrada para testes','GrupoController','S');
insert into acao(id, nome, descricao, controller, ativo) values (3,'ROLE_GRUPO_EXCLUIR','Acao cadastrada para testes','GrupoController','S');
insert into acao(id, nome, descricao, controller, ativo) values (4,'ROLE_GRUPO_INSERIR','Acao cadastrada para testes','GrupoController','S');
insert into acao(id, nome, descricao, controller, ativo) values (5,'ROLE_GRUPO_ALTERAR','Acao cadastrada para testes','GrupoController','S');
insert into acao(id, nome, descricao, controller, ativo) values (6,'ROLE_GRUPO_PESQUISAR_ID','Acao cadastrada para testes','GrupoController','S');

insert into acao(id, nome, descricao, controller, ativo) values (7,'ROLE_USUARIO_LISTAR','Acao cadastrada para testes','UsuarioController','S');
insert into acao(id, nome, descricao, controller, ativo) values (8,'ROLE_USUARIO_EXCLUIR','Acao cadastrada para testes','UsuarioController','S');
insert into acao(id, nome, descricao, controller, ativo) values (9,'ROLE_USUARIO_INSERIR','Acao cadastrada para testes','UsuarioController','S');
insert into acao(id, nome, descricao, controller, ativo) values (10,'ROLE_USUARIO_ALTERAR','Acao cadastrada para testes','UsuarioController','S');
insert into acao(id, nome, descricao, controller, ativo) values (11,'ROLE_USUARIO_PESQUISAR_ID','Acao cadastrada para testes','UsuarioController','S');

insert into acao(id, nome, descricao, controller, ativo) values (12,'ROLE_ACAO_LISTAR','Acao cadastrada para testes','UsuarioController','S');
insert into acao(id, nome, descricao, controller, ativo) values (13,'ROLE_ACAO_EXCLUIR','Acao cadastrada para testes','UsuarioController','S');
insert into acao(id, nome, descricao, controller, ativo) values (14,'ROLE_ACAO_INSERIR','Acao cadastrada para testes','UsuarioController','S');
insert into acao(id, nome, descricao, controller, ativo) values (15,'ROLE_ACAO_ALTERAR','Acao cadastrada para testes','UsuarioController','S');
insert into acao(id, nome, descricao, controller, ativo) values (16,'ROLE_ACAO_PESQUISAR_ID','Acao cadastrada para testes','UsuarioController','S');

insert into grupo(id, nome, descricao, ativo) values (1,'ADMIN', 'Grupo liberado para o suporte e desenvolvedores, pois a pessoa que possui esse podere se assemelha a DEUS !!!', 'S');
insert into grupo_acao(idgrupo, idacao) values (1,1);
insert into grupo_acao(idgrupo, idacao) values (1,2);
insert into grupo_acao(idgrupo, idacao) values (1,3);
insert into grupo_acao(idgrupo, idacao) values (1,4);
insert into grupo_acao(idgrupo, idacao) values (1,5);
insert into grupo_acao(idgrupo, idacao) values (1,6);
insert into grupo_acao(idgrupo, idacao) values (1,7);
insert into grupo_acao(idgrupo, idacao) values (1,8);
insert into grupo_acao(idgrupo, idacao) values (1,9);
insert into grupo_acao(idgrupo, idacao) values (1,10);
insert into grupo_acao(idgrupo, idacao) values (1,11);
insert into grupo_acao(idgrupo, idacao) values (1,12);
insert into grupo_acao(idgrupo, idacao) values (1,13);
insert into grupo_acao(idgrupo, idacao) values (1,14);
insert into grupo_acao(idgrupo, idacao) values (1,15);
insert into grupo_acao(idgrupo, idacao) values (1,16);


insert into grupo(id, nome, descricao, ativo) values (2,'AUXILIAR ADMINISTRATIVO', 'Perfil voltado para funcionario que exercem essa função e trabalham com os módulos voltados para o setor de operação da empresa como por exemplo: Solicitação de Compras, Força de Trabalho Diário', 'S');
insert into grupo_acao(idgrupo, idacao) values (2,2);

insert into usuario(id, nome, login, senha, ativo, idgrupo) values (1, 'administrador do sistema', 'admin','123','S',1);
insert into usuario(id, nome, login, senha, ativo, idgrupo) values (2,'thiano pereira lima', 'thiano','123','S',2);

