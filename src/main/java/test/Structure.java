package test;

import java.util.HashMap;
import javax.persistence.Persistence;

public class Structure
{
    public static void main(String[] args)
    {
        HashMap<String, Object> puproperties = new HashMap();
         
        Persistence.generateSchema("seedPU", null);
    }
}