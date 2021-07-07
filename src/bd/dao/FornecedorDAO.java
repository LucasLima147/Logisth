/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.dao;
import bd.beans.Fornecedor;
import bd.connectionFactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Autores: Lucas Lima e Hermane Boavida
 * <p>
 * Data: 06/06/2020. <br>
 * Este é o DAO dos fornecedores, sendo responsável por fazer a manipulação dos
 * dados do Banco de Dados</p>
 */
public class FornecedorDAO extends Fornecedor {
    
    //atributo
    private FornecedorDAO fornecedor;
    
    /**
     * Autores:Lucas Lima e Hermane Boavida
     * <p>Data: 07/06/2020. <br>
     * Este método realiza as inserções no banco de dados de fornecedor</p>
     */
    public void insert() {
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo variável para preparar as SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("INSERT INTO fornecedor(cnpj, tipoPessoa, razaoSocial, "
                    + "nomeFantasia, dataCadastro, endereco, bairro, cidade, "
                    + "uf, celular, telefone, email)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            // passando por parâmentro os dados da inserção
            stmt.setString(1, this.getCnpj());
            stmt.setString(2, this.getTipoPessoa());
            stmt.setString(3, this.getRazaoSocial());
            stmt.setString(4, this.getNomeFantasia());
            stmt.setString(5, this.getDataCadastro());
            stmt.setString(6, this.getEndereco());
            stmt.setString(7, this.getBairro());
            stmt.setString(8, this.getCidade());
            stmt.setString(9, this.getUf());
            stmt.setString(10, this.getCelular());
            stmt.setString(11, this.getTelefone());
            stmt.setString(12, this.getEmail());
            // executando o comando SQL
            stmt.executeUpdate();
            // sinalizando que a inserção foi realiza
            JOptionPane.showMessageDialog(null, "Fornecedor salvo com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Erro de Inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
        
    /**
     * Autores: Lucas Lima e Hermame Boavida
     * <p> Data: 13/07/2020</p>
     * <p> Este método pesquisa e retorna a lista de Fornecedores cadastrados</p>
     */
    public List<Fornecedor> select_PesquisaFornecedor(String query){
        // abrindo conexão com o Bando de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável de preparo para o SQL
        PreparedStatement stmt = null;
        // variável que guarda o resiltado da consulta
        ResultSet rs = null;
        // variável para armazenar o resultado da consulta
        List<Fornecedor> listaDeFornecedores = new ArrayList<>();
        
        // testando execução de consulta
        try {
            // definindo a SQL
            stmt = con.prepareStatement("SELECT * FROM fornecedor " + query);
            // executando a SQL
            rs = stmt.executeQuery();
            
            //pegando os registros encontrados no Banco de Dados
            while(rs.next()){
                // instanciando um novo fornecedor
                fornecedor = new FornecedorDAO();
                //pegando os valres do Banco de Dados
                fornecedor.setCodFornecedor(Integer.parseInt(rs.getString("codFornecedor")));
                fornecedor.setNomeFantasia(rs.getString("nomeFantasia"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setCelular(rs.getString("celular"));
                fornecedor.setEmail(rs.getString("email"));
                // salvando na lista
                listaDeFornecedores.add(fornecedor);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        // retornando a lista de fornecedores
        return listaDeFornecedores;
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 01/08/2020</p>
     * <p> Este método relaiza a consulta de todos os dados do fornecedor</p>
     */
    public void select_dadosDoFornecedor(int codFornecedor){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        // variável aonde será guarado o resultado da consulta
        ResultSet rs = null;
        
        // testando a execução de consulta
        try {
            //defindo a SQL
            stmt = con.prepareStatement("SELECT * FROM fornecedor WHERE codFornecedor = ?");
            stmt.setString(1, Integer.toString(codFornecedor));
            // executando a SQL
            rs = stmt.executeQuery();
            while(rs.next()){
                this.setCodFornecedor(rs.getInt("codFornecedor"));
                this.setCnpj(rs.getString("cnpj"));
                this.setTipoPessoa(rs.getString("tipoPessoa"));
                this.setRazaoSocial(rs.getString("razaoSocial"));
                this.setNomeFantasia(rs.getString("nomeFantasia"));
                this.setDataCadastro(rs.getString("dataCadastro"));
                this.setEndereco(rs.getString("endereco"));
                this.setBairro(rs.getString("bairro"));
                this.setCidade(rs.getString("cidade"));
                this.setUf(rs.getString("uf"));
                this.setCelular(rs.getString("celular"));
                this.setTelefone(rs.getString("telefone"));
                this.setEmail(rs.getString("email"));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 13/07/2020</p>
     * <p> Este método realiza a atualização de dados do fornecedor </p>
     */
    public void update_DadosFornecedor(){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("UPDATE fornecedor SET razaoSocial = ?,nomeFantasia = ?, endereco = ?, bairro = ?, "
                    + "cidade = ?, uf = ?, celular = ?, telefone = ?, email = ? WHERE codFornecedor = ?");
            
            stmt.setString(1, this.getRazaoSocial());
            stmt.setString(2, this.getNomeFantasia());
            stmt.setString(3, this.getEndereco());
            stmt.setString(4, this.getBairro());
            stmt.setString(5, this.getCidade());
            stmt.setString(6, this.getUf());
            stmt.setString(7, this.getCelular());
            stmt.setString(8, this.getTelefone());
            stmt.setString(9, this.getEmail());
            stmt.setInt(10, this.getCodFornecedor());
            // execução do comando SQL
            stmt.executeUpdate();
            // sinalizando que a inserção foi realiza
            JOptionPane.showMessageDialog(null, "Alterado com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de alteração: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 13/07/2020</p>
     * <p> Este método realiza a exclusão do registro do fornecedor </p>
     */
    public void Delete_Fornecedor(){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("DELETE FROM fornecedor WHERE codFornecedor = ?");
            
            stmt.setInt(1, this.getCodFornecedor());
            // execução do comando SQL
            stmt.executeUpdate();
            // sinalizando que a inserção foi realiza
            JOptionPane.showMessageDialog(null, "Apagado com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de alteração: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 12/08/2020</p>
     * <p> Método para retornar o nome do fornecedor correspondente a cada produto</p>
     */
    public String nomeFornecedor(FornecedorDAO fornecedor, int codFornecedor){
        fornecedor.select_dadosDoFornecedor(codFornecedor);
        return fornecedor.getNomeFantasia();
    }
}