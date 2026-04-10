import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArvoreRNTest {

	private ArvoreRN arvore;

	@BeforeEach
	void setUp() {
		arvore = new ArvoreRN();
	}

	@Test
	void testInsercaoBasica() {
		arvore.inserir(10);
		assertEquals(10, arvore.getRaiz().dado);
		assertEquals(Cor.PRETO, arvore.getRaiz().cor);
		assertEquals(arvore.getNulo(), arvore.getRaiz().esquerdo);
		assertEquals(arvore.getNulo(), arvore.getRaiz().direito);
	}

	@Test
	void testInsercaoDuplicada() {
		arvore.inserir(5);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			arvore.inserir(5);
		});
		assertEquals("Erro ao inserir. Valor ja existe na arvore.", exception.getMessage());
	}

	@Test
	void testPropriedadeRaizPreta() {
		arvore.inserir(10);
		arvore.inserir(20);
		arvore.inserir(5);
		assertEquals(Cor.PRETO, arvore.getRaiz().cor);
	}

	@Test
	void testRotacaoEsquerda() {
		arvore.inserir(10);
		arvore.inserir(20);
		arvore.inserir(30);

		No raiz = arvore.getRaiz();
		assertEquals(20, raiz.dado);
		assertEquals(10, raiz.esquerdo.dado);
		assertEquals(30, raiz.direito.dado);
	}

	@Test
	void testRotacaoDireita() {
		arvore.inserir(30);
		arvore.inserir(20);
		arvore.inserir(10);

		No raiz = arvore.getRaiz();
		assertEquals(20, raiz.dado);
		assertEquals(10, raiz.esquerdo.dado);
		assertEquals(30, raiz.direito.dado);
	}

	@Test
	void testCaso1Recoloracao() {
		arvore.inserir(10);
		arvore.inserir(20);
		arvore.inserir(5);
		arvore.inserir(7);

		No raiz = arvore.getRaiz();
		assertEquals(10, raiz.dado);
		assertEquals(Cor.PRETO, raiz.cor);
		assertEquals(Cor.PRETO, raiz.esquerdo.cor);
		assertEquals(Cor.PRETO, raiz.direito.cor);
		assertEquals(Cor.VERMELHO, raiz.esquerdo.direito.cor);
	}

	@Test
	void testCaso2RotacaoEsquerda() {
		arvore.inserir(10);
		arvore.inserir(5);
		arvore.inserir(7);

		No raiz = arvore.getRaiz();
		assertEquals(7, raiz.dado);
		assertEquals(5, raiz.esquerdo.dado);
		assertEquals(10, raiz.direito.dado);
		assertEquals(Cor.PRETO, raiz.cor);
		assertEquals(Cor.VERMELHO, raiz.esquerdo.cor);
		assertEquals(Cor.VERMELHO, raiz.direito.cor);
	}

	@Test
	void testCaso3RotacaoDireita() {
		arvore.inserir(10);
		arvore.inserir(5);
		arvore.inserir(3);

		No raiz = arvore.getRaiz();
		assertEquals(5, raiz.dado);
		assertEquals(3, raiz.esquerdo.dado);
		assertEquals(10, raiz.direito.dado);
		assertEquals(Cor.PRETO, raiz.cor);
		assertEquals(Cor.VERMELHO, raiz.esquerdo.cor);
		assertEquals(Cor.VERMELHO, raiz.direito.cor);
	}

	@Test
	void testCaso4RotacaoDireita() {
		arvore.inserir(10);
		arvore.inserir(20);
		arvore.inserir(15);

		No raiz = arvore.getRaiz();
		assertEquals(15, raiz.dado);
		assertEquals(10, raiz.esquerdo.dado);
		assertEquals(20, raiz.direito.dado);
		assertEquals(Cor.PRETO, raiz.cor);
		assertEquals(Cor.VERMELHO, raiz.esquerdo.cor);
		assertEquals(Cor.VERMELHO, raiz.direito.cor);
	}

	@Test
	void testCaso5RotacaoEsquerda() {
		arvore.inserir(10);
		arvore.inserir(20);
		arvore.inserir(30);

		No raiz = arvore.getRaiz();
		assertEquals(20, raiz.dado);
		assertEquals(10, raiz.esquerdo.dado);
		assertEquals(30, raiz.direito.dado);
		assertEquals(Cor.PRETO, raiz.cor);
		assertEquals(Cor.VERMELHO, raiz.esquerdo.cor);
		assertEquals(Cor.VERMELHO, raiz.direito.cor);
	}

	@Test
	void testBuscaValorExistente() {
		arvore.inserir(34);
		arvore.inserir(3);
		arvore.inserir(50);

		No encontrado = arvore.buscarNoPorValor(3);
		assertEquals(3, encontrado.dado);
		assertNotEquals(arvore.getNulo(), encontrado);
	}

	@Test
	void testBuscaValorInexistente() {
		arvore.inserir(34);
		arvore.inserir(3);
		arvore.inserir(50);

		No encontrado = arvore.buscarNoPorValor(100);
		assertEquals(arvore.getNulo(), encontrado);
	}

	@Test
	void testArvoreComplexa() {
		arvore.inserir(34);
		arvore.inserir(3);
		arvore.inserir(50);
		arvore.inserir(20);
		arvore.inserir(15);
		arvore.inserir(16);
		arvore.inserir(25);
		arvore.inserir(27);

		assertEquals(15, arvore.buscarNoPorValor(15).dado);

		No raiz = arvore.getRaiz();
		assertEquals(Cor.PRETO, raiz.cor);

		No no20 = arvore.buscarNoPorValor(20);
		assertEquals(20, no20.dado);
		assertEquals(15, no20.esquerdo.dado);
		assertEquals(34, no20.direito.dado);
	}

	@Test
	void testPropriedadeSemVermelhosConsecutivos() {
		arvore.inserir(10);
		arvore.inserir(20);
		arvore.inserir(5);
		arvore.inserir(30);
		arvore.inserir(15);

		No raiz = arvore.getRaiz();
		verificarSemVermelhosConsecutivos(raiz);
	}

	@Test
	void testPropriedadeCaminhoPreto() {
		arvore.inserir(10);
		arvore.inserir(20);
		arvore.inserir(5);
		arvore.inserir(30);
		arvore.inserir(15);
		arvore.inserir(3);
		arvore.inserir(25);

		int caminhoPreto = contarNosPretos(arvore.getRaiz().esquerdo);
		verificarCaminhoPreto(arvore.getRaiz(), 0, caminhoPreto);
	}

	@Test
	void testInsercoesMassivas() {
		for (int i = 1; i <= 100; i++) {
			arvore.inserir(i);
		}

		assertEquals(Cor.PRETO, arvore.getRaiz().cor);
		verificarSemVermelhosConsecutivos(arvore.getRaiz());

		int caminhoPreto = contarNosPretos(arvore.getRaiz().esquerdo);
		verificarCaminhoPreto(arvore.getRaiz(), 0, caminhoPreto);

		for (int i = 1; i <= 100; i++) {
			assertEquals(i, arvore.buscarNoPorValor(i).dado);
		}
	}

	private void verificarSemVermelhosConsecutivos(No no) {
		if (no == arvore.getNulo()) {
			return;
		}

		if (no.cor == Cor.VERMELHO) {
			assertEquals(Cor.PRETO, no.esquerdo.cor);
			assertEquals(Cor.PRETO, no.direito.cor);
		}

		verificarSemVermelhosConsecutivos(no.esquerdo);
		verificarSemVermelhosConsecutivos(no.direito);
	}

	private int contarNosPretos(No no) {
		if (no == arvore.getNulo()) {
			return 1; // O nó nulo é considerado preto
		}

		int contagem = (no.cor == Cor.PRETO) ? 1 : 0;
		return contagem + contarNosPretos(no.esquerdo);
	}

	private void verificarCaminhoPreto(No no, int contagemAtual, int esperado) {
		if (no == arvore.getNulo()) {
			assertEquals(esperado, contagemAtual);
			return;
		}

		if (no.cor == Cor.PRETO) {
			contagemAtual++;
		}

		verificarCaminhoPreto(no.esquerdo, contagemAtual, esperado);
		verificarCaminhoPreto(no.direito, contagemAtual, esperado);
	}

	@Test
void testRemocaoNoFolha() {
    arvore.inserir(10);
    arvore.inserir(5);
    arvore.remover(5);

    No raiz = arvore.getRaiz();
    assertEquals(10, raiz.dado);
    assertEquals(Cor.PRETO, raiz.cor);
    assertEquals(arvore.getNulo(), raiz.esquerdo);
    assertEquals(arvore.getNulo(), raiz.direito);
}

@Test
void testRemocaoRaizUnica() {
    arvore.inserir(10);
    arvore.remover(10);

    assertEquals(arvore.getNulo(), arvore.getRaiz());
}

@Test
void testRemocaoNoComUmFilho() {
    arvore.inserir(10);
    arvore.inserir(5);
    arvore.inserir(7);
    arvore.remover(5);

    No raiz = arvore.getRaiz();
    assertEquals(7, raiz.dado);
    assertEquals(10, raiz.direito.dado);
    assertEquals(Cor.PRETO, raiz.cor);
    assertEquals(Cor.VERMELHO, raiz.direito.cor);
}

@Test
void testRemocaoNoComDoisFilhos() {
    arvore.inserir(10);
    arvore.inserir(5);
    arvore.inserir(15);
    arvore.inserir(12);
    arvore.inserir(20);
    arvore.remover(15);

    No raiz = arvore.getRaiz();
    assertEquals(10, raiz.dado);
    assertEquals(5, raiz.esquerdo.dado);
    assertEquals(20, raiz.direito.dado);
    assertEquals(12, raiz.direito.esquerdo.dado);
    verificarSemVermelhosConsecutivos(raiz);
    int caminhoPreto = contarNosPretos(raiz.esquerdo);
    verificarCaminhoPreto(raiz, 0, caminhoPreto);
}

@Test
void testRemocaoCaso1IrmaoVermelho() {
    arvore.inserir(10);
    arvore.inserir(5);
    arvore.inserir(15);
    arvore.inserir(20);
    arvore.remover(5);

    No raiz = arvore.getRaiz();
    assertEquals(15, raiz.dado);
    assertEquals(10, raiz.esquerdo.dado);
    assertEquals(20, raiz.direito.dado);
    assertEquals(Cor.PRETO, raiz.cor);
    assertEquals(Cor.PRETO, raiz.esquerdo.cor);
    assertEquals(Cor.PRETO, raiz.direito.cor);
}

@Test
void testRemocaoCaso2IrmaoPretoFilhosPretos() {
    arvore.inserir(10);
    arvore.inserir(5);
    arvore.inserir(15);
    arvore.inserir(3);
    arvore.remover(15);

    No raiz = arvore.getRaiz();
    assertEquals(5, raiz.dado);
    assertEquals(3, raiz.esquerdo.dado);
    assertEquals(10, raiz.direito.dado);
    verificarSemVermelhosConsecutivos(raiz);
    int caminhoPreto = contarNosPretos(raiz.esquerdo);
    verificarCaminhoPreto(raiz, 0, caminhoPreto);
}

@Test
void testRemocaoCaso3IrmaoPretoFilhoInternoVermelho() {
    arvore.inserir(20);
    arvore.inserir(10);
    arvore.inserir(30);
    arvore.inserir(25);
    arvore.remover(10);

    No raiz = arvore.getRaiz();
    assertEquals(25, raiz.dado);
    assertEquals(20, raiz.esquerdo.dado);
    assertEquals(30, raiz.direito.dado);
    verificarSemVermelhosConsecutivos(raiz);
    int caminhoPreto = contarNosPretos(raiz.esquerdo);
    verificarCaminhoPreto(raiz, 0, caminhoPreto);
}

@Test
void testRemocaoCaso4IrmaoPretoFilhoExternoVermelho() {
    arvore.inserir(20);
    arvore.inserir(10);
    arvore.inserir(30);
    arvore.inserir(35);
    arvore.remover(10);

    No raiz = arvore.getRaiz();
    assertEquals(30, raiz.dado);
    assertEquals(20, raiz.esquerdo.dado);
    assertEquals(35, raiz.direito.dado);
    verificarSemVermelhosConsecutivos(raiz);
    int caminhoPreto = contarNosPretos(raiz.esquerdo);
    verificarCaminhoPreto(raiz, 0, caminhoPreto);
}

@Test
void testRemocaoMassiva() {
    for (int i = 1; i <= 20; i++) {
        arvore.inserir(i);
    }
    for (int i = 10; i <= 15; i++) {
        arvore.remover(i);
    }

    No raiz = arvore.getRaiz();
    assertEquals(Cor.PRETO, raiz.cor);
    verificarSemVermelhosConsecutivos(raiz);
    int caminhoPreto = contarNosPretos(raiz.esquerdo);
    verificarCaminhoPreto(raiz, 0, caminhoPreto);

    for (int i = 10; i <= 15; i++) {
        assertEquals(arvore.getNulo(), arvore.buscarNoPorValor(i));
    }
}
}