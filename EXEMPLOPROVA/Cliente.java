public class Cliente {
    int codigo;
    String nome;
    String endereco;

    public Cliente(int codigo, String nome, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente [codigo=" + codigo + ", nome=" + nome + ", endereco=" + endereco + "]";
    }

}
