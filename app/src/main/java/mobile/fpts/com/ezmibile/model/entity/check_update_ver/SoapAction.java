package mobile.fpts.com.ezmibile.model.entity.check_update_ver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SoapAction {
    private String WebServiceUrl;
    private String SoapAction;
    private String Method;
    private String NameSpace;
    private String RequestData;
    private String ResponseData;
    private ArrayList<Map<String, String>> Property;
    private ArrayList<String> arrKey;

    public SoapAction() {
        SoapAction = new String();
        Method = new String();
        NameSpace = new String();
        RequestData = new String();
        setResponseData(new String());
        Property = new ArrayList<Map<String, String>>();
        RequestData = new String();
        arrKey = new ArrayList<String>();
    }

    public void setProperty(String key, String Value) {
        Map<String, String> CurMap = new HashMap<String, String>();
        CurMap.put(key, Value);
        Property.add(CurMap);
        arrKey.add(key);
    }

    public String getRequestData() {
        RequestData = RequestData.concat("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        RequestData = RequestData.concat("<soap:Envelope ");
        RequestData = RequestData.concat("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
        RequestData = RequestData.concat("xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ");
        RequestData = RequestData.concat("xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        RequestData = RequestData.concat("<soap:Body>");
        RequestData = RequestData.concat("<" + Method + " xmlns=\"http://fpts.com.vn/EzGateway/\">");
        for (int i = 0; i < Property.size(); i++) {
            Map<String, String> CurMap = Property.get(i);
            if (CurMap.get(arrKey.get(i)).length() > 0) {
                RequestData = RequestData.concat("<").concat(arrKey.get(i)).concat(">");
                RequestData = RequestData.concat(CurMap.get(arrKey.get(i)));
                RequestData = RequestData.concat("</").concat(arrKey.get(i)).concat(">");
            } else {
                RequestData = RequestData.concat("<").concat(arrKey.get(i)).concat(" />");
            }
        }
        RequestData = RequestData.concat("</").concat(Method).concat("> ");
        RequestData = RequestData.concat("</soap:Body>");
        RequestData = RequestData.concat("</soap:Envelope>");
        System.out.println("WS Request Data:" + RequestData);
        return RequestData;
    }

    public void setSoapAction(String sNameSpace, String sMethod) {
        this.Method = sMethod;
        this.NameSpace = sNameSpace;
        this.SoapAction = this.NameSpace + this.Method;
    }

    public String getSoapAction() {
        return this.SoapAction;
    }

    /**
     * @param webServiceUrl the webServiceUrl to set
     */
    public void setWebServiceUrl(String webServiceUrl) {
        WebServiceUrl = webServiceUrl;
    }

    /**
     * @return the webServiceUrl
     */
    public String getWebServiceUrl() {
        return WebServiceUrl;
    }

    /**
     * @param responseData the responseData to set
     */
    public void setResponseData(String responseData) {
        ResponseData = responseData;
    }

    /**
     * @return the responseData
     */
    public String getResponseData() {
        return ResponseData;
    }
}
