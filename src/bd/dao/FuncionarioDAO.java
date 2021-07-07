/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.dao;

import bd.beans.Funcionario;
import bd.connectionFactory.ConnectionFactory;
import main.LoginUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Autores: Lucas Lima e Hermane Boavida
 * <p> Data: 06/06/2020. <br>
 * Este é o DAO dos funcionarios, sendo responsável por fazer a manipulação dos dados do Banco de Dados</p>
 */
public class FuncionarioDAO extends Funcionario{
    
    //atributos
    private FuncionarioDAO funcionario;
    LoginUsuario usuario = new LoginUsuario();
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 07/06/2020<br>
     * Este método realiza as inserções na tabela de Funcionário</p>
     */
    public void insert(){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("INSERT INTO funcionario(nome, cpf,"
                    + "dataNascimento, sexo, endereco, bairro, cidade, uf,"
                    + "celular, email, cargo, dataContratacao, senha)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getCpf());
            stmt.setString(3, this.getDataNascimento());
            stmt.setString(4, this.getSexo());
            stmt.setString(5, this.getEndereco());
            stmt.setString(6, this.getBairro());
            stmt.setString(7, this.getCidade());
            stmt.setString(8, this.getUf());
            stmt.setString(9, this.getCelular());
            stmt.setString(10, this.getEmail());
            stmt.setString(11, this.getCargo());
            stmt.setString(12, this.getDataContratacao());
            stmt.setString(13, this.getSenha());
            // execução do comando SQL
            stmt.executeUpdate();
            // sinalizando que a inserção foi realiza
            JOptionPane.showMessageDialog(null, "Funcionário salvo com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de inserção: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 07/06/2020<br>
     * Este método pesquisa e retorna a lista a ser carregada na tabela de usuários</p>
     */
    public List<Funcionario> select_PesquisaUsuario(String query){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável de preparo para o SQL
        PreparedStatement stmt = null;
        // Variável aonde será guardado o resultado da consulta
        ResultSet rs = null;
        
        //Declarando uma lista para salvar os valores
        List<Funcionario> listaDeFuncionario = new ArrayList<>();
        
        // testando execução de consulta
        try {
            // definindo a SQL
            stmt = con.prepareStatement("SELECT * FROM funcionario " + query);
            // executando a SQL
            rs = stmt.executeQuery();
            
            // pegando todos os registros do Bando de Dados
            while(rs.next()){
                // instancianciando um novo  funcionario
                funcionario = new FuncionarioDAO();
                // pegando os valores do Banco de Dados
                funcionario.setCodUsuario(rs.getInt("codUsuario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setDataContratacao(rs.getString("dataContratacao"));
                // salvando na lista
                listaDeFuncionario.add(funcionario);
//                System.out.println("Nome; "+funcionario.getNome());
            }
//            JOptionPane.showMessageDialog(null, "Consulta bem-sucessedida");   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        // retornando a lista de funcionarios
        return listaDeFuncionario;
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 08/07/2020<br>
     * Este método realiza as consulta de todos os dados do funcionário</p>
     */
    public void select_DadosDoUsuario(int codUser){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        // Variável aonde será guardado o resultado da consulta
        ResultSet rs = null;
        
        // testando execução de consulta
        try {
            // defindo a SQL
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE codUsuario = ?");
            stmt.setString(1, Integer.toString(codUser));
            // executando a SQL
            rs = stmt.executeQuery();
            while(rs.next()){
                this.setCodUsuario(rs.getInt("codUsuario"));
                this.setNome(rs.getString("nome"));
                this.setCpf(rs.getString("cpf"));
                this.setDataNascimento(rs.getString("dataNascimento"));
                this.setSexo(rs.getString("sexo"));
                this.setEndereco(rs.getString("endereco"));
                this.setBairro(rs.getString("bairro"));
                this.setCidade(rs.getString("cidade"));
                this.setUf(rs.getString("Uf"));
                this.setCelular(rs.getString("celular"));
                this.setEmail(rs.getString("email"));
                this.setCargo(rs.getString("cargo"));
                this.setDataContratacao(rs.getString("dataContratacao"));
                this.setSenha(rs.getString("senha"));
            }
//            JOptionPane.showMessageDialog(null, "Consulta bem-sucessedida");   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de consulta: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 09/07/2020<br>
     * Este método válida o login e senha inicial do sistema para futuras consultas de login</p>
     */
    public boolean select_VerificaLogin(String login, String senha){
        boolean loginValido = false;
        
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        // Variável aonde será guardado o resultado da consulta
        ResultSet rs = null;
        
        // testando a Conexão
        try {
            // defindo a SQL
            stmt = con.prepareStatement("SELECT codUsuario, senha, nome, cargo FROM funcionario WHERE codUsuario = ?");
            stmt.setString(1, login);
            // executando a SQL
            rs = stmt.executeQuery();
            while(rs.next()){
                if(login.equals(rs.getString("codUsuario")) && senha.equals(rs.getString("senha"))){
                    usuario.setUsuario(login);
                    usuario.setSenha(senha);
                    usuario.setNomeUsuario(rs.getString("nome"));
                    usuario.setCargo(rs.getString("cargo"));
                    loginValido = true;
                    break;
                }
            }
//            JOptionPane.showMessageDialog(null, "Bem vindo " + login);   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar login: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return loginValido;
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 09/07/2020<br>
     * Este método realiza as atualizações de dados do Funcionário</p>
     */
    public void update_DadosUsuario(){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("UPDATE funcionario SET endereco = ?, bairro = ?, cidade = ?, uf = ?,"
                    + "celular = ?, email = ?, cargo = ?, dataContratacao = ?, senha = ? WHERE codUsuario = ?");
            
            stmt.setString(1, this.getEndereco());
            stmt.setString(2, this.getBairro());
            stmt.setString(3, this.getCidade());
            stmt.setString(4, this.getUf());
            stmt.setString(5, this.getCelular());
            stmt.setString(6, this.getEmail());
            stmt.setString(7, this.getCargo());
            stmt.setString(8, this.getDataContratacao());
            stmt.setString(9, this.getSenha());
            stmt.setInt(10, this.getCodUsuario());
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
     * <p> Data: 09/07/2020<br>
     * Este método realiza as atualizações da senha do Funcionário</p>
     */
    public void update_Senha(){
        // abrindo a conexão com o Banco de Dados
        Connection con = ConnectionFactory.startConnection();
        // definindo a variável para preparar o SQL
        PreparedStatement stmt = null;
        //teste de execução do comando SQL
        try {
            // SQL com parametros(?)
            stmt = con.prepareStatement("UPDATE funcionario SET senha = ? WHERE codUsuario = ?");
            
            stmt.setString(1, this.getSenha());
            stmt.setInt(2, this.getCodUsuario());
            // execução do comando SQL
            stmt.executeUpdate();
            // sinalizando que a inserção foi realiza
            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso");
            
            LoginUsuario.setSenha(this.getSenha());
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de alteração: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p> Data: 27/09/2020</p>
     * <p> Método para retornar o nome do Funcionário correspondente a cada pedido/entrada</p>
     */
    public String nomeFuncionario(FuncionarioDAO funcionario, int codFuncionario){
        funcionario.select_DadosDoUsuario(codFuncionario);
        return funcionario.getNome();
    }
}       
 