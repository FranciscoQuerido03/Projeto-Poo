/**
 * Class Pastelaria que extends Restauracao, herdando todos os seus metodos e atributos
 */
public class Pastelaria extends Restauracao{
    private int nrMedBolosDia;
    private int medFatBoloVendDia;

    /**
     * Construtor da classe, recebe dados para a inicialização
     * @param nome Nome da Empresa
     * @param distrito Distrito da Empresa
     * @param gps Objeto da classe Coordenadas
     * @param custoSalMedAnual Custo de Salario Anual
     * @param nrEmpMesa Numero de empregados de mesa
     * @param nrMedClientesDia Numero Medio de Clientes Dia
     * @param nrMedBolosDia Numero de Bolos vendidos por Dia
     * @param medFatBoloVendDia Media de Faturação por Bolo vendido por Dia
     */
    public Pastelaria(String nome, String distrito, Coordenadas gps, int custoSalMedAnual, int nrEmpMesa, int nrMedClientesDia, int nrMedBolosDia, int medFatBoloVendDia) {
        super(nome, distrito, gps, custoSalMedAnual, nrEmpMesa, nrMedClientesDia);
        this.nrMedBolosDia = nrMedBolosDia;
        this.medFatBoloVendDia = medFatBoloVendDia;
    }

    @Override
    public String toString() {
        return "Pastelaria:" + super.toString();
    }
    public int getNrMedBolosDia() {
        return nrMedBolosDia;
    }

    public void setNrMedBolosDia(int nrMedBolosDia) {
        this.nrMedBolosDia = nrMedBolosDia;
    }

    public int getMedFatBoloVendDia() {
        return medFatBoloVendDia;
    }

    public void setMedFatBoloVendDia(int medFatBoloVendDia) {
        this.medFatBoloVendDia = medFatBoloVendDia;
    }

    @Override
    protected float despesaAnual() {
        return super.despesaAnual();
    }

    /**
     * Método que calcula a receita anual de um cafe
     * @return receita anual
     */
    @Override
    protected float receitaAnual() {
        return getNrMedBolosDia()*getMedFatBoloVendDia();
    }

    protected float lucro() {
        return super.lucro();
    }

    @Override
    protected String getCateg() {
        return super.getCateg();
    }

    @Override
    protected String printCap() {
        return super.printCap();
    }

    @Override
    protected float capacidade() {
        return super.capacidade();
    }
}
