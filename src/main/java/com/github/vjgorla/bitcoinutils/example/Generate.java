package com.github.vjgorla.bitcoinutils.example;

import com.github.vjgorla.bitcoinutils.KeyPair;
import com.github.vjgorla.bitcoinutils.NetPrefix;

public class Generate {

    public static void main( String[] args ) {
        KeyPair keyPair = new KeyPair(NetPrefix.MAIN_NET);
        String address = keyPair.getAddress(false);
        String privateKeyWIF = keyPair.getPrivateKeyImport();
        System.out.println(address + " - " + privateKeyWIF);
    }
    
}
