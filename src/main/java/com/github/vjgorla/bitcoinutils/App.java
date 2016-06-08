package com.github.vjgorla.bitcoinutils;

/**
 * Hello world!
 *
 */
public class App {
    
    public static void main( String[] args ) {
        KeyPair keyPair = new KeyPair(NetPrefix.MAIN_NET);
        String address = keyPair.getAddress(false);
        String privateKeyWIF = keyPair.getPrivateKeyImport();
        System.out.println(address + " - " + privateKeyWIF);
    }
}
