package listas;

public class ListaLigada {
    
    private No cabeca;
    private No cauda;
    private int tamanho;

    public ListaLigada() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public void adicionarInicio(int dado) {
        No novoNo = new No(dado);
        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            novoNo.proximo = cabeca;
            cabeca.anterior = novoNo;
            cabeca = novoNo;
        }
        tamanho++;
    }

    public void adicionarFim(int dado) {
        No novoNo = new No(dado);
        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            novoNo.anterior = cauda;
            cauda.proximo = novoNo;
            cauda = novoNo;
        }
        tamanho++;
    }

    public void removerInicio() {
        if (cabeca == null) {
            return;
        }
        if (cabeca == cauda) {
            cabeca = null;
            cauda = null;
        } else {
            cabeca = cabeca.proximo;
            cabeca.anterior = null;
        }
        tamanho--;
    }

    public void removerFim() {
        if (cauda == null) {
            return;
        }
        if (cabeca == cauda) {
            cabeca = null;
            cauda = null;
        } else {
            cauda = cauda.anterior;
            cauda.proximo = null;
        }
        tamanho--;
    }

    public void adicionarNaPosicao(int dado, int posicao) {
        if (posicao < 0 || posicao > tamanho()) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        No novoNo = new No(dado);

        if (posicao == 0) {
            adicionarInicio(dado);
            return;
        }

        if (posicao == tamanho()) {
            adicionarFim(dado);
            return;
        }

        No atual = cabeca;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
        }
        novoNo.proximo = atual;
        novoNo.anterior = atual.anterior;
        atual.anterior.proximo = novoNo;
        atual.anterior = novoNo;
        tamanho++;
    }

    public void removerNaPosicao(int posicao) {
        if (cabeca == null || posicao < 0 || posicao >= tamanho()) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        if (posicao == 0) {
            removerInicio();
            return;
        }
        
        if (posicao == (tamanho - 1)){
            removerFim();
            return;
        }

        No atual = cabeca;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
        }

        if (atual == cauda) {
            cauda = atual.anterior;
            cauda.proximo = null;
            tamanho--;
            return;
        }

        atual.anterior.proximo = atual.proximo;
        atual.proximo.anterior = atual.anterior;
        tamanho--;
    }
   
    public int pegarNaPosicao(int posicao){

        if (posicao < 0 || posicao >= tamanho){
            throw new IndexOutOfBoundsException("Posicao invalida: " + posicao);
        }
        No atual = cabeca;
        for(int i=0; i < posicao; i++){
            atual = atual.proximo;
        }
        return atual.dado;
    }

    public boolean contem(int dado) {
        No atual = cabeca;
        while (atual != null) {
            if (atual.dado == dado) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public int tamanho() {
        return this.tamanho;
    }

    public No obterInicio(){
        return this.cabeca;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        No atual = cabeca;
        while (atual != null) {
            sb.append(atual.dado);
            if (atual.proximo != null) {
                sb.append(", ");
            }
            atual = atual.proximo;
        }
        sb.append("]");
        return sb.toString();
    }
}