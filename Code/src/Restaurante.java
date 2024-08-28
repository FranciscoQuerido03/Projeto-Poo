/**
 * Classe Resturante, abstrata que extends Restauracao, herdando todos os seus métodos e atributos
 */
public abstract class Restaurante extends Restauracao{
    private int nrMedClientesDia;
    private int nrDiasFuncAno;

    /**
     * Construtor da classe, recebe dados para a inicialização
     * @param nome Nome da Empresa
     * @param distrito Distrito da Empresa
     * @param gps Objeto da classe Coordenadas
     * @param custoSalMedAnual Custo de Salario Anual
     * @param nrEmpMesa Numero de empregados de mesa
     * @param nrMedClientesDia Numero Medio de Clientes Dia
     * @param nrDiasFuncAno Numero de Dias de Funcionameto por Ano
     */
    public Restaurante(String nome, String distrito, Coordenadas gps, int custoSalMedAnual, int nrEmpMesa, int nrMedClientesDia, int nrDiasFuncAno) {
        super(nome, distrito, gps, custoSalMedAnual, nrEmpMesa, nrMedClientesDia);
        this.nrMedClientesDia = nrMedClientesDia;
        this.nrDiasFuncAno = nrDiasFuncAno;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int getNrMedClientesDia() {
        return nrMedClientesDia;
    }

    public void setNrMedClientesDia(int nrMedClientesDia) {
        this.nrMedClientesDia = nrMedClientesDia;
    }

    public int getNrDiasFuncAno() {
        return nrDiasFuncAno;
    }

    public void setNrDiasFuncAno(int nrDiasFuncAno) {
        this.nrDiasFuncAno = nrDiasFuncAno;
    }


    @Override
    protected float despesaAnual() {
        return super.despesaAnual();
    }

    /**
     * Método que indica a quantidade de dias que o estabelecimento se encontra
     * aberto por ano para utilização nas subclasses
     * @return Numero de Dias de Funcionamento por Ano
     */
    @Override
    protected float receitaAnual() {
        return getNrDiasFuncAno();
    }
    protected float lucro() {
        return super.lucro();
    }
    protected int nClientesDia(){
        return getNrMedClientesDia();
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
