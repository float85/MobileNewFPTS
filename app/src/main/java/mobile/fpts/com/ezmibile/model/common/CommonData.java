package mobile.fpts.com.ezmibile.model.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class CommonData {
    public static String getValue(String xmlDocument, String tagName) {

        if (xmlDocument == null)
            return "";

        // Find first match start tag
        int startPos = xmlDocument.indexOf(startTag(tagName));

        // Find first match end tag
        int endPos = xmlDocument.indexOf(endTag(tagName));
        // Can not found any match tag
        if (+startPos < 0 || endPos < 0)
            return "";
        else {
            // Find empty tag
            int emptyPos = xmlDocument.indexOf(emptyTag(tagName));

            if (emptyPos > 0 && emptyPos > startPos) {
                return "";
            }
        }
        String value = "";
        value = xmlDocument.substring(startPos + startTag(tagName).length(), endPos);
        // System.out.println("getValue:" + value);
        return value;
    }

    public static String InputStreamToString(InputStream is) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            byte[] buffer = new byte[4096];

            for (int b = is.read(buffer); b >= 0; b = is.read(buffer)) {
                out.write(buffer, 0, b);
            }

            return new String(out.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    public static String startTag(String tagName) {
        StringBuffer tag = new StringBuffer();
        tag.append("<").append(tagName).append(">");

        return tag.toString();
    }

    public static String endTag(String tagName) {
        StringBuffer tag = new StringBuffer();
        tag.append("</").append(tagName).append(">");

        return tag.toString();
    }

    public static String emptyTag(String tagName) {
        StringBuffer tag = new StringBuffer();
        tag.append("<").append(tagName).append("/>");

        return tag.toString();
    }
}
