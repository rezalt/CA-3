/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author josephawwal
 */
@Entity
public class Currency implements Serializable
{

    public Currency()
    {
    }

    @Id
    private String currencyCode;
    private String description;
    private double rate;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date dates;

    public Date getDate()
    {
        return dates;
    }

    public void setDate(Date date)
    {
        this.dates = date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public double getRate()
    {
        return rate;
    }

    public void setCurrencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

}
