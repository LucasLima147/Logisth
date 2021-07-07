
package bd.dao;
import bd.beans.Entrada;
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
public class EntradaDAO extends Entrada{
    // atributos
    private EntradaDAO entrada;
    
    /**
     * Autores:Lucas Lima e Hermane Boavida
     * <p>Data: 14/09/2020. <br>
     * Este método realiza a inserção do pedido de produtos</p>
     */
    public void insert_realizarPedido() {
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo variável para preparar as SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("INSERT INTO entrada(codProduto, qtdPedido, dataPedido, "
                    + "userPedido, codFornecedor, situacao) VALUES(?,?,?,?,?,?)");
            // passando por parâmentro os dados da inserção
            stmt.setInt(1, this.getCodProduto());
            stmt.setInt(2, this.getQtdPedido());
            stmt.setString(3, this.getDataPedido());
            stmt.setInt(4, this.getCodFuncionarioPedido());
            stmt.setInt(5, this.getCodFornecedor());
            stmt.setString(6, this.getSituacao());
            // executando o comando SQL
            stmt.executeUpdate();
            // sinalizando que a inserção foi realiza
            JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Erro de Inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     * Autores:Lucas Lima e Hermane Boavida
     * <p>Data: 11/08/2020. <br>
     * Este método realiza a pesquisa de pedidos, retornando uma lista</p>
     */
    public List<Entrada> select_PedidosRealizados(String query){
        // abrindo conexão com o Bando de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável de preparo para o SQL
        PreparedStatement stmt = null;
        // variável que guarda o resiltado da consulta
        ResultSet rs = null;
        // variável para armazenar o resultado da consulta
        List<Entrada> listaDePedidos = new ArrayList<>();
        
        // testando execução de consulta
        try {
            // definindo a SQL
            stmt = con.prepareStatement("SELECT * FROM entrada " + query);
            // executando a SQL
            rs = stmt.executeQuery();
            
            //pegando os registros encontrados no Banco de Dados
            while(rs.next()){
                System.out.println(rs);
                entrada = new EntradaDAO();
                //pegando os valres do Banco de Dados
                entrada.setCodEntrada(Integer.parseInt(rs.getString("codEntrada")));
                entrada.setCodProduto(Integer.parseInt(rs.getString("codProduto")));
                entrada.setDataPedido(rs.getString("dataPedido"));
                entrada.setQtdPedido(Integer.parseInt(rs.getString("qtdPedido")));
                entrada.setCodFornecedor(Integer.parseInt(rs.getString("codFornecedor")));
                entrada.setCodFuncionarioPedido(Integer.parseInt(rs.getString("userPedido")));
                listaDePedidos.add(entrada);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            System.out.println(listaDePedidos.size());
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        // retornando a lista de produtoes
        return listaDePedidos;
    }
    
     /**
     * Autores:Lucas Lima e Hermane Boavida
     * <p>Data: 24/10/2020. <br>
     * Este método realiza a pesquisa de entradas, retornando uma lista</p>
     */
    public List<Entrada> select_EntradasRealizados(String query){
        // abrindo conexão com o Bando de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável de preparo para o SQL
        PreparedStatement stmt = null;
        // variável que guarda o resiltado da consulta
        ResultSet rs = null;
        // variável para armazenar o resultado da consulta
        List<Entrada> listaDePedidos = new ArrayList<>();
        
        // testando execução de consulta
        try {
            // definindo a SQL
            stmt = con.prepareStatement("SELECT * FROM entrada " + query);
            // executando a SQL
            rs = stmt.executeQuery();
            
            //pegando os registros encontrados no Banco de Dados
            while(rs.next()){
                System.out.println(rs);
                entrada = new EntradaDAO();
                //pegando os valres do Banco de Dados
                entrada.setCodEntrada(Integer.parseInt(rs.getString("codEntrada")));
                entrada.setCodProduto(Integer.parseInt(rs.getString("codProduto")));
                entrada.setDataPedido(rs.getString("dataPedido"));
                entrada.setQtdPedido(Integer.parseInt(rs.getString("qtdPedido")));
                entrada.setCodFornecedor(Integer.parseInt(rs.getString("codFornecedor")));
                entrada.setCodFuncionarioPedido(Integer.parseInt(rs.getString("userPedido")));
                entrada.setDataEntrada(rs.getString("dataEntrega"));
                entrada.setQtdEntrega(Integer.parseInt(rs.getString("qtdEntrega")));
                entrada.setCodFuncionarioEntrada(Integer.parseInt(rs.getString("userEntrada")));
                entrada.setCaminhoNF(rs.getString("caminhoNF"));
                entrada.setSituacao(rs.getString("situacao"));
                listaDePedidos.add(entrada);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        // retornando a lista de produtoes
        return listaDePedidos;
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 27/09/2020</p>
     * <p> Este método realiza a a atualização de dados do produto </p>
     */
    public void update_EntradaDeProdutos(boolean isEntrada, boolean isReabertura){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("UPDATE entrada SET dataEntrega = ?, userEntrada = ?, qtdEntrega = ?, caminhoNF = ?,"
                    + "situacao = ? WHERE codEntrada = ?");
            
            stmt.setString(1, this.getDataEntrada());
            stmt.setInt(2, this.getCodFuncionarioEntrada());
            stmt.setInt(3, this.getQtdEntrega());
            stmt.setString(4, this.getCaminhoNF());
            stmt.setString(5, this.getSituacao());
            stmt.setInt(6, this.getCodEntrada());
            // execução do comando SQL
            stmt.executeUpdate();
            
            stmt = con.prepareStatement("UPDATE produto SET qtdEstoque = qtdEstoque + ? WHERE codProduto = ?");
            stmt.setInt(1, this.getQtdEntrega());
            stmt.setInt(2, this.getCodProduto());
            // execução do comando SQL
            stmt.executeUpdate();
            //verificando se foi uma entrada de produto ou cancelamento de pedido
            if(isEntrada){
                // sinalizando que a Entrada foi realiza
                JOptionPane.showMessageDialog(null, "Entrada de produto feita com sucesso");
            }else{
                if(isReabertura){
                    // sinalizando a reaburtura do pedido
                    JOptionPane.showMessageDialog(null, "Pedido reaberto com sucesso");
                }else{
                    // sinalizando o cancelamento do pedido
                    JOptionPane.showMessageDialog(null, "Pedido cancelado com sucesso");   
                }
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de alteração: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
