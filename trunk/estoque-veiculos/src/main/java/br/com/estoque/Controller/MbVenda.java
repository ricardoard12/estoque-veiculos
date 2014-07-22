/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Cliente;
import br.com.estoque.Model.Entidades.Venda;
import br.com.estoque.Util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luciano E. Mundt
 */
@ManagedBean(name = "mbVenda")
@RequestScoped
public class MbVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    private Venda venda = new Venda();
    private List<Venda> vendas;
    private Cliente cliente = new Cliente();
    private List<Cliente> clientes;

    public MbVenda() {
        this.cliente = new Cliente();
    }

    private InterfaceDAO<Venda> vendasDAO() {
        InterfaceDAO<Venda> vendasDAO = new HibernateDAO<Venda>(Venda.class, FacesContextUtil.getRequestSession());
        return vendasDAO;
    }

    private InterfaceDAO<Cliente> clientesDAO() {
        InterfaceDAO<Cliente> clientesDAO = new HibernateDAO<Cliente>(Cliente.class, FacesContextUtil.getRequestSession());
        return clientesDAO;
    }

    public String limpaVenda() {
        venda = new Venda();
        cliente = new Cliente();
        return editVenda();
    }

    public String editVenda() {
        return "/restrict/cadastrarVenda.faces";
    }

    public String addVenda() {
        cliente.setCli_codigo(1);
        venda.setCliente(cliente);
        if (venda.getVenCod() == null || venda.getVenCod() == 0) {
            insertVenda();
        } else {
            updateVenda();
        }
        limpaVenda();
        return null;
    }

    private void insertVenda() {
        vendasDAO().save(venda);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Venda Gravada com SUCESSO!", ""));
    }

    private void updateVenda() {
        vendasDAO().update(venda);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Venda Atualizada com SUCESSO!", ""));
    }

    public void deleteVenda() {
        vendasDAO().remove(venda);
        limpaVenda();
    }

//Gets e Seters
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Venda> getVendas() {
        vendas = vendasDAO().getEntities();
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        this.clientes = clientesDAO().getEntities();
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}
