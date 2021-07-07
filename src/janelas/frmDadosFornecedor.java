/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import bd.beans.Fornecedor;
import bd.dao.FornecedorDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * Autor: Lucas Limas e Hermane Boavida
 * <p> Tela: Dados da conta<br>
 *  Finalidade: ao consultas os funcionários, o usuário irá ver primeiro os dados básicos do usuário<br>
 *  OBS: ele pode apenas pesquisar e, para alterar dados, será chamado uma tela de login
 */
public class frmDadosFornecedor extends javax.swing.JFrame {

    /**
     * Cosntrutor da Classe
     */
    public frmDadosFornecedor() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.dadosBasicosDosFornecedores = funcionarios.select_PesquisaFornecedor("");
        this.indice = -1;
        this.proximoFornecedor();
    }
    
    // Instanciando o Registro de Funcionários
    private FornecedorDAO funcionarios = new FornecedorDAO();
    private List<Fornecedor> dadosBasicosDosFornecedores = new ArrayList<>();
    private int indice;
    
    // SETTERS & GETTERS
    public JFormattedTextField getTxtCelular() {
        return txtCelular;
    }
    public void setTxtCelular(String txtCelular) {
        this.txtCelular .setText(txtCelular);
    }

    public JFormattedTextField getTxtCnpj() {
        return txtCnpj;
    }
    public void setTxtCnpj(String txtCnpj) {
        this.txtCnpj.setText(txtCnpj);
    }

    public JTextField getTxtCodFornecedor() {
        return txtCodFornecedor;
    }
    public void setTxtCodFornecedor(String txtCodFornecedor) {
        this.txtCodFornecedor.setText(txtCodFornecedor);
    }

    public JFormattedTextField getTxtTelefone() {
        return txtTelefone;
    }
    public void setTxtTelefone(String txtTelefone) {
        this.txtTelefone.setText(txtTelefone);
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }
    public void setTxtEmail(String txtEmail) {
        this.txtEmail.setText(txtEmail);
    }

    public JLabel getTxtEmpresa() {
        return txtEmpresa;
    }
    public void setTxtEmpresa(String txtEmpresa) {
        this.txtEmpresa.setText(txtEmpresa);
    }

    public JTextField getTxtEndereco() {
        return txtEndereco;
    }
    public void setTxtEndereco(String txtEndereco) {
        this.txtEndereco.setText(txtEndereco);
    }
    
    public int getIndice() {
        return indice;
    }
    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    /**
    *
    * Autor: Lucas Limas e Hermane Boavida
    * <p> Data: 26/08/2020<br>
    *  Finalidade: função que acessa a lista de fornecedores e mostra o próximo registro</p>
    */
    public void proximoFornecedor(){
        this.setIndice(this.getIndice()+1);
        int totalRegistros = dadosBasicosDosFornecedores.size() - 1;
        if(this.getIndice() <= totalRegistros){
            Fornecedor f = dadosBasicosDosFornecedores.get(indice);
            txtCodFornecedor.setText(Integer.toString(f.getCodFornecedor()));
            txtEmpresa.setText(f.getNomeFantasia());
            txtEndereco.setText(f.getEndereco());
            txtCnpj.setText(f.getCnpj());
            txtTelefone.setText(f.getTelefone());
            txtCelular.setText(f.getCelular());
            txtEmail.setText(f.getEmail());
        }else{
            JOptionPane.showMessageDialog(null, "Este já é o último fornecedor registrado");
        }
    }
    /**
    *
    * Autor: Lucas Limas e Hermane Boavida
    * <p> Data: 26/08/2020<br>
    *  Finalidade: função que acessa a lista de fornecedores e mostra o registro anterior</p>
    */
    public void anteriorFornecedor(){
        this.setIndice(this.getIndice()-1);
        int totalRegistros = dadosBasicosDosFornecedores.size() - 1;
        if(this.getIndice() >= 0){
            Fornecedor f = dadosBasicosDosFornecedores.get(indice);
            txtCodFornecedor.setText(Integer.toString(f.getCodFornecedor()));
            txtEmpresa.setText(f.getNomeFantasia());
            txtEndereco.setText(f.getEndereco());
            txtCnpj.setText(f.getCnpj());
            txtTelefone.setText(f.getTelefone());
            txtCelular.setText(f.getCelular());
            txtEmail.setText(f.getEmail());
        }else{
            JOptionPane.showMessageDialog(null, "Este já é o primeiro fornecedor registrado");
        }
    }
    /**
    *
    * Autor: Lucas Limas e Hermane Boavida
    * <p> Data: 26/08/2020<br>
    *  Finalidade: função que a tela da tabela de fornecedores usa para retornar qual o indice do funcionário selecionado</p>
    */
    public void valorSelecionadoDaTabela(int codFornecedor){
        for(Fornecedor f: dadosBasicosDosFornecedores){
            if(f.getCodFornecedor()== codFornecedor){
                this.setIndice(dadosBasicosDosFornecedores.indexOf(f)-1);
                this.proximoFornecedor();
                break;
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAnterior = new javax.swing.JToggleButton();
        btnPesquisar = new javax.swing.JToggleButton();
        btnProximo = new javax.swing.JToggleButton();
        btnAlterar = new javax.swing.JToggleButton();
        txtEmpresa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodFornecedor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dados do Funcionário");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnAnterior.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnAnterior.png"))); // NOI18N
        btnAnterior.setText("Anterior");
        btnAnterior.setToolTipText("Apagar funcionário");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnPesquisar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnPesquisar.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setToolTipText("Pesquisar funcionário");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnProximo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnProximo.png"))); // NOI18N
        btnProximo.setText("Próximo");
        btnProximo.setToolTipText("Apagar funcionário");
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icoCadastroUsuarios.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setToolTipText("Alterar dados");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        txtEmpresa.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        txtEmpresa.setText("Nome Fantasia da empresa");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("CNPJ");

        try {
            txtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCnpj.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCnpj.setToolTipText("00.000.000/0000-00");
        txtCnpj.setEnabled(false);
        txtCnpj.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Cod. Fornecedor");

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCelular.setToolTipText("(00)00000-0000");
        txtCelular.setEnabled(false);
        txtCelular.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Celular:");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefone.setToolTipText("(00) 0000-0000");
        txtTelefone.setEnabled(false);
        txtTelefone.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Telefone:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txtEmail.setEnabled(false);

        txtEndereco.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txtEndereco.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Endereço completo da empresa");

        txtCodFornecedor.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txtCodFornecedor.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmpresa)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEndereco)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(txtCodFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnProximo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(txtEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel9)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        /**
        *
        * Autor: Lucas Limas e Hermane Boavida
        * <p> Data: 22/05/2020<br>
        *  Finalidade: Abrir a tela de Registro de Fornecedor para alterar os dados<br>
        *  OBS: será aberto uma tela de login antes de poder alterar os dados</p>
        */
    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // BOTÃO ALTERAR
        // Instâncias
        frmRegistroFornecedor fornecedor = new frmRegistroFornecedor();
        jDialogAlteraDados loginAlterarDados = new jDialogAlteraDados(this, true);
        
        loginAlterarDados.setVisible(true);
        if(loginAlterarDados.isPermissao()){
            // abrindo a tela de registro de fornecedor
            fornecedor.AlterarDados(Integer.parseInt(txtCodFornecedor.getText()));
            // fechando a tela de consulta de dados
            this.dispose();
        }
    }//GEN-LAST:event_btnAlterarActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 14/07/2020 <br>
     * Finalidade: abrir a tela com todos os fornecedores cadastrados</p>
     */
    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        frmListaDeFornecedores fornecedores = new frmListaDeFornecedores();
        fornecedores.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPesquisarActionPerformed
    /**
    * Autor: Lucas Limas e Hermane Boavida
    * <p> Data: 26/08/2020<br>
    *  Finalidade: chama a função que passa para o próximo registro de fornecedor</p>
    */
    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        this.proximoFornecedor();
    }//GEN-LAST:event_btnProximoActionPerformed
    /**
    * Autor: Lucas Limas e Hermane Boavida
    * <p> Data: 26/08/2020<br>
    *  Finalidade: chama a função que passa para o registro de fornecedor anterior</p>
    */
    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        this.anteriorFornecedor();
    }//GEN-LAST:event_btnAnteriorActionPerformed

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
            java.util.logging.Logger.getLogger(frmDadosFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDadosFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDadosFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDadosFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDadosFornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAlterar;
    private javax.swing.JToggleButton btnAnterior;
    private javax.swing.JToggleButton btnPesquisar;
    private javax.swing.JToggleButton btnProximo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtCodFornecedor;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
