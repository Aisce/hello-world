package test.cfca.sadk.util;

import test.cfca.sadk.testdata.RSATestData;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public class SignatureTestCaseRSAsha256_3107 extends SignatureTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        hashAlg = "SHA256";
        signAlg = "SHA256withRSAEncryption";
        priKey = RSATestData.userPriKey;
        pubKey = RSATestData.userPubKey;
        cert = RSATestData.UserCert;

        sourceFilePath = "TestData/rsa/test.dat";
        signedFilePath = "TestData/rsa/test-sha256.sig";
        signedDataPathAttach = "TestData/rsa/test-sha256-msg-attach.p7b";
        signedDataPathDetach = "TestData/rsa/test-sha256-msg-detach.p7b";
        signedFilePathAttach = "TestData/rsa/test-sha256-file-attach.p7b";
        signedFilePathDetach = "TestData/rsa/test-sha256-file-detach.p7b";

        sourceHashPath = "TestData/rsa/test-sha256.hash";
        signedHashPathP7 = "TestData/rsa/test-sha256-hash-signed.p7b";
        signedHashPathP1 = "TestData/rsa/test-sha256-hash-signed.p1";

        signedByteLength = 256;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    boolean R32Version() {
        return false;
    }

}
