package pe.edu.pucp.inf30.softprog.negocio.bo.pago;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BlockchainBO {

    private static final String WALLET_EMPRESA = "0xd9f931c0db96b290ccb0ca9bf1070767175b43ec"; 
    private static final String API_KEY = "Q1EFY4BA8F79BAA4D3YYQ9C3AQ97QCSSZD";
    
    private static final String ETHERSCAN_URL = "https://api-sepolia.etherscan.io/api?module=account&action=txlist&address=" + WALLET_EMPRESA + "&startblock=0&endblock=99999999&sort=desc&apikey=" + API_KEY;


    public String verificarPagoEnBlockchain(double montoEsperadoETH) {
        try {
            URL url = new URL(ETHERSCAN_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) return null;

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(sb.toString());
            JsonNode transacciones = root.path("result");

            if (!transacciones.isArray()) return null;

            for (JsonNode tx : transacciones) {
                double valorWei = tx.path("value").asDouble(); 
                double valorEth = valorWei / 1_000_000_000_000_000_000.0;
                
                if (Math.abs(valorEth - montoEsperadoETH) < 0.00001) {
                    return tx.path("hash").asText();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}