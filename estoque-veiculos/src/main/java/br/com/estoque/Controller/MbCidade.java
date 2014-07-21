/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Cidade;
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
@ManagedBean(name = "mbCidade")
@SessionScoped
public class MbCidade implements Serializable {

    private static final long serialVersionUID = 1L;
    private Cidade cidade = new Cidade();
    private List<Cidade> cidades;
    private List<Estado> estados;

    public MbCidade() {
    }

    private InterfaceDAO<Cidade> cidadesDAO() {
        InterfaceDAO<Cidade> cidadesDAO = new HibernateDAO<Cidade>(Cidade.class, FacesContextUtil.getRequestSession());
        return cidadesDAO;
    }

    private InterfaceDAO<Estado> EstadosDAO() {
        InterfaceDAO<Estado> EstadosDAO = new HibernateDAO<Estado>(Estado.class, FacesContextUtil.getRequestSession());
        return EstadosDAO;
    }

    public String limpaCidade() {
        cidade = new Cidade();
        return "/restrict/cadastrarCidade.faces";
    }

    public String editCidade() {
        return "/restrict/cadastrarCidade.faces";
    }

    public String addCidade() {
        if (cidade.getCid_codigo() == null || cidade.getCid_codigo() == 0) {
            insertCidade();
        } else {
            updateCidade();
        }
        limpaCidade();
        return null;
    }

    private void insertCidade() {
        cidadesDAO().save(cidade);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Cidade Gravado com SUCESSO!", ""));
    }

    private void updateCidade() {
        cidadesDAO().update(cidade);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Cidade Atualizado com SUCESSO!", ""));
    }

    public void deleteCidade() {
        cidadesDAO().remove(cidade);
    }
//Gets e Seters

    public List<Cidade> getCidades() {
        cidades = cidadesDAO().getEntities();
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Estado> getEstados() {
        estados = EstadosDAO().getEntities();
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

}
