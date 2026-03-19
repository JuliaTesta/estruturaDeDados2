import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArvoreBinariaBuscaTest {
    private ArvoreBinariaBusca arvore;

    @BeforeEach
    public void inicializar(){
        arvore = new ArvoreBinariaBusca();
    }

    @Test
    public void inserir(){
        assertTrue(arvore.estaVazia());
        arvore.adicionar(10);
        assertFalse(arvore.estaVazia());
        assertEquals(10, arvore.buscar(10).valor);
        arvore.adicionar(20);
        assertEquals(20, arvore.buscar(20).valor);
    }

    @Test
    public void buscarEContem(){
        arvore.adicionar(20);
        arvore.adicionar(10);
        arvore.adicionar(6);
        arvore.adicionar(6);
        arvore.adicionar(14);
        assertTrue(arvore.contem(20));
        assertTrue(arvore.contem(10));
        assertTrue(arvore.contem(6));
        assertFalse(arvore.contem(16));
    }

    @Test
    public void removerSoLadoDireito(){
        arvore.adicionar(20);
        arvore.adicionar(60);
        arvore.adicionar(16);
        arvore.adicionar(19);
        arvore.adicionar(14);
        arvore.remover(16);
        assertTrue(arvore.contem(20));
        assertTrue(arvore.contem(60));
        assertTrue(arvore.contem(14));
        assertFalse(arvore.contem(16));

        No no = arvore.buscar(14);
        assertTrue(no.isFolha(no));

        no = arvore.buscar(60);
        assertTrue(no.isFolha(no));
    }

    @Test
    public void removerComDireitoEExtremaEsquerda(){
        arvore.adicionar(20);
        arvore.adicionar(60);
        arvore.adicionar(15);
        arvore.adicionar(19);
        arvore.adicionar(14);
        arvore.adicionar(16);
        arvore.adicionar(17);
        arvore.remover(15);

        assertTrue(arvore.contem(20));
        assertTrue(arvore.contem(60));
        assertTrue(arvore.contem(14));
        assertTrue(arvore.contem(19));
        assertTrue(arvore.contem(17));
        assertTrue(arvore.contem(16));
        assertFalse(arvore.contem(15));

        No no = arvore.buscar(17);
        assertTrue(no.isFolha(no));
    }

   @Test
   public void profundidade(){
        arvore.adicionar(20);
        arvore.adicionar(60);
        arvore.adicionar(15);
        arvore.adicionar(19);
        arvore.adicionar(14);
        assertEquals(1, arvore.profundidade(15));
        assertEquals(2, arvore.profundidade(19));
        assertEquals(1, arvore.profundidade(60));
        assertEquals(0, arvore.profundidade(20));
        assertEquals(-1, arvore.profundidade(100));
   }

   @Test
   public void altura(){
        arvore.adicionar(20);
        arvore.adicionar(60);
        arvore.adicionar(15);
        arvore.adicionar(19);
        arvore.adicionar(14);
        assertEquals(2, arvore.alturaNo(20));
        assertEquals(1, arvore.alturaNo(15));
        assertEquals(0, arvore.alturaNo(60));
        assertEquals(-1, arvore.alturaNo(100));
   }

    
   @Test
   public void impressaoInOrder(){
        No raiz = new No(10);

        raiz.esquerdo = new No(5);
        raiz.direito = new No(15);
        raiz.esquerdo.esquerdo = new No(3);
        raiz.esquerdo.direito = new No(7);
        raiz.direito.esquerdo = new No(12);
        raiz.direito.direito = new No(17);

        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        arvore.raiz = raiz;

        System.out.println("In order: ");
        arvore.impressaoInOrder();
   }
   
   @Test 
   public void impressaoPosOrder(){
        No raiz = new No(10);

        raiz.esquerdo = new No(5);
        raiz.direito = new No(15);
        raiz.esquerdo.esquerdo = new No(3);
        raiz.esquerdo.direito = new No(7);
        raiz.direito.esquerdo = new No(12);
        raiz.direito.direito = new No(17);

        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        arvore.raiz = raiz;

        System.out.println("In order: ");
        arvore.impressaoPosOrder();
   }
}