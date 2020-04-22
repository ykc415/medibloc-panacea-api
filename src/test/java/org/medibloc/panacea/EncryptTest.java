package org.medibloc.panacea;

import org.apache.commons.net.util.Base64;
import org.junit.Test;

public class EncryptTest {

    @Test
    public void testBase64() {
        String str = Base64.encodeBase64StringUnChunked("pubKeyBytes".getBytes());
        System.out.printf("[%s]\n", str);
    }
}
