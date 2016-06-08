package com.github.vjgorla.bitcoinutils;

import java.math.BigInteger;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

public class Secp256k1 {
    
    static {
        Crypt.init();
    }

    private final static ECParameterSpec PARAM_SPEC = ECNamedCurveTable.getParameterSpec("secp256k1");
    private final static ECCurve curve = PARAM_SPEC.getCurve();
    private final static ECPoint G = PARAM_SPEC.getG();
    
    public static ECPoint getPoint(BigInteger k) {
        return G.multiply(k.mod(PARAM_SPEC.getN()));
    }
    
    public static ECPoint getG() {
        return G;
    }
    
    public static BigInteger getN() {
        return PARAM_SPEC.getN();
    }
    
    public static int getFieldSize() {
        return PARAM_SPEC.getCurve().getFieldSize();
    }
    
    public static ECCurve getCurve() {
        return curve;
    }

    public static ECParameterSpec getParameterSpec() {
        return PARAM_SPEC;
    }
}