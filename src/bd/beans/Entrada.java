/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.beans;

/**
 * Autores: Lucas Lima e Hermane Boavida
 * <p>
 * Data: 14/09/2020. <br>
 * Esta classe guarda os campos da tabela de Entrada do Banco de Dados.</p>
 */
public abstract class Entrada {
    // Atributos
    private int codEntrada;
    private String dataPedido;
    private int codFuncionarioPedido;
    private int qtdPedido;
    private String dataEntrega;
    private int codFuncionarioEntrada;
    private int codProduto;
    private int codFornecedor;
    private int qtdEntrega;
    private String situacao;
    private String caminhoNF;
    
    // SETTERS & GETTERS
    public int getCodEntrada() {
        return codEntrada;
    }
    public void setCodEntrada(int codEntrada) {
        this.codEntrada = codEntrada;
    }
    
    public int getCodProduto() {
        return codProduto;
    }
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getQtdPedido() {
        return qtdPedido;
    }
    public void setQtdPedido(int qtdPedido) {
        this.qtdPedido = qtdPedido;
    }

    public String getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public int getCodFuncionarioPedido() {
        return codFuncionarioPedido;
    }
    public void setCodFuncionarioPedido(int codFuncionarioPedido) {
        this.codFuncionarioPedido = codFuncionarioPedido;
    }

    public int getCodFornecedor() {
        return codFornecedor;
    }
    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    public String getDataEntrada() {
        return dataEntrega;
    }
    public void setDataEntrada(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getCodFuncionarioEntrada() {
        return codFuncionarioEntrada;
    }
    public void setCodFuncionarioEntrada(int codFuncionarioEntrada) {
        this.codFuncionarioEntrada = codFuncionarioEntrada;
    }

    public int getQtdEntrega() {
        return qtdEntrega;
    }
    public void setQtdEntrega(int qtdEntrega) {
        this.qtdEntrega = qtdEntrega;
    }

    public String getSituacao() {
        return situacao;
    }
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getCaminhoNF() {
        return caminhoNF;
    }
    public void setCaminhoNF(String caminhoNF) {
        this.caminhoNF = caminhoNF;
    }
    
    
}
