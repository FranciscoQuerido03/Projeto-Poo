import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe Ficheiros
 */
public class Ficheiros {
    private File fTxt, fDat;

    /**
     * Construtor da Classe que inicializa as variaveis fTxt e fDat
     */
    public Ficheiros() {
        fTxt = new File("dados.txt");
        fDat = new File("dados.dat");
    }

    public String verificaFicheiro() {
        String controlo = "";
        if (fDat.exists() && fDat.isFile()) {
            controlo = "fDat";
        } else
            controlo = "fTxt";


        return controlo;
    }
    public File getfTxt() {
        return fTxt;
    }

    public void setfTxt(File fTxt) {
        this.fTxt = fTxt;
    }

    public File getfDat() {
        return fDat;
    }

    public void setfDat(File fDat) {
        this.fDat = fDat;
    }

    /**
     * Metodo que le e trata os dados do ficheiro de texto, cria um objeto Empresas com esses dados
     * e adiciona-o a um ArrayList que vai conter todas as Empresas.
     * @return ArrayList com todas as empresas lidas do ficheiro de texto
     */
    public ArrayList<Empresas> lerFichTXT() {
        ArrayList<Empresas> novaListaEmpresa = new ArrayList<>();
        if (fTxt.exists() && fTxt.isFile()) {
            try {
                FileReader fr = new FileReader(fTxt);
                BufferedReader br = new BufferedReader(fr);
                String line;
                String[] line1;
                //Le o ficheiro linha a linha ate chegar ao fim do mesmo
                while ((line = br.readLine()) != null) {
                    switch (line) {
                        case "FastFood":
                            while (!Objects.equals(line = br.readLine(), "--")) { // Le as linhas do ficheiro até encontrar o separador "--" que indica mudança de categoria.
                                line1 = line.split("-"); //Divide os varios atributos lidos na linha separados por "-" e adiciona-os ao array line1
                                FastFood f = new FastFood(line1[0], line1[1], new Coordenadas(Integer.parseInt(line1[2]),Integer.parseInt(line1[3])), Integer.parseInt(line1[3]), Integer.parseInt(line1[4]), Integer.parseInt(line1[5]), Integer.parseInt(line1[6]), Integer.parseInt(line1[7]), Integer.parseInt(line1[8]), Integer.parseInt(line1[9]), Integer.parseInt(line1[10]));
                                novaListaEmpresa.add(f); //adiciona o objeto criado ao ArrayList novaListaEmpresa
                            }
                            break;

                        case "Local":
                            while (!Objects.equals(line = br.readLine(), "--")) {// Le as linhas do ficheiro até encontrar o separador "--" que indica mudança de categoria.
                                line1 = line.split("-");//Divide os varios atributos lidos na linha separados por "-" e adiciona-os ao array line1
                                Locais l = new Locais(line1[0], line1[1], new Coordenadas(Integer.parseInt(line1[2]),Integer.parseInt(line1[3])), Integer.parseInt(line1[3]), Integer.parseInt(line1[4]), Integer.parseInt(line1[5]), Integer.parseInt(line1[6]), Integer.parseInt(line1[7]), Integer.parseInt(line1[8]), Integer.parseInt(line1[9]), Integer.parseInt(line1[10]));
                                novaListaEmpresa.add(l);//adiciona o objeto criado ao ArrayList novaListaEmpresa
                            }
                            break;
                        case "Cafe":
                            while (!Objects.equals(line = br.readLine(), "--")) {// Le as linhas do ficheiro até encontrar o separador "--" que indica mudança de categoria.
                                line1 = line.split("-");//Divide os varios atributos lidos na linha separados por "-" e adiciona-os ao array line1
                                Cafe c = new Cafe(line1[0], line1[1], new Coordenadas(Integer.parseInt(line1[2]),Integer.parseInt(line1[3])), Integer.parseInt(line1[3]), Integer.parseInt(line1[4]), Integer.parseInt(line1[5]), Integer.parseInt(line1[6]), Integer.parseInt(line1[7]));
                                novaListaEmpresa.add(c);//adiciona o objeto criado ao ArrayList novaListaEmpresa
                            }
                            break;
                        case "Pastelaria":
                            while (!Objects.equals(line = br.readLine(), "--")) {// Le as linhas do ficheiro até encontrar o separador "--" que indica mudança de categoria.
                                line1 = line.split("-");//Divide os varios atributos lidos na linha separados por "-" e adiciona-os ao array line1
                                Pastelaria p = new Pastelaria(line1[0], line1[1],new Coordenadas(Integer.parseInt(line1[2]),Integer.parseInt(line1[3])), Integer.parseInt(line1[3]), Integer.parseInt(line1[4]), Integer.parseInt(line1[5]), Integer.parseInt(line1[6]), Integer.parseInt(line1[7]));
                                novaListaEmpresa.add(p);//adiciona o objeto criado ao ArrayList novaListaEmpresa
                            }
                            break;
                        case "Mercado":
                            while (!Objects.equals(line = br.readLine(), "--")) {// Le as linhas do ficheiro até encontrar o separador "--" que indica mudança de categoria.
                                line1 = line.split("-");//Divide os varios atributos lidos na linha separados por "-" e adiciona-os ao array line1
                                Mercado m = new Mercado(line1[0], line1[1], new Coordenadas(Integer.parseInt(line1[2]),Integer.parseInt(line1[3])),Integer.parseInt(line1[5]),line1[4], Integer.parseInt(line1[6]),Integer.parseInt(line1[7]));
                                novaListaEmpresa.add(m);//adiciona o objeto criado ao ArrayList novaListaEmpresa
                            }
                            break;
                        case "Frutaria":
                            while (!Objects.equals(line = br.readLine(), "--")) {// Le as linhas do ficheiro até encontrar o separador "--" que indica mudança de categoria.
                                line1 = line.split("-");//Divide os varios atributos lidos na linha separados por "-" e adiciona-os ao array line1
                                Frutaria ft = new Frutaria(line1[0], line1[1], new Coordenadas(Integer.parseInt(line1[2]),Integer.parseInt(line1[3])), Integer.parseInt(line1[3]), Integer.parseInt(line1[4]), Integer.parseInt(line1[5]));
                                novaListaEmpresa.add(ft);//adiciona o objeto criado ao ArrayList novaListaEmpresa
                            }
                            break;
                    }
                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe.");
        }
        return novaListaEmpresa;
    }

    /**
     * Escreve no ficheiro de objetos todos os objetos contidos
     * no ArrayList que contem as Empresas
     * @param listaEmpresa ArrayList que contem as Empresas
     */
    public void escreverFichDAT(ArrayList<Empresas> listaEmpresa) {
        fDat = new File("dados.dat");

        try {
            FileOutputStream fos = new FileOutputStream(fDat);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(listaEmpresa);

            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro de objetos.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever ficheiro de objetos.");
        }
    }

    /**
     * Le o ficheiro de objetos e adiciona os objetos lidos ao ArrayList que contem as Empresas
     * @return  listaEmpresa ArrayList que contem as Empresas
     */
    public ArrayList<Empresas> lerFichDat(){
        fDat = new File("dados.dat");
        ArrayList<Empresas> novaListaEmpresa = new ArrayList<>();
        if (fDat.exists() && fDat.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(fDat);
                ObjectInputStream ois = new ObjectInputStream(fis);

                novaListaEmpresa = (ArrayList<Empresas>) ois.readObject();

                ois.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de objetos.");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Erro a ler ficheiro de objetos.");
            } catch (ClassNotFoundException ex) {
                System.out.println("Erro a converter objeto.");
            }
        } else {
            System.out.println("Ficheiro não existe.");
        }
        return novaListaEmpresa;
    }
}