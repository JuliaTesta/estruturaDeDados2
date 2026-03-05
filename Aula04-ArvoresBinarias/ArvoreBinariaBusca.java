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
}
