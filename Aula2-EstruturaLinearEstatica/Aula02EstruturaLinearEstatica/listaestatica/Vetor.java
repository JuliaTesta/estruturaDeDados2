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

   private void garatirEspaco(){
     if (totalDeClientes == clientes.length){
          clientes = Arrays.copyOf(clientes, clientes.length * 2);
     }
   }

   public void adicionar(Cliente novoCliente){
        garatirEspaco();
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
        return posicao >=0 && posicao < totalDeClientes;
   }

   public Cliente pegar(int posicao){
        if (!posicaoValida(posicao)){
            throw new IllegalArgumentException("Posicao Invalida");
        } 
        return clientes[posicao];
   }

   public boolean contem(Cliente clienteBuscado){
          for(int i=0; i<totalDeClientes; i++){
               if (clientes[i].equals(clienteBuscado)){
                    return true;
               }
          }
          return false;
   }

   public void remover (int posicaoRemover){
          if (!posicaoValida(posicaoRemover)){
               throw new IllegalArgumentException("Posicao Invalida");
          }
          for (int i=posicaoRemover; i<totalDeClientes-1; i++){
               clientes[i] = clientes[i+1];
          }
          clientes[totalDeClientes-1] = null;
          totalDeClientes--;

          if(totalDeClientes > 0){
               reduzirMetade();
          }
   }

   //001 
   public void inserirEspecifico(int posicaoInserir, Cliente novoCliente){
     if(posicaoInserir < 0 || posicaoInserir > totalDeClientes){
          throw new IllegalArgumentException("Posicao Invalida");
     }
     
     garatirEspaco();

     //Deslocar para direita
     for(int i = totalDeClientes - 1; i >= posicaoInserir; i--){
          clientes[i+1] = clientes[i];
     }
     
     clientes[posicaoInserir] = novoCliente;
     totalDeClientes++;
   }

   //002
   public void removerUltimo(){
     if(totalDeClientes == 0){
          throw new IllegalArgumentException("Nao ha clientes");
     }
     remover(totalDeClientes-1);
   }

   //003
   public void removerPrimeiro(){
     if(totalDeClientes == 0){
          throw new IllegalArgumentException("Nao ha clientes");
     }
     remover(0);
   }

   //004
   public void removerTodos(){
     if(totalDeClientes == 0){
          throw new IllegalArgumentException("Nao ha clientes");
     }
     
     while(totalDeClientes>0){
          removerUltimo();
     }
   }

   //005
   public void reduzirMetade(){    
     if(totalDeClientes == 0){
          throw new IllegalArgumentException("Nao ha clientes");
     }

     //garantir que nao reduza menos do que a capacidadeInicial
     if(totalDeClientes <= clientes.length/4 && clientes.length/2 >= CAPACIDADE_INICIAL){
          //reduzir
          clientes = Arrays.copyOf(clientes, clientes.length/2);
     }
   }
    
}

