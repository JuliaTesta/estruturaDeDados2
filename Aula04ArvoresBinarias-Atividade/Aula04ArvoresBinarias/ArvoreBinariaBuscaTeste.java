import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ArvoreBinariaBuscaTeste {
    private ArvoreBinariaBusca arvore;

    //Instanciar como atributos da classe para todos os testes terem acesso aos objetos
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
        assertFalse(arvore.existe(d16));
        assertTrue(arvore.buscar(20).folha());
    }

    @Test
    public void testarInserirEBuscarMultiplosNos() {
     
        arvore.inserir(d20);
        arvore.inserir(d60);
        arvore.inserir(d16);
        assertTrue(arvore.existe(d20));
        assertTrue(arvore.existe(d60));
        assertTrue(arvore.existe(d16));
        assertFalse(arvore.existe(d19));
        assertTrue(arvore.buscar(60).folha());
        assertTrue(arvore.buscar(16).folha());
    }

    @Test
    public void testarInserirDuplicado() {
        arvore.inserir(d20);
        arvore.inserir(d20); // Não deve alterar a estrutura
        assertEquals(1, arvore.quantidadeNos());
        assertTrue(arvore.existe(d20));
    }

    @Test
    public void testarBuscarEmArvoreVazia() {
        assertFalse(arvore.existe(d20));
    }

    @Test
    public void testarRemoverNoFolha() {
        arvore.inserir(d20);
        arvore.inserir(d60);
        arvore.remover(60);
        assertTrue(arvore.existe(d20));
        assertFalse(arvore.existe(d60));
        assertEquals(1, arvore.quantidadeNos());
    }

    @Test
    public void testarRemoverNoComUmFilho() {
        arvore.inserir(d20);
        arvore.inserir(d60);
        arvore.inserir(d16);
        arvore.remover(60);
        assertTrue(arvore.existe(d20));
        assertTrue(arvore.existe(d16));
        assertFalse(arvore.existe(d60));
        assertEquals(2, arvore.quantidadeNos());
    }

    @Test
    public void testarRemoverNoComDoisFilhos() {
     
        arvore.inserir(d20);
        arvore.inserir(d60);
        arvore.inserir(d16);
        arvore.inserir(d70);
        arvore.inserir(d40);

        arvore.remover(60);
        assertTrue(arvore.existe(d20));
        assertTrue(arvore.existe(d16));
        assertTrue(arvore.existe(d70));
        assertTrue(arvore.existe(d40));

        assertFalse(arvore.existe(d60));
        assertEquals(4, arvore.quantidadeNos());
    }

    @Test
    public void testarRemoverNoComDoisFilhosSlide() {
     
        arvore.inserir(d20);
        arvore.inserir(d60);
        arvore.inserir(d16);
        arvore.inserir(d70);
        arvore.inserir(d40);
        
        arvore.remover(60);
        assertFalse(arvore.existe(d60));
        assertTrue(arvore.existe(d20));
        assertTrue(arvore.existe(d16));
        assertTrue(arvore.existe(d40));

        assertEquals(4, arvore.quantidadeNos());
        assertEquals(16, arvore.raiz.esquerdo.disp.id);
    }

    @Test
    public void testarRemoverRaizUnica() {
        arvore.inserir(d20);
        arvore.remover(20);
        assertFalse(arvore.existe(d20));
        assertEquals(0, arvore.quantidadeNos());
    }

    @Test
    public void testarRemoverNaoExistente() {
        arvore.inserir(d20);
        arvore.remover(60);
        assertTrue(arvore.existe(d20));
        assertEquals(1, arvore.quantidadeNos());
    }

    @Test
    public void testarContarNosArvoreVazia() {
        assertEquals(0, arvore.quantidadeNos());
    }

    @Test
    public void testarContarNosUmNo() {
        arvore.inserir(d20);
        assertEquals(1, arvore.quantidadeNos());
    }

    @Test
    public void testarContarNosArvoreGrande() {
        arvore.inserir(d20);
        arvore.inserir(d16);
        arvore.inserir(d70);
        arvore.inserir(d40);
        arvore.inserir(d19);
        assertEquals(5, arvore.quantidadeNos());
    }

    @Test
    public void testarCalcularAlturaArvoreVazia() {
        assertEquals(-1, arvore.calcularAlturaArvore());
    }

    @Test
    public void testarCalcularAlturaArvoreUmNo() {
        arvore.inserir(d20);
        assertEquals(0, arvore.calcularAlturaArvore());
    }

    @Test
    public void testarCalcularAlturaArvoreBalanceada() {
        arvore.inserir(d60);
        arvore.inserir(d20);
        arvore.inserir(d70);
        assertEquals(1, arvore.calcularAlturaArvore());
    }

    @Test
    public void testarCalcularAlturaArvoreDesbalanceada() {
        arvore.inserir(d70);
        arvore.inserir(d60);
        arvore.inserir(d40);
        arvore.inserir(d20);
        assertEquals(3, arvore.calcularAlturaArvore());
    }

    @Test
    public void testarCalcularAlturaNoInexistente() {
        assertEquals(-1, arvore.calcularAlturaNo(d20));
    }

    @Test
    public void testarCalcularAlturaNoRaiz() {
        arvore.inserir(d40);
        arvore.inserir(d20);
        arvore.inserir(d70);
        assertEquals(1, arvore.calcularAlturaNo(d40));
    }

    @Test
    public void testarCalcularAlturaNoFolha() {
        arvore.inserir(d40);
        arvore.inserir(d20);
        assertEquals(0, arvore.calcularAlturaNo(d20));
    }

    @Test
    public void testarCalcularAlturaNoIntermediario() {
        arvore.inserir(d70);
        arvore.inserir(d40);
        arvore.inserir(d20);
        assertEquals(1, arvore.calcularAlturaNo(d40));
    }

    @Test
    public void testarCalcularProfundidadeArvoreVazia() {
        assertEquals(-1, arvore.calcularProfundidadeArvore());
    }

    @Test
    public void testarCalcularProfundidadeArvoreUmNo() {
        arvore.inserir(d20);
        assertEquals(0, arvore.calcularProfundidadeArvore());
    }

    @Test
    public void testarCalcularProfundidadeArvoreDesbalanceada() {
        arvore.inserir(d70);
        arvore.inserir(d60);
        arvore.inserir(d40);
        arvore.inserir(d20);
        assertEquals(3, arvore.calcularProfundidadeArvore());
    }

    @Test
    public void testarCalcularProfundidadeNoInexistente() {
        assertEquals(-1, arvore.calcularProfundidadeNo(d20));
    }

    @Test
    public void testarCalcularProfundidadeNoRaiz() {
        arvore.inserir(d20);
        assertEquals(0, arvore.calcularProfundidadeNo(d20));
    }

    @Test
    public void testarCalcularProfundidadeNoFolha() {
        arvore.inserir(d70);
        arvore.inserir(d60);
        arvore.inserir(d40);
        assertEquals(2, arvore.calcularProfundidadeNo(d40));
    }

    @Test
    public void testarCalcularProfundidadeNoIntermediario() {
        arvore.inserir(d70);
        arvore.inserir(d60);
        arvore.inserir(d40);
        assertEquals(1, arvore.calcularProfundidadeNo(d60));
    }

    @Test
    public void testarImprimirPreOrdemArvoreVazia() {
        assertEquals("[]", arvore.imprimirPreOrdem());
    }

    @Test
    public void testarImprimirPreOrdemUmNo() {
        arvore.inserir(d20);
        assertEquals("[20]", arvore.imprimirPreOrdem());
    }

    @Test
    public void testarImprimirPreOrdem() {
        arvore.inserir(d60);
        arvore.inserir(d20);
        arvore.inserir(d16);
        arvore.inserir(d40);
        arvore.inserir(d70);
        String esperado = "[60, 20, 16, 40, 70]";
        assertEquals(esperado, arvore.imprimirPreOrdem());
    }

    @Test
    public void testarImprimirPosOrdemArvoreVazia() {
        assertEquals("[]", arvore.imprimirPosOrdem());
    }

    @Test
    public void testarImprimirPosOrdemUmNo() {
        arvore.inserir(d20);
        assertEquals("[20]", arvore.imprimirPosOrdem());
    }

    @Test
    public void testarImprimirPosOrdem() {
        arvore.inserir(d60);
        arvore.inserir(d20);
        arvore.inserir(d16);
        arvore.inserir(d40);
        arvore.inserir(d70);
        String esperado = "[16, 40, 20, 70, 60]";
        assertEquals(esperado, arvore.imprimirPosOrdem());
    }

    @Test
    public void testarImprimirInOrdemArvoreVazia() {
        assertEquals("[]", arvore.imprimirInOrdem());
    }

    @Test
    public void testarImprimirInOrdemUmNo() {
        arvore.inserir(d20);
        assertEquals("[20]", arvore.imprimirInOrdem());
    }

    @Test
    public void testarImprimirInOrdem() {
        arvore.inserir(d60);
        arvore.inserir(d20);
        arvore.inserir(d16);
        arvore.inserir(d40);
        arvore.inserir(d70);
       
        String esperado = "[16, 20, 40, 60, 70]";
        assertEquals(esperado, arvore.imprimirInOrdem());
    }

    @Test
    public void testarInsercaoRemocaoComplexa() {
        arvore.inserir(d60);
        arvore.inserir(d20);
        arvore.inserir(d16);
        arvore.inserir(d40);
        arvore.inserir(d70);
        arvore.remover(20);
        assertEquals(4, arvore.quantidadeNos());
        assertFalse(arvore.existe(d20));
        assertTrue(arvore.existe(d60));
        assertEquals(1, arvore.calcularAlturaNo(d40));
        assertEquals(1, arvore.calcularProfundidadeNo(d70));
    }

    @Test
    public void testarArvoreComplexaAposRemocao() {
        arvore.inserir(d60);
        arvore.inserir(d20);
        arvore.inserir(d16);
        arvore.inserir(d40);
        arvore.inserir(d70);
        arvore.remover(40);
        assertEquals(4, arvore.quantidadeNos());
        assertEquals(2, arvore.calcularAlturaArvore());
        assertEquals(2, arvore.calcularProfundidadeNo(d16));
    }

    @Test
    public void testarMultiplasRemocoes() {
        arvore.inserir(d60);
        arvore.inserir(d20);
        arvore.inserir(d16);
        arvore.inserir(d40);
        arvore.inserir(d70);
        arvore.remover(20);
        arvore.remover(40);
        assertEquals(3, arvore.quantidadeNos());
        assertTrue(arvore.existe(d16));
        assertTrue(arvore.existe(d60));
        assertTrue(arvore.existe(d70));
        assertEquals(1, arvore.calcularAlturaArvore());
    }

    //Novas funcionalidades

    @Test 
    public void percentualEmAlertaTest(){
        arvore.inserir(d20); //alerta = 50
        arvore.inserir(d14); //alerta = 25
        arvore.inserir(d16); //alerta = 30
        arvore.inserir(d60); //alerta = 40

        arvore.atualizarLeitura(20, 30); //abaixo
        arvore.atualizarLeitura(14, 20); //abaixo
        arvore.atualizarLeitura(16, 25); //abaixo
        arvore.atualizarLeitura(60, 35); //abaixo 

        assertEquals(0.0, arvore.percentualEmAlerta(), 0.01);

        //1 em alerta
        arvore.atualizarLeitura(20, 55); //acima
        assertEquals(25.00, arvore.percentualEmAlerta(), 0.01);

        //2 em alerta
        arvore.atualizarLeitura(16, 35); //acima
        assertEquals(50.0, arvore.percentualEmAlerta(), 0.01);

        //Todos em alerta
        arvore.atualizarLeitura(14, 30); // acima 
        arvore.atualizarLeitura(60, 45); // acima 
        assertEquals(100.0, arvore.percentualEmAlerta(), 0.01);
    }

    @Test
    public void limiteHistoricoLeiturasTest(){
        arvore.inserir(d20);

        arvore.atualizarLeitura(20, 10);
        arvore.atualizarLeitura(20, 20);
        arvore.atualizarLeitura(20, 30);
        arvore.atualizarLeitura(20, 40);
        arvore.atualizarLeitura(20, 50);
        arvore.atualizarLeitura(20, 60); // mais antiga (10) deve ser removida

        No novoNo = arvore.buscar(20);

        assertEquals(5, novoNo.historico.size());
        assertEquals(20, novoNo.historico.get(0).valor, 0.001);
        assertEquals(60, novoNo.historico.get(4).valor, 0.001);
    }

    @Test 
    public void importarCSVInexistenteTest(){
        arvore.importarCSV("nao_existe");
        assertTrue(arvore.estaVazia());        
    }

    @Test
    public void importarCSV() throws Exception{
        File tempCSV = File.createTempFile("teste", ".csv"); //temporario
        PrintWriter writer = new PrintWriter(tempCSV);

        //Conteudo CSV
        writer.println("10,D10,Sala,°C,50,23.5");
        writer.println("20,D20,Quarto,%,40,35");
        writer.close();

        //Criar Arvore
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        //Importar
        arvore.importarCSV(tempCSV.getAbsolutePath());

        //testar se dispositivos foram inseridos
        assertEquals(2, arvore.quantidadeNos());
        assertTrue(arvore.existe(new Dispositivo(10, "D10", "Sala", "°C", 50)));
        assertTrue(arvore.existe(new Dispositivo(20, "D20", "Quarto", "%", 40)));

        tempCSV.delete();
    }
}