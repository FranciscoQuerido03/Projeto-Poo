import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe Screen, classe que contém a GUI
 */
public class Screen {

    private JList<String> lista;
    private StarThrive gestao;
    private ArrayList<Empresas> listaEmpresas;
    private JFrame gerirEmpresas;

    /**
     * Construtor da classe, recebe dados para a inicialização
     * @param gestao objeto da classe StarThrive
     * @param listaEmpresas ArrayList com todas as empresas
     */
    public Screen(StarThrive gestao, ArrayList<Empresas> listaEmpresas) {
        this.gestao = gestao;
        this.listaEmpresas = listaEmpresas;
    }

    /**
     * Metodo que cria a GUI
     */
    public void criaScreen() {
        //ecra
        JFrame mainScreen = new JFrame();
        mainScreen.setTitle("Aplicação StarThrive");
        mainScreen.setSize(800, 300);
        mainScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Para que o programa continue a correr mesmo depois de ser fechada a janela.
        mainScreen.addWindowListener(new WindowAdapter() {    //Criar caixa de diálogo de confirmação ao clicar no botão de fechar janela
            @Override
            public void windowClosing(WindowEvent event) {
                int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende fechar a aplicação?", "App Closing Confirmation", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        mainScreen.setLocationRelativeTo(null);

        //botoes
        JButton empresas = new JButton("Empresas");
        JButton maiorCapacidade = new JButton("Maior Capacidadde");
        JButton despRecLucro = new JButton("Despesa/Receita/Lucro");
        JButton encerrar = new JButton("Encerrar");

        //texto
        JLabel mensagemInicial = new JLabel("StarThrive, escolha a ação a realizar:", SwingConstants.CENTER);

        //paineis
        JPanel botoes = new JPanel();
        JPanel principal = new JPanel();


        botoes.setLayout(new GridLayout(2, 3));

        botoes.add(empresas);
        botoes.add(maiorCapacidade);
        botoes.add(despRecLucro);
        botoes.add(encerrar);

        principal.setLayout(new GridLayout(2, 1));

        principal.add(mensagemInicial);
        principal.add(botoes);

        mainScreen.add(principal);
        mainScreen.setVisible(true);

        //listeners
        empresas.addActionListener(new EmpresasButtonListener());
        maiorCapacidade.addActionListener(new CapacidadeButtonListener());
        despRecLucro.addActionListener(new DesRecLucroButtonListener());
        encerrar.addActionListener(actionEvent -> {System.exit(0);});


    }

    /**
     * Inner Class EmpresasButtonListener, implementa ActionListener
     */
    private class EmpresasButtonListener implements ActionListener {
        /**
         * Metodo que cria uma janela com um menu com tres opções (Listar Empresas, Gerir Empresas, Fechar),
         * quando o botão Empresas é selecionado
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //nova frame
            JFrame screenEmpresas = new JFrame();
            screenEmpresas.setSize(500, 200);
            screenEmpresas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            screenEmpresas.setLocationRelativeTo(null); //coloca o frame a aparecer no meio do ecra


            //botoes e labels
            JButton listarEmpresas = new JButton("Listar empresas");
            JButton gerirEmpresas = new JButton("Gerir empresas");
            JButton closeButton = new JButton("Fechar");
            closeButton.addActionListener(actionEvent -> {screenEmpresas.dispose();}); //para fechar a janela quando o botao closeButton é premido
            JLabel label = new JLabel("Selecione a opção que pretende", SwingConstants.CENTER);

            //principal grande
            JPanel principal = new JPanel();
            JPanel botoes = new JPanel();
            principal.setLayout(new GridLayout(2, 1));
            botoes.setLayout(new GridLayout(1, 3));
            principal.add(label);
            botoes.add(listarEmpresas);
            botoes.add(gerirEmpresas);
            botoes.add(closeButton);
            principal.add(botoes);
            screenEmpresas.add(principal);
            screenEmpresas.setVisible(true);


            //listeners
            listarEmpresas.addActionListener(new ListarEmpresasButtonListener());
            gerirEmpresas.addActionListener(new GerirEmpresasButtonListener());

        }
    }
    /**
     * Inner Class CapacidadeButtonListener, implementa ActionListener
     */
    private class CapacidadeButtonListener implements ActionListener {
        ArrayList<Empresas> arr;
        Empresas a;
        Empresas b;

        /**
         * Metodo que cria uma janela com uma caixa de texto onde estão escritas as duas
         * empresas do tipo Restauração com maior capacidade e um botão para fechar a janela
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //as duas empresas do tipo restauração com maior Capacidade de Clientes por dia
            arr = gestao.maiorCapClientesDia();
            a = arr.get(0);
            b = arr.get(1);

            //JFrame
            JFrame screenCap = new JFrame();
            screenCap.setSize(500, 200);
            screenCap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            screenCap.setLocationRelativeTo(null); //coloca o frame a aparecer no meio do ecra

            //JLabel e JTextArea
            JLabel label = new JLabel("As 2 empresas do tipo Restauração com maior capacidade de clientes por dia", SwingConstants.CENTER);
            JTextArea areaTexto = new JTextArea(a.printCap() + "\n" + b.printCap());
            areaTexto.setEditable(false);

            //botao close
            JButton closeButton = new JButton("Fechar");
            closeButton.setFocusable(false);
            closeButton.addActionListener(actionEvent -> {screenCap.dispose();}); //para fechar a janela quando o botão closeButton é premido

            //JPanel
            JPanel principal = new JPanel();
            principal.setLayout(new BorderLayout());
            principal.add(label, BorderLayout.NORTH);
            principal.add(areaTexto, BorderLayout.CENTER, SwingConstants.CENTER);
            principal.add(closeButton, BorderLayout.SOUTH);
            screenCap.add(principal);
            screenCap.setVisible(true);
        }
    }
    /**
     * Inner Class DesRecLucroButtonListener, implementa ActionListener
     */
    private class DesRecLucroButtonListener implements ActionListener {
        /**
         * Metodo que cria uma janela com várias caixas de Texto e titulos
         * que servem para indicar qual a empresa com maior receita, menor despesa e maior lucro
         * do tipo Mercearia e do tipo Restauração
         * @param e the event to be processed
         */

        @Override
        public void actionPerformed(ActionEvent e) {
            //JFrame
            JFrame screenDesRecLucro = new JFrame();
            screenDesRecLucro.setSize(800, 300);
            screenDesRecLucro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            screenDesRecLucro.setLocationRelativeTo(null); //coloca o frame a aparecer no meio do ecra

            //JTextField
            JTextField caixaTxtReceita = new JTextField(gestao.maiorReceita("Mercearia").printReceita());
            JTextField caixaTxtDespesa = new JTextField(gestao.menorDespesa("Mercearia").printDespesa());
            JTextField caixaTxtLucro = new JTextField(gestao.lucro("Mercearia").printLucro());

            JTextField caixaTxtReceitaR = new JTextField(gestao.maiorReceita("Restauração").printReceita());
            JTextField caixaTxtDespesaR = new JTextField(gestao.menorDespesa("Restauração").printDespesa());
            JTextField caixaTxtLucroR = new JTextField(gestao.lucro("Restauração").printLucro());


            //botao close
            JButton closeButton = new JButton("Fechar");
            closeButton.addActionListener(actionEvent -> {screenDesRecLucro.dispose();}); //para fechar a janela quando o botão closeButton é premido


            //JLabel main
            JLabel textoMercearia = new JLabel("Tipo Mercearia", SwingConstants.CENTER);
            JLabel textoRestauracao = new JLabel("Tipo Restauração", SwingConstants.CENTER);

            //JPanel titulos
            JPanel titulos = new JPanel();
            titulos.setLayout(new GridLayout(1, 2));
            titulos.add(textoMercearia);
            titulos.add(textoRestauracao);


            //JLabel Mercearia
            JLabel receita = new JLabel("Empresa(s) com maior receita", SwingConstants.LEFT);
            JLabel despesa = new JLabel("Empresa(s) com menor despesa", SwingConstants.LEFT);
            JLabel lucro = new JLabel("Empresa(s) com maior lucro", SwingConstants.LEFT);

            //JPanel Mercearia
            JPanel mercearia = new JPanel();
            mercearia.setLayout(new GridLayout(6, 1));
            mercearia.add(receita);
            mercearia.add(caixaTxtReceita);
            mercearia.add(despesa);
            mercearia.add(caixaTxtDespesa);
            mercearia.add(lucro);
            mercearia.add(caixaTxtLucro);

            //JLabel Restauração
            JLabel receitaR = new JLabel("Empresa(s) com maior receita", SwingConstants.LEFT);
            JLabel despesaR = new JLabel("Empresa(s) com menor despesa", SwingConstants.LEFT);
            JLabel lucroR = new JLabel("Empresa(s) com maior lucro", SwingConstants.LEFT);

            //JPanel Restauração
            JPanel restauracao = new JPanel();
            restauracao.setLayout(new GridLayout(6, 1));
            restauracao.add(receitaR);
            restauracao.add(caixaTxtReceitaR);
            restauracao.add(despesaR);
            restauracao.add(caixaTxtDespesaR);
            restauracao.add(lucroR);
            restauracao.add(caixaTxtLucroR);

            //JPanel main
            JPanel mainScreen = new JPanel();
            mainScreen.setLayout(new BorderLayout());
            mainScreen.add(titulos, BorderLayout.NORTH);
            mainScreen.add(mercearia, BorderLayout.WEST);
            mainScreen.add(restauracao, BorderLayout.EAST);
            mainScreen.add(closeButton, BorderLayout.SOUTH);
            screenDesRecLucro.add(mainScreen);
            screenDesRecLucro.setVisible(true);

        }

    }

    /**
     * Inner Class ListarEmpresasButtonListener, implementa ActionListener
     */
    private class ListarEmpresasButtonListener implements ActionListener {
        /**
         * Metodo que cria uma janela com uma lista de todas as empresas
         * e um botão para analisar a empresa selecionada e outro para
         * fechar a janela.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //JFrame
            JFrame listarEmpresas = new JFrame();
            listarEmpresas.setTitle("Lista de empresas StarThrive");
            listarEmpresas.setSize(400, 300);
            listarEmpresas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            listarEmpresas.setLocationRelativeTo(null); //coloca o frame a aparecer no meio do ecra

            JLabel label = new JLabel("Selecione uma empresa");

            ArrayList<Empresas> listaDeEmpresas = gestao.getListaEmpresa();

            DefaultListModel<String> nomesEmpresas = new DefaultListModel<>();

            for (int i = 0; i < listaDeEmpresas.size(); i++) {  //percorrer todos as empresas do array e adicionar o seu nome ao array nomesEmpresas
                nomesEmpresas.addElement(listaDeEmpresas.get(i).getNome());
            }
            //Lista e scroller
            lista = new JList<>(nomesEmpresas);
            JScrollPane scroller = new JScrollPane(lista);

            //botões
            JButton analisar = new JButton("Analisar");
            JButton closeButton = new JButton("Fechar");
            closeButton.addActionListener(actionEvent -> {listarEmpresas.dispose();}); //para fechar as janelas quando o closeButton é premido

            //JPanel principal
            JPanel principal = new JPanel();
            principal.setLayout(new BorderLayout());
            //JPanel botoes
            JPanel botoes = new JPanel();
            botoes.setLayout(new GridLayout(2, 2));
            botoes.add(analisar);
            botoes.add(closeButton);

            principal.add(label, BorderLayout.NORTH);
            principal.add(scroller, BorderLayout.CENTER);
            principal.add(botoes, BorderLayout.SOUTH);

            listarEmpresas.add(principal);
            listarEmpresas.setVisible(true);

            //listener
            analisar.addActionListener(new AnalisarButtonListener());

        }
    }

    /**
     * Inner Class GerirEmpresasButtonListener, implementa ActionListener
     */
    private class GerirEmpresasButtonListener implements ActionListener {
        /**
         * Metodo que cria uma janela com uma lista das empresas e botões
         * com as opções (Adicionar Empresa, Eliminar Empresa, Editar Empresa, Fechar)
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //Screen
            gerirEmpresas = new JFrame();
            gerirEmpresas.setTitle("Lista de empresas StarThrive");
            gerirEmpresas.setSize(400, 300);
            gerirEmpresas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            gerirEmpresas.setLocationRelativeTo(null); //coloca o frame a aparecer no meio do ecra

            JLabel label = new JLabel("Selecione uma empresa");

            ArrayList<Empresas> listaDeEmpresas = gestao.getListaEmpresa();

            DefaultListModel<String> nomesEmpresas = new DefaultListModel<>();

            for (int i = 0; i < listaDeEmpresas.size(); i++) {  //percorrer todos as empresas do array e adicionar o seu nome ao array nomesEmpresas
                nomesEmpresas.addElement(listaDeEmpresas.get(i).getNome());
            }
            //Lista e scroller
            lista = new JList<>(nomesEmpresas);
            JScrollPane scroller = new JScrollPane(lista);

            //botao editar empresa
            JButton editar = new JButton("Editar empresa");
            //botao adicionar empresa
            JButton adicionar = new JButton("Adicionar empresa");
            //botao eliminar empresa
            JButton eliminar = new JButton("Eliminar empresa");
            //botao close
            JButton closeButton = new JButton("Fechar");
            closeButton.addActionListener(actionEvent -> {gerirEmpresas.dispose();}); //para fechar a janela quando o botão closeButton é premido

            //JPanel principal e botoes
            JPanel principal = new JPanel();
            JPanel botoes = new JPanel();
            principal.setLayout(new BorderLayout());
            botoes.setLayout(new GridLayout(2, 4));
            botoes.add(editar);
            botoes.add(adicionar);
            botoes.add(eliminar);
            botoes.add(closeButton);
            principal.add(label, BorderLayout.NORTH);
            principal.add(scroller, BorderLayout.CENTER);
            principal.add(botoes, BorderLayout.SOUTH);

            gerirEmpresas.add(principal);
            gerirEmpresas.setVisible(true);

            //listeners
            editar.addActionListener(new EditarEmpresaButtonListener());
            adicionar.addActionListener(new AddEmpresaMenuButtonListener());
            eliminar.addActionListener(new RemoverEmpresaButtonListener());
        }
    }
    /**
     * Inner Class AnalisarButtonListener, implementa ActionListener
     */
    private class AnalisarButtonListener implements ActionListener {
        /**
         *Metodo que cria uma janela com várias caixas de texto, cada uma com um título associado,
         * para indicar todos os atributos da empresa selecionada
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //titulos gerais
            JLabel tituloCategoria = new JLabel("Categoria");
            JLabel tituloDistrito = new JLabel("Distrito");
            JLabel tituloLocalizacao = new JLabel("Localização GPS (Latitude,Longitude)");
            JLabel tituloReceita = new JLabel("Receita Anual(euros)");
            JLabel tituloDespesa = new JLabel("Despesa Anual(euros)");
            JLabel tituloLucro = new JLabel("Lucro(Sim/Não)");


            //JTextFields
            JTextField categoria;
            JTextField distrito;
            JTextField localizacao;
            JTextField receita;
            JTextField despesa;
            JTextField lucro;


            for (Empresas a : gestao.getListaEmpresa()) {
                if (Objects.equals(a.getNome(), lista.getSelectedValue())) {
                    //JFrame empresa
                    JFrame empresa = new JFrame();
                    empresa.setTitle(a.getNome());
                    empresa.setSize(800, 500);
                    empresa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    empresa.setLocationRelativeTo(null); //coloca o frame a aparecer no meio do ecra

                    //JTextFields -nao editaveis
                    categoria = new JTextField(a.getCateg());
                    categoria.setEditable(false);
                    distrito = new JTextField(a.getDistrito());
                    distrito.setEditable(false);
                    localizacao = new JTextField(a.getGps().toString());
                    localizacao.setEditable(false);
                    receita = new JTextField(String.valueOf(a.receitaAnual()));
                    receita.setEditable(false);
                    despesa = new JTextField(String.valueOf(a.despesaAnual()));
                    despesa.setEditable(false);
                    lucro = new JTextField(String.valueOf(a.lucro()));
                    lucro.setEditable(false);

                    //botao close
                    JButton closeButton = new JButton("Fechar");
                    closeButton.addActionListener(actionEvent -> {empresa.dispose();}); //para fechar a janela quando o botão closeButton é premido

                    //JPanel main
                    JPanel main = new JPanel();
                    main.setLayout(new GridLayout(7, 2));

                    main.add(tituloCategoria);
                    main.add(categoria);
                    main.add(tituloDistrito);
                    main.add(distrito);
                    main.add(tituloLocalizacao);
                    main.add(localizacao);
                    main.add(tituloReceita);
                    main.add(receita);
                    main.add(tituloDespesa);
                    main.add(despesa);
                    main.add(tituloLucro);
                    main.add(lucro);
                    main.add(closeButton);

                    empresa.add(main);
                    empresa.setVisible(true);
                    break;
                }
            }
        }
    }
    /**
     * Inner Class EditarEmpresaButtonListener, implementa ActionListener
     */
    private class EditarEmpresaButtonListener implements ActionListener {
        /**
         * Metodo que permite editar os tres atributos comuns a todas as emprersas (nome, distrito, coordenadas)
         * Funciona pedindo ao utilizador que indique qual dos tres atributos deseja editar da empresa selecionada,
         * cria uma caixa de dialogo para que o utilizador preencha com os dados do atributo a editar. Termina atualizando
         * o dado editado pelo utilizador na variavel
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //titulos gerais
            JLabel tituloNome = new JLabel("Nome");
            JLabel tituloDistrito = new JLabel("Distrito");
            JLabel tituloLocalizacao = new JLabel("Localização GPS (Latitude,Longitude)");

            String escolha;
            for (Empresas emp : gestao.getListaEmpresa()) {
                if (Objects.equals(emp.getNome(), lista.getSelectedValue())) {

                    String[] arrayEscolhaF = {"Nome", "Distrito", "Coordenadas"};

                    escolha = (String) JOptionPane.showInputDialog(null, "Escolhe o parâmetro a editar", "Editor", JOptionPane.PLAIN_MESSAGE, null, arrayEscolhaF, arrayEscolhaF[0]);

                    boolean controlo = false;

                    if (escolha == null) { //se o input==null significa que foi selecionado a opção Cancel
                        escolha = "Cancelar";
                    }

                    //Edição do atributo selecionado
                    switch (escolha) {
                        case "Nome" -> {
                            while (!controlo) {
                                String nome = JOptionPane.showInputDialog("Nome da empresa:");
                                if (nome == null || (("".equals(nome))))
                                    JOptionPane.showMessageDialog(null, "Por favor insira um nome válido!");

                                else {
                                    emp.setNome(nome);
                                    gestao.atualizaFicheiros(listaEmpresas);
                                    tituloNome.setText(nome);
                                    JOptionPane.showMessageDialog(null, "Nome editado com sucesso");
                                    controlo = true;
                                }
                            }
                            gerirEmpresas.dispose(); //fecha a janela para que o conteudo atualize
                        }
                        case "Distrito" -> {
                            String[] arrayDistritos = {"Aveiro", "Beja", "Braga", "Bragança", "Castelo Branco", "Coimbra", "Évora", "Faro", "Guarda", "Leiria", "Lisboa", "Portalegre", "Porto", "Santarém", "Setúbal", "Viana do Castelo", "Vila Real", "Viseu"};
                            String distrito = (String) JOptionPane.showInputDialog(null, "Escolhe o distrito", "Modo Editor", JOptionPane.PLAIN_MESSAGE, null, arrayDistritos, arrayDistritos[0]);
                            if (distrito != null) {
                                emp.setDistrito(distrito);
                                gestao.atualizaFicheiros(listaEmpresas);
                                tituloDistrito.setText(distrito);
                                gerirEmpresas.dispose(); //fecha a janela para que o conteudo atualize
                            }
                        }
                        case "Coordenadas" -> {
                            double latitude = 0, longitude = 0;

                            //Latitude
                            while (!controlo) {
                                String latitudeStr = JOptionPane.showInputDialog("Latitude:");
                                if (latitudeStr == null)
                                    return;

                                latitude = Double.parseDouble(latitudeStr);

                                if (latitude < -90 || latitude > 90 || (("".equals(latitudeStr)))) {
                                    JOptionPane.showMessageDialog(null, "Por favor adicione um valor entre -90º e 90º!");
                                } else
                                    controlo = true;
                            }
                            //reset à variavel controlo
                            controlo = false;

                            //Longitude
                            while (!controlo) {
                                String longitudeString = JOptionPane.showInputDialog("Longitude:");
                                if (longitudeString == null) {
                                    return;
                                }
                                longitude = Double.parseDouble(longitudeString);
                                if (longitude < -180 || longitude > 180 || "".equals(longitudeString)) {
                                    JOptionPane.showMessageDialog(null, "Por favor adicione um valor entre -180º e 180º!");
                                } else
                                    controlo = true;
                            }
                            emp.setGps(new Coordenadas(latitude, longitude));
                            gestao.atualizaFicheiros(listaEmpresas);
                            tituloLocalizacao.setText(String.valueOf(emp.getGps().toString()));
                        }
                    }
                }

            }

        }
    }
    /**
     * Inner Class AddEmpresaMenuButtonListener, implementa ActionListener
     */
    private class AddEmpresaMenuButtonListener implements ActionListener {
        /**
         * Metodo para criar um objeto do tipo empresa e adicioná-lo ao ArrayList que contém todas as empresas.
         * Funciona pedindo ao utilizador para indicar a categoria da empresa que quer adcionar, depois conforme a resposta
         * pede para o utilizador inserir os vários atributos da categoria selecionada, termina criando um objeto da categoria
         * selecionada, com os atributos introduzidos pelo utilizador.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] arrayCategorias = {"Frutaria", "Mercado", "Café", "Pastelaria", "Restaurante fast-food", "Restaurante local"};
            String[] arrayDistritos = {"Aveiro", "Beja", "Braga", "Bragança", "Castelo Branco", "Coimbra", "Évora", "Faro", "Guarda", "Leiria", "Lisboa", "Portalegre", "Porto", "Santarém", "Setúbal", "Viana do Castelo", "Vila Real", "Viseu"};
            String[] arrayMercadoTipo = {"mini", "super", "hiper"};
            boolean controlo = false; //variavel de controlo para confirmar que o input é valido
            //caixa de texto para selecionar a categotia
            String categoria = (String) JOptionPane.showInputDialog(null, "Categoria", "Input Dialog", JOptionPane.PLAIN_MESSAGE, null, arrayCategorias, arrayCategorias[0]);
            if (categoria == null) {
                return;
            }

            //Atributos Gerais

            //nome
            String nome = JOptionPane.showInputDialog("Nome da empresa:");
            if (nome != null && ("".equals(nome))) {
                JOptionPane.showMessageDialog(null, "Nome de empresa inválido.\n" + "Recomeçe o processo de criação de uma nova empresa.");
                return;
            }
            //distrto
            String distrito = (String) JOptionPane.showInputDialog(null, "Distrito", "Input Dialog", JOptionPane.PLAIN_MESSAGE, null, arrayDistritos, arrayDistritos[0]);
            if (distrito == null) {
                return;
            }
            DefaultListModel<String> novosValoresDaLista = new DefaultListModel<String>();

            //Coordenadas
            //Latitude
            double latitude = 0;
            while (!controlo) {
                String latitudeString = JOptionPane.showInputDialog("Latitude:");
                if (latitudeString == null) {
                    return;
                }
                latitude = Double.parseDouble(latitudeString);
                if (latitude < -90 || latitude > 90 || "".equals(latitudeString)) {
                    JOptionPane.showMessageDialog(null, "Por favor adicione um valor entre -90º e 90º!");
                }
                controlo = true;

            }
            controlo = false;  //reset à variavel controlo

            //Longitude
            double longitude = 0;
            while (!controlo) {
                String longitudeString = JOptionPane.showInputDialog("Longitude:");
                if (longitudeString == null) {
                    return;
                }
                longitude = Double.parseDouble(longitudeString);
                if (longitude < -180 || longitude > 180 || "".equals(longitudeString)) {
                    JOptionPane.showMessageDialog(null, "Por favor adicione um valor entre -180º e 180º!");
                }
                controlo = true;
            }
            //reset à variavel controlo
            controlo = false;
            //criação da variavel coordenada para a nova empresa a criar
            Coordenadas coordenada = new Coordenadas(latitude, longitude);

            //Atributos Especificos

            switch (categoria) {
                case "Frutaria":
                    //numero de produtos
                    int numProdutos = 0;
                    while (!controlo) {
                        try {
                            String numProdutosString = JOptionPane.showInputDialog("Número de produtos:");
                            if (numProdutosString == null) {
                                return;
                            }
                            numProdutos = Integer.parseInt(numProdutosString);
                            if (numProdutos <= 0 || "".equals(numProdutosString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //valor medio da faturacao anual por produto
                    int valorMedFaturacaoAnualProduto = 0;
                    while (!controlo) {
                        try {
                            String valorMedFaturacaoAnualProdutoString = JOptionPane.showInputDialog("Valor médio da faturação anual por produto");
                            if (valorMedFaturacaoAnualProdutoString == null) {
                                return;
                            }
                            valorMedFaturacaoAnualProduto = Integer.parseInt(valorMedFaturacaoAnualProdutoString);
                            if (valorMedFaturacaoAnualProduto <= 0 || "".equals(valorMedFaturacaoAnualProdutoString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //Custo da limpeza
                    int custoLimpeza = 0;
                    while (!controlo) {
                        try {
                            String custoLimpezaString = JOptionPane.showInputDialog("Custo Anual de limpeza");
                            if (custoLimpezaString == null) {
                                return;
                            }
                            custoLimpeza = Integer.parseInt(custoLimpezaString);
                            if (custoLimpeza <= 0 || "".equals(custoLimpezaString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;
                    Frutaria novaFrutaria = new Frutaria(nome, distrito, coordenada, custoLimpeza, numProdutos, valorMedFaturacaoAnualProduto);
                    gestao.addEmpresa(novaFrutaria);

                    //atualizar a lista do menu
                    novosValoresDaLista = new DefaultListModel<String>();
                    for (Empresas emp : gestao.getListaEmpresa()) {
                        novosValoresDaLista.addElement(emp.getNome());
                    }
                    lista.setModel(novosValoresDaLista);
                    break;

                case "Mercado":
                    //String nome, String distrito, Coordenada localizacaoGPS, String mercadoTipo, double areaCorredores, double valorMedFaturacaoAnualPorMetroQuadrado, double custoAnualLimpeza
                    String mercadoTipo = (String) JOptionPane.showInputDialog(null, "Escolha o tipo de mercado", "Tipo de Mercado", JOptionPane.PLAIN_MESSAGE, null, arrayMercadoTipo, arrayMercadoTipo[0]);
                    if (mercadoTipo == null) {
                        return;
                    }


                    //areaCorredores
                    int areaCorredores = 0;
                    while (!controlo) {
                        try {
                            String areaCorredoresString = JOptionPane.showInputDialog("Área dos corredores do mercado em m2");
                            if (areaCorredoresString == null) {
                                return;
                            }
                            areaCorredores = Integer.parseInt(areaCorredoresString);
                            if (areaCorredores <= 0 || "".equals(areaCorredoresString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //valorMedFaturacaoAnualPorMetroQuadrado
                    int valorMedFaturacaoAnualPorMetroQuadrado = 0;
                    while (!controlo) {
                        try {
                            String valorMedFaturacaoAnualPorMetroQuadradoString = JOptionPane.showInputDialog("Valor médio faturado anualmente por metro quadrado");
                            if (valorMedFaturacaoAnualPorMetroQuadradoString == null) {
                                return;
                            }
                            valorMedFaturacaoAnualPorMetroQuadrado = Integer.parseInt(valorMedFaturacaoAnualPorMetroQuadradoString);
                            if (valorMedFaturacaoAnualPorMetroQuadrado <= 0 || "".equals(valorMedFaturacaoAnualPorMetroQuadradoString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //Custo da limpeza do mercado
                    int custoLimpezaMercado = 0;
                    while (!controlo) {
                        try {
                            String custoLimpezaMercadoString = JOptionPane.showInputDialog("Custo Anual de limpeza\"");
                            if (custoLimpezaMercadoString == null) {
                                return;
                            }
                            custoLimpezaMercado = Integer.parseInt(custoLimpezaMercadoString);
                            if (custoLimpezaMercado <= 0 || "".equals(custoLimpezaMercadoString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;
                    Mercado novoMercado = new Mercado(nome, distrito, coordenada, custoLimpezaMercado, mercadoTipo, areaCorredores, valorMedFaturacaoAnualPorMetroQuadrado);
                    gestao.addEmpresa(novoMercado);

                    //atualizar a lista do menu
                    novosValoresDaLista = new DefaultListModel<String>();
                    for (Empresas emp : gestao.getListaEmpresa()) {
                        novosValoresDaLista.addElement(emp.getNome());
                    }
                    lista.setModel(novosValoresDaLista);
                    break;
                case "Café":
                    //numEmpregMesa
                    int numEmpregMesa = 0;
                    while (!controlo) {
                        try {
                            String numEmpregMesaString = JOptionPane.showInputDialog("Número de empregados de mesa");
                            if (numEmpregMesaString == null) {
                                return;
                            }
                            numEmpregMesa = Integer.parseInt(numEmpregMesaString);
                            if (numEmpregMesa <= 0 || "".equals(numEmpregMesaString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //custoSalMedioAnual
                    int custoSalMedioAnual = 0;
                    while (!controlo) {
                        try {
                            String custoSalMedioAnualString = JOptionPane.showInputDialog("Custo do salário médio anual dos emrpegados");
                            if (custoSalMedioAnualString == null) {
                                return;
                            }
                            custoSalMedioAnual = Integer.parseInt(custoSalMedioAnualString);
                            if (custoSalMedioAnual <= 0 || "".equals(custoSalMedioAnualString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numMedClientDiario
                    int numMedClientDiario = 0;
                    while (!controlo) {
                        try {
                            String numMedClientDiarioString = JOptionPane.showInputDialog("Número médio de clientes diário");
                            if (numMedClientDiarioString == null) {
                                return;
                            }
                            numMedClientDiario = Integer.parseInt(numMedClientDiarioString);
                            if (numMedClientDiario <= 0 || "".equals(numMedClientDiarioString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numMedCafeDiario
                    int numMedCafeDiario = 0;
                    while (!controlo) {
                        try {
                            String numMedCafeDiarioString = JOptionPane.showInputDialog("Número médios de cafés vendidos diariamente");
                            if (numMedCafeDiarioString == null) {
                                return;
                            }
                            numMedCafeDiario = Integer.parseInt(numMedCafeDiarioString);
                            if (numMedCafeDiario <= 0 || "".equals(numMedCafeDiarioString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo e inteiro!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //valorMedFaturadoAnualCafePorDia
                    int valorMedFaturadoAnualCafePorDia = 0;
                    while (!controlo) {
                        try {
                            String valorMedFaturadoAnualCafePorDiaString = JOptionPane.showInputDialog("Valor médio faturado anualmente de café por dia");
                            if (valorMedFaturadoAnualCafePorDiaString == null) {
                                return;
                            }
                            valorMedFaturadoAnualCafePorDia = Integer.parseInt(valorMedFaturadoAnualCafePorDiaString);
                            if (valorMedFaturadoAnualCafePorDia <= 0 || "".equals(valorMedFaturadoAnualCafePorDiaString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    Cafe novoCafe = new Cafe(nome, distrito, coordenada, custoSalMedioAnual, numEmpregMesa, numMedClientDiario, numMedCafeDiario, valorMedFaturadoAnualCafePorDia);
                    gestao.addEmpresa(novoCafe);

                    //atualizar a lista do menu
                    novosValoresDaLista = new DefaultListModel<String>();
                    for (Empresas emp : gestao.getListaEmpresa()) {
                        novosValoresDaLista.addElement(emp.getNome());
                    }
                    lista.setModel(novosValoresDaLista);
                    break;
                case "Pastelaria":
                    //numEmpregMesa
                    int numEmpregMesaP = 0;
                    while (!controlo) {
                        try {
                            String numEmpregMesaPString = JOptionPane.showInputDialog("Número de empregados de mesa");
                            if (numEmpregMesaPString == null) {
                                return;
                            }
                            numEmpregMesaP = Integer.parseInt(numEmpregMesaPString);
                            if (numEmpregMesaP <= 0 || "".equals(numEmpregMesaPString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //custoSalMedioAnualP
                    int custoSalMedioAnualP = 0;
                    while (!controlo) {
                        try {
                            String custoSalMedioAnualPString = JOptionPane.showInputDialog("Custo do salário médio anual dos empregados");
                            if (custoSalMedioAnualPString == null) {
                                return;
                            }
                            custoSalMedioAnualP = Integer.parseInt(custoSalMedioAnualPString);
                            if (custoSalMedioAnualP <= 0 || "".equals(custoSalMedioAnualPString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numMedClientDiario
                    int numMedClientDiarioP = 0;
                    while (!controlo) {
                        try {
                            String numMedClientDiarioPString = JOptionPane.showInputDialog("Número médio de clientes diário");
                            if (numMedClientDiarioPString == null) {
                                return;
                            }
                            numMedClientDiarioP = Integer.parseInt(numMedClientDiarioPString);
                            if (numMedClientDiarioP <= 0 || "".equals(numMedClientDiarioPString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numMedBoloDiarioP
                    int numMedBoloDiarioP = 0;
                    while (!controlo) {
                        try {
                            String numMedBoloDiarioPString = JOptionPane.showInputDialog("Número médio de bolos vendidos diariamente");
                            if (numMedBoloDiarioPString == null) {
                                return;
                            }
                            numMedBoloDiarioP = Integer.parseInt(numMedBoloDiarioPString);
                            if (numMedBoloDiarioP <= 0 || "".equals(numMedBoloDiarioPString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo e inteiro!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //valorMedFaturadoAnualBoloPorDia
                    int valorMedFaturadoAnualBoloPorDia = 0;
                    while (!controlo) {
                        try {
                            String valorMedFaturadoAnualBoloPorDiaString = JOptionPane.showInputDialog("Valor médio faturado anualmente de bolo por dia");
                            if (valorMedFaturadoAnualBoloPorDiaString == null) {
                                return;
                            }
                            valorMedFaturadoAnualBoloPorDia = Integer.parseInt(valorMedFaturadoAnualBoloPorDiaString);
                            if (valorMedFaturadoAnualBoloPorDia <= 0 || "".equals(valorMedFaturadoAnualBoloPorDiaString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }

                    Pastelaria novaPastelaria = new Pastelaria(nome, distrito, coordenada, custoSalMedioAnualP, numEmpregMesaP, numMedClientDiarioP, numMedBoloDiarioP, valorMedFaturadoAnualBoloPorDia);
                    gestao.addEmpresa(novaPastelaria);

                    //atualizar a lista do menu
                    novosValoresDaLista = new DefaultListModel<String>();
                    for (Empresas emp : gestao.getListaEmpresa()) {
                        novosValoresDaLista.addElement(emp.getNome());
                    }
                    lista.setModel(novosValoresDaLista);
                    break;
                case "Restaurante fast-food":
                    //numEmpregMesaR
                    int numEmpregMesaR = 0;
                    while (!controlo) {
                        try {
                            String numEmpregMesaRString = JOptionPane.showInputDialog("Número de empregados de mesa");
                            if (numEmpregMesaRString == null) {
                                return;
                            }
                            numEmpregMesaR = Integer.parseInt(numEmpregMesaRString);
                            if (numEmpregMesaR <= 0 || "".equals(numEmpregMesaRString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo e inteiro!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //custoSalMedioAnualR
                    int custoSalMedioAnualR = 0;
                    while (!controlo) {
                        try {
                            String custoSalMedioAnualRString = JOptionPane.showInputDialog("Custo do salário médio anual dos empregados");
                            if (custoSalMedioAnualRString == null) {
                                return;
                            }
                            custoSalMedioAnualR = Integer.parseInt(custoSalMedioAnualRString);
                            if (custoSalMedioAnualR <= 0 || "".equals(custoSalMedioAnualRString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numMedClientDiarioR
                    int numMedClientDiarioR = 0;
                    while (!controlo) {
                        try {
                            String numMedClientDiarioRString = JOptionPane.showInputDialog("Número médio de clientes diário");
                            if (numMedClientDiarioRString == null) {
                                return;
                            }
                            numMedClientDiarioR = Integer.parseInt(numMedClientDiarioRString);
                            if (numMedClientDiarioR <= 0 || "".equals(numMedClientDiarioRString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numDiasFuncPorAno
                    int numDiasFuncPorAno = 0;
                    while (!controlo) {
                        try {
                            String numDiasFuncPorAnoString = JOptionPane.showInputDialog("Número de dias de funcionamento por ano");
                            if (numDiasFuncPorAnoString == null) {
                                return;
                            }
                            numDiasFuncPorAno = Integer.parseInt(numDiasFuncPorAnoString);
                            if (numDiasFuncPorAno <= 0 || "".equals(numDiasFuncPorAnoString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numMesasInterior
                    int numMesasInteriorFF = 0;
                    while (!controlo) {
                        try {
                            String numMesasInteriorFFString = JOptionPane.showInputDialog("Número de mesas interiores");
                            if (numMesasInteriorFFString == null) {
                                return;
                            }
                            numMesasInteriorFF = Integer.parseInt(numMesasInteriorFFString);
                            if (numMesasInteriorFF <= 0 || "".equals(numMesasInteriorFFString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //valorMedioFaturacaoMesaPorDiaFF
                    int valorMedioFaturacaoMesaPorDiaFF = 0;
                    while (!controlo) {
                        try {
                            String valorMedioFaturacaoMesaPorDiaFFString = JOptionPane.showInputDialog("Valor médio faturado por mesa por dia");
                            if (valorMedioFaturacaoMesaPorDiaFFString == null) {
                                return;
                            }
                            valorMedioFaturacaoMesaPorDiaFF = Integer.parseInt(valorMedioFaturacaoMesaPorDiaFFString);
                            if (valorMedioFaturacaoMesaPorDiaFF <= 0 || "".equals(valorMedioFaturacaoMesaPorDiaFFString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numMedDiarioClientesDT
                    int numMedDiarioClientesDT = 0;
                    while (!controlo) {
                        try {
                            String numMedDiarioClientesDTString = JOptionPane.showInputDialog("Número médio diário de clientes Drive Thru");
                            if (numMedDiarioClientesDTString == null) {
                                return;
                            }
                            numMedDiarioClientesDT = Integer.parseInt(numMedDiarioClientesDTString);
                            if (numMedDiarioClientesDT <= 0 || "".equals(numMedDiarioClientesDTString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo e inteiro!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //valorMedDiarioFaturacaoClienteDT
                    int valorMedDiarioFaturacaoClienteDT = 0;
                    while (!controlo) {
                        try {
                            String valorMedDiarioFaturacaoClienteDTString = JOptionPane.showInputDialog("Valor médio diário de faturação por cada cliente Drive Thru");
                            if (valorMedDiarioFaturacaoClienteDTString == null) {
                                return;
                            }
                            valorMedDiarioFaturacaoClienteDT = Integer.parseInt(valorMedDiarioFaturacaoClienteDTString);
                            if (valorMedDiarioFaturacaoClienteDT <= 0 || "".equals(valorMedDiarioFaturacaoClienteDTString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo e inteiro!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    FastFood novoFF = new FastFood(nome, distrito, coordenada, custoSalMedioAnualR, numEmpregMesaR, numMedClientDiarioR, numDiasFuncPorAno, numMesasInteriorFF, numMedDiarioClientesDT, valorMedioFaturacaoMesaPorDiaFF, valorMedDiarioFaturacaoClienteDT);
                    gestao.addEmpresa(novoFF);

                    //atualizar a lista do menu
                    novosValoresDaLista = new DefaultListModel<String>();
                    for (Empresas emp : gestao.getListaEmpresa()) {
                        novosValoresDaLista.addElement(emp.getNome());
                    }
                    lista.setModel(novosValoresDaLista);
                    break;
                case "Restaurante local":
                    //numEmpregMesaL
                    int numEmpregMesaL = 0;
                    while (!controlo) {
                        try {
                            String numEmpregMesaLString = JOptionPane.showInputDialog("Número de empregados de mesa");
                            if (numEmpregMesaLString == null) {
                                return;
                            }
                            numEmpregMesaL = Integer.parseInt(numEmpregMesaLString);
                            if (numEmpregMesaL <= 0 || "".equals(numEmpregMesaLString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo e inteiro!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //custoSalMedioAnualL
                    int custoSalMedioAnualL = 0;
                    while (!controlo) {
                        try {
                            String custoSalMedioAnualLString = JOptionPane.showInputDialog("Custo do salário médio anual dos empregados");
                            if (custoSalMedioAnualLString == null) {
                                return;
                            }
                            custoSalMedioAnualL = Integer.parseInt(custoSalMedioAnualLString);
                            if (custoSalMedioAnualL <= 0 || "".equals(custoSalMedioAnualLString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numMedClientDiarioL
                    int numMedClientDiarioL = 0;
                    while (!controlo) {
                        try {
                            String numMedClientDiarioLString = JOptionPane.showInputDialog("Número médio de clientes diário");
                            if (numMedClientDiarioLString == null) {
                                return;
                            }
                            numMedClientDiarioL = Integer.parseInt(numMedClientDiarioLString);
                            if (numMedClientDiarioL <= 0 || "".equals(numMedClientDiarioLString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numDiasFuncPorAnoL
                    int numDiasFuncPorAnoL = 0;
                    while (!controlo) {
                        try {
                            String numDiasFuncPorAnoLString = JOptionPane.showInputDialog("Número de dias de funcionamento por ano");
                            if (numDiasFuncPorAnoLString == null) {
                                return;
                            }
                            numDiasFuncPorAnoL = Integer.parseInt(numDiasFuncPorAnoLString);
                            if (numDiasFuncPorAnoL <= 0 || "".equals(numDiasFuncPorAnoLString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numMesasInteriorLL
                    int numMesasInteriorLL = 0;
                    while (!controlo) {
                        try {
                            String numMesasInteriorLLString = JOptionPane.showInputDialog("Número de mesas interiores");
                            if (numMesasInteriorLLString == null) {
                                return;
                            }
                            numMesasInteriorLL = Integer.parseInt(numMesasInteriorLLString);
                            if (numMesasInteriorLL <= 0 || "".equals(numMesasInteriorLLString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //numMesasEsplanada
                    int numMesasEsplanada = 0;
                    while (!controlo) {
                        try {
                            String numMesasEsplanadaString = JOptionPane.showInputDialog("Número de mesas interiores");
                            if (numMesasEsplanadaString == null) {
                                return;
                            }
                            numMesasEsplanada = Integer.parseInt(numMesasEsplanadaString);
                            if (numMesasEsplanada <= 0 || "".equals(numMesasEsplanadaString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //custoLicencaAnualPorMesaEsplanada
                    int custoLicencaAnualPorMesaEsplanada = 0;
                    while (!controlo) {
                        try {
                            String custoLicencaAnualPorMesaEsplanadaString = JOptionPane.showInputDialog("Custo da licença anual por mesa de esplanada");
                            if (custoLicencaAnualPorMesaEsplanadaString == null) {
                                return;
                            }
                            custoLicencaAnualPorMesaEsplanada = Integer.parseInt(custoLicencaAnualPorMesaEsplanadaString);
                            if (custoLicencaAnualPorMesaEsplanada <= 0 || "".equals(custoLicencaAnualPorMesaEsplanadaString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }
                    //reset à variavel controlo
                    controlo = false;

                    //valorMedFaturacaoMesaPorDiaL
                    int valorMedFaturacaoMesaPorDiaL = 0;
                    while (!controlo) {
                        try {
                            String valorMedFaturacaoMesaPorDiaLString = JOptionPane.showInputDialog("Valor médio faturado por mesa por dia");
                            if (valorMedFaturacaoMesaPorDiaLString == null) {
                                return;
                            }
                            valorMedFaturacaoMesaPorDiaL = Integer.parseInt(valorMedFaturacaoMesaPorDiaLString);
                            if (valorMedFaturacaoMesaPorDiaL <= 0 || "".equals(valorMedFaturacaoMesaPorDiaLString)) {
                                throw new IllegalArgumentException("Por favor insira um número válido.");
                            }
                            controlo = true;
                        } catch (IllegalArgumentException s) {
                            JOptionPane.showMessageDialog(null, "Por favor adicione um número positivo!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Por favor insira um número válido!");
                        }
                    }

                    Locais novoLocal = new Locais(nome, distrito, coordenada, custoSalMedioAnualL, numEmpregMesaL, numMedClientDiarioL, numDiasFuncPorAnoL, numMesasInteriorLL, numMesasEsplanada, custoLicencaAnualPorMesaEsplanada, valorMedFaturacaoMesaPorDiaL);
                    gestao.addEmpresa(novoLocal);
                    break;
            }
            //atualizar a lista do menu
            novosValoresDaLista = new DefaultListModel<String>();
            for (Empresas emp : gestao.getListaEmpresa()) {
                novosValoresDaLista.addElement(emp.getNome());
            }
            lista.setModel(novosValoresDaLista);

        }

    }


    /**
     * Inner Class RemoverEmpresaButtonListener, implementa ActionListener
     */
    private class RemoverEmpresaButtonListener implements ActionListener {
        /**
         * Metodo que remove a empresa selecionada
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {
            String nome = lista.getSelectedValue();
            if (lista.getSelectedValue() == null) {
                return;
            }
            //mensagem de dialogo para confirmação da eliminação
            int result = JOptionPane.showConfirmDialog(null, "Eliminar a empresa " + nome + "\nConfirmar?", "Confirmação de eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            //se clicar em Yes entao econtra e apaga a empresa
            if (result == JOptionPane.YES_OPTION) {
                for (Empresas a : gestao.getListaEmpresa()) {
                    if (Objects.equals(nome, a.getNome())) {
                        gestao.removeEmpresa(a);
                        break;
                    }
                }
                DefaultListModel<String> novosValoresDaLista = new DefaultListModel<String>(); //atualizar a lista se a empresa eliminada
                for (Empresas a : gestao.getListaEmpresa()) {
                    novosValoresDaLista.addElement(a.getNome());
                }
                lista.setModel(novosValoresDaLista);
            }

        }
    }
}
