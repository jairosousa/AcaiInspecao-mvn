/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jairo
 */
@Entity
@Table(name = "tecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tecnico.findAll", query = "SELECT t FROM Tecnico t"),
    @NamedQuery(name = "Tecnico.findById", query = "SELECT t FROM Tecnico t WHERE t.id = :id"),
    @NamedQuery(name = "Tecnico.findByMatricula", query = "SELECT t FROM Tecnico t WHERE t.matricula = :matricula"),
    @NamedQuery(name = "Tecnico.findByNome", query = "SELECT t FROM Tecnico t WHERE t.nome = :nome"),
    @NamedQuery(name = "Tecnico.findByEmail", query = "SELECT t FROM Tecnico t WHERE t.email = :email"),
    @NamedQuery(name = "Tecnico.findBySenha", query = "SELECT t FROM Tecnico t WHERE t.senha = :senha")})
public class Tecnico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nome")
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "senha")
    private String senha;
    @Transient
    private String ConfirmeSenha;
    @OneToMany(mappedBy = "tecnico1")
    private List<Vistoria> vistoriaList;
    @OneToMany(mappedBy = "tecnico2")
    private List<Vistoria> vistoriaList1;

    public Tecnico() {
    }

    public Tecnico(Integer id) {
        this.id = id;
    }

    public Tecnico(Integer id, String matricula, String nome, String senha) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmeSenha() {
        return ConfirmeSenha;
    }

    public void setConfirmeSenha(String ConfirmeSenha) {
        this.ConfirmeSenha = ConfirmeSenha;
    }

    @XmlTransient
    public List<Vistoria> getVistoriaList() {
        return vistoriaList;
    }

    public void setVistoriaList(List<Vistoria> vistoriaList) {
        this.vistoriaList = vistoriaList;
    }

    @XmlTransient
    public List<Vistoria> getVistoriaList1() {
        return vistoriaList1;
    }

    public void setVistoriaList1(List<Vistoria> vistoriaList1) {
        this.vistoriaList1 = vistoriaList1;
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
        if (!(object instanceof Tecnico)) {
            return false;
        }
        Tecnico other = (Tecnico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufra.entidade.Tecnico[ id=" + id + " ]";
    }

}
