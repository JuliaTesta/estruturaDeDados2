
public class No {
	
	public int dado;
	public No pai;
	public No direito;
	public No esquerdo;
	public Cor cor;
	
	public No() {
		dado = 0;
		esquerdo = null;
		direito = null;
		pai = null;
		cor = Cor.PRETO;
	}
	
	public No (int dado) {
		this.dado = dado;
		esquerdo = null;
		direito = null;
		pai = null;
		cor = Cor.VERMELHO;
	}
	
} 
