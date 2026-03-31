public class ArvoreBinariaBusca {

    No raiz;

    public ArvoreBinariaBusca(){
        raiz = null;
    }

    public boolean estaVazia(){
        return raiz == null;
    }
    
    public void adicionar(int novoValor){

        if (estaVazia()){
            raiz = new No(novoValor);
            return;
        }

        No atual = raiz;
        No pai = null;

        while (atual != null){
            pai = atual;
            if (novoValor < atual.valor){
                atual = atual.esquerdo;
            }
            else if (novoValor > atual.valor){
                atual = atual.direito;
            }
            else {
                return; // ja existe, nao faz nada
            }
        }

        No novoNo = new No(novoValor);
        if (novoValor < pai.valor){
            pai.esquerdo = novoNo;
        }
        else {
            pai.direito = novoNo;
        }
    }

    public No buscar (int valorBuscado){

        No atual = raiz;

        while (atual != null){
        
            if (valorBuscado == atual.valor){
                return atual;
            }
            else if (valorBuscado < atual.valor){
                atual = atual.esquerdo;
            }
            else {
                atual = atual.direito;
            }

        }  
        return null;
    }

    public boolean contem (int valorBuscado){
        return buscar(valorBuscado) != null;
    }

    public void remover(int valorRemover){

        if (estaVazia()){
            return;
        }

        No atual = raiz;
        No pai = null;

        while (atual != null && valorRemover != atual.valor){
            pai = atual;
            if (valorRemover < atual.valor){
                atual = atual.esquerdo;
            }
            else {
                atual = atual.direito;
            }
        }

        if (atual == null){ // nao achou
            return;
        }

        //folha e 1 filho: caso 1 e caso 2
        if (atual.esquerdo == null || atual.direito == null){
            
            No filho;
            if (atual.esquerdo != null){
                filho = atual.esquerdo;
            }
            else{
                filho = atual.direito;
            }
            if (pai == null){
                raiz = filho;
            }
            else {
                if (atual == pai.esquerdo){
                    pai.esquerdo = filho;
                }
                else{
                    pai.direito = filho; //esta recebendo o filho, que pode ser null
                }
            }
        }
        else { //caso 3

            No paiSucessor = atual;
            No sucessor = atual.direito;

            while (sucessor.esquerdo != null) {
                paiSucessor = sucessor;
                sucessor = sucessor.esquerdo; 
            }

            atual.valor = sucessor.valor;

            if (paiSucessor == atual){
                paiSucessor.direito = sucessor.direito; // justamente pq nao tem esquerda
            }
            else {
                paiSucessor.esquerdo = sucessor.direito; //justamente pq ja foi pra maxima esquerda
            }

        }
    }

    //Altura Arvore
        public int alturaArvore(){
            return alturaRecursiva(raiz);
        }

        private int alturaRecursiva(No noAtual){
            if(noAtual == null){
                return -1;
            }

            int alturaEsquerda = alturaRecursiva(noAtual.esquerdo);
            int alturaDireita = alturaRecursiva(noAtual.direito);
            return 1 + Math.max(alturaEsquerda, alturaDireita);
        }
        
        //Altura Nó
        public int alturaNo(int valorBuscado){
            No no = buscar(valorBuscado);
            if(no == null){
                return -1;
            }
            return alturaRecursiva(no);
        }

        //Profundidade Arvore
        public int profundidadeArvore(){
            return alturaArvore();
        }

        //Profundidade Nó
       public int profundidadeNo(int valorBuscado){
            return profundidadeRecursivo(raiz, valorBuscado, 0);
       }

       private int profundidadeRecursivo(No atual, int valorBuscado, int profundidade){
        if(atual == null){
            return -1;
        }
        if(atual.valor == valorBuscado){
            return profundidade;
        }
        if(valorBuscado > atual.valor){
            return profundidadeRecursivo(atual.direito, valorBuscado, profundidade+1);
        }
        return profundidadeRecursivo(atual.esquerdo, valorBuscado, profundidade + 1);
       }


        //OBS: Usamos buscar na altura porque precisamos primeiro encontrar o nó para depois explorar toda a subárvore dele; já na profundidade, a gente encontra o nó enquanto desce pela árvore em um único caminho.

    
    //Impressão In-Order(EVD)
    public void impressaoInOrder(){
        impressaoInOrderRecursivo(raiz);
    }

    private void impressaoInOrderRecursivo(No noAtual){
        if(noAtual != null){
            impressaoInOrderRecursivo(noAtual.esquerdo); //E
            System.out.println(noAtual.valor + " "); //V
            impressaoInOrderRecursivo(noAtual.direito); //D
        }
    }

    //Impressão Pre-Order(VED)
    public void impressaoPreOrder(){
        impressaoPreOrderRecursivo(raiz);
    }

    private void impressaoPreOrderRecursivo(No noAtual){
        if(noAtual != null){
            System.out.println(noAtual.valor + " "); //V
            impressaoPreOrderRecursivo(noAtual.esquerdo);//E
            impressaoPreOrderRecursivo(noAtual.direito);//D
        }
    }

    //Impressao Pos-Ordem(EDV)
    public void impressaoPosOrdem(){
        impressaoPosOrderRecursivo(raiz);
    }

    private void impressaoPosOrderRecursivo(No noAtual){
        if(noAtual != null){
            impressaoPosOrderRecursivo(noAtual.esquerdo); //E
            impressaoPosOrderRecursivo(noAtual.direito);//D
            System.out.println(noAtual.valor + " ");
        }
    }  

    //encontre o menor e maior valor
        public int menorValor(){
            No atual = raiz;

            if(raiz == null) {
                return -1;
            }

            while(atual.esquerdo != null){
                atual = atual.esquerdo;
            }
            return atual.valor;
        }

        public int maiorValor(){
            No atual = raiz;

            if(raiz == null) {
                return -1;
            }

            while(atual.direito != null){
                atual = atual.direito;
            }
            return atual.valor;
        }

        //Somar todos os valores da arvore
        public int somaNos(){
            return somaNosRecursivo(raiz);
        }

        private int somaNosRecursivo(No noAtual){
            if(noAtual == null){
                return 0;
            }

            return noAtual.valor + somaNosRecursivo(noAtual.direito) + somaNosRecursivo(noAtual.esquerdo);
        }

        //contarFolhas
        public int contarFolhas(){
            return contarFolhasRecursivo(raiz);
        }

        private int contarFolhasRecursivo(No noAtual){
            if(noAtual == null){
                return 0;
            }

            if(noAtual.esquerdo == null && noAtual.direito == null){
                return 1;
            }

            return contarFolhasRecursivo(noAtual.direito) + contarFolhasRecursivo(noAtual.esquerdo);
        }

        //verificar balanceamento
        public boolean estaBalanceada(){
            return estaBalanceadaRecursivo(raiz);
        }

        private boolean estaBalanceadaRecursivo(No noAtual){
            if(noAtual == null){
                return true;
            }

            int alturaEsq = alturaRecursiva(noAtual.esquerdo);
            int alturaDir = alturaRecursiva(noAtual.direito);

            if(Math.abs(alturaEsq - alturaDir) > 1){
                return false;
            }

            //verifica para os filhos
            return estaBalanceadaRecursivo(noAtual.esquerdo) && estaBalanceadaRecursivo(noAtual.direito);
        }
}

        

