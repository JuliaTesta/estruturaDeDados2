import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AVLTeste {
    private AVL arvore;

    @Before
    public void configurar() {
        arvore = new AVL();
    }

    @Test
    public void testarEstaVaziaArvoreNova() {
        assertTrue(arvore.estaVazia());
    }

    @Test
    public void testarEstaVaziaAposInsercao() {
        arvore.inserir(10);
        assertFalse(arvore.estaVazia());
    }

    @Test
    public void testarEstaVaziaAposRemocaoUnicoNo() {
        arvore.inserir(10);
        arvore.remover(10);
        assertTrue(arvore.estaVazia());
    }

    @Test
    public void testarInserirEBuscarUnicoNo() {
        arvore.inserir(5);
        assertTrue(arvore.existe(5));
        assertFalse(arvore.existe(10));
    }

    @Test
    public void testarInserirEBuscarMultiplosNos() {
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(7);
        assertTrue(arvore.existe(5));
        assertTrue(arvore.existe(3));
        assertTrue(arvore.existe(7));
        assertFalse(arvore.existe(4));
    }

    @Test
    public void testarInserirDuplicadoDeveIgnorar() {
        arvore.inserir(5);
        arvore.inserir(5); 
        assertEquals(1, arvore.contarNos());
        assertTrue(arvore.existe(5));
    }

    @Test
    public void testarBuscarEmArvoreVazia() {
        assertFalse(arvore.existe(1));
    }

    @Test
    public void testarRemoverNoFolha() {
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.remover(3);
        assertTrue(arvore.existe(5));
        assertFalse(arvore.existe(3));
        assertEquals(1, arvore.contarNos());
    }

    @Test
    public void testarRemoverNoComUmFilho() {
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(4);
        arvore.remover(3);
        assertTrue(arvore.existe(5));
        assertTrue(arvore.existe(4));
        assertFalse(arvore.existe(3));
        assertEquals(2, arvore.contarNos());
    }

    @Test
    public void testarRemoverNoComDoisFilhos() {
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(7);
        arvore.inserir(6);
        arvore.inserir(8);
        arvore.remover(7);
        assertTrue(arvore.existe(5));
        assertTrue(arvore.existe(3));
        assertTrue(arvore.existe(6));
        assertTrue(arvore.existe(8));
        assertFalse(arvore.existe(7));
        assertEquals(4, arvore.contarNos());
    }

    @Test
    public void testarRemoverNoComDoisFilhosSlide() {
        arvore.inserir(15);
        arvore.inserir(16);
        arvore.inserir(20);
        arvore.inserir(23);
        arvore.inserir(18);
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(12);
        arvore.inserir(13);
        arvore.inserir(10);
        arvore.inserir(6);
        arvore.inserir(7);

        arvore.remover(5);
        assertFalse(arvore.existe(5));
        assertTrue(arvore.existe(6));
        assertTrue(arvore.existe(7));
        assertEquals(11, arvore.contarNos());
    }

    @Test
    public void testarRemoverRaizUnica() {
        arvore.inserir(5);
        arvore.remover(5);
        assertFalse(arvore.existe(5));
        assertEquals(0, arvore.contarNos());
    }

    @Test
    public void testarRemoverNaoExistente() {
        arvore.inserir(5);
        arvore.remover(10);
        assertTrue(arvore.existe(5));
        assertEquals(1, arvore.contarNos());
    }

    @Test
    public void testarContarNosArvoreVazia() {
        assertEquals(0, arvore.contarNos());
    }

    @Test
    public void testarContarNosUmNo() {
        arvore.inserir(5);
        assertEquals(1, arvore.contarNos());
    }

    @Test
    public void testarContarNosArvoreGrande() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(7);
        assertEquals(5, arvore.contarNos());
    }

    @Test
    public void testarCalcularAlturaArvoreVazia() {
        assertEquals(-1, arvore.calcularAlturaArvore());
    }

    @Test
    public void testarCalcularAlturaArvoreUmNo() {
        arvore.inserir(5);
        assertEquals(0, arvore.calcularAlturaArvore());
    }

    @Test
    public void testarCalcularAlturaArvoreBalanceada() {
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(7);
        assertEquals(1, arvore.calcularAlturaArvore());
    }

    @Test
    public void testarCalcularAlturaArvoreDesbalanceada() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(1);
        assertEquals(2, arvore.calcularAlturaArvore()); // Balanceada pela AVL
    }

    @Test
    public void testarCalcularAlturaNoInexistente() {
        assertEquals(-1, arvore.calcularAlturaNo(5));
    }

    @Test
    public void testarCalcularAlturaNoRaiz() {
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(7);
        assertEquals(1, arvore.calcularAlturaNo(5));
    }

    @Test
    public void testarCalcularAlturaNoFolha() {
        arvore.inserir(5);
        arvore.inserir(3);
        assertEquals(0, arvore.calcularAlturaNo(3));
    }

    @Test
    public void testarCalcularAlturaNoIntermediario() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(7);
        assertEquals(2, arvore.calcularAlturaNo(5));
    }

    @Test
    public void testarCalcularProfundidadeArvoreVazia() {
        assertEquals(-1, arvore.calcularProfundidadeArvore());
    }

    @Test
    public void testarCalcularProfundidadeArvoreUmNo() {
        arvore.inserir(5);
        assertEquals(0, arvore.calcularProfundidadeArvore());
    }

    @Test
    public void testarCalcularProfundidadeArvoreDesbalanceada() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(1);
        assertEquals(2, arvore.calcularProfundidadeArvore()); 
    }

    @Test
    public void testarCalcularProfundidadeNoInexistente() {
        assertEquals(-1, arvore.calcularProfundidadeNo(5));
    }

    @Test
    public void testarCalcularProfundidadeNoRaiz() {
        arvore.inserir(5);
        assertEquals(0, arvore.calcularProfundidadeNo(5));
    }

    @Test
    public void testarCalcularProfundidadeNoFolha() {
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(1);
        assertEquals(1, arvore.calcularProfundidadeNo(1)); 
    }

    @Test
    public void testarCalcularProfundidadeNoIntermediario() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(3);
        assertEquals(0, arvore.calcularProfundidadeNo(5));
    }

    @Test
    public void testarImprimirPreOrdemArvoreVazia() {
        assertEquals("[]", arvore.imprimirPreOrdem());
    }

    @Test
    public void testarImprimirPreOrdemUmNo() {
        arvore.inserir(10);
        assertEquals("[10]", arvore.imprimirPreOrdem());
    }

    @Test
    public void testarImprimirPreOrdem() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(7);
        String esperado = "[10, 5, 3, 7, 15]";
        assertEquals(esperado, arvore.imprimirPreOrdem());
    }

    @Test
    public void testarImprimirPosOrdemArvoreVazia() {
        assertEquals("[]", arvore.imprimirPosOrdem());
    }

    @Test
    public void testarImprimirPosOrdemUmNo() {
        arvore.inserir(10);
        assertEquals("[10]", arvore.imprimirPosOrdem());
    }

    @Test
    public void testarImprimirPosOrdem() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(7);
        String esperado = "[3, 7, 5, 15, 10]";
        assertEquals(esperado, arvore.imprimirPosOrdem());
    }

    @Test
    public void testarImprimirInOrdemArvoreVazia() {
        assertEquals("[]", arvore.imprimirInOrdem());
    }

    @Test
    public void testarImprimirInOrdemUmNo() {
        arvore.inserir(10);
        assertEquals("[10]", arvore.imprimirInOrdem());
    }

    @Test
    public void testarImprimirInOrdem() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(7);
        String esperado = "[3, 5, 7, 10, 15]";
        assertEquals(esperado, arvore.imprimirInOrdem());
    }

    @Test
    public void testarInsercaoRemocaoComplexa() {
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);
        arvore.remover(30);
        assertEquals(6, arvore.contarNos());
        assertFalse(arvore.existe(30));
        assertTrue(arvore.existe(40));
        assertEquals(2, arvore.calcularAlturaNo(50));
        assertEquals(1, arvore.calcularProfundidadeNo(70));
    }

    @Test
    public void testarArvoreComplexaAposRemocao() {
        arvore.inserir(10);
        arvore.imprimirArvoreTexto();
        arvore.inserir(5);
        arvore.imprimirArvoreTexto();
        arvore.inserir(15);
        arvore.imprimirArvoreTexto();
        arvore.inserir(3);
        arvore.imprimirArvoreTexto();
        arvore.inserir(1);
        arvore.imprimirArvoreTexto();
        arvore.remover(15);
        arvore.imprimirArvoreTexto();
        assertEquals(4, arvore.contarNos());
        assertEquals(2, arvore.calcularAlturaArvore());
        assertEquals(1, arvore.calcularProfundidadeNo(1));
    }

    @Test
    public void testarMultiplasRemocoes() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(7);
        arvore.remover(5);
        arvore.remover(15);
        assertEquals(3, arvore.contarNos());
        assertTrue(arvore.existe(10));
        assertTrue(arvore.existe(3));
        assertTrue(arvore.existe(7));
        assertEquals(1, arvore.calcularAlturaArvore());
    }

    @Test
    public void testarGetAlturaNo() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        assertEquals(1, arvore.getAlturaNo(10)); // Raiz com dois filhos
        assertEquals(0, arvore.getAlturaNo(5)); // Folha
        assertEquals(0, arvore.getAlturaNo(15)); // Folha
        assertEquals(-1, arvore.getAlturaNo(20)); // Nó inexistente
    }

    @Test
    public void testarGetFatorBalanceamento() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        assertEquals(0, arvore.getFatorBalanceamento(10)); // Balanceado
        assertEquals(0, arvore.getFatorBalanceamento(5)); // Sem filhos
        assertEquals(0, arvore.getFatorBalanceamento(15)); // Sem filhos
    }

    @Test
    public void testarRotacaoDireitaAposInsercao() {
        arvore.inserir(30);
        arvore.inserir(20);
        arvore.inserir(10); 
        assertEquals(20, arvore.buscar(20).valor); 
        assertEquals(10, arvore.buscar(20).esquerdo.valor);
        assertEquals(30, arvore.buscar(20).direito.valor);
        assertEquals(1, arvore.calcularAlturaArvore());
        assertTrue(Math.abs(arvore.getFatorBalanceamento(20)) <= 1);
    }

    @Test
    public void testarRotacaoEsquerdaAposInsercao() {
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(30); 
        assertEquals(20, arvore.buscar(20).valor); 
        assertEquals(10, arvore.buscar(20).esquerdo.valor);
        assertEquals(30, arvore.buscar(20).direito.valor);
        assertEquals(1, arvore.calcularAlturaArvore());
        assertTrue(Math.abs(arvore.getFatorBalanceamento(20)) <= 1);
    }

    @Test
    public void testarRotacaoDuplaEsquerdaDireitaAposInsercao() {
        arvore.inserir(30);
        arvore.inserir(10);
        arvore.inserir(20); 
        assertEquals(20, arvore.buscar(20).valor); 
        assertEquals(10, arvore.buscar(20).esquerdo.valor);
        assertEquals(30, arvore.buscar(20).direito.valor);
        assertEquals(1, arvore.calcularAlturaArvore());
        assertTrue(Math.abs(arvore.getFatorBalanceamento(20)) <= 1);
    }

    @Test
    public void testarRotacaoDuplaDireitaEsquerdaAposInsercao() {
        arvore.inserir(10);
        arvore.inserir(30);
        arvore.inserir(20); 
        assertEquals(20, arvore.buscar(20).valor); 
        assertEquals(10, arvore.buscar(20).esquerdo.valor);
        assertEquals(30, arvore.buscar(20).direito.valor);
        assertEquals(1, arvore.calcularAlturaArvore());
        assertTrue(Math.abs(arvore.getFatorBalanceamento(20)) <= 1);
    }

    @Test
    public void testarBalanceamentoAposMultiplasInsercoes() {
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);
        assertEquals(2, arvore.calcularAlturaArvore()); 
        assertTrue(Math.abs(arvore.getFatorBalanceamento(50)) <= 1); 
        assertEquals(7, arvore.contarNos());
    }

    @Test
    public void testarRotacaoDireitaAposRemocao() {
        arvore.inserir(20);
        arvore.inserir(10);
        arvore.inserir(30);
        arvore.inserir(5);
        arvore.remover(30); 
        assertEquals(10, arvore.buscar(10).valor); 
        assertEquals(5, arvore.buscar(10).esquerdo.valor);
        assertEquals(20, arvore.buscar(10).direito.valor);
        assertEquals(1, arvore.calcularAlturaArvore());
    }

    @Test
    public void testarRotacaoEsquerdaAposRemocao() {
        arvore.inserir(20);
        arvore.inserir(10);
        arvore.inserir(30);
        arvore.inserir(40);
        arvore.remover(10); 
        assertEquals(30, arvore.buscar(30).valor); 
        assertEquals(20, arvore.buscar(30).esquerdo.valor);
        assertEquals(40, arvore.buscar(30).direito.valor);
        assertEquals(1, arvore.calcularAlturaArvore());
    }

    @Test
    public void testarBalanceamentoAposMultiplasRemocoes() {
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);
        arvore.remover(20);
        arvore.remover(80);
        assertEquals(2, arvore.calcularAlturaArvore());
        assertTrue(Math.abs(arvore.getFatorBalanceamento(50)) <= 1);
        assertEquals(5, arvore.contarNos());
    }

    @Test 
    public void testEstritamenteBinaria(){
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(60);
        arvore.inserir(59);
        arvore.inserir(70);
        assertTrue(arvore.estritamenteBinaria());
    }

    @Test 
    public void testNaoEstritamenteBinaria(){
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(31);
        arvore.inserir(60);
        arvore.inserir(59);
        arvore.inserir(70);
        assertFalse(arvore.estritamenteBinaria());
    }
    
    @Test
    public void testImprimirNosNivel(){
        No raiz = new No(10);
        raiz.esquerdo = new No(5);
        raiz.direito = new No(15);
        raiz.esquerdo.esquerdo = new No(3);
        raiz.esquerdo.direito = new No(7);
        raiz.direito.esquerdo = new No(12);
        raiz.direito.direito = new No(17);

        AVL arvore = new AVL();
        arvore.raiz = raiz;

        System.out.println("Nivel 1:");
        arvore.imprimirNosNivel(1);
    }

    @Test
    public void estBinariaTest(){
        No raiz = new No(30);
        raiz.esquerdo = new No(29);
        raiz.direito = new No(45);
        raiz.direito.direito = new No(47);
        raiz.direito.esquerdo = new No(43);
        raiz.direito.esquerdo.esquerdo = new No(41);
        raiz.direito.esquerdo.esquerdo.direito = new No(44);

        AVL arvore = new AVL();
        arvore.raiz = raiz;

        System.out.println("EstritamenteBinaria");
        arvore.estritamenteBinaria();
    }

    @Test
    public void binariaCompTest(){
        No raiz = new No(30);
        raiz.esquerdo = new No(26);
        raiz.esquerdo.esquerdo = new No(27);
        raiz.direito = new No(45);
        raiz.direito.direito = new No(47);
        raiz.direito.esquerdo = new No(43);
        raiz.direito.esquerdo.esquerdo = new No(41); 
        raiz.direito.esquerdo.esquerdo.direito = new No(44);
        

        AVL arvore = new AVL();
        arvore.raiz = raiz;

        System.out.println("Binaria completa");
        arvore.completa();
    }

    @Test
    public void cheiaTest(){
        No raiz = new No(30);
        raiz.esquerdo = new No(26);
        raiz.esquerdo.esquerdo = new No(27);
        raiz.direito = new No(45);
        raiz.direito.direito = new No(47);
        raiz.direito.esquerdo = new No(43);        

        AVL arvore = new AVL();
        arvore.raiz = raiz;

        System.out.println("Binaria Cheia");
        arvore.cheia();
    }
    
}