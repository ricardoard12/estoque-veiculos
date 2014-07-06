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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Rogério Koglin
 */
@Entity
@Table(name = "produtos")
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "pro_codigo", nullable = false)
    private Integer proCodigo;
    @Column(name = "pro_nome", nullable = false, length = 45)
    private String proNome;
    @Column(name = "pro_descricao", nullable = true, length = 100)
    private String proDescricao;
    @Column(name = "pro_marca", nullable = false, length = 45)
    private String proMarca;
    @Column(name = "pro_img", nullable = true, length = 100)
    private String proImg;
    @Column(name = "pro_qtd", nullable = false, length = 4)
    private int proQtd;
    @Column(name = "pro_valor_compra", nullable = false)
    private double proValorCompra;
    @Column(name = "pro_valor_venda", nullable = false)
    private double proValorVenda;
    @Column(name = "pro_observacao", nullable = true, length = 60)
    private String proObservacao;

    @OneToMany(mappedBy = "pro_codigo", fetch = FetchType.LAZY)
    @ForeignKey(name = "ProdutoItensVenda")
    private List<ItensVenda> itensVenda;

    @ManyToOne(optional = false)
    @ForeignKey(name = "FornecedorProduto")
    @JoinColumn(name = "pro_for_codigo", referencedColumnName = "for_codigo")
    private Fornecedores fornecedores;

    @ManyToOne(optional = false)
    @ForeignKey(name = "CategoriaProduto")
    @JoinColumn(name = "pro_cat_codigo", referencedColumnName = "cat_codigo")
    private Categorias categorias;

    public Produtos() {
        this.fornecedores = new Fornecedores();
        this.categorias = new Categorias();
    }

    public Integer getProCodigo() {
        return proCodigo;
    }

    public void setProCodigo(Integer proCodigo) {
        this.proCodigo = proCodigo;
    }

    public String getProNome() {
        return proNome;
    }

    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    public String getProDescricao() {
        return proDescricao;
    }

    public void setProDescricao(String proDescricao) {
        this.proDescricao = proDescricao;
    }

    public String getProMarca() {
        return proMarca;
    }

    public void setProMarca(String proMarca) {
        this.proMarca = proMarca;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }

    public int getProQtd() {
        return proQtd;
    }

    public void setProQtd(int proQtd) {
        this.proQtd = proQtd;
    }

    public double getProValorCompra() {
        return proValorCompra;
    }

    public void setProValorCompra(double proValorCompra) {
        this.proValorCompra = proValorCompra;
    }

    public double getProValorVenda() {
        return proValorVenda;
    }

    public void setProValorVenda(double proValorVenda) {
        this.proValorVenda = proValorVenda;
    }

    public String getProObservacao() {
        return proObservacao;
    }

    public void setProObservacao(String proObservacao) {
        this.proObservacao = proObservacao;
    }

    public List<ItensVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItensVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public Fornecedores getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(Fornecedores fornecedores) {
        this.fornecedores = fornecedores;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.proCodigo != null ? this.proCodigo.hashCode() : 0);
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
        final Produtos other = (Produtos) obj;
        if (this.proCodigo != other.proCodigo && (this.proCodigo == null || !this.proCodigo.equals(other.proCodigo))) {
            return false;
        }
        return true;
    }
}
