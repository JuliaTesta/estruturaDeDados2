package listaestatica;

import java.util.Objects;

public class Cliente {


    private final String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }
        
        if (outroObjeto == null || getClass() != outroObjeto.getClass()) {
            return false;
        }

        Cliente outroCliente = (Cliente) outroObjeto;
        return Objects.equals(nome, outroCliente.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
