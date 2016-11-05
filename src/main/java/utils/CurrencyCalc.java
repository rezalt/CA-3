/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.net.*;
import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author josephawwal
 */
public final class CurrencyCalc {
    
    private static CurrencyCalc instance = null;
    private final String nbRatesURL = "http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesHistoryXML?lang=da";
    private File cacheFile = null;
    private String cacheFileName = null;
    private HashMap<String, Long> fxRates = new HashMap<String, Long>(40);
    private Date referenceDate = null;
    private String lastError = null;
    
    private CurrencyCalc(){
        
    }
    
    public static CurrencyCalc getInstance(){
        if (instance == null){
            instance = new CurrencyCalc();
        }
        return instance;
    }
    
    public double convert(double amount, String fromCurrency, String toCurrency) throws IOException, ParseException, IllegalArgumentException {
        
        if (checkCurrencyArgs(fromCurrency, toCurrency)){
            amount *= fxRates.get(toCurrency);
            amount /= fxRates.get(fromCurrency);
        }
        return amount;
    }
    private boolean checkCurrencyArgs(String fromCurrency, String toCurrency) throws IOException, ParseException, IllegalArgumentException {
        update();
        if (!fxRates.containsKey(fromCurrency)){
           throw new IllegalArgumentException(fromCurrency + "currency not available");
           
        }
        if (!fxRates.containsKey(toCurrency)){
            throw new IllegalArgumentException(toCurrency + " currency not available");
            
        }
        return (!fromCurrency.equals(toCurrency));
    }
    
    public boolean isAvailable(String currency){
        
        return (fxRates.containsKey(currency));
    }
    
    public String[] getCurrencies() throws IOException, ParseException{
        if (fxRates.isEmpty()){
            update();
        }
        String[] currencies = fxRates.keySet().toArray(new String[fxRates.size()]);
        return currencies;
    }
    
    public Date getReferenceDate(){
        
        return referenceDate;
    }
    
    public String getCacheFileName(){
        return cacheFileName;
    }
    
    public void setCacheFileName(String cacheFileName){
        this.cacheFileName = cacheFileName;
    }
    
    public void clearCache(){
        initCacheFile();
        cacheFile.delete();
        cacheFile = null;
        referenceDate = null;
    }
    
    private void update() throws IOException, ParseException {
        if (referenceDate == null){
            initCacheFile();
            if (!cacheFile.exists()){
                refreshCacheFile();
            }
            parse();
        }
        if (cacheIsExpired()){
            refreshCacheFile();
            parse();
        }
    }
    
    private void initCacheFile(){
        if (cacheFile == null){
            if (cacheFileName == null || cacheFileName.equals("")){
                cacheFileName = System.getProperty("java.io.tmpdir") + "ExchangeRates.xml";
            }
            cacheFile = new File(cacheFileName);
        }
    }
    
    private boolean cacheIsExpired(){
        final int tolerance = 12;
        if (referenceDate == null){
            return true;
        }
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long hoursOld = (cal.getTimeInMillis() - referenceDate.getTime()) / (1000 * 60 * 60);
        int hoursValid = 24 + tolerance;
        cal.setTime(referenceDate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
            hoursValid = 48;
        }
        if (hoursOld > hoursValid){
            return true;
        }
        return false;
    }
    
    private void refreshCacheFile() throws IOException {
        lastError = null;
        initCacheFile();
        InputStreamReader in;
        FileWriter out;
        try {
            URL ecbRates = new URL(nbRatesURL);
            in = new InputStreamReader(ecbRates.openStream());
            out = new FileWriter(cacheFile);
            
            try {
                int c;
                while((c = in.read()) != -1){
                    out.write(c);
                }
            } catch(IOException e){
                lastError = "Read/Write Error: " + e.getMessage();
            } finally {
                out.flush();
                out.close();
                in.close();
            }
            
        } catch (IOException e){
            lastError = "Connection/Open Error: " + e.getMessage();
        }
        if (lastError != null){
            throw new IOException(lastError);
        }
    }
    
 private long stringToLong(String str) throws NumberFormatException {
        int decimalPoint = str.indexOf('.');
        String wholePart = "";
        String fractionPart = "";
        if (decimalPoint > -1) {
            if (decimalPoint > 0) {
                wholePart = str.substring(0, decimalPoint);
            }
            fractionPart = str.substring(decimalPoint + 1);
            String padString = "0000";
            int padLength = 4 - fractionPart.length();
            if (padLength > 0) {
                fractionPart += padString.substring(0, padLength);
            } else if (padLength < 0) {
                fractionPart = fractionPart.substring(0, 4);
            }
        } else {
            wholePart = str;
            fractionPart = "0000";
        }
        return (Long.parseLong(wholePart + fractionPart));
    }

    private void parse() throws ParseException {
        try {
            FileReader input = new FileReader(cacheFile);
            XMLReader saxReader = XMLReaderFactory.createXMLReader();
            DefaultHandler handler = new DefaultHandler(){
            public void startElement(String uri, String localName, String qName, Attributes attributes){
                if (localName.equals("Cube")){
                    String date = attributes.getValue("time");
                    if (date != null){
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
                        try {
                            referenceDate = df.parse(date + " 14:15 CET");
                            
                        } catch (ParseException e){
                            lastError = "Cannot parse reference date: " + date;
                        }
                    }
                    String currency = attributes.getValue("currency");
                    String rate = attributes.getValue("rate");
                    if (currency != null && rate != null){
                        try {
                            fxRates.put(currency, stringToLong(rate));
                        } catch (Exception e){
                            lastError = "Cannot parse exchange rate: " + rate + ". " + e.getMessage();
                        }
                    }
                }
            }
            
        };
            lastError = null;
            fxRates.clear();
            fxRates.put("EUR", 10000L);
            saxReader.setContentHandler(handler);
            saxReader.setErrorHandler(handler);
            saxReader.parse(new InputSource(input));
            input.close();
    } catch (Exception e){
        lastError = "Parser error: " + e.getMessage();
    }
        if (lastError != null){
            throw new ParseException(lastError, 0);
        }
    }
    
    
}
