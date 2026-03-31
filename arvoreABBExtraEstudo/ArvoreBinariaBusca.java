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


        //EXERCICIOS

        //Altura Arvore
       public int alturaArvore(){
        return alturaRecursivo(raiz);
       }

        //Altura Nó
        public int alturaNo(int valorBuscado){
            No no = buscar(valorBuscado);

            if(no == null){
                return -1;
            }
            return alturaRecursivo(no);
        }

        private int alturaRecursivo(No noAtual){
            if(noAtual == null){
                return -1;
            }

            int altEsq = alturaRecursivo(noAtual.esquerdo);
            int altDir = alturaRecursivo(noAtual.direito);

            return 1 + Math.max(altEsq, altDir);
        }
       
        //Profundidade Arvore
        public int profundidadeArvore(){
            return alturaArvore();
        }

        //Profundidade Nó
       public int profundidadeNo(int valorBuscado){
        return profundidadeRecursivo(raiz, valorBuscado, 0);
      }

      private int profundidadeRecursivo(No noAtual, int valorBuscado, int profundidade){
        if(noAtual == null){
            return -1;
        }

        if(valorBuscado == noAtual.valor){
            return profundidade;
        }

        if(valorBuscado > noAtual.valor){
            return profundidadeRecursivo(noAtual.direito, valorBuscado, profundidade + 1);
        }

        return profundidadeRecursivo(noAtual.esquerdo, valorBuscado, profundidade + 1);
      }
      
        //Impressao InOrder EVD
        public void impressaoInOrder(){
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

        //contar nos 
        public int contarNos(){
            return contarNosRecursivo(raiz);
        }

        private int contarNosRecursivo(No noAtual){
            if(noAtual == null){
                return 0;
            }

            return 1 + contarNosRecursivo(noAtual.direito) + contarNosRecursivo(noAtual.esquerdo);
        }
       
        //encontre o menor e maior valor
        public int maiorValor(){
            No atual = raiz;

            if(raiz == null){
                return -1;
            }

            while(atual.direito != null){
                atual = atual.direito;
            }

            return atual.valor;
        }

        public int menorValor(){
            No atual = raiz;

            if(raiz == null){
                return -1;
            }

            while(atual.esquerdo != null){
                atual = atual.esquerdo;
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

            return contarNosRecursivo(noAtual.direito) + contarNosRecursivo(noAtual.esquerdo);
        }
        
        
        //verificar balanceamento
        public boolean estaBalanceada(){
            return estaBalanceadaRecursivo(raiz);
        }

        private boolean estaBalanceadaRecursivo(No noAtual){
            if(noAtual== null){
                return true;
            }

            int altEsq = alturaRecursivo(noAtual.esquerdo);
            int altDir = alturaRecursivo(noAtual.direito);

            if(Math.abs(altEsq - altDir) > 1){
                return false;
            }

            //verificar para os filhos
            return estaBalanceadaRecursivo(noAtual.esquerdo) && estaBalanceadaRecursivo(noAtual.direito);
        }
        
}