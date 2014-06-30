/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.model.Entidades;

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
@Table(name = "clientes")
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "cli_codigo", nullable = false)
    private Integer cli_codigo;
    @Column(name = "cli_senha", nullable = false, length = 45)
    private String cli_senha;
    @Column(name = "cli_tipo", nullable = false, length = 3)
    private char cli_tipo;
    @Column(name = "cli_cpf", nullable = false, length = 11, unique = true)
    private char cli_cpf;
    @Column(name = "cli_nome", nullable = false, length = 45)
    private String cli_nome;
    @Column(name = "cli_senha", nullable = false, length = 45)
    private String cli_endereco;
    @Column(name = "cli_senha", nullable = true, length = 11)
    private char cli_telefone;

    @ManyToOne(optional = false)
    @ForeignKey(name = "CidadeEstado")
    @JoinColumn(name = "cli_cid_codigo", referencedColumnName = "cid_codigo")
    private Cidades cidades;

    public Clientes() {
    }

    public Integer getCli_codigo() {
        return cli_codigo;
    }

    public void setCli_codigo(Integer cli_codigo) {
        this.cli_codigo = cli_codigo;
    }

    public String getCli_senha() {
        return cli_senha;
    }

    public void setCli_senha(String cli_senha) {
        this.cli_senha = cli_senha;
    }

    public char getCli_tipo() {
        return cli_tipo;
    }

    public void setCli_tipo(char cli_tipo) {
        this.cli_tipo = cli_tipo;
    }

    public char getCli_cpf() {
        return cli_cpf;
    }

    public void setCli_cpf(char cli_cpf) {
        this.cli_cpf = cli_cpf;
    }

    public String getCli_nome() {
        return cli_nome;
    }

    public void setCli_nome(String cli_nome) {
        this.cli_nome = cli_nome;
    }

    public String getCli_endereco() {
        return cli_endereco;
    }

    public void setCli_endereco(String cli_endereco) {
        this.cli_endereco = cli_endereco;
    }

    public char getCli_telefone() {
        return cli_telefone;
    }

    public void setCli_telefone(char cli_telefone) {
        this.cli_telefone = cli_telefone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.cli_codigo != null ? this.cli_codigo.hashCode() : 0);
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
        final Clientes other = (Clientes) obj;
        if (this.cli_codigo != other.cli_codigo && (this.cli_codigo == null || !this.cli_codigo.equals(other.cli_codigo))) {
            return false;
        }
        return true;
    }

}
