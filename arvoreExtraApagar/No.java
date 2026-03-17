public class No {

    int valor;
    No esquerdo;
    No direito;

    public No(int valor){
        this.valor = valor;
        direito = null;
        esquerdo = null;
    }

    public boolean isFolha(No no){
        return no.esquerdo == null && no.direito == null;
    }
}