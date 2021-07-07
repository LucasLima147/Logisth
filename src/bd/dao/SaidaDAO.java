/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.dao;

import bd.beans.Saida;
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
public class SaidaDAO extends Saida{
    // isntâncias
    SaidaDAO saida;
    ProdutoDAO produto;
    
    int qtdEstoque;
    int qtdMinima;
    String preco;
    String descricao;
    
    /**
     * Autores:Lucas Lima e Hermane Boavida
     * <p>Data: 01/10/2020. <br>
     * Este método realiza o registro (ou não) de saída do produto</p>
     */
    public void insert_retirarProdutoEstoque() {
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo variável para preparar as SQL
        PreparedStatement stmt = null;
        // variável que guarda o resultado da consulta
        ResultSet rs = null;
        //teste de execução do comando SQL
        try {
            // verificando se há estoque
            if(liberarSaidaDeProduto()){
                // inserindo na tabela de saída
                stmt = con.prepareStatement("INSERT INTO saida(data, qtdProduto, codProduto, codUsuario) VALUES(?,?,?,?)");
                // passando por parâmentro os dados da inserção
                stmt.setString(1, this.getData());
                stmt.setInt(2, this.getQtdProduto());
                System.out.println("COd: "+this.getCodProduto());
                stmt.setInt(3, this.getCodProduto());
                stmt.setInt(4, this.getCodUsuario());
                // executando o comando SQL
                stmt.executeUpdate();
                // subtraindo a quatidade retirada do produto na tabela de produto
                stmt = con.prepareStatement("UPDATE produto SET qtdEstoque = qtdEstoque - ? WHERE codProduto = ?");
                stmt.setInt(1, this.getQtdProduto());
                stmt.setInt(2, this.getCodProduto());
                // execução do comando SQL
                stmt.executeUpdate();
                // sinalizando que a inserção foi realiza
                JOptionPane.showMessageDialog(null, "Registro de saída de produto feito com sucesso");
            }else{
                // sinalizando que não há estoque suficiente
                JOptionPane.showMessageDialog(null, "Não há estoque suficiente para retirar essa quantidade");
            }
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Erro de Inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     * Autores:Lucas Lima e Hermane Boavida
     * <p>Data: 11/08/2020. <br>
     * Este método realiza a pesquisa de saidas realizadas, retornando uma lista</p>
     */
    public List<Saida> select_SaidaRealizadas(String query){
        // abrindo conexão com o Bando de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável de preparo para o SQL
        PreparedStatement stmt = null;
        // variável que guarda o resiltado da consulta
        ResultSet rs = null;
        // variável para armazenar o resultado da consulta
        List<Saida> listaDePedidos = new ArrayList<>();
        
        // testando execução de consulta
        try {
            // definindo a SQL
            stmt = con.prepareStatement("SELECT * FROM saida "
                    + "INNER JOIN  produto ON produto.codProduto = saida.codProduto " + query);
            // executando a SQL
            rs = stmt.executeQuery();
            
            //pegando os registros encontrados no Banco de Dados
            while(rs.next()){
                // instanciando um novo produto
                saida = new SaidaDAO();
                //pegando os valres do Banco de Dados
                saida.setCodSaida(Integer.parseInt(rs.getString("codSaida")));
                saida.setCodUsuario(Integer.parseInt(rs.getString("codUsuario")));
                saida.setData(rs.getString("data"));
                saida.setQtdProduto(Integer.parseInt(rs.getString("qtdProduto")));
                saida.setCodProduto(Integer.parseInt(rs.getString("codProduto")));
                saida.setQtdEstoque((Integer.parseInt(rs.getString("qtdEstoque"))));
                saida.setQtdMinima(Integer.parseInt(rs.getString("qtdMinima")));
                saida.setPreco(rs.getString("preco"));
                saida.setDescricao(rs.getString("descricao"));
                listaDePedidos.add(saida);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        // retornando a lista de produtoes
        return listaDePedidos;
    }
}
