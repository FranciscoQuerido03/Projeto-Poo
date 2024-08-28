import java.io.Serializable;

/**
 * Classe Empresa, implementa Serializable
 */
public abstract class Empresas implements Serializable {
    private String nome;
    private String distrito;
    private Coordenadas gps;
/**
 * Construtor da classe, recebe dados para a inicialização
 * @param nome Nome da Empresa
 * @param distrito Distrito da Empresa
 * @param gps objeto da classe Coordenadas
 */
    public Empresas(String nome, String distrito, Coordenadas gps) {
        this.nome = nome;
        this.distrito = distrito;
        this.gps = gps;
    }

    public Coordenadas getGps() {
        return gps;
    }

    public void setGps(Coordenadas gps) {
        this.gps = gps;
    }

    /**
     * @return Print das variaveis da classe
     */
    @Override
    public String toString() {
        return "Nome=" + nome + ", Distrito= " + distrito + ",Despesa Anual= "+despesaAnual()+ ", Receita Anual= "+receitaAnual()+ ", Lucro= "+lucro();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     * Metodo printReceita
     * @return print do nome da empresa e a sua receita anual
     */
    protected String printReceita(){
        return getNome() +" com uma receita de "+receitaAnual()+" euros";
    }
    /**
     * Metodo printDespesa
     * @return print do nome da empresa e a sua despesa anual
     */
    protected String printDespesa(){
        return getNome() +" com uma despesa de "+despesaAnual()+" euros";
    }
    /**
     * Metodo printLucro
     * @return print do nome da empresa e do seu lucro
     */
    protected String printLucro(){
        return getNome() +" com um lucro de "+lucro()+" euros";
    }


    abstract protected String getCateg();

    /**
     * Metodo capacidade
     * @return -1 em todas as classes que nao sejam do tipo Restauração
     */
    protected float capacidade(){
        return -1;
    }
    /**
     * Metodo printCap
     * @return print do nome da empresa e a sua capacidade
     */
    protected String printCap(){
        return getNome()+" com uma capacidade de "+capacidade()+" clientes diarios.";
    }


    abstract protected float despesaAnual();
    abstract protected float receitaAnual();
    /**
     * Metodo lucro
     * @return calculo do lucro, subtraindo a depesa anual à receita anual
     */
    protected float lucro() {
        return ((receitaAnual() - despesaAnual()));
    }

}
