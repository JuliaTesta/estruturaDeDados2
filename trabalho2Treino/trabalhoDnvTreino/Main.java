import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(java.util.Locale.US);
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        int opcao;

        do{
            System.out.println("\n--------MENU---------\n");
            System.out.println(" 1 - Buscar Dispositivo\n");
            System.out.println(" 2 - Atualizar leitura Dispositivo\n");


            System.out.println("Escolha uma opcao: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o id a ser buscado: ");
                    int idBuscar = sc.nextInt();
                    arvore.exibirLeituras(idBuscar);
                    break;
            
                case 2: 
                    System.out.println("Digite o id do dispositivo: ");
                    int idAtualizar = sc.nextInt();

                    //historico
                    arvore.exibirLeituras(idAtualizar);

                    System.out.println("Digite o novo valor da leitura: ");
                    float novoValor = sc.nextFloat();

                    arvore.atualizarLeituras(idAtualizar, novoValor);

                    System.out.println("Historico Atualizado: ");
                    arvore.exibirLeituras(idAtualizar);
                    break;

                case 3:
                    arvore.listarDispComAlerta();
                    break;
                case 4:
                    System.out.println("Digite o ID para remover: ");
                    int idRemover = sc.nextInt();
                    arvore.remover(idRemover);
                    break;
                case 5:
                    System.out.println("Total: " + arvore.quantidadeNos());
                    System.out.println("Altura: " + arvore.calcularAlturaArvore());
                    System.out.println("% em alerta: " + arvore.percentualEmAlterta());
                    break;
                default:
                    break;
            }


        } while(opcao != 0);







        sc.close();
    }
}
