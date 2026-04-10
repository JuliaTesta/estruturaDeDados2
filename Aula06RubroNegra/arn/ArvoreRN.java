
public class ArvoreRN {

	private static final String ERRO_AO_INSERIR_VALOR = "Erro ao inserir. Valor ja existe na arvore.";
	private No raiz;
	private No nulo;

	public ArvoreRN() {
		nulo = new No();
		raiz = nulo;
	}

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public No getNulo() {
		return nulo;
	}

	public void setNulo(No nulo) {
		this.nulo = nulo;
	}

	void rotacaoEsquerda(No noReferenciaX) {

		No noReferenciaY = noReferenciaX.direito;

		noReferenciaX.direito = noReferenciaY.esquerdo;
		if (noReferenciaY.esquerdo != nulo) {
			noReferenciaY.esquerdo.pai = noReferenciaX;
		}

		noReferenciaY.pai = noReferenciaX.pai;

		if (noReferenciaX.pai == nulo) {
			raiz = noReferenciaY;
		} else {
			if (noReferenciaX == noReferenciaX.pai.esquerdo) {
				noReferenciaX.pai.esquerdo = noReferenciaY;
			} else {
				noReferenciaX.pai.direito = noReferenciaY;
			}
		}
		noReferenciaY.esquerdo = noReferenciaX;
		noReferenciaX.pai = noReferenciaY;
	}

	void rotacaoDireita(No noReferenciaY) {

		No noReferenciaX = noReferenciaY.esquerdo;

		noReferenciaY.esquerdo = noReferenciaX.direito;
		if (noReferenciaX.direito != nulo) {
			noReferenciaX.direito.pai = noReferenciaY;
		}

		noReferenciaX.pai = noReferenciaY.pai;

		if (noReferenciaY.pai == nulo) {
			raiz = noReferenciaX;
		} else {
			if (noReferenciaY == noReferenciaY.pai.esquerdo) {
				noReferenciaY.pai.esquerdo = noReferenciaX;
			} else {
				noReferenciaY.pai.direito = noReferenciaX;
			}
		}
		noReferenciaX.direito = noReferenciaY;
		noReferenciaY.pai = noReferenciaX;
	}

	void inserir(int novoValor) {

		No noAnterior = nulo;
		No noAtual = raiz;

		while (noAtual != nulo) {

			noAnterior = noAtual;

			if (novoValor < noAtual.dado) {
				noAtual = noAtual.esquerdo;
			} else if (novoValor > noAtual.dado) {
				noAtual = noAtual.direito;
			} else {
				throw new IllegalArgumentException(ERRO_AO_INSERIR_VALOR);
			}
		}

		No novoNo = new No(novoValor);
		novoNo.pai = noAnterior;
		novoNo.direito = nulo;
		novoNo.esquerdo = nulo;
		novoNo.cor = Cor.VERMELHO;

		if (raiz == nulo) {
			raiz = novoNo;
		} else {
			if (novoValor < noAnterior.dado) {
				noAnterior.esquerdo = novoNo;
			} else {
				noAnterior.direito = novoNo;
			}
		}

		garantirPropriedadesRubroNegras(novoNo);
	}

	private void garantirPropriedadesRubroNegras(No noReferenciaX) {

		No noReferenciaU; // tio

		while (noReferenciaX.pai.cor == Cor.VERMELHO) {

			if (noReferenciaX.pai == noReferenciaX.pai.pai.esquerdo) {
				noReferenciaU = noReferenciaX.pai.pai.direito;
				noReferenciaX = aplicarCasosRecolorirRotacionar(noReferenciaX, noReferenciaU); // caso 1, 2 e 3
			} else {
				noReferenciaU = noReferenciaX.pai.pai.esquerdo;
				noReferenciaX = aplicarCasosRecolorirRotacionarEspelho(noReferenciaX, noReferenciaU); // caso 1, 4, 5
			}
		}

		raiz.cor = Cor.PRETO;
	}

	//pai é filho direito do avô
	private No aplicarCasosRecolorirRotacionarEspelho(No noReferenciaX, No noReferenciaU) { 
		if (noReferenciaU.cor == Cor.VERMELHO) {
			noReferenciaU.cor = Cor.PRETO; // caso 1
			noReferenciaX.pai.cor = Cor.PRETO;
			noReferenciaX.pai.pai.cor = Cor.VERMELHO;
			noReferenciaX = noReferenciaX.pai.pai;
		} else { // tio preto , pai direito

			if (noReferenciaX == noReferenciaX.pai.esquerdo) { // caso 4
				noReferenciaX = noReferenciaX.pai;
				rotacaoDireita(noReferenciaX);
			}
			noReferenciaX.pai.cor = Cor.PRETO; // caso 5
			noReferenciaX.pai.pai.cor = Cor.VERMELHO;
			rotacaoEsquerda(noReferenciaX.pai.pai);
		}

		return noReferenciaX;
	}

	//pai é filho esquerdo do avô
	private No aplicarCasosRecolorirRotacionar(No noReferenciaX, No noReferenciaU) { 

		if (noReferenciaU.cor == Cor.VERMELHO) {
			noReferenciaU.cor = Cor.PRETO; // caso 1
			noReferenciaX.pai.cor = Cor.PRETO;
			noReferenciaX.pai.pai.cor = Cor.VERMELHO;
			noReferenciaX = noReferenciaX.pai.pai;
		} else { // tio preto , pai esquerdo

			if (noReferenciaX == noReferenciaX.pai.direito) { // caso 2
				noReferenciaX = noReferenciaX.pai;
				rotacaoEsquerda(noReferenciaX);
			}
			noReferenciaX.pai.cor = Cor.PRETO; // caso 3
			noReferenciaX.pai.pai.cor = Cor.VERMELHO;
			rotacaoDireita(noReferenciaX.pai.pai);

		}

		return noReferenciaX;
	}

	public void imprimirInOrder() {
		percorrerInOrder(raiz, "   ");
	}

	private void percorrerInOrder(No noReferencia, String espaco) {

		if (noReferencia != nulo) {
			percorrerInOrder(noReferencia.esquerdo, "     " + espaco);
			System.out.println(espaco + noReferencia.dado + "," + noReferencia.cor);
			percorrerInOrder(noReferencia.direito, "     " + espaco);

		}

	}

	public No buscarNoPorValor(int valorBuscado) {

		No noAtual = raiz;

		while (noAtual != null) {
			if (valorBuscado == noAtual.dado) {
				return noAtual;
			} else if (valorBuscado < noAtual.dado) {
				noAtual = noAtual.esquerdo;
			} else {
				noAtual = noAtual.direito;
			}
		}
		return nulo;
	}
	
	public void remover(int valor) {
		No noParaRemover = buscarNoPorValor(valor);
		if (noParaRemover == nulo) {
			return; // Valor não encontrado, ignora.
		}
	
		No noSubstituto;
		No noAtual = noParaRemover;
		Cor corOriginal = noAtual.cor;
	
		if (noParaRemover.esquerdo == nulo) {
			noSubstituto = noParaRemover.direito;
			transplantar(noParaRemover, noParaRemover.direito);
		} else if (noParaRemover.direito == nulo) {
			noSubstituto = noParaRemover.esquerdo;
			transplantar(noParaRemover, noParaRemover.esquerdo);
		} else {
			noAtual = minimo(noParaRemover.direito); // Sucessor
			corOriginal = noAtual.cor;
			noSubstituto = noAtual.direito;
			if (noAtual.pai == noParaRemover) {
				noSubstituto.pai = noAtual;
			} else {
				transplantar(noAtual, noAtual.direito);
				noAtual.direito = noParaRemover.direito;
				noAtual.direito.pai = noAtual;
			}
			transplantar(noParaRemover, noAtual);
			noAtual.esquerdo = noParaRemover.esquerdo;
			noAtual.esquerdo.pai = noAtual;
			noAtual.cor = noParaRemover.cor;
		}
	
		if (corOriginal == Cor.PRETO) {
			corrigirRemocao(noSubstituto);
		}
	}
	
	private void transplantar(No noAntigo, No noNovo) {
		if (noAntigo.pai == nulo) {
			raiz = noNovo;
		} else if (noAntigo == noAntigo.pai.esquerdo) {
			noAntigo.pai.esquerdo = noNovo;
		} else {
			noAntigo.pai.direito = noNovo;
		}
		noNovo.pai = noAntigo.pai;
	}
	
	private No minimo(No no) {
		No atual = no;
		while (atual.esquerdo != nulo) {
			atual = atual.esquerdo;
		}
		return atual;
	}

	private No maximo(No no) {
    No atual = no;
    while(atual.direito != nulo){
        atual = atual.direito;
    }
    return atual;
}
	
	private void corrigirRemocao(No noReferenciaX) {
		while (noReferenciaX != raiz && noReferenciaX.cor == Cor.PRETO) {
			if (noReferenciaX == noReferenciaX.pai.esquerdo) {
				No noIrmao = noReferenciaX.pai.direito;
	
				// Caso 1: Irmão vermelho
				if (noIrmao.cor == Cor.VERMELHO) {
					noIrmao.cor = Cor.PRETO;
					noReferenciaX.pai.cor = Cor.VERMELHO;
					rotacaoEsquerda(noReferenciaX.pai);
					noIrmao = noReferenciaX.pai.direito;
				}
	
				// Caso 2: Irmão preto com ambos filhos pretos
				if (noIrmao.esquerdo.cor == Cor.PRETO && noIrmao.direito.cor == Cor.PRETO) {
					noIrmao.cor = Cor.VERMELHO;
					noReferenciaX = noReferenciaX.pai;
				} else {
					// Caso 3: Irmão preto, filho direito preto, filho esquerdo vermelho
					if (noIrmao.direito.cor == Cor.PRETO) {
						noIrmao.esquerdo.cor = Cor.PRETO;
						noIrmao.cor = Cor.VERMELHO;
						rotacaoDireita(noIrmao);
						noIrmao = noReferenciaX.pai.direito;
					}
	
					// Caso 4: Irmão preto, filho direito vermelho
					noIrmao.cor = noReferenciaX.pai.cor;
					noReferenciaX.pai.cor = Cor.PRETO;
					noIrmao.direito.cor = Cor.PRETO;
					rotacaoEsquerda(noReferenciaX.pai);
					noReferenciaX = raiz;
				}
			} else { // Espelho dos casos acima
				No noIrmao = noReferenciaX.pai.esquerdo;
	
				if (noIrmao.cor == Cor.VERMELHO) {
					noIrmao.cor = Cor.PRETO;
					noReferenciaX.pai.cor = Cor.VERMELHO;
					rotacaoDireita(noReferenciaX.pai);
					noIrmao = noReferenciaX.pai.esquerdo;
				}
	
				if (noIrmao.esquerdo.cor == Cor.PRETO && noIrmao.direito.cor == Cor.PRETO) {
					noIrmao.cor = Cor.VERMELHO;
					noReferenciaX = noReferenciaX.pai;
				} else {
					if (noIrmao.esquerdo.cor == Cor.PRETO) {
						noIrmao.direito.cor = Cor.PRETO;
						noIrmao.cor = Cor.VERMELHO;
						rotacaoEsquerda(noIrmao);
						noIrmao = noReferenciaX.pai.esquerdo;
					}
	
					noIrmao.cor = noReferenciaX.pai.cor;
					noReferenciaX.pai.cor = Cor.PRETO;
					noIrmao.esquerdo.cor = Cor.PRETO;
					rotacaoDireita(noReferenciaX.pai);
					noReferenciaX = raiz;
				}
			}
		}
		noReferenciaX.cor = Cor.PRETO; // Garantir que a raiz ou o nó ajustado seja preto
	}


	//EXERCICIOS
	//1- Contar nós vermelhos e pretos
	public int contarVermelhos(){
		return contarNosPorCorRecursivo(raiz, Cor.VERMELHO);
	}

	public int contarPretos(){
		return contarNosPorCorRecursivo(raiz, Cor.PRETO);
	}

	private int contarNosPorCorRecursivo(No no, Cor cor){
		if(no == nulo){
			return 0;
		}

		int count = 0;

		if(no.cor == cor){
			count = 1;
		}

		return contarNosPorCorRecursivo(no.direito, cor) + contarNosPorCorRecursivo(no.esquerdo, cor);
	}


	//2- buscar sucessor e predecessor de um nó
	public No sucessor(No no){ //PROXIMO MAIOR
		if(no.direito != nulo){
			return minimo(no.direito); //menor da direita
		}

		No pai = no.pai;

		while(pai != null && no == pai.direito){//esta a direita do pai
			no = pai;
			pai = pai.pai;
		}
		return pai;
	}
	
	public No predecessor(No no){ //PROXIMO MENOR
		if(no.esquerdo != nulo){
			return maximo(no.esquerdo); //maior da esquerda
		}

		No pai = no.pai;

		while(pai != null && no == pai.esquerdo){ //esta a esquerda do pai
			no = pai;
			pai = pai.pai;
		}
		return pai;
	}
	
	//altura negra
	public int alturaNegra(){
		return alturaNegraRec(raiz);
	}

	private int alturaNegraRec(No no){
		if(no == nulo){
			return 1; //nulo conta como preto
		}

		int alturaEsq = alturaNegraRec(no.esquerdo);
		int alturaDir = alturaNegraRec(no.direito);

		if(alturaEsq != alturaDir){
			return -1;
		}

		//se alturas forem iguais
		int alturaAtual = alturaEsq;
		if(no.cor == Cor.PRETO){
			alturaAtual += 1;
		}

		return alturaAtual;
	}


	//LISTA EXERCICIOS
	//1
	public boolean validarRegraVermelho(No atual) {
		if(atual == nulo){
			return true;
		}

		//se for vermelho, nao pode ter filho vermelho
		if(atual.cor == Cor.VERMELHO){
			if((atual.esquerdo != nulo && atual.esquerdo.cor == Cor.VERMELHO) || (atual.direito != nulo && atual.esquerdo.cor == Cor.VERMELHO)){
				return false;
			}
		}

		return validarRegraVermelho(atual.direito) && validarRegraVermelho(atual.esquerdo);
	}
}
