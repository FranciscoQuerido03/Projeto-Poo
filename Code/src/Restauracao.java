/**
 * Classe Restauração, abstrata e extends de Empresas, herdando todos os seus metodos e atributos
 */
public abstract class Restauracao extends Empresas {
    private int custoSalMedAnual;
    private int nrEmpMesa;

    private int nrMedClientesDia;

    /**
     * Construtor da classe, recebe dados para a inicialização
     * @param nome Nome da Empresa
     * @param distrito Distrito da Empresa
     * @param gps Objeto da classe Coordenadas
     * @param custoSalMedAnual Custo de Salario Anual
     * @param nrEmpMesa Numero de empregados de mesa
     * @param nrMedClientesDia Numero Medio de Clientes Dia
     */
    public Restauracao(String nome, String distrito, Coordenadas gps, int custoSalMedAnual, int nrEmpMesa, int nrMedClientesDia) {
        super(nome, distrito, gps);
        this.custoSalMedAnual = custoSalMedAnual;
        this.nrEmpMesa = nrEmpMesa;
        this.nrMedClientesDia = nrMedClientesDia;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getNrMedClientesDia() {
        return nrMedClientesDia;
    }

    public void setNrMedClientesDia(int nrMedClientesDia) {
        this.nrMedClientesDia = nrMedClientesDia;
    }

    public int getCustoSalMedAnual() {
        return custoSalMedAnual;
    }

    public void setCustoSalMedAnual(int custoSalMedAnual) {
        this.custoSalMedAnual = custoSalMedAnual;
    }

    public int getNrEmpMesa() {
        return nrEmpMesa;
    }

    public void setNrEmpMesa(int nrEmpMesa) {
        this.nrEmpMesa = nrEmpMesa;
    }

    /**
     * Método que calcula a despesa anual de uma empresa do tipo restauração com base
     * no numero de empregados e os seus salarios medios anuais
     * @return despesa anual de uma empresa do tipo restauração
     */
    @Override
    protected float despesaAnual() {
        return getNrEmpMesa() * getCustoSalMedAnual();
    }

    /**
     * Método abstrato que calcula a receita anual
     * @return receita anual
     */
    @Override
    protected abstract float receitaAnual();

    protected float lucro() {
        return super.lucro();
    }

    /**
     * Método que identifica  a categoria da empresa
     * @return categoria da empresa
     */
    @Override
    protected String getCateg() {
        return "Restauração";
    }

    /**
     * Método que define a capacidade de uma empresa de restauração
     * @return numero médio de clientes por dia
     */
    @Override
    protected float capacidade() {
        return getNrMedClientesDia();
    }

    @Override
    protected String printCap() {
        return super.printCap();
    }
}