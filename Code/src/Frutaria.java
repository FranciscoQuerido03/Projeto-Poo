/**
 * Classe Frutaria que extends Mercearia, herdando todos os seus métodos e atributos
 */
public class Frutaria extends Mercearia {
    private int nrProd;
    private int medFatProdAno;

    /**
     * Construtor da classe, recebe dados para a inicialização
     * @param nome Nome da Empresa
     * @param distrito Distrito da Empresa
     * @param gps Objeto da classe Coordenadas
     * @param custoLimpAno Custo de Limpeza por Ano
     * @param nrProd Numero de Produtos
     * @param medFatProdAno Média de Faturação Anual por Produto
     */
    public Frutaria(String nome, String distrito, Coordenadas gps, int custoLimpAno, int nrProd, int medFatProdAno) {
        super(nome, distrito, gps, custoLimpAno);
        this.nrProd = nrProd;
        this.medFatProdAno = medFatProdAno;
    }

    @Override
    public String toString() {
        return "Frutaria:" + super.toString();
    }

    public int getNrProd() {
        return nrProd;
    }

    public void setNrProd(int nrProd) {
        this.nrProd = nrProd;
    }

    public int getMedFatProdAno() {
        return medFatProdAno;
    }

    public void setMedFatProdAno(int medFatProdAno) {
        this.medFatProdAno = medFatProdAno;
    }

    @Override
    protected float despesaAnual() {
        return super.despesaAnual();
    }

    /**
     * Método que calcula a receita anual de uma frutaria
     * @return receita anual
     */
    @Override
    protected float receitaAnual() {
        return getMedFatProdAno() * getNrProd();
    }

    @Override
    protected float lucro() {
        return super.lucro();
    }

    @Override
    protected String getCateg() {
        return super.getCateg();
    }
}
