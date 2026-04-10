public class NoHash {
    Cliente dado;       
    NoHash proximo;     
    NoHash anterior;    

    public NoHash(Cliente dado) {
        this.dado = dado;
        this.proximo = null;
        this.anterior = null;
    }
}