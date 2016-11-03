/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author josephawwal
 */
@Entity
@XmlRootElement
@NamedQuery(name="ExchangeRates.findByDate", query="SELECT e FROM ExchangeRates e where e.dato = :dato")
public class ExchangeRates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String refCur;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SingleExchangeRate> rates;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ExchangeRates(String date, String refCur, List<SingleExchangeRate> rates){
        this.date = date;
        this.refCur = refCur;
        this.rates = rates;
    }
    
    public ExchangeRates(){
        
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String date){
        
        this.date = date;
    }
    
    public String getRefCur(){
        return refCur;
    }
    
    public void setRefCur(String refCur){
        this.refCur = refCur;
    }
    
    @XmlTransient
    public List<SingleExchangeRate> getRates(){
        return rates;
    }
    public void setRates(List<SingleExchangeRate> rates){
        
        this.rates = rates;
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
        if (!(object instanceof ExchangeRates)) {
            return false;
        }
        ExchangeRates other = (ExchangeRates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ExchangeRates[ id=" + id + " ]";
    }
    
}
