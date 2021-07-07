package main;
import bd.connectionFactory.ConnectionFactory;
import janelas.frmLogin;
import java.sql.Connection;

/**
 *
 * Autor: Lucas Lima e Hermane Boavida
 * <p> Data: 13/05/2020</p>
 * <p> Classe para incializar o programa chamando a tela de Login Inicial</p>
 * 
 */
public class Main {

    public static void main(String[] args) {
        frmLogin inicioPrograma = new frmLogin();
        inicioPrograma.setVisible(true);
    }
    
}
