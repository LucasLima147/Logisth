/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import bd.beans.Funcionario;
import bd.beans.Produto;
import bd.dao.FuncionarioDAO;
import bd.dao.ProdutoDAO;
import javax.swing.JTextField;

/**
 *
 * @author LucasLima
 */
public class jDialogPesquisaRelatorioSaida extends javax.swing.JDialog {

    /**
     * Construtor da classe
     */
    public jDialogPesquisaRelatorioSaida(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.produto = new ProdutoDAO();
        this.funcionario = new FuncionarioDAO();
        this.pesquisaProduto = false;
        this.pesquisaNome = false;
        this.pesquisaData = false;
        this.preencherComboBoxProdutos();
        this.preencherComboBoxFuncionario();
        this.setLocationRelativeTo(null);
    }
    private FuncionarioDAO funcionario;
    private ProdutoDAO produto;
    
    private boolean pesquisaProduto;
    private boolean pesquisaNome;
    private boolean pesquisaData;
    
    // campos para validar pesquisa
    public boolean isPesquisaProduto() {
        return pesquisaProduto;
    }
    public boolean isPesquisaNome() {
        return pesquisaNome;
    }
    public boolean isPesquisaData() {
        return pesquisaData;
    }
    
    public int getCbbProduto() {
        return cbbProdutos.getSelectedIndex();
    }
    public Funcionario getCbbFuncionario() {
        return (Funcionario) cbbFuncionario.getSelectedItem();
    }
    
    public String getTxtDataRetirada() {
        return txtDataRetirada.getText();
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 28/09/2020</p>
     * <p> Método responsáve por fazer o preenchimento dos produtos cadastrados no Banco de Dados</p>
     */
    public void preencherComboBoxProdutos(){
        // recebendo a lista de fornecedores registrados
        for(Produto p: produto.select_PesquisaProduto("")){
            cbbProdutos.addItem(p);
        }
    }
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 28/09/2020</p>
     * <p> Método responsáve por fazer o preenchimento dos funcionarios cadastrados no Banco de Dados</p>
     */
    public void preencherComboBoxFuncionario(){
        // recebendo a lista de fornecedores registrados
        for(Funcionario p: funcionario.select_PesquisaUsuario("")){
            cbbFuncionario.addItem(p);
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
        jLabel2 = new javax.swing.JLabel();
        btnPesquisarProduto = new javax.swing.JButton();
        cbbProdutos = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnPesquisarFuncionario = new javax.swing.JButton();
        txtDataRetirada = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnPesquisaData = new javax.swing.JButton();
        cbbFuncionario = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa de Produto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Nome do produto");

        btnPesquisarProduto.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisarProduto.setText("Pesquisar Produto");
        btnPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarProdutoActionPerformed(evt);
            }
        });

        cbbProdutos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Nome Funcionario");

        btnPesquisarFuncionario.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisarFuncionario.setText("Pesquisar Usuário");
        btnPesquisarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarFuncionarioActionPerformed(evt);
            }
        });

        txtDataRetirada.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Data de Retirada");

        btnPesquisaData.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisaData.setText("Pesquisar Data");
        btnPesquisaData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaDataActionPerformed(evt);
            }
        });

        cbbFuncionario.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(btnPesquisarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbFuncionario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbProdutos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(btnPesquisarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtDataRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 19, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisaData, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
         * Autores: Lucas Lima e Hermane Boavida
         * <p>Data: 27/08/2020<br>
         * Botão que valida a pesquisa na tabela de produtos pelo nome do produto</p>
         */
    private void btnPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProdutoActionPerformed
        this.pesquisaProduto = true;
        this.dispose();
    }//GEN-LAST:event_btnPesquisarProdutoActionPerformed

    private void btnPesquisarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarFuncionarioActionPerformed
        this.pesquisaNome = true;
        this.dispose();
    }//GEN-LAST:event_btnPesquisarFuncionarioActionPerformed

    private void btnPesquisaDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaDataActionPerformed
        this.pesquisaData = true;
        this.dispose();
    }//GEN-LAST:event_btnPesquisaDataActionPerformed

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
            java.util.logging.Logger.getLogger(jDialogPesquisaRelatorioSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jDialogPesquisaRelatorioSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jDialogPesquisaRelatorioSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jDialogPesquisaRelatorioSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jDialogPesquisaRelatorioSaida dialog = new jDialogPesquisaRelatorioSaida(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisaData;
    private javax.swing.JButton btnPesquisarFuncionario;
    private javax.swing.JButton btnPesquisarProduto;
    private javax.swing.JComboBox<Object> cbbFuncionario;
    private javax.swing.JComboBox<Object> cbbProdutos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtDataRetirada;
    // End of variables declaration//GEN-END:variables
}
