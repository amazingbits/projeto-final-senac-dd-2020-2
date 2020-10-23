# Projeto Final Senac - Disciplina de Desenvolvimento de Software (2020/2)

### A tarefa

A tarefa consiste em desenvolver uma aplicação utilizando os assuntos que
abordamos durante o semestre. Utilizaremos a linguagem de programação JAVA e
a arquitetura de projeto MVC. Para as telas utilizaremos o Java Swing.

### A equipe

- [Guilherme V. de Andrade](https://github.com/amazingbits)
- [Jadson dos Santos Silva](https://github.com/jadson179)

### O projeto

Para abstrairmos o que foi ensinado durante o semestre, decidimos desenvolver
uma pequena aplicação de controle de estoque. Ela será capaz de cadastrar
produtos e efetuar lançamentos.

### O quê precisa ser construído?

- [x] Requisitos funcionais e não funcionais
- [x] Modelos das entidades do banco de dados
- [x] Diagrama de classes
- [x] Construir as classes de entidade (VO)
- [x] Construir as classes de persistência (DAO)
- [ ] Construir as classes de regra de negócio (BO)
- [ ] Construir os controladores
- [ ] Construir as telas (JPanel) separadamente e implementar suas regras
- [x] Construir a tela principal (JFrame) com o menu de navegação do sistema

### Regras de nomenclatura

- **Linguagem** - português brasileiro (questão didática).
- **Pacotes** - {empresa}.{nome do projeto}.{nome do pacote}. Ex: senac.financeiro.controller.
- **Classes** - Pascal case. Geralmente com o primeiro nome sendo um substantivo. Ex: UsuarioController.
- **Métodos** - Camel case. Geralmente começando com um verbo no infinitivo. Ex: cadastrarUsuario().
- **Constantes** - Uppercase separados por underline. Ex: MENSAGEM_PADRAO.
- **Variáveis** - Camel case. Ex: dataDeNascimento.

### Arquitetura

![Arquitetura do projeto](./arquitetura/arquitetura.png)

### Requisitos

- CRUD de produtos
- CRUD de lançamentos dos produtos do estoque
- Informar últimas saídas
- Informar últimas entradas
- Arquitetura MVC
- Uma tela para cada operação de cada entidade
- Pelo menos uma tela de busca de produtos cadastrados
- Linguagem Java no back-end
- Java Swing no front-end
- SQBG MySQL

### Modelo SGBD

![Banco de dados](./db/modelo.png)

```sql
drop database if exists db_estoque;
create database db_estoque;
use db_estoque;

create table if not exists tb_categoria(
	id int not null auto_increment primary key,
    descricao text
);

insert into tb_categoria values (NULL, "Escritório");
insert into tb_categoria values (NULL, "Informática");
insert into tb_categoria values (NULL, "Lazer");

create table if not exists tb_setor(
	id int not null auto_increment primary key,
    descricao text
);

insert into tb_setor values (NULL, "Administração");
insert into tb_setor values (NULL, "TI");
insert into tb_setor values (NULL, "Diretoria");

create table if not exists tb_tipo_lancamento(
	id int not null auto_increment primary key,
    descricao text
);

insert into tb_tipo_lancamento values (NULL, "Entrada");
insert into tb_tipo_lancamento values (NULL, "Saída");

create table if not exists tb_produto(
	id int not null auto_increment primary key,
    descricao text,
    categoria int not null,
    quantidade int default 0,
    preco numeric(10,2) default 0,
    data_ultima_entrada date default null,
    data_ultima_saida date default null,
    constraint fk_produto_categoria
    foreign key (categoria) references tb_categoria(id)
);

create table if not exists tb_lancamento(
	id int not null auto_increment primary key,
    idproduto int not null,
    idsetor int not null,
    tipo int not null,
    quantidade int not null,
    preco_total numeric(10,2) default 0,
    data date,
    constraint fk_lancamento_tipo
    foreign key (tipo) references tb_tipo_lancamento(id),
    constraint fk_lancamento_produto
    foreign key (idproduto) references tb_produto(id),
    constraint fk_lancamento_setor
    foreign key (idsetor) references tb_setor(id)
);

create table if not exists tb_produto_log(
	idproduto int,
    quantidade int,
    operacao text,
    data date
);

create table if not exists tb_lancamento_log(
	idproduto int,
    quantidade int,
    idtipo int,
    descricaotipo text,
    operacao text,
    data date
);

create view vw_produto_log as
select 	tb_produto.descricao as produto,
        tb_produto_log.quantidade as quantidade,
        tb_produto_log.operacao as operacao,
        date_format(tb_produto_log.data, "%d/%m/%Y") as data
from tb_produto_log
inner join tb_produto on
(tb_produto_log.idproduto = tb_produto.id)
order by tb_produto_log.data DESC;

create view vw_lancamento_log as
select 	tb_lancamento_log.idproduto as codigoproduto,
		tb_produto.descricao as produto,
        tb_lancamento_log.descricaotipo as tipo,
        tb_lancamento_log.quantidade as quantidade,
        tb_lancamento_log.operacao as operacao,
        date_format(tb_lancamento_log.data, "%d/%m/%Y") as data
from tb_lancamento_log
inner join tb_produto on
(tb_lancamento_log.idproduto = tb_produto.id)
order by data DESC;

create view vw_produto as
select 	tb_produto.id as codigo,
		tb_produto.descricao as descricao,
        tb_produto.preco as preco,
        tb_categoria.descricao as categoria,
        tb_produto.quantidade as quantidade,
        date_format(tb_produto.data_ultima_entrada, "%d/%m/%Y") as ultima_entrada,
        date_format(tb_produto.data_ultima_saida, "%d/%m/%Y") as ultima_saida
from tb_produto
inner join tb_categoria on
(tb_produto.categoria = tb_categoria.id)
order by tb_produto.id desc;

create view vw_lancamento as
select 	tb_lancamento.id as codigo,
		tb_lancamento.idproduto as codigoproduto,
        tb_lancamento.preco_total as preco_total,
        tb_produto.descricao as produto,
        tb_setor.descricao as setor,
        tb_tipo_lancamento.descricao as tipo,
        tb_lancamento.quantidade as quantidade,
        date_format(tb_lancamento.data, "%d/%m/%Y") as data
from tb_lancamento
inner join tb_produto on
(tb_lancamento.idproduto = tb_produto.id)
inner join tb_setor on
(tb_lancamento.idsetor = tb_setor.id)
inner join tb_tipo_lancamento on
(tb_lancamento.tipo = tb_tipo_lancamento.id)
order by tb_lancamento.id desc;

DELIMITER $$

create trigger tg_tb_produto_bi before insert on tb_produto for each row
begin
	declare vProduto int;

    select count(*) into vProduto from tb_produto where tb_produto.id = new.id;

    if(vProduto > 0) then
		signal sqlstate "45000" set message_text = "Este produto já existe no banco de dados!";
    end if;

end $$

create trigger tg_tb_produto_ai after insert on tb_produto for each row
begin
	 insert into tb_produto_log values (new.id, new.quantidade, "Inserção", NOW());
end $$

create trigger tg_tb_produto_ad after delete on tb_produto for each row
begin
	insert into tb_produto_log values (old.id, old.quantidade, "Remoção", NOW());
end $$


create trigger tg_tb_lancamento_bi before insert on tb_lancamento for each row
begin
	declare vQuantidadeEstoque int;
    declare vTipoLancamento int;
    declare vQuantidadeAtual int;
    declare vDescricaoTipo text;
    declare vValorTotal numeric(10,2);
    declare vValorProduto numeric(10,2);

    select preco into vValorProduto from tb_produto where id = new.idproduto;
    set vValorTotal = new.quantidade * vValorProduto;
    set new.preco_total = vValorTotal;

    select descricao into vDescricaoTipo from tb_tipo_lancamento where id = new.tipo;

    select quantidade into vQuantidadeEstoque from tb_produto where id = new.idproduto;

    set vTipoLancamento = new.tipo;

    if(vTipoLancamento = 2 and new.quantidade > vQuantidadeEstoque) then
		signal sqlstate "45000" set message_text = "Não há quantidade suficiente em estoque!";
    end if;

    if(vTipoLancamento = 1) then
		set vQuantidadeAtual = vQuantidadeEstoque + new.quantidade;
        update tb_produto set data_ultima_entrada = NOW() where id = new.idproduto;
    else
		set vQuantidadeAtual = vQuantidadeEstoque - new.quantidade;
        update tb_produto set data_ultima_saida = NOW() where id = new.idproduto;
    end if;

    update tb_produto set quantidade = vQuantidadeAtual where id = new.idproduto;
    insert into tb_lancamento_log values (new.idproduto, new.quantidade, new.tipo, vDescricaoTipo, "Inserção", NOW());

end $$

create trigger tg_tb_lancamento_ad after delete on tb_lancamento for each row
begin
	declare vQuantidadeEstoqueAtual int;
    declare vQuantidadeEstoque int;
    declare vDescricaoTipo text;

    select descricao into vDescricaoTipo from tb_tipo_lancamento where id = old.tipo;

    select quantidade into vQuantidadeEstoqueAtual from tb_produto where id = old.idproduto;
    set vQuantidadeEstoque = vQuantidadeEstoqueAtual + old.quantidade;
    update tb_produto set quantidade = vQuantidadeEstoque where id = old.idproduto;
    insert into tb_lancamento_log values (old.idproduto, old.quantidade, old.tipo, vDescricaoTipo, "Remoção", NOW());
end $$

DELIMITER ;
```

### Diagrama de classe

![Diagrama de Classe](./diagramas/diagrama.png)

### Atualizações

- **23/10/2020**: foi constuída uma rotina de cadastro de categorias. Para isso, construímos uma classe CategoriaController, CategoriaBO e uma tela na view que se chama CadastrarCategoria.java.
- **23/10/2020**: foi construída a tela principal (JFrame) com a estrutura de menu e uma imagem ilustrativa de
  apresentação para que a tela não fique vazia. O padrão de largura de janelas por enquanto é de 650.
