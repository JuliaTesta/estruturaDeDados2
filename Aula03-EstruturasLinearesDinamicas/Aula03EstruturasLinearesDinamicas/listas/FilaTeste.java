package listas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilaTeste {
    private Fila fila;

    @BeforeEach
    public void configurar() {
        fila = new Fila();
    }

    @Test
    public void testeFilaVazia() {
        assertTrue(fila.estaVazia());
        assertEquals(0, fila.tamanho());
        assertEquals("[]", fila.toString());
    }

    @Test
    public void testeAdicionar() {
        fila.adicionar(1);
        assertEquals(1, fila.tamanho());
        assertTrue(fila.contem(1));
        assertEquals("[1]", fila.toString());

        fila.adicionar(2);
        assertEquals(2, fila.tamanho());
        assertEquals("[1, 2]", fila.toString());
    }

    @Test
    public void testeRemover() {
        fila.adicionar(1);
        fila.adicionar(2);
        assertEquals("[1, 2]", fila.toString());

        fila.remover();
        assertFalse(fila.contem(1));
        assertEquals(1, fila.tamanho());
        assertEquals("[2]", fila.toString());

        fila.remover();
        assertFalse(fila.contem(2));
        assertTrue(fila.estaVazia());
        assertEquals("[]", fila.toString());
    }

    @Test
    public void testeRemoverFilaVazia() {
        assertThrows(IllegalStateException.class, () -> fila.remover());
    }

    @Test
    public void testeContem() {
        fila.adicionar(3);
        fila.adicionar(7);
        assertTrue(fila.contem(3));
        assertTrue(fila.contem(7));
        assertFalse(fila.contem(5));
    }

    @Test
    public void testeTamanho() {
        assertEquals(0, fila.tamanho());
        fila.adicionar(1);
        assertEquals(1, fila.tamanho());
        fila.adicionar(2);
        assertEquals(2, fila.tamanho());
        fila.remover();
        assertEquals(1, fila.tamanho());
    }

    @Test
    public void testeOrdemFIFO() {
        fila.adicionar(10);
        fila.adicionar(20);
        fila.adicionar(30);
        assertEquals("[10, 20, 30]", fila.toString());

        fila.remover();
        assertEquals("[20, 30]", fila.toString());
        assertEquals(2, fila.tamanho());

        fila.remover();
        assertEquals("[30]", fila.toString());
        assertEquals(1, fila.tamanho());
        
        fila.remover();
        assertEquals("[]", fila.toString());
        assertEquals(0, fila.tamanho());
    }
}
