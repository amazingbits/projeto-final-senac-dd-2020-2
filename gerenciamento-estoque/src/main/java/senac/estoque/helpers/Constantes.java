package senac.estoque.helpers;

public class Constantes {
    // CATEGORIA
    public final static String MENSAGEM_VALIDACAO_NOME_CATEGORIA = "A categoria deve ter pelo menos 3 caracteres!";
    public final static String MENSAGEM_VALIDACAO_SE_EXISTE_CATEGORIA = "Já existe uma categoria com este nome no banco de dados!";
    public final static String MENSAGEM_CATEGORIA_CADASTRADA = "Categoria cadastrada com sucesso!";

    //LANCAMENTO 
    public final static String MENSAGEM_VALIDACAO_FK_PRODUTO = "O produto não pode estar vazio";
    public final static String MENSAGEM_VALIDACAO_FK_SETOR = "O setor não pode estar vazio";
    public final static String MENSAGEM_VALIDACAO_FK_TIPO = "O tipo de lancamento não pode estar vazio";
    public final static String MENSAGEM_VALIDACAO_LANCAMENTO_QUANTIDADE = "A quantidade não pode ser menor que 0 e somente aceita números";
    public final static String MENSAGEM_FALHA_CADASTRO_LANCAMENTO = "Não foi possivel cadastrar o Lancamento";
    public final static String MENSAGEM_SUCESSO_CADASTRO_LANCAMENTO = "Sucesso em cadastrar o Lancamento";

    //PRODUTO
    public final static String MENSAGEM_VALIDACAO_SE_EXISTE_PRODUTO = "O produto já foi cadastrado";
    public final static String MENSAGEM_VALIDACAO_PRODUTO_QUANTIDADE = "Somente é permitido valores a cima ou igual a 0";
    public final static String MENSAGEM_VALIDACAO_PRODUTO_PRECO = "Somente é permitido valores a cima ou igual a 0";
    public final static String MENSAGEM_VALIDACAO_PRODUTO_DESCRICAO = "A descrição deve conter mais de 3 caracteres";
    public final static String MENSAGEM_VALIDACAO_PRODUTO_CATEGORIA_DESCRICAO = "A categória deve conter mais de 3 caracteres";
    public final static String MENSAGEM_SUCESSO_CADASTRO_PRODUTO = "Sucesso em cadastrar o produto";
    public final static String MENSAGEM_FALHA_CADASTRO_PRODUTO = "Erro em cadastrar o produto";

    //SETOR
    public final static String MENSAGEM_VALIDACAO_SE_EXISTE_SETOR = "O Setor já foi cadastrado";
    public final static String MENSAGEM_VALIDACAO_SETOR_DESCRICAO = "O setor deve conter um nome e o mesmo precisa ter mais de 3 letras";
    public final static String MENSAGEM_FALHA_CADASTRO_SETOR = "Erro ao cadastrar o setor";
    public final static String MENSAGEM_SUCESSO_CADASTRO_SETOR = "Sucesso em cadastrar o Setor";

    // SOBRE 

    public final static String MENSAGEM_SOBRE_CONTENT = "Este é um sistema simples de controle de estoque. A ideia de desenvolver "
                + "este sistema é abstrair tudo o que foi ensinado na terceira fase do curso de"
                + "Análise e Desenvolvimento de Sistemas do SENAC do ano 2020/2."; 
    
    // PAGINAÇÃO
    public final static int ITEM_POR_PAGINA = 10;

}
