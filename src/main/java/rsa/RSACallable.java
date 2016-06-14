package rsa;

import experiment.CallableImpl;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by bdanglot on 29/04/16.
 */
public class RSACallable extends CallableImpl<byte[], byte[]> {

    static BigInteger mod = new BigInteger("b259d2d6e627a768c94be36164c2d9fc79d97aab9253140e5bf17751197731d6f7540d2509e7b9ffee0a70a6e26d56e92d2edd7f85aba85600b69089f35f6bdbf3c298e05842535d9f064e6b0391cb7d306e0a2d20c4dfb4e7b49a9640bdea26c10ad69c3f05007ce2513cee44cfe01998e62b6c3637d3fc0391079b26ee36d5", 16);
    static BigInteger pubExp = new BigInteger("11", 16);
    static BigInteger privExp = new BigInteger("92e08f83cc9920746989ca5034dcb384a094fb9c5a6288fcc4304424ab8f56388f72652d8fafc65a4b9020896f2cde297080f2a540e7b7ce5af0b3446e1258d1dd7f245cf54124b4c6e17da21b90a0ebd22605e6f45c9f136d7a13eaac1c0f7487de8bd6d924972408ebb58af71e76fd7b012a8d0e165f3ae2e5077a8648e619", 16);
    static BigInteger p = new BigInteger("f75e80839b9b9379f1cf1128f321639757dba514642c206bbbd99f9a4846208b3e93fbbe5e0527cc59b1d4b929d9555853004c7c8b30ee6a213c3d1bb7415d03", 16);
    static BigInteger q = new BigInteger("b892d9ebdbfc37e397256dd8a5d3123534d1f03726284743ddc6be3a709edb696fc40c7d902ed804c6eee730eee3d5b20bf6bd8d87a296813c87d3b3cc9d7947", 16);
    static BigInteger pExp = new BigInteger("1d1a2d3ca8e52068b3094d501c9a842fec37f54db16e9a67070a8b3f53cc03d4257ad252a1a640eadd603724d7bf3737914b544ae332eedf4f34436cac25ceb5", 16);
    static BigInteger qExp = new BigInteger("6c929e4e81672fef49d9c825163fec97c4b7ba7acb26c0824638ac22605d7201c94625770984f78a56e6e25904fe7db407099cad9b14588841b94f5ab498dded", 16);
    static BigInteger crtCoef = new BigInteger("dae7651ee69ad1d081ec5e7188ae126f6004ff39556bde90e0b870962fa7b926d070686d8244fe5a9aa709a95686a104614834b0ada4b10f53197a5cb4c97339", 16);

    static RSAKeyParameters pubParameters = new RSAKeyParameters(false, mod, pubExp);
    static RSAKeyParameters privParameters = new RSAPrivateCrtKeyParameters(mod, pubExp, privExp, p, q, pExp, qExp, crtCoef);

    private RSAEngine cipher;

    private RSAEngine decipher;

    public RSACallable(byte[] input) {
        super(input);
    }

    @Override
    public byte[] call() throws Exception {
        cipher = new RSAEngine();
        cipher.init(true, privParameters);
        decipher = new RSAEngine();
        decipher.init(false, pubParameters);
        byte[] ciphered = cipher.processBlock(input, 0, input.length);
        return decipher.processBlock(ciphered, 0, ciphered.length);
    }


//    private String processLargeBlock() {
//        byte[][] datas = new byte[data.length / 128][128];
//        for (int i = 0; i < datas.length; i++)
//            datas[i] = cipher.processBlock(data, 128 * i, 128);
//        for (int i = 0; i < datas.length; i++) {
//            datas[i] = decipher.processBlock(datas[i], 0, datas[i].length);
//            System.arraycopy(datas[i], 0, data, 128 * i, 128);
//        }
//        return new String(Hex.encode(data));
//    }
//
//    private String proccessBlock() {
//        data = cipher.processBlock(data, 0, data.length);
//        data = decipher.processBlock(data, 0, data.length);
//        return new String(Hex.encode(data));
//    }

    public static void main(String[] args) {

        int z = 129;
        byte[] input = new byte[z];
        Random rnd = new Random(31L);
        for (int i = 0; i < z; i++) {
            while ((input[i] = (byte) rnd.nextInt()) == 0) ;
        }

        input[7] = 0;

        RSAEngine cipher = new RSAEngine();
        RSAEngine decipher = new RSAEngine();

        cipher.init(true, privParameters);
        decipher.init(false, pubParameters);


        byte[][] multiciphered = new byte[(input.length / 128) + (input.length % 128 == 0 ? 0 : 1)][128];

        for (int i = 0; i < multiciphered.length; i++) {
            int length = i == multiciphered.length - 1 ? (input.length - (128 * i)) : 128;
            System.arraycopy(input, 128 * i, multiciphered[i], 0, length);
        }

        for (int i = 0; i < multiciphered.length; i++) {
            for (int i1 = 0; i1 < multiciphered[i].length; i1++) {
                System.out.print(multiciphered[i][i1] + " ");
            }
        }
        System.out.println();

        for (int i = 0; i < multiciphered.length; i++) {
            int length = i == multiciphered.length - 1 ? (input.length - (128 * i)) : 128;
            for (int i1 = 0; i1 < length; i1++) {
                System.out.print(multiciphered[i][i1]+ " ");
            }
            System.out.println();
            multiciphered[i] = cipher.processBlock(multiciphered[i], 0, length);
        }

        for (int i = 0; i < multiciphered.length; i++) {
            int length = i == multiciphered.length - 1 ? (input.length - (128 * i)) : 128;

            multiciphered[i] = decipher.processBlock(multiciphered[i], 0, length);
        }

        for (int i = 0; i < multiciphered.length; i++) {
            for (int i1 = 0; i1 < multiciphered[i].length; i1++) {
                System.out.print(multiciphered[i][i1] + " ");
            }
        }
        System.out.println();

        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + " ");
        }
        System.out.println();


//        byte[] ciphered = cipher.processBlock(input, 0, input.length);
//        byte[] unciphered = decipher.processBlock(ciphered, 0, ciphered.length);
//
//        if (unciphered.length != input.length)
//            System.err.println("ERROR");
//
//        try {
//
//            boolean assertion = true;
//            for (int i = 0; i < input.length; i++) {
//                assertion &= input[i] == unciphered[i];
//            }
//
//
//            if (!assertion) {
//                for (int i = 0; i < input.length; i++)
//                    System.out.print(input[i] + " ");
//                System.out.println();
//                for (int i = 0; i < unciphered.length; i++)
//                    System.out.print(unciphered[i] + " ");
//                System.out.println();
//            }
//
//        } catch (ArrayIndexOutOfBoundsException e) {
//            for (int i = 0; i < input.length; i++)
//                System.out.print(input[i] + " ");
//            System.out.println();
//            for (int i = 0; i < unciphered.length; i++)
//                System.out.print(unciphered[i] + " ");
//            System.out.println();
//        }
    }

    //TODO processing large block doesn't work....

}
