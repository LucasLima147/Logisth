/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.beans;
/**
 * Autor: Lucas Lima e Hermane Boavida
 * <p> Data: 06/06/2020. <br>
 * Esta classe é guarda campos da tabela de funcionario do Banco de Dados.</p>
 */
public abstract class Funcionario {
    // atributos (classe) - campos da tabela (BD)
    private int codUsuario;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String sexo;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String celular;
    private String email;
    private String cargo;
    private String dataContratacao;
    private String senha;
    
    private static String login;
    private static String password;
    
    // SETTERS E GETTERS
    public int getCodUsuario() {
        return codUsuario;
    }
    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDataContratacao() {
        return dataContratacao;
    }
    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p>Data: 13/09/2020</p>
     * <p>Reescrevendo a função to String para retornar o nome do produto</p>
     */
    @Override
    public String toString() {
        return getNome();
    }
}
