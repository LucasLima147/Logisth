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
import bd.dao.ProdutoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import main.LoginUsuario;

/**
 *
 * @author LucasLima
 */
public class frmEstoqueBaixo extends javax.swing.JFrame {

    /**
     * Creates new form frmEstoqueBaixo1
     */
    public frmEstoqueBaixo() {
        initComponents();
        this.setLocationRelativeTo(null);
        fornecedor = new FornecedorDAO();
        produto = new ProdutoDAO();
        this.pegarDataAtual();
        txtNomeProduto.setEnabled(false);
        txtEstoqueMin.setEnabled(false);
        txtCodProduto.setEnabled(false);
        this.preencherComboBoxFornecedor();
        this.gravaValoresNaTabela("WHERE qtdEstoque < qtdMinima");
    }
    // Para usar a tabela
    DefaultTableModel dtmTabela;
    // instanciando o DAO do produto
    FornecedorDAO fornecedor;
    ProdutoDAO produto;
    
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
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 11/08/2020</p>
     * <p> Método responsáve por fazer o preenchimento dos fornecedires cadastrados no Banco de Dados</p>
     */
    private void preencherComboBoxFornecedor(){
        // recebendo a lista de fornecedores registrados
        for(Fornecedor f: fornecedor.select_PesquisaFornecedor("")){
            cbbFornecedor.addItem(f);
        }
    }
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 11/08/2020</p>
     * <p> Este método realiza o preenchimento da tabela de produtos</p>
     */
    private void gravaValoresNaTabela(String query){
        DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel(); 
        
        // zerando o número de linhas
        modelo.setNumRows(0);
        
        // inserindo os valores na tabela
        for(Produto p: produto.select_PesquisaProduto(query)){
            // pegando os valores do Banco de Dados
            modelo.addRow(new Object[]{
                p.getCodProduto(),
                p.getNome(),
                p.getDescricao(),
                p.getQtdEstoque(),
                p.getPreco(),
                p.getQtdMinima(),
                fornecedor.nomeFornecedor(fornecedor, p.getFornecedor())
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
        jPanel2 = new javax.swing.JPanel();
        txtNomeProduto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEstoqueMin = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCodProduto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbbFornecedor = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnFinalizarRegistro = new javax.swing.JToggleButton();
        btnCancelarRegistro = new javax.swing.JToggleButton();
        txtDataPedido = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fazer Pedido de Produtos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 24))); // NOI18N

        txtNomeProduto.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNomeProduto.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Nome do Produto");

        txtQuantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtQuantidade.setEnabled(false);
        txtQuantidade.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Quantidade a pedir");

        txtEstoqueMin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtEstoqueMin.setEnabled(false);
        txtEstoqueMin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Estoque Mínimo");

        txtCodProduto.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCodProduto.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Código do Produto");

        cbbFornecedor.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbFornecedor.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Fornecedor");

        btnFinalizarRegistro.setBackground(new java.awt.Color(217, 212, 255));
        btnFinalizarRegistro.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnFinalizarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnFinalizarCadastroUsuario.png"))); // NOI18N
        btnFinalizarRegistro.setText("Finalizar");
        btnFinalizarRegistro.setToolTipText("");
        btnFinalizarRegistro.setEnabled(false);
        btnFinalizarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarRegistroActionPerformed(evt);
            }
        });

        btnCancelarRegistro.setBackground(new java.awt.Color(217, 212, 255));
        btnCancelarRegistro.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnCancelarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnCancelarrCadastroUsuario.png"))); // NOI18N
        btnCancelarRegistro.setText("Cancelar");
        btnCancelarRegistro.setEnabled(false);
        btnCancelarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRegistroActionPerformed(evt);
            }
        });

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

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Data do Pedido:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtCodProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                        .addComponent(txtNomeProduto)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(74, 74, 74))
                            .addComponent(txtEstoqueMin)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cbbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtDataPedido))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFinalizarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(btnCancelarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstoqueMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnFinalizarRegistro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelarRegistro)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos Abaixo do Estoque Minimo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 24))); // NOI18N

        tblProdutos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Produto", "Nome", "Descricao", "Quantidade", "Preço", "Estoque Min", "Fornecedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProdutos.getTableHeader().setReorderingAllowed(false);
        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblProdutos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnFinalizarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarRegistroActionPerformed
        // realizando o pedido
        EntradaDAO entrada = new EntradaDAO();
        Fornecedor f = (Fornecedor) cbbFornecedor.getSelectedItem();
        entrada.setCodProduto(Integer.parseInt(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0).toString()));
        entrada.setQtdPedido(Integer.parseInt(txtQuantidade.getText()));
        entrada.setDataPedido(txtDataPedido.getText());
        entrada.setCodFuncionarioPedido(Integer.parseInt(LoginUsuario.getUsuario()));
        entrada.setCodFornecedor(f.getCodFornecedor());
        entrada.setSituacao("pedido");
        entrada.insert_realizarPedido();
        //desabilitando botões
        btnCancelarRegistro.setEnabled(false);
        btnFinalizarRegistro.setEnabled(false);
        // desabilitando edição de campos
        cbbFornecedor.setEnabled(false);
        txtQuantidade.setEnabled(false);
        // limpando os campos
        txtNomeProduto.setText("");
        cbbFornecedor.setSelectedIndex(0);
        txtQuantidade.setText("");
        txtEstoqueMin.setText("");
        txtCodProduto.setText("");

    }//GEN-LAST:event_btnFinalizarRegistroActionPerformed

    private void btnCancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRegistroActionPerformed
        //desabilitando botões
        btnCancelarRegistro.setEnabled(false);
        btnFinalizarRegistro.setEnabled(false);
        // desabilitando edição de campos
        cbbFornecedor.setEnabled(false);
        txtQuantidade.setEnabled(false);
        // limpando os campos
        txtNomeProduto.setText("");
        cbbFornecedor.setSelectedIndex(0);
        txtQuantidade.setText("");
        txtEstoqueMin.setText("");
        txtCodProduto.setText("");
    }//GEN-LAST:event_btnCancelarRegistroActionPerformed

    private void tblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosMouseClicked
        // buscando o valor da tabela no combo box
        String fornecedores = tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 6).toString();
        if(tblProdutos.getSelectedRow() != -1){
            // Forbecedor
            int cont = cbbFornecedor.getItemCount()-1;
            while (cont >= 0){
                if(fornecedores.equals(cbbFornecedor.getItemAt(cont).toString())){
                    cbbFornecedor.setSelectedIndex(cont);
                    cont = -1;
                }
                cont --;
            }

            // transferindo os dados da linha selecionada para os campos
            txtCodProduto.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0).toString());
            txtNomeProduto.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 1).toString());
            txtEstoqueMin.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 6).toString());

            // habilitando edição de campos
            cbbFornecedor.setEnabled(true);
            txtQuantidade.setEnabled(true);
            
            btnFinalizarRegistro.setEnabled(true);
            btnCancelarRegistro.setEnabled(true);
        }

    }//GEN-LAST:event_tblProdutosMouseClicked

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
            java.util.logging.Logger.getLogger(frmEstoqueBaixo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEstoqueBaixo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEstoqueBaixo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEstoqueBaixo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmEstoqueBaixo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnCancelarRegistro;
    private javax.swing.JToggleButton btnFinalizarRegistro;
    private javax.swing.JComboBox<Object> cbbFornecedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtCodProduto;
    private javax.swing.JFormattedTextField txtDataPedido;
    private javax.swing.JFormattedTextField txtEstoqueMin;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JFormattedTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}