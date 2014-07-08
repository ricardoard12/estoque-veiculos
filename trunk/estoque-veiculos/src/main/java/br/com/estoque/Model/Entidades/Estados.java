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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Rogério Koglin
 */
@Entity
@Table(name = "estados")
public class Estados implements Serializable{
private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "est_sigla", nullable = false)
    private String est_sigla;
    @Column(name="est_nome",nullable = false,length = 40)
    private String est_nome;
    
    @OneToMany(mappedBy = "estados",fetch = FetchType.LAZY)
    @ForeignKey(name="CidadeEstado")
    private List<Cidades> cidades;
    
    public Estados() {
    }

    public String getEst_sigla() {
        return est_sigla;
    }

    public void setEst_sigla(String est_sigla) {
        this.est_sigla = est_sigla;
    }

    public String getEst_nome() {
        return est_nome;
    }

    public void setEst_nome(String est_nome) {
        this.est_nome = est_nome;
    }

    public List<Cidades> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidades> cidades) {
        this.cidades = cidades;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.est_sigla != null ? this.est_sigla.hashCode() : 0);
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
        final Estados other = (Estados) obj;
        if (this.est_sigla != other.est_sigla) {
            return false;
        }
        return true;
    }
    
}
