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
@Table(name = "estabelecimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estabelecimento.findAll", query = "SELECT e FROM Estabelecimento e"),
    @NamedQuery(name = "Estabelecimento.findById", query = "SELECT e FROM Estabelecimento e WHERE e.id = :id"),
    @NamedQuery(name = "Estabelecimento.findByRazaoSocial", query = "SELECT e FROM Estabelecimento e WHERE e.razaoSocial = :razaoSocial"),
    @NamedQuery(name = "Estabelecimento.findByNomeFantasia", query = "SELECT e FROM Estabelecimento e WHERE e.nomeFantasia = :nomeFantasia"),
    @NamedQuery(name = "Estabelecimento.findByCnpj", query = "SELECT e FROM Estabelecimento e WHERE e.cnpj = :cnpj"),
    @NamedQuery(name = "Estabelecimento.findByNomeContato", query = "SELECT e FROM Estabelecimento e WHERE e.nomeContato = :nomeContato"),
    @NamedQuery(name = "Estabelecimento.findByRg", query = "SELECT e FROM Estabelecimento e WHERE e.rg = :rg"),
    @NamedQuery(name = "Estabelecimento.findByCpf", query = "SELECT e FROM Estabelecimento e WHERE e.cpf = :cpf"),
    @NamedQuery(name = "Estabelecimento.findByEmail", query = "SELECT e FROM Estabelecimento e WHERE e.email = :email"),
    @NamedQuery(name = "Estabelecimento.findByTelefone", query = "SELECT e FROM Estabelecimento e WHERE e.telefone = :telefone"),
    @NamedQuery(name = "Estabelecimento.findByCep", query = "SELECT e FROM Estabelecimento e WHERE e.cep = :cep"),
    @NamedQuery(name = "Estabelecimento.findByLogradouro", query = "SELECT e FROM Estabelecimento e WHERE e.logradouro = :logradouro"),
    @NamedQuery(name = "Estabelecimento.findByNumero", query = "SELECT e FROM Estabelecimento e WHERE e.numero = :numero"),
    @NamedQuery(name = "Estabelecimento.findByComplemento", query = "SELECT e FROM Estabelecimento e WHERE e.complemento = :complemento"),
    @NamedQuery(name = "Estabelecimento.findByBairro", query = "SELECT e FROM Estabelecimento e WHERE e.bairro = :bairro"),
    @NamedQuery(name = "Estabelecimento.findByCidade", query = "SELECT e FROM Estabelecimento e WHERE e.cidade = :cidade"),
    @NamedQuery(name = "Estabelecimento.findByUf", query = "SELECT e FROM Estabelecimento e WHERE e.uf = :uf"),
    @NamedQuery(name = "Estabelecimento.findByDataCadastro", query = "SELECT e FROM Estabelecimento e WHERE e.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Estabelecimento.findByDataLicenca", query = "SELECT e FROM Estabelecimento e WHERE e.dataLicenca = :dataLicenca"),
    @NamedQuery(name = "Estabelecimento.findByDataVencimento", query = "SELECT e FROM Estabelecimento e WHERE e.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "Estabelecimento.findByStatus", query = "SELECT e FROM Estabelecimento e WHERE e.status = :status"),
    @NamedQuery(name = "Estabelecimento.findByLatitude", query = "SELECT e FROM Estabelecimento e WHERE e.latitude = :latitude"),
    @NamedQuery(name = "Estabelecimento.findByLongitude", query = "SELECT e FROM Estabelecimento e WHERE e.longitude = :longitude")})
public class Estabelecimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "razaoSocial")
    private String razaoSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomeFantasia")
    private String nomeFantasia;
    @Size(max = 45)
    @Column(name = "cnpj")
    private String cnpj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomeContato")
    private String nomeContato;
    @Size(max = 11)
    @Column(name = "rg")
    private String rg;
    @Size(max = 15)
    @Column(name = "cpf")
    private String cpf;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "cep")
    private String cep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "logradouro")
    private String logradouro;
    @Size(max = 45)
    @Column(name = "numero")
    private String numero;
    @Size(max = 200)
    @Column(name = "complemento")
    private String complemento;
    @Size(max = 100)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 100)
    @Column(name = "cidade")
    private String cidade;
    @Size(max = 3)
    @Column(name = "uf")
    private String uf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataCadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Column(name = "dataLicenca")
    @Temporal(TemporalType.DATE)
    private Date dataLicenca;
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    @Size(max = 15)
    @Column(name = "latitude")
    private String latitude;
    @Size(max = 15)
    @Column(name = "longitude")
    private String longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estabelecimento")
    private List<Vistoria> vistoriaList;

    public Estabelecimento() {
    }

    public Estabelecimento(Integer id) {
        this.id = id;
    }

    public Estabelecimento(Integer id, String nomeFantasia, String nomeContato, String telefone, String cep, String logradouro, Date dataCadastro, String status) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.nomeContato = nomeContato;
        this.telefone = telefone;
        this.cep = cep;
        this.logradouro = logradouro;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public List<Vistoria> getVistoriaList() {
        return vistoriaList;
    }

    public void setVistoriaList(List<Vistoria> vistoriaList) {
        this.vistoriaList = vistoriaList;
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
        if (!(object instanceof Estabelecimento)) {
            return false;
        }
        Estabelecimento other = (Estabelecimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufra.entidade.Estabelecimento[ id=" + id + " ]";
    }
    
}
