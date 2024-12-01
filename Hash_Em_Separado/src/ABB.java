import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ABB<E extends Comparable<E>> {

	private No<E> raiz; // referência à raiz da árvore.

    /// Construtor da classe.
    /// Esse construtor cria uma nova árvore binária de busca vazia. Para isso, esse método atribui null à raiz da árvore.
    public ABB() {
        raiz = null;
    }

    /// Método booleano que indica se a árvore está vazia ou não.
    /// Retorna: 
    /// verdadeiro: se a raiz da árvore for null, o que significa que a árvore está vazia.
    /// falso: se a raiz da árvore não for null, o que significa que a árvore não está vazia.
    public Boolean vazia() {
        return (this.raiz == null);
    }

    private No<E> pesquisar(No<E> raizArvore, E procurado) {
    	
    	if (raizArvore == null)
    		/// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então o item não foi encontrado.
    		throw new NoSuchElementException("O item não foi localizado na árvore!");
    	else if (raizArvore.getItem().equals(procurado))
    		/// O item procurado foi encontrado.
    		return raizArvore;
    	else if (raizArvore.getItem().compareTo(procurado) > 0)
    		/// Se o item armazenado na raiz da árvore for maior do que o item procurado:
            /// pesquise esse item na sub-árvore esquerda.    
    		return pesquisar(raizArvore.getEsquerda(), procurado);
    	else
    		/// Se o item armazenado na raiz da árvore for menor do que o item que procurado:
            /// pesquise esse item na sub-árvore direita.
    		return pesquisar(raizArvore.getDireita(), procurado);
    }
    
    public E pesquisar(E procurado) {
    	return pesquisar(this.raiz, procurado).getItem();
    }
    
    /// Método recursivo responsável por adicionar um item à árvore.
    /// Parâmetro "raizArvore": raiz da árvore ou sub-árvore em que o item será adicionado.
    /// Parâmetro "item": item que deverá ser adicionado à árvore.
    /// Retorna a raiz atualizada da árvore ou sub-árvore em que o item foi adicionado.
    private No<E> adicionar(No<E> raizArvore, E item) {
        /// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então um novo item é inserido.
        if (raizArvore == null)
            raizArvore = new No<>(item);
        else if (raizArvore.getItem().compareTo(item) > 0)
        	/// Se o item armazenado na raiz da árvore for maior do que o item que deverá ser inserido na árvore:
            /// adicione esse novo item à sub-árvore esquerda; e atualize a referência para a sub-árvore esquerda modificada. 
            raizArvore.setEsquerda(adicionar(raizArvore.getEsquerda(), item));
        else if (raizArvore.getItem().compareTo(item) < 0)
            /// Se o item armazenado na raiz da árvore for menor do que o item que deverá ser inserido na árvore:
            /// adicione esse novo item à sub-árvore direita; e atualize a referência para a sub-árvore direita modificada.
            raizArvore.setDireita(adicionar(raizArvore.getDireita(), item));
        else
            /// O item armazenado na raiz da árvore é igual ao novo item que deveria ser inserido na árvore.
            throw new RuntimeException("O item já foi inserido anteriormente na árvore.");
           
        /// Retorna a raiz atualizada da árvore ou sub-árvore em que o item foi adicionado.
        return raizArvore;
    }

    /// Método que encapsula a adição recursiva de itens à árvore.
    /// Parâmetro "item": item que deverá ser inserido na árvore.
    public void adicionar(E item) {
        /// Chama o método recursivo "adicionar", que será responsável por adicionar, o item passado como parâmetro, à árvore.
        /// O método "adicionar" recursivo receberá, como primeiro parâmetro, a raiz atual da árvore; e, como segundo parâmetro, 
    	/// o item que deverá ser adicionado à árvore.
        /// Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo método "adicionar" recursivo.
        this.raiz = adicionar(this.raiz, item);
    }
    
    public void caminhamentoEmOrdem() {
    	
    	if (vazia())
    		throw new IllegalStateException("A árvore está vazia!");
    	
    	caminhamentoEmOrdem(this.raiz);
    }
    
    private void caminhamentoEmOrdem(No<E> raizArvore) {
    	if (raizArvore != null) {
    		caminhamentoEmOrdem(raizArvore.getEsquerda());
    		System.out.println(raizArvore.getItem());
    		caminhamentoEmOrdem(raizArvore.getDireita());
    	}
    }
    
    public void caminhamentoPreOrdem() {
    	
    	if (vazia())
    		throw new IllegalStateException("A árvore está vazia!");
    	
    	caminhamentoPreOrdem(this.raiz);
    }
    
    private void caminhamentoPreOrdem(No<E> raizArvore) {
    	if (raizArvore != null) {
    		System.out.println(raizArvore.getItem());
    		caminhamentoPreOrdem(raizArvore.getEsquerda());
    		caminhamentoPreOrdem(raizArvore.getDireita());
    	}
    }
    
    public void caminhamentoPosOrdem() {
    	
    	if (vazia())
    		throw new IllegalStateException("A árvore está vazia!");
    	
    	caminhamentoPosOrdem(this.raiz);
    }
    
    private void caminhamentoPosOrdem(No<E> raizArvore) {
    	if (raizArvore != null) {
    		caminhamentoPosOrdem(raizArvore.getEsquerda());
    		caminhamentoPosOrdem(raizArvore.getDireita());
    		System.out.println(raizArvore.getItem());
    	}
    }
    
    public void caminhamentoDecrescente() {
    	
    	if (vazia())
    		throw new IllegalStateException("A árvore está vazia!");
    	
    	caminhamentoDecrescente(this.raiz);
    }
    
    private void caminhamentoDecrescente(No<E> raizArvore) {
    	if (raizArvore != null) {
    		caminhamentoDecrescente(raizArvore.getDireita());
    		System.out.println(raizArvore.getItem());
    		caminhamentoDecrescente(raizArvore.getEsquerda());
    	}
    }
    
    /// Método recursivo responsável por localizar na árvore ou sub-árvore o antecessor do nó que deverá ser retirado. 
    /// O antecessor do nó que deverá ser retirado da árvore corresponde
    /// ao nó que armazena o item que é o maior, 
    /// dentre os itens menores do que o item que deverá ser retirado.
    /// Depois de ser localizado na árvore ou sub-árvore, 
    /// o antecessor do nó que deverá ser retirado da árvore o substitui.
    /// Adicionalmente, a árvore ou sub-árvore é atualizada com a remoção do antecessor.
    /// Parâmetro "itemRetirar": referência ao nó que armazena o item que deverá ser retirado da árvore.
    /// Parâmetro "raizArvore": raiz da árvore ou sub-árvore em que o antecessor do nó que deverá ser retirado deverá ser localizado.
    /// Retorna: raiz atualizada da árvore ou sub-árvore após a remoção do antecessor do nó que foi retirado da árvore.
    private No<E> antecessor(No<E> itemRetirar, No<E> raizArvore) {
        /// Se o antecessor do nó que deverá ser retirado da árvore ainda não foi encontrado...
        if (raizArvore.getDireita() != null)
            /// Pesquise o antecessor na sub-árvore direita.
            raizArvore.setDireita(antecessor(itemRetirar, raizArvore.getDireita()));
        else {
        	/// O antecessor do nó que deverá ser retirado da árvore foi encontrado e deverá substitui-lo.
            itemRetirar.setItem(raizArvore.getItem());
            /// A raiz da árvore ou sub-árvore é atualizada com os descendentes à esquerda do antecessor.
            /// Ou seja, retira-se o antecessor da árvore.
            raizArvore = raizArvore.getEsquerda();
        }
        return raizArvore;
    }

    /// Método recursivo responsável por localizar um item na árvore e retirá-lo da árvore.
    /// Parâmetro "raizArvore": raiz da árvore ou sub-árvore da qual o item será retirado.
    /// Parâmetro "itemRemover": item que deverá ser localizado e removido da árvore.
    /// Retorna a raiz atualizada da árvore ou sub-árvore da qual o item foi retirado.
    private No<E> remover(No<E> raizArvore, E itemRemover) {
        /// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e o item, que deveria ser retirado dessa árvore, não foi encontrado.
        /// Nesse caso, deve-se lançar uma exceção.
        if (raizArvore == null) 
        	throw new NoSuchElementException("O item a ser removido não foi localizado na árvore!");
        else if (raizArvore.getItem().compareTo(itemRemover) == 0) {
            /// O item armazenado na raiz da árvore corresponde ao item que deve ser retirado dessa árvore.
            /// Ou seja, o item que deve ser retirado da árvore foi encontrado.
        	if (raizArvore.getDireita() == null)
        		/// O nó da árvore que será retirado não possui descendentes à direita.
                /// Nesse caso, os descendentes à esquerda do nó que está sendo retirado da árvore passarão a ser descendentes do nó-pai do nó que está sendo retirado.
                raizArvore = raizArvore.getEsquerda();
            else if (raizArvore.getEsquerda() == null)
                /// O nó da árvore que será retirado não possui descendentes à esquerda.
                /// Nesse caso, os descendentes à direita do nó que está sendo retirado da árvore passarão a ser descendentes do nó-pai do nó que está sendo retirado.
                raizArvore = raizArvore.getDireita();
            else
            	/// O nó que está sendo retirado da árvore possui descendentes à esquerda e à direita.
                /// Nesse caso, o antecessor do nó que está sendo retirado é localizado na sub-árvore esquerda desse nó. 
                /// O antecessor do nó que está sendo retirado da árvore corresponde
                /// ao nó que armazena o item que é o maior, 
                /// dentre os itens menores do que o item do nó que está sendo retirado.
                /// Depois de ser localizado na sub-árvore esquerda do nó que está sendo retirado, 
                /// o antecessor desse nó o substitui.
                /// A sub-árvore esquerda do nó que foi retirado é atualizada com a remoção do antecessor.
                raizArvore.setEsquerda(antecessor(raizArvore, raizArvore.getEsquerda()));
        } else if (raizArvore.getItem().compareTo(itemRemover) > 0)
        	/// Se o item armazenado na raiz da árvore for maior do que o item que deverá ser localizado e retirado da árvore:
            /// pesquise e retire esse item da sub-árvore esquerda.
            raizArvore.setEsquerda(remover(raizArvore.getEsquerda(), itemRemover));
        else
        	/// Se o item armazenado na raiz da árvore for menor do que o item que deverá ser localizado e retirado da árvore:
            /// pesquise e retire esse item da sub-árvore direita.
            raizArvore.setDireita(remover(raizArvore.getDireita(), itemRemover));
         
        /// Retorna a raiz atualizada da árvore ou sub-árvore da qual o item foi retirado.
        return raizArvore;
    }

    /// Método que encapsula a remoção recursiva de um item da árvore.
    /// Parâmetro "itemRemover": item que deverá ser localizado e removido da árvore.
    public void remover(E itemRemover) {
        /// Chama o método recursivo "remover", que será responsável por pesquisar o item passado como parâmetro na árvore e retirá-lo da árvore.
        /// O método "remover" recursivo receberá, como primeiro parâmetro, a raiz atual da árvore; 
    	/// e, como segundo parâmetro, o item que deverá ser localizado e retirado dessa árvore.
    	/// Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo método "remover" recursivo.
        this.raiz = remover(this.raiz, itemRemover);
    }
    
    public E obterMenor() {
    	
    	if (vazia())
    		throw new IllegalStateException("A árvore está vazia!");
    	
    	return obterMenor(this.raiz);
    }
    
    private E obterMenor(No<E> raizArvore) {
    	if (raizArvore.getEsquerda() != null)
    		return obterMenor(raizArvore.getEsquerda());
    	else
    		return raizArvore.getItem();
    }
    
    public E obterMaior() {
    	
    	if (vazia())
    		throw new IllegalStateException("A árvore está vazia!");
    	
    	return obterMaior(this.raiz);
    }
    
    private E obterMaior(No<E> raizArvore) {
    	if (raizArvore.getDireita() != null)
    		return obterMaior(raizArvore.getDireita());
    	else
    		return raizArvore.getItem();
    }
    
    public int numFolhas() {
    	
    	return numFolhas(this.raiz);
    }
    
    private int numFolhas(No<E> raizArvore) {
    	
    	if (raizArvore == null)
    		return 0;
    	else if (raizArvore.ehFolha())
    		return 1;
    	else
    		return (numFolhas(raizArvore.getEsquerda()) + numFolhas(raizArvore.getDireita()));
    }
    
    public int tamanho() {
    	
    	return numNos(this.raiz);
    }
    
    private int numNos(No<E> raizArvore) {
    	
    	int esquerda = 0, direita = 0;
    	
    	if (raizArvore == null)
    		return 0;
    	else if (raizArvore.getEsquerda() == null && raizArvore.getDireita() == null)
    		return 1;
    	else {
    		if (raizArvore.getEsquerda() != null)
    			esquerda = numNos(raizArvore.getEsquerda());
    		if (raizArvore.getDireita() != null)
    			direita = numNos(raizArvore.getDireita());
    		return (1 + esquerda + direita);
    	}
    }
    
    public int obterAltura() {
    	
    	return obterAltura(this.raiz);
    }
    
    private int obterAltura(No<E> raizArvore) {
    
    	int alturaEsquerda, alturaDireita;
    	
    	if (raizArvore == null)
    		return -1;
    	else {
    		alturaEsquerda = obterAltura(raizArvore.getEsquerda());
    		alturaDireita = obterAltura(raizArvore.getDireita());
    		return Math.max(alturaEsquerda, alturaDireita) + 1;
    	}
    }
    
    public E obterAntecessor(E item) {
    	
    	No<E> procurado, antecessor;
    	ArrayList<No<E>> procuradoEAntecessor;
    	
    	procuradoEAntecessor = pesquisarNoEAntecessor(this.raiz, null, item);
    	procurado = procuradoEAntecessor.get(0);
    	antecessor = procuradoEAntecessor.get(1);
    	
    	if (procurado.getEsquerda() == null && antecessor == null)
    		throw new NoSuchElementException("O item não possui antecessor.");
    	else if (procurado.getEsquerda() == null)
        	return antecessor.getItem();
    	
    	return antecessor(procurado.getEsquerda());
    }
    
    private ArrayList<No<E>> pesquisarNoEAntecessor(No<E> raizArvore, No<E> antecessor, E procurado) {
    	
    	ArrayList<No<E>> procuradoEAntecessor;
    	
    	if (raizArvore == null)
    		/// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então o item não foi encontrado.
    		throw new NoSuchElementException("O item não foi localizado na árvore!");
    	else if (raizArvore.getItem().equals(procurado)) {
    		/// O item procurado foi encontrado.
    		procuradoEAntecessor = new ArrayList<>();
    		procuradoEAntecessor.add(0, raizArvore);
    		procuradoEAntecessor.add(1, antecessor);
    		return procuradoEAntecessor;
    	} else if (raizArvore.getItem().compareTo(procurado) > 0)
    		return pesquisarNoEAntecessor(raizArvore.getEsquerda(), antecessor, procurado);
    	else
    		return pesquisarNoEAntecessor(raizArvore.getDireita(), raizArvore, procurado);
    }
      
    private E antecessor(No<E> raizArvore) {
    	
    	if (raizArvore.getDireita() == null)
    		return raizArvore.getItem();
    	else
    		return antecessor(raizArvore.getDireita());
    }
    
    private ArrayList<No<E>> pesquisarNoESucessor(No<E> raizArvore, No<E> sucessor, E procurado) {
    	
    	ArrayList<No<E>> procuradoESucessor;
    	
    	if (raizArvore == null)
    		/// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então o item não foi encontrado.
    		throw new NoSuchElementException("O item não foi localizado na árvore!");
    	else if (raizArvore.getItem().equals(procurado)) {
    		/// O item procurado foi encontrado.
    		procuradoESucessor = new ArrayList<>();
    		procuradoESucessor.add(0, raizArvore);
    		procuradoESucessor.add(1, sucessor);
    		return procuradoESucessor;
    	} else if (raizArvore.getItem().compareTo(procurado) > 0)
    		return pesquisarNoESucessor(raizArvore.getEsquerda(), raizArvore, procurado);
    	else
    		return pesquisarNoESucessor(raizArvore.getDireita(), sucessor, procurado);
    }
    
    private E sucessor(No<E> raizArvore) {
    	
    	if (raizArvore.getEsquerda() == null)
    		return raizArvore.getItem();
    	else
    		return sucessor(raizArvore.getEsquerda());
    }

    public E obterSucessor(E item) {
    	
    	No<E> procurado, sucessor;
    	ArrayList<No<E>> procuradoESucessor;
    	
    	procuradoESucessor = pesquisarNoESucessor(this.raiz, null, item);
    	procurado = procuradoESucessor.get(0);
    	sucessor = procuradoESucessor.get(1);
    	
    	if (procurado.getDireita() == null && sucessor == null)
    		throw new NoSuchElementException("O item não possui sucessor.");
    	else if (procurado.getDireita() == null)
    		return sucessor.getItem();
    	
    	return sucessor(procurado.getDireita());
    }
    
    public ABB<E> clone() {
    	
    	ABB<E> copia = new ABB<>();
    	
    	copia.raiz = clone(this.raiz);
    	return copia;
    }
    
    private No<E> clone(No<E> raizArvore) {
    	
    	No<E> copia;
    	
    	if (raizArvore != null) {
    		copia = raizArvore.clone();
    		copia.setEsquerda(clone(raizArvore.getEsquerda()));
    		copia.setDireita(clone(raizArvore.getDireita()));
    		return copia;
    	}
    	
    	return null;
    }
    
    public ABB<E> obterSubconjuntoMaiores(E item) {
    	
    	ABB<E> maiores = new ABB<>();
    	maiores.raiz = obterSubconjuntoMaiores(this.raiz, item);
    	return maiores;
    }
    
    private No<E> obterSubconjuntoMaiores(No<E> raizArvore, E item) {
    	
    	No<E> cabeca;
    	
    	if (raizArvore == null)
    		throw new NoSuchElementException("O item não foi encontrado.");
    	else if (raizArvore.getItem().equals(item)) {
    		cabeca = raizArvore.clone();
    		cabeca.setDireita(clone(raizArvore.getDireita()));
    		cabeca.setEsquerda(null);
    		return cabeca;
    	} else if (raizArvore.getItem().compareTo(item) > 0) {
    		cabeca = raizArvore.clone();
    		cabeca.setEsquerda(obterSubconjuntoMaiores(raizArvore.getEsquerda(), item));
    		cabeca.setDireita(clone(raizArvore.getDireita()));
    		return cabeca;
    	} else
    		return obterSubconjuntoMaiores(raizArvore.getDireita(), item);
    }
    
    public ABB<E> obterSubconjuntoMenores(E item) {
    	
    	ABB<E> menores = new ABB<>();
    	menores.raiz = obterSubconjuntoMenores(this.raiz, item);
    	return menores;
    }
    
    private No<E> obterSubconjuntoMenores(No<E> raizArvore, E item) {
    	
    	No<E> cabeca;
    	
    	if (raizArvore == null)
    		throw new NoSuchElementException("O item não foi encontrado.");
    	else if (raizArvore.getItem().equals(item)) {
    		cabeca = raizArvore.clone();
    		cabeca.setEsquerda(clone(raizArvore.getEsquerda()));
    		cabeca.setDireita(null);
    		return cabeca;
    	} else if (raizArvore.getItem().compareTo(item) < 0) {
    		cabeca = raizArvore.clone();
    		cabeca.setEsquerda(clone(raizArvore.getEsquerda()));
    		cabeca.setDireita(obterSubconjuntoMenores(raizArvore.getDireita(), item));
    		return cabeca;
    	} else
    		return obterSubconjuntoMenores(raizArvore.getEsquerda(), item);
    }
    
    public int obterGrau() {
    	return obterGrau(this.raiz);
    }
    
    private int obterGrau(No<E> raizArvore) {
    	
    	int grau, grauEsquerda, grauDireita;
    	
    	if (raizArvore != null) {
    		grau = raizArvore.obterGrau();
    		grauEsquerda = obterGrau(raizArvore.getEsquerda());
    		grauDireita = obterGrau(raizArvore.getDireita());
    		
    		return Math.max(Math.max(grauEsquerda, grauDireita), grau);
    	}
    	return 0;
    }
    
    public boolean ehRaiz(E item) {
    	
    	if (vazia())
    		throw new IllegalStateException("A árvore está vazia!");
    	
    	return (this.raiz.getItem().equals(item));
    }
    
    private E pesquisarPai(No<E> raizArvore, E pai, E procurado) {
    	
    	if (raizArvore == null)
    		/// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então o item não foi encontrado.
    		throw new NoSuchElementException("O item não foi localizado na árvore!");
    	else if (raizArvore.getItem().equals(procurado))
    		/// O item procurado foi encontrado.
    		return pai;
    	else if (raizArvore.getItem().compareTo(procurado) > 0)
    		return pesquisarPai(raizArvore.getEsquerda(), raizArvore.getItem(), procurado);
    	else
    		return pesquisarPai(raizArvore.getDireita(), raizArvore.getItem(), procurado);
    }

    public E obterPai(E item) {
    	
    	if (ehRaiz(item))
    		throw new NoSuchElementException("O item não possui pai.");

    	return pesquisarPai(this.raiz, null, item);
    }
    
    private int obterNivel(No<E> raizArvore, E item) {
    	
    	if (raizArvore == null)
    		/// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então o item não foi encontrado.
    		throw new NoSuchElementException("O item não foi localizado na árvore!");
    	else if (raizArvore.getItem().equals(item))
    		/// O item procurado foi encontrado.
    		return 0;
    	else if (raizArvore.getItem().compareTo(item) > 0)
    		return 1 + obterNivel(raizArvore.getEsquerda(), item);
    	else
    		return 1 + obterNivel(raizArvore.getDireita(), item);
    }
    
    public int obterNivel(E item) {
    
    	return obterNivel(this.raiz, item);
    }
    
    public Lista<E> recortar(E deOnde, E ateOnde) {
    	
    	Lista<E> subconjunto = recortar(this.raiz, deOnde, ateOnde);
    	
    	if (subconjunto.vazia())
    		throw new NoSuchElementException("Nao ha itens na arvore entre os indicados.");
    		
    	return subconjunto;
    }
    
    private Lista<E> recortar(No<E> raizArvore, E deOnde, E ateOnde) {
    	
    	Lista<E> subconjunto = new Lista<>();
    	
    	if (raizArvore != null) {
    		if ((deOnde.compareTo(raizArvore.getItem()) < 0) && (ateOnde.compareTo(raizArvore.getItem()) > 0)) {
    			subconjunto.concatenar(recortar(raizArvore.getEsquerda(), deOnde, ateOnde));
    			subconjunto.inserirFinal(raizArvore.getItem());
    			subconjunto.concatenar(recortar(raizArvore.getDireita(), deOnde, ateOnde));
    		} else if (deOnde.compareTo(raizArvore.getItem()) < 0)
    			subconjunto.concatenar(recortar(raizArvore.getEsquerda(), deOnde, ateOnde));
    		else
    			subconjunto.concatenar(recortar(raizArvore.getDireita(), deOnde, ateOnde));
    	}
    	return subconjunto;
    }
    
    public void caminhamentoPorNivel() {
    	
    	Lista<No<E>> itens = new Lista<>();
    	
    	if (vazia())
    		throw new IllegalStateException("Não foi possível imprimir os itens da árvore por nível: a árvore "
    				+ "está vazia!");
    	
    	itens.inserirFinal(this.raiz);
    	
    	caminhamentoPorNivel(0, itens);
    }
    
    private void caminhamentoPorNivel(int nivel, Lista<No<E>> itens) {
    	
    	Lista<No<E>> proximoNivel = new Lista<>();
    	No<E> raizArvore;
    	
    	if (! itens.vazia()) {
    		System.out.println("Nível " + nivel);
    		while (! itens.vazia()) {
    			raizArvore = itens.removerInicio();
    			System.out.println(raizArvore.getItem().toString());
    			if (raizArvore.getEsquerda() != null)
    				proximoNivel.inserirFinal(raizArvore.getEsquerda());
    			if (raizArvore.getDireita() != null)
    				proximoNivel.inserirFinal(raizArvore.getDireita());
    		}
    		caminhamentoPorNivel(nivel + 1, proximoNivel);
    	}	
    }
    
    public int tamanho(E item) {
    	
    	return tamanho(this.raiz, item);
    }
    
    private int tamanho(No<E> raizArvore, E item) {
    	
    	if (raizArvore == null)
    		throw new NoSuchElementException("O item não foi localizado na árvore!");
    	else if (raizArvore.getItem().equals(item)) {
    		return numNos(raizArvore);
    	} else if (raizArvore.getItem().compareTo(item) > 0)
    		return tamanho(raizArvore.getEsquerda(), item);
    	else
    		return tamanho(raizArvore.getDireita(), item);
    }

}
