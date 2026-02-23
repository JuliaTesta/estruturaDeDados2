import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Sistema de Blog - Versão com Gargalos de Performance
 * 
 * CONTEXTO: Sua startup de blog está crescendo rapidamente.
 * O sistema precisa mostrar o primeiro e último post de cada usuário
 * na página de perfil. Com 1.000+ usuários, a página está demorando
 * muito para carregar.
 * 
 * DESAFIO: Identifique os gargalos de complexidade e refatore o código
 * para melhorar a performance, justificando cada decisão.
 * 
 * DICAS PARA ANÁLISE:
 * - Qual a complexidade de tempo de cada método?
 * - Existem operações redundantes?
 * - As estruturas de dados são adequadas?
 * - Como o código se comporta com N usuários e M posts?
 */

public class SistemaPostsBlog {
    
    //Nesse caso, as listas não são eficientes -> O(n). Seria mais interessante realizar uma busca por id.
    private List<Usuario> usuarios;
    //Qualquer consulta por um usuário percorre todos os posts -> O(m), uma vez que estão em uma única lista.
    private List<Post> todosOsPosts;
    
    public SistemaPostsBlog() {
        //Buscas lineares frenquentes causa gargalo.
        this.usuarios = new ArrayList<>();
        this.todosOsPosts = new ArrayList<>();
    }
    
    public void adicionarPost(int usuarioId, String conteudo) {
        Usuario usuario = null;
        //Busca linear por usuário a cada post adicionado causa gargalo pelo grande numero de usuarios.
        for (Usuario u : usuarios) {
            if (u.getId() == usuarioId) {
                usuario = u; 
                break;
            }
        }
        
        if (usuario != null) { 
            Post novoPost = new Post(usuarioId, conteudo, System.currentTimeMillis());
            //Todos os posts ficam juntos, dificultando consultas futuras eficientes.
            todosOsPosts.add(novoPost);
            //Sistema ignora essa valor e posteriormente recalcula tudo.
            usuario.incrementarContadorPosts();
        }
    }
    
    public PostsExtremos obterPrimeiroEUltimoPost(int usuarioId) {
        //Criação de lista intermediária desnecessária pois guaradará TODOS os posts e nao apenas o primeiro e o ultimo.
        List<Post> postsDoUsuario = new ArrayList<>();
        
        //Gargalo pois percorre todos os posts do sistema para obter apenas o primeiro e o ultimo.
        for (Post post : todosOsPosts) {
            if (post.getUsuarioId() == usuarioId) {
                postsDoUsuario.add(post);
            }
        }
        
        //Gargalo -> O(p²). Desnecessário pois só é preciso o maior e menor timestamp, então basta percorrer uma vez e guardar essa informações.
        //Não é preciso ordenar a lista inteira.
        for (int i = 0; i < postsDoUsuario.size() - 1; i++) {
            for (int j = 0; j < postsDoUsuario.size() - i - 1; j++) {
                if (postsDoUsuario.get(j).getTimestamp() > 
                    postsDoUsuario.get(j + 1).getTimestamp()) {
                    // Troca de elementos desnecessarias. Gasto computacional.
                    Post temp = postsDoUsuario.get(j);
                    postsDoUsuario.set(j, postsDoUsuario.get(j + 1));
                    postsDoUsuario.set(j + 1, temp);
                }
            }
        }
        
        if (postsDoUsuario.isEmpty()) {
            return null; 
        }
        
        //A lista foi totalmente ordenada para usar apenas dois elementos, causando desperdício.
        Post primeiro = postsDoUsuario.get(0);
        Post ultimo = postsDoUsuario.get(postsDoUsuario.size() - 1);
        
        return new PostsExtremos(primeiro, ultimo);
    }
    
    public void adicionarUsuario(Usuario usuario) {
        boolean existe = false; //Verificando duplicidade
        
        //Busca linear - Ineficiente para muitos usuários
        for (Usuario u : usuarios) {
            if (u.getId() == usuario.getId()) {
                existe = true;
                break;
            }
        }
        
        if (!existe) {
            usuarios.add(usuario);
        }
    }
    
    public int contarPostsDoUsuario(int usuarioId) {
        int contador = 0;
        
        //Busca linear ineficiente -> O(n). 
        for (Post post : todosOsPosts) {
            if (post.getUsuarioId() == usuarioId) {
                contador++; //Redundante, pois já existe o contador no objeto usuário.
            }
        }
        return contador;
    }
    
    public String gerarRelatorioGeral() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO DO BLOG ===\n\n");
        
        //Busca linear ineficiente para grande numero de usuarios -> O(n).
        for (Usuario usuario : usuarios) {
            relatorio.append("Usuário: ").append(usuario.getNome()).append("\n");
            
            //Para cada usuario é chamado o contar posts
            //Chama O(m) dentre de O(n) =  O(n*m).
            int totalPosts = contarPostsDoUsuario(usuario.getId());
            relatorio.append("Total de posts: ").append(totalPosts).append("\n");
            
            //Para um usuario especifico, chama o obterPrimeiroEUltimoPost
            //Gargalo ainda maior: O(m + p²) dentro de loop O(n)
            //Principal causa da lentidão do relatório
            PostsExtremos extremos = obterPrimeiroEUltimoPost(usuario.getId()); //O(p²) -> Visto anteriormente
            if (extremos != null) {
                relatorio.append("Primeiro post: ")
                         .append(extremos.getPrimeiro().getConteudo())
                         .append("\n"); 
                relatorio.append("Último post: ")
                         .append(extremos.getUltimo().getConteudo())
                         .append("\n");
            }
            relatorio.append("\n");
        }
        
        return relatorio.toString();
    }

    public static void main(String[] args) {
        SistemaPostsBlog sistema = new SistemaPostsBlog();
        
        System.out.println("Criando usuários e posts...");
        long inicio = System.currentTimeMillis();
        
        // Adiciona 1000 usuários
        //À medida que N cresce, terá gargalos
        for (int i = 1; i <= 1000; i++) {
            sistema.adicionarUsuario(new Usuario(i, "Usuario" + i));
        }
        
        // Cada usuário faz 50 posts
        //Cada adição de post busca linearmente -> O(N) pelo usuário na lista
        for (int i = 1; i <= 1000; i++) {
            for (int j = 1; j <= 50; j++) {
                sistema.adicionarPost(i, "Post " + j + " do usuário " + i);
                try {//Desnecessario para o sistema -> atrasa tempo de execução
                    Thread.sleep(1); //Simula passagem de tempo
                } catch (InterruptedException e) {}
            }
        }
        
        //DEBUG
        long fimCarga = System.currentTimeMillis();
        System.out.println("Tempo de carga: " + (fimCarga - inicio) + "ms");
        
        System.out.println("\nTestando operações críticas...");
        long inicioOp = System.currentTimeMillis();
        
        PostsExtremos extremos = sistema.obterPrimeiroEUltimoPost(500);
        int total = sistema.contarPostsDoUsuario(500);
        
        long fimOp = System.currentTimeMillis();
        System.out.println("Tempo de operações: " + (fimOp - inicioOp) + "ms");
        System.out.println("Total de posts do usuário 500: " + total);
        
        
        System.out.println("\nGerando relatório completo...");
        long inicioRel = System.currentTimeMillis();
        
        String relatorio = sistema.gerarRelatorioGeral();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio.txt"))) {
            writer.write(relatorio);
            System.out.println("Relatorio criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }       
        
        long fimRel = System.currentTimeMillis();
        System.out.println("Tempo de geração relatorio: " + (fimRel - inicioRel) + "ms");
        
    }
}
