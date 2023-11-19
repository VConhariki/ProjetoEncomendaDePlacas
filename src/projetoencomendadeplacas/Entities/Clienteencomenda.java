/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoencomendadeplacas.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vconh
 */
@Entity
@Table(name = "clienteencomenda")
@NamedQueries({
    @NamedQuery(name = "Clienteencomenda.findAll", query = "SELECT c FROM Clienteencomenda c"),
    @NamedQuery(name = "Clienteencomenda.findById", query = "SELECT c FROM Clienteencomenda c WHERE c.id = :id")})
public class Clienteencomenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idcliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "idencomenda", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Encomenda idencomenda;

    public Clienteencomenda() {
    }

    public Clienteencomenda(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Encomenda getIdencomenda() {
        return idencomenda;
    }

    public void setIdencomenda(Encomenda idencomenda) {
        this.idencomenda = idencomenda;
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
        if (!(object instanceof Clienteencomenda)) {
            return false;
        }
        Clienteencomenda other = (Clienteencomenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "projetoencomendadeplacas.Entities.Clienteencomenda[ id=" + id + " ]";
    }
    
}
