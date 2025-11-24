package pe.edu.pucp.inf30.softprog.negocio.bo.pago;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CriptoMonedaBO {

    // API Pública de CoinGecko
    private static final String API_URL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=usd";

    public double obtenerPrecioBitcoinEnDolares() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error al conectar con API externa: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            conn.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(sb.toString());
            
            //return rootNode.path("bitcoin").path("usd").asDouble();
            return 4000.00; // 1 ETH = 4000 Soles
            
        } catch (Exception e) {
            e.printStackTrace();
            return 4000.00; // 1 ETH = 4000 Soles
        }
    }
    
    public double calcularMontoEnBTC(double totalVentaUSD) {
        double precioBTC = obtenerPrecioBitcoinEnDolares();
        if (precioBTC == 0) return 0;
        return totalVentaUSD / precioBTC;
    }
}