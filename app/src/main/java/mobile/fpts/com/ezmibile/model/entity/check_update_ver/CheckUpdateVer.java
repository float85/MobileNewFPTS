package mobile.fpts.com.ezmibile.model.entity.check_update_ver;

import mobile.fpts.com.ezmibile.model.common.CommonConnection;
import mobile.fpts.com.ezmibile.util.Define;

public class CheckUpdateVer extends MobileStockAccessorParseData_ORC implements ICheckUpdate {

    public int CheckVersion(String VersionName, String ProductType)
            throws Exception {
        SoapAction CheckVersion = new SoapAction();
        CheckVersion.setWebServiceUrl(Define.WS_URL);
        CheckVersion.setSoapAction(Define.WS_SOAP_NAMESPACE,
                Define.WS_SOAP_METHOD_CHECK_VERSION);
        CheckVersion.setProperty(Define.PARAM_VERSION_NAME, VersionName);
        CheckVersion.setProperty(Define.PARAM_PRODUCTTYPE, ProductType);
        CommonConnection.sendWebServiceRequest(CheckVersion);
        int reset = MobileStockAccessorParseData_ORC.parseCheckVersionResponse(
                CheckVersion.getResponseData());
        return reset;
    }


}
