/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Cidade;
import br.com.estoque.Model.Entidades.Fornecedor;
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
@ManagedBean(name = "mbFornecedor")
@RequestScoped
public class MbFornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Fornecedor fornecedor = new Fornecedor();
    private List<Fornecedor> fornecedores;
    private Cidade cidade;
    private List<Cidade> cidades;
    public MbFornecedor() {
        this.cidade = new Cidade();
    }

    private InterfaceDAO<Fornecedor> fornecedoresDAO() {
        InterfaceDAO<Fornecedor> fornecedoresDAO = new HibernateDAO<Fornecedor>(Fornecedor.class, FacesContextUtil.getRequestSession());
        return fornecedoresDAO;
    }
    private InterfaceDAO<Cidade> cidadesDAO() {
        InterfaceDAO<Cidade> cidadesDAO = new HibernateDAO<Cidade>(Cidade.class, FacesContextUtil.getRequestSession());
        return cidadesDAO;
    }
    public String limpaFornecedor() {
        fornecedor = new Fornecedor();
        cidade = new Cidade();
        return "/restrict/cadastrarFornecedor.faces";
    }

    public String editFornecedor() {
        return "/restrict/cadastrarFornecedor.faces";
    }

    public String addFornecedor() {
        if ((null == fornecedor.getForCodigo()) || (fornecedor.getForCodigo() == 0)) {
            insertFornecedor();
            System.out.println("INSERIU");
            System.out.println("codigo" + fornecedor.getForCodigo());
        } else {
            System.out.println("UPDATIOU");
            updateFornecedor();
        }
        limpaFornecedor();
        return null;
    }

    private void insertFornecedor() {
        fornecedor.setCidade(cidade);
        fornecedoresDAO().save(fornecedor);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Fornecedor Gravado com SUCESSO!", ""));
    }

    private void updateFornecedor() {
        fornecedor.setCidade(cidade);
        fornecedoresDAO().update(fornecedor);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Fornecedor Atualizado com SUCESSO!", ""));
    }
    public void deleteFornecedor() {
        fornecedoresDAO().remove(fornecedor);
    }

//Gets e Seters
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Fornecedor> getFornecedores() {
        fornecedores = fornecedoresDAO().getEntities();
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public Fornecedor getFornecedor(){
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Cidade> getCidades() {
        cidades = cidadesDAO().getEntities();
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

}
