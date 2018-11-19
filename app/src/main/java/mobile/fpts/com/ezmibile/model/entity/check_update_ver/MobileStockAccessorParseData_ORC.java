package mobile.fpts.com.ezmibile.model.entity.check_update_ver;


import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import mobile.fpts.com.ezmibile.model.common.CommonData;
import mobile.fpts.com.ezmibile.util.Define;

public class MobileStockAccessorParseData_ORC {
    protected static int parseCheckVersionResponse(String response) throws ParserConfigurationException,
            SAXException, IOException {
        System.out.println("parseCheckVersionResponse#Start:" + response);
        int result = -1;
        try {
            result = Integer.parseInt(CommonData.getValue(
                    response, Define.PARAM_CHECKVERSION_RESULT));
        } catch (Throwable e) {
            System.out.println("Error in parsing checkVersionResponse:" + e);
        }
        System.out.println("parseCheckVersionResponse#End");
        return result;
    }
}
