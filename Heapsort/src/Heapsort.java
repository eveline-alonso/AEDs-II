import java.util.Comparator;

public class Heapsort<T extends Comparable<T>> implements IOrdenator<T> {

	private T[] dadosOrdenados;
	private Comparator<T> comparador;
	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;
	
	public Heapsort() {
		
		comparacoes = 0;
		movimentacoes = 0;
		setComparador(T::compareTo);
	}
	
	public Heapsort(Comparator<T> comparador) {
		
		comparacoes = 0;
		movimentacoes = 0;
		setComparador(comparador);
	}

	@Override
	public void setComparador(Comparator<T> comparador) {
		this.comparador = comparador;
	}
	
	@Override
	public T[] ordenar (T[] dados) {

		dadosOrdenados = dados;
		
		comparacoes = 0;
		movimentacoes = 0;
		
		// Criando outro vetor, com todos os elementos do vetor anterior reposicionados (uma posição a frente)
		// de forma a ignorar a posição zero	    
		@SuppressWarnings("unchecked")
		T[] tmp = (T[]) new Comparable[dadosOrdenados.length + 1];
		for(int i = 0; i < dadosOrdenados.length; i++) {
			tmp[i+1] = dadosOrdenados[i];
		}
			      	
		// Construção do heap
		for(int tamHeap = (tmp.length - 1)/2; tamHeap >= 1; tamHeap--) {
			restaura(tmp, tamHeap, tmp.length - 1);
		}
			    	
		iniciar();
			      	
		//Ordenação propriamente dita
		int tamHeap = tmp.length - 1;
		swap(tmp, 1, tamHeap--);
		while(tamHeap > 1) {
			restaura(tmp, 1, tamHeap);
			swap(tmp, 1, tamHeap--);
		}

		//Alterar o vetor para voltar à posição zero
		for(int i = 0; i < dadosOrdenados.length; i++) {
			dadosOrdenados[i] = tmp[i+1];
		}
		
		terminar();
		
		return  dadosOrdenados;
	}
	
	private void restaura(T[] tmp, int i, int tamHeap) {

	    int maior = i;
        int filho = getMaiorFilho(tmp, i, tamHeap);

	    if(comparador.compare(tmp[i], tmp[filho]) < 0) {
		    maior = filho;
	    }
	    if (maior != i) {
	    	swap(tmp, i, maior);
	        if (maior <= tamHeap/2) {
	        	restaura(tmp, maior, tamHeap);
	        }
	    }
	}

	private int getMaiorFilho(T[] tmp, int i, int tamHeap) {

		int filho;

		if (2*i == tamHeap || (comparador.compare(tmp[2*i], tmp[2*i+1]) > 0)) {
        	filho = 2*i;
      	} else {
        	filho = 2*i + 1;
      	}
      	return filho;
	}
	
	private void swap(T[] dados, int i, int j) {
	      
		movimentacoes++;
		
		T temp = dados[i];
	    dados[i] = dados[j];
	    dados[j] = temp;
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