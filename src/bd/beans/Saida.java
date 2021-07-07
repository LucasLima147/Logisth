/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.beans;

import bd.dao.ProdutoDAO;

/**
 *
 * @author LucasLima
 */
public abstract class Saida {
    // atributos (classe) - campos da tabela (BD)
    private int codSaida;
    private String data;
    private int qtdProduto;
    private int codProduto;
    private int codUsuario;
    
    // GETTERS & SETTERS
    public int getCodSaida() {
        return codSaida;
    }
    public void setCodSaida(int codSaida) {
        this.codSaida = codSaida;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }
    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public int getCodProduto() {
        return codProduto;
    }
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getCodUsuario() {
        return codUsuario;
    }
    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    // para liberar saídad de produto
    private int qtdEstoque;
    private int qtdMinima;
    private String preco;
    private String descricao;
    
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    public int getQtdEstoque() {
        return qtdEstoque;
    }
    
    public int getQtdMinima() {
        return qtdMinima;
    }
    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }
    
    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }
    
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
    // 01/10/2020 - liberar saída de produto
    public boolean liberarSaidaDeProduto(){
        boolean liberar = false;
        if(qtdProduto <= qtdEstoque){
            System.out.println("Passou aqui");
            liberar = true;
        }
        return liberar;
    }
   
}
