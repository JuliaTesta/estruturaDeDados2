import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

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
    
    //Substituição de listas por Map. Evita buscas lineares -> O(n) se torna O(1).
    private Map<Integer, Usuario> usuarios;
    
    //Substituição de lista única de posts. Agora, cada usuário tem a sua propria lista.
    private Map<Integer, List<Post>> postsPorUsuario;
  
    //Mapas para armazenar primeiro e ultimo post de cada usuario
    private Map<Integer, Post> primeiroPostPorUsuario;
    private Map<Integer, Post> ultimoPostPorUsuario;
    
    public SistemaPostsBlog() {
        //Inicialização do Map
        this.usuarios = new HashMap<>();
        this.postsPorUsuario = new HashMap<>();
        //Para evitar loops desnecessarios
        this.primeiroPostPorUsuario = new HashMap<>();
        this.ultimoPostPorUsuario = new HashMap<>();
    }
    
    public void adicionarUsuario(Usuario usuario) {
        //Evita loop linear para verificar duplicidade
       usuarios.putIfAbsent(usuario.getId(), usuario);
    }
    
    
    public void adicionarPost(int usuarioId, String conteudo) {
        //Busca do usuario em O(1)
        Usuario usuario = usuarios.get(usuarioId);
        
        if (usuario != null) { 
            Post novoPost = new Post(usuarioId, conteudo, System.currentTimeMillis());
            //Adiciona post diretamente na lista do usuario em questao
            //Se determinado usuario nao tiver lista, irá cria-lá e adicionará o post nela. Se já existir mantém e adiciona o post.
            postsPorUsuario.computeIfAbsent(usuarioId, k -> new ArrayList<>()).add(novoPost);
            //Linha com sentido real agora
            usuario.incrementarContadorPosts();
            
            //Atualiza primeiro post
            primeiroPostPorUsuario.merge(usuarioId, novoPost,
                    (atual, novo) -> novo.getTimestamp() < atual.getTimestamp() ? novo : atual);
            
            // Atualiza último post
            ultimoPostPorUsuario.merge(usuarioId, novoPost,
                 (atual, novo) -> novo.getTimestamp() > atual.getTimestamp() ? novo : atual);
        }
    }
    
    public PostsExtremos obterPrimeiroEUltimoPost(int usuarioId) {
        //Busca direta da lista de posts do usuário
        Post primeiro = primeiroPostPorUsuario.get(usuarioId);
        Post ultimo = ultimoPostPorUsuario.get(usuarioId);
        
       if(primeiro == null || ultimo == null){
           return null;
       }
        return new PostsExtremos(primeiro, ultimo);
    }
    public int contarPostsDoUsuario(int usuarioId) {
        //Retorna contador ja armazenado no objeto usuario
        Usuario usuario = usuarios.get(usuarioId);
        return usuario != null ? usuario.getContadorPosts() : 0;
    }
    
    public String gerarRelatorioGeral() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO DO BLOG ===\n\n");
        
       //Atualiza sobre Map ao invés de lista
       for(Usuario usuario : usuarios.values()){
           relatorio.append("Usuario: ").append(usuario.getNome()).append("\n");
           relatorio.append("Total de posts: ").append(usuario.getContadorPosts()).append("\n");
           
           PostsExtremos extremos = obterPrimeiroEUltimoPost(usuario.getId());
            if(extremos != null){
               relatorio.append("Primeiro Post: ").append(extremos.getPrimeiro().getConteudo()).append("\n");
               relatorio.append("Ultimo Post: ").append(extremos.getUltimo().getConteudo()).append("\n");
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
        //Criacao eficiente de usuarios e posts
        for (int i = 1; i <= 1000; i++) {
            //Adiciona usuario em O(1)
            Usuario usuario = new Usuario(i, "Usuario" + i);
            sistema.adicionarUsuario(usuario);
            
            // Cada usuário cria 50 posts rapidamente em O(1) cada.
            for (int j = 1; j <= 50; j++) {
                sistema.adicionarPost(i, "Post " + j + " do usuário " + i);
            
    }
  }
        
        
        
        //debug
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
