package listas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PilhaTeste {
    private Pilha pilha;

    @BeforeEach
    public void configurar() {
        pilha = new Pilha();
    }

    @Test
    public void testePilhaVazia() {
        assertTrue(pilha.estaVazia());
        assertEquals(0, pilha.tamanho());
        assertEquals("[]", pilha.toString());
    }

    @Test
    public void testeAdicionar() {
        pilha.adicionar(1);
        assertEquals(1, pilha.tamanho());
        assertTrue(pilha.contem(1));
        assertEquals("[1]", pilha.toString());

        pilha.adicionar(2);
        assertEquals(2, pilha.tamanho());
        assertEquals("[1, 2]", pilha.toString());
    }

    @Test
    public void testeRemover() {
        pilha.adicionar(1);
        pilha.adicionar(2);
        assertEquals("[1, 2]", pilha.toString());

        pilha.remover();
        assertFalse(pilha.contem(2));
        assertEquals(1, pilha.tamanho());
        assertEquals("[1]", pilha.toString());

        pilha.remover();
        assertFalse(pilha.contem(2));
        assertTrue(pilha.estaVazia());
        assertEquals("[]", pilha.toString());
    }

    @Test
    public void testeRemoverPilhaVazia() {
        assertThrows(IllegalStateException.class, () -> pilha.remover());
    }

    @Test
    public void testeContem() {
        pilha.adicionar(3);
        pilha.adicionar(7);
        assertTrue(pilha.contem(3));
        assertTrue(pilha.contem(7));
        assertFalse(pilha.contem(5));
    }

    @Test
    public void testeTamanho() {
        assertEquals(0, pilha.tamanho());
        pilha.adicionar(1);
        assertEquals(1, pilha.tamanho());
        pilha.adicionar(2);
        assertEquals(2, pilha.tamanho());
        pilha.remover();
        assertEquals(1, pilha.tamanho());
    }

    @Test
    public void testeOrdemLIFO() {
        pilha.adicionar(10);
        pilha.adicionar(20);
        pilha.adicionar(30);
        assertEquals("[10, 20, 30]", pilha.toString());

        pilha.remover();
        assertEquals("[10, 20]", pilha.toString());
        assertEquals(2, pilha.tamanho());

        pilha.remover();
        assertEquals("[10]", pilha.toString());
        assertEquals(1, pilha.tamanho());
        
        pilha.remover();
        assertEquals("[]", pilha.toString());
        assertEquals(0, pilha.tamanho());
    }
}
