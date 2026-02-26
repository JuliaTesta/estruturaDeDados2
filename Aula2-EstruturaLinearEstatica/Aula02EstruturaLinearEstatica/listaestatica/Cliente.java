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

    //usa override quando for reescrever um metodo que ja existe: toString, equals, hashCode...
    @Override
    public String toString() { //System.out.println("Cliente: " + c); chama o toString automaticamente
        return nome;
    }

    @Override
    public boolean equals(Object outroObjeto) {
        //Cliente c1 = new Cliente("Maria");
        //Cliente c2 = c1;
        //c1 == c2 // true 
        if (this == outroObjeto) {
            return true;
        }
        
        if (outroObjeto == null || getClass() != outroObjeto.getClass()) {
            return false;
        }

        //Cast: trate esse objeto como cliente
        Cliente outroCliente = (Cliente) outroObjeto;
       
        //Cliente c1 = new Cliente("Maria");
        //Cliente c2 = new Cliente("Maria"); ->MEMÓRIAS DIFERENTES
        //c1 == c2 // false, são objetos diferentes
        return Objects.equals(nome, outroCliente.nome);
    }

    @Override
    //se dois objetos sao iguais pelo equals eles DEVEM ter o mesmo hashCode
    public int hashCode() {
        return Objects.hash(nome);
    }
}
