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
@Table(name = "vendas")
public class Vendas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "ven_cod", nullable = false)
    private Integer venCod;
    @Column(name = "ven_val_tot")
    private double venValTot;
    @Column(name = "ven_data")
    private String venData;

    @OneToMany(mappedBy = "vendas")
    @ForeignKey(name = "VendaItensVenda")
    private List<ItensVenda> itensVenda;

    @ManyToOne(optional = false)
    @ForeignKey(name = "vendaCliente")
    @JoinColumn(name = "ven_cli_codigo", referencedColumnName = "cli_codigo")
    private Clientes clientes;

    public Vendas() {
        this.clientes = new Clientes();
    }

    public Integer getVenCod() {
        return venCod;
    }

    public void setVenCod(Integer venCod) {
        this.venCod = venCod;
    }

    public double getVenValTot() {
        return venValTot;
    }

    public void setVenValTot(double venValTot) {
        this.venValTot = venValTot;
    }

    public String getVenData() {
        return venData;
    }

    public void setVenData(String venData) {
        this.venData = venData;
    }

    public List<ItensVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItensVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public Clientes clientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.venCod != null ? this.venCod.hashCode() : 0);
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
        final Vendas other = (Vendas) obj;
        if (this.venCod != other.venCod && (this.venCod == null || !this.venCod.equals(other.venCod))) {
            return false;
        }
        return true;
    }

}
