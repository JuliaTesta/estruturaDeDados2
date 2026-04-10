public class ItemCache {
    String sql;
    String resultado;
    long timestampCriacao;

    public ItemCache(String sql, String resultado){
        this.sql = sql;
        this.resultado = resultado;
        this.timestampCriacao = System.currentTimeMillis();
    }

    @Override
    public String toString(){
        return "SQL: " + sql + 
               " | Resultado: " + resultado + 
               " | Timestamp: " + timestampCriacao;
    }
}

