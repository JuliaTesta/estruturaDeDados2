public class NoABB {
   
    int valor;
    NoABB direito;
    NoABB esquerdo;

    public NoABB (int valor){
        this.valor = valor;
        direito = null;
        esquerdo = null;
    }

    public boolean isFolha(NoABB no){
        return no.esquerdo == null && no.direito == null;
    }

    

}