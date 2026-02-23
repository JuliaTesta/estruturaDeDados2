package listaestatica;

import java.util.Objects;

public class Cliente {
    private final String nome;

    public Cliente(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    @Override
    public String toString(){
        return nome;
    }
    //Se tivesse isso: 
//Cliente c = new Cliente("Alice");
//System.out.println(c); -> chama c.toString() automaticamente

    @Override
    public boolean equals(Object outroObjeto){
        if(this == outroObjeto){
            return true;
        }

        //se o objeto for nulo ou nao for extamente um cliente, então eles nao sao iguais   
        if(outroObjeto == null || getClass() != outroObjeto.getClass()){
            return false;
        }
        //"confie em mim, esse objeto é um cliente de vdd:"
        Cliente outroCliente = (Cliente) outroObjeto;
        return Objects.equals(nome, outroCliente.nome);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nome);//hash code (numero) é o mesmo para dois objetos iguais
    }
}

//obs -> criando um objeto 
//Object outroObjeto = new Cliente("Alice");

//tipo da variável é Object, mas o objeto criado de fato é um Cliente
//depois acontece o casting para assegurar que é um cliente. (é um cliente mas está sendo tratado como object)