/**
 * Classe FastFood que extends Restaurante, herdando todos os métodos e atributos
 */
public class FastFood extends Restaurante{
    @Override
    public String toString() {
        return "FastFood: " + super.toString();
    }

    private int nrMesasInt;
    private int nrClientesDriveThru;
    private int valMedFatMesaDia;
    private int ValMedFatClienteDriveThru;

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
     * @param nrClientesDriveThru Numero de Clientes Drive-Thru
     * @param valMedFatMesaDia Valor Médio de Faturação por Mesa por Dia
     * @param valMedFatClienteDriveThru Valor Médio de Faturação por Cliente Drive-Thru
     */
    public FastFood(String nome, String distrito, Coordenadas gps, int custoSalMedAnual, int nrEmpMesa, int nrMedClientesDia,int nrDiasFuncAno, int nrMesasInt, int nrClientesDriveThru, int valMedFatMesaDia, int valMedFatClienteDriveThru) {
        super(nome, distrito, gps, custoSalMedAnual, nrEmpMesa, nrMedClientesDia, nrDiasFuncAno);
        this.nrMesasInt = nrMesasInt;
        this.nrClientesDriveThru = nrClientesDriveThru;
        this.valMedFatMesaDia = valMedFatMesaDia;
        ValMedFatClienteDriveThru = valMedFatClienteDriveThru;
    }

    public int getValMedFatClienteDriveThru() {
        return ValMedFatClienteDriveThru;
    }

    public void setValMedFatClienteDriveThru(int valMedFatClienteDriveThru) {
        ValMedFatClienteDriveThru = valMedFatClienteDriveThru;
    }

    public int getNrMesasInt() {
        return nrMesasInt;
    }

    public int getValMedFatMesaDia() {
        return valMedFatMesaDia;
    }

    public void setValMedFatMesaDia(int valMedFatMesaDia) {
        this.valMedFatMesaDia = valMedFatMesaDia;
    }

    public void setNrMesasInt(int nrMesasInt) {
        this.nrMesasInt = nrMesasInt;
    }

    public int getNrClientesDriveThru() {
        return nrClientesDriveThru;
    }

    public void setNrClientesDriveThru(int nrClientesDriveThru) {
        this.nrClientesDriveThru = nrClientesDriveThru;
    }

    @Override
    protected float despesaAnual() {
        return super.despesaAnual();
    }

    /**
     * Método que calcula a receita anual de um restaurante fast food
     * @return receita anual
     */
    @Override
    protected float receitaAnual() {
        return (getNrMesasInt()*getValMedFatMesaDia()+getNrClientesDriveThru()*getValMedFatClienteDriveThru())*super.receitaAnual();
    }

    protected float lucro() {
        return super.lucro();
    }

    @Override
    protected String getCateg() {
        return super.getCateg();
    }

    /**
     * Método que indica a capacidade de um restaurante fast food
     * @return capacidade de um restaurante fast food
     */
    @Override
    protected float capacidade() {
        return super.capacidade()+getNrClientesDriveThru();
    }

    @Override
    protected String printCap() {
        return super.printCap();
    }
}
