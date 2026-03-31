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
   public void alturaArvoreVazia(){
        assertEquals(-1, arvore.alturaArvore());
   }

   @Test
   public void alturaUmNo(){
       arvore.adicionar(2);
       assertEquals(0, arvore.alturaArvore());
   }

   @Test
   public void alturaBalanceada(){
       arvore.adicionar(10);
       arvore.adicionar(9);
       arvore.adicionar(12);

       assertEquals(1, arvore.alturaArvore());
   }

   @Test 
   public void alturaDesbalanceada(){
       arvore.adicionar(10);
       arvore.adicionar(9);
       arvore.adicionar(8);

       assertEquals(2, arvore.alturaArvore());
   }

   @Test
   public void alturaNoInexistente(){
        assertEquals(-1, arvore.alturaNo(5));
   }

   @Test
   public void alturaNoRaiz(){
       arvore.adicionar(10);
       arvore.adicionar(5);
       arvore.adicionar(3);
       arvore.adicionar(12);

       assertEquals(2, arvore.alturaNo(10));
   }

   @Test
   public void alturaNoFolha(){
        arvore.adicionar(5);
        arvore.adicionar(3);

        assertEquals(0, arvore.alturaNo(3));
   }

   @Test
   public void alturaNoIntermediario(){
        arvore.adicionar(10);
        arvore.adicionar(5);
        arvore.adicionar(3);
        arvore.adicionar(7);

        assertEquals(1, arvore.alturaNo(5));
   }

   @Test
   public void profundidadeArvoreVazia(){
    assertEquals(-1, arvore.profundidadeArvore());
   }

   @Test 
   public void profundidadeUmNo(){
    arvore.adicionar(3);
    assertEquals(0, arvore.profundidadeArvore());
   }

   @Test
   public void profundidadeDesbalanceado(){
        arvore.adicionar(10);
        arvore.adicionar(5);
        arvore.adicionar(3);
        arvore.adicionar(1);
        assertEquals(3, arvore.profundidadeArvore());
   }

   @Test 
   public void profundidadeNoInexistente(){
    assertEquals(-1, arvore.profundidadeNo(9));
   }

   @Test
   public void profundidadeNoRaiz(){
    arvore.adicionar(3);
    assertEquals(0, arvore.profundidadeNo(3));
   }

   @Test
   public void profundidadeNoFolha(){
    arvore.adicionar(5);
    arvore.adicionar(3);
    arvore.adicionar(1);

    assertEquals(2, arvore.profundidadeNo(1));
   }

   @Test
   public void profundidadeNoIntermediario(){
    arvore.adicionar(10);
    arvore.adicionar(5);
    arvore.adicionar(3);

    assertEquals(1, arvore.profundidadeNo(5));
   }

   @Test
   public void inOrderTest(){
     No raiz = new No(10);

     raiz.esquerdo = new No(5);
     raiz.direito = new No(15);
     raiz.esquerdo.esquerdo = new No(3);
     raiz.esquerdo.direito = new No(7);
     raiz.direito.esquerdo = new No(12);
     raiz.direito.direito = new No(17);

     ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
     arvore.raiz = raiz;

     System.out.println("In Order:");
     arvore.impressaoInOrder();
   }

   @Test
   public void preOrderTest(){
     No raiz = new No(10);

     raiz.esquerdo = new No(5);
     raiz.direito = new No(15);
     raiz.esquerdo.esquerdo = new No(3);
     raiz.esquerdo.direito = new No(7);
     raiz.direito.esquerdo = new No(12);
     raiz.direito.direito = new No(17);

     ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
     arvore.raiz = raiz;

     System.out.println("Pre order: ");
     arvore.impressaoPreOrder();
}

    @Test
    public void contarNosVazia(){
        assertEquals(0,arvore.contarNos());
    }

    @Test 
    public void contarNos(){
        arvore.adicionar(10);
        arvore.adicionar(12);
        arvore.adicionar(9);

        assertEquals(3, arvore.contarNos());
    }

    @Test
    public void maiorValor(){
        arvore.adicionar(20);
        arvore.adicionar(5);
        arvore.adicionar(22);
        arvore.adicionar(21);
        arvore.adicionar(29);

        assertEquals(29, arvore.maiorValor());
    }

    @Test 
    public void maiorValorVazio(){
        assertEquals(-1, arvore.maiorValor());
    }

    @Test
    public void menorValor(){
        arvore.adicionar(20);
        arvore.adicionar(5);
        arvore.adicionar(3);
        arvore.adicionar(22);
        arvore.adicionar(21);
        arvore.adicionar(29);

        assertEquals(3, arvore.menorValor());
    }

    @Test
    public void somaNos(){
        arvore.adicionar(20); 
        arvore.adicionar(5);
        arvore.adicionar(3);
        arvore.adicionar(22);
        arvore.adicionar(21);
        arvore.adicionar(29);

        assertEquals(100, arvore.somaNos());
    }

    @Test 
    public void somaNosVazia(){
        assertEquals(0, arvore.somaNos());
    }

    @Test
    public void somaFolhas(){
        arvore.adicionar(30);
        arvore.adicionar(20); 
        arvore.adicionar(5);
        arvore.adicionar(3);
        arvore.adicionar(22);
        arvore.adicionar(21);
        arvore.adicionar(29);

        assertEquals(3, arvore.contarFolhas());
    }

    @Test
    public void somaFolhasVazia(){
        assertEquals(0, arvore.contarFolhas());
    }

    @Test
    public void arvoreBalanceada(){
        arvore.adicionar(30);
        arvore.adicionar(20); 
        arvore.adicionar(40);

        assertTrue(arvore.estaBalanceada());
    }

     @Test
    public void arvoreDesbalanceada(){
        arvore.adicionar(30);
        arvore.adicionar(20); 
        arvore.adicionar(5);

        assertFalse(arvore.estaBalanceada());
    }

}

//altura arvore vazia
//altura no folha
//altura no intermediario
//altura raiz
//altura desbalanceado
//altura balanceado
//altura um no
//altura no inexistente