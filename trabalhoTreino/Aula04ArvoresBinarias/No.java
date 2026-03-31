import java.util.ArrayList;

public class No {
    
    Dispositivo disp;
    No esquerdo;
    No direito;

    ArrayList<Leitura>historico;
    
    public No(Dispositivo disp) {
        this.disp = disp;
        esquerdo = null;
        direito = null;
    }

    public boolean folha(){
        return esquerdo == null && direito == null;
    }
}