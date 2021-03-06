/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import bd.beans.Categoria;
import bd.dao.CategoriaDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LucasLima
 */
public class jDialogCategorias extends javax.swing.JDialog {

    /**
     * Construtor da janela de consulta de categorias
     */
    public jDialogCategorias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();    this.setLocationRelativeTo(null);
        dfmListaCategorias = (DefaultTableModel) tblCategorias.getModel();
        this.gravaValoresNaTabela("");
        this.cancelar = false;
    }
    
    // Para usar a tabela
    DefaultTableModel dfmListaCategorias;
    private boolean cancelar;
    
    // Instanciando o Registro de Funcionários
    CategoriaDAO categoria = new CategoriaDAO();
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 01/08/2020 </p>
     * <p> Função para realizar a consulta no Banco de Dados e adicionar os valores na tabela de categoria</p>
     */
    private String query;
    public void gravaValoresNaTabela(String query){
        DefaultTableModel modelo = (DefaultTableModel) tblCategorias.getModel(); 
        
        // zerando o número de linhas
        modelo.setNumRows(0);
        
        // inserindo os valores na tabela
        for(Categoria c: categoria.select_PesquisaCategoria(query)){
            // pegando os valores do Banco de Dados
            modelo.addRow(new Object[]{
                c.getCodCatoria(),
                c.getNome(),
                c.getDescricao()
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
        txtNomeCategoria = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        btnCadastrar = new javax.swing.JToggleButton();
        btnAlterar = new javax.swing.JToggleButton();
        btnApagar = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categorias", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N

        txtNomeCategoria.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Nome:");

        btnPesquisar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPesquisar.setText("Pesquisar Nome");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDescricao.setRows(4);
        jScrollPane1.setViewportView(txtDescricao);

        btnCadastrar.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnCAdastroProduto.png"))); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setToolTipText("Adicionar novo funcionário");
        btnCadastrar.setBorderPainted(false);
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icoCadastroUsuarios.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setToolTipText("Alterar dados");
        btnAlterar.setEnabled(false);
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnApagar.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnaApagarProduto.png"))); // NOI18N
        btnApagar.setText("Apagar");
        btnApagar.setToolTipText("Apagar funcionário");
        btnApagar.setEnabled(false);
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codCategoria", "Nome", "Descricao"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCategorias);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnPesquisar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(txtNomeCategoria, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNomeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
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
     * <p> Data: 01/08/2020</p>
     * <p> Botão que pesquisa a tabela do nome da categoria</p>
     * <p> Este botão também é usado para cancelar uma seleção de uma categoria na tabela</p>
     */
    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        if(cancelar){
            txtNomeCategoria.setText("");
            txtDescricao.setText("");
            btnCadastrar.setEnabled(true);
            btnAlterar.setEnabled(false);
            btnApagar.setEnabled(false);
            btnPesquisar.setText("Pesquisar Nome");
        }else{
            query = "WHERE nome LIKE '%"+txtNomeCategoria.getText()+"%'";
            gravaValoresNaTabela(query);
        }
        
    }//GEN-LAST:event_btnPesquisarActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 01/08/2020</p>
     * <p> Botão que realiza o cadastro de uma nova categoria</p>
     */
    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        categoria.setNome(txtNomeCategoria.getText());
        categoria.setDescricao(txtDescricao.getText());
        categoria.insert();
        gravaValoresNaTabela("");
    }//GEN-LAST:event_btnCadastrarActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 01/08/2020</p>
     * <p> Botão que irá realizar a alteração dos dados da categoria selecionada</p>
     */
    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // instância
        jDialogAlteraDados alterar = new jDialogAlteraDados(null, true);
        
        if(tblCategorias.getSelectedRow() != -1){
            alterar.setVisible(true);
            if(alterar.isPermissao()){            
            categoria.setCodCatoria(Integer.parseInt(tblCategorias.getValueAt(tblCategorias.getSelectedRow(), 0).toString()));
            categoria.setNome(txtNomeCategoria.getText());
            categoria.setDescricao(txtDescricao.getText());
            categoria.update_DadosCategoria();
            
             //habilitando botões
            btnAlterar.setEnabled(false);
            btnApagar.setEnabled(false);
            btnCadastrar.setEnabled(true);
            //limpando campos
            txtNomeCategoria.setText("");
            txtDescricao.setText("");
            //Atualizando tabela
            this.gravaValoresNaTabela("");
            btnPesquisar.setText("Pesquisar Nome");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum item Selecionado");
        }
    }//GEN-LAST:event_btnAlterarActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 01/08/2020</p>
     * <p> Botão que irá apagar o registro da categoria selecionada</p>
     */
    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        // instância
        jDialogAlteraDados alterar = new jDialogAlteraDados(null, true);
        
        if(tblCategorias.getSelectedRow() != -1){
            alterar.setVisible(true);
            if(alterar.isPermissao()){            
            categoria.setCodCatoria(Integer.parseInt(tblCategorias.getValueAt(tblCategorias.getSelectedRow(), 0).toString()));
            categoria.setNome(tblCategorias.getValueAt(tblCategorias.getSelectedRow(), 1).toString());
            categoria.setDescricao(tblCategorias.getValueAt(tblCategorias.getSelectedRow(), 2).toString());
            categoria.Delete_Categoria();
            
            //habilitando botões
            btnAlterar.setEnabled(false);
            btnApagar.setEnabled(false);
            btnCadastrar.setEnabled(true);
            this.gravaValoresNaTabela("");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum item Selecionado");
        }
    }//GEN-LAST:event_btnApagarActionPerformed
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 01/08/2020</p>
     * <p> Método que realiza a habilitação dos botões de alterar e apagar quando selecionado uma categoria na tabela</p>
     */
    private void tblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriasMouseClicked
        if(tblCategorias.getSelectedRow() != -1){
            categoria.setCodCatoria(Integer.parseInt(tblCategorias.getValueAt(tblCategorias.getSelectedRow(), 0).toString()));
            txtNomeCategoria.setText(tblCategorias.getValueAt(tblCategorias.getSelectedRow(), 1).toString());
            categoria.setNomeAnteriorCategoria(txtNomeCategoria.getText());
            txtDescricao.setText(tblCategorias.getValueAt(tblCategorias.getSelectedRow(), 2).toString());
        }
        
        //habilitando botões
        btnAlterar.setEnabled(true);
        btnApagar.setEnabled(true);
        btnCadastrar.setEnabled(false);
        
        btnPesquisar.setText("Cancelar");
        this.cancelar = true;
        
    }//GEN-LAST:event_tblCategoriasMouseClicked

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
            java.util.logging.Logger.getLogger(jDialogCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jDialogCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jDialogCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jDialogCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jDialogCategorias dialog = new jDialogCategorias(new javax.swing.JFrame(), true);
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
    private javax.swing.JToggleButton btnAlterar;
    private javax.swing.JToggleButton btnApagar;
    private javax.swing.JToggleButton btnCadastrar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNomeCategoria;
    // End of variables declaration//GEN-END:variables
}
