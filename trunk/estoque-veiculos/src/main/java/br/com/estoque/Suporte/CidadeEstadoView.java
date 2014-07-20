/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Suporte;

import br.com.estoque.Controller.MbCidade;
import br.com.estoque.Controller.MbEstado;
import br.com.estoque.Model.Entidades.Cidade;
import br.com.estoque.Model.Entidades.Estado;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Rogério Koglin
 */
@ManagedBean
@RequestScoped
public class CidadeEstadoView implements Serializable {

    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private String estado;
    private String cidade;
    private Map<String, String> estados;
    private Map<String, String> cidades;
    private List<Estado> estadosList;
    private List<Cidade> cidadesList;

    @PostConstruct
    public void init() {

        estados = new HashMap<String, String>();
        estadosList = new MbEstado().getEstados();
        cidadesList = new MbCidade().getCidades();
        //popula o mapa de estados
        for (int i = 0; i < estadosList.size(); i++) {
            estados.put(estadosList.get(i).getEst_nome(), estadosList.get(i).getEst_sigla());
        }
        Map<String, String> map = null;
        String estadoAUX = "";
        for (int i = 0; i < cidadesList.size(); i++) {
            //se for a primeira cria o mapa e define o estado
            if (i == 0) {
                map = new HashMap<String, String>();
                estadoAUX = cidadesList.get(i).getEstado().getEst_sigla();
            }
            //se o estado mudou salva o anterior e cria um novo map
            if (!estadoAUX.equals(cidadesList.get(i).getEstado().getEst_sigla())) {
                estadoAUX = cidadesList.get(i).getEstado().getEst_sigla();
                data.put(cidadesList.get(i - 1).getEstado().getEst_sigla(), map);
                map = new HashMap<String, String>();
            }
            //adiciona cidades ao map
            map.put(cidadesList.get(i).getCid_nome(), cidadesList.get(i).getCid_codigo().toString());
            //se for a ultima linha salva os dados antes de sair do laço
            if (i == (cidadesList.size() - 1)) {
                data.put(cidadesList.get(i).getEstado().getEst_sigla(), map);
            }
        }
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Map<String, String> getEstados() {
        return estados;
    }

    public Map<String, String> getCidades() {
        return cidades;
    }

    public void onEstadoChange() {
        if (estado != null && !estado.equals("")) {
            cidades = data.get(estado);
        } else {
            cidades = new HashMap<String, String>();
        }
    }
}
