import java.util.Comparator;

public interface IOrdenator<T> {

	public T[] ordenar();
    public void setComparador(Comparator<T> comparador);
    public long getComparacoes();
	public long getMovimentacoes();
    public double getTempoOrdenacao();
}
