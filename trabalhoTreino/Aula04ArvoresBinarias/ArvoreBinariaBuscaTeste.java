import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArvoreBinariaBuscaTeste {
    private ArvoreBinariaBusca arvore;

    private Dispositivo d20;
    private Dispositivo d60;
    private Dispositivo d16;
    private Dispositivo d19;
    private Dispositivo d14;
    private Dispositivo d70;
    private Dispositivo d40;

    @Before
    public void configurar() {
        arvore = new ArvoreBinariaBusca();

        d20 = new Dispositivo(20, "D20", "Sala", "°C", 50);
        d60 = new Dispositivo(60, "D60", "Quarto", "%", 40);
        d16 = new Dispositivo(16, "D16", "Sala", "°C", 30);
        d19 = new Dispositivo(19, "D19", "Quarto", "dB", 70);
        d14 = new Dispositivo(14, "D14", "Sala", "%", 25);
        d70 = new Dispositivo(70, "D19", "Quarto", "dB", 70);
        d40 = new Dispositivo(40, "D14", "Sala", "%", 25);

    }

    @Test
    public void testarEstaVaziaArvoreNova() {
        assertTrue(arvore.estaVazia());
    }

    @Test
    public void testarEstaVaziaAposInsercao() {
        arvore.inserir(d20);
        assertFalse(arvore.estaVazia());
    }

    @Test
    public void testarEstaVaziaAposRemocaoUnicoNo() {
        arvore.inserir(d20);
        arvore.remover(20);
        assertTrue(arvore.estaVazia());
    }

    @Test
    public void testarInserirEBuscarUnicoNo() {
        arvore.inserir(d20);
        assertTrue(arvore.existe(d20));
        assertFalse(arvore.existe(d20));
        assertTrue(arvore.buscar(20).folha());
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
         assertTrue(arvore.buscar(3).folha());
          assertTrue(arvore.buscar(7).folha());
    }

    @Test
    public void testarInserirDuplicado() {
        arvore.inserir(5);
        arvore.inserir(5); // Não deve alterar a estrutura
        assertEquals(1, arvore.quantidadeNos());
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
        assertEquals(1, arvore.quantidadeNos());
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
        assertEquals(2, arvore.quantidadeNos());
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
        assertEquals(4, arvore.quantidadeNos());
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
        assertEquals(11, arvore.quantidadeNos());
        assertEquals(6, arvore.raiz.esquerdo.valor);
    }

    @Test
    public void testarRemoverRaizUnica() {
        arvore.inserir(5);
        arvore.remover(5);
        assertFalse(arvore.existe(5));
        assertEquals(0, arvore.quantidadeNos());
    }

    @Test
    public void testarRemoverNaoExistente() {
        arvore.inserir(5);
        arvore.remover(10);
        assertTrue(arvore.existe(5));
        assertEquals(1, arvore.quantidadeNos());
    }

    @Test
    public void testarContarNosArvoreVazia() {
        assertEquals(0, arvore.quantidadeNos());
    }

    @Test
    public void testarContarNosUmNo() {
        arvore.inserir(5);
        assertEquals(1, arvore.quantidadeNos());
    }

    @Test
    public void testarContarNosArvoreGrande() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(7);
        assertEquals(5, arvore.quantidadeNos());
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
        assertEquals(3, arvore.calcularAlturaArvore());
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
        assertEquals(1, arvore.calcularAlturaNo(5));
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
        assertEquals(3, arvore.calcularProfundidadeArvore());
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
        assertEquals(2, arvore.calcularProfundidadeNo(1));
    }

    @Test
    public void testarCalcularProfundidadeNoIntermediario() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(3);
        assertEquals(1, arvore.calcularProfundidadeNo(5));
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
        assertEquals(6, arvore.quantidadeNos());
        assertFalse(arvore.existe(30));
        assertTrue(arvore.existe(40));
        assertEquals(2, arvore.calcularAlturaNo(50));
        assertEquals(1, arvore.calcularProfundidadeNo(70));
    }

    @Test
    public void testarArvoreComplexaAposRemocao() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(1);
        arvore.remover(15);
        assertEquals(4, arvore.quantidadeNos());
        assertEquals(3, arvore.calcularAlturaArvore());
        assertEquals(3, arvore.calcularProfundidadeNo(1));
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
        assertEquals(3, arvore.quantidadeNos());
        assertTrue(arvore.existe(10));
        assertTrue(arvore.existe(3));
        assertTrue(arvore.existe(7));
        assertEquals(2, arvore.calcularAlturaArvore());
    }
}