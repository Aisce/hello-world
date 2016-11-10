package demo;

import cfca.ra.common.vo.request.CertServiceRequestVO;
import cfca.ra.common.vo.response.CertServiceResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;

// 证书删除
public class Test2201 {
    public static void main(String[] args) {
        // String locale = "zh_CN";
        String dn = "CN=051@testName@Z1234567890@27,OU=Individual-1,OU=Local RA,O=CFCA TEST CA,C=CN";
        try {
            RAClient client = TestConfig.getRAClient();

            CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
            certServiceRequestVO.setTxCode("2201");
            // certServiceRequestVO.setLocale(locale);
            certServiceRequestVO.setDn(dn);

            CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);

            System.out.println(certServiceResponseVO.getResultCode());
            System.out.println(certServiceResponseVO.getResultMessage());
            if (RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())) {
                //
            }
        } catch (RATKException e) {
            e.printStackTrace();
        }
    }
}
