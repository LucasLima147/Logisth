/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Autor: Lucas Lima e Hermane Boavida
 * <p>
 * Data: 06/06/2020. <br>
 * Classe responsável pela conexão com o Banco de Dados. <br>
 * Dentro da classe temos os dados de conexão (em forma de constantes) e um
 * método que realiza a conexão</p>
 */
public abstract class ConnectionFactory {

    // Constantes de conexão com BD 
    // Drive do jdbc para conexão de com o MySQL
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    // Caminho de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3307/dbestoque";
    // Nome de login para conexão
    private static final String USER = "root";
    // Senha de Login para conexão
    private static final String PASS = "vertrigo";

    /**
     * Autor: Lucas Lima e Hermane Boavida
     * <p>
     * Data: 06/06/2020. <br>
     * Este método é responsável por realizar a conexão com o Banco de Dados</p>
     */
    public static Connection startConnection() {
        // testando a conexão
        try {
            // carregando a classe do Driver
            Class.forName(DRIVER);
            System.out.println("Fazendo a conexão");
            // retornando o caminho, com o login e senha, do Banco de Dados 
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            // caso aconteça algum problema na conexão
            throw new RuntimeException("Erro na conexão: ", ex);
        }
    }

    /**
     * Autor: Lucas Lima e Hermane Boavida
     * <p>
     * Data: 06/06/2020. <br>
     * Este método é responsável para fechar a conexão com o Banco de Dados e
     * não haver uma sobrecarga de processamento. <br>
     * (Está é a verificação com o paramentro Connection)</p>
     */
    public static void closeConnection(Connection conexao) {
        try {
            // verificando se a conexão está aberta
            if (conexao != null) {
                // fechando a conexão
                conexao.close();
                System.out.println("Conexão Fechada com sucesso");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("1 - Não foi possível fechar a conexão: ", ex);
        }
    }
    
    /**
     * Autor: Lucas Lima e Hermane Boavida
     * <p>
     * Data: 06/06/2020. <br>
     * Este método é responsável para fechar a conexão com o Banco de Dados e
     * não haver uma sobrecarga de processamento. <br>
     * (Está é a verificação com o paramentro Connection e PreparedStatement)</p>
     */
    public static void closeConnection(Connection conexao, PreparedStatement stmt) {
        // chamando o outro método para fechar a conexão
        closeConnection(conexao);
        
        try {
            // verificando se a conexão está aberta
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("2 - Não foi possível fechar a conexão: ", ex);
        }
    }
    
    /**
     * Autor: Lucas Lima e Hermane Boavida
     * <p>
     * Data: 06/06/2020. <br>
     * Este método é responsável para fechar a conexão com o Banco de Dados e
     * não haver uma sobrecarga de processamento. <br>
     * (Está é a verificação com o paramentro Connection, PreparedStatement e ResultSet)</p>
     */
    public static void closeConnection(Connection conexao, PreparedStatement stmt, ResultSet result) {
        // chamando o outro método para fechar a conexão
        closeConnection(conexao, stmt);
        
        try {
            // verificando se a conexão está aberta
            if(result != null){
                result.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("3 - Não foi possível fechar a conexão: ", ex);
        }
    }
    
    
}
