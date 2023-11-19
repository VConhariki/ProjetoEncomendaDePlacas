/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoencomendadeplacas.Entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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

/**
 *
 * @author vconh
 */
@Entity
@Table(name = "encomenda")
@NamedQueries({
    @NamedQuery(name = "Encomenda.findAll", query = "SELECT e FROM Encomenda e"),
    @NamedQuery(name = "Encomenda.findById", query = "SELECT e FROM Encomenda e WHERE e.id = :id"),
    @NamedQuery(name = "Encomenda.findByAlturaplaca", query = "SELECT e FROM Encomenda e WHERE e.alturaplaca = :alturaplaca"),
    @NamedQuery(name = "Encomenda.findByLarguraplaca", query = "SELECT e FROM Encomenda e WHERE e.larguraplaca = :larguraplaca"),
    @NamedQuery(name = "Encomenda.findByFrase", query = "SELECT e FROM Encomenda e WHERE e.frase = :frase"),
    @NamedQuery(name = "Encomenda.findByCorplaca", query = "SELECT e FROM Encomenda e WHERE e.corplaca = :corplaca"),
    @NamedQuery(name = "Encomenda.findByCorfrase", query = "SELECT e FROM Encomenda e WHERE e.corfrase = :corfrase"),
    @NamedQuery(name = "Encomenda.findByDataentrega", query = "SELECT e FROM Encomenda e WHERE e.dataentrega = :dataentrega"),
    @NamedQuery(name = "Encomenda.findByValorservico", query = "SELECT e FROM Encomenda e WHERE e.valorservico = :valorservico"),
    @NamedQuery(name = "Encomenda.findByValorsinal", query = "SELECT e FROM Encomenda e WHERE e.valorsinal = :valorsinal"),
    @NamedQuery(name = "Encomenda.findByFormapagamento", query = "SELECT e FROM Encomenda e WHERE e.formapagamento = :formapagamento"),
    @NamedQuery(name = "Encomenda.findByPagamentopendente", query = "SELECT e FROM Encomenda e WHERE e.pagamentopendente = :pagamentopendente")})
public class Encomenda implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idencomenda")
    private Collection<Clienteencomenda> clienteencomendaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "alturaplaca")
    private BigInteger alturaplaca;
    @Basic(optional = false)
    @Column(name = "larguraplaca")
    private BigInteger larguraplaca;
    @Column(name = "frase")
    private String frase;
    @Basic(optional = false)
    @Column(name = "corplaca")
    private int corplaca;
    @Basic(optional = false)
    @Column(name = "corfrase")
    private int corfrase;
    @Basic(optional = false)
    @Column(name = "dataentrega")
    @Temporal(TemporalType.DATE)
    private Date dataentrega;
    @Column(name = "valorservico")
    private BigInteger valorservico;
    @Column(name = "valorsinal")
    private BigInteger valorsinal;
    @Column(name = "formapagamento")
    private Integer formapagamento;
    @Column(name = "pagamentopendente")
    private Boolean pagamentopendente;

    public Encomenda() {
    }

    public Encomenda(Integer id) {
        this.id = id;
    }

    public Encomenda(Integer id, BigInteger alturaplaca, BigInteger larguraplaca, int corplaca, int corfrase, Date dataentrega) {
        this.id = id;
        this.alturaplaca = alturaplaca;
        this.larguraplaca = larguraplaca;
        this.corplaca = corplaca;
        this.corfrase = corfrase;
        this.dataentrega = dataentrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getAlturaplaca() {
        return alturaplaca;
    }

    public void setAlturaplaca(BigInteger alturaplaca) {
        this.alturaplaca = alturaplaca;
    }

    public BigInteger getLarguraplaca() {
        return larguraplaca;
    }

    public void setLarguraplaca(BigInteger larguraplaca) {
        this.larguraplaca = larguraplaca;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public int getCorplaca() {
        return corplaca;
    }

    public void setCorplaca(int corplaca) {
        this.corplaca = corplaca;
    }

    public int getCorfrase() {
        return corfrase;
    }

    public void setCorfrase(int corfrase) {
        this.corfrase = corfrase;
    }

    public Date getDataentrega() {
        return dataentrega;
    }

    public void setDataentrega(Date dataentrega) {
        this.dataentrega = dataentrega;
    }

    public BigInteger getValorservico() {
        return valorservico;
    }

    public void setValorservico(BigInteger valorservico) {
        this.valorservico = valorservico;
    }

    public BigInteger getValorsinal() {
        return valorsinal;
    }

    public void setValorsinal(BigInteger valorsinal) {
        this.valorsinal = valorsinal;
    }

    public Integer getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(Integer formapagamento) {
        this.formapagamento = formapagamento;
    }

    public Boolean getPagamentopendente() {
        return pagamentopendente;
    }

    public void setPagamentopendente(Boolean pagamentopendente) {
        this.pagamentopendente = pagamentopendente;
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
        if (!(object instanceof Encomenda)) {
            return false;
        }
        Encomenda other = (Encomenda) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "projetoencomendadeplacas.Entities.Encomenda[ id=" + id + " ]";
    }

    public Collection<Clienteencomenda> getClienteencomendaCollection() {
        return clienteencomendaCollection;
    }

    public void setClienteencomendaCollection(Collection<Clienteencomenda> clienteencomendaCollection) {
        this.clienteencomendaCollection = clienteencomendaCollection;
    }
    
}
