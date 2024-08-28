/**
 * @author Francisco Querido e Miguel Braga
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe para gerir as empresas
 */
public class StarThrive {

    public static void main(String[] args) {
        StarThrive gestao = new StarThrive();
        Screen gui = new Screen(gestao, gestao.listaEmpresas);
        gui.criaScreen();
    }

    /**
     * ArrayList que contém todas as empresas
     */
    private ArrayList<Empresas> listaEmpresas;

    /**
     * Variavel da classe ficheiro
     */
    private final Ficheiros ficheiro;

    /**
     * Construtor da classe, cria um listaEmpresas, com os dados do ficheiro de objetos (caso exista)
     * ou com o ficheiro de texto (caso o de dados nao exista), recorrendo a um ‘setter’ e a função que lê os ficheiros
     * da classe Ficheiro.
     */
    public StarThrive() {
        listaEmpresas = new ArrayList<Empresas>();
        ficheiro = new Ficheiros();
       if (ficheiro.getfDat().exists() && ficheiro.getfDat().isFile()){  //se o ficheiro de objetos existir então lê os dados dele
            setListaEmpresa(ficheiro.lerFichDat());
        }else{
            setListaEmpresa(ficheiro.lerFichTXT());  //caso não exista, lê os dados do ficheiro de texto
        }

    }

    public ArrayList<Empresas> getListaEmpresa() {
        return listaEmpresas;
    }

    public void setListaEmpresa(ArrayList<Empresas> listaEmpresa) {
        this.listaEmpresas = listaEmpresa;
    }

    /**
     * Metodo para adicionar empresas a ArrayList, e ao ficheiro de dados
     * @param empresa
     */
    public void addEmpresa(Empresas empresa){
        listaEmpresas.add(empresa);
        ficheiro.escreverFichDAT(getListaEmpresa());
    }
    /**
     * Metodo para remover empresas da ArrayList, e do ficheiro de dados
     */
    public void removeEmpresa(Empresas empresa){
        listaEmpresas.remove(empresa);
        ficheiro.escreverFichDAT(getListaEmpresa());
    }

    /**
     * Metodo para atualizar ficheiro
     * @param lista lista de empresas
     */
    public void atualizaFicheiros(ArrayList<Empresas> lista){
        ficheiro.escreverFichDAT(lista);
    }
    /**
     * Metodo que devolve a empresa com menor despesa anual do ArrayList com as empresas
     */
    protected Empresas menorDespesa(String tipo) {
        Empresas var = null;

        for (Empresas a:listaEmpresas){ //guardar uma empresa do tipo pertendido na variavel ver para ter um valor de referência para comparar com as restantes empresas do mesmo tipo
            if(Objects.equals(a.getCateg(), tipo)) {
                var = a;
                break;
            }
        }
        for (Empresas a : listaEmpresas) { //percorre todos as empresas do array com as empresas
            if (var.despesaAnual() > a.despesaAnual() && Objects.equals(tipo, a.getCateg())) {  //se a despesa anual da empresa a estudar for menor que a atualmente guardada em maiorDespesa, atualiza esse valor e guarda a empresa a estudar em var
                var = a;
            }
        }
        return var; //devolve a empresa com menor despesa anual
    }

    /**
     * Metodo receita, encontra a empresa com maior receita
     * @param tipo tipo de empresa (restauração ou mercearia)
     * @return Empresa com maior receita
     */
    protected Empresas maiorReceita(String tipo) {
        float maiorReceita = 0;
        Empresas var = null;
        for (Empresas a : listaEmpresas) { //percorre todos as empresas do array fornecido
            if (maiorReceita < a.receitaAnual() && Objects.equals(tipo, a.getCateg())) {   //se a receita anual da empresa a estudar for maior que a atualmente guardada em maiorDespesa, atualiza esse valor e guarda a empresa a estudar em var
                maiorReceita=a.receitaAnual();
                var = a;
            }
        }
        return var;
    }

    /**
     * Metodo que percorre o ArraList com as empresas todas e guarda a com maior lucro
     * @param tipo tipo de empresa (restauração ou mercearia)
     * @return Empresa com maior lucro
     */
    protected Empresas lucro(String tipo) {
        float lucro = 0;
        Empresas var = null;
        for (Empresas a : listaEmpresas) {
            if (lucro < a.lucro() && Objects.equals(tipo, a.getCateg())) {  //se o lucro da empresa a estudar for maior que a atualmente guardada em lucro, atualiza esse valor e guarda a empresa a estudar em var
                lucro = a.lucro();
                var = a;
            }
        }
        return var;
    }

    /**
     * Metodo que percorre o ArraList com as empresas todas e guarda noutro ArrayList as duas com maior capacidade
     * @return ArrayList com as duas Empresas de Restauração com maior capacidadde do ArrayList com as empresas todas
     */
    protected ArrayList<Empresas> maiorCapClientesDia() {  //as duas empresas que atendem mais clientes por dia **VER CASO DO FASTFOOD, CLIENTES DRIVE-THRU**
        float nClientes = 0;
        Empresas var1 = null;
        Empresas var2 = null;
        ArrayList<Empresas> arr = new ArrayList<>();
        for (Empresas a : listaEmpresas) { //se a capacidade da empresa a estudar for maior que a atualmente guardada em nClientes, atualiza esse valor e guarda a empresa a estudar em var
            if (nClientes < a.capacidade() && a.capacidade()!=-1 ) {
                    nClientes = a.capacidade();
                    var1 = a;
                }
            }
        arr.add(var1);//adiciona a empresa com maior capacidade ao ArrayList<Empresas> arr
        nClientes = 0;
        for (Empresas a : listaEmpresas) {
            if (nClientes <= a.capacidade() && a != var1 && a.capacidade()!=-1 ) {//se a capacidade da empresa a estudar for maior ou igual que a atualmente guardada em nClientes, atualiza esse valor e guarda a empresa a estudar em var
                nClientes = a.capacidade();
                var2 = a;
            }
        }
        arr.add(var2);////adiciona a segunda empresa com maior capacidade ao ArrayList<Empresas> arr
        return arr;
    }
}
