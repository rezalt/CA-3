package security;

import javax.xml.bind.DatatypeConverter;

public class Secret
{

    public static byte[] SHARED_SECRET;

    public static byte[] getSecret()
    {

        if (SHARED_SECRET == null)
        {

            SHARED_SECRET = DatatypeConverter.parseHexBinary("nb9ghjrb42wed6e8e58c8cc3f190ab3a62f9d9be0e3fd1bfd8fajhbfdr56734578jhgtyuika3839af727555d6d9a04v1804da2bb32495d16bc7844tyh3fghop");

        }

        return SHARED_SECRET;
    }
}
