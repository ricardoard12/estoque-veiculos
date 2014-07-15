/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.estoque.Controller;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Categorias;
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
@ManagedBean(name = "mbCategorias")
@SessionScoped
public class MbCategorias implements Serializable {

    private static final long serialVersionUID = 1L;
    private Categorias categoria = new Categorias();
    private List<Categorias> categorias;

    public MbCategorias() {
    }

    private InterfaceDAO<Categorias> categoriasDAO() {
        InterfaceDAO<Categorias> categoriasDAO = new HibernateDAO<Categorias>(Categorias.class, FacesContextUtil.getRequestSession());
        return categoriasDAO;
    }

    public String limpaCategoria() {
        categoria = new Categorias();
        return "/restrict/cadastrarCategoria.faces";
    }

    public String editCategoria() {
        return "/restrict/cadastrarCategoria.faces";
    }

    public String addCategoria() {
        if (categoria.getCatCodigo() == null || categoria.getCatCodigo() == 0) {
            insertCategoria();
        } else {
            updateCategoria();
        }
        limpaCategoria();
        return null;
    }

    private void insertCategoria() {
        categoriasDAO().save(categoria);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria Gravada com SUCESSO!", ""));
    }

    private void updateCategoria() {
        categoriasDAO().update(categoria);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria Atualizada com SUCESSO!", ""));
    }
    public void deleteCategoria() {
        categoriasDAO().remove(categoria);
    }
//Gets e Seters

    public List<Categorias> getCategorias() {
        categorias = categoriasDAO().getEntities();
        return categorias;
    }

    public void setCategorias(List<Categorias> categorias) {
        this.categorias = categorias;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }
}
