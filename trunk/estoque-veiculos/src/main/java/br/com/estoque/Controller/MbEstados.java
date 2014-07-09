/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Estados;
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
@ManagedBean(name = "mbEstados")
@RequestScoped
public class MbEstados implements Serializable {

    private static final long serialVersionUID = 1L;
    private Estados Estado = new Estados();
    private List<Estados> Estados;

    public MbEstados() {
    }

    private InterfaceDAO<Estados> EstadosDAO() {
        InterfaceDAO<Estados> EstadosDAO = new HibernateDAO<Estados>(Estados.class, FacesContextUtil.getRequestSession());
        return EstadosDAO;
    }

    public String limpaEstado() {
        Estado = new Estados();
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

    public List<Estados> getEstados() {
        Estados = EstadosDAO().getEntities();
        return Estados;
    }

    public void setEstados(List<Estados> Estados) {
        this.Estados = Estados;
    }

    public Estados getEstado() {
        return Estado;
    }

    public void setEstado(Estados Estado) {
        this.Estado = Estado;
    }
}
