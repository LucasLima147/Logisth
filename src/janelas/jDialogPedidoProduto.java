/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import bd.beans.Fornecedor;
import bd.beans.Produto;
import bd.dao.EntradaDAO;
import bd.dao.FornecedorDAO;
import bd.dao.FuncionarioDAO;
import bd.dao.ProdutoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.LoginUsuario;

/**
 *
 * @author LucasLima
 */
public class jDialogPedidoProduto extends javax.swing.JDialog {

    /**
     * Creates new form jDialogPedidoProduto
     */
    public jDialogPedidoProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fornecedor = new FornecedorDAO();
        produto = new ProdutoDAO();
        funcionario = new FuncionarioDAO();
        txtNomeFuncionario.setText(funcionario.nomeFuncionario(funcionario, Integer.parseInt(LoginUsuario.getUsuario())));
        this.preencherComboBoxFornecedor();
        this.preencherComboBoxProdutos();
        this.pegarDataAtual();
        this.setLocationRelativeTo(null);
        this.fezPedido = false;
    }

    private FornecedorDAO fornecedor;
    private ProdutoDAO produto;
    private FuncionarioDAO funcionario;
    private boolean fezPedido;

    public boolean isFezPedido() {
        return fezPedido;
    }
    
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 13/09/2020</p>
     * <p> Método responsáve por fazer o preenchimento dos fornecedires cadastrados no Banco de Dados</p>
     */
    public void preencherComboBoxFornecedor(){
        // recebendo a lista de fornecedores registrados
        for(Fornecedor f: fornecedor.select_PesquisaFornecedor("")){
            cbbFornecedor.addItem(f);
        }
    }
    
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 13/09/2020</p>
     * <p> Método responsáve por fazer o preenchimento combo box dos produtos cadastrados no Banco de Dados</p>
     */
    private void preencherComboBoxProdutos(){
        // recebendo a lista de fornecedores registrados
        for(Produto p: produto.select_PesquisaProduto("")){
            cbbProdutos.addItem(p);
        }
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 13/09/2020</p>
     * <p> Método responsáve por pegar e formatar a data atual do computador.</p>
     */
    private void pegarDataAtual(){
        // pegando a data do computador
        Date data = new Date();
        // criando a formatação para a data
        SimpleDateFormat fomatarData = new SimpleDateFormat("dd/MM/yyyy");
        // passando a data para a o campo de data
        txtDataPedido.setText(fomatarData.format(data));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JFormattedTextField();
        cbbProdutos = new javax.swing.JComboBox<>();
        txtDataPedido = new javax.swing.JFormattedTextField();
        txtNomeFuncionario = new javax.swing.JTextField();
        cbbFornecedor = new javax.swing.JComboBox<>();
        btnFinalizarRegistro1 = new javax.swing.JToggleButton();
        btnCancelarRegistro = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedido de produto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Produto:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Data do Pedido:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Pedido por:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Quantidade:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Fornecedor");

        txtQuantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQuantidade.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbProdutos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        try {
            txtDataPedido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataPedido.setText("");
        txtDataPedido.setToolTipText("Ex: 01/01/2000");
        txtDataPedido.setEnabled(false);
        txtDataPedido.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N

        txtNomeFuncionario.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtNomeFuncionario.setToolTipText("Nome de quem está fazendo o lançamento no sistema");
        txtNomeFuncionario.setEnabled(false);

        cbbFornecedor.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnFinalizarRegistro1.setBackground(new java.awt.Color(217, 212, 255));
        btnFinalizarRegistro1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnFinalizarRegistro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnFinalizarCadastroUsuario.png"))); // NOI18N
        btnFinalizarRegistro1.setText("Finalizar");
        btnFinalizarRegistro1.setToolTipText("");
        btnFinalizarRegistro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarRegistro1ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFinalizarRegistro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cbbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantidade)
                    .addComponent(txtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizarRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnFinalizarRegistro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarRegistro1ActionPerformed
        EntradaDAO entrada = new EntradaDAO();
        Fornecedor f = (Fornecedor) cbbFornecedor.getSelectedItem();
        Produto p = (Produto) cbbProdutos.getSelectedItem();
        entrada.setCodProduto(p.getCodProduto());
        entrada.setQtdPedido(Integer.parseInt(txtQuantidade.getText()));
        entrada.setDataPedido(txtDataPedido.getText());
        entrada.setCodFuncionarioPedido(Integer.parseInt(LoginUsuario.getUsuario()));
        entrada.setCodFornecedor(f.getCodFornecedor());
        entrada.setSituacao("pedido");
        entrada.insert_realizarPedido();
        fezPedido = true;
        this.dispose();

    }//GEN-LAST:event_btnFinalizarRegistro1ActionPerformed

    private void btnCancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRegistroActionPerformed
        // BOTÃO CANCELAR REGISTRO
        this.dispose();
    }//GEN-LAST:event_btnCancelarRegistroActionPerformed

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
            java.util.logging.Logger.getLogger(jDialogPedidoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jDialogPedidoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jDialogPedidoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jDialogPedidoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jDialogPedidoProduto dialog = new jDialogPedidoProduto(new javax.swing.JFrame(), true);
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
    private javax.swing.JToggleButton btnCancelarRegistro;
    private javax.swing.JToggleButton btnFinalizarRegistro1;
    private javax.swing.JComboBox<Object> cbbFornecedor;
    private javax.swing.JComboBox<Object> cbbProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField txtDataPedido;
    private javax.swing.JTextField txtNomeFuncionario;
    private javax.swing.JFormattedTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
