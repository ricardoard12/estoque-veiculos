/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Estado;
import br.com.estoque.Util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luciano E. Mundt
 */
@ManagedBean(name = "mbEstado")
@SessionScoped
public class MbEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    private Estado Estado = new Estado();
    private List<Estado> Estados;

    public MbEstado() {
    }

    private InterfaceDAO<Estado> EstadosDAO() {
        InterfaceDAO<Estado> EstadosDAO = new HibernateDAO<Estado>(Estado.class, FacesContextUtil.getRequestSession());
        return EstadosDAO;
    }

    public String limpaEstado() {
        Estado = new Estado();
        return "/restrict/cadastrarEstado.faces";
    }

    public String editEstado() {
        return "/restrict/cadastrarEstado.faces";
    }
    public String addEstado() {
        if (Estado.getEst_sigla() == null || Estado.getEst_sigla() == "") {
            insertEstado();
        } else {
            updateEstado();
        }
        limpaEstado();
        return null;
    }

    private void insertEstado() {
        EstadosDAO().save(Estado);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Gravado com SUCESSO!", ""));
    }

    private void updateEstado() {
        EstadosDAO().update(Estado);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Atualizado com SUCESSO!", ""));
    }

    public void deleteEstado() {
        EstadosDAO().remove(Estado);
    }
//Gets e Seters

    public List<Estado> getEstados() {
        Estados = EstadosDAO().getEntities();
        return Estados;
    }

    public void setEstados(List<Estado> Estados) {
        this.Estados = Estados;
    }

    public Estado getEstado() {
        return Estado;
    }

    public void setEstado(Estado Estado) {
        this.Estado = Estado;
    }
}
