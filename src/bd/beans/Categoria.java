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
 * Esta classe guarda os campos da tabela de Categoria do Banco de Dados.</p>
 */
public abstract class Categoria {
    private int codCatoria;
    private String nome;
    private String descricao;
    
    
    public int getCodCatoria() {
        return codCatoria;
    }
    public void setCodCatoria(int codCatoria) {
        this.codCatoria = codCatoria;
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
    @Override
    public String toString() {
        return getNome();
    }
}
