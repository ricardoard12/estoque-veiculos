/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Cidade;
import br.com.estoque.Model.Entidades.Cliente;
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
@ManagedBean(name = "mbCliente")
@RequestScoped
public class MbCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    private Cliente cliente = new Cliente();
    private List<Cliente> clientes;
    private Cidade cidade = new Cidade();
    private List<Cidade> cidades;

    public MbCliente() {
        this.cidade = new Cidade();
    }

    private InterfaceDAO<Cliente> clientesDAO() {
        InterfaceDAO<Cliente> clientesDAO = new HibernateDAO<Cliente>(Cliente.class, FacesContextUtil.getRequestSession());
        return clientesDAO;
    }

    private InterfaceDAO<Cidade> cidadesDAO() {
        InterfaceDAO<Cidade> cidadesDAO = new HibernateDAO<Cidade>(Cidade.class, FacesContextUtil.getRequestSession());
        return cidadesDAO;
    }

    public String limpaCliente() {
        cliente = new Cliente();
        cidade = new Cidade();
        return editCliente();
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
        limpaCliente();
    }

//Gets e Seters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        clientes = clientesDAO().getEntities();
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        cidades = cidadesDAO().getEntities();
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}