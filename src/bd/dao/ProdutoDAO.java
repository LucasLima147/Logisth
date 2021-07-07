/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.dao;
import bd.beans.Produto;
import bd.connectionFactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Autor: Lucas Lima e Hermane Boavida
 * <p> Data: 06/06/2020. <br>
 * Este é o DAO dos produtos, sendo responsável por fazer a manipulação dos dados do Banco de Dados</p>
 */
public class ProdutoDAO extends Produto{
    //atributo
    private ProdutoDAO produto;
    
    /**
     * Autores:Lucas Lima e Hermane Boavida
     * <p>Data: 11/08/2020. <br>
     * Este método realiza as inserções no banco de dados de produto</p>
     */
    public void insert() {
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo variável para preparar as SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("INSERT INTO produto(nome, descricao, codCategoria, "
                    + "qtdEstoque, preco, qtdMinima, codFornecedor)"
                    + "VALUES(?,?,?,?,?,?,?)");
            // passando por parâmentro os dados da inserção
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getDescricao());
            stmt.setInt(3, this.getCategoria());
            stmt.setInt(4, this.getQtdEstoque());
            stmt.setString(5, this.getPreco());
            stmt.setInt(6, this.getQtdMinima());
            stmt.setInt(7, this.getFornecedor());
            
            // executando o comando SQL
            stmt.executeUpdate();
            // sinalizando que a inserção foi realiza
            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Erro de Inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
        
    /**
     * Autores:Lucas Lima e Hermane Boavida
     * <p>Data: 11/08/2020. <br>
     * Este método realiza a pesquisa de produtos, retornando uma lista</p>
     */
    public List<Produto> select_PesquisaProduto(String query){
        // abrindo conexão com o Bando de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável de preparo para o SQL
        PreparedStatement stmt = null;
        // variável que guarda o resultado da consulta
        ResultSet rs = null;
        // variável para armazenar o resultado da consulta
        List<Produto> listaDeProdutoes = new ArrayList<>();
        
        // testando execução de consulta
        try {
            // definindo a SQL
            stmt = con.prepareStatement("SELECT * FROM produto " + query);
            // executando a SQL
            rs = stmt.executeQuery();
            
            //pegando os registros encontrados no Banco de Dados
            while(rs.next()){
                // instanciando um novo produto
                produto = new ProdutoDAO();
                //pegando os valres do Banco de Dados
                produto.setCodProduto(Integer.parseInt(rs.getString("codProduto")));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setCategoria(Integer.parseInt(rs.getString("codCategoria")));
                produto.setQtdEstoque(Integer.parseInt(rs.getString("qtdEstoque")));
                produto.setPreco(rs.getString("preco"));
                produto.setQtdMinima(Integer.parseInt(rs.getString("qtdMinima")));
                produto.setFornecedor(Integer.parseInt(rs.getString("codFornecedor")));
                // salvando na lista
                listaDeProdutoes.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        // retornando a lista de produtoes
        return listaDeProdutoes;
    }
    
    /**
     * Autores:Lucas Lima e Hermane Boavida
     * <p>Data: 01/10/2020. <br>
     * Este método realiza a pesquisa de produtos disponível para retirada</p>
     */
    public List<Produto> select_ProdutosDisponiveisParaRetirada(String query){
        // abrindo conexão com o Bando de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável de preparo para o SQL
        PreparedStatement stmt = null;
        // variável que guarda o resultado da consulta
        ResultSet rs = null;
        // variável para armazenar o resultado da consulta
        List<Produto> listaDeProdutoes = new ArrayList<>();
        
        // testando execução de consulta
        try {
            // definindo a SQL
            stmt = con.prepareStatement("SELECT * FROM produto " + query);
            // executando a SQL
            rs = stmt.executeQuery();
            
            //pegando os registros encontrados no Banco de Dados
            while(rs.next()){
                // instanciando um novo produto
                produto = new ProdutoDAO();
                //pegando os valres do Banco de Dados
                produto.setCodProduto(Integer.parseInt(rs.getString("codProduto")));
                produto.setNome(rs.getString("nome"));
                produto.setQtdEstoque(Integer.parseInt(rs.getString("qtdEstoque")));
                produto.setQtdMinima(Integer.parseInt(rs.getString("qtdMinima")));
                produto.setDescricao(rs.getString("descricao"));
                produto.setFornecedor(Integer.parseInt(rs.getString("codFornecedor")));
                // salvando na lista
                listaDeProdutoes.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        // retornando a lista de produtoes
        return listaDeProdutoes;
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 01/08/2020</p>
     * <p> Este método relaiza a consulta de todos os dados do produto</p>
     */
    public void select_dadosDoProduto(int codProduto){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        // variável aonde será guarado o resultado da consulta
        ResultSet rs = null;
        
        // testando a execução de consulta
        try {
            //defindo a SQL
            stmt = con.prepareStatement("SELECT * FROM produto WHERE codProduto = ?");
            stmt.setString(1, Integer.toString(codProduto));
            // executando a SQL
            rs = stmt.executeQuery();
            while(rs.next()){
                //pegando os valres do Banco de Dados
                this.setCodProduto(Integer.parseInt(rs.getString("codProduto")));
                this.setNome(rs.getString("nome"));
                this.setDescricao(rs.getString("descricao"));
                this.setCategoria(Integer.parseInt(rs.getString("codCategoria")));
                this.setQtdEstoque(Integer.parseInt(rs.getString("qtdEstoque")));
                this.setPreco(rs.getString("preco"));
                this.setQtdMinima(Integer.parseInt(rs.getString("qtdMinima")));
                this.setFornecedor(Integer.parseInt(rs.getString("codFornecedor")));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 11/08/2020</p>
     * <p> Este método realiza a a atualização de dados do produto </p>
     */
    public void update_DadosProduto(){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("UPDATE produto SET nome = ?, descricao = ?, codCategoria = ?, qtdEstoque = ?,"
                    + "preco = ?, qtdMinima = ?, codFornecedor = ? WHERE codProduto = ?");
            
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getDescricao());
            stmt.setInt(3, this.getCategoria());
            stmt.setInt(4, this.getQtdEstoque());
            stmt.setString(5, this.getPreco());
            stmt.setInt(6, this.getQtdMinima());
            stmt.setInt(7, this.getFornecedor());
            stmt.setInt(8, this.getCodProduto());
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
     * <p> Este método realiza a exclusão do produto </p>
     */
    public void Delete_Produto(){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("DELETE FROM produto WHERE codProduto = ?");
            stmt.setInt(1, this.getCodProduto());
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
     * <p> Data: 27/09/2020</p>
     * <p> Método para retornar o nome do produto correspondente a cada pedido/entrada</p>
     */
    public String nomeProduto(ProdutoDAO produto, int codProduto){
        produto.select_dadosDoProduto(codProduto);
        return produto.getNome();
    }
}
