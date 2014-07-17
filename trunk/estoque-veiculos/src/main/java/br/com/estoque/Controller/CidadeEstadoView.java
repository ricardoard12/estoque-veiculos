/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.Controller;

import br.com.estoque.Model.Entidades.Cidades;
import br.com.estoque.Model.Entidades.Estados;
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
    private List<Estados> estadosList;
    private List<Cidades> cidadesList;

    @PostConstruct
    public void init() {

        estados = new HashMap<String, String>();
        estadosList = new MbEstados().getEstados();
        cidadesList = new MbCidades().getCidades();

        for (int i = 0; i < estadosList.size(); i++) {
            estados.put(estadosList.get(i).getEst_nome(), estadosList.get(i).getEst_sigla());
        }
        Map<String, String> map = new HashMap<String, String>();
        String estadoOLD="";
        for (int i = 0; i < cidadesList.size(); i++) {
            if (i == 0) {
                map = new HashMap<String, String>();
            }
            map.put(cidadesList.get(i).getCid_nome(), cidadesList.get(i).getCid_codigo().toString());

            if (!estadoOLD.equals(cidadesList.get(i).getEstados().getEst_sigla())) {
                estadoOLD = cidadesList.get(i).getEstados().getEst_sigla();
                data.put(cidadesList.get(i).getEstados().getEst_sigla(), map);
                map = new HashMap<String, String>();
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
