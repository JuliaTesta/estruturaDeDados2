package listas;

public class Fila {
    private ListaLigada fila;   

    public Fila() {
        this.fila = new ListaLigada();
    }

    public void adicionar(int dado) { //enqueue
        fila.adicionarFim(dado);
    }

    public void remover() { //dequeue
        if (fila.tamanho() == 0) {
            throw new IllegalStateException("Fila vazia");
        }
        fila.removerInicio();
    }

    public boolean contem(int dado) {
        return fila.contem(dado);
    }

    public int tamanho() {
        return fila.tamanho();
    }

    public boolean estaVazia() {
        return fila.tamanho() == 0;
    }

    @Override
    public String toString() {
        return fila.toString();
    }
}
