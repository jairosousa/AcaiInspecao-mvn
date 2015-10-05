/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jairo
 */
@Entity
@Table(name = "inspecao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inspecao.findAll", query = "SELECT i FROM Inspecao i"),
    @NamedQuery(name = "Inspecao.findById", query = "SELECT i FROM Inspecao i WHERE i.id = :id"),
    @NamedQuery(name = "Inspecao.findByDataInsp", query = "SELECT i FROM Inspecao i WHERE i.dataInsp = :dataInsp"),
    @NamedQuery(name = "Inspecao.findByApto", query = "SELECT i FROM Inspecao i WHERE i.apto = :apto")})
public class Inspecao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataInsp")
    @Temporal(TemporalType.DATE)
    private Date dataInsp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apto")
    private boolean apto;
    @Lob
    @Size(max = 65535)
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "equipamento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equipamento equipamento;
    @JoinColumn(name = "vistoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vistoria vistoria;

    public Inspecao() {
        
        
    }

    public Inspecao(Integer id) {
        this.id = id;
    }

    public Inspecao(Integer id, Date dataInsp, boolean apto) {
        this.id = id;
        this.dataInsp = dataInsp;
        this.apto = apto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInsp() {
        return dataInsp;
    }

    public void setDataInsp(Date dataInsp) {
        this.dataInsp = dataInsp;
    }

    public boolean getApto() {
        return apto;
    }

    public void setApto(boolean apto) {
        this.apto = apto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Vistoria getVistoria() {
        return vistoria;
    }

    public void setVistoria(Vistoria vistoria) {
        this.vistoria = vistoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inspecao)) {
            return false;
        }
        Inspecao other = (Inspecao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufra.entidade.Inspecao[ id=" + id + " ]";
    }
    
}
