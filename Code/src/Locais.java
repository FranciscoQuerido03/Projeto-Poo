/**
 * Classe Locais extends Restaurante, herdando todos os métodos e atributos
 */
public class Locais extends Restaurante{
    private int nrMesasInt;
    private int nrMesasEsp;
    private int custoLicAnMesaEsp;
    private int valMedFatMesaDia;

    /**
     * Construtor da classe, recebe dados para a inicialização
     * @param nome Nome da Empresa
     * @param distrito Distrito da Empresa
     * @param gps Objeto da classe Coordenadas
     * @param custoSalMedAnual Custo de Salario Anual
     * @param nrEmpMesa Numero de empregados de mesa
     * @param nrMedClientesDia Numero Medio de Clientes Dia
     * @param nrDiasFuncAno Numero de Dias de Funcionameto por Ano
     * @param nrMesasInt Numero de Mesas Interiores
     * @param nrMesasEsp Numero de Mesas Espladada
     * @param custoLicAnMesaEsp Custo Licença Anual por Mesa Esplanada
     * @param valMedFatMesaDia Valor Médio de Faturação por Mesa por Dia
     */
    public Locais(String nome, String distrito, Coordenadas gps, int custoSalMedAnual, int nrEmpMesa, int nrMedClientesDia, int nrDiasFuncAno, int nrMesasInt, int nrMesasEsp, int custoLicAnMesaEsp, int valMedFatMesaDia) {
        super(nome, distrito, gps, custoSalMedAnual, nrEmpMesa, nrMedClientesDia, nrDiasFuncAno);
        this.nrMesasInt = nrMesasInt;
        this.nrMesasEsp = nrMesasEsp;
        this.custoLicAnMesaEsp = custoLicAnMesaEsp;
        this.valMedFatMesaDia = valMedFatMesaDia;
    }

    @Override
    public String toString() {
        return  "Local: " +super.toString();
    }

    public int getNrMesasInt() {
        return nrMesasInt;
    }

    public void setNrMesasInt(int nrMesasInt) {
        this.nrMesasInt = nrMesasInt;
    }

    public int getNrMesasEsp() {
        return nrMesasEsp;
    }

    public void setNrMesasEsp(int nrMesasEsp) {
        this.nrMesasEsp = nrMesasEsp;
    }

    public int getCustoLicAnMesaEsp() {
        return custoLicAnMesaEsp;
    }

    public void setCustoLicAnMesaEsp(int custoLicAnMesaEsp) {
        this.custoLicAnMesaEsp = custoLicAnMesaEsp;
    }

    public int getValMedFatMesaDia() {
        return valMedFatMesaDia;
    }

    public void setValMedFatMesaDia(int valMedFatMesaDia) {
        this.valMedFatMesaDia = valMedFatMesaDia;
    }

    /**
     * Método que calcula a despesa anual dos restaurantes locais
     * @return despesa anual dos restaurantes locais
     */
    @Override
    protected float despesaAnual() {
        return super.despesaAnual()+getNrMesasEsp()*getCustoLicAnMesaEsp();
    }

    /**
     * Método que calcula a receita anual dos restaurantes locais
     * @return despesa anual dos restaurantes locais
     */
    @Override
    protected float receitaAnual() {
        return (getNrMesasEsp()+getNrMesasInt())*getValMedFatMesaDia()*super.receitaAnual();
    }

    protected float lucro() {
        return super.lucro();
    }

    @Override
    protected String getCateg() {
        return super.getCateg();
    }

    @Override
    protected float capacidade() {
        return super.capacidade();
    }

    @Override
    protected String printCap() {
        return super.printCap();
    }
}
