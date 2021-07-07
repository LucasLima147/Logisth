package main;

import janelas.frmRegistroFuncionario;

/**
 *
 * Autor: Lucas Lima e Hermane Boavida
 * <p> Data: 13/05/2020</p>
 * <p> Classe para armazenar o login e Senha do Usuário<br>
 * OBS: também é usada para verificar os logins antes das alterações de dados</p>
 * 
 */
public class LoginUsuario {
    // Atributos
    private static String usuario;
    private static String senha;
    private static String nomeUsuario;
    private static String cargo;
    
    // SETTERS E GETTERS
    public static String getUsuario() {
        return usuario;
    }
    public static void setUsuario(String usuario) {
        LoginUsuario.usuario = usuario;
    }
    
    public static String getSenha() {
        return senha;
    }
    public static void setSenha(String senha) {
        LoginUsuario.senha = senha;
    }

    public static String getNomeUsuario() {
        return nomeUsuario;
    }
    public static void setNomeUsuario(String nomeUsuario) {
        LoginUsuario.nomeUsuario = nomeUsuario;
    }

    public static String getCargo() {
        return cargo;
    }
    public static void setCargo(String cargo) {
        LoginUsuario.cargo = cargo;
    }
    
    // MEUS MÉTODOS
    public boolean validaUsuario(String login, String password){
        if((login.equals(usuario)) & (password.equals(senha))){
            return true;
        }else{
            return false;
        }
    }
}
