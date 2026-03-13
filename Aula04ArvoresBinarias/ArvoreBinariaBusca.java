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
                    pai.direito = filho;
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
                paiSucessor.direito = sucessor.direito;
            }
            else {
                paiSucessor.esquerdo = sucessor.direito;
            }

        }
    }

    public int profundidadeNo(int valorNo){
        return profundidadeNoRecursivo(raiz, valorNo, 0);
    }

    private int profundidadeNoRecursivo(No noAtual, int valorNo, int profundidade ){
        
        if (noAtual == null){
            return -1;
        }

        if (valorNo == noAtual.valor){
            return profundidade;
        }

        if(valorNo > noAtual.valor ){
            return profundidadeNoRecursivo(noAtual.direito, valorNo, profundidade + 1);
        }
        else{
            return profundidadeNoRecursivo(noAtual.esquerdo, valorNo, profundidade + 1);
        }

    }
}
