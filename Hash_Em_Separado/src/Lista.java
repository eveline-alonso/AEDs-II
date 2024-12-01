import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

public class Lista<E> {

	private Celula<E> primeiro;
	private Celula<E> ultimo;
	private int tamanho;
	
	public Lista() {
		
		Celula<E> sentinela = new Celula<>();
		
		this.primeiro = this.ultimo = sentinela;
		this.tamanho = 0;
	}
	
	public boolean vazia() {
		
		return (this.primeiro == this.ultimo);
	}
	
	public void inserirInicio(E novo) {
	
		Celula<E> novaCelula = new Celula<>(novo);
		novaCelula.setProximo(this.primeiro.getProximo());
		this.primeiro.setProximo(novaCelula);
		
		if (vazia())
			this.ultimo = novaCelula;
		
		this.tamanho++;
	}
	
	public void inserirFinal(E novo) {
		
		Celula<E> novaCelula = new Celula<>(novo);
		this.ultimo.setProximo(novaCelula);
		this.ultimo = novaCelula;
		
		this.tamanho++;
	}
	
	public void inserir(E novo, int posicao) {
		
		Celula<E> anterior, novaCelula, proximaCelula;
		
		if ((posicao < 0) || (posicao > this.tamanho))
			throw new IndexOutOfBoundsException("Não foi possível inserir o item na lista: "
					+ "a posição informada é inválida!");
		
		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
				
		novaCelula = new Celula<>(novo);
			
		proximaCelula = anterior.getProximo();
			
		anterior.setProximo(novaCelula);
		novaCelula.setProximo(proximaCelula);
			
		if (posicao == this.tamanho)  // a inserção ocorreu na última posição da lista
			this.ultimo = novaCelula;
			
		this.tamanho++;		
	}
	
	public E removerInicio() {
		
		Celula<E> removida;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");
		
		removida = this.primeiro.getProximo();
		this.primeiro.setProximo(removida.getProximo());
		
		if (removida == this.ultimo)
			this.ultimo = this.primeiro;
		
		this.tamanho--;
		
		return removida.getItem();
	}
	
	public E removerFinal() {
		
		Celula<E> anterior, removida;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");
		
		anterior = this.primeiro;
		while (anterior.getProximo() != this.ultimo)
			anterior = anterior.getProximo();
		
		removida = this.ultimo;
		anterior.setProximo(null);
		this.ultimo = anterior;
		
		this.tamanho--;
		
		return (removida.getItem());
	}
	
	public E remover(int posicao) {
		
		Celula<E> anterior, celulaRemovida, proximaCelula;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");
		
		if ((posicao < 0) || (posicao >= this.tamanho ))
			throw new IndexOutOfBoundsException("Não foi possível remover o item da lista: "
					+ "a posição informada é inválida!");
			
		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
				
		celulaRemovida = anterior.getProximo();
				
		proximaCelula = celulaRemovida.getProximo();
				
		anterior.setProximo(proximaCelula);
		celulaRemovida.setProximo(null);
				
		if (celulaRemovida == this.ultimo)
			this.ultimo = anterior;
				
		this.tamanho--;
				
		return (celulaRemovida.getItem());	
	}
	
	public E remover(E removido) {
		
		Celula<E> aux, anterior;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");
		
		aux = this.primeiro.getProximo();
		anterior = this.primeiro;
		while (aux != null) {
			if (aux.getItem().equals(removido)) {
				anterior.setProximo(aux.getProximo());
				if (aux == this.ultimo)
					this.ultimo = anterior;
				this.tamanho--;
				return aux.getItem();
			}
			aux = aux.getProximo();
			anterior = anterior.getProximo();
		}
		
		throw new NoSuchElementException("Não foi possível remover o item da lista: "
				+ "o item não foi encontrado na lista!");
	}
	
	public E localizar(E procurado) {
		
		Celula<E> aux;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível localizar o item na lista: "
					+ "a lista está vazia!");
		
		aux = this.primeiro.getProximo();
		while (aux != null) {
			if (aux.getItem().equals(procurado))
				return aux.getItem();
			aux = aux.getProximo();
		}
		
		throw new NoSuchElementException("O item não foi encontrado na lista!");
	}
	
	public E localizar(int posicao) {
		
		Celula<E> aux;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível localizar o item na lista: "
					+ "a lista está vazia!");
		
		if ((posicao < 0) || (posicao >= this.tamanho ))
			throw new IndexOutOfBoundsException("Não foi possível localizar o item na lista: "
					+ "a posição informada é inválida!");
			
		aux = this.primeiro.getProximo();
		for (int i = 0; i < posicao; i++)
			aux = aux.getProximo();
	
		return aux.getItem();
	}
	
	public void imprimir() {
		
		Celula<E> aux;
		
		if (vazia())
			System.out.println("A lista está vazia!");
		else {
			aux = this.primeiro.getProximo();
			while (aux != null) {
				System.out.println(aux.getItem());
				aux = aux.getProximo();
			}
		}		
	}
	
	public void concatenar(Lista<E> outraLista) {
		
		if (! outraLista.vazia()) {
			this.ultimo.setProximo(outraLista.primeiro.getProximo());
			this.ultimo = outraLista.ultimo;
			this.tamanho += outraLista.tamanho;
		}
	}
	
	public Lista<E> copiar() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Lista<E> copia = new Lista<>();
		Celula<E> aux = this.primeiro.getProximo();
		E itemCopia;
		
		while (aux != null) {
			//itemCopia = (E) aux.getItem().getClass().getMethod("clone").invoke(aux.getItem());
			itemCopia = aux.getItem();
			copia.inserirFinal(itemCopia);
			aux = aux.getProximo();
		}
		
		return(copia);
	}
	
	// elimina os itens da lista encadeada que ocupam posição ímpar.
    public void eliminarItensPosicoesImpares() {
        
    	Celula<E> par;

        if (vazia())
        	throw new IllegalStateException("A lista está vazia!");
		
        par = this.primeiro.getProximo();

        while ((par != null) && (par.getProximo() != null)) {
        	par.setProximo(par.getProximo().getProximo());
        	this.tamanho--;
            if (par.getProximo() == null)
                    this.ultimo = par;
                par = par.getProximo();
        }
    }

    // elimina os itens da lista encadeada que ocupam posição par.
    public void eliminarItensPosicoesPares() {
    	
    	Celula<E> impar;

        if (vazia())
        	throw new IllegalStateException("A lista está vazia!");
		
        impar = this.primeiro;

        while ((impar != null) && (impar.getProximo() != null)) {
        	impar.setProximo(impar.getProximo().getProximo());
        	this.tamanho--;
            if (impar.getProximo() == null)
            	this.ultimo = impar;
            impar = impar.getProximo();
        }
    }

    
    public E obterItemMeio() {
 		
 		int meio = this.tamanho/2;
 		Celula<E> aux = this.primeiro.getProximo();
 		
 		if (vazia())
        	throw new IllegalStateException("A lista está vazia!");
		
 		for (int numItens = 0; numItens != meio; numItens++)
 			aux = aux.getProximo();
 		
 		return aux.getItem();
 	}
    
    private Celula<E> localizarCelula(E item) {
    	
    	Celula<E> aux;
    	
    	if (vazia())
        	throw new IllegalStateException("A lista está vazia!");
    	
    	aux = this.primeiro.getProximo();
    	while (aux != null) {
    		if (aux.getItem().equals(item))
    			return aux;
    		aux = aux.getProximo();
    	}
	
    	throw new NoSuchElementException("O item não foi encontrado na lista!");
    }
    
    // troca os itens, passados como parâmetros, de lugar na lista encadeada.
    public void trocar(E itemX, E itemY) {
    	
    	E temp;
    	Celula<E> celulaX = localizarCelula(itemX);
    	Celula<E> celulaY = localizarCelula(itemY);
    
    	temp = celulaX.getItem();
    	celulaX.setItem(celulaY.getItem());
    	celulaY.setItem(temp);
    }

	public int tamanho(){
		return tamanho;
	}
}
