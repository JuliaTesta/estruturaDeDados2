package listaestatica;

import java.util.Arrays;

public class Vetor {
    
    private static final int CAPACIDADE_INICIAL = 5;    
    private Cliente[] clientes;
    private int totalDeClientes; //clientes que realmente existem

    public Vetor(){
       clientes = new Cliente[CAPACIDADE_INICIAL];
       totalDeClientes = 0;
    }

    private void garantirEspaco(){
        if(totalDeClientes == clientes.length){
            clientes = Arrays.copyOf(clientes, clientes.length * 2);
        }
    }

    public void adicionar(Cliente novoCliente){
        garantirEspaco();
        clientes[totalDeClientes] = novoCliente;
        totalDeClientes++;
    }

    public int pegarTotalClientes(){
        return totalDeClientes;
    }

    public int tamanhoVetor(){
        return clientes.length;
    }

    private boolean posicaoValida(int posicao){
       return posicao >= 0 && posicao < totalDeClientes;
       //posicao == totaldeclientes é uma posicao vazia
    }

    public Cliente pegar(int posicao){
        if(!posicaoValida(posicao)){
            throw new IllegalArgumentException("Posicao Invalida");
        }
        return clientes[posicao];
    }

    public boolean contem(Cliente clienteBuscado){
        for(int i=0; i<totalDeClientes; i++){
            if(clientes[i].equals(clienteBuscado)){
                return true;
            }
        }
        return false;
    }

    public void remover(int posicaoRemover){
       if(!posicaoValida(posicaoRemover)){
            throw new IllegalArgumentException("Posicao Invalida");
       }
       for(int i=posicaoRemover; i<totalDeClientes-1; i++){
            clientes[i] = clientes[i+1];
       }
       clientes[totalDeClientes-1] = null;
       totalDeClientes--;
    }


    //EXERCICIOS 

    //001
    public void inserirEspecifico(int posicaoInserir, Cliente novoCliente){
        if(posicaoInserir < 0 || posicaoInserir > totalDeClientes){
            throw new IllegalArgumentException("Posicao Invalida");
        }

        //Deslocar Para Direita
        for(int i=totalDeClientes-1; i<=posicaoInserir; i--){
            clientes[i+1] = clientes[i];
        }
        clientes[posicaoInserir] = novoCliente;
        totalDeClientes++;
    }

    //002
    public void removerUltimo(){
        if(totalDeClientes == 0){
            throw new IllegalArgumentException("Posicao Invalida");
        }
        remover(totalDeClientes-1);
    }

    //003
    public void removerPrimeiro(){
        if(totalDeClientes == 0){
            throw new IllegalArgumentException("Posicao Invalida");
        }
        remover(0);
    }

    //004
    public void removerTodos(){
       if(totalDeClientes == 0){
            throw new IllegalArgumentException("Posicao Invalida");
        }
        while(totalDeClientes > 0){
            removerUltimo();
        }
    }

    //005
    public void reduzirMetade(){
        if(totalDeClientes == 0){
            throw new IllegalArgumentException("Posicao Invalida");
        }

        if(totalDeClientes <= clientes.length/4 && clientes.length/2 >= CAPACIDADE_INICIAL){
            clientes = Arrays.copyOf(clientes, clientes.length/2);
        }
    }
}

