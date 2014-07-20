/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Categoria;
import br.com.estoque.Model.Entidades.Fornecedor;
import br.com.estoque.Model.Entidades.Produto;
import br.com.estoque.Util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rogério Koglin
 */
@ManagedBean(name = "mbProduto")
@SessionScoped
public class MbProduto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Produto produto = new Produto();
    private List<Produto> produtos;
    private Categoria categoria = new Categoria();
    private List<Categoria> categorias;
    private Fornecedor fornecedor = new Fornecedor();
    private List<Fornecedor> fornecedores;

    public MbProduto() {
    }

    private InterfaceDAO<Produto> produtosDAO() {
        InterfaceDAO<Produto> produtosDAO = new HibernateDAO<Produto>(Produto.class, FacesContextUtil.getRequestSession());
        return produtosDAO;
    }

    private InterfaceDAO<Categoria> categoriaDAO() {
        InterfaceDAO<Categoria> categoriaDAO = new HibernateDAO<Categoria>(Categoria.class, FacesContextUtil.getRequestSession());
        return categoriaDAO;
    }

    private InterfaceDAO<Fornecedor> fornecedorDAO() {
        InterfaceDAO<Fornecedor> fornecedorDAO = new HibernateDAO<Fornecedor>(Fornecedor.class, FacesContextUtil.getRequestSession());
        return fornecedorDAO;
    }

    public String limpaProduto() {
        produto = new Produto();
        categoria = new Categoria();
        fornecedor = new Fornecedor();
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
        produto.setCategoria(categoria);
        produto.setFornecedor(fornecedor);
        produtosDAO().save(produto);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto Gravado com SUCESSO!", ""));
    }

    private void updateProduto() {
        produto.setCategoria(categoria);
        produto.setFornecedor(fornecedor);
        produtosDAO().update(produto);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto Atualizado com SUCESSO!", ""));
    }

    public void deleteProduto() {
        produtosDAO().remove(produto);
    }
//Gets e Seters

    public List<Produto> getProdutos() {
        produtos = produtosDAO().getEntities();
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        categorias = categoriaDAO().getEntities();
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getFornecedores() {
        fornecedores = fornecedorDAO().getEntities();
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

}
