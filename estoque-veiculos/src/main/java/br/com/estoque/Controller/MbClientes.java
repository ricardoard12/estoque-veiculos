/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Clientes;
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
@ManagedBean(name = "mbClientes")
@RequestScoped
public class MbClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    private Clientes cliente = new Clientes();
    private List<Clientes> clientes;

    public MbClientes() {
    }

    private InterfaceDAO<Clientes> clientesDAO() {
        InterfaceDAO<Clientes> clientesDAO = new HibernateDAO<Clientes>(Clientes.class, FacesContextUtil.getRequestSession());
        return clientesDAO;
    }

    public String limpaCliente() {
        cliente = new Clientes();
        return "/restrict/cadastrarCliente.faces";
    }

    public String editCliente() {
        return "/restrict/cadastrarCliente.faces";
    }

    public String addCliente() {
        if (cliente.getCli_codigo() == null || cliente.getCli_codigo() == 0) {
            insertCliente();
        } else {
            updateCliente();
        }
        limpaCliente();
        return null;
    }

    private void insertCliente() {
        clientesDAO().save(cliente);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Gravado com SUCESSO!", ""));
    }

    private void updateCliente() {
        clientesDAO().update(cliente);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Atualizado com SUCESSO!", ""));
    }

    public void deleteCliente() {
        clientesDAO().remove(cliente);
    }
//Gets e Seters

    public List<Clientes> getClientes() {
        clientes = clientesDAO().getEntities();
        return clientes;
    }

    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
}
