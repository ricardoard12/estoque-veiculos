/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.estoque.Model.Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Rogério Koglin
 */
@Entity
@Table(name = "itens_venda")
public class ItensVenda implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "itv_codigo",nullable = false)
    private Integer itvCodigo;
    @Column(name = "itv_quantidade",nullable = false,length = 4)
    private int itvQuantidade;
    
    @ManyToOne(optional = false)
    @ForeignKey(name = "ProdutoItensVenda")
    @JoinColumn(name = "itv_pro_codigo", referencedColumnName = "pro_codigo")    
    private Produtos itvProCodigo;
    
    @ManyToOne(optional = false)
    @ForeignKey(name = "VendasItensVenda")
    @JoinColumn(name = "itv_ven_codigo", referencedColumnName = "ven_cod")
    private Vendas itvVenCodigo;

    public ItensVenda() {
    }

    public Integer getItvCodigo() {
        return itvCodigo;
    }

    public void setItvCodigo(Integer itvCodigo) {
        this.itvCodigo = itvCodigo;
    }

    public int getItvQuantidade() {
        return itvQuantidade;
    }

    public void setItvQuantidade(int itvQuantidade) {
        this.itvQuantidade = itvQuantidade;
    }

    public Produtos getItvProCodigo() {
        return itvProCodigo;
    }

    public void setItvProCodigo(Produtos itvProCodigo) {
        this.itvProCodigo = itvProCodigo;
    }

    public Vendas getItvVenCodigo() {
        return itvVenCodigo;
    }

    public void setItvVenCodigo(Vendas itvVenCodigo) {
        this.itvVenCodigo = itvVenCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.itvCodigo != null ? this.itvCodigo.hashCode() : 0);
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
        final ItensVenda other = (ItensVenda) obj;
        if (this.itvCodigo != other.itvCodigo && (this.itvCodigo == null || !this.itvCodigo.equals(other.itvCodigo))) {
            return false;
        }
        return true;
    }
    
}
