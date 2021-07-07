/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.beans;

/**
 * Autor: Lucas Lima e Hermane Boavida
 * <p> Data: 06/06/2020. <br>
 * Esta classe é guarda campos da tabela de Fornecedor do Banco de Dados.</p>
 */
public abstract class Fornecedor{
    // atributos (classe) - campos da tabela (BD)
    private int codFornecedor;
    private String cnpj;
    private String tipoPessoa;
    private String razaoSocial;
    private String nomeFantasia;
    private String dataCadastro;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String celular;
    private String telefone;
    private String email;

    // SETTERS E GETTERS
    public int getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }
    
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }
    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
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

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Autores: Lucas Lima e Hermane Boavida
     * <p>Data: 03/08/2020</p>
     * <p>Reescrevendo a função to String para retornar o nome fatasia do fornecedor</p>
     */
    @Override
    public String toString() {
        return getNomeFantasia();
    }
    
    // método para formar o endereço completo do fornecedor
    public String escreveEnderecoCompleto(){
        String enderecoCompleto;
        enderecoCompleto = this.endereco+", "+this.bairro+", "+this.cidade+" - "+this.uf;
        return enderecoCompleto;
    }
}
