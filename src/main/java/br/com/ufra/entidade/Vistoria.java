/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jairo
 */
@Entity
@Table(name = "vistoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vistoria.findAll", query = "SELECT v FROM Vistoria v"),
    @NamedQuery(name = "Vistoria.findById", query = "SELECT v FROM Vistoria v WHERE v.id = :id"),
    @NamedQuery(name = "Vistoria.findByDataSolicitacao", query = "SELECT v FROM Vistoria v WHERE v.dataSolicitacao = :dataSolicitacao"),
    @NamedQuery(name = "Vistoria.findByDataVistoria", query = "SELECT v FROM Vistoria v WHERE v.dataVistoria = :dataVistoria"),
    @NamedQuery(name = "Vistoria.findByPrazo", query = "SELECT v FROM Vistoria v WHERE v.prazo = :prazo"),
    @NamedQuery(name = "Vistoria.findByApto", query = "SELECT v FROM Vistoria v WHERE v.apto = :apto")})
public class Vistoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dataSolicitacao")
    @Temporal(TemporalType.DATE)
    private Date dataSolicitacao;
    @Column(name = "dataVistoria")
    @Temporal(TemporalType.DATE)
    private Date dataVistoria;
    @Column(name = "prazo")
    private Integer prazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apto")
    private boolean apto;
    @Lob
    @Size(max = 65535)
    @Column(name = "observacao")
    private String observacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vistoria")
    private List<Inspecao> inspecaoList;
    @JoinColumn(name = "estabelecimento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estabelecimento estabelecimento;
    @JoinColumn(name = "tecnico1", referencedColumnName = "id")
    @ManyToOne
    private Tecnico tecnico1;
    @JoinColumn(name = "tecnico2", referencedColumnName = "id")
    @ManyToOne
    private Tecnico tecnico2;

    public Vistoria() {
    }

    public Vistoria(Integer id) {
        this.id = id;
    }

    public Vistoria(Integer id, boolean apto) {
        this.id = id;
        this.apto = apto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataVistoria() {
        return dataVistoria;
    }

    public void setDataVistoria(Date dataVistoria) {
        this.dataVistoria = dataVistoria;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
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

    @XmlTransient
    public List<Inspecao> getInspecaoList() {
        return inspecaoList;
    }

    public void setInspecaoList(List<Inspecao> inspecaoList) {
        this.inspecaoList = inspecaoList;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Tecnico getTecnico1() {
        return tecnico1;
    }

    public void setTecnico1(Tecnico tecnico1) {
        this.tecnico1 = tecnico1;
    }

    public Tecnico getTecnico2() {
        return tecnico2;
    }

    public void setTecnico2(Tecnico tecnico2) {
        this.tecnico2 = tecnico2;
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
        if (!(object instanceof Vistoria)) {
            return false;
        }
        Vistoria other = (Vistoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufra.entidade.Vistoria[ id=" + id + " ]";
    }
    
}
