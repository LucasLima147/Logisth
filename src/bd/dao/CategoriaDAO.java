/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.dao;

import bd.beans.Categoria;
import bd.connectionFactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author LucasLima
 */
public class CategoriaDAO extends Categoria{
    
    private CategoriaDAO categoria;
    private String nomeAnteriorCategoria;

    public void setNomeAnteriorCategoria(String nomeAnteriorCategoria) {
        this.nomeAnteriorCategoria = nomeAnteriorCategoria;
    }
    
    /**
     * Autores:Lucas Lima e Hermane Boavida
     * <p>Data: 03/08/2020. <br>
     * Este método realiza as inserções no banco de dadods de Categoria</p>
     */
    public void insert() {
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo variável para preparar as SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("INSERT INTO categoria(nome, descricao) VALUES (?,?)");
            // passando por parâmentro os dados da inserção
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getDescricao());
            
            // executando o comando SQL
            stmt.executeUpdate();
            // sinalizando que a inserção foi realiza
            JOptionPane.showMessageDialog(null, "Categoria salva com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Erro de Inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     * Autores: Lucas Lima e Hermame Boavida
     * <p> Data: 01/08/2020</p>
     * <p> Este método pesquisa e retorna a lista de categorias cadastrados</p>
     */
    public List<Categoria> select_PesquisaCategoria(String query){
        // abrindo conexão com o Bando de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável de preparo para o SQL
        PreparedStatement stmt = null;
        // variável que guarda o resiltado da consulta
        ResultSet rs = null;
        // variável para armazenar o resultado da consulta
        List<Categoria> listaDeFornecedores = new ArrayList<>();
        
        // testando execução de consulta
        try {
            // definindo a SQL
            stmt = con.prepareStatement("SELECT * FROM categoria " + query);
            // executando a SQL
            rs = stmt.executeQuery();
            
            //pegando os registros encontrados no Banco de Dados
            while(rs.next()){
                // instanciando um novo categoria
                categoria = new CategoriaDAO();
                //pegando os valres do Banco de Dados
                categoria.setCodCatoria(Integer.parseInt(rs.getString("codCategoria")));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                // salvando na lista
                listaDeFornecedores.add(categoria);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        // retornando a lista de categorias
        return listaDeFornecedores;
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 01/08/2020</p>
     * <p> Este método relaiza a consulta de todos os dados do categoria</p>
     */
    public void select_dadosDaCategoria(int codCategoria){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        // variável aonde será guarado o resultado da consulta
        ResultSet rs = null;
        
        // testando a execução de consulta
        try {
            //defindo a SQL
            stmt = con.prepareStatement("SELECT * FROM categoria WHERE codCategoria = ?");
            stmt.setString(1, Integer.toString(codCategoria));
            // executando a SQL
            rs = stmt.executeQuery();
            while(rs.next()){
                this.setCodCatoria(rs.getInt("codCategoria"));
                this.setNome(rs.getString("nome"));
                this.setDescricao(rs.getString("descricao"));
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
   /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 01/08/2020</p>
     * <p> Este método relaiza a atualiza os dados da categoria selecionada</p>
    */
    public void update_DadosCategoria(){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // Atualizando a tabela de categorias no BD
            // SQL com parametros(?)
            stmt = con.prepareStatement("UPDATE categoria SET nome = ?, descricao = ? WHERE codCategoria= ?");
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getDescricao());
            stmt.setInt(3, this.getCodCatoria());
            
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
     * <p> Data: 01/08/2020</p>
     * <p> Este método que apagada o registro da categoria selecionada</p>
     */
    public void Delete_Categoria(){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("DELETE FROM categoria WHERE codCategoria = ?");
            
            stmt.setInt(1, this.getCodCatoria());
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
     * <p> Método para retornar o nome da caregoria correspondente a cada produto</p>
     */
    public String nomeCategoria(CategoriaDAO categoria, int codCategoria){
        categoria.select_dadosDaCategoria(codCategoria);
        return categoria.getNome();
    }
}
