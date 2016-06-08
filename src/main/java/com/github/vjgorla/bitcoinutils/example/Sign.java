package com.github.vjgorla.bitcoinutils.example;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;

import com.github.vjgorla.bitcoinutils.Base58;
import com.github.vjgorla.bitcoinutils.KeyPair;
import com.github.vjgorla.bitcoinutils.NetPrefix;
import com.github.vjgorla.bitcoinutils.Secp256k1;

/**
 * Hello world!
 *
 */
public class Sign {
    
    public static void main( String[] args ) throws Exception {
        KeyPair keyPair = new KeyPair(NetPrefix.MAIN_NET);
        ECPrivateKeySpec priKeySpec = new ECPrivateKeySpec(keyPair.getPrivateKey(), Secp256k1.getParameterSpec());
        ECPublicKeySpec pubKeySpec = new ECPublicKeySpec(keyPair.getPublicKey(), Secp256k1.getParameterSpec());
        
        KeyFactory fact = KeyFactory.getInstance("ECDSA", BouncyCastleProvider.PROVIDER_NAME);
        PrivateKey privateKey = fact.generatePrivate(priKeySpec);
        System.out.println("Private: " + privateKey.getFormat() + " : " + Base58.encode(privateKey.getEncoded()));

        PublicKey publicKey = fact.generatePublic(pubKeySpec);
        System.out.println("Public: " + publicKey.getFormat() + " : " + Base58.encode(publicKey.getEncoded()));

        Signature dsa = Signature.getInstance("SHA256withECDSA", BouncyCastleProvider.PROVIDER_NAME);
        dsa.initSign(privateKey);
        dsa.update("This is string to sign".getBytes("UTF-8"));
        byte[] realSig = dsa.sign();
        String realSigStr = Base58.encode(realSig);
        System.out.println("Signature: " + realSigStr);
        
        Signature dsav = Signature.getInstance("SHA256withECDSA", BouncyCastleProvider.PROVIDER_NAME);
        dsav.initVerify(publicKey);
        dsav.update("This is string to sign".getBytes("UTF-8"));
        System.out.println("Signature valid: " + dsav.verify(realSig));

        String address = keyPair.getAddress(false);
        String privateKeyWIF = keyPair.getPrivateKeyImport();
        System.out.println(address + " - " + privateKeyWIF);
    }
}
