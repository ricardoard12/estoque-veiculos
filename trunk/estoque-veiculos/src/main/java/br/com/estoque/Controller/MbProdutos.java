/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Produtos;
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
 * @author Rogério Koglin
 */
@ManagedBean(name = "mbProdutos")
@SessionScoped
public class MbProdutos implements Serializable {

    private static final long serialVersionUID = 1L;
    private Produtos produto = new Produtos();
    private List<Produtos> produtos;
    public MbProdutos() {
    }

    private InterfaceDAO<Produtos> produtosDAO() {
        InterfaceDAO<Produtos> produtosDAO = new HibernateDAO<Produtos>(Produtos.class, FacesContextUtil.getRequestSession());
        return produtosDAO;
    }

    public String limpaProduto() {
        produto = new Produtos();
        return "/restrict/cadastrarProduto.faces";
    }

    public String editProduto() {
        return "/restrict/cadastrarProduto.faces";
    }

    public String addProduto() {
        if (produto.getProCodigo() == null || produto.getProCodigo() == 0) {
            insertProduto();
        } else {
            updateProduto();
        }
        limpaProduto();
        return null;
    }

    private void insertProduto() {
        produtosDAO().save(produto);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto Gravada com SUCESSO!", ""));
    }

    private void updateProduto() {
        produtosDAO().update(produto);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto Atualizada com SUCESSO!", ""));
    }
    public void deleteProduto() {
        produtosDAO().remove(produto);
    }
//Gets e Seters

    public List<Produtos> getProdutos() {
        produtos = produtosDAO().getEntities();
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }
}
