import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(java.util.Locale.US);
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        int opcao;

        do {
            System.out.println("\n----MENU DISPOSITIVOS----\n");
            System.out.println(" 1 - Buscar Dispositivo");
            System.out.println(" 2 - Atualizar leitura de dispositivo");
            System.out.println(" 3 - Listar dispositivos em alerta");
            System.out.println(" 4 - Remover");
            System.out.println(" 5 - Importar");
            System.out.println(" 6 - Estatisticas");
            System.out.println(" 0 - Sair");
            System.out.println("Escolha uma opcao: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o ID do dispositivo: ");
                    int idBuscar = sc.nextInt();
                    arvore.exibirLeituras(idBuscar);
                    break;

                case 2:
                    System.out.println("Digite o ID do dispositivo: ");
                    int idAtualizar = sc.nextInt();

                    //Historico
                    arvore.exibirLeituras(idAtualizar);

                    System.out.println("Digite o novo valor da leitura: ");
                    float novoValor = sc.nextFloat();

                    arvore.atualizarLeitura(idAtualizar, novoValor);

                    System.out.println("Historico Atualizado: ");
                    arvore.exibirLeituras(idAtualizar);
                    break;
            
                case 3:
                    arvore.listarDispComAlerta();
                    break;
                
                case 4:
                    System.out.println("Digite o ID do dispositivo para remover: ");
                    int idRemover = sc.nextInt();
                    arvore.remover(idRemover);
                    break;

                case 5:
                    System.out.println("Digite o nome do arquivo csv: ");
                    sc.nextLine();
                    String caminho = sc.nextLine();

                    arvore.importarCSV(caminho);
                    break;

                case 6:
                    System.out.println("Total dispositivos: " + arvore.quantidadeNos());
                    System.out.println("Altura: " + arvore.calcularAlturaArvore());
                    System.out.println("% em Alerta: " + String.format("%.4f", arvore.percentualEmAlerta()) + "%");
                    break;
                default:
                    break;
            }
        } while(opcao != 0);
    }
}
