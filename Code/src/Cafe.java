/**
 * Classe Cafe que extends Restauracao, herdando todos os seus métodos e atributos
 */
public class Cafe extends Restauracao{
    private int nrCafeVendDia;
    private int medFatCafeVendDia;

    /**
     * Construtor da classe, recebe dados para a inicialização
     * @param nome Nome da Empresa
     * @param distrito Distrito da Empresa
     * @param gps Objeto da classe Coordenadas
     * @param custoSalMedAnual Custo de Salario Anual
     * @param nrEmpMesa Numero de empregados de mesa
     * @param nrMedClientesDia Numero Medio de Clientes Dia
     * @param nrCafeVendDia Numero de Cafes vendidos por Dia
     * @param medFatCafeVendDia Media de Faturação por Café vendido por Dia
     */
    public Cafe(String nome, String distrito, Coordenadas gps, int custoSalMedAnual, int nrEmpMesa, int nrMedClientesDia, int nrCafeVendDia, int medFatCafeVendDia) {
        super(nome, distrito, gps, custoSalMedAnual, nrEmpMesa, nrMedClientesDia);
        this.nrCafeVendDia = nrCafeVendDia;
        this.medFatCafeVendDia = medFatCafeVendDia;
    }

    @Override
    public String toString() {
        return "Cafe: " +super.toString();
    }

    public int getNrCafeVendDia() {
        return nrCafeVendDia;
    }

    public void setNrCafeVendDia(int nrCafeVendDia) {
        this.nrCafeVendDia = nrCafeVendDia;
    }

    public int getMedFatCafeVendDia() {
        return medFatCafeVendDia;
    }

    public void setMedFatCafeVendDia(int medFatCafeVendDia) {
        this.medFatCafeVendDia = medFatCafeVendDia;
    }

    protected float despesaAnual(){
        return super.despesaAnual();
    }

    /**
     * Método que calcula a receita anual de um cafe
     * @return receita anual
     */
    protected float receitaAnual(){
        return getMedFatCafeVendDia()*getNrCafeVendDia();
    }

    @Override
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
