
public class ListaLigada {
    private NoHash cabeca;
    private NoHash cauda;
    private int tamanho;

    public ListaLigada() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public void adicionarInicio(Cliente dado) {

        if (dado == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        NoHash novoNo = new NoHash(dado);
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

    public void adicionarFim(Cliente dado) {
        if (dado == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        NoHash novoNo = new NoHash(dado);
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

    public void adicionarNaPosicao(Cliente dado, int posicao) {
        if (posicao < 0 || posicao > tamanho()) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }
        if (dado == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }

        NoHash novoNo = new NoHash(dado);

        if (posicao == 0) {
            adicionarInicio(dado);
            return;
        }

        if (posicao == tamanho()) {
            adicionarFim(dado);
            return;
        }

        NoHash atual = cabeca;
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

        NoHash atual = cabeca;
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
   
    public Cliente pegarNaPosicao(int posicao){

        if (posicao < 0 || posicao >= tamanho){
            throw new IndexOutOfBoundsException("Posicao invalida: " + posicao);
        }
        NoHash atual = cabeca;
        for(int i=0; i < posicao; i++){
            atual = atual.proximo;
        }
        return atual.dado;
    }

    public boolean contem(Cliente dado) {
        NoHash atual = cabeca;
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

    public NoHash obterInicio(){
        return this.cabeca;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        NoHash atual = cabeca;
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