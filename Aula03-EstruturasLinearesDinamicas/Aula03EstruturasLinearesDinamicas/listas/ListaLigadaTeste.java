package listas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListaLigadaTeste {
    private ListaLigada lista;

    @BeforeEach
    public void configurar() {
        lista = new ListaLigada();
    }

    @Test
    public void testeListaVazia() {
        assertEquals(0, lista.tamanho());
        assertEquals("[]", lista.toString());
    }

    @Test
    public void testeAdicionarInicio() {
        lista.adicionarInicio(5);
        assertEquals(1, lista.tamanho());
        assertTrue(lista.contem(5));
        assertEquals("[5]", lista.toString());

        lista.adicionarInicio(3);
        assertEquals(2, lista.tamanho());
        assertEquals("[3, 5]", lista.toString());
    }

    @Test
    public void testeAdicionarFim() {
        lista.adicionarFim(10);
        assertEquals(1, lista.tamanho());
        assertTrue(lista.contem(10));
        assertEquals("[10]", lista.toString());

        lista.adicionarFim(20);
        assertEquals(2, lista.tamanho());
        assertEquals("[10, 20]", lista.toString());
    }

    @Test
    public void testeRemoverInicio() {
        lista.adicionarInicio(1);
        lista.adicionarInicio(2);
        assertEquals("[2, 1]", lista.toString());

        lista.removerInicio();
        assertEquals(1, lista.tamanho());
        assertEquals("[1]", lista.toString());

        lista.removerInicio();
        assertEquals(0, lista.tamanho());
        assertEquals("[]", lista.toString());

        lista.removerInicio();
        assertEquals(0, lista.tamanho());
    }

    @Test
    public void testeContem() {
        lista.adicionarInicio(3);
        lista.adicionarFim(7);
        assertTrue(lista.contem(3));
        assertTrue(lista.contem(7));
        assertFalse(lista.contem(5));
    }

    @Test
    public void testeTamanho() {
        assertEquals(0, lista.tamanho());
        lista.adicionarInicio(1);
        assertEquals(1, lista.tamanho());
       
        lista.adicionarFim(2);
        assertEquals(2, lista.tamanho());
       
        lista.adicionarFim(3);
        assertEquals(3, lista.tamanho());
       
        lista.adicionarNaPosicao(4, 1);
        assertEquals(4, lista.tamanho());
        assertEquals("[1, 4, 2, 3]", lista.toString());
       
        lista.removerInicio();
        assertEquals(3, lista.tamanho());
        assertEquals("[4, 2, 3]", lista.toString());
       
        lista.removerNaPosicao(1);
        assertEquals(2, lista.tamanho());
        assertEquals("[4, 3]", lista.toString());
       
        lista.removerFim(); ;
        assertEquals(1, lista.tamanho());
        assertEquals("[4]", lista.toString());
    }

    @Test
    public void testeAdicionarInicioEFim() {
        lista.adicionarInicio(1);
        lista.adicionarFim(2);
        lista.adicionarInicio(0);
        assertEquals(3, lista.tamanho());
        assertEquals("[0, 1, 2]", lista.toString());
    }

    @Test
    public void testeInserirNaPosicao() {
        lista.adicionarInicio(1);
        lista.adicionarFim(3);
        assertEquals("[1, 3]", lista.toString());

        lista.adicionarNaPosicao(2, 1);
        assertEquals(3, lista.tamanho());
        assertEquals("[1, 2, 3]", lista.toString());

        lista.adicionarNaPosicao(0, 0);
        assertEquals("[0, 1, 2, 3]", lista.toString());

        lista.adicionarNaPosicao(4, 4);
        assertEquals("[0, 1, 2, 3, 4]", lista.toString());

        assertThrows(IndexOutOfBoundsException.class, () -> lista.adicionarNaPosicao(5, 6));
    }

    @Test
    public void testeRemoverNaPosicao() {
        lista.adicionarFim(0);
        lista.adicionarFim(1);
        lista.adicionarFim(2);
        lista.adicionarFim(3);
        assertEquals("[0, 1, 2, 3]", lista.toString());

        lista.removerNaPosicao(1);
        assertEquals(3, lista.tamanho());
        assertEquals("[0, 2, 3]", lista.toString());

        lista.removerNaPosicao(0);
        assertEquals("[2, 3]", lista.toString());

        lista.removerNaPosicao(1);
        assertEquals("[2]", lista.toString());

        assertThrows(IndexOutOfBoundsException.class, () -> lista.removerNaPosicao(1));
    }
    @Test
    public void testeRemoverFim() {
        lista.adicionarFim(1);
        lista.adicionarFim(2);
        assertEquals("[1, 2]", lista.toString());

        lista.removerFim();
        assertEquals(1, lista.tamanho());
        assertEquals("[1]", lista.toString());

        lista.removerFim();
        assertEquals(0, lista.tamanho());
        assertEquals("[]", lista.toString());

        lista.removerFim();
        assertEquals(0, lista.tamanho());
    }
	

    @Test
    public void testPegarNaPosicaoInicioUnicoElemento() {
        lista.adicionarInicio(5);
        assertEquals(5, lista.pegarNaPosicao(0));
    }

    @Test
    public void testPegarNaPosicaoInicioMultiplosElementos() {
        lista.adicionarFim(1);
        lista.adicionarFim(2);
        lista.adicionarFim(3);
        assertEquals(1, lista.pegarNaPosicao(0));
	assertEquals(2, lista.pegarNaPosicao(1));
	assertEquals(3, lista.pegarNaPosicao(2));
    }

    @Test
    public void testPegarNaPosicaoNegativa() {
        lista.adicionarFim(1);
	assertThrows(IndexOutOfBoundsException.class, () -> lista.pegarNaPosicao(-1));
    }

    @Test
    public void testPegarNaPosicaoForaDoTamanho() {
        lista.adicionarFim(1);
        lista.adicionarFim(2);
        assertThrows(IndexOutOfBoundsException.class, () -> lista.pegarNaPosicao(3));
    }


}