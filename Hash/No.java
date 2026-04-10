public class No {
    Cliente dado;       
    No proximo;     
    No anterior;    

    public No(Cliente dado) {
        this.dado = dado;
        this.proximo = null;
        this.anterior = null;
    }
}