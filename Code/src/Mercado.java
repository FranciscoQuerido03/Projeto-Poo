/**
 * Classe Mercado que extends Mercearia, herdando todos os seus métodos e atributos
 */
public class Mercado extends Mercearia {
    private String tipo;
    private int areaCorredores;
    private int medFatMAno;

    /**
     * Construtor da classe, recebe dados para a inicialização
     * @param nome Nome da Empresa
     * @param distrito Distrito da Empresa
     * @param gps Objeto da classe Coordenadas
     * @param custoLimpAno Custo de Limpeza por Ano
     * @param tipo Tipo de Mercado (mini, super, hiper)
     * @param areaCorredores Área dos Corredores
     * @param medFatMAno Média da Faturação Anual por m²
     */
    public Mercado(String nome, String distrito, Coordenadas gps, int custoLimpAno, String tipo, int areaCorredores, int medFatMAno) {
        super(nome, distrito, gps, custoLimpAno);
        this.tipo = tipo;
        this.areaCorredores = areaCorredores;
        this.medFatMAno = medFatMAno;
    }

    @Override
    public String toString() {
        return "Mercado:" + super.toString();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAreaCorredores() {
        return areaCorredores;
    }

    public void setAreaCorredores(int areaCorredores) {
        this.areaCorredores = areaCorredores;
    }

    public int getMedFatMAno() {
        return medFatMAno;
    }

    public void setMedFatMAno(int medFatMAno) {
        this.medFatMAno = medFatMAno;
    }


    @Override
    protected float despesaAnual() {
        return super.despesaAnual();
    }

    /**
     * Método que calcula a receita anual de um mercado
     * @return receita anual
     */
    @Override
    protected float receitaAnual() {
        return getAreaCorredores()*getMedFatMAno();
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