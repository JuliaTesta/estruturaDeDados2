public class TabelaHash {

    private int tamanho;
    private ListaLigada[] tabela;

    private static final long TTL = 5*60*1000; //5 min em milissegundos -> para verificar se expirou ou não

    public TabelaHash(int tamanho){

        if (tamanho <= 0){
            throw new IllegalArgumentException("O tamanho não pode ser menor ou igual a zero!");
        }
        this.tamanho = tamanho;
        this.tabela = new ListaLigada[tamanho];

        for (int i=0; i< tamanho; i++){
            tabela[i] = new ListaLigada();
        }
    }

    public int calcularHash(String sql){ //muda para String ao invés de int 

        int hash = 0;
        for (int i=0; i< sql.length(); i++){
            hash = 31 * hash + sql.charAt(i);
        }
        return Math.abs(hash % tamanho); //math garante tamanho de 0 ate tamanho-1
    }

    public void inserir (ItemCache item){  //mudançass
        //pede que: se a SQL já existir no cache, deve atualizar o resultado e o timestamp
        
        long inicio = System.nanoTime();

        if (item == null){
            throw new IllegalArgumentException("Item nao pode ser nulo!");
        }

        int enderecoHash = calcularHash(item.sql); //
        ListaLigada lista = tabela[enderecoHash];

        int elementosPercorridos = 0;

        for(int i=0; i<lista.tamanho(); i++){
            elementosPercorridos++;

            ItemCache atual = lista.pegarNaPosicao(i);

            if(atual.sql.equals(item.sql)){
                atual.resultado = item.resultado; //atualizando
                atual.timestampCriacao = System.currentTimeMillis(); //atualizando
            
                long fim = System.nanoTime();

                System.out.println("Consulta atualizada no cache.");
                System.out.println("Bucket: " + enderecoHash);
                System.out.println("Elementos Percorridos: " + elementosPercorridos);
                System.out.println("Tempo de Insercao: " + (fim-inicio) + " ns");

                return;
            }
        }

        //Se ainda não existir
        lista.adicionarFim(item);

        long fim = System.nanoTime();

        System.out.println("Consulta inserida no cache");
        System.out.println("Bucket: " + enderecoHash);
        System.out.println("Elementos percorridos: " + elementosPercorridos);
        System.out.println("Tempo de insercao: " + (fim - inicio) + " ns");
    }

    public ItemCache buscar(String sqlBuscada){ //alterado

        long inicio = System.nanoTime();

        int enderecoHash = calcularHash(sqlBuscada);//
        ListaLigada lista = tabela[enderecoHash];

        int elementosPercorridos = 0;

        for (int i=0; i< lista.tamanho(); i++){
            elementosPercorridos++;

            ItemCache item = lista.pegarNaPosicao(i);

            if (item.sql.equals(sqlBuscada)){ //
                long agora = System.currentTimeMillis();

                if(agora - item.timestampCriacao > TTL){
                    lista.removerNaPosicao(i);

                    long fim = System.nanoTime();

                    System.out.println("Cache expirado-cache miss");
                    System.out.println("Elementos Percorridos: " + elementosPercorridos);
                    System.out.println("Tempo de Busca: " + (fim-inicio) + " ns");

                    return null;
                }

                //se ainda é validp
                long fim = System.nanoTime();

                System.out.println("Cache hit - resultado recuperado");
                System.out.println("Resultado: " + item.resultado);
                System.out.println("Elementos percorridos: " + elementosPercorridos);
                System.out.println("Tempo de busca: " + (fim - inicio) + " ns");
                return item;
            }
        }
        //Se não foi encontrado
            long fim = System.nanoTime();

            System.out.println("Cache miss - Consulta nao encontrada.");
            System.out.println("Elementos Percorridos: " + elementosPercorridos);
            System.out.println("Tempo de Busca: " + (fim - inicio) + " ns");
            return null;
    }

    //remover (codigo)
    public void remover(String sql){ //

        int enderecoHash = calcularHash(sql);
        ListaLigada lista = tabela[enderecoHash]; //lista aponta para uma lista específica dentro do array

        for(int i =0; i<lista.tamanho(); i++){
            ItemCache item = lista.pegarNaPosicao(i); //

            if(item.sql.equals(sql)){ //
                lista.removerNaPosicao(i);
                return;
            }
        }
        System.out.println("Consulta nao encontrada");
    }


    //imprimirElementos()
    public void imprimirCache(){
        for(int i=0; i<tamanho; i++){
            System.out.println("Bucket " + i + ": ");
        

        ListaLigada lista = tabela[i];

        for(int j=0; j<lista.tamanho(); j++){
                ItemCache item = lista.pegarNaPosicao(j); //
                System.out.println(item);
            }
        }
    }
    //BUSCAR E INSERIR CORRETOS, CONTINUAR NO ITEM 3 DO MENU

}
