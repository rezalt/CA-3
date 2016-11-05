package data;

import deploy.DeploymentConfiguration;
import entity.Currency;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CurrencyNB extends DefaultHandler implements Runnable
{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    private static ArrayList<Currency> tmpCurrency;
    private int count = 0;
    private java.sql.Date date;

    public CurrencyNB()
    {
        tmpCurrency = new ArrayList();
    }

    @Override
    public void startDocument() throws SAXException
    {
        tmpCurrency.clear();
    }

    @Override
    public void endDocument() throws SAXException
    {

        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();

            Currency cur;
            for (int i = 0; i < tmpCurrency.size(); i++)
            {
                cur = tmpCurrency.get(i);
                em.persist(cur);
            }

            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
      
        if (count == 1)
        { //get date
            for (int i = 0; i < attributes.getLength(); i++)
            {
                System.out.println(attributes.getValue(i));
                date = java.sql.Date.valueOf(attributes.getValue(i));
                System.out.println(date);
            }
        }
        else if (count > 1)
        { //get other data
            boolean isOk = true;
            Currency tmpCur = new Currency();
            for (int i = 0; i < attributes.getLength(); i++)
            {
                switch (attributes.getLocalName(i))
                {
                    case "code":
                        tmpCur.setCurrencyCode(attributes.getValue(i));
                        break;
                    case "desc":
                        tmpCur.setDescription(attributes.getValue(i));
                        break;
                    case "rate":
                        try
                        {
                            tmpCur.setRate(Float.parseFloat(attributes.getValue(i)));
                        }
                        catch (NumberFormatException e)
                        {
                            tmpCur.setRate(0f);
                        }
                        break;
                    default:
                        isOk = false;
                        break;
                }
            }
            if (isOk)
            {
                tmpCur.setDate(date);
                tmpCurrency.add(tmpCur);
            }
        }
        count++;
    }

    public static ArrayList<Currency> getDailyRates()
    {
        return tmpCurrency;
    }

    @Override
    public void run()
    {
        try
        {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(this);
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        }
        catch (SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
