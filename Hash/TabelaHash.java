public class TabelaHash {

    private int tamanho;
    private ListaLigada[] tabela;

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

    public int calcularHash(int chave){

        String chaveConvertida = String.valueOf(chave);
        int hash = 0;
        for (int i=0; i<chaveConvertida.length(); i++){
            hash = 31 * hash + chaveConvertida.charAt(i);
        }
        return hash % tamanho;
    }

    public void inserir (Cliente novoCliente){

        if (novoCliente == null){
            throw new IllegalArgumentException("Cliente nao pode ser nulo!");
        }

        int enderecoHash = calcularHash(novoCliente.codigo); 
        
        ListaLigada lista = tabela[enderecoHash];

        if (!lista.contem(novoCliente)){
            lista.adicionarFim(novoCliente);
        }
    }

    public Cliente buscarClientePorCodigo(int codigoClienteBuscado){

        int enderecoHash = calcularHash(codigoClienteBuscado);
        ListaLigada lista = tabela[enderecoHash];

        for (int i=0; i< lista.tamanho(); i++){
            Cliente clienteAtual = lista.pegarNaPosicao(i);
            if (clienteAtual.codigo == codigoClienteBuscado){
                return clienteAtual;
            }
        }

        return null;
    }

    //remover (codigo)
    public void remover(int codigoCliente){

        int enderecoHash = calcularHash(codigoCliente);
        ListaLigada lista = tabela[enderecoHash]; //lista aponta para uma lista específica dentro do array

        for(int i =0; i<lista.tamanho(); i++){
            Cliente clienteAtual = lista.pegarNaPosicao(i);

            if(clienteAtual.codigo == codigoCliente){
                lista.removerNaPosicao(i);
                return;
            }
        }
        System.out.println("Cliente nao encontrado");
    }


    //imprimirElementos()
    public void imprimirClientes(){
        for(int i=0; i<tamanho; i++){
            System.out.println("Posicao " + i + ": ");
        

        ListaLigada lista = tabela[i];

        for(int j=0; j<lista.tamanho(); j++){
                Cliente cliente = lista.pegarNaPosicao(j);
                System.out.println(" Codigo: " + cliente.codigo);
            }
        }
    }

    //LISTA EXERCÍCIO
    //1- Contar indices com colisao
    public int contarIndicesComColisao(){
        int contador = 0;

        for(int i=0; i< tamanho; i++){
            ListaLigada lista = tabela[i];

            if(lista.tamanho() > 1){ //mais de um elemento = colisao
                contador++;
            }
        }
        return contador;
    }

    //2 - Maior cliente por índice
    public Cliente[] maioresPorIndice(){
        Cliente[] resultado = new Cliente[tamanho];//array que guarda referencias para clientes. 

        for(int i=0; i<tamanho; i++){
            ListaLigada lista = tabela[i];
            Cliente maior = null;

            for(int j=0; j<lista.tamanho(); j++){
                Cliente c = lista.pegarNaPosicao(j);

                if(maior == null || c.codigo > maior.codigo){
                    maior = c;
                }
            }
            //se a lista estiver vazia:
            resultado[i] = maior;
        }
        return resultado;
    }

    //3- Contar Clientes por Inicial
    public int contarClientesPorInicial(char letra){
        int contador = 0;
        letra = Character.toUpperCase(letra);

        for(int i=0; i<tamanho; i++){
            ListaLigada lista = tabela[i];

            for(int j=0; j<lista.tamanho(); j++){
                Cliente c = lista.pegarNaPosicao(j);
                if(!c.nome.isEmpty() && Character.toUpperCase(c.nome.charAt(0)) == letra){
                    contador++;
                }
            }
        }
        return contador;
    }
}
