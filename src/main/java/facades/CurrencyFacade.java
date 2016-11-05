/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author josephawwal
 */
public class CurrencyFacade
{

    EntityManagerFactory emf;

    public CurrencyFacade()
    {

    }

    public CurrencyFacade(EntityManagerFactory emf)
    {
        this.emf = emf;

    }

    private EntityManager getEntityManager()
    {
        if (emf == null)
        {
            emf = Persistence.createEntityManagerFactory(deploy.DeploymentConfiguration.PU_NAME);
        }
        return emf.createEntityManager();
    }

    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    

    public List<Currency> getDailyRates(Date date)
    {
        EntityManager em = getEntityManager();
        List<Currency> rates = new ArrayList<>();
        try
        {   
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u from Currency u WHERE u.dates =:date"); //SELECT e FROM rate E
            rates = query.getResultList();
            em.getTransaction().commit();
            return rates;
        }
        finally
        {
            em.close();
        }
    }

    public Currency getRateByCurrency(String currency)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Currency.class, currency);

        }
        finally
        {
            em.close();
        }

    }

}
