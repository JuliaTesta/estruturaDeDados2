import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TabelaHashTest {

     private TabelaHash tabela;

     @BeforeEach
     public void setup() {
         tabela = new TabelaHash(17);
     }

     @Test
     public void deveAdicionarEBuscarClienteSemColisao() {
         Cliente cliente = new Cliente(22, "Fernando", "Avenida Brasil, 200");
         tabela.inserir(cliente);
         Cliente encontrado = tabela.buscarClientePorCodigo(22);
         assertNotNull(encontrado);
         assertEquals("Fernando", encontrado.nome);
     }

     @Test
     public void deveLidarComColisaoUtilizandoListas() {
         Cliente cliente1 = new Cliente(22, "Fernando", "Avenida Brasil, 200");
         Cliente cliente2 = new Cliente(3641, "Andre", "Rua XV de Novembro, 43");

         tabela.inserir(cliente1);
         tabela.inserir(cliente2);

         assertEquals("Fernando", tabela.buscarClientePorCodigo(22).nome);
         assertEquals("Andre", tabela.buscarClientePorCodigo(3641).nome);
         tabela.imprimirClientes();
     }

     @Test
     public void deveIgnorarClienteComMesmoCodigo() {
         Cliente cliente1 = new Cliente(22, "Fernando", "Avenida Brasil, 200");
         Cliente cliente2 = new Cliente(22, "Fernando Atualizado", "Nova Rua, 123");

         tabela.inserir(cliente1);
         tabela.inserir(cliente2);

         Cliente encontrado = tabela.buscarClientePorCodigo(22);
         assertNotNull(encontrado);
         assertEquals("Fernando", encontrado.nome);
     }

         @Test
     public void deveLancarExcecaoAoAdicionarClienteNulo() {
         assertThrows(IllegalArgumentException.class, () -> tabela.inserir(null));
     }

     @Test
     public void deveLancarExcecaoSeTamanhoInvalido() {
         assertThrows(IllegalArgumentException.class, () -> new TabelaHash(0));
     }

     @Test
     public void deveRetornarNullParaCodigoInexistente() {
         assertNull(tabela.buscarClientePorCodigo(888));
     }

     @Test
    public void deveRemoverClienteExistente() {

    Cliente cliente = new Cliente(22, "Fernando", "Avenida Brasil, 200");

    tabela.inserir(cliente);
    tabela.remover(22);

    Cliente encontrado = tabela.buscarClientePorCodigo(22);
    assertNull(encontrado);
    }

    @Test
    public void naoDeveFalharAoRemoverClienteInexistente() {

    tabela.remover(999); // não existe

    // se não lançar erro, já está correto
    assertTrue(true);
    }

    @Test
    public void deveRemoverClienteMesmoComColisao() {

    Cliente cliente1 = new Cliente(22, "Fernando", "Avenida Brasil, 200");
    Cliente cliente2 = new Cliente(3641, "Andre", "Rua XV de Novembro, 43");

    tabela.inserir(cliente1);
    tabela.inserir(cliente2);

    tabela.remover(22);

    assertNull(tabela.buscarClientePorCodigo(22));
    assertNotNull(tabela.buscarClientePorCodigo(3641));
    }

    @Test
    public void deveImprimirClientesNoConsole() {

    Cliente cliente1 = new Cliente(22, "Fernando", "Avenida Brasil, 200");
    Cliente cliente2 = new Cliente(30, "Ana", "Rua A");

    tabela.inserir(cliente1);
    tabela.inserir(cliente2);

    tabela.imprimirClientes(); //isso já imprime no console
    }

    @Test
    public void deveContarIndicesComColisao(){
        //5 posicoes para facilitar colisoes
        TabelaHash tabela = new TabelaHash(5);

        // Inserindo clientes
    Cliente cliente1 = new Cliente(1, "Ana", "Rua A");
    Cliente cliente2 = new Cliente(6, "Bruno", "Rua B"); 
    Cliente cliente3 = new Cliente(2, "Carlos", "Rua C");

    tabela.inserir(cliente1);
    tabela.inserir(cliente2);
    tabela.inserir(cliente3);

    int colisoes = tabela.contarIndicesComColisao();

    assertTrue(colisoes > 0);

    // Agora tabela sem colisão
    TabelaHash tabela2 = new TabelaHash(10);
    tabela2.inserir(new Cliente(10, "Ana", "Rua A"));
    tabela2.inserir(new Cliente(20, "Bruno", "Rua B"));
    tabela2.inserir(new Cliente(30, "Carlos", "Rua C"));

    int colisoes2 = tabela2.contarIndicesComColisao();
    assertEquals(0, colisoes2);
    }

    @Test
    public void retornarMaiorClientePorIndice(){
        TabelaHash tabela = new TabelaHash(5);

        Cliente c1 = new Cliente(1, "Ana", "Rua A");// 1 % 5 = 1
        Cliente c2 = new Cliente(6, "Bruno", "Rua B");// 6 % 5 = 1, colisão garantida
        Cliente c3 = new Cliente(3, "Carlos", "Rua C"); 

        tabela.inserir(c1);
        tabela.inserir(c2);
        tabela.inserir(c3);

        Cliente[] maiores = tabela.maioresPorIndice();

        //No indice de c1 e c2, o maior é c2
        int indiceC1 = tabela.calcularHash(c1.codigo);
        assertEquals(6, maiores[indiceC1].codigo);

        //Indice 3 contem ele mesmo
        int indiceC3 = tabela.calcularHash(c3.codigo);
        assertEquals(3, maiores[indiceC3].codigo);
    }

    @Test
    public void deveContarClientesPorInicial(){
        Cliente c1 = new Cliente(1, "Ana", "Rua A");
        Cliente c2 = new Cliente(2, "Alice", "Rua B");
        Cliente c3 = new Cliente(3, "Bruno", "Rua C");
        Cliente c4 = new Cliente(4, "ana", "Rua D"); // minúscula, deve contar

        tabela.inserir(c1);
        tabela.inserir(c2);
        tabela.inserir(c3);
        tabela.inserir(c4);

        int totalA = tabela.contarClientesPorInicial('A');
        int totalB = tabela.contarClientesPorInicial('B');
        int totalC = tabela.contarClientesPorInicial('C');

        assertEquals(3, totalA); // Ana, Alice, ana
        assertEquals(1, totalB); // Bruno
        assertEquals(0, totalC); // nenhum cliente começa com C
    }

    @Test
    public void testInserirElementosABB(){
        TabelaHash tabela = new TabelaHash(5);

        tabela.inserir(new Cliente(10, "Julia", "oioi"));
        tabela.inserir(new Cliente(22, "Carlos", "bbb"));
        tabela.inserir(new Cliente(35, "Ana", "ccc"));
        tabela.inserir(new Cliente(17, "Pedro", "ddd"));
        tabela.inserir(new Cliente(28, "Maria", "aaa"));

        ArvoreBinariaBusca abb = tabela.transferir();
        assertNotNull(abb);

        assertTrue(abb.contem(10));
    }
}
