package janelas;

import bd.dao.FornecedorDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 * 
 * Autores: Lucas Lima e Hermane Boavida
 * <p> Data: 22/05/2020 <br>
 * Tela com o registro do Fornecedor completo<br>
 * Esta tela também é usada para cadastrar um novo fornecedor
 */
public class frmRegistroFornecedor extends javax.swing.JFrame {

    /**
     * Construtor da tela
     */
    public frmRegistroFornecedor() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    
    // atributos
    private boolean novoCadastroFornecedor;
    private int codFornecedor;
    
    // GEETERS E SETTERS DO PAINEL DADOS DO FORNECEDOR
    public JComboBox<String> getCbbTipoPessoa() {
        return cbbTipoPessoa;
    }
    public void setCbbTipoPessoa(int indexTipoPessoa) {
        this.cbbTipoPessoa.setSelectedIndex(indexTipoPessoa);
    }

    public JFormattedTextField getTxtCnpj() {
        return txtCnpj;
    }
    public void setTxtCnpj(String txtCnpj) {
        this.txtCnpj.setText(txtCnpj);
    }

    public JTextField getTxtNomeFantasia() {
        return txtNomeFantasia;
    }
    public void setTxtNomeFantasia(String txtNomeFantasia) {
        this.txtNomeFantasia.setText(txtNomeFantasia);
    }

    public JTextField getTxtRazaoSocial() {
        return txtRazaoSocial;
    }
    public void setTxtRazaoSocial(String txtRazaoSocial) {
        this.txtRazaoSocial.setText(txtRazaoSocial);
    }

    public JFormattedTextField getTxtDataContratro() {
        return txtDataCadastro;
    }
    public void setTxtDataContratro(String txtDataCadastro) {
        this.txtDataCadastro.setText(txtDataCadastro);
    }
    
    // GEETERS E SETTERS DO PAINEL CONTATO
    public JTextField getTxtBairro() {
        return txtBairro;
    }
    public void setTxtBairro(String txtBairro) {
        this.txtBairro.setText(txtBairro);
    }

    public JFormattedTextField getTxtCelular() {
        return txtCelular;
    }
    public void setTxtCelular(String txtCelular) {
        this.txtCelular.setText(txtCelular);
    }

    public JTextField getTxtCidade() {
        return txtCidade;
    }
    public void setTxtCidade(String txtCidade) {
        this.txtCidade.setText(txtCidade);
    }

    public JFormattedTextField getTxtDataCadastro() {
        return txtDataCadastro;
    }
    public void setTxtDataCadastro(String txtDataCadastro) {
        this.txtDataCadastro.setText(txtDataCadastro);
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }
    public void setTxtEmail(String txtEmail) {
        this.txtEmail.setText(txtEmail);
    }

    public JTextField getTxtEndereco() {
        return txtEndereco;
    }
    public void setTxtEndereco(String txtEndereco) {
        this.txtEndereco.setText(txtEndereco);
    }

    public JFormattedTextField getTxtTelefone() {
        return txtTelefone;
    }
    public void setTxtTelefone(String txtTelefone) {
        this.txtTelefone.setText(txtTelefone);
    }

    public JTextField getTxtUF() {
        return txtUF;
    }
    public void setTxtUF(String txtUF) {
        this.txtUF.setText(txtUF);
    }
    
    // GETTERS DOS BOTÕES
    public JToggleButton getBtnCancelar() {
        return btnCancelar;
    }
    public JToggleButton getBtnFinalizar() {
        return btnFinalizar;
    }
    
    // MÉTODOS
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 09/07/2020</p>
     * <p> Este método é responsável por habilitar a edição dos campos e sinalizar a flag de alteração
     * de dados do fornecedor</p>
     * @param codFornecedor 
     */
    public void AlterarDados(int codFornecedor){
        // instancia
        FornecedorDAO fornecedor = new FornecedorDAO();
        // executando e salvando os dados no objeto
        fornecedor.select_dadosDoFornecedor(codFornecedor);
        
        // pegando os dados do objeto para colocar nos campos
        txtCnpj.setText(fornecedor.getCnpj());
        txtNomeFantasia.setText(fornecedor.getNomeFantasia());
        txtRazaoSocial.setText(fornecedor.getRazaoSocial());
        txtDataCadastro.setText(fornecedor.getDataCadastro());
        cbbTipoPessoa.setSelectedItem(fornecedor.getTipoPessoa());
        txtEndereco.setText(fornecedor.getEndereco());        
        txtBairro.setText(fornecedor.getBairro());        
        txtCidade.setText(fornecedor.getCidade());        
        txtUF.setText(fornecedor.getUf());        
        txtCelular.setText(fornecedor.getCelular());
        txtTelefone.setText(fornecedor.getTelefone());
        txtEmail.setText(fornecedor.getEmail());
        System.out.println("O código Passado foi: "+codFornecedor);
        this.codFornecedor = codFornecedor;
        
        // habilitando alterar outros campos
        txtNomeFantasia.setEnabled(true);
        txtRazaoSocial.setEnabled(true);
        cbbTipoPessoa.setEnabled(true);
        
        
        // tornando a janela visível
        this.setVisible(true);
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 28/09/202</p>
     * <p> Método responsáve por pegar e formatar a data atual do computador.</p>
     */
    private void pegarDataAtual(){
        // pegando a data do computador
        Date data = new Date();
        // criando a formatação para a data
        SimpleDateFormat fomatarData = new SimpleDateFormat("dd/MM/yyyy");
        // passando a data para a o campo de data
        txtDataCadastro.setText(fomatarData.format(data));
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 09/07/2020</p>
     * <p> Este método é responsável por habilitar a edição dos campos e sinalizar a flag
     * de criação de um novo fornecedor</p> 
     */
    public void novoUsuario(){
        FornecedorDAO fornecedor = new FornecedorDAO();
        
        // habilitando somente os campos editáveis
        txtCnpj.setEnabled(true);
        txtNomeFantasia.setEnabled(true);
        txtRazaoSocial.setEnabled(true);
        cbbTipoPessoa.setEnabled(true);
        txtDataCadastro.setEnabled(true);
        pegarDataAtual();
        
        // sinalizando a flag de criação de um novo Fornecedor
        this.novoCadastroFornecedor = true;
        
        //tornando a janela visível
        this.setVisible(true);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        btnFinalizar = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JFormattedTextField();
        txtRazaoSocial = new javax.swing.JTextField();
        txtNomeFantasia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDataCadastro = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        cbbTipoPessoa = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtEmail = new javax.swing.JTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        txtUF = new javax.swing.JFormattedTextField();
        btnCancelar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Fornecedor");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnFinalizar.setBackground(new java.awt.Color(217, 212, 255));
        btnFinalizar.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnFinalizarCadastroUsuario.png"))); // NOI18N
        btnFinalizar.setText("Finalizar");
        btnFinalizar.setToolTipText("");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do fornecedor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("CNPJ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Noma Fantasia");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Razão Social");

        try {
            txtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCnpj.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCnpj.setToolTipText("00.000.000/0000-00");
        txtCnpj.setEnabled(false);
        txtCnpj.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtRazaoSocial.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtRazaoSocial.setEnabled(false);

        txtNomeFantasia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNomeFantasia.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Data do cadastro");

        try {
            txtDataCadastro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataCadastro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataCadastro.setToolTipText("01/01/0000");
        txtDataCadastro.setEnabled(false);
        txtDataCadastro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Tipo de pessoa");

        cbbTipoPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbTipoPessoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo de Pessoa", "Física", "Jurídica" }));
        cbbTipoPessoa.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRazaoSocial)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(cbbTipoPessoa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtNomeFantasia)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contato", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 24))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Endereço:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Bairro:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Cidade:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("UF:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Telefone:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Celular:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Email:");

        txtEndereco.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEndereco.setToolTipText("Ex: R. noma da minha rua, 00 - ap 00 ");

        txtBairro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBairro.setToolTipText("Ex: Meu Bairro");

        txtCidade.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCidade.setToolTipText("Ex: Santa Rita do Sapucaí");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefone.setToolTipText("(00) 0000-0000");
        txtTelefone.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCelular.setToolTipText("(00)00000-0000");
        txtCelular.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        try {
            txtUF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UU")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(86, 86, 86)
                                .addComponent(jLabel10))
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(235, 235, 235)
                            .addComponent(txtCidade)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(txtEmail)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setBackground(new java.awt.Color(217, 212, 255));
        btnCancelar.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnCancelarrCadastroUsuario.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnFinalizar))
                .addGap(25, 25, 25))
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // BOTÃO CANCELAR REGISTRO
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed
        /**
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 07/06/2020<br>
         * Botão finalizar que será usado para alterar ou inserir dados dos fornecedores</p>
         */
    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        FornecedorDAO fornecedor = new FornecedorDAO();
        System.out.println("CodFornecedor sem o this: "+codFornecedor);
        System.out.println("CodFornecedor com o this: "+this.codFornecedor);
        fornecedor.setCodFornecedor(this.codFornecedor);
        fornecedor.setCnpj(txtCnpj.getText());
        fornecedor.setTipoPessoa(cbbTipoPessoa.getSelectedItem().toString());
        fornecedor.setRazaoSocial(txtRazaoSocial.getText());
        fornecedor.setNomeFantasia(txtNomeFantasia.getText());
        fornecedor.setDataCadastro(txtDataCadastro.getText());
        fornecedor.setEndereco(txtEndereco.getText());
        fornecedor.setBairro(txtBairro.getText());
        fornecedor.setCidade(txtCidade.getText());
        fornecedor.setUf(txtUF.getText());
        fornecedor.setCelular(txtCelular.getText());
        fornecedor.setTelefone(txtTelefone.getText());
        fornecedor.setEmail(txtEmail.getText());
        
        if(this.novoCadastroFornecedor){
            fornecedor.insert();
        }else{
            fornecedor.update_DadosFornecedor();
        }
        
        this.dispose();
        
    }//GEN-LAST:event_btnFinalizarActionPerformed

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
            java.util.logging.Logger.getLogger(frmRegistroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRegistroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRegistroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRegistroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRegistroFornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnCancelar;
    private javax.swing.JToggleButton btnFinalizar;
    private javax.swing.JComboBox<String> cbbTipoPessoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JFormattedTextField txtDataCadastro;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNomeFantasia;
    private javax.swing.JTextField txtRazaoSocial;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JFormattedTextField txtUF;
    // End of variables declaration//GEN-END:variables
}
