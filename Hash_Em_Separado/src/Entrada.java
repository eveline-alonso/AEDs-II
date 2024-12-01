import java.util.Objects;

@SuppressWarnings("unchecked")
public class Entrada<K, V> {

	private final K chave;
	private V valor;
	
	public Entrada(K chave, V item) {
		this.chave = chave;
		this.valor = item;
	}

	public K getChave() {
		return chave;
	}
	
	public V getValor() {
		return valor;
	}
	
	public void setValor(V valor) {
		this.valor = valor;
	}
	
	
	@Override
	public boolean equals(Object outroObjeto) {
		boolean resposta;
		Entrada<K, V> outraEntrada;
		try{
			outraEntrada = (Entrada<K, V>) outroObjeto;
			resposta = (outraEntrada.getChave().equals(this.chave));
		}catch (ClassCastException ex){
			resposta = false;
		}
		return resposta;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.chave);
	}
	
	@Override
	public String toString() {
		return (this.chave + ": " + this.valor);
	}
}
