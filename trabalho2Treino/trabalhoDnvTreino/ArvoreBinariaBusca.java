public class ArvoreBinariaBusca {

    No raiz;
    private int quantidadeNos;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.quantidadeNos = 0;
    }

    public No inserir(Dispositivo disp) {
        if (estaVazia()) {
            raiz = new No(disp);
            quantidadeNos++;
            return raiz;
        }

        No atual = raiz;
        No pai = null;

        // Percorre a árvore até encontrar a posição de inserção
        while (atual != null) {
            pai = atual;
            if (disp.id < atual.disp.id) {
                atual = atual.esquerdo;
            } else if (disp.id > atual.disp.id) {
                atual = atual.direito;
            } else {
                return atual; // Valor já existe, ignora
            }
        }

        // Após encontrar a posição (atual == null), insere o novo nó
        No novoNo = new No(disp);
        if (disp.id < pai.disp.id) {
            pai.esquerdo = novoNo;
        } else {
            pai.direito = novoNo;
        }
        quantidadeNos++;
        return novoNo;
    }

    public boolean existe(Dispositivo disp) {
        return (buscar(disp.id) != null);
    }

    public No buscar(int idBuscado) {
        No atual = raiz;
        while (atual != null) {
            if (idBuscado == atual.disp.id)
                return atual;
            else if (idBuscado < atual.disp.id)
                atual = atual.esquerdo;
            else
                atual = atual.direito;
        }
        return null;
    }

    public void remover(int idRemover) {

        if (estaVazia()) {
            return;
        }

        No atual = raiz;
        No pai = null;

        // Encontrar o nó a ser removido e seu pai
        while (atual != null && atual.disp.id != idRemover) {
            pai = atual;
            if (idRemover < atual.disp.id) {
                atual = atual.esquerdo;
            } else {
                atual = atual.direito;
            }
        }

        // Se o nó não foi encontrado, retorna
        if (atual == null) {
            return;
        }

        // Caso 1 e 2: Nó sem filhos ou com apenas um filho
        if (atual.esquerdo == null || atual.direito == null) {
            No filho;
            if (atual.esquerdo != null) {
                filho = atual.esquerdo;
            } else {
                filho = atual.direito;
            }
            // Se o nó a ser removido é a raiz
            if (pai == null) {
                raiz = filho;
            } else {
                // Conecta o pai ao filho do nó removido
                if (atual == pai.esquerdo) {
                    pai.esquerdo = filho;
                } else {
                    pai.direito = filho;
                }
            }
        }
        // Caso 3: Nó com dois filhos
        else {
            // Encontrar o sucessor (menor valor da subárvore direita)
            No paiSucessor = atual;
            No sucessor = atual.direito;
            while (sucessor.esquerdo != null) {
                paiSucessor = sucessor;
                sucessor = sucessor.esquerdo;
            }

            // Copiar o valor do sucessor para o nó atual
            atual.disp.id = sucessor.disp.id;
            atual.historico = sucessor.historico;

            // Remover o sucessor (que tem no máximo um filho direito)
            if (paiSucessor == atual) {
                paiSucessor.direito = sucessor.direito;
            } else {
                paiSucessor.esquerdo = sucessor.direito;
            }
        }
        quantidadeNos--;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public int quantidadeNos() {
        return quantidadeNos;
    }

    public int calcularAlturaArvore() {
        return calcularAlturaRecursivo(raiz);
    }

    private int calcularAlturaRecursivo(No atual) {
       if(atual == null){
        return -1;
       }
       int alturaEsquerda = calcularAlturaRecursivo(atual.esquerdo);
       int alturaDireita = calcularAlturaRecursivo(atual.direito);
       return 1+ Math.max(alturaDireita, alturaEsquerda);
    }

    public int calcularAlturaNo(Dispositivo disp) {
        No no = buscar(disp.id);
        if(no == null){
            return -1;
        }
        return calcularAlturaRecursivo(no);

    }

    public int calcularProfundidadeArvore() {
       return calcularAlturaArvore();
    }

    public int calcularProfundidadeNo(Dispositivo disp) {
        return calcularProfundidadeRecursivo(raiz, disp.id, 0);
    }

    private int calcularProfundidadeRecursivo(No atual, int id, int profundidade) {
       if(atual == null){
            return -1;
       }
       if(atual.disp.id == id){
            return profundidade;
       }
       if(id < atual.disp.id) {
            return calcularProfundidadeRecursivo(atual.esquerdo, id, profundidade + 1);
       }
       return calcularProfundidadeRecursivo(atual.direito, id, profundidade +1);
    }

    public String imprimirPreOrdem() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        imprimirPreOrdemRecursivo(raiz, sb);
        
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length()); // Remove a última vírgula e espaço
        }
        sb.append("]");
        return sb.toString();
    }

    private void imprimirPreOrdemRecursivo(No atual, StringBuilder sb) {
        if (atual != null) {
            sb.append(atual.disp.id).append(", ");
            imprimirPreOrdemRecursivo(atual.esquerdo, sb);
            imprimirPreOrdemRecursivo(atual.direito, sb);
        }
    }

    public String imprimirPosOrdem() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        imprimirPosOrdemRecursivo(raiz, sb);
        
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    private void imprimirPosOrdemRecursivo(No atual, StringBuilder sb) {
        if (atual != null) {
            imprimirPosOrdemRecursivo(atual.esquerdo, sb);
            imprimirPosOrdemRecursivo(atual.direito, sb);
            sb.append(atual.disp.id).append(", ");
        }
    }

    public String imprimirInOrdem() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        imprimirInOrdemRecursivo(raiz, sb);
        
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    private void imprimirInOrdemRecursivo(No atual, StringBuilder sb) {
        if (atual != null) {
            imprimirInOrdemRecursivo(atual.esquerdo, sb);
            sb.append(atual.disp.id).append(", ");
            imprimirInOrdemRecursivo(atual.direito, sb);
        }
    }

    //Novas funcionalidade
    public void exibirLeituras(int id){
        No no = buscar(id);

        if(no == null){
            System.out.println("Dispositivo Nao encontrado.\n");
            return;
        }

        System.out.println("Historico " + no.disp.nome + ":");
        for(Leitura l: no.historico){
            System.out.println("Valor: " + l.valor + " " + no.disp.unidade_medida + "Data/Hora: " + l.dataHora);
        }
    }

    public void atualizarLeituras(int id, float valor){
        No no = buscar(id);

        if(no == null){
            System.out.println("Dispositivo nao encontrado.\n");
            return;
        }

        //Ultima leitura
        if(!no.historico.isEmpty()){
            Leitura ultima = no.historico.get(no.historico.size() -1);
            System.out.println("Ultima leitura: " + ultima.valor + "--" + ultima.dataHora);
        }
        else {
            System.out.println("Nao ha leituras neste dispositivo.\n");
        }

        //sobrescrever
        if(no.historico.size() == 5){
            no.historico.remove(0); //mais antigo
        }

        no.historico.add(new Leitura(valor));
        System.out.println("Nova leitura: " + valor);
    }

    //Dispositivos com Alerta
    public void listarDispComAlerta(){
        listarEmAlertaRecursivo(raiz);
    }

    private void listarEmAlertaRecursivo(No noAtual){
        if(noAtual == null){
            return;
        }
        listarEmAlertaRecursivo(noAtual.esquerdo);

        if(!noAtual.historico.isEmpty()){
            Leitura ultima = noAtual.historico.get(noAtual.historico.size()-1);
            if(ultima.valor > noAtual.disp.valor_alerta){
                System.out.println("ID: " + noAtual.disp.id
                                    + " Nome: " + noAtual.disp.nome 
                                    + " Valor: " + ultima.valor
                                    + " Limite alerta: " + noAtual.disp.valor_alerta);
            }
        }
        listarEmAlertaRecursivo(noAtual.direito);
    }

    public double percentualEmAlerta(){
        int total = quantidadeNos();
        if(total == 0){
            return 0;
        }

        int alertas = contarAlertas(raiz);
        return (alertas * 100.0) / total;
    }

    private int contarAlertas(No noAtual){
        if(noAtual == null){
            return 0;
        }

        int count = 0;
        if(!noAtual.historico.isEmpty()){
            Leitura ultima = noAtual.historico.get(noAtual.historico.size()-1);
            if(ultima.valor > noAtual.disp.valor_alerta){
                count = 1;
            }
        }

        return count + contarAlertas(noAtual.direito) + contarAlertas(noAtual.esquerdo);
    }

}