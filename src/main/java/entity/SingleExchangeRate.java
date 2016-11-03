/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author josephawwal
 */
@Entity
@XmlRootElement
public class SingleExchangeRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currencyCode;
    private String description;
    private String rate;
    
    public SingleExchangeRate(){
        
    }
    
    public SingleExchangeRate(String currencyCode, String desc, String rate){
        this.currencyCode = currencyCode;
        this.description = desc;
        this.rate = rate;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof SingleExchangeRate)) {
            return false;
        }
        SingleExchangeRate other = (SingleExchangeRate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SingleExchangeRate[ id=" + id + " ]";
    }
    
    public String getCurrencyCode(){
        return currencyCode;
    }
    
    public void setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    
    public String getDesc(){
        return description;
    }
    public void setDescription(String desc){
        this.description = desc;
    }
    
    public String getRate(){
        return rate;
    }
    
    public void setRate(String rate){
        
        this.rate = rate;
    }
    
}
