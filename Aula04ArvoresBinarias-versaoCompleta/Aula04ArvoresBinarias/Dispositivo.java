public class Dispositivo {
    public int id;
    public String nome, localizacao, unidade_medida;
    public float valor_alerta;
   

    public Dispositivo (int id, String nome, String localizacao, String unidade_medida, float valor_alerta) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.unidade_medida = unidade_medida;
        this.valor_alerta = valor_alerta;
    }
}