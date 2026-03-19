public class ArvoreBinariaBusca{

    No raiz;

    public ArvoreBinariaBusca(){
        raiz = null;
    }

    public boolean estaVazia(){
        return raiz == null;
    }

    public void adicionar(int novoValor){

        if(estaVazia()){
            raiz = new No(novoValor);
            return;
        }

        No atual = raiz;
        No pai = null;

        while(atual != null){
            pai = atual;

            if(novoValor < atual.valor){
                atual = atual.esquerdo;
            }
            else if(novoValor > atual.valor){
                atual = atual.direito;
            }
            else{
                return;
            }
        }

        No novoNo = new No(novoValor);
            if(novoValor < pai.valor){
                pai.esquerdo = novoNo;
            }
            else {
                pai.direito = novoNo;
            }
        }

        public No buscar (int valorBuscado){
            No atual = raiz;

            while(atual != null){

                if(valorBuscado == atual.valor){
                    return atual;
                }
                else if(valorBuscado < atual.valor){
                    atual = atual.esquerdo;
                }
                else {
                    atual = atual.direito;
                }
            }
            return null;
        }

        public boolean contem(int valorBuscado){
            return buscar(valorBuscado) != null;
        }

        public void remover(int valorRemover){

            if(estaVazia()){
                return;
            }

            No atual = raiz;
            No pai = null;

            while(atual != null && valorRemover != atual.valor){
                pai = atual;

                if(valorRemover < atual.valor){
                    atual = atual.esquerdo;
                }
                else {
                    atual = atual.direito;
                }
            }

            if(atual == null){
                return; //nao achou
            }

            //folha e 1 filho
            if(atual.esquerdo == null || atual.direito == null){

                No filho;
                if(atual.esquerdo != null){
                    filho = atual.esquerdo;
                }
                else {
                    filho = atual.direito;
                }
                if(pai == null){
                    raiz = filho;
                }

                else {
                    if(atual == pai.esquerdo){
                        pai.esquerdo = filho;
                    }
                    else {
                        pai.direito = filho;
                    }
                }
            } 
            else {
                No paiSucessor = atual;
                No sucessor = atual.direito;

                while(sucessor.esquerdo != null){
                    paiSucessor = sucessor;
                    sucessor = sucessor.esquerdo;
                }

                atual.valor = sucessor.valor;

                if(paiSucessor == atual){
                    paiSucessor.direito = sucessor.direito;
                }
                else {
                    paiSucessor.esquerdo = sucessor.direito;
                }
            }
        }

        //Profundidade 
      public int profundidade(int valorNo){
        return profundidadeRecursivo(raiz, valorNo, 0);
      }
        
      private int profundidadeRecursivo(No noAtual, int valorNo, int profundidade){
        if(noAtual == null){
            return -1;
        }

        if(valorNo == noAtual.valor){
            return profundidade;
        }

        if(valorNo > noAtual.valor){
            return profundidadeRecursivo(noAtual.direito, valorNo, profundidade + 1);
        }
        else {
            return profundidadeRecursivo(noAtual.esquerdo, valorNo, profundidade + 1);
        }
      }

        //Altura
      public int alturaNo(int valorNo){
        No no = buscar(valorNo);

        if(no == null){
            return -1;
        }

        return alturaNoRecursivo(no);
      }

      private int alturaNoRecursivo(No noAtual){
        if(noAtual == null){
            return -1;
        }

        int alturaEsquerda = alturaNoRecursivo(noAtual.esquerdo);
        int alturaDireita = alturaNoRecursivo(noAtual.direito);

        return 1 + Math.max(alturaEsquerda, alturaDireita);

      }


       public void impressaoInOrder(){
            //EVD
            impressaoInOrderRecursivo(raiz);
       }

       private void impressaoInOrderRecursivo(No noAtual){
        if(noAtual != null){
            impressaoInOrderRecursivo(noAtual.esquerdo);
            System.out.println(noAtual.valor + " ");
            impressaoInOrderRecursivo(noAtual.direito);
        }
       }

        public void impressaoPosOrder(){
            //EDV
            impressaoPosOrderRecursivo(raiz);
        }

        private void impressaoPosOrderRecursivo(No noAtual){
            if(noAtual != null){
                impressaoPosOrderRecursivo(noAtual.esquerdo);
                impressaoPosOrderRecursivo(noAtual.direito);
                System.out.println(noAtual.valor + " ");
            }
        }

        public void impressaoPreOrder(){
            //VED
            impressaoPreOrderRecursivo(raiz);
        }

        private void impressaoPreOrderRecursivo(No noAtual){
            if(noAtual != null){
                System.out.println(noAtual.valor + " ");
                impressaoPreOrderRecursivo(noAtual.esquerdo);
                impressaoPreOrderRecursivo(noAtual.direito);
            }

        }
}