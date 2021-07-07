/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;
import bd.beans.Entrada;
import bd.beans.Fornecedor;
import bd.beans.Funcionario;
import bd.beans.Produto;
import bd.beans.Saida;
import bd.dao.EntradaDAO;
import bd.dao.FornecedorDAO;
import bd.dao.FuncionarioDAO;
import bd.dao.ProdutoDAO;
import bd.dao.SaidaDAO;
import java.awt.Desktop;
import main.LoginUsuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * Autores: Lucas Lima e Herbame Boavida
 * <p> Data: 13/05/20202</p>
 * <p> Tela inicia para navegação no Menu principal<br>
 * Aqui ficará os Painéis a serem tornados visíceis dependendo do tipo do usuário</p>
 */
public class frmTelaPrincipal extends javax.swing.JFrame {

    private boolean frmTelaPrincipal;
    public frmTelaPrincipal() {
        initComponents();
        pedidosCancelados = false;
        avisoProdutoAbaixoEstoqueMinimo = false;
        produto = new ProdutoDAO();
        fornecedor = new FornecedorDAO();
        funcionario = new FuncionarioDAO();
        entrada = new EntradaDAO();
        saida = new SaidaDAO();
        this.verificaEstoque();
        this.controleDeAcesso();
        jdpEntradaProduto.setVisible(false);
        jdpSaidaProduto.setVisible(false);
        jdpRelatorioEntrada.setVisible(false);
        jdpRelatorioSaida.setVisible(false);
        
    }
    private SaidaDAO saida;
    private EntradaDAO entrada;
    private ProdutoDAO produto;
    private FornecedorDAO fornecedor;
    private FuncionarioDAO funcionario;
    private boolean pedidosCancelados;
    private boolean produtoSelecionadoRelatorio;
    private boolean produtoSelecionadoRelatorioSaida;
    private boolean avisoProdutoAbaixoEstoqueMinimo;
    
    // variável que vai receber o arquivo da NF selecionado na entrada do produto
    private File arquivo;
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 27/09/2020</p>
     * <p> Este método realiza o preenchimento da tabela de Pedidos</p>
     */
    private String query;
    public void gravaValoresNaTabelaEntrada(String query){
        DefaultTableModel modelo = (DefaultTableModel) tblPedidosProdutos.getModel(); 
        
        // zerando o número de linhas
        modelo.setNumRows(0);
        // inserindo os valores na tabela
        for(Entrada e: entrada.select_PedidosRealizados(query)){
            // pegando os valores do Banco de Dados
            modelo.addRow(new Object[]{
                e.getCodEntrada(),
                produto.nomeProduto(produto, e.getCodProduto()),
                e.getQtdPedido(),
                e.getDataPedido(),
                fornecedor.nomeFornecedor(fornecedor, e.getCodFornecedor()),
                funcionario.nomeFuncionario(funcionario, e.getCodFuncionarioPedido())
            });
        }
    }
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 27/09/2020</p>
     * <p> Este método realiza o preenchimento da tabela de Pedidos</p>
     */
    public void gravaValoresNaTabelaSaida(String query){
        DefaultTableModel modelo = (DefaultTableModel) tblProdutosDisponiveis.getModel(); 
        
        // zerando o número de linhas
        modelo.setNumRows(0);
        // inserindo os valores na tabela
        for(Produto p: produto.select_ProdutosDisponiveisParaRetirada(query)){
            // pegando os valores do Banco de Dados
            modelo.addRow(new Object[]{
                p.getCodProduto(),
                p.getNome(),
                p.getQtdEstoque(),
                p.getQtdMinima(),
                p.getDescricao()
            });
        }
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 24/10/2020</p>
     * <p> Este método realiza o preenchimento da tabela de Relatorio de entrada</p>
     */
    public void gravaValoresNaTabelaRelatorioEntradas(String query){
        DefaultTableModel modelo = (DefaultTableModel) tblEntradasProdutos.getModel(); 
        
        int qtdTotalLancamento = 0;
        int qtdTotalCancelamento = 0;
        // zerando o número de linhas
        modelo.setNumRows(0);
        // inserindo os valores na tabela
        for(Entrada e: entrada.select_EntradasRealizados(query)){
            System.out.println(e);
            // pegando os valores do Banco de Dados
            modelo.addRow(new Object[]{
                e.getCodEntrada(),
                produto.nomeProduto(produto, e.getCodProduto()),
                e.getDataPedido(),
                e.getQtdPedido(),
                fornecedor.nomeFornecedor(fornecedor, e.getCodFornecedor()),
                funcionario.nomeFuncionario(funcionario, e.getCodFuncionarioPedido()),
                e.getDataEntrada(),
                e.getQtdEntrega(),
                funcionario.nomeFuncionario(funcionario, e.getCodFuncionarioEntrada()),
                e.getCaminhoNF(),
                e.getSituacao()
            });
            if(!e.getSituacao().equals("cancelado")){
                qtdTotalLancamento += 1;
            }else{
                qtdTotalCancelamento += 1;
            }
        }
        
        txtTotalLancamentos.setText(Integer.toString(qtdTotalLancamento));
        txtTotalCancelamentos.setText(Integer.toString(qtdTotalCancelamento));
    }
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 24/10/2020</p>
     * <p> Este método realiza o preenchimento da tabela de Relatorio de saida</p>
     */
    public void gravaValoresNaTabelaRelatorioSaidas(String query){
        DefaultTableModel modelo = (DefaultTableModel) tblRelatorioSaidaProduto.getModel(); 
        ProdutoDAO prodRegistrados;
        int qtdTotalRegistros = 0;
        int qtdTotalRetirado = 0;
        // zerando o número de linhas
        modelo.setNumRows(0);
        // inserindo os valores na tabela
        for(Saida s: saida.select_SaidaRealizadas(query)){
            // pegando os valores do Banco de Dados
            modelo.addRow(new Object[]{
                s.getCodSaida(),
                funcionario.nomeFuncionario(funcionario, s.getCodUsuario()),
                s.getData(),
                produto.nomeProduto(produto, s.getCodProduto()),
                s.getQtdProduto(),
                s.getQtdEstoque(),
                s.getQtdMinima(),
                s.getPreco(),
                s.getDescricao()
            });
            qtdTotalRegistros += 1;
            qtdTotalRetirado += s.getQtdProduto();
        }
        txtTotalRegistros.setText(Integer.toString(qtdTotalRegistros));
        txtTotalRetirado.setText(Integer.toString(qtdTotalRetirado));
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 28/09/2020</p>
     * <p> Método responsáve por fazer o preenchimento dos produtos cadastrados no Banco de Dados</p>
     */
    private void preencherComboBoxProdutos(){
        // recebendo a lista de fornecedores registrados
        cbbProdutosEntrada.removeAllItems();
        cbbProdutoRelatorio.removeAllItems();
        cbbProdutoRelatorioSaida.removeAllItems();
        for(Produto p: produto.select_PesquisaProduto("")){
            cbbProdutosEntrada.addItem(p);
            cbbProdutoRelatorio.addItem(p);
            cbbProdutoRelatorioSaida.addItem(p);
        }
    }
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 28/09/2020</p>
     * <p> Método responsáve por pegar e formatar a data atual do computador.</p>
     */
    private void pegarDataAtual(){
        // pegando a data do computador
        Date data = new Date();
        // criando a formatação para a data
        SimpleDateFormat fomatarData = new SimpleDateFormat("dd-MM-yyyy");
        // passando a data para a o campo de data
        txtDataEntrada.setText(fomatarData.format(data));
        txtDataSaida.setText(fomatarData.format(data));
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 19/10/2020</p>
     * <p> Método responsáve por verificar e informar o usuário quando á produtos abaixo do estoque mínimo.</p>
     */
    private void verificaEstoque(){
        List<Produto> estoque;
        int qtdEstoque;
        estoque = produto.select_PesquisaProduto("WHERE qtdEstoque < qtdMinima");
        qtdEstoque = estoque.size();
        System.out.println(qtdEstoque);
        if(qtdEstoque > 0 && !avisoProdutoAbaixoEstoqueMinimo){
            JOptionPane.showMessageDialog(null, "Existe produtos que estão abaixo do estoque minimo\nVerifique seu estoque");
            avisoProdutoAbaixoEstoqueMinimo = true;
        }
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 19/10/2020</p>
     * <p> Método responsáve por verificar quais funções o usuário vai poder acessar através do seu cargo.</p>
     */
    private void controleDeAcesso(){
        if(LoginUsuario.getCargo().equals("Administrador")){
            mnArquivosFuncionarios.setEnabled(true);
            mnArquivosFornecedores.setEnabled(true);
            mnArquivosProdutos.setEnabled(true);
            mnArquivosNovoFuncionario.setEnabled(true);
            mnArquivosNovoFornecedor.setEnabled(true);
            mnMovimentosPedido.setEnabled(true);
            mnMovimentosEntrada.setEnabled(true);
            mnMovimentosSaida.setEnabled(true);
            mnMovimentosEstoqueMinimo.setEnabled(true);
            mnMovimentosRelatorioEntrada.setEnabled(true);
            mnMovimentosRelatorioSaida.setEnabled(true);
        }else if(LoginUsuario.getCargo().equals("Gerente")){
            mnArquivosFuncionarios.setEnabled(true);
            mnArquivosFornecedores.setEnabled(true);
            mnArquivosProdutos.setEnabled(true);
            mnArquivosNovoFuncionario.setEnabled(true);
            mnArquivosNovoFornecedor.setEnabled(true);
            mnMovimentosPedido.setEnabled(true);
            mnMovimentosEntrada.setEnabled(true);
            mnMovimentosSaida.setEnabled(true);
            mnMovimentosEstoqueMinimo.setEnabled(true);
        }else if(LoginUsuario.getCargo().equals("Funcionario")){
            mnArquivosProdutos.setEnabled(true);
            mnMovimentosEntrada.setEnabled(true);
            mnMovimentosSaida.setEnabled(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpSaidaProduto = new javax.swing.JDesktopPane();
        jifSaidaProduto = new javax.swing.JInternalFrame();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProdutosDisponiveis = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFuncionarioSaida = new javax.swing.JTextField();
        txtQtdEstoqueSaida = new javax.swing.JFormattedTextField();
        txtNomeProdutoSaida = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtQtdRetirar = new javax.swing.JFormattedTextField();
        txtEstoqueMinimo = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JToggleButton();
        btnCancelar = new javax.swing.JToggleButton();
        btnFinalizarRetirada = new javax.swing.JToggleButton();
        txtDataSaida = new javax.swing.JFormattedTextField();
        jdpRelatorioEntrada = new javax.swing.JDesktopPane();
        jifRelatorioEntrada = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCaminhoNF = new javax.swing.JTextField();
        btnAbrirNF = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtFuncionarioPedido = new javax.swing.JTextField();
        txtQtdPedido = new javax.swing.JFormattedTextField();
        txtDataPedido = new javax.swing.JTextField();
        txtStatusPedido = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCodPedido = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        txtQtdLancamento = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        txtDataLancamento = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtFuncionarioLancamento = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEntradasProdutos = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        cbbProdutoRelatorio = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtTotalLancamentos = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTotalCancelamentos = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        btnPesquisarEntradas = new javax.swing.JButton();
        btnCancelarPesquisaEntrada = new javax.swing.JButton();
        btnEntradasLancadas = new javax.swing.JButton();
        btnEntradasCanceladas = new javax.swing.JButton();
        btnTodasEntradas = new javax.swing.JButton();
        btnPesquisarEntradasProduto = new javax.swing.JButton();
        jdpEntradaProduto = new javax.swing.JDesktopPane();
        jifEntradaProdutos = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCaminhoArquivo = new javax.swing.JTextField();
        btnBuscarArquivo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtFuncionarioEntrada = new javax.swing.JTextField();
        btnlancamento = new javax.swing.JButton();
        txtQtdEntregas = new javax.swing.JFormattedTextField();
        btnPesquisarPedido = new javax.swing.JButton();
        btnCancelarPedido = new javax.swing.JButton();
        btnPedidosCancelados = new javax.swing.JButton();
        cbbProdutosEntrada = new javax.swing.JComboBox<>();
        txtDataEntrada = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidosProdutos = new javax.swing.JTable();
        jdpRelatorioSaida = new javax.swing.JDesktopPane();
        jifSaidaProduto1 = new javax.swing.JInternalFrame();
        jPanel11 = new javax.swing.JPanel();
        tblRelatorioSaida = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRelatorioSaidaProduto = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtFuncionarioRetirada = new javax.swing.JTextField();
        txtQtdEstoqueRetirado = new javax.swing.JFormattedTextField();
        txtCodProdutoRetirado = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtQtdRetirada = new javax.swing.JFormattedTextField();
        txtEstoqueMinimoRetirado = new javax.swing.JFormattedTextField();
        jLabel29 = new javax.swing.JLabel();
        txtDataRetirada = new javax.swing.JFormattedTextField();
        jPanel12 = new javax.swing.JPanel();
        cbbProdutoRelatorioSaida = new javax.swing.JComboBox<>();
        txtTotalRegistros = new javax.swing.JFormattedTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtTotalRetirado = new javax.swing.JFormattedTextField();
        jLabel33 = new javax.swing.JLabel();
        btnTodasRetirados = new javax.swing.JButton();
        btnPesquisarRestirada = new javax.swing.JToggleButton();
        btnPesquisarRetirados = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnArquivos = new javax.swing.JMenu();
        mnArquivosFuncionarios = new javax.swing.JMenuItem();
        mnArquivosFornecedores = new javax.swing.JMenuItem();
        mnArquivosProdutos = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnArquivosNovoFuncionario = new javax.swing.JMenuItem();
        mnArquivosNovoFornecedor = new javax.swing.JMenuItem();
        mnMovimentos = new javax.swing.JMenu();
        mnMovimentosPedido = new javax.swing.JMenuItem();
        mnMovimentosEntrada = new javax.swing.JMenuItem();
        mnMovimentosSaida = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnMovimentosEstoqueMinimo = new javax.swing.JMenuItem();
        mnMovimentosRelatorioEntrada = new javax.swing.JMenuItem();
        mnMovimentosRelatorioSaida = new javax.swing.JMenuItem();
        mnMinConta = new javax.swing.JMenu();
        mnMinContaDados = new javax.swing.JMenuItem();
        mnMinContaSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menu Principal");
        setBackground(new java.awt.Color(204, 204, 255));

        jdpSaidaProduto.setBackground(new java.awt.Color(255, 255, 255));

        jifSaidaProduto.setVisible(true);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos Disponíveis para Saída", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        tblProdutosDisponiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codProduto", "Nome", "QtdEstoque", "EstoqueMin", "Descricao"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProdutosDisponiveis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosDisponiveisMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProdutosDisponiveis);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados de Saída de Produto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Produto:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Quntidade em Estoque:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Data de entrada:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Lançado por:");

        txtFuncionarioSaida.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtFuncionarioSaida.setToolTipText("Nome de quem está fazendo o lançamento no sistema");
        txtFuncionarioSaida.setEnabled(false);

        txtQtdEstoqueSaida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQtdEstoqueSaida.setEnabled(false);
        txtQtdEstoqueSaida.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtNomeProdutoSaida.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtNomeProdutoSaida.setEnabled(false);
        txtNomeProdutoSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeProdutoSaidaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Quantidade para retirada:");

        txtQtdRetirar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQtdRetirar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtEstoqueMinimo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtEstoqueMinimo.setEnabled(false);
        txtEstoqueMinimo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Estoque Minimo:");

        btnPesquisar.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnPesquisar.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setToolTipText("Pesquisar funcionário");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(217, 212, 255));
        btnCancelar.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnCancelarrCadastroUsuario.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnFinalizarRetirada.setBackground(new java.awt.Color(217, 212, 255));
        btnFinalizarRetirada.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnFinalizarRetirada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnFinalizarCadastroUsuario.png"))); // NOI18N
        btnFinalizarRetirada.setText("Finalizar");
        btnFinalizarRetirada.setToolTipText("");
        btnFinalizarRetirada.setEnabled(false);
        btnFinalizarRetirada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarRetiradaActionPerformed(evt);
            }
        });

        txtDataSaida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtDataSaida.setEnabled(false);
        txtDataSaida.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(289, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQtdRetirar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNomeProdutoSaida, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(txtQtdEstoqueSaida)
                    .addComponent(jLabel8)
                    .addComponent(txtDataSaida))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12)
                        .addComponent(txtEstoqueMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtFuncionarioSaida)
                        .addComponent(btnFinalizarRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(404, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstoqueMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFuncionarioSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFinalizarRetirada))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeProdutoSaida, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtQtdEstoqueSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQtdRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jifSaidaProdutoLayout = new javax.swing.GroupLayout(jifSaidaProduto.getContentPane());
        jifSaidaProduto.getContentPane().setLayout(jifSaidaProdutoLayout);
        jifSaidaProdutoLayout.setHorizontalGroup(
            jifSaidaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jifSaidaProdutoLayout.setVerticalGroup(
            jifSaidaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifSaidaProdutoLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jdpSaidaProduto.setLayer(jifSaidaProduto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jdpSaidaProdutoLayout = new javax.swing.GroupLayout(jdpSaidaProduto);
        jdpSaidaProduto.setLayout(jdpSaidaProdutoLayout);
        jdpSaidaProdutoLayout.setHorizontalGroup(
            jdpSaidaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jifSaidaProduto)
        );
        jdpSaidaProdutoLayout.setVerticalGroup(
            jdpSaidaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jifSaidaProduto)
        );

        jdpRelatorioEntrada.setBackground(new java.awt.Color(255, 255, 255));
        jdpRelatorioEntrada.setPreferredSize(new java.awt.Dimension(1222, 1422));

        jifRelatorioEntrada.setVisible(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da entrada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Quantidade pedida");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Data de Pedido");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("NF:");

        txtCaminhoNF.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCaminhoNF.setToolTipText("Caminho do arquivo da Nota Fiscal");
        txtCaminhoNF.setEnabled(false);

        btnAbrirNF.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAbrirNF.setText("Abrir Nota Fiscal");
        btnAbrirNF.setEnabled(false);
        btnAbrirNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirNFActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Pedido Por");

        txtFuncionarioPedido.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtFuncionarioPedido.setToolTipText("Nome de quem está fazendo o lançamento no sistema");
        txtFuncionarioPedido.setEnabled(false);

        txtQtdPedido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQtdPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQtdPedido.setEnabled(false);
        txtQtdPedido.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtDataPedido.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDataPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataPedido.setToolTipText("Nome de quem está fazendo o lançamento no sistema");
        txtDataPedido.setEnabled(false);

        txtStatusPedido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtStatusPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStatusPedido.setEnabled(false);
        txtStatusPedido.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Status do Pedido");

        txtCodPedido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCodPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodPedido.setEnabled(false);
        txtCodPedido.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("Cód. Lançamento");
        jLabel21.setEnabled(false);

        txtQtdLancamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQtdLancamento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQtdLancamento.setEnabled(false);
        txtQtdLancamento.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("Quantidade Lançada");

        txtDataLancamento.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDataLancamento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataLancamento.setToolTipText("Nome de quem está fazendo o lançamento no sistema");
        txtDataLancamento.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("Data de Entrada");

        txtFuncionarioLancamento.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtFuncionarioLancamento.setToolTipText("Nome de quem está fazendo o lançamento no sistema");
        txtFuncionarioLancamento.setEnabled(false);

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("Lançado Por");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(txtFuncionarioPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(txtQtdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtStatusPedido)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCaminhoNF))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(txtFuncionarioLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(txtDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtQtdLancamento))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAbrirNF))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuncionarioPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQtdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatusPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuncionarioLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQtdLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCaminhoNF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(btnAbrirNF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entrada de produtos / Produtos cancelados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        tblEntradasProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codEntrada", "Produto", "DataPedido", "QtdPedido", "Fornecedor", "PedidoPor", "DataEntarda", "QtdEntrada", "LançadoPor", "NF", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEntradasProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntradasProdutosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEntradasProdutos);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total por produto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        cbbProdutoRelatorio.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Selecione um produto:");

        txtTotalLancamentos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtTotalLancamentos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalLancamentos.setEnabled(false);
        txtTotalLancamentos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Lançamentos:");

        txtTotalCancelamentos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtTotalCancelamentos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalCancelamentos.setEnabled(false);
        txtTotalCancelamentos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Cancelamentos:");

        btnPesquisarEntradas.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnPesquisarEntradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnMinContaDados.png"))); // NOI18N
        btnPesquisarEntradas.setText("Pesquisar Produto");
        btnPesquisarEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarEntradasActionPerformed(evt);
            }
        });

        btnCancelarPesquisaEntrada.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCancelarPesquisaEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnCancelarrCadastroUsuario.png"))); // NOI18N
        btnCancelarPesquisaEntrada.setText("Cancelar Pesquisa");
        btnCancelarPesquisaEntrada.setEnabled(false);
        btnCancelarPesquisaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPesquisaEntradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addContainerGap(119, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelarPesquisaEntrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPesquisarEntradas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbProdutoRelatorio, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(txtTotalLancamentos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotalCancelamentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(26, 26, 26)))))
                        .addContainerGap())))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(cbbProdutoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalLancamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalCancelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisarEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarPesquisaEntrada)
                .addContainerGap())
        );

        btnEntradasLancadas.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnEntradasLancadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnCAdastroProduto.png"))); // NOI18N
        btnEntradasLancadas.setText("Produtos Lançados");
        btnEntradasLancadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradasLancadasActionPerformed(evt);
            }
        });

        btnEntradasCanceladas.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnEntradasCanceladas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnaApagarProduto.png"))); // NOI18N
        btnEntradasCanceladas.setText("Produtos Cancelados");
        btnEntradasCanceladas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradasCanceladasActionPerformed(evt);
            }
        });

        btnTodasEntradas.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnTodasEntradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnArquivo.png"))); // NOI18N
        btnTodasEntradas.setText("Todos os Registros");
        btnTodasEntradas.setEnabled(false);
        btnTodasEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodasEntradasActionPerformed(evt);
            }
        });

        btnPesquisarEntradasProduto.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnPesquisarEntradasProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnPesquisar.png"))); // NOI18N
        btnPesquisarEntradasProduto.setText("Pesquisar Registros");
        btnPesquisarEntradasProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarEntradasProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEntradasLancadas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEntradasCanceladas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPesquisarEntradasProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTodasEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEntradasLancadas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(btnEntradasCanceladas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(btnPesquisarEntradasProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(btnTodasEntradas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jifRelatorioEntradaLayout = new javax.swing.GroupLayout(jifRelatorioEntrada.getContentPane());
        jifRelatorioEntrada.getContentPane().setLayout(jifRelatorioEntradaLayout);
        jifRelatorioEntradaLayout.setHorizontalGroup(
            jifRelatorioEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jifRelatorioEntradaLayout.setVerticalGroup(
            jifRelatorioEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jdpRelatorioEntrada.setLayer(jifRelatorioEntrada, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jdpRelatorioEntradaLayout = new javax.swing.GroupLayout(jdpRelatorioEntrada);
        jdpRelatorioEntrada.setLayout(jdpRelatorioEntradaLayout);
        jdpRelatorioEntradaLayout.setHorizontalGroup(
            jdpRelatorioEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jifRelatorioEntrada, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jdpRelatorioEntradaLayout.setVerticalGroup(
            jdpRelatorioEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jifRelatorioEntrada, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jdpEntradaProduto.setBackground(new java.awt.Color(255, 255, 255));

        jifEntradaProdutos.setVisible(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados de Entrada de Produto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Produto:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Quantidade:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Data de entrada:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("NF:");

        txtCaminhoArquivo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCaminhoArquivo.setToolTipText("Caminho do arquivo da Nota Fiscal");
        txtCaminhoArquivo.setEnabled(false);

        btnBuscarArquivo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBuscarArquivo.setText("Bucar PDF ou XML");
        btnBuscarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArquivoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Pedido por:");

        txtFuncionarioEntrada.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtFuncionarioEntrada.setToolTipText("Nome de quem está fazendo o lançamento no sistema");
        txtFuncionarioEntrada.setEnabled(false);

        btnlancamento.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnlancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnFinalizarCadastroUsuario.png"))); // NOI18N
        btnlancamento.setText("Lançamento");
        btnlancamento.setEnabled(false);
        btnlancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlancamentoActionPerformed(evt);
            }
        });

        txtQtdEntregas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQtdEntregas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQtdEntregas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnPesquisarPedido.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnPesquisarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnPesquisar.png"))); // NOI18N
        btnPesquisarPedido.setText("Pesquisar Pedido");
        btnPesquisarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarPedidoActionPerformed(evt);
            }
        });

        btnCancelarPedido.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCancelarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnCancelarrCadastroUsuario.png"))); // NOI18N
        btnCancelarPedido.setText("Cancelar Pedido");
        btnCancelarPedido.setEnabled(false);
        btnCancelarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPedidoActionPerformed(evt);
            }
        });

        btnPedidosCancelados.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnPedidosCancelados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnArquivo.png"))); // NOI18N
        btnPedidosCancelados.setText("Cancelados");
        btnPedidosCancelados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosCanceladosActionPerformed(evt);
            }
        });

        cbbProdutosEntrada.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbProdutosEntrada.setEnabled(false);

        txtDataEntrada.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDataEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataEntrada.setToolTipText("Nome de quem está fazendo o lançamento no sistema");
        txtDataEntrada.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(cbbProdutosEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtQtdEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtCaminhoArquivo))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtFuncionarioEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(262, Short.MAX_VALUE)
                .addComponent(btnlancamento)
                .addGap(18, 18, 18)
                .addComponent(btnPesquisarPedido)
                .addGap(18, 18, 18)
                .addComponent(btnCancelarPedido)
                .addGap(18, 18, 18)
                .addComponent(btnPedidosCancelados)
                .addGap(0, 251, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuncionarioEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQtdEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbProdutosEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCaminhoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPesquisarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnlancamento))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelarPedido)
                        .addComponent(btnPedidosCancelados, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedidos em Aberto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        tblPedidosProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodPedido", "Produto", "Quantidade", "DataPedido", "Fornecedor", "PedidoPor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPedidosProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedidosProdutos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jifEntradaProdutosLayout = new javax.swing.GroupLayout(jifEntradaProdutos.getContentPane());
        jifEntradaProdutos.getContentPane().setLayout(jifEntradaProdutosLayout);
        jifEntradaProdutosLayout.setHorizontalGroup(
            jifEntradaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jifEntradaProdutosLayout.setVerticalGroup(
            jifEntradaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jdpEntradaProduto.setLayer(jifEntradaProdutos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jdpEntradaProdutoLayout = new javax.swing.GroupLayout(jdpEntradaProduto);
        jdpEntradaProduto.setLayout(jdpEntradaProdutoLayout);
        jdpEntradaProdutoLayout.setHorizontalGroup(
            jdpEntradaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jifEntradaProdutos)
        );
        jdpEntradaProdutoLayout.setVerticalGroup(
            jdpEntradaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jifEntradaProdutos)
        );

        jdpRelatorioSaida.setBackground(new java.awt.Color(255, 255, 255));

        jifSaidaProduto1.setVisible(true);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        tblRelatorioSaida.setBackground(new java.awt.Color(255, 255, 255));
        tblRelatorioSaida.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro de produtos retirados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        tblRelatorioSaidaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codSaida", "RetiradoPor", "DataSaida", "Produto", "QtdRetirada", "QtdEmEstoque", "EstoqueMínimo", "Preco", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRelatorioSaidaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRelatorioSaidaProdutoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblRelatorioSaidaProduto);

        javax.swing.GroupLayout tblRelatorioSaidaLayout = new javax.swing.GroupLayout(tblRelatorioSaida);
        tblRelatorioSaida.setLayout(tblRelatorioSaidaLayout);
        tblRelatorioSaidaLayout.setHorizontalGroup(
            tblRelatorioSaidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        tblRelatorioSaidaLayout.setVerticalGroup(
            tblRelatorioSaidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados de Saída de Produto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Código do produto:");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Quntidade em Estoque:");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setText("Data de Retirada:");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("Lançado por:");

        txtFuncionarioRetirada.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtFuncionarioRetirada.setToolTipText("Nome de quem está fazendo o lançamento no sistema");
        txtFuncionarioRetirada.setEnabled(false);

        txtQtdEstoqueRetirado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQtdEstoqueRetirado.setEnabled(false);
        txtQtdEstoqueRetirado.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtCodProdutoRetirado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtCodProdutoRetirado.setEnabled(false);

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("Quantidade retirada:");

        txtQtdRetirada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQtdRetirada.setEnabled(false);
        txtQtdRetirada.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtEstoqueMinimoRetirado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtEstoqueMinimoRetirado.setEnabled(false);
        txtEstoqueMinimoRetirado.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setText("Estoque Minimo:");

        txtDataRetirada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtDataRetirada.setEnabled(false);
        txtDataRetirada.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQtdRetirada, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(txtCodProdutoRetirado, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25)
                    .addComponent(txtQtdEstoqueRetirado, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jLabel26)
                    .addComponent(txtDataRetirada))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(txtEstoqueMinimoRetirado, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txtFuncionarioRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstoqueMinimoRetirado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFuncionarioRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtQtdEstoqueRetirado)
                            .addComponent(txtCodProdutoRetirado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQtdRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total por produto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        cbbProdutoRelatorioSaida.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtTotalRegistros.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtTotalRegistros.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalRegistros.setEnabled(false);
        txtTotalRegistros.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setText("Selecione um produto para pesquisa");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setText("Total de registros:");

        txtTotalRetirado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtTotalRetirado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalRetirado.setEnabled(false);
        txtTotalRetirado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setText("Total Retirado:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(77, 77, 77))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbProdutoRelatorioSaida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(txtTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotalRetirado)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbProdutoRelatorioSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalRetirado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnTodasRetirados.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTodasRetirados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnArquivo.png"))); // NOI18N
        btnTodasRetirados.setText("Todos os Registros");
        btnTodasRetirados.setEnabled(false);
        btnTodasRetirados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodasRetiradosActionPerformed(evt);
            }
        });

        btnPesquisarRestirada.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisarRestirada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnPesquisar.png"))); // NOI18N
        btnPesquisarRestirada.setText("Pesquisar Registros");
        btnPesquisarRestirada.setToolTipText("Pesquisar funcionário");
        btnPesquisarRestirada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarRestiradaActionPerformed(evt);
            }
        });

        btnPesquisarRetirados.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisarRetirados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnMinContaDados.png"))); // NOI18N
        btnPesquisarRetirados.setText("Pesquisar Produto");
        btnPesquisarRetirados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarRetiradosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tblRelatorioSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPesquisarRestirada, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTodasRetirados, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisarRetirados, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnPesquisarRestirada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisarRetirados, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTodasRetirados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(tblRelatorioSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jifSaidaProduto1Layout = new javax.swing.GroupLayout(jifSaidaProduto1.getContentPane());
        jifSaidaProduto1.getContentPane().setLayout(jifSaidaProduto1Layout);
        jifSaidaProduto1Layout.setHorizontalGroup(
            jifSaidaProduto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jifSaidaProduto1Layout.setVerticalGroup(
            jifSaidaProduto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifSaidaProduto1Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jdpRelatorioSaida.setLayer(jifSaidaProduto1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jdpRelatorioSaidaLayout = new javax.swing.GroupLayout(jdpRelatorioSaida);
        jdpRelatorioSaida.setLayout(jdpRelatorioSaidaLayout);
        jdpRelatorioSaidaLayout.setHorizontalGroup(
            jdpRelatorioSaidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jifSaidaProduto1)
        );
        jdpRelatorioSaidaLayout.setVerticalGroup(
            jdpRelatorioSaidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jifSaidaProduto1)
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        mnArquivos.setBackground(new java.awt.Color(255, 255, 255));
        mnArquivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnArquivo.png"))); // NOI18N
        mnArquivos.setText("Arquivos");
        mnArquivos.setFocusable(false);
        mnArquivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mnArquivosFuncionarios.setBackground(new java.awt.Color(255, 255, 255));
        mnArquivosFuncionarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnArquivosCliente.png"))); // NOI18N
        mnArquivosFuncionarios.setText("Funcionários");
        mnArquivosFuncionarios.setEnabled(false);
        mnArquivosFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnArquivosFuncionariosActionPerformed(evt);
            }
        });
        mnArquivos.add(mnArquivosFuncionarios);

        mnArquivosFornecedores.setBackground(new java.awt.Color(255, 255, 255));
        mnArquivosFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/fornecedor.png"))); // NOI18N
        mnArquivosFornecedores.setText("Fornecedores");
        mnArquivosFornecedores.setEnabled(false);
        mnArquivosFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnArquivosFornecedoresActionPerformed(evt);
            }
        });
        mnArquivos.add(mnArquivosFornecedores);

        mnArquivosProdutos.setBackground(new java.awt.Color(255, 255, 255));
        mnArquivosProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnArquivosProdutos.png"))); // NOI18N
        mnArquivosProdutos.setText("Produtos");
        mnArquivosProdutos.setEnabled(false);
        mnArquivosProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnArquivosProdutosActionPerformed(evt);
            }
        });
        mnArquivos.add(mnArquivosProdutos);

        jSeparator1.setEnabled(false);
        mnArquivos.add(jSeparator1);

        mnArquivosNovoFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        mnArquivosNovoFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnNovoFuncionario.png"))); // NOI18N
        mnArquivosNovoFuncionario.setText("Novo Funcinário");
        mnArquivosNovoFuncionario.setEnabled(false);
        mnArquivosNovoFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnArquivosNovoFuncionarioActionPerformed(evt);
            }
        });
        mnArquivos.add(mnArquivosNovoFuncionario);

        mnArquivosNovoFornecedor.setBackground(new java.awt.Color(255, 255, 255));
        mnArquivosNovoFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnNovoFornecedor.png"))); // NOI18N
        mnArquivosNovoFornecedor.setText("Novo Fornecedor");
        mnArquivosNovoFornecedor.setEnabled(false);
        mnArquivosNovoFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnArquivosNovoFornecedorActionPerformed(evt);
            }
        });
        mnArquivos.add(mnArquivosNovoFornecedor);

        jMenuBar1.add(mnArquivos);

        mnMovimentos.setBackground(new java.awt.Color(255, 255, 255));
        mnMovimentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnMovimento.png"))); // NOI18N
        mnMovimentos.setText("Movimentos");
        mnMovimentos.setMaximumSize(new java.awt.Dimension(105, 32400));

        mnMovimentosPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/fazerPedido.png"))); // NOI18N
        mnMovimentosPedido.setText("Pedido de Produto");
        mnMovimentosPedido.setEnabled(false);
        mnMovimentosPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMovimentosPedidoActionPerformed(evt);
            }
        });
        mnMovimentos.add(mnMovimentosPedido);

        mnMovimentosEntrada.setBackground(new java.awt.Color(255, 255, 255));
        mnMovimentosEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnMovimentoSaida.png"))); // NOI18N
        mnMovimentosEntrada.setText("Entrada de Produtos");
        mnMovimentosEntrada.setEnabled(false);
        mnMovimentosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMovimentosEntradaActionPerformed(evt);
            }
        });
        mnMovimentos.add(mnMovimentosEntrada);

        mnMovimentosSaida.setBackground(new java.awt.Color(255, 255, 255));
        mnMovimentosSaida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnMovimentoEntrada.png"))); // NOI18N
        mnMovimentosSaida.setText("Saida de Produtos");
        mnMovimentosSaida.setEnabled(false);
        mnMovimentosSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMovimentosSaidaActionPerformed(evt);
            }
        });
        mnMovimentos.add(mnMovimentosSaida);
        mnMovimentos.add(jSeparator2);

        mnMovimentosEstoqueMinimo.setBackground(new java.awt.Color(255, 255, 255));
        mnMovimentosEstoqueMinimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/alerta.png"))); // NOI18N
        mnMovimentosEstoqueMinimo.setText("Estoque Minimo ");
        mnMovimentosEstoqueMinimo.setEnabled(false);
        mnMovimentosEstoqueMinimo.setMaximumSize(new java.awt.Dimension(32700, 32700));
        mnMovimentosEstoqueMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMovimentosEstoqueMinimoActionPerformed(evt);
            }
        });
        mnMovimentos.add(mnMovimentosEstoqueMinimo);

        mnMovimentosRelatorioEntrada.setBackground(new java.awt.Color(255, 255, 255));
        mnMovimentosRelatorioEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnMovimentoRelaEntrada.jpg"))); // NOI18N
        mnMovimentosRelatorioEntrada.setText("Relatorio de Entrada");
        mnMovimentosRelatorioEntrada.setEnabled(false);
        mnMovimentosRelatorioEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMovimentosRelatorioEntradaActionPerformed(evt);
            }
        });
        mnMovimentos.add(mnMovimentosRelatorioEntrada);

        mnMovimentosRelatorioSaida.setBackground(new java.awt.Color(255, 255, 255));
        mnMovimentosRelatorioSaida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnMovimentoRelaSaida.jpg"))); // NOI18N
        mnMovimentosRelatorioSaida.setText("Relatorio de Saida");
        mnMovimentosRelatorioSaida.setEnabled(false);
        mnMovimentosRelatorioSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMovimentosRelatorioSaidaActionPerformed(evt);
            }
        });
        mnMovimentos.add(mnMovimentosRelatorioSaida);

        jMenuBar1.add(mnMovimentos);

        mnMinConta.setBackground(new java.awt.Color(255, 255, 255));
        mnMinConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnMinConta.png"))); // NOI18N
        mnMinConta.setText("Minha Conta");

        mnMinContaDados.setBackground(new java.awt.Color(255, 255, 255));
        mnMinContaDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnMinContaDados.png"))); // NOI18N
        mnMinContaDados.setText("Meus Dados");
        mnMinContaDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMinContaDadosActionPerformed(evt);
            }
        });
        mnMinConta.add(mnMinContaDados);

        mnMinContaSair.setBackground(new java.awt.Color(255, 255, 255));
        mnMinContaSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mnMinContaSair.png"))); // NOI18N
        mnMinContaSair.setText("Sair");
        mnMinContaSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMinContaSairActionPerformed(evt);
            }
        });
        mnMinConta.add(mnMinContaSair);

        jMenuBar1.add(mnMinConta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1257, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jdpEntradaProduto))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jdpRelatorioEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 1274, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jdpSaidaProduto))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jdpRelatorioSaida))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1115, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jdpEntradaProduto)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jdpRelatorioEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jdpSaidaProduto))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jdpRelatorioSaida))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        /**
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 24/10/2020<br>
         *  Menu: Movimento | Submenu: Relatório de Entrada<br>
         *  Finalidade: Mostrar a relação lançamentos e cancelamentos de pedidos</p>
         */
    private void mnMovimentosRelatorioEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMovimentosRelatorioEntradaActionPerformed
        // fechando as outras janelas
        jdpEntradaProduto.setVisible(false);
        jdpSaidaProduto.setVisible(false);
        jdpRelatorioEntrada.setVisible(true);
        jdpRelatorioSaida.setVisible(false);
        // limpando os campos
        txtCodPedido.setText("");
        txtDataPedido.setText("");
        txtQtdPedido.setText("");
        txtFuncionarioPedido.setText("");
        txtDataLancamento.setText("");
        txtQtdLancamento.setText("");
        txtFuncionarioLancamento.setText("");
        txtCaminhoNF.setText("");
        txtStatusPedido.setText("");
        //preenchendo a tabela
        this.gravaValoresNaTabelaRelatorioEntradas("Where situacao != 'pedido'");
        // preenchando o Combo Box de produtos
        this.preencherComboBoxProdutos();
        // setando que nenhum produto foi selecionado para pesquisa ainda
        produtoSelecionadoRelatorio = false;
    }//GEN-LAST:event_mnMovimentosRelatorioEntradaActionPerformed
        /**
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 24/10/2020<br>
         *  Menu: Movimento | Submenu: Relatório de Saida<br>
         *  Finalidade: Mostrar a relação da retirada de produtos</p>
         */
    private void mnMovimentosRelatorioSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMovimentosRelatorioSaidaActionPerformed
        //fehcando as outras janelas
        jdpEntradaProduto.setVisible(false);
        jdpSaidaProduto.setVisible(false);
        jdpRelatorioEntrada.setVisible(false);
        jdpRelatorioSaida.setVisible(true);
        // limpando os camos
        txtCodProdutoRetirado.setText("");
        txtFuncionarioRetirada.setText("");
        txtDataRetirada.setText("");
        txtQtdRetirada.setText("");
        txtQtdEstoqueRetirado.setText("");
        txtEstoqueMinimoRetirado.setText("");
        // preenchendo a tabela
        this.gravaValoresNaTabelaRelatorioSaidas("");
        // preenchando o Combo Box de produtos
        this.preencherComboBoxProdutos();
        // setando que nenhum produto foi selecionado para pesquisa ainda
        produtoSelecionadoRelatorioSaida = false;
    }//GEN-LAST:event_mnMovimentosRelatorioSaidaActionPerformed
        /**
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 15/05/2020<br>
         *  Menu: Minha Conta | Submenu: Sair<br>
         *  Finalidade: Sair do sistema </p>
         */
    private void mnMinContaSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMinContaSairActionPerformed
        // MENU MINHA CONTA - SUBMENU SAIR
        frmLogin login =  new frmLogin();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnMinContaSairActionPerformed
        /**
         * 
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 15/05/2020<br>
         *  Menu: Arquivos | Submenu: Fornecedores<br>
         *  Finalidade: Consultar fornecedores</p>
         */
    private void mnArquivosFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnArquivosFornecedoresActionPerformed
        /// MENU ARQUIVOS | SUBMENU FORNECEDORES
        frmDadosFornecedor frmfornecedor = new frmDadosFornecedor();
        frmfornecedor.setVisible(true);
    }//GEN-LAST:event_mnArquivosFornecedoresActionPerformed
        /**
         * 
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 15/05/2020<br>
         *  Menu: Arquivos | Submenu: Funcionarios<br>
         *  Finalidade: Consultar os funcionários por uma Janela Interna no painel</p>
         */
    private void mnArquivosFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnArquivosFuncionariosActionPerformed
        // MENU ARQUIVOS | SUBMENU FUNCIONARIOS
        frmDadosFuncionario dadosFuncionario = new frmDadosFuncionario();
        dadosFuncionario.setVisible(true);
    }//GEN-LAST:event_mnArquivosFuncionariosActionPerformed
        /**
         * 
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 15/05/2020<br>
         *  Menu: Arquivos | Submenu: Produtos<br>
         *  Finalidade: Abrir a tela para cadastrar, consultar ou apagar produtos</p>
         */
    private void mnArquivosProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnArquivosProdutosActionPerformed
        frmRegistroProduto produtos = new frmRegistroProduto();
        produtos.setVisible(true);
        
    }//GEN-LAST:event_mnArquivosProdutosActionPerformed
        /**
         * 
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 15/05/2020<br>
         *  Menu: Minha Conta | Submenu: Meus Dados<br>
         *  Finalidade: Abrir a tela de Registro de usuário para 
         *  consultar os dados do usuário ou poder alterálos </p>
         */
    private void mnMinContaDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMinContaDadosActionPerformed
        // MENU MINHA CONTA | SUBMENU MEU DADOS
        frmRegistroFuncionario registro = new frmRegistroFuncionario();
        
        // validando e abrindo o registro do usuário
        registro.consultarMeusDados(Integer.parseInt(LoginUsuario.getUsuario()));
        
        // Habilitando o botão Alterar Senha
        registro.getBtnAlterarMeusDados().setEnabled(true);
        // Desabilitando botões
        registro.getBtnFinalizarRegistro().setEnabled(false);
        registro.getBtnCancelarRegistro().setEnabled(false);
    }//GEN-LAST:event_mnMinContaDadosActionPerformed
        
        /**
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 15/05/2020<br>
         *  Menu: Arquivos | Submenu: Novo Funcionário<br>
         *  Finalidade: abrir a tela de Registro de Usuário para novo Cadastro </p>
         */
    private void mnArquivosNovoFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnArquivosNovoFuncionarioActionPerformed
        //  MENU ARQUIVOS | SUBMENU NOVO FUNCIONÁRIO
        frmRegistroFuncionario registro = new frmRegistroFuncionario();
        registro.novoUsuario();
    }//GEN-LAST:event_mnArquivosNovoFuncionarioActionPerformed
        /**
         * 
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 15/05/2020<br>
         *  Menu: Arquivos | Submenu: Novo Fornecedor<br>
         *  Finalidade: Consultar fornecedores</p>
         */
    private void mnArquivosNovoFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnArquivosNovoFornecedorActionPerformed
        // MENU ARQUIVOS | SUBMENU NOVO FORNECEDOR
        frmRegistroFornecedor frmFornecedor = new frmRegistroFornecedor();
        frmFornecedor.novoUsuario();
    }//GEN-LAST:event_mnArquivosNovoFornecedorActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 27/09/2020</p>
     * <p> Botão que abre a tela de pesquisa de arquivos para buscar a Nota Fiscal (PDF ou XML)<br>
    *  OBS: será criado um filtro para a busca </p>
    */
    private void btnBuscarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArquivoActionPerformed
        // BOTÃO BUSCAR ARQUIVO
        // instancia
        JFileChooser buscaArquivo = new JFileChooser();
        FileNameExtensionFilter filtroArquivos = new FileNameExtensionFilter("Arquivos PDF ou  XML", "pdf", "xml");

        // título da tela de busca
        buscaArquivo.setDialogTitle("Buscador de Arquivos");
        //permitindo buscar somente arquivos
        buscaArquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // filtrando os tipos de arquivos
        buscaArquivo.setFileFilter(filtroArquivos);
        // abrindo a tela de busca e pegando retorno
        int retorno = buscaArquivo.showOpenDialog(this);

        // Verificicando se o user selecionou um arquivo
        if(retorno == JFileChooser.APPROVE_OPTION){
            //pegando arquivo selecionado
            arquivo = buscaArquivo.getSelectedFile();
            
            txtCaminhoArquivo.setText(arquivo.getPath());
            btnlancamento.setEnabled(true);
            
            // testando se é possível exibir o arquivo
            try {
                // Exibindo o arquivo Selecionado
                Desktop.getDesktop().open(arquivo);
            } catch (IOException ex) {
                System.out.println("Erro ao exibir o arquivo"+ ex);
            }
        }
    }//GEN-LAST:event_btnBuscarArquivoActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 28/09/2020</p>
     * <p> Botão que realiza a pesquisa de pedidos em abertos ou cancelados</p>
     */
    private void btnPesquisarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarPedidoActionPerformed
        jDialogPesquisaPedidos pesquisaPedido = new jDialogPesquisaPedidos(this, true);
        pesquisaPedido.setVisible(true);
        if(!pedidosCancelados){
            if(pesquisaPedido.isPesquisaNome()){
                Produto p = pesquisaPedido.getCbbProduto();
                query = "WHERE situacao = 'pedido' AND  codProduto LIKE '%"+Integer.toString(p.getCodProduto())+"%'";
                gravaValoresNaTabelaEntrada(query);
            }else if(pesquisaPedido.isPesquisaFornecedor()){
                Fornecedor f = pesquisaPedido.getCbbFornecedor();
                query = "WHERE situacao = 'pedido' AND  codFornecedor LIKE '%"+Integer.toString(f.getCodFornecedor())+"%'";
                gravaValoresNaTabelaEntrada(query);
            }
        }else{
            if(pesquisaPedido.isPesquisaNome()){
                Produto p = pesquisaPedido.getCbbProduto();
                query = "WHERE situacao = 'cancelado' AND  codProduto LIKE '%"+Integer.toString(p.getCodProduto())+"%'";
                gravaValoresNaTabelaEntrada(query);
            }else if(pesquisaPedido.isPesquisaFornecedor()){
                Fornecedor f = pesquisaPedido.getCbbFornecedor();
                query = "WHERE situacao = 'cancelado' AND  codFornecedor LIKE '%"+Integer.toString(f.getCodFornecedor())+"%'";
                gravaValoresNaTabelaEntrada(query);
            }
        }
    }//GEN-LAST:event_btnPesquisarPedidoActionPerformed
    /**
    * Autores: Lucas Lima e Hermane Boavida
    * <p> Data: 15/05/2020<br>
    *  Menu: Movimentos | Submenu: Pedido de Produto<br>
    *  Finalidade: Registrar pedido de produto</p>
    */
    private void mnMovimentosPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMovimentosPedidoActionPerformed
        jDialogPedidoProduto pedido = new jDialogPedidoProduto(this, true);
        pedido.setVisible(true);
        if(pedido.isFezPedido()){
            this.gravaValoresNaTabelaEntrada("WHERE situacao = 'pedido'");
        }
    }//GEN-LAST:event_mnMovimentosPedidoActionPerformed
    /**
    * Autores: Lucas Lima e Hermane Boavida
    * <p> Data: 15/05/2020<br>
    *  Menu: Movimentos | Submenu: Entrada de Produtos<br>
    *  Finalidade: Registrar a entrada de produtos</p>
    */
    private void mnMovimentosEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMovimentosEntradaActionPerformed
        // limpando os campos
        this.preencherComboBoxProdutos();
        cbbProdutosEntrada.setSelectedIndex(0);
        txtQtdEntregas.setText("");
        txtCaminhoArquivo.setText("");
        txtFuncionarioEntrada.setText(funcionario.nomeFuncionario(funcionario, Integer.parseInt(LoginUsuario.getUsuario())));
        // preenchendo o campo de produtos 
        // preenchendo a tabela de pedidos
        this.gravaValoresNaTabelaEntrada("WHERE situacao = 'pedido'");
        // preenchando o campo de data com a data atual
        this.pegarDataAtual();
        this.btnlancamento.setText("Lançamento");
        this.btnlancamento.setEnabled(false);
        this.btnCancelarPedido.setText("Cancelar Pedido");
        this.btnCancelarPedido.setEnabled(false);
        this.pedidosCancelados = false;
        this.btnBuscarArquivo.setEnabled(false);
        // mostrando a tela de entrada de produtos e fechando a saída de produtos
        jdpEntradaProduto.setVisible(true);
        jdpSaidaProduto.setVisible(false);
        jdpRelatorioEntrada.setVisible(false);
        jdpRelatorioSaida.setVisible(false);
    }//GEN-LAST:event_mnMovimentosEntradaActionPerformed
    /**
    * Autores: Lucas Lima e Hermane Boavida
    * <p> Data: 15/05/2020<br>
    *  Menu: Movimentos | Submenu: Saída de Produto<br>
    *  Finalidade: Registrar saída de produto</p>
    */
    private void mnMovimentosSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMovimentosSaidaActionPerformed
        txtNomeProdutoSaida.setText("");
        txtQtdEstoqueSaida.setText("");
        txtEstoqueMinimo.setText("");
        txtQtdRetirar.setText("");
        txtFuncionarioSaida.setText(funcionario.nomeFuncionario(funcionario, Integer.parseInt(LoginUsuario.getUsuario())));
        // preenchendo a tabela de produtos disponíveis
        this.gravaValoresNaTabelaSaida("");
        // preenchando o campo de data com a data atual
        this.pegarDataAtual();
        jdpEntradaProduto.setVisible(false);
        jdpSaidaProduto.setVisible(true);
        jdpRelatorioEntrada.setVisible(false);
        jdpRelatorioSaida.setVisible(false);
    }//GEN-LAST:event_mnMovimentosSaidaActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 27/09/2020</p>
     * <p> Botão que pega os dados da tabela de pedidos para os campos de textos</p>
     */
    private void tblPedidosProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosProdutosMouseClicked
        // transferindo os dados da linha selecionada para os campos
        if(tblPedidosProdutos.getSelectedRow() != -1){
            txtQtdEntregas.setText(tblPedidosProdutos.getValueAt(tblPedidosProdutos.getSelectedRow(), 2).toString());
            txtCaminhoArquivo.setText("");
            int cont = cbbProdutosEntrada.getItemCount()-1;
            String nomeProduto = tblPedidosProdutos.getValueAt(tblPedidosProdutos.getSelectedRow(), 1).toString();
            while (cont >= 0){
                if(nomeProduto.equals(cbbProdutosEntrada.getItemAt(cont).toString())){                    
                    cbbProdutosEntrada.setSelectedIndex(cont);
                    cont = 0;
                }
                cont --;
            }
            this.btnBuscarArquivo.setEnabled(true);
            entrada.setCodEntrada(Integer.parseInt(tblPedidosProdutos.getValueAt(tblPedidosProdutos.getSelectedRow(), 0).toString()));
            if(!pedidosCancelados){
                // Habilitando os botões de lançamento e cancelamento de pedido
                btnCancelarPedido.setEnabled(true);
                btnlancamento.setEnabled(false);
            }else{
                btnCancelarPedido.setEnabled(true);
                btnlancamento.setEnabled(true);
            }
        }
    }//GEN-LAST:event_tblPedidosProdutosMouseClicked
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 27/09/2020</p>
     * <p> Botão realiza o cancelamento de pedidos em aberto</p>
     */
    private void btnCancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPedidoActionPerformed
        // instância
        jDialogAlteraDados alterar = new jDialogAlteraDados(this, true);
        if(!pedidosCancelados){
            alterar.setVisible(true);
            if(alterar.isPermissao()){
                Produto p = (Produto) cbbProdutosEntrada.getSelectedItem();
                entrada.setDataEntrada(txtDataEntrada.getText());
                entrada.setCodFuncionarioEntrada(Integer.parseInt(LoginUsuario.getUsuario()));
                entrada.setQtdEntrega(0);
                entrada.setCaminhoNF("");
                entrada.setSituacao("cancelado");
                entrada.setCodProduto(p.getCodProduto());
                entrada.update_EntradaDeProdutos(false, false);
                this.gravaValoresNaTabelaEntrada("WHERE situacao = 'pedido'");
                txtFuncionarioEntrada.setText(funcionario.nomeFuncionario(funcionario, Integer.parseInt(LoginUsuario.getUsuario())));
                // preenchando o campo de data com a data atual
                this.pegarDataAtual();
            }
        }else{
            this.gravaValoresNaTabelaEntrada("WHERE situacao = 'pedido'");
            this.btnlancamento.setText("Lançamento");
            this.btnCancelarPedido.setText("Cancelar Pedido");
            this.pedidosCancelados = false;
            this.btnBuscarArquivo.setEnabled(false);
            this.btnPedidosCancelados.setEnabled(true);
        }
        // desabilitando os botões de lançamento e cancelamento
        btnlancamento.setEnabled(false);
        btnCancelarPedido.setEnabled(false);
        btnBuscarArquivo.setEnabled(false);
        // limpando os campos
        cbbProdutosEntrada.setSelectedIndex(0);
        txtQtdEntregas.setText("");
        txtCaminhoArquivo.setText("");
        this.pegarDataAtual();
        // preenchendo o campo do nome do usuário
        txtFuncionarioEntrada.setText(funcionario.nomeFuncionario(funcionario, Integer.parseInt(LoginUsuario.getUsuario())));
    }//GEN-LAST:event_btnCancelarPedidoActionPerformed
        /**
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 27/09/2020</p>
         * <p> Botão que faz o lançamento de pedido em abertos</p>
         */
    private void btnlancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlancamentoActionPerformed
        jDialogAlteraDados alterar = new jDialogAlteraDados(this, true);
        if(!pedidosCancelados){
            // montando o nome do arquivo pra cada entrada
            String nomeArquivo = tblPedidosProdutos.getValueAt(tblPedidosProdutos.getSelectedRow(), 1).toString();
            nomeArquivo += "_"+txtDataEntrada.getText();
            // pegando o caminho do arquivo original
            String caminhoDoArquivoOriginal = arquivo.getPath();
            // especificando o novo diretório do arquivo
            String caminhoDoDiretorioDasNF = "C:/Users/LucasLima/Desktop/NFs/"+nomeArquivo+".pdf";
            
            Produto p = (Produto) cbbProdutosEntrada.getSelectedItem();
            entrada.setDataEntrada(txtDataEntrada.getText());
            entrada.setCodFuncionarioEntrada(Integer.parseInt(LoginUsuario.getUsuario()));
            entrada.setQtdEntrega(Integer.parseInt(txtQtdEntregas.getText()));
            entrada.setCaminhoNF(caminhoDoDiretorioDasNF);
            entrada.setSituacao("entregue");
            entrada.setCodProduto(p.getCodProduto());
            entrada.update_EntradaDeProdutos(true, false);
            this.gravaValoresNaTabelaEntrada("WHERE situacao = 'pedido'");
            // limpando os campos
            cbbProdutosEntrada.setSelectedIndex(0);
            txtQtdEntregas.setText("");
            txtFuncionarioEntrada.setText(funcionario.nomeFuncionario(funcionario, Integer.parseInt(LoginUsuario.getUsuario())));
            txtCaminhoArquivo.setText("");

            // desabilitando os botões de lançamento e cancelamento
            btnlancamento.setEnabled(false);
            btnCancelarPedido.setEnabled(false);
            btnBuscarArquivo.setEnabled(false);
            
            try {
                // transferindo o arquivo da Nota Fiscal de entrada para o diretório específico
                // Carregando o caminho do arquivo
                FileInputStream fisFrom = new FileInputStream(caminhoDoArquivoOriginal);
                // Carregando o caminho do repositório de saída
                FileOutputStream fisTo = new FileOutputStream(caminhoDoDiretorioDasNF);
                
                // carregando o arquivo para transferência
                FileChannel fcFrom = fisFrom.getChannel();
                // carregando o diretório para transferência
                FileChannel fcTo = fisTo.getChannel();
                // testando se foi possível fazer a transferência
                if( fcFrom.transferTo(0, fcFrom.size(), fcTo ) == 0L ) {
                    // fechando o caminho do arquivo
                    fcFrom.close();
                    // fechando o caminho do diretório
                    fcTo.close();
                }
                fcFrom.close();
                fcTo.close();
                // deletando o arquivo original
                if( !arquivo.delete() )
                    throw new Exception("NÃO FOI POSSÍVEL EXCLUIR O ARQUIVO");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            // Caso esteja apenas reabrindo o pedido
            alterar.setVisible(true);
            if(alterar.isPermissao()){
                Produto p = (Produto) cbbProdutosEntrada.getSelectedItem();
                entrada.setDataEntrada(txtDataEntrada.getText());
                entrada.setCodFuncionarioEntrada(Integer.parseInt(LoginUsuario.getUsuario()));
                entrada.setQtdEntrega(0);
                entrada.setCaminhoNF("");
                entrada.setSituacao("pedido");
                entrada.setCodProduto(p.getCodProduto());
                entrada.update_EntradaDeProdutos(false,true);
                this.gravaValoresNaTabelaEntrada("WHERE situacao = 'cancelado'");
                // tratamento dos botões
                btnlancamento.setEnabled(false);
                btnCancelarPedido.setEnabled(true);
            }
        }
        // preenchando o campo de data com a data atual
        this.pegarDataAtual();
        txtFuncionarioEntrada.setText(funcionario.nomeFuncionario(funcionario, Integer.parseInt(LoginUsuario.getUsuario())));
    }//GEN-LAST:event_btnlancamentoActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 28/09/2020</p>
     * <p> Botão que mostra na tabela os pedidos cancelados e altera a funcionalidades dos botões</p>
     */
    private void btnPedidosCanceladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosCanceladosActionPerformed
        this.pedidosCancelados = true;
        btnlancamento.setText("Reabrir");
        btnCancelarPedido.setText("Cancelar Alteração");
        btnCancelarPedido.setEnabled(true);
        btnPedidosCancelados.setEnabled(false);
        this.gravaValoresNaTabelaEntrada("WHERE situacao = 'cancelado'");
    }//GEN-LAST:event_btnPedidosCanceladosActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 03/10/2020</p>
     * <p> Botão realiza a pesquisa de produtos disponíveis para retirada</p>
     */
    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        jDialogPesquisaProdutosDisponíveis pesquisaPedido = new jDialogPesquisaProdutosDisponíveis(this, true);
        
        pesquisaPedido.setVisible(true);
        if(pesquisaPedido.isPesquisaNome()){
            Produto p = pesquisaPedido.getCbbProduto();
            query = "WHERE nome LIKE '%"+p.getNome()+"%'";
            this.gravaValoresNaTabelaSaida(query);
        }else if(pesquisaPedido.isPesquisaCodigo()){
            query = "WHERE codProduto LIKE '%"+pesquisaPedido.getTxtCodProduto()+"%'";
            this.gravaValoresNaTabelaSaida(query);
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 03/10/2020</p>
     * <p> Botão que transfere os dados da linha selecionada para os campos</p>
     */
    private void tblProdutosDisponiveisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosDisponiveisMouseClicked
        // transferindo os dados da linha selecionada para os campos
        if(tblProdutosDisponiveis.getSelectedRow() != -1){
            txtNomeProdutoSaida.setText(tblProdutosDisponiveis.getValueAt(tblProdutosDisponiveis.getSelectedRow(), 1).toString());
            txtQtdEstoqueSaida.setText(tblProdutosDisponiveis.getValueAt(tblProdutosDisponiveis.getSelectedRow(), 2).toString());
            txtEstoqueMinimo.setText(tblProdutosDisponiveis.getValueAt(tblProdutosDisponiveis.getSelectedRow(), 3).toString());
            btnCancelar.setEnabled(true);
            btnFinalizarRetirada.setEnabled(true);
        }
    }//GEN-LAST:event_tblProdutosDisponiveisMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        txtNomeProdutoSaida.setText("");
        txtQtdEstoqueSaida.setText("");
        txtEstoqueMinimo.setText("");
        txtQtdRetirar.setText("");
        btnCancelar.setEnabled(false);
        btnFinalizarRetirada.setEnabled(false);
        this.pegarDataAtual();
        // preenchendo o campo do nome do usuário
        txtFuncionarioSaida.setText(funcionario.nomeFuncionario(funcionario, Integer.parseInt(LoginUsuario.getUsuario())));
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnFinalizarRetiradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarRetiradaActionPerformed
        saida.setData(txtDataSaida.getText());
        saida.setQtdProduto(Integer.parseInt(txtQtdRetirar.getText()));
        saida.setCodProduto(Integer.parseInt(tblProdutosDisponiveis.getValueAt(tblProdutosDisponiveis.getSelectedRow(), 0).toString()));
        saida.setCodUsuario(Integer.parseInt(LoginUsuario.getUsuario()));
        saida.setQtdEstoque(Integer.parseInt(txtQtdEstoqueSaida.getText()));
        saida.insert_retirarProdutoEstoque();
        this.gravaValoresNaTabelaSaida("");
        // preenchando o campo de data com a data atual
        this.pegarDataAtual();
        // preenchendo o campo do nome do usuário
        txtFuncionarioSaida.setText(funcionario.nomeFuncionario(funcionario, Integer.parseInt(LoginUsuario.getUsuario())));
        //limpando os campos
        txtNomeProdutoSaida.setText("");
        txtQtdEstoqueSaida.setText("");
        txtEstoqueMinimo.setText("");
        txtQtdRetirar.setText("");
        btnCancelar.setEnabled(false);
        btnFinalizarRetirada.setEnabled(false);
    }//GEN-LAST:event_btnFinalizarRetiradaActionPerformed

    private void txtNomeProdutoSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeProdutoSaidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeProdutoSaidaActionPerformed

    private void mnMovimentosEstoqueMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMovimentosEstoqueMinimoActionPerformed
        frmEstoqueBaixo estoqueBaixo = new frmEstoqueBaixo();
        estoqueBaixo.setVisible(true);
    }//GEN-LAST:event_mnMovimentosEstoqueMinimoActionPerformed

    private void btnAbrirNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirNFActionPerformed
        File arquivoNF = new File(txtCaminhoNF.getText());
        if(arquivoNF.exists()){
            // testando se é possível exibir o arquivo
            try {
                // Exibindo o arquivo Selecionado
                Desktop.getDesktop().open(arquivoNF);
            } catch (IOException ex) {
                System.out.println("Erro ao exibir o arquivo"+ ex);
            }
        }
    }//GEN-LAST:event_btnAbrirNFActionPerformed

    private void btnEntradasLancadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradasLancadasActionPerformed
        if(produtoSelecionadoRelatorio){
            Produto p = (Produto) cbbProdutoRelatorio.getSelectedItem();
            query = "WHERE codProduto = "+Integer.toString(p.getCodProduto())+" AND situacao = 'entregue'";
        }else{
            query = "WHERE situacao = 'entregue'";
        }
        this.gravaValoresNaTabelaRelatorioEntradas(query);
        btnTodasEntradas.setEnabled(true);
        btnCancelarPesquisaEntrada.setEnabled(true);
    }//GEN-LAST:event_btnEntradasLancadasActionPerformed

    private void btnEntradasCanceladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradasCanceladasActionPerformed
        if(produtoSelecionadoRelatorio){ 
            Produto p = (Produto) cbbProdutoRelatorio.getSelectedItem();
            query = "WHERE codProduto = "+Integer.toString(p.getCodProduto())+" AND situacao = 'cancelado'";
        }else{
            query = "WHERE situacao = 'cancelado'";
        }
        this.gravaValoresNaTabelaRelatorioEntradas(query);
        btnTodasEntradas.setEnabled(true);
        btnCancelarPesquisaEntrada.setEnabled(true);
    }//GEN-LAST:event_btnEntradasCanceladasActionPerformed

    private void btnTodasEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodasEntradasActionPerformed
        if(produtoSelecionadoRelatorio){
            Produto p = (Produto) cbbProdutoRelatorio.getSelectedItem();
            query = "WHERE codProduto = "+Integer.toString(p.getCodProduto())+" AND situacao != 'pedido'";
            btnCancelarPesquisaEntrada.setEnabled(true);
        }else{
            query = "Where situacao != 'pedido'";
            btnCancelarPesquisaEntrada.setEnabled(true);
        }
        this.gravaValoresNaTabelaRelatorioEntradas(query);
        btnTodasEntradas.setEnabled(false);
    }//GEN-LAST:event_btnTodasEntradasActionPerformed

    private void tblEntradasProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntradasProdutosMouseClicked
        int cont = cbbProdutoRelatorio.getItemCount()-1;
        String nomeProduto = tblEntradasProdutos.getValueAt(tblEntradasProdutos.getSelectedRow(), 1).toString();
        while (cont >= 0){
            if(nomeProduto.equals(cbbProdutoRelatorio.getItemAt(cont).toString())){                    
                cbbProdutoRelatorio.setSelectedIndex(cont);
                cont = 0;
            }
            cont --;
        }
        
        txtCodPedido.setText(tblEntradasProdutos.getValueAt(tblEntradasProdutos.getSelectedRow(), 0).toString());
        txtDataPedido.setText(tblEntradasProdutos.getValueAt(tblEntradasProdutos.getSelectedRow(), 2).toString());
        txtQtdPedido.setText(tblEntradasProdutos.getValueAt(tblEntradasProdutos.getSelectedRow(), 3).toString());
        txtFuncionarioPedido.setText(tblEntradasProdutos.getValueAt(tblEntradasProdutos.getSelectedRow(), 5).toString());
        txtDataLancamento.setText(tblEntradasProdutos.getValueAt(tblEntradasProdutos.getSelectedRow(), 6).toString());
        txtQtdLancamento.setText(tblEntradasProdutos.getValueAt(tblEntradasProdutos.getSelectedRow(), 7).toString());
        txtFuncionarioLancamento.setText(tblEntradasProdutos.getValueAt(tblEntradasProdutos.getSelectedRow(), 8).toString());
        txtCaminhoNF.setText(tblEntradasProdutos.getValueAt(tblEntradasProdutos.getSelectedRow(), 9).toString());
        txtStatusPedido.setText(tblEntradasProdutos.getValueAt(tblEntradasProdutos.getSelectedRow(), 10).toString());
        if(!txtCaminhoNF.getText().equals("")){
            btnAbrirNF.setEnabled(true);
        }else{
            btnAbrirNF.setEnabled(false);
        }
    }//GEN-LAST:event_tblEntradasProdutosMouseClicked

    private void btnPesquisarEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarEntradasActionPerformed
        Produto p = (Produto) cbbProdutoRelatorio.getSelectedItem();
        System.out.println("Cod produto: "+p.getCodProduto());
        query = "WHERE codProduto = "+Integer.toString(p.getCodProduto()) + " AND userEntrada is not null";
        this.gravaValoresNaTabelaRelatorioEntradas(query);
        btnCancelarPesquisaEntrada.setEnabled(true);
        txtFuncionarioPedido.setText("");
        txtDataPedido.setText("");
        txtQtdPedido.setText("");
        txtStatusPedido.setText("");
        txtFuncionarioLancamento.setText("");
        txtDataLancamento.setText("");
        txtQtdLancamento.setText("");
        txtCodPedido.setText("");
        txtCaminhoNF.setText("");
        btnAbrirNF.setEnabled(false);
        produtoSelecionadoRelatorio = true;
    }//GEN-LAST:event_btnPesquisarEntradasActionPerformed

    private void btnCancelarPesquisaEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPesquisaEntradaActionPerformed
        this.gravaValoresNaTabelaRelatorioEntradas("Where situacao != 'pedido'");
        btnCancelarPesquisaEntrada.setEnabled(false);
        btnTodasEntradas.setEnabled(false);
        txtFuncionarioPedido.setText("");
        txtDataPedido.setText("");
        txtQtdPedido.setText("");
        txtStatusPedido.setText("");
        txtFuncionarioLancamento.setText("");
        txtDataLancamento.setText("");
        txtQtdLancamento.setText("");
        txtCodPedido.setText("");
        txtCaminhoNF.setText("");
        btnAbrirNF.setEnabled(false);
        produtoSelecionadoRelatorio = false;
    }//GEN-LAST:event_btnCancelarPesquisaEntradaActionPerformed

    private void btnPesquisarEntradasProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarEntradasProdutoActionPerformed
        jDialogPesquisaRelatorioEntrada pesquisaPedido = new jDialogPesquisaRelatorioEntrada(this, true);
        pesquisaPedido.setVisible(true);
        if(produtoSelecionadoRelatorio){
            Produto p = (Produto) cbbProdutoRelatorio.getSelectedItem();
            String produtoSelecionado = Integer.toString(p.getCodProduto());
            if(pesquisaPedido.getJrbEntregues()){
                if(pesquisaPedido.isPesquisaDataPedido()){
                    query = "WHERE codProduto = "+produtoSelecionado;
                    query += " AND situacao = 'entregue' AND  dataPedido LIKE '%"+pesquisaPedido.getTxtDataPedido()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaFornecedor()){
                    Fornecedor f = pesquisaPedido.getCbbFornecedor();
                    query = "WHERE codProduto = "+produtoSelecionado;
                    query += " AND situacao = 'entregue' AND  codFornecedor LIKE '"+Integer.toString(f.getCodFornecedor())+"'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaDataEntrada()){
                    query = "WHERE codProduto = "+produtoSelecionado;
                    query += " AND situacao = 'entregue' AND  dataEntrega LIKE '%"+pesquisaPedido.getTxtDataEntrada()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }
            }else if(pesquisaPedido.getJrbCancelados()){
                if(pesquisaPedido.isPesquisaDataPedido()){
                    query = "WHERE codProduto = "+produtoSelecionado;
                    query += " AND situacao = 'cancelado' AND  dataPedido LIKE '%"+pesquisaPedido.getTxtDataPedido()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaFornecedor()){
                    Fornecedor f = pesquisaPedido.getCbbFornecedor();
                    query = "WHERE codProduto = "+produtoSelecionado;
                    query += " AND situacao = 'cancelado' AND  codFornecedor LIKE '"+Integer.toString(f.getCodFornecedor())+"'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaDataEntrada()){
                    query = "WHERE codProduto = "+produtoSelecionado;
                    query +=" AND situacao = 'cancelado' AND  dataEntrega LIKE '%"+pesquisaPedido.getTxtDataEntrada()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }
            }else if(pesquisaPedido.getJrbTodos()){
                if(pesquisaPedido.isPesquisaDataPedido()){
                    query = "WHERE codProduto = "+produtoSelecionado;
                    query += " AND situacao != 'pedido' AND dataPedido LIKE '%"+pesquisaPedido.getTxtDataPedido()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaFornecedor()){
                    Fornecedor f = pesquisaPedido.getCbbFornecedor();
                    query = "WHERE codProduto = "+produtoSelecionado;
                    query += " AND situacao != 'pedido' AND codFornecedor LIKE '"+Integer.toString(f.getCodFornecedor())+"'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaDataEntrada()){
                    query = "WHERE codProduto = "+produtoSelecionado;
                    query +=" AND situacao != 'pedido' AND dataEntrega LIKE '%"+pesquisaPedido.getTxtDataEntrada()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }
            }
        }else{
            if(pesquisaPedido.getJrbEntregues()){
                if(pesquisaPedido.isPesquisaDataPedido()){
                    query = "WHERE situacao = 'entregue' AND  dataPedido LIKE '%"+pesquisaPedido.getTxtDataPedido()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaFornecedor()){
                    Fornecedor f = pesquisaPedido.getCbbFornecedor();
                    query = "WHERE situacao = 'entregue' AND  codFornecedor LIKE '"+Integer.toString(f.getCodFornecedor())+"'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaDataEntrada()){
                    query = "WHERE situacao = 'entregue' AND  dataEntrega LIKE '%"+pesquisaPedido.getTxtDataEntrada()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }
            }else if(pesquisaPedido.getJrbCancelados()){
                if(pesquisaPedido.isPesquisaDataPedido()){
                    query = "WHERE situacao = 'cancelado' AND  dataPedido LIKE '%"+pesquisaPedido.getTxtDataPedido()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaFornecedor()){
                    Fornecedor f = pesquisaPedido.getCbbFornecedor();
                    query = "WHERE situacao = 'cancelado' AND  codFornecedor LIKE '"+Integer.toString(f.getCodFornecedor())+"'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaDataEntrada()){
                    query = "WHERE situacao = 'cancelado' AND  dataEntrega LIKE '%"+pesquisaPedido.getTxtDataEntrada()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }
            }else if(pesquisaPedido.getJrbTodos()){
                if(pesquisaPedido.isPesquisaDataPedido()){
                    query = "WHERE situacao != 'pedido' AND dataPedido LIKE '%"+pesquisaPedido.getTxtDataPedido()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaFornecedor()){
                    Fornecedor f = pesquisaPedido.getCbbFornecedor();
                    query = "WHERE situacao != 'pedido' AND codFornecedor LIKE '"+Integer.toString(f.getCodFornecedor())+"'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }else if(pesquisaPedido.isPesquisaDataEntrada()){
                    query = "WHERE situacao != 'pedido' AND dataEntrega LIKE '%"+pesquisaPedido.getTxtDataEntrada()+"%'";
                    gravaValoresNaTabelaRelatorioEntradas(query);
                }
            }
        }
        btnTodasEntradas.setEnabled(true);
        btnCancelarPesquisaEntrada.setEnabled(true);
    }//GEN-LAST:event_btnPesquisarEntradasProdutoActionPerformed

    private void btnPesquisarRestiradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarRestiradaActionPerformed
        jDialogPesquisaRelatorioSaida pesquisaSaida = new jDialogPesquisaRelatorioSaida(this, true);
        pesquisaSaida.setVisible(true);
        boolean pesquisouProduto = false;
        Produto p = (Produto) cbbProdutoRelatorioSaida.getSelectedItem();
        int produtoSelecionado = p.getCodProduto();
        if(produtoSelecionadoRelatorioSaida){
            query = "WHERE saida.codProduto = "+produtoSelecionado;
            if(pesquisaSaida.isPesquisaNome()){
                Funcionario f = pesquisaSaida.getCbbFuncionario();
                query = "WHERE saida.codUsuario = "+Integer.toString(f.getCodUsuario());
            }else if(pesquisaSaida.isPesquisaProduto()){
                produtoSelecionado = pesquisaSaida.getCbbProduto();
                query = "WHERE saida.codProduto = "+(produtoSelecionado+1);
                pesquisouProduto = true;
            }else if(pesquisaSaida.isPesquisaData()){
                query += " AND data LIKE '%"+pesquisaSaida.getTxtDataRetirada()+"%'";
                System.out.println(query);
            }
        }else{
            if(pesquisaSaida.isPesquisaNome()){
                Funcionario f = pesquisaSaida.getCbbFuncionario();
                query = "WHERE saida.codUsuario = "+Integer.toString(f.getCodUsuario());
            }else if(pesquisaSaida.isPesquisaProduto()){
                 produtoSelecionado = pesquisaSaida.getCbbProduto();
                System.out.println(produtoSelecionado);
                query = "WHERE saida.codProduto = "+(produtoSelecionado+1);
                pesquisouProduto = true;
            }else if(pesquisaSaida.isPesquisaData()){
                query = "WHERE data LIKE '%"+pesquisaSaida.getTxtDataRetirada()+"%'";
            }else{
                query = "";
            }
        }
        if(pesquisouProduto){
            cbbProdutoRelatorioSaida.setSelectedIndex(produtoSelecionado);
        }
        gravaValoresNaTabelaRelatorioSaidas(query);
        btnTodasRetirados.setEnabled(true);
    }//GEN-LAST:event_btnPesquisarRestiradaActionPerformed

    private void btnTodasRetiradosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodasRetiradosActionPerformed
        this.gravaValoresNaTabelaRelatorioSaidas("");
        btnTodasRetirados.setEnabled(false);
        produtoSelecionadoRelatorioSaida = false;
    }//GEN-LAST:event_btnTodasRetiradosActionPerformed

    private void btnPesquisarRetiradosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarRetiradosActionPerformed
        Produto p = (Produto) cbbProdutoRelatorioSaida.getSelectedItem();
        query = "WHERE saida.codProduto = "+Integer.toString(p.getCodProduto())+"";
        this.gravaValoresNaTabelaRelatorioSaidas(query);
        // limpando os camos
        txtCodProdutoRetirado.setText("");
        txtFuncionarioRetirada.setText("");
        txtDataRetirada.setText("");
        txtQtdRetirada.setText("");
        txtQtdEstoqueRetirado.setText("");
        txtEstoqueMinimoRetirado.setText("");
        btnTodasRetirados.setEnabled(true);
        produtoSelecionadoRelatorioSaida = true;
    }//GEN-LAST:event_btnPesquisarRetiradosActionPerformed

    private void tblRelatorioSaidaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRelatorioSaidaProdutoMouseClicked
         // transferindo os dados da linha selecionada para os campos
        if(tblRelatorioSaidaProduto.getSelectedRow() != -1){
            int cont = cbbProdutoRelatorioSaida.getItemCount()-1;
            String nomeProduto = tblRelatorioSaidaProduto.getValueAt(tblRelatorioSaidaProduto.getSelectedRow(), 3).toString();
            while (cont >= 0){
                if(nomeProduto.equals(cbbProdutoRelatorioSaida.getItemAt(cont).toString())){                    
                    cbbProdutoRelatorioSaida.setSelectedIndex(cont);
                    Produto p = (Produto)cbbProdutoRelatorioSaida.getSelectedItem();
                    txtCodProdutoRetirado.setText(Integer.toString(p.getCodProduto()));
                    cont = -1;
                }
                cont --;
            }
            txtFuncionarioRetirada.setText(tblRelatorioSaidaProduto.getValueAt(tblRelatorioSaidaProduto.getSelectedRow(), 1).toString());
            txtDataRetirada.setText(tblRelatorioSaidaProduto.getValueAt(tblRelatorioSaidaProduto.getSelectedRow(), 2).toString());
            txtQtdRetirada.setText(tblRelatorioSaidaProduto.getValueAt(tblRelatorioSaidaProduto.getSelectedRow(), 4).toString());
            txtQtdEstoqueRetirado.setText(tblRelatorioSaidaProduto.getValueAt(tblRelatorioSaidaProduto.getSelectedRow(), 5).toString());
            txtEstoqueMinimoRetirado.setText(tblRelatorioSaidaProduto.getValueAt(tblRelatorioSaidaProduto.getSelectedRow(), 6).toString());
            btnCancelar.setEnabled(true);
            btnFinalizarRetirada.setEnabled(true);
        }
    }//GEN-LAST:event_tblRelatorioSaidaProdutoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirNF;
    private javax.swing.JButton btnBuscarArquivo;
    private javax.swing.JToggleButton btnCancelar;
    private javax.swing.JButton btnCancelarPedido;
    private javax.swing.JButton btnCancelarPesquisaEntrada;
    private javax.swing.JButton btnEntradasCanceladas;
    private javax.swing.JButton btnEntradasLancadas;
    private javax.swing.JToggleButton btnFinalizarRetirada;
    private javax.swing.JButton btnPedidosCancelados;
    private javax.swing.JToggleButton btnPesquisar;
    private javax.swing.JButton btnPesquisarEntradas;
    private javax.swing.JButton btnPesquisarEntradasProduto;
    private javax.swing.JButton btnPesquisarPedido;
    private javax.swing.JToggleButton btnPesquisarRestirada;
    private javax.swing.JButton btnPesquisarRetirados;
    private javax.swing.JButton btnTodasEntradas;
    private javax.swing.JButton btnTodasRetirados;
    private javax.swing.JButton btnlancamento;
    private javax.swing.JComboBox<Object> cbbProdutoRelatorio;
    private javax.swing.JComboBox<Object> cbbProdutoRelatorioSaida;
    private javax.swing.JComboBox<Object> cbbProdutosEntrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JDesktopPane jdpEntradaProduto;
    private javax.swing.JDesktopPane jdpRelatorioEntrada;
    private javax.swing.JDesktopPane jdpRelatorioSaida;
    private javax.swing.JDesktopPane jdpSaidaProduto;
    private javax.swing.JInternalFrame jifEntradaProdutos;
    private javax.swing.JInternalFrame jifRelatorioEntrada;
    private javax.swing.JInternalFrame jifSaidaProduto;
    private javax.swing.JInternalFrame jifSaidaProduto1;
    private javax.swing.JMenu mnArquivos;
    private javax.swing.JMenuItem mnArquivosFornecedores;
    private javax.swing.JMenuItem mnArquivosFuncionarios;
    private javax.swing.JMenuItem mnArquivosNovoFornecedor;
    private javax.swing.JMenuItem mnArquivosNovoFuncionario;
    private javax.swing.JMenuItem mnArquivosProdutos;
    private javax.swing.JMenu mnMinConta;
    private javax.swing.JMenuItem mnMinContaDados;
    private javax.swing.JMenuItem mnMinContaSair;
    private javax.swing.JMenu mnMovimentos;
    private javax.swing.JMenuItem mnMovimentosEntrada;
    private javax.swing.JMenuItem mnMovimentosEstoqueMinimo;
    private javax.swing.JMenuItem mnMovimentosPedido;
    private javax.swing.JMenuItem mnMovimentosRelatorioEntrada;
    private javax.swing.JMenuItem mnMovimentosRelatorioSaida;
    private javax.swing.JMenuItem mnMovimentosSaida;
    private javax.swing.JTable tblEntradasProdutos;
    private javax.swing.JTable tblPedidosProdutos;
    private javax.swing.JTable tblProdutosDisponiveis;
    private javax.swing.JPanel tblRelatorioSaida;
    private javax.swing.JTable tblRelatorioSaidaProduto;
    private javax.swing.JTextField txtCaminhoArquivo;
    private javax.swing.JTextField txtCaminhoNF;
    private javax.swing.JFormattedTextField txtCodPedido;
    private javax.swing.JTextField txtCodProdutoRetirado;
    private javax.swing.JTextField txtDataEntrada;
    private javax.swing.JTextField txtDataLancamento;
    private javax.swing.JTextField txtDataPedido;
    private javax.swing.JFormattedTextField txtDataRetirada;
    private javax.swing.JFormattedTextField txtDataSaida;
    private javax.swing.JFormattedTextField txtEstoqueMinimo;
    private javax.swing.JFormattedTextField txtEstoqueMinimoRetirado;
    private javax.swing.JTextField txtFuncionarioEntrada;
    private javax.swing.JTextField txtFuncionarioLancamento;
    private javax.swing.JTextField txtFuncionarioPedido;
    private javax.swing.JTextField txtFuncionarioRetirada;
    private javax.swing.JTextField txtFuncionarioSaida;
    private javax.swing.JTextField txtNomeProdutoSaida;
    private javax.swing.JFormattedTextField txtQtdEntregas;
    private javax.swing.JFormattedTextField txtQtdEstoqueRetirado;
    private javax.swing.JFormattedTextField txtQtdEstoqueSaida;
    private javax.swing.JFormattedTextField txtQtdLancamento;
    private javax.swing.JFormattedTextField txtQtdPedido;
    private javax.swing.JFormattedTextField txtQtdRetirada;
    private javax.swing.JFormattedTextField txtQtdRetirar;
    private javax.swing.JFormattedTextField txtStatusPedido;
    private javax.swing.JFormattedTextField txtTotalCancelamentos;
    private javax.swing.JFormattedTextField txtTotalLancamentos;
    private javax.swing.JFormattedTextField txtTotalRegistros;
    private javax.swing.JFormattedTextField txtTotalRetirado;
    // End of variables declaration//GEN-END:variables
}
