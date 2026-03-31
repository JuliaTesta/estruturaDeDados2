import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(java.util.Locale.US);
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        int opcao;

        do {
            System.out.println("---MENU---");
            System.out.println(" 1 - Buscar Dispositivo");
            System.out.println(" 2 - Atualizar Leituras");
            System.out.println(" 3 - Listar Dipositivos em Alerta");
            System.out.println(" 4 - Remover");
            System.out.println(" 5 - Importar");
            System.out.println(" 6 - Estatisticas");
            System.out.println("0 - sair");
            System.out.println("Escolha uma opcao: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o id a ser buscado: ");
                    int idBuscar = sc.nextInt();
                    arvore.exibirLeituras(idBuscar);
                    break;
            
                case 2:
                    System.out.println("Digite o id a ser atualizado: ");
                    int idAtualizar = sc.nextInt();

                    arvore.exibirLeituras(idAtualizar);

                    System.out.println("Digite o novo valor da leitura: ");
                    float novoValor = sc.nextFloat();

                    arvore.atualizarLeituras(idAtualizar, novoValor);

                    System.out.println("Historico Atualizado: ");
                    arvore.exibirLeituras(idAtualizar);
        
                    break;
                case 3:
                    arvore.listarEmAlerta();
                    break;
                case 4:
                    System.out.println("Digite o id a ser removido:");
                    int idRemover = sc.nextInt();

                    arvore.remover(idRemover);
                    break;
                    
                default:
                    break;
            }

        } while(opcao != 0);




        sc.close();
    }
}
