import java.util.NoSuchElementException;

public class TabelaHash<K, V> implements IMapeamento<K, V>{

	private Entrada<K, V>[] tabelaHash; 
	
	private int capacidade; /// tamanho da tabela hash.
	                        /// deve ser um número primo grande para diminuirmos a probabilidade de colisões.

	/**
	 * Construtor da classe.
	 * Esse método é responsável por inicializar a tabela hash que trabalha com endereçamento em aberto e rehashing.
	 * Assim, esse método atribui, ao atributo "capacidade", dessa classe, o valor passado por meio do parâmetro "capacidade".
	 * Esse método também cria um vetor, de tamanho "capacidade", de entradas; e o atribui ao atributo "tabelaHash".
	 * Adicionalmente, cada posição do vetor é inicializada com null. 
	 * @param capacidade: tamanho da tabela hash. 
	 */
	@SuppressWarnings("unchecked")
	public TabelaHash(int capacidade) {
		
		this.capacidade = capacidade;
		tabelaHash = (Entrada<K, V>[]) new Entrada[this.capacidade]; 
		
		for (int i = 0; i < this.capacidade; i++)
			tabelaHash[i] = null;
	}
	
	/**
	 * Esse método implementa a função de transformação da tabela hash, 
	 * ou seja, calcula a posição, na tabela hash, em que o item,
	 * que possui a chave informada por meio do parâmetro "chave", deve ser encontrado.
	 * A função de transformação utilizada corresponde ao resto da divisão do hashCode de "chave" + "tentativas"
	 * pelo tamanho da tabela hash.
	 * @param chave: chave da qual desejamos saber a posição na tabela hash.
	 * @param tentativas: indica a quantidade de tentativas realizadas com o objetivo de se localizar o item na tabela hash.
	 * @return a posição que o item, cuja chave corresponde a que foi passada como parâmetro para esse método, deve ocupar na tabela hash.
	 */
	private int funcaoHash(K chave, int tentativas) {
		return (Math.abs(chave.hashCode() + tentativas)) % capacidade;
	}
	
	/**
	 * Método responsável por inserir um novo item na tabela hash.
	 * Não é permitido inserir, nessa tabela hash, mais de um item com uma mesma chave. 
	 * @param chave: chave do item que deve ser inserido na tabela hash.
	 * @param item: referência ao item que deve ser inserido na tabela hash.
	 * @return a posição na tabela hash em que o novo item foi inserido.
	 */
	@Override
	public int inserir(K chave, V item) {
		
		int tentativas = 0;
		int posicao = funcaoHash(chave, tentativas);
		boolean inseriu = false;
		
		while ((tentativas < capacidade) && !inseriu) {
			if ((tabelaHash[posicao] == null) || (tabelaHash[posicao].isRemovida())) {
				tabelaHash[posicao] = new Entrada<>(chave, item);
				inseriu = true;
			} else if ((tabelaHash[posicao].getChave().equals(chave)) && !(tabelaHash[posicao].isRemovida())) {
				throw new IllegalArgumentException("O item já havia sido inserido anteriormente na tabela hash!");
			} else {
				tentativas++;
			
				/// cálculo da posição da tabela hash em que o novo item deverá ser armazenado.
				posicao = funcaoHash(chave, tentativas);
			}
		}
		
		if (inseriu) {
			return posicao;
		} else {
			throw new IllegalStateException("A tabela hash está cheia: não foi possível inserir o novo elemento.");
		}
	}
	
	/**
	 * Método responsável por localizar, na tabela hash, o item
	 * cuja chave corresponde à que foi passada como parâmetro para esse método. 
	 * @param chave: chave do item que deve ser localizado na tabela hash.
	 * @return uma referência ao item encontrado.
	 * O método lança uma exceção caso o item não tenha sido localizado na tabela hash.
	 */
	@Override
	public V pesquisar(K chave) {
		
		int tentativas = 0;
		int posicao = funcaoHash(chave, tentativas);
		
		while (tentativas < capacidade) {
			if (tabelaHash[posicao] == null) {
				throw new NoSuchElementException("Item não encontrado!");
			} else if ((tabelaHash[posicao].getChave().equals(chave)) && !(tabelaHash[posicao].isRemovida())) {
					return tabelaHash[posicao].getValor();
			} else {
				tentativas++;
				
				/// cálculo da posição da tabela hash em que o item deve estar armazenado.
				posicao = funcaoHash(chave, tentativas);	
			}
		}
				
		throw new NoSuchElementException("Item não encontrado!");
	}
	
	/**
	 * Método responsável por remover, da tabela hash, o item
	 * cuja chave corresponde à que foi passada como parâmetro para esse método.
	 * @param chave: chave do item que deve ser removido da tabela hash.
	 * @return uma referência ao item removido.
	 * O método lança uma exceção caso o item não seja localizado na tabela hash.
	 */
	@Override
	public V remover(K chave) {
		
		int tentativas = 0;
		int posicao = funcaoHash(chave, tentativas);
		
		while (tentativas < capacidade) {
			
			if (tabelaHash[posicao] == null) {
				throw new NoSuchElementException("Item não encontrado!");
			} else if ((tabelaHash[posicao].getChave().equals(chave)) && !(tabelaHash[posicao].isRemovida())) {
					tabelaHash[posicao].setRemovida(true);
					return tabelaHash[posicao].getValor();
			} else {
				tentativas++;
				
				/// cálculo da posição da tabela hash em que o item deve estar armazenado.
				posicao = funcaoHash(chave, tentativas);
			}
		}
				
		throw new NoSuchElementException("Item não encontrado!");
	}
	
	//#region Herança Object
	@Override
	public String toString(){
		return percorrer();
	}
	//#endregion
		
	/**
	 * Método responsável por percorrer todo o conteúdo da tabela hash e retornar sua representação, em string.
	 * A string inclui o índice da tabela hash e seu correspondente conteúdo.
	 * Se a posição da tabela hash estiver vazia, é incluída uma mensagem explicativa.
	 * Caso contrário, para cada posição da tabela hash, são incluídos seus dados, sempre usando
	 * o polimorfismo do toString.
	 */
	 @Override
	public String percorrer(){
	
		String conteudo = "";
		for (int i = 0; i < capacidade; i++) {
			conteudo += ("Posição[" + i + "]: ");
			if ((tabelaHash[i] == null) || (tabelaHash[i].isRemovida())) {
				conteudo += "vazia\n";
			} else {
				conteudo += tabelaHash[i] + "\n";
			}
		}
		return conteudo;
	}

	@Override
	public int tamanho() {
		int tamanho = 0;
		for (int i = 0; i < capacidade; i++) {
			if ((tabelaHash[i] != null) && !(tabelaHash[i].isRemovida())) {
				tamanho++;
			}
		}
		return tamanho;
	}
}
