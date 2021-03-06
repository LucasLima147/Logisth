/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import bd.beans.Funcionario;
import bd.dao.FuncionarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LucasLima
 */
public class frmListaDeFuncionarios extends javax.swing.JFrame {

    /**
     * Construtor da tela frmPesquisaFuncionarios
     */
    public frmListaDeFuncionarios() {
        initComponents();
        this.setLocationRelativeTo(null);
        dfmListaProdutos = (DefaultTableModel) tblListaFuncionarios.getModel();
        this.gravaValoresNaTabela("");
    }
    
    // Para usar a tabela
    DefaultTableModel dfmListaProdutos;
    
    // Instanciando o Registro de Funcionários
    FuncionarioDAO funcionarios = new FuncionarioDAO();
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 06/07/2020 </p>
     * <p> Função para realizar a consulta no Banco de Dados e adicionar os valores na tabela </p>
     */
    private String query;
    public void gravaValoresNaTabela(String query){
        DefaultTableModel modelo = (DefaultTableModel) tblListaFuncionarios.getModel(); 
        
        // zerando o número de linhas
        modelo.setNumRows(0);
        
        // inserindo os valores na tabela
        for(Funcionario f: funcionarios.select_PesquisaUsuario(query)){
            // pegando os valores do Banco de Dados
            modelo.addRow(new Object[]{
                f.getCodUsuario(),
                f.getNome(),
                f.getEndereco(),
                f.getCelular(),
                f.getCargo(),
                f.getDataContratacao()
            });
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaFuncionarios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodUser = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        btnPesquisarNome = new javax.swing.JButton();
        btnPesquisarCodUser = new javax.swing.JButton();
        btnPesquisarCelular = new javax.swing.JButton();
        btnFinalizarRegistro = new javax.swing.JToggleButton();
        btnCancelarRegistro = new javax.swing.JToggleButton();
        txtCelular = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lista de Funcionarios");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setText("Lista de Funcionarios");

        tblListaFuncionarios.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblListaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "CodUsuario", "Nome", "Endereco", "Celular", "Cargo", "Contratado em"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblListaFuncionarios);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Nome do Funcionario");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Codigo do Funcionário");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Celular do Funcionário");

        txtCodUser.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCodUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtNome.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnPesquisarNome.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisarNome.setText("Pesquisar Nome");
        btnPesquisarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarNomeActionPerformed(evt);
            }
        });

        btnPesquisarCodUser.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisarCodUser.setText("Pesquisar Código");
        btnPesquisarCodUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCodUserActionPerformed(evt);
            }
        });

        btnPesquisarCelular.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisarCelular.setText("Pesquisar Celular");
        btnPesquisarCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCelularActionPerformed(evt);
            }
        });

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

        txtCelular.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCelular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCelular.setToolTipText("Ex: (00)00000-0000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(171, 171, 171))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPesquisarNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(txtCodUser)
                    .addComponent(btnPesquisarCodUser, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(btnPesquisarCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(txtCelular))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnCancelarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btnFinalizarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisarNome, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 06/07/2020 </p>
     * <p> Botão que verifica se foi selecionado algum registro e, caso selecionado, 
     * transfere o código do funcionário para pesquisa pesquisa na lista salva na outra tela</p>
     */   
    private void btnFinalizarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarRegistroActionPerformed
        // BOTÃO FINALIZAR
        if(tblListaFuncionarios.getSelectedRow() != -1){
            frmDadosFuncionario dados = new frmDadosFuncionario();
            dados.valorSelecionadoDaTabela((int) tblListaFuncionarios.getValueAt(tblListaFuncionarios.getSelectedRow(), 0));
            dados.setVisible(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum item Selecionado");
        }
    }//GEN-LAST:event_btnFinalizarRegistroActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 26/08/2020 </p>
     * <p> Botão que fecha a tela da lista de funcionário</p>
     */
    private void btnCancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRegistroActionPerformed
        // BOTÃO CANCELAR REGISTRO
        frmDadosFuncionario dados = new frmDadosFuncionario();
        dados.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarRegistroActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 26/08/2020 </p>
     * <p> Botão que preenche a tabela de funcionário que o usuário escreveu no campo nome</p>
     */
    private void btnPesquisarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarNomeActionPerformed
        query = "WHERE nome LIKE '%"+txtNome.getText()+"%'";
        gravaValoresNaTabela(query);
    }//GEN-LAST:event_btnPesquisarNomeActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 26/08/2020 </p>
     * <p> Botão que preenche a tabela de funcionário que o usuário escreveu no campo Códgio do usuário</p>
     */
    private void btnPesquisarCodUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCodUserActionPerformed
        query = "WHERE codUsuario LIKE '%"+txtCodUser.getText()+"%'";
        gravaValoresNaTabela(query);
    }//GEN-LAST:event_btnPesquisarCodUserActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 26/08/2020 </p>
     * <p> Botão que preenche a tabela de funcionário que o usuário escreveu no celular do usuário</p>
     */
    private void btnPesquisarCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCelularActionPerformed
        query = "WHERE celular LIKE '%"+txtCelular.getText()+"%'";
        gravaValoresNaTabela(query);
    }//GEN-LAST:event_btnPesquisarCelularActionPerformed

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
            java.util.logging.Logger.getLogger(frmListaDeFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmListaDeFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmListaDeFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmListaDeFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmListaDeFuncionarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnCancelarRegistro;
    private javax.swing.JToggleButton btnFinalizarRegistro;
    private javax.swing.JButton btnPesquisarCelular;
    private javax.swing.JButton btnPesquisarCodUser;
    private javax.swing.JButton btnPesquisarNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListaFuncionarios;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCodUser;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
