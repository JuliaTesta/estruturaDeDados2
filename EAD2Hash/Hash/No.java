public class No {
    ItemCache dado;       
    No proximo;     
    No anterior;    

    public No(ItemCache dado) {
        this.dado = dado;
        this.proximo = null;
        this.anterior = null;
    }
}