/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Cidades;
import br.com.estoque.Model.Entidades.Clientes;
import br.com.estoque.Util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luciano E. Mundt
 */
@ManagedBean(name = "mbClientes")
@SessionScoped
public class MbClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    private Clientes cliente = new Clientes();
    private List<Clientes> clientes;
    private List<Cidades> cidades;
    private Cidades cidade = new Cidades();

    public MbClientes() {
    }

    private InterfaceDAO<Clientes> clientesDAO() {
        InterfaceDAO<Clientes> clientesDAO = new HibernateDAO<Clientes>(Clientes.class, FacesContextUtil.getRequestSession());
        return clientesDAO;
    }

    private InterfaceDAO<Cidades> cidadesDAO() {
        InterfaceDAO<Cidades> cidadesDAO = new HibernateDAO<Cidades>(Cidades.class, FacesContextUtil.getRequestSession());
        return cidadesDAO;
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
        System.out.println("#######################################################3 cidfade" + cidade.getCid_codigo().toString());
        cliente.setCidades(cidade);
        System.out.println("cliente_Cidade" + cliente.getCidades().getCid_codigo().toString());
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

    public List<Cidades> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidades> cidades) {
        this.cidades = cidades;
    }

    public Cidades getCidade() {
        return cidade;
    }

    public void setCidade(Cidades cidade) {
        this.cidade = cidade;
    }

}
