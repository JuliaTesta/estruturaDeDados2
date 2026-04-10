public class AVL {

    private static final int ESPACO_IMPRESSAO = 4;
    No raiz;

    public AVL() {
        this.raiz = null;
    }

    private int altura(No no) {
        if (no == null) {
            return -1;
        }
        return no.altura;
    }
    
    public int getAlturaNo(int valorNo) {
        No no = buscar(valorNo);
        return altura(no);
    }

    private void atualizarAltura(No no) {
        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));  
    }

    private int fatorBalanceamento(No no) {
        if (no == null) {
            return 0;
        }
        return altura(no.esquerdo) - altura(no.direito);
    }

    public int getFatorBalanceamento(int valorNo) {
        No no = buscar(valorNo);
        return fatorBalanceamento(no);
    }

    private No rotacaoDireita(No p) {
        contadorRotacoes++;
        No u = p.esquerdo;
        No T2 = u.direito;

        u.direito = p;
        p.esquerdo = T2;

        atualizarAltura(p);
        atualizarAltura(u);

        return u;
    }

    private No rotacaoEsquerda(No p) {
        contadorRotacoes++;
        No u = p.direito;
        No T2 = u.esquerdo;

        u.esquerdo = p;
        p.direito = T2;

        atualizarAltura(p);
        atualizarAltura(u);

        return u;
    }

    private No balancear(No no) {
        int fb = fatorBalanceamento(no);

        if (fb > 1) {
            if (fatorBalanceamento(no.esquerdo) < 0) {
                no.esquerdo = rotacaoEsquerda(no.esquerdo);
            }
            return rotacaoDireita(no);
        }
        if (fb < -1) {
            if (fatorBalanceamento(no.direito) > 0) {
                no.direito = rotacaoDireita(no.direito);
            }
            return rotacaoEsquerda(no);
        }
        atualizarAltura(no);
        return no;
    }

    public void inserir(int valorNovo) {
        raiz = inserirRecursivo(raiz, valorNovo);
    }

    private No inserirRecursivo(No noAtual, int valorNovo) {
        if (noAtual == null) {
            return new No(valorNovo);
        }
        if (valorNovo < noAtual.valor) {
            noAtual.esquerdo = inserirRecursivo(noAtual.esquerdo, valorNovo);
        } else if (valorNovo > noAtual.valor) {
            noAtual.direito = inserirRecursivo(noAtual.direito, valorNovo);
        } else {
            return noAtual; //já existe, ignora
        }
        return balancear(noAtual);
    }

    public void remover(int valorRemover) {
        raiz = removerRecursivo(raiz, valorRemover);
    }

    private No removerRecursivo(No noAtual, int valorRemover) {
        if (noAtual == null) {
            return null;
        }
        if (valorRemover < noAtual.valor) {
            noAtual.esquerdo = removerRecursivo(noAtual.esquerdo, valorRemover);
        } else if (valorRemover > noAtual.valor) {
            noAtual.direito = removerRecursivo(noAtual.direito, valorRemover);
        } else {
            if (noAtual.esquerdo == null) {
                return noAtual.direito;
            } else if (noAtual.direito == null) {
                return noAtual.esquerdo;
            }
            No sucessor = encontrarMenor(noAtual.direito);
            noAtual.valor = sucessor.valor;
            noAtual.direito = removerRecursivo(noAtual.direito, sucessor.valor);
        }
        return balancear(noAtual);
    }

    private No encontrarMenor(No no) {
        No atual = no;
        while (atual.esquerdo != null) {
            atual = atual.esquerdo;
        }
        return atual;
    }

    public boolean existe(int valorNo) {
        return buscar(valorNo) != null;
    }

    public No buscar(int valorBuscado) {
        No atual = raiz;
        while (atual != null) {
            if (valorBuscado == atual.valor) {
                return atual;
            } else if (valorBuscado < atual.valor) {
                atual = atual.esquerdo;
            } else {
                atual = atual.direito;
            }
        }
        return null;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public int contarNos() {
        return contarNosRecursivo(raiz);
    }

    private int contarNosRecursivo(No atual) {
        if (atual == null) {
            return 0;
        }
        return 1 + contarNosRecursivo(atual.esquerdo) + contarNosRecursivo(atual.direito);
    }

    public int calcularAlturaArvore() {
        return altura(raiz);
    }

    public int calcularAlturaNo(int valorNo) {
        No no = buscar(valorNo);
        return no == null ? -1 : altura(no);
    }

    public int calcularProfundidadeArvore() {
        return calcularAlturaArvore();
    }

    public int calcularProfundidadeNo(int valorNo) {
        return calcularProfundidadeRecursivo(raiz, valorNo, 0);
    }

    private int calcularProfundidadeRecursivo(No atual, int valorNo, int profundidade) {
        if (atual == null) {
            return -1;
        }
        if (atual.valor == valorNo) {
            return profundidade;
        }
        if (valorNo < atual.valor) {
            return calcularProfundidadeRecursivo(atual.esquerdo, valorNo, profundidade + 1);
        }
        return calcularProfundidadeRecursivo(atual.direito, valorNo, profundidade + 1);
    }

    public String imprimirPreOrdem() {
        StringBuilder sb = new StringBuilder("[");
        imprimirPreOrdemRecursivo(raiz, sb);
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    private void imprimirPreOrdemRecursivo(No atual, StringBuilder sb) {
        if (atual != null) {
            sb.append(atual.valor).append(", ");
            imprimirPreOrdemRecursivo(atual.esquerdo, sb);
            imprimirPreOrdemRecursivo(atual.direito, sb);
        }
    }

    public String imprimirPosOrdem() {
        StringBuilder sb = new StringBuilder("[");
        imprimirPosOrdemRecursivo(raiz, sb);
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    private void imprimirPosOrdemRecursivo(No atual, StringBuilder sb) {
        if (atual != null) {
            imprimirPosOrdemRecursivo(atual.esquerdo, sb);
            imprimirPosOrdemRecursivo(atual.direito, sb);
            sb.append(atual.valor).append(", ");
        }
    }

    public String imprimirInOrdem() {
        StringBuilder sb = new StringBuilder("[");
        imprimirInOrdemRecursivo(raiz, sb);
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    private void imprimirInOrdemRecursivo(No atual, StringBuilder sb) {
        if (atual != null) {
            imprimirInOrdemRecursivo(atual.esquerdo, sb);
            sb.append(atual.valor).append(", ");
            imprimirInOrdemRecursivo(atual.direito, sb);
        }
    }

    public void imprimirArvoreTexto() {
        if (estaVazia()) {
            System.out.println("Árvore vazia");
        } else {
            imprimirArvoreTextoRecursivo(raiz, 0);
        }
    }

    private void imprimirArvoreTextoRecursivo(No atual, int espaco) {
        if (atual == null) {
            return;
        }
        espaco += ESPACO_IMPRESSAO;
        imprimirArvoreTextoRecursivo(atual.direito, espaco);

        System.out.print("\n");
        for (int i = ESPACO_IMPRESSAO; i < espaco; i++) {
            System.out.print(" ");
        }
        System.out.print(atual.valor + "\n");

        imprimirArvoreTextoRecursivo(atual.esquerdo, espaco);
    }

     private int contadorRotacoes = 0;

    //EXERCICIOS

    //1-Qual a largura? (maior quantidade de nós em um nível) TESTADO
   public int larguraArvore(){
    int altura = calcularAlturaArvore();
    int maxLargura = 0;

    for(int nivel = 0; nivel<=altura; nivel++){
        int larguraAtual = contarNivel(raiz, nivel);
        if(larguraAtual > maxLargura){
            maxLargura = larguraAtual;
        }
    }
    return maxLargura;
   }

   private int contarNivel(No no, int nivelDesejado){
    if(no == null){
        return 0;
    }

    if(nivelDesejado == 0){
        return 1;
    }

    int esquerda = contarNivel(no.esquerdo, nivelDesejado -1);
    int direita = contarNivel(no.direito, nivelDesejado - 1);

    return esquerda + direita;        
   }

    //2- verificar se a arvore é estritamente binaria (0 ou 2 filhos) TESTADO
   public boolean estritamenteBinaria(){
    return estritamenteBinariaRec(raiz);
   }

   private boolean estritamenteBinariaRec(No atual){
        if(atual == null){
            return true;
        }

        //nao pode ter 1 filho
        if((atual.esquerdo == null && atual.direito != null) || (atual.esquerdo != null && atual.direito == null)){
            return false;
        }

        return estritamenteBinariaRec(atual.esquerdo) && estritamenteBinariaRec(atual.direito);
   }
    
    //3- Verificar se é completa(tudo deve vir preenchido da esquerda para direita)
    public boolean completa(){
        int totalNos = contarNos();
        return completaRecursiva(raiz, 0, totalNos);
    }

    private boolean completaRecursiva(No no, int indice, int totalNos){
        if(no == null){
            return true;
        }

        if(indice >= totalNos){
            return false;
        }

        return completaRecursiva(no.esquerdo, 2*indice + 1, totalNos) && completaRecursiva(no.direito, 2*indice+2, totalNos);
    }
    
    //4- verificar se é cheia
    public boolean cheia(){
        return cheiaRecursivo(raiz);
    }

    private boolean cheiaRecursivo(No no){
        if(no == null){
            return true;
        }

        //se for folha, ok
        if(no.esquerdo == null && no.direito == null){
            return true;
        }

        //nós internos precisam ter 2 filhos
        if(no.direito != null && no.esquerdo != null){
            return cheiaRecursivo(no.esquerdo) && cheiaRecursivo(no.direito);
        }

        return false;
    }

    //6- Retorne o menor elemento
    public int menorElemento(){
        return menorElementoRecursivo(raiz);
    }

    private int menorElementoRecursivo(No noAtual){
        if(noAtual == null){
            return 0;
        }

        while(noAtual.esquerdo != null){
            noAtual = noAtual.esquerdo;
        }

        return noAtual.valor;
    }

    //7- In order EVD
    public void impressaoInOrder(){
        //EVD
        impressaoInOrderRec(raiz);
    }

    private void impressaoInOrderRec(No no){
        if(no != null){
            impressaoInOrderRec(no.esquerdo);
            System.out.println(no.valor + " ");
            impressaoInOrderRec(no.direito);
        }
    }
}