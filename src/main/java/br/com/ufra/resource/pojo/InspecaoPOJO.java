/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.resource.pojo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class InspecaoPOJO implements Serializable{

    private Integer id;
    private long dataInspPOJO;
    private boolean aptoPOJO;
    private String observacaoPOJO;
    private VistoriaPOJO vistoriaPOJO;
    private EquipamentoPOJO equipamentoPOJO;     

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getDataInspPOJO() {
        return dataInspPOJO;
    }

    public void setDataInspPOJO(long dataInspPOJO) {
        this.dataInspPOJO = dataInspPOJO;
    }

    public boolean isAptoPOJO() {
        return aptoPOJO;
    }

    public void setAptoPOJO(boolean aptoPOJO) {
        this.aptoPOJO = aptoPOJO;
    }

    public String getObservacaoPOJO() {
        return observacaoPOJO;
    }

    public void setObservacaoPOJO(String observacaoPOJO) {
        this.observacaoPOJO = observacaoPOJO;
    }

    public VistoriaPOJO getVistoriaPOJO() {
        return vistoriaPOJO;
    }

    public void setVistoriaPOJO(VistoriaPOJO vistoriaPOJO) {
        this.vistoriaPOJO = vistoriaPOJO;
    }

    public EquipamentoPOJO getEquipamentoPOJO() {
        return equipamentoPOJO;
    }

    public void setEquipamentoPOJO(EquipamentoPOJO equipamentoPOJO) {
        this.equipamentoPOJO = equipamentoPOJO;
    }

    
    
}
