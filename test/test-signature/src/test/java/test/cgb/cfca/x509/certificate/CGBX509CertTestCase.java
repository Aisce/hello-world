package test.cgb.cfca.x509.certificate;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cfca.sm2rsa.common.PKIException;
import cfca.util.Base64;
import cfca.x509.certificate.X509Cert;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class CGBX509CertTestCase {

    final String certText1 = "MIIDdzCCAl+gAwIBAgIFEAUxV5AwDQYJKoZIhvcNAQEFBQAwIzELMAkGA1UEBhMCQ04xFDASBgNVBAoMC0NBMjAyOC0yMDMxMB4XDTE1MDMyOTAzMzE0MloXDTE2MDMyODAzMzE0MlowNTELMAkGA1UEBhMCQ04xFDASBgNVBAoMC0NBMjAyOC0yMDMxMRAwDgYDVQQDDAd0ZXN0MzI5MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFm+6NCVS/g+D7y6XrAizq+G2rQl84nkiy/SKrnDZzhnuphiUuijW/6mbVuKaDUkFxbaba3z97rl66Q2fTYTEbC2ImUClWtnEiCM4IW/rXDVkp6sKD0utBP4mXvECqVi5XnyVrTLNWpqlGloUyyj0MtKcblgIYGvRUbHNWj+JXLQIDAQABo4IBIjCCAR4wHwYDVR0jBBgwFoAUQh91vsC1py4NpqRVNNbJMtnVCkYwgc4GA1UdHwSBxjCBwzCBjqCBi6CBiIaBhWxkYXA6Ly8xOTIuMTY4LjkzLjExNDozODkvY249Y3JsMzUyOSxvdT1SU0EsT1U9Q1JMLE89Q0EyMDI4LTIwMzEsQz1DTj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0P2Jhc2U/b2JqZWN0Y2xhc3M9Y1JMRGlzdHJpYnV0aW9uUG9pbnQwMKAuoCyGKmh0dHA6Ly8xOTIuMTY4LjEyMC4xMjcvY3JsL1JTQS9jcmwzNTI5LmNybDALBgNVHQ8EBAMCA/gwHQYDVR0OBBYEFFwnP005w7UYf172RmulL14U05htMA0GCSqGSIb3DQEBBQUAA4IBAQB38XeCXUwoWvUcI68tkxNIWTmw9qATzgQobnnwxaXFPunHT9aiEIdvHPZkL1jzsoBmFEK3sWOKRtqAd7qq3GHqxIP2zfg01O5+DPDWTY+L2Tfsg5bekfKNjd5I9D+ZrxUDkAyxPVugyluZYA0UHDM1bCKFW/5TyxFQqGUM7X3GsTqN7fBNf15cH8myl/3Inr/pc0zXryvf19fEv1+1KYYkU5xOVobqJj1LBe+d7ax8gmvU4lkRWFuy3wnKzetXbiNB8QQ6dApiOyWHRNDVLPL2n4Aom++bFCh7ceZVAb0hqWiGIVBp8y7AYrq7D23V72PSl7tjl72FBLRwUt+8SoTq";
    final String certText2 = "MIICeDCCAh2gAwIBAgIFEAEpGVMwDAYIKoEcz1UBg3UFADAfMQswCQYDVQQGEwJDTjEQMA4GA1UECgwHQk9DIFNNMjAeFw0xNTA0MDEwODI3MDZaFw0xNzA0MDEwODI3MDZaMHoxCzAJBgNVBAYTAkNOMRUwEwYDVQQKDAxDRkNBIFRFU1QgQ0ExETAPBgNVBAsMCExvY2FsIFJBMRUwEwYDVQQLDAxJbmRpdmlkdWFsLTExKjAoBgNVBAMMITA1MUDmsJHnlJ/mtYvor5VAMTEyMzIzMTEyMTMxMzJAMTBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABDuvSpYqxzc2yREw9rH+eOFoNNaiKy6VBqYGmBN3ocjz2gmGqAsZTgvp/G1ADOuMk9gdpcglJ1GTgqRgUDvL5hCjgegwgeUwHwYDVR0jBBgwFoAUa/4Y2o9COqa4bbMuiIM6NKLBMOEwSAYDVR0gBEEwPzA9BghggRyG7yoBATAxMC8GCCsGAQUFBwIBFiNodHRwOi8vd3d3LmNmY2EuY29tLmNuL3VzL3VzLTE0Lmh0bTA3BgNVHR8EMDAuMCygKqAohiZodHRwOi8vdWNybC5jZmNhLmNvbS5jbi9TTTIvY3JsODQ2LmNybDALBgNVHQ8EBAMCA+gwHQYDVR0OBBYEFAnPUnYjbDhvC0aT0ASbCnTuEVTvMBMGA1UdJQQMMAoGCCsGAQUFBwMCMAwGCCqBHM9VAYN1BQADRwAwRAIgHxnPCRSFy7RBxDt0AqF0CXS1Nagkbx4W6FvxKMINzPMCIDoBm4bsOnK95MAPV6IrdBjeuG0gd+d60KtYe/h5mw4A";

    X509Cert cert1 = null;
    X509Cert cert2 = null;

    final String certText1CA = "MIIDJzCCAg+gAwIBAgIEFnyYNjANBgkqhkiG9w0BAQUFADAmMQswCQYDVQQGEwJDTjEXMBUGA1UECgwOQ0ZDQSBXTSBSU0EgQ0EwHhcNMTMwOTEyMDk1NDQ3WhcNMjMwOTEwMDk1NDQ3WjAmMQswCQYDVQQGEwJDTjEXMBUGA1UECgwOQ0ZDQSBXTSBSU0EgQ0EwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC14gh6swaFSJbNvZFNr+j/idOxcv09pjdq0WVEm+2hHiF2wnq6n9selgWbvY/3D8XAbxa11ZpggpXzhM3fGEVXH3jPAuCJYiKEm8Tr2lpq5YO55ckvfsc0M1Al6+vkyu0+9Jolj4uzo3ALkk/wM4DNutzqmR4TgRFgm1T8Gm1eZxKn2BYVnY7cL83tp4bQ7gATSLaLMHoHshoR8qls1FGTav1FGO/gSm05HCFzVdrm57kzkITpCJ1uZ2OUnS9Cw61eNf253ybuo/06lqTw92K9lSoYYZe9hFP46h0oQMDVEbTv7fjzA8FnW/a2OG8ETQrafkyHr6+aez636Ejl1h39AgMBAAGjXTBbMB8GA1UdIwQYMBaAFEIfdb7AtacuDaakVTTWyTLZ1QpGMAwGA1UdEwQFMAMBAf8wCwYDVR0PBAQDAgHGMB0GA1UdDgQWBBRCH3W+wLWnLg2mpFU01sky2dUKRjANBgkqhkiG9w0BAQUFAAOCAQEAVCWG/iDDfU+DuAU6P0LhopskOUmi3n0ExPAD5DehAak61lpAJIfbG4xq6aT9F2h/LEFx2dviPRRCqNx7uOWFiqnUB8LywRwu0wcV9aJi3jfGg9XztCp8fa/ZKlO3PC7sBQ4RXCMRIEF30COz9n6PEi3bUdSlT8JFDZ2a82Clg5+rM4DE9RLeVFbUIC6ngHa2MguIV6kcJrOXwliMdaivRoM6hdqKItyXzgSXSStWWoAu3gDoHLrwwPTvO4hu+DFgMbyCiOYZ2j3An9lEh4aDNdf9aa6DbsOwjewwyC3ZueviZOgLnyENdDjD6+IfkZ4hu6bhoIzbVKz8qu8tCdP9rg==";
    final String certText2CA = "MIIB9jCCAZmgAwIBAgIFIAAAAAgwDAYIKoEcz1UBg3UFADBGMQswCQYDVQQGEwJDTjEZMBcGA1UECgwQQ0ZDQSBURVNUIFNNMiBDQTEcMBoGA1UEAwwTQ0ZDQSBDUyBURVNUIFNNMiBDQTAeFw0xMjEyMjUxMjI1MDZaFw0zMjA3MjMxMjI1MDZaMB8xCzAJBgNVBAYTAkNOMRAwDgYDVQQKDAdCT0MgU00yMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEM7hYCW3nWOebujU6CRJRlsz0/vVBdXcXKKZ3qzTt36kXeAtZQ88/SyWUn5jMjWGpMmAvus4fRuv2j11tTnf0RaOBmDCBlTAfBgNVHSMEGDAWgBS12JBvXPDYM9JjvX6yw43GTxJ6YTAMBgNVHRMEBTADAQH/MDgGA1UdHwQxMC8wLaAroCmGJ2h0dHA6Ly8yMTAuNzQuNDEuODcvcmNhc20yL1NNMi9jcmwxLmNybDALBgNVHQ8EBAMCAf4wHQYDVR0OBBYEFGv+GNqPQjqmuG2zLoiDOjSiwTDhMAwGCCqBHM9VAYN1BQADSQAwRgIhAMy54+ZNRjoHMXs2yFVtZmkyTCTzGTuV0LxwDf3BowBaAiEA1+XOlir3YEJxZ9qv1bAql/hccjo/kQ3bqex/DbL3uFc=";

    @Before
    public void setUp() throws Exception {
        cert1 = new X509Cert(certText1.getBytes());
        cert2 = new X509Cert(certText2.getBytes());

        //        System.err.println(new String(Base64.encode(FileHelper.read("testRSACa.cer"))));
        //        System.err.println(new String(Base64.encode(FileHelper.read("oca1sm2.cer"))));

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetDN() throws PKIException {
        final String dnText1 = "C=CN,O=CA2028-2031,CN=test329";
        final String dnText2 = "C=CN,O=CFCA TEST CA,OU=Local RA,OU=Individual-1,CN=051@民生测试@11232311213132@1";

        Assert.assertTrue("testGetDN", dnText1.equals(cert1.getDN()));
        Assert.assertTrue("testGetDN", dnText2.equals(cert2.getDN()));

    }

    @Test
    public void testGetEncoded() throws PKIException {
        final byte[] certByte1 = Base64.decode(certText1);
        final byte[] certByte2 = Base64.decode(certText2);
        Assert.assertTrue("testGetEncoded", Arrays.equals(certByte1, cert1.getEncoded()));
        Assert.assertTrue("testGetEncoded", Arrays.equals(certByte2, cert2.getEncoded()));
    }

    @Test
    public void testGetIssuerDN() {
        final String dnText1 = "C=CN,O=CA2028-2031";
        final String dnText2 = "C=CN,O=BOC SM2";

        Assert.assertTrue("testGetIssuerDN", dnText1.equals(cert1.getIssuerDN()));
        Assert.assertTrue("testGetIssuerDN", dnText2.equals(cert2.getIssuerDN()));
    }

    @Test
    public void testGetNotBefore() {
        final String Text1 = "Sun Mar 29 11:31:42 CST 2015";
        final String Text2 = "Wed Apr 01 16:27:06 CST 2015";
        Assert.assertTrue("testGetNotBefore", Text1.equals(cert1.getNotBefore().toString()));
        Assert.assertTrue("testGetNotBefore", Text2.equals(cert2.getNotBefore().toString()));

    }

    @Test
    public void testGetNotAfter() {
        final String Text1 = "Mon Mar 28 11:31:42 CST 2016";
        final String Text2 = "Sat Apr 01 16:27:06 CST 2017";
        Assert.assertTrue("testGetNotAfter", Text1.equals(cert1.getNotAfter().toString()));
        Assert.assertTrue("testGetNotAfter", Text2.equals(cert2.getNotAfter().toString()));
    }

    @Test
    public void testGetSerialNumber() {

        final String Text1 = "68806596496";
        final String Text2 = "68738947411";
        Assert.assertTrue("testGetSerialNumber", Text1.equals(cert1.getSerialNumber().toString()));
        Assert.assertTrue("testGetSerialNumber", Text2.equals(cert2.getSerialNumber().toString()));
    }

    @Test
    public void testGetStringSerialNumber() {
        final String Text1 = "1005315790";
        final String Text2 = "1001291953";
        Assert.assertTrue("testGetStringSerialNumber", Text1.equals(cert1.getStringSerialNumber()));
        Assert.assertTrue("testGetStringSerialNumber", Text2.equals(cert2.getStringSerialNumber()));
    }

    @Test
    public void testGetSignatureAlgName() {
        System.err.println(cert1.getSignatureAlgName());
        System.err.println(cert2.getSignatureAlgName());
        final String Text1 = "SHA1withRSAEncryption";
        final String Text2 = "SM3withSM2";
        System.err.println(Text1);
        System.err.println(cert1.getSignatureAlgName());
        Assert.assertTrue("testGetSignatureAlgName", Text1.equals(cert1.getSignatureAlgName()));
        Assert.assertTrue("testGetSignatureAlgName", Text2.equals(cert2.getSignatureAlgName()));
    }

    @Test
    public void testGetPublicKey() throws PKIException {
        System.err.println(HexBin.encode(cert1.getPublicKey().getEncoded()));
        System.err.println(HexBin.encode(cert2.getPublicKey().getEncoded()));

        final byte[] byte1 = HexBin
                .decode("30819F300D06092A864886F70D010101050003818D0030818902818100C59BEE8D0954BF83E0FBCBA5EB022CEAF86DAB425F389E48B2FD22AB9C3673867BA986252E8A35BFEA66D5B8A6835241716DA6DADF3F7BAE5EBA4367D361311B0B6226502956B6712208CE085BFAD70D5929EAC283D2EB413F8997BC40AA562E579F256B4CB356A6A946968532CA3D0CB4A71B9602181AF4546C73568FE2572D0203010001");
        final byte[] byte2 = HexBin
                .decode("3059301306072A8648CE3D020106082A811CCF5501822D034200043BAF4A962AC73736C91130F6B1FE78E16834D6A22B2E9506A606981377A1C8F3DA0986A80B194E0BE9FC6D400CEB8C93D81DA5C82527519382A460503BCBE610");
        Assert.assertTrue("testGetPublicKey", Arrays.equals(byte1, cert1.getPublicKey().getEncoded()));
        Assert.assertTrue("testGetPublicKey", Arrays.equals(byte2, cert2.getPublicKey().getEncoded()));
    }

    @Test
    public void testVerify() throws PKIException {
        X509Cert ca1 = new X509Cert(certText1CA.getBytes());

        Assert.assertTrue("testVerify", cert1.verify(ca1.getPublicKey()));

        X509Cert ca2 = new X509Cert(certText2CA.getBytes());

        Assert.assertTrue("testVerify", cert2.verify(ca2.getPublicKey()));
    }

    @Test
    public void testGetPublicKeyData() throws PKIException {
        final byte[] byte1 = HexBin
                .decode("30818902818100C59BEE8D0954BF83E0FBCBA5EB022CEAF86DAB425F389E48B2FD22AB9C3673867BA986252E8A35BFEA66D5B8A6835241716DA6DADF3F7BAE5EBA4367D361311B0B6226502956B6712208CE085BFAD70D5929EAC283D2EB413F8997BC40AA562E579F256B4CB356A6A946968532CA3D0CB4A71B9602181AF4546C73568FE2572D0203010001");
        final byte[] byte2 = HexBin
                .decode("043BAF4A962AC73736C91130F6B1FE78E16834D6A22B2E9506A606981377A1C8F3DA0986A80B194E0BE9FC6D400CEB8C93D81DA5C82527519382A460503BCBE610");
        Assert.assertTrue("testGetPublicKeyData", Arrays.equals(byte1, cert1.getPublicKeyData()));
        Assert.assertTrue("testGetPublicKeyData", Arrays.equals(byte2, cert2.getPublicKeyData()));
    }

    @Test
    public void testGetTBSCertificate() throws PKIException {
        final byte[] byte1 = HexBin
                .decode("3082025FA00302010202051005315790300D06092A864886F70D01010505003023310B300906035504061302434E31143012060355040A0C0B4341323032382D32303331301E170D3135303332393033333134325A170D3136303332383033333134325A3035310B300906035504061302434E31143012060355040A0C0B4341323032382D323033313110300E06035504030C077465737433323930819F300D06092A864886F70D010101050003818D0030818902818100C59BEE8D0954BF83E0FBCBA5EB022CEAF86DAB425F389E48B2FD22AB9C3673867BA986252E8A35BFEA66D5B8A6835241716DA6DADF3F7BAE5EBA4367D361311B0B6226502956B6712208CE085BFAD70D5929EAC283D2EB413F8997BC40AA562E579F256B4CB356A6A946968532CA3D0CB4A71B9602181AF4546C73568FE2572D0203010001A38201223082011E301F0603551D23041830168014421F75BEC0B5A72E0DA6A45534D6C932D9D50A463081CE0603551D1F0481C63081C330818EA0818BA081888681856C6461703A2F2F3139322E3136382E39332E3131343A3338392F636E3D63726C333532392C6F753D5253412C4F553D43524C2C4F3D4341323032382D323033312C433D434E3F63657274696669636174655265766F636174696F6E4C6973743F626173653F6F626A656374636C6173733D63524C446973747269627574696F6E506F696E743030A02EA02C862A687474703A2F2F3139322E3136382E3132302E3132372F63726C2F5253412F63726C333532392E63726C300B0603551D0F0404030203F8301D0603551D0E041604145C273F4D39C3B5187F5EF6466BA52F5E14D3986D");
        final byte[] byte2 = HexBin
                .decode("3082021DA00302010202051001291953300C06082A811CCF550183750500301F310B300906035504061302434E3110300E060355040A0C07424F4320534D32301E170D3135303430313038323730365A170D3137303430313038323730365A307A310B300906035504061302434E31153013060355040A0C0C4346434120544553542043413111300F060355040B0C084C6F63616C20524131153013060355040B0C0C496E646976696475616C2D31312A302806035504030C2130353140E6B091E7949FE6B58BE8AF9540313132333233313132313331333240313059301306072A8648CE3D020106082A811CCF5501822D034200043BAF4A962AC73736C91130F6B1FE78E16834D6A22B2E9506A606981377A1C8F3DA0986A80B194E0BE9FC6D400CEB8C93D81DA5C82527519382A460503BCBE610A381E83081E5301F0603551D230418301680146BFE18DA8F423AA6B86DB32E88833A34A2C130E130480603551D200441303F303D060860811C86EF2A01013031302F06082B060105050702011623687474703A2F2F7777772E636663612E636F6D2E636E2F75732F75732D31342E68746D30370603551D1F0430302E302CA02AA0288626687474703A2F2F7563726C2E636663612E636F6D2E636E2F534D322F63726C3834362E63726C300B0603551D0F0404030203E8301D0603551D0E0416041409CF5276236C386F0B4693D0049B0A74EE1154EF30130603551D25040C300A06082B06010505070302");
        Assert.assertTrue("testGetTBSCertificate", Arrays.equals(byte1, cert1.getTBSCertificate()));
        Assert.assertTrue("testGetTBSCertificate", Arrays.equals(byte2, cert2.getTBSCertificate()));
    }

    @Test
    public void testGetSignature() throws PKIException {
        final byte[] byte1 = HexBin
                .decode("77F177825D4C285AF51C23AF2D9313485939B0F6A013CE04286E79F0C5A5C53EE9C74FD6A210876F1CF6642F58F3B280661442B7B1638A46DA8077BAAADC61EAC483F6CDF834D4EE7E0CF0D64D8F8BD937EC8396DE91F28D8DDE48F43F99AF1503900CB13D5BA0CA5B99600D141C33356C22855BFE53CB1150A8650CED7DC6B13A8DEDF04D7F5E5C1FC9B297FDC89EBFE9734CD7AF2BDFD7D7C4BF5FB5298624539C4E5686EA263D4B05EF9DEDAC7C826BD4E25911585BB2DF09CACDEB576E2341F1043A740A623B258744D0D52CF2F69F80289BEF9B14287B71E65501BD21A96886215069F32EC062BABB0F6DD5EF63D297BB6397BD8504B47052DFBC4A84EA");
        final byte[] byte2 = HexBin
                .decode("1F19CF091485CBB441C43B7402A1740974B535A8246F1E16E85BF128C20DCCF33A019B86EC3A72BDE4C00F57A22B7418DEB86D2077E77AD0AB587BF8799B0E00");
        
        
        System.err.println(HexBin.encode(cert2.getSignature()));
        Assert.assertTrue("testGetSignature", Arrays.equals(byte1, cert1.getSignature()));
        Assert.assertTrue("testGetSignature", Arrays.equals(byte2, cert2.getSignature()));
    }
}
