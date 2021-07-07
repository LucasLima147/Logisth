/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import bd.beans.Fornecedor;
import bd.dao.FornecedorDAO;

/**
 *
 * @author LucasLima
 */
public class jDialogPesquisaRelatorioEntrada extends javax.swing.JDialog {

    /**
     * Construtor da classe
     */
    public jDialogPesquisaRelatorioEntrada(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.fornecedor = new FornecedorDAO();
        this.preencherComboBoxFornecedor();
        this.pesquisaDataPedido = false;
        this.pesquisaFornecedor = false;
        this.pesquisaDataEntrada = false;
        this.setLocationRelativeTo(null);
    }
    
    // atributos
    FornecedorDAO fornecedor;
    private boolean pesquisaFornecedor;
    private boolean pesquisaDataPedido;
    private boolean pesquisaDataEntrada;
    
    
    // selecionando qual campo vai pesquisar
    public boolean isPesquisaFornecedor() {
        return pesquisaFornecedor;
    }
    public boolean isPesquisaDataPedido() {
        return pesquisaDataPedido;
    }
    public boolean isPesquisaDataEntrada() {
        return pesquisaDataEntrada;
    }
    
    // retornando o valor digitado/escolhido
    public Fornecedor getCbbFornecedor() {
        return (Fornecedor) cbbFornecedor.getSelectedItem();
    }
    public String getTxtDataEntrada() {
        return txtDataEntrada.getText();
    }
    public String getTxtDataPedido() {
        return txtDataPedido.getText();
    }
    
    public boolean getJrbCancelados(){
        return jrbCancelados.isSelected();
    }

    public boolean getJrbEntregues() {
        return jrbEntregues.isSelected();
    }

    // selecionando o tipo de pesquisa
    public boolean getJrbTodos() {    
        return jrbTodos.isSelected();
    }

    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 11/08/2020</p>
     * <p> Método responsáve por fazer o preenchimento dos fornecedires cadastrados no Banco de Dados</p>
     */
    public void preencherComboBoxFornecedor() {
        // recebendo a lista de fornecedores registrados
        for(Fornecedor f: fornecedor.select_PesquisaFornecedor("")){
            cbbFornecedor.addItem(f);
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

        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnPesquisarFornecedor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbbFornecedor = new javax.swing.JComboBox<>();
        btnPesquisarDataEntrada = new javax.swing.JButton();
        txtDataEntrada = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnPesquisarPedido = new javax.swing.JButton();
        txtDataPedido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jrbTodos = new javax.swing.JRadioButton();
        jrbEntregues = new javax.swing.JRadioButton();
        jrbCancelados = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa de Produto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        btnPesquisarFornecedor.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisarFornecedor.setText("Pesquisar Fornecedor");
        btnPesquisarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarFornecedorActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Fornecedor");

        cbbFornecedor.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnPesquisarDataEntrada.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisarDataEntrada.setText("Pesquisar esta data");
        btnPesquisarDataEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarDataEntradaActionPerformed(evt);
            }
        });

        txtDataEntrada.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Data da baixa");

        btnPesquisarPedido.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisarPedido.setText("Pesquisar esta data");
        btnPesquisarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarPedidoActionPerformed(evt);
            }
        });

        txtDataPedido.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Data de Pedido");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Pesquisar sobre");

        jrbTodos.setBackground(new java.awt.Color(255, 255, 255));
        jrbTodos.setSelected(true);
        jrbTodos.setText("Todos os registros");
        jrbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTodosActionPerformed(evt);
            }
        });

        jrbEntregues.setBackground(new java.awt.Color(255, 255, 255));
        jrbEntregues.setText("Registrados como Entregue");
        jrbEntregues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbEntreguesActionPerformed(evt);
            }
        });

        jrbCancelados.setBackground(new java.awt.Color(255, 255, 255));
        jrbCancelados.setText("Registrados como Cancelados");
        jrbCancelados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCanceladosActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(cbbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jrbTodos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPesquisarPedido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPesquisarDataEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jrbEntregues, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPesquisarFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbCancelados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbTodos)
                    .addComponent(jrbEntregues)
                    .addComponent(jrbCancelados))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

       /**
         * Autores: Lucas Lima e Hermane Boavida
         * <p>Data: 27/08/2020<br>
         * Botão que valida a pesquisa na tabela de produtos pelo nome do fornecedor</p>
         */
    private void btnPesquisarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarFornecedorActionPerformed
        this.pesquisaFornecedor = true;
        this.dispose();
    }//GEN-LAST:event_btnPesquisarFornecedorActionPerformed

    private void btnPesquisarDataEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarDataEntradaActionPerformed
        this.pesquisaDataEntrada = true;
        this.dispose();
    }//GEN-LAST:event_btnPesquisarDataEntradaActionPerformed

    private void btnPesquisarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarPedidoActionPerformed
        this.pesquisaDataPedido = true;
        this.dispose();
    }//GEN-LAST:event_btnPesquisarPedidoActionPerformed

    private void jrbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTodosActionPerformed
        jrbEntregues.setSelected(false);
        jrbCancelados.setSelected(false);
    }//GEN-LAST:event_jrbTodosActionPerformed

    private void jrbEntreguesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbEntreguesActionPerformed
        jrbTodos.setSelected(false);
        jrbCancelados.setSelected(false);
    }//GEN-LAST:event_jrbEntreguesActionPerformed

    private void jrbCanceladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCanceladosActionPerformed
        jrbEntregues.setSelected(false);
        jrbTodos.setSelected(false);
    }//GEN-LAST:event_jrbCanceladosActionPerformed

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
            java.util.logging.Logger.getLogger(jDialogPesquisaRelatorioEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jDialogPesquisaRelatorioEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jDialogPesquisaRelatorioEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jDialogPesquisaRelatorioEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jDialogPesquisaRelatorioEntrada dialog = new jDialogPesquisaRelatorioEntrada(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnPesquisarDataEntrada;
    private javax.swing.JButton btnPesquisarFornecedor;
    private javax.swing.JButton btnPesquisarPedido;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<Object> cbbFornecedor;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jrbCancelados;
    private javax.swing.JRadioButton jrbEntregues;
    private javax.swing.JRadioButton jrbTodos;
    private javax.swing.JTextField txtDataEntrada;
    private javax.swing.JTextField txtDataPedido;
    // End of variables declaration//GEN-END:variables
}
