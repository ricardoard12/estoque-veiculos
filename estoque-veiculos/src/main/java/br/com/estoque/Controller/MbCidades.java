/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Cidades;
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
@ManagedBean(name = "mbCidades")
@RequestScoped
public class MbCidades implements Serializable {

    private static final long serialVersionUID = 1L;
    private Cidades cidade = new Cidades();
    private List<Cidades> cidades;

    public MbCidades() {
    }

    private InterfaceDAO<Cidades> cidadesDAO() {
        InterfaceDAO<Cidades> cidadesDAO = new HibernateDAO<Cidades>(Cidades.class, FacesContextUtil.getRequestSession());
        return cidadesDAO;
    }

    public String limpaCidade() {
        cidade = new Cidades();
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

    public List<Cidades> getCidades() {
        cidades = cidadesDAO().getEntities();
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
