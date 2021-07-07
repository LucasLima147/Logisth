/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.beans;

import bd.dao.ProdutoDAO;

/**
 * Autor: Lucas Lima e Hermane Boavida
 * <p> Data: 06/06/2020. <br>
 * Esta classe é guarda campos da tabela de Produto do Banco de Dados..</p>
 */
public abstract class Produto {
    // atributos (classe) - campos da tabela (BD)
    private int codProduto;
    private String nome;
    private String descricao;
    private int categoria;
    private int qtdEstoque;
    private String preco;
    private int qtdMinima;
    private int fornecedor;
    
    // classe produto
    private ProdutoDAO produto;
    
    // SETTERS E GETTERS
    public int getCodProduto() {
        return codProduto;
    }
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }

    public int getQtdMinima() {
        return qtdMinima;
    }
    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public int getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }

    public ProdutoDAO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDAO produto) {
        this.produto = produto;
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
