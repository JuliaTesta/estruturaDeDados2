package listas;

public class Pilha{
    private ListaLigada pilha;

    public Pilha(){
        this.pilha = new ListaLigada();
    }

    public void adicionar(int dado){
        pilha.adicionarFim(dado);
    }

    public void remover(){
         if (pilha.tamanho() == 0) {
            throw new IllegalStateException("Pilha vazia");
        }
        pilha.removerFim();
    }

    public boolean contem(int dado){
        return pilha.contem(dado);
    }

    public int tamanho() {
        return pilha.tamanho();
    }

    public boolean estaVazia() {
        return pilha.tamanho() == 0;
    }

    @Override
    public String toString() {
        return pilha.toString();
    }
}