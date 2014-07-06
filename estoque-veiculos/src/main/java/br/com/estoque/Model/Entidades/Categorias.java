/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.estoque.Model.Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Rogério Koglin
 */
@Entity
@Table(name = "categorias")
public class Categorias implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "cat_codigo",nullable = false)
    private Integer catCodigo;
    @Column(name = "cat_nome",nullable = false,length = 40)
    private String catNome;
    
    @OneToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    @ForeignKey(name="CategoriaProduto")
    private List<Produtos> produtos;

    public Categorias() {
    }

    public Integer getCatCodigo() {
        return catCodigo;
    }

    public void setCatCodigo(Integer catCodigo) {
        this.catCodigo = catCodigo;
    }

    public String getCatNome() {
        return catNome;
    }

    public void setCatNome(String catNome) {
        this.catNome = catNome;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.catCodigo != null ? this.catCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categorias other = (Categorias) obj;
        if (this.catCodigo != other.catCodigo && (this.catCodigo == null || !this.catCodigo.equals(other.catCodigo))) {
            return false;
        }
        return true;
    }

    
}
