/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import bd.beans.Funcionario;
import bd.dao.FuncionarioDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * Autores: Lucas Lima e Hermane Boavida
 * <p> Dara: 14/05/2020</p>
 * <p> Tela com os dados do funcionário completo<br>
 *  Esta tela também é usado para registrar um novo funcionário no sistema</p>
 */
public class frmRegistroFuncionario extends javax.swing.JFrame {
    
    /**
     * Construtor da tela de Registro de Funcionário
     */
    public frmRegistroFuncionario() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.novoUsuario = false;
        this.atualizacaoDadosUsuario = false;
    }
    
    // Flag para verificar se ocorrerá uma inserção ou alteração;
    private boolean novoUsuario;
    private boolean atualizacaoDadosUsuario;
    
    
    // MEU GETTERS E SETTERS
    // Painel de Dados Pessoais
    public JComboBox<String> getCbbSexo() {
        return cbbSexo;
    }
    public void setCbbSexo(int indexSexo) {
        this.cbbSexo.setSelectedIndex(indexSexo);
    }

    public JFormattedTextField getTxtCPF() {
        return txtCPF;
    }
    public void setTxtCPF(String txtCPF) {
        this.txtCPF.setText(txtCPF);
    }

    public JFormattedTextField getTxtDataNascimento() {
        return txtDataNascimento;
    }
    public void setTxtDataNascimento(String txtDataNascimento) {
        this.txtDataNascimento.setText(txtDataNascimento);
    }

    public JTextField getTxtNomeUsuario() {
        return txtNomeUsuario;
    }
    public void setTxtNomeUsuario(String txtNomeUsuario) {
        this.txtNomeUsuario.setText(txtNomeUsuario);
    }
    
    // Painel de Contato
    public JTextField getTxtBairro() {
        return txtBairro;
    }
    public void setTxtBairro(JTextField txtBairro) {
        this.txtBairro = txtBairro;
    }

    public JFormattedTextField getTxtCelular() {
        return txtCelular;
    }
    public void setTxtCelular(JFormattedTextField txtCelular) {
        this.txtCelular = txtCelular;
    }

    public JTextField getTxtCidade() {
        return txtCidade;
    }
    public void setTxtCidade(JTextField txtCidade) {
        this.txtCidade = txtCidade;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }
    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JTextField getTxtEndereco() {
        return txtEndereco;
    }
    public void setTxtEndereco(JTextField txtEndereco) {
        this.txtEndereco = txtEndereco;
    }

    public JTextField getTxtUF() {
        return txtUF;
    }
    public void setTxtUF(JFormattedTextField txtUF) {
        this.txtUF = txtUF;
    }
    
    // Painel da Empresa
    public JComboBox<String> getCbbCargo() {
        return cbbCargo;
    }
    public void setCbbCargo(int indexCargo) {
        this.cbbCargo.setSelectedIndex(indexCargo);
    }

    public JPasswordField getTxtConfirmarSenha() {
        return txtConfirmarSenha;
    }
    public void setTxtConfirmarSenha(String txtConfirmarSenha) {
        this.txtConfirmarSenha.setText(txtConfirmarSenha);
    }

    public JTextField getTxtLogin() {
        return txtCodUser;
    }
    public void setTxtLogin(String txtLogin) {
        this.txtCodUser.setText(txtLogin);
    }

    public JPasswordField getTxtSenha() {
        return txtSenha;
    }
    public void setTxtSenha(String txtSenha) {
        this.txtSenha.setText(txtSenha);
    }
    
    public JFormattedTextField getTxtDataContratacao() {
        return txtDataContratacao;
    }
    public void setTxtDataContratacao(String txtDataContratacao) {
        this.txtDataContratacao.setText(txtDataContratacao);
    }
    
    // Botões
    public JButton getBtnAlterarMeusDados() {
        return btnAlterarSenha;
    }
    public JToggleButton getBtnCancelarRegistro() {
        return btnCancelarRegistro;
    }
    public JToggleButton getBtnFinalizarRegistro() {
        return btnFinalizarRegistro;
    }

    
    // MÉTODOS
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
        txtDataContratacao.setText(fomatarData.format(data));
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 09/07/2020</p>
     * <p> Este método é responsável por habilitar a edição dos campos e sinalizar a flag de alteração
     * de dados do funcionario</p>
     * @param codUsuario 
     */
    public void AlterarDados(int codUsuario){
        // instancia
        FuncionarioDAO funcionario = new FuncionarioDAO();
        // executando e salvando os dados no objeto
        funcionario.select_DadosDoUsuario(codUsuario);
        
        // pegando os dados do objeto para colocar nos campos
        txtNomeUsuario.setText(funcionario.getNome());
        txtCPF.setText(funcionario.getCpf());
        txtDataNascimento.setText(funcionario.getDataNascimento());
        cbbSexo.setSelectedItem(funcionario.getSexo());
        txtEndereco.setText(funcionario.getEndereco());        
        txtBairro.setText(funcionario.getBairro());        
        txtCidade.setText(funcionario.getCidade());        
        txtUF.setText(funcionario.getUf());        
        txtCelular.setText(funcionario.getCelular());
        txtEmail.setText(funcionario.getEmail());
        txtCodUser.setText(Integer.toString(funcionario.getCodUsuario()));
        cbbCargo.setSelectedItem(funcionario.getCargo());
        txtDataContratacao.setText(funcionario.getDataContratacao());
        txtSenha.setText(funcionario.getSenha());
        txtConfirmarSenha.setText(funcionario.getSenha());
        
        
        txtEndereco.setEnabled(true);
        txtBairro.setEnabled(true);
        txtCidade.setEnabled(true);
        txtUF.setEnabled(true);
        txtCelular.setEnabled(true);
        txtEmail.setEnabled(true);
        cbbCargo.setEnabled(true);
        txtSenha.setEnabled(true);
        txtConfirmarSenha.setEnabled(true);
        
        // sinalizando a flag de alteração de dados
       this.atualizacaoDadosUsuario = true;
        
        // tornando a janela visível
        this.setVisible(true);
    }
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 09/07/2020</p>
     * <p> Este método é responsável por habilitar a edição dos campos e sinalizar a flag
     * de criação de um novo funcionario</p> 
     */
    public void novoUsuario(){
        FuncionarioDAO funcionario = new FuncionarioDAO();
        
        // habilitando somente os campos editáveis
        txtNomeUsuario.setEnabled(true);
        txtCPF.setEnabled(true);
        txtDataNascimento.setEnabled(true);
        cbbSexo.setEnabled(true);
        txtEndereco.setEnabled(true);
        txtBairro.setEnabled(true);
        txtCidade.setEnabled(true);
        txtUF.setEnabled(true);
        txtCelular.setEnabled(true);
        txtEmail.setEnabled(true);
        cbbCargo.setEnabled(true);
        txtSenha.setEnabled(true);
        txtDataContratacao.setEnabled(true);
        txtConfirmarSenha.setEnabled(true);
        pegarDataAtual();
        
        // sinalizando a flag de criação de um novo Usuario
        this.novoUsuario = true;
        
        //tornando a janela visível
        this.setVisible(true);
    }
    
    
    public void consultarMeusDados(int codUsuario){
        // instancia
        FuncionarioDAO funcionario = new FuncionarioDAO();
        // executando e salvando os dados no objeto
        funcionario.select_DadosDoUsuario(codUsuario);
        
        // pegando os dados do objeto para colocar nos campos
        txtNomeUsuario.setText(funcionario.getNome());
        txtCPF.setText(funcionario.getCpf());
        txtDataNascimento.setText(funcionario.getDataNascimento());
        cbbSexo.setSelectedItem(funcionario.getSexo());
        txtEndereco.setText(funcionario.getEndereco());        
        txtBairro.setText(funcionario.getBairro());        
        txtCidade.setText(funcionario.getCidade());        
        txtUF.setText(funcionario.getUf());        
        txtCelular.setText(funcionario.getCelular());
        txtEmail.setText(funcionario.getEmail());
        txtCodUser.setText(Integer.toString(funcionario.getCodUsuario()));
        cbbCargo.setSelectedItem(funcionario.getCargo());
        txtDataContratacao.setText(funcionario.getDataContratacao());
        txtSenha.setText(funcionario.getSenha());
        System.out.println(novoUsuario);
        System.out.println(atualizacaoDadosUsuario);
        // tornando a janela visível
        this.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jpDadosPessoais = new javax.swing.JPanel();
        txtNomeUsuario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbbSexo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        jpContato = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtUF = new javax.swing.JFormattedTextField();
        jpEmpresa = new javax.swing.JPanel();
        txtCodUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtConfirmarSenha = new javax.swing.JPasswordField();
        cbbCargo = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDataContratacao = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        btnFinalizarRegistro = new javax.swing.JToggleButton();
        btnCancelarRegistro = new javax.swing.JToggleButton();
        btnAlterarSenha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Funcionário");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAlignmentY(0.6F);

        jpDadosPessoais.setBackground(new java.awt.Color(255, 255, 255));
        jpDadosPessoais.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Pessoais", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 24))); // NOI18N

        txtNomeUsuario.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNomeUsuario.setToolTipText("Seu nome completo");
        txtNomeUsuario.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Nome Completo:");

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCPF.setToolTipText("Ex: 000.000.000-00");
        txtCPF.setEnabled(false);
        txtCPF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("CPF:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Sexo:");

        cbbSexo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sexos", "Masculino", "Feminino" }));
        cbbSexo.setToolTipText("Selecione seu Sexo");
        cbbSexo.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Data de Nascimento:");

        try {
            txtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataNascimento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataNascimento.setToolTipText("Ex: 01/01/2000");
        txtDataNascimento.setEnabled(false);
        txtDataNascimento.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jpDadosPessoaisLayout = new javax.swing.GroupLayout(jpDadosPessoais);
        jpDadosPessoais.setLayout(jpDadosPessoaisLayout);
        jpDadosPessoaisLayout.setHorizontalGroup(
            jpDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDadosPessoaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDadosPessoaisLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeUsuario))
                    .addGroup(jpDadosPessoaisLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(10, 10, 10)
                        .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataNascimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpDadosPessoaisLayout.setVerticalGroup(
            jpDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDadosPessoaisLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jpDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jpDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpDadosPessoaisLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jpDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(cbbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpContato.setBackground(new java.awt.Color(255, 255, 255));
        jpContato.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contato", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 24))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Celular:");

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCelular.setToolTipText("Ex: (00)00000-0000");
        txtCelular.setEnabled(false);
        txtCelular.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Endereço:");

        txtEndereco.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEndereco.setToolTipText("Ex: R. noma da minha rua, 00 - ap 00 ");
        txtEndereco.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEmail.setToolTipText("Ex: meuemail@email.com");
        txtEmail.setEnabled(false);

        txtCidade.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCidade.setToolTipText("Ex: Santa Rita do Sapucaí");
        txtCidade.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Cidade:");

        txtBairro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBairro.setToolTipText("Ex: Meu Bairro");
        txtBairro.setEnabled(false);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("UF:");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Bairro:");

        try {
            txtUF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UU")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtUF.setEnabled(false);

        javax.swing.GroupLayout jpContatoLayout = new javax.swing.GroupLayout(jpContato);
        jpContato.setLayout(jpContatoLayout);
        jpContatoLayout.setHorizontalGroup(
            jpContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContatoLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContatoLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpContatoLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEndereco)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpContatoLayout.setVerticalGroup(
            jpContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContatoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jpContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        jpEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sistema", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 24))); // NOI18N

        txtCodUser.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCodUser.setToolTipText("Nome para login");
        txtCodUser.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Senha:");

        txtSenha.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSenha.setToolTipText("Senha para login");
        txtSenha.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Confirmar Senha:");

        txtConfirmarSenha.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtConfirmarSenha.setToolTipText("Reescreva a senha coloda");
        txtConfirmarSenha.setEnabled(false);

        cbbCargo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cargos", "Administrador", "Gerente", "Funcionario" }));
        cbbCargo.setToolTipText("Selecione um cargo");
        cbbCargo.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Cargo:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Contratado em:");

        try {
            txtDataContratacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataContratacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataContratacao.setToolTipText("Ex: 01/01/2000");
        txtDataContratacao.setEnabled(false);
        txtDataContratacao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("cod. Usuário:");

        javax.swing.GroupLayout jpEmpresaLayout = new javax.swing.GroupLayout(jpEmpresa);
        jpEmpresa.setLayout(jpEmpresaLayout);
        jpEmpresaLayout.setHorizontalGroup(
            jpEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpEmpresaLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodUser))
                    .addGroup(jpEmpresaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpEmpresaLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataContratacao))
                    .addGroup(jpEmpresaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpEmpresaLayout.setVerticalGroup(
            jpEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(cbbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(txtDataContratacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirmarSenha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnFinalizarRegistro.setBackground(new java.awt.Color(217, 212, 255));
        btnFinalizarRegistro.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnFinalizarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnFinalizarCadastroUsuario.png"))); // NOI18N
        btnFinalizarRegistro.setText("Finalizar");
        btnFinalizarRegistro.setToolTipText("");
        btnFinalizarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarRegistroActionPerformed(evt);
            }
        });

        btnCancelarRegistro.setBackground(new java.awt.Color(217, 212, 255));
        btnCancelarRegistro.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnCancelarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnCancelarrCadastroUsuario.png"))); // NOI18N
        btnCancelarRegistro.setText("Cancelar");
        btnCancelarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRegistroActionPerformed(evt);
            }
        });

        btnAlterarSenha.setBackground(new java.awt.Color(217, 212, 255));
        btnAlterarSenha.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnAlterarSenha.setText("Alterar Senha");
        btnAlterarSenha.setEnabled(false);
        btnAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinalizarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpContato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpDadosPessoais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpDadosPessoais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
           /**
         * 
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 14/05/2020<br>
         *  Finaiizada: Cadastrar um novo usuário ou finalizar a alteração de dados</p>
         */
    private void btnFinalizarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarRegistroActionPerformed
        // BOTÃO FINALIZAR
        
        //instancias
        FuncionarioDAO funcionarios = new FuncionarioDAO();
        
        //convertendo a senha pra String
        String senha = String.valueOf(txtSenha.getPassword());
        String confirmaSenha = String.valueOf(txtConfirmarSenha.getPassword());
        if(!(senha.equals(confirmaSenha))){
            JOptionPane.showMessageDialog(null, "Campo SENHA e CONF. SENHA são difentes");
        }else{
            // pegando os dados do funcionário
            funcionarios.setNome(txtNomeUsuario.getText());
            funcionarios.setCpf(txtCPF.getText());
            funcionarios.setDataNascimento(txtDataNascimento.getText());
            funcionarios.setSexo(cbbSexo.getSelectedItem().toString());
            funcionarios.setEndereco(txtEndereco.getText());
            funcionarios.setBairro(txtBairro.getText());
            funcionarios.setCidade(txtCidade.getText());
            funcionarios.setUf(txtUF.getText());
            funcionarios.setCelular(txtCelular.getText());
            funcionarios.setEmail(txtEmail.getText());
            funcionarios.setCargo(cbbCargo.getSelectedItem().toString());
            funcionarios.setDataContratacao(txtDataContratacao.getText());
            funcionarios.setSenha(senha);
            //realizando a inserção ou alteração de dados
            if(this.novoUsuario){
                funcionarios.insert();
            }else if(this.atualizacaoDadosUsuario){
                funcionarios.setCodUsuario(Integer.parseInt(txtCodUser.getText()));
                funcionarios.update_DadosUsuario();
            }else{
                System.out.println("atualizar a senha");
                funcionarios.setCodUsuario(Integer.parseInt(txtCodUser.getText()));
                System.out.println(funcionarios.getSenha());
                funcionarios.update_Senha();
            }
            this.dispose();
        }
        
        
    }//GEN-LAST:event_btnFinalizarRegistroActionPerformed
        /**
         * 
         * Autores: Lucas Lima e Hermane Boavida
         * <p> Data: 14/05/2020<br>
         *  Finaiizada: fechar a tela caso o usuário cancela o registro/alteraçao de dados</p>
         */
    private void btnCancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRegistroActionPerformed
        // BOTÃO CANCELAR REGISTRO
        this.setVisible(false);

    }//GEN-LAST:event_btnCancelarRegistroActionPerformed
        
    /**
     * 
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 15/05/2020 <br>
     *  Botão: Alterar Meus Dados<br>
     *  Função: habilitar a edição dos dados do próprio funcionário logado</p>
     */
    private void btnAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarSenhaActionPerformed
        // BOTÃO ALTERAR MEUS DADOS
        // habilitando edição de campos
        this.txtSenha.setEnabled(true);
        this.txtConfirmarSenha.setEnabled(true);
        // limpando os camos
        this.txtSenha.setText("");
        this.txtConfirmarSenha.setText("");

        // habilitando botões de Finalizar e Cancelar
        this.getBtnCancelarRegistro().setEnabled(true);
        this.getBtnFinalizarRegistro().setEnabled(true);
        // tornando o botão de alterar senha invisível
        this.getBtnAlterarMeusDados().setVisible(false);
    }//GEN-LAST:event_btnAlterarSenhaActionPerformed

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
            java.util.logging.Logger.getLogger(frmRegistroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRegistroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRegistroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRegistroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRegistroFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarSenha;
    private javax.swing.JToggleButton btnCancelarRegistro;
    private javax.swing.JToggleButton btnFinalizarRegistro;
    private javax.swing.JComboBox<String> cbbCargo;
    private javax.swing.JComboBox<String> cbbSexo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpContato;
    private javax.swing.JPanel jpDadosPessoais;
    private javax.swing.JPanel jpEmpresa;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCodUser;
    private javax.swing.JPasswordField txtConfirmarSenha;
    private javax.swing.JFormattedTextField txtDataContratacao;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JFormattedTextField txtUF;
    // End of variables declaration//GEN-END:variables

}
