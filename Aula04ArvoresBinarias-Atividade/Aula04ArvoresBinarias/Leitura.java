import java.time.LocalDateTime;

public class Leitura {
    float valor;
    LocalDateTime dataHora;
    
    public Leitura(float valor){
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }
}
