/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Fornecedores;
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
@ManagedBean(name = "mbFornecedores")
@RequestScoped
public class MbFornecedores implements Serializable {

    private static final long serialVersionUID = 1L;
    private Fornecedores fornecedor = new Fornecedores();
    private List<Fornecedores> fornecedores;

    public MbFornecedores() {
    }

    private InterfaceDAO<Fornecedores> fornecedoresDAO() {
        InterfaceDAO<Fornecedores> fornecedoresDAO = new HibernateDAO<Fornecedores>(Fornecedores.class, FacesContextUtil.getRequestSession());
        return fornecedoresDAO;
    }

    public String limpaFornecedor() {
        fornecedor = new Fornecedores();
        return "/restrict/cadastrarFornecedor.faces";
    }

    public String editFornecedor() {
        return "/restrict/cadastrarFornecedor.faces";
    }

    public String addFornecedor() {
        if (fornecedor.getForCodigo() == null || fornecedor.getForCodigo() == 0) {
            insertFornecedor();
        } else {
            updateFornecedor();
        }
        limpaFornecedor();
        return null;
    }

    private void insertFornecedor() {
        fornecedoresDAO().save(fornecedor);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Fornecedor Gravado com SUCESSO!", ""));
    }

    private void updateFornecedor() {
        fornecedoresDAO().update(fornecedor);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Fornecedor Atualizado com SUCESSO!", ""));
    }
    public void deleteFornecedor() {
        fornecedoresDAO().remove(fornecedor);
    }
//Gets e Seters

    public List<Fornecedores> getFornecedores() {
        fornecedores = fornecedoresDAO().getEntities();
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedores> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public Fornecedores getFornecedor(){
        return fornecedor;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }
}

