/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.estoque.Suporte;

import br.com.estoque.Model.DAO.HibernateDAO;
import br.com.estoque.Model.DAO.InterfaceDAO;
import br.com.estoque.Model.Entidades.Fornecedor;
import br.com.estoque.Util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Rogério Koglin
 */
@ManagedBean(name = "bbFornecedor")
@RequestScoped
public class BbFornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<Fornecedor> getFornecedores() {
        InterfaceDAO<Fornecedor> categoriaDAO = new HibernateDAO<Fornecedor>(Fornecedor.class, FacesContextUtil.getRequestSession());
        return categoriaDAO.getEntities();
    }

}
