/**
 * Class Mercearia, abstrata extends Empresas, herdando todos os seus métodos e atributos
 */
abstract public class Mercearia extends Empresas {
    private int custoLimpAno;

    /**
     * Construtor da classe, recebe dados para a inicialização
     * @param nome Nome da Empresa
     * @param distrito Distrito da Empresa
     * @param gps Objeto da classe Coordenadas
     * @param custoLimpAno Custo de Limpeza por Ano
     */
    public Mercearia(String nome, String distrito, Coordenadas gps, int custoLimpAno) {
        super(nome, distrito, gps);
        this.custoLimpAno = custoLimpAno;
    }

    public int getCustoLimpAno() {
        return custoLimpAno;
    }

    public void setCustoLimpAno(int custoLimpAno) {
        this.custoLimpAno = custoLimpAno;
    }

    /**
     * Método que calcula a receita anual de uma empresa da categoria mercearia
     * @return receita anual
     */
    @Override
    abstract protected float receitaAnual();

    @Override
    protected float despesaAnual() {
        return getCustoLimpAno();
    }
    protected float lucro() {
        return super.lucro();
    }

    /**
     * Método que identifica a categoria da empresa
     * @return categoria da empresa
     */
    @Override
    protected String getCateg() {
        return "Mercearia";
    }
}