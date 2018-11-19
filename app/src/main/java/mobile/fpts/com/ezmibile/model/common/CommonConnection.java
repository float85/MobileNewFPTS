package mobile.fpts.com.ezmibile.model.common;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import mobile.fpts.com.ezmibile.model.entity.check_update_ver.SoapAction;
import mobile.fpts.com.ezmibile.util.Define;

public class CommonConnection {
    public static void sendWebServiceRequest(SoapAction SA) throws Exception {
        HttpURLConnection connection = null;
        InputStream httpInputStream = null;
        OutputStream httpOutputStream = null;
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    // Trust always
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    // Trust always
                }
            }};
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("TLS");
            // Create empty HostnameVerifier
            HostnameVerifier hv = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    if (session.isValid() && hostname.compareTo(Define.CONN_HOST) == 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            URL url = new URL(SA.getWebServiceUrl());

            connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            byte[] byteAry = SA.getRequestData().getBytes();

//            connection.setRequestProperty("Method", "POST");
            connection.setRequestProperty("POST", "/Gateway5/ezGateway.asmx HTTP/1.1");
            connection.setRequestProperty("Host", Define.CONN_HOST);
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            connection.setRequestProperty("Content-Length", String.valueOf(byteAry.length));
            connection.setRequestProperty("SOAPAction", SA.getSoapAction());
            connection.setConnectTimeout(10000);

            httpOutputStream = connection.getOutputStream();
            httpOutputStream.write(byteAry);
            httpOutputStream.flush();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                httpInputStream = connection.getInputStream();
                SA.setResponseData(CommonData.InputStreamToString(httpInputStream));
                return;
            } else {
                System.out.println("[ERR]ServiceUtils#sendRequest(" + connection.getResponseCode() + " : "
                        + connection.getResponseMessage() + ") End:");
                return;
            }
        } catch (Exception e) {
            System.out.println("[ERR]ServiceUtils#sendRequest(" + e.getMessage() + ") End");
            throw e;
        } finally {
            if (httpInputStream != null) {
                try {
                    httpInputStream = null;
                    httpInputStream.close();
                } catch (Exception ex) {
                }
            }
            if (httpOutputStream != null) {
                try {
                    httpOutputStream = null;
                    httpOutputStream.close();
                } catch (Exception ex) {
                }
            }
            if (connection != null) {
                try {
                    connection = null;
                    connection.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
