import java.util.Objects;

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
		
		Entrada<?, ?> outraEntrada;
		
		if (this == outroObjeto) return true;
		else if (outroObjeto == null || ! (outroObjeto.getClass() == this.getClass())) return false;
		else {
			outraEntrada = (Entrada<?, ?>) outroObjeto;
			return (outraEntrada.getChave().equals(this.chave));
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(chave);
	}
	
	@Override
	public String toString() {
		return (chave + "-->" + valor);
	}
}
