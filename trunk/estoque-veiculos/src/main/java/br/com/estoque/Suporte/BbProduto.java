/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.estoque.Suporte;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Produto;
import br.com.estoque.Util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Rogério Koglin
 */
@ManagedBean(name = "bbProduto")
@RequestScoped
public class BbProduto implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<Produto> getProdutos() {
        InterfaceDAO<Produto> produtoDAO = new HibernateDAO<Produto>(Produto.class, FacesContextUtil.getRequestSession());
        return produtoDAO.getEntities();
    }

}
