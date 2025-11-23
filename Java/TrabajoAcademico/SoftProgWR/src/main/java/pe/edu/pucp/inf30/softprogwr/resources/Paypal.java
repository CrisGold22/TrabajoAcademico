/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprogwr.resources;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author BRI
 */
public class Paypal {
    public static final String CLIENT_ID = "Acx8KiA0hQdYJ4LSlRSmtJSXD5cS1y52WKROceTbh4yEM5dfamCZ3rQj3L7ACb23OMlBcPcCJL_uXSUW";
    public static final String CLIENT_SECRET = "EPQhplU9dSGHAWYWaErBoLQjHx6nnLPf029ndt6B9cdjNPuoa5mK3bih2A47v87ffpJhtdFoU5RrY2Yj";
    private static final String MODE = "sandbox"; // o "live"

    public static APIContext getAPIContext() throws PayPalRESTException {
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", MODE);

        String accessToken = new OAuthTokenCredential(CLIENT_ID, CLIENT_SECRET, sdkConfig).getAccessToken();
        APIContext context = new APIContext(accessToken);
        context.setConfigurationMap(sdkConfig);

        return context;
    }
}
