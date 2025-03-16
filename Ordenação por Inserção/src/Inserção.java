import java.util.Comparator;

public class Inserção<T extends Comparable<T>> implements IOrdenator<T> {

	private T[] dados;
	private Comparator<T> comparador;
	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;
	
	public Inserção(T[] dados) {
		
		comparacoes = 0;
		movimentacoes = 0;
		inicio = System.nanoTime();
		this.dados = dados;
	}
	
	@Override
	public void setComparador(Comparator<T> comparador) {
		this.comparador = comparador;
	}
	
	@Override
	public T[] ordenar() {
	
		comparacoes = 0;
		movimentacoes = 0;
		iniciar();
		
		for (int i = 1; i < dados.length; i++) {
			T item = dados[i];
			int j = i - 1;

			while ((j >= 0) && (comparador.compare(dados[j], item) > 0)) {
				comparacoes++;
				
				movimentacoes++;
				dados[j + 1] = dados[j];
				j--;
			}
			movimentacoes++;
			dados[j + 1] = item;
		}
		
		terminar();
		
		return dados;
	}
	
	@Override
	public long getComparacoes() {
		return comparacoes;
	}
	
	@Override
	public long getMovimentacoes() {
		return movimentacoes;
	}
	
	private void iniciar() {
		inicio = System.nanoTime();
	}
	
	private void terminar() {
		termino = System.nanoTime();
	}
	
	@Override
	public double getTempoOrdenacao() {
		
		double tempoTotal;
		
	    tempoTotal = (termino - inicio) / 1_000_000;
	    return tempoTotal;
	}
}