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
@Table(name = "cidade")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "cid_codigo", nullable = false)
    private Integer cid_codigo;
    @Column(name = "cid_nome", nullable = false, length = 45)
    private String cid_nome;

    @OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
    @ForeignKey(name = "ClienteCidade")
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
    @ForeignKey(name = "FornecedorCidade")
    private List<Fornecedor> fornecedores;

    @ManyToOne(optional = false)
    @ForeignKey(name = "CidadeEstado")
    @JoinColumn(name = "cid_est_sigla", referencedColumnName = "est_sigla")
    private Estado estado;

    public Cidade() {
        this.estado = new Estado();
    }

    public Integer getCid_codigo() {
        return cid_codigo;
    }

    public void setCid_codigo(Integer cid_codigo) {
        this.cid_codigo = cid_codigo;
    }

    public String getCid_nome() {
        return cid_nome;
    }

    public void setCid_nome(String cid_nome) {
        this.cid_nome = cid_nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.cid_codigo != null ? this.cid_codigo.hashCode() : 0);
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
        final Cidade other = (Cidade) obj;
        if (this.cid_codigo != other.cid_codigo && (this.cid_codigo == null || !this.cid_codigo.equals(other.cid_codigo))) {
            return false;
        }
        return true;
    }
}
