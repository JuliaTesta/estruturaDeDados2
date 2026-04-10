public class ArvoreBinariaBusca {

    NoABB raiz;

    public ArvoreBinariaBusca(){
        raiz = null;
    }

    public boolean estaVazia(){
        return raiz == null;
    }
    
    public void adicionar(int novoValor){

        if (estaVazia()){
            raiz = new NoABB(novoValor);
            return;
        }

        NoABB atual = raiz;
        NoABB pai = null;

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

        NoABB novoNo = new NoABB(novoValor);
        if (novoValor < pai.valor){
            pai.esquerdo = novoNo;
        }
        else {
            pai.direito = novoNo;
        }
    }

    public NoABB buscar (int valorBuscado){

        NoABB atual = raiz;

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

        NoABB atual = raiz;
        NoABB pai = null;

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
            
            NoABB filho;
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

            NoABB paiSucessor = atual;
            NoABB sucessor = atual.direito;

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

        private int alturaRecursiva(NoABB noAtual){
            if(noAtual == null){
                return -1;
            }

            int alturaEsquerda = alturaRecursiva(noAtual.esquerdo);
            int alturaDireita = alturaRecursiva(noAtual.direito);
            return 1 + Math.max(alturaEsquerda, alturaDireita);
        }
        
        //Altura Nó
        public int alturaNo(int valorBuscado){
            NoABB no = buscar(valorBuscado);
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

       private int profundidadeRecursivo(NoABB atual, int valorBuscado, int profundidade){
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

    private void impressaoInOrderRecursivo(NoABB noAtual){
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

    private void impressaoPreOrderRecursivo(NoABB noAtual){
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

    private void impressaoPosOrderRecursivo(NoABB noAtual){
        if(noAtual != null){
            impressaoPosOrderRecursivo(noAtual.esquerdo); //E
            impressaoPosOrderRecursivo(noAtual.direito);//D
            System.out.println(noAtual.valor + " ");
        }
    }  

    //EXERCÍCIOS

        //encontre o menor e maior valor
      public int menorValor(){
        NoABB atual = raiz;

        if(raiz == null){
            return -1;
        }

        while(atual.esquerdo != null){
            atual = atual.esquerdo;
        }

        return atual.valor;
      }

      public int maiorValor(){
        NoABB atual = raiz;

        if(atual == null){
            return -1;
        }

        while(atual.direito != null){
            atual = atual.direito;
        }

        return atual.valor;
      }

        //Somar todos os valores da arvore
        public int somaNos(){
            return somaNosRec(raiz);
        }

        private int somaNosRec(NoABB no){
            if(no == null){
                return 0;
            }

            return no.valor + somaNosRec(no.esquerdo) + somaNosRec(no.direito);
        }

        //contarFolhas
        public int contarFolhas(){
            return contarFolhasRec(raiz);
        }

        private int contarFolhasRec(NoABB no){
            if(no == null){
                return 0;
            }

            if(no.esquerdo == null && no.direito == null){
                return 1;
            }

            return contarFolhasRec(no.direito) + contarFolhasRec(no.esquerdo);
        }

        //verificar balanceamento
        public boolean verificarBalanceamento(){
            return verificarBalanceamentoRec(raiz);
        }

        private boolean verificarBalanceamentoRec(NoABB no){
            if(no == null){
                return true;
            }

            int alturaEsq = alturaRecursiva(no.esquerdo);
            int alturaDir = alturaRecursiva(no.direito);

            if(Math.abs(alturaEsq - alturaDir) > 1){
                return false;
            }

            //verifica para os filhos
            return verificarBalanceamentoRec(no.esquerdo) && verificarBalanceamentoRec(no.direito);
        }


        //É ABB válida
        public boolean ehABB(){
            return ehABBrecursivo(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private boolean ehABBrecursivo(NoABB noAtual, int min, int max){
            if(noAtual == null){
                return true;
            }

            if(noAtual.valor >= max || noAtual.valor <= min){
                return false;
            }

            return ehABBrecursivo(noAtual.esquerdo, min, noAtual.valor) && ehABBrecursivo(noAtual.direito, noAtual.valor, max); 
        }

        //Menor ancestral Comum
       public NoABB menorAncestralComum(int v1, int v2){
            NoABB atual = raiz;

            while(atual != null){
                if(v1 < atual.valor && v2 < atual.valor){
                    //esquerda
                    atual = atual.esquerdo;
                }
                else if(v1 > atual.valor && v2 > atual.valor){
                    //direita
                    atual = atual.direito;
                }
                else { //um esta de cada lado. achamos o ancestral
                    return atual;
                }
            }
            return null; //nao encontrou, um dos valores pode nao estar na arvore
       }



       //LISTA ESTUDO
       //1
       public void imprimirApenasFolhas() { 
        imprimirApenasFolhasRec(raiz);
       }

       private void imprimirApenasFolhasRec(NoABB atual){
        if(atual == null){
            return;
        }

        //nao pode ter filhos
        if(atual.esquerdo == null && atual.direito == null){
            System.out.println(atual.valor);
        }

        imprimirApenasFolhasRec(atual.direito);
        imprimirApenasFolhasRec(atual.esquerdo);
       }

       //2
       public boolean isEstritamenteBinaria() {
        return isEstritamenteBinariaRec(raiz);
       }

       private boolean isEstritamenteBinariaRec(NoABB atual){
        if(atual == null){
            return true;
        }

        //se tiver 1 filho nao pode
        if((atual.direito == null && atual.esquerdo != null) || (atual.direito != null && atual.esquerdo == null)){
            return false;
        }

        return isEstritamenteBinariaRec(atual.direito) && isEstritamenteBinariaRec(atual.esquerdo);
       }

}

        

