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
@Table(name = "fornecedores")
public class Fornecedores implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "for_codigo", nullable = false)
    private Integer forCodigo;
    @Column(name = "for_razao", nullable = false, length = 45)
    private String forRazao;
    @Column(name = "for_nome_fantasia", nullable = true, length = 45)
    private String forNomeFantasia;
    @Column(name = "for_cnpj", nullable = false, length = 14)
    private char forCnpj;
    @Column(name = "for_endereco", nullable = false, length = 50)
    private String forEndereco;
    @Column(name = "for_cep", nullable = true, length = 8)
    private String forCep;
    @Column(name = "for_telefone", nullable = false, length = 11)
    private String forTelefone;
    @Column(name = "for_email", nullable = true, length = 50)
    private String forEmail;

    @OneToMany(mappedBy = "fornecedores", fetch = FetchType.LAZY)
    @ForeignKey(name = "FornecedorProduto")
    private List<Fornecedores> fornecedores;

    @ManyToOne(optional = false)
    @ForeignKey(name = "CidadesFornecedor")
    @JoinColumn(name = "for_cid_codigo", referencedColumnName = "cid_codigo")
    private Cidades cidades;

    public Fornecedores() {
        this.cidades = new Cidades();
    }

    public Integer getForCodigo() {
        return forCodigo;
    }

    public void setForCodigo(Integer forCodigo) {
        this.forCodigo = forCodigo;
    }

    public String getForRazao() {
        return forRazao;
    }

    public void setForRazao(String forRazao) {
        this.forRazao = forRazao;
    }

    public String getForNomeFantasia() {
        return forNomeFantasia;
    }

    public void setForNomeFantasia(String forNomeFantasia) {
        this.forNomeFantasia = forNomeFantasia;
    }

    public char getForCnpj() {
        return forCnpj;
    }

    public void setForCnpj(char forCnpj) {
        this.forCnpj = forCnpj;
    }

    public String getForEndereco() {
        return forEndereco;
    }

    public void setForEndereco(String forEndereco) {
        this.forEndereco = forEndereco;
    }

    public String getForCep() {
        return forCep;
    }

    public void setForCep(String forCep) {
        this.forCep = forCep;
    }

    public String getForTelefone() {
        return forTelefone;
    }

    public void setForTelefone(String forTelefone) {
        this.forTelefone = forTelefone;
    }

    public String getForEmail() {
        return forEmail;
    }

    public void setForEmail(String forEmail) {
        this.forEmail = forEmail;
    }

    public Cidades getCidades() {
        return cidades;
    }

    public void setCidades(Cidades cidades) {
        this.cidades = cidades;
    }

    public List<Fornecedores> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedores> fornecedores) {
        this.fornecedores = fornecedores;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.forCodigo != null ? this.forCodigo.hashCode() : 0);
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
        final Fornecedores other = (Fornecedores) obj;
        if (this.forCodigo != other.forCodigo && (this.forCodigo == null || !this.forCodigo.equals(other.forCodigo))) {
            return false;
        }
        return true;
    }

}
