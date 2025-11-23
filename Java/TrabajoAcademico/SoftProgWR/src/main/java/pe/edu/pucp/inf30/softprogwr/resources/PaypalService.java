/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprogwr.resources;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;
public class PaypalService {

    public Payment createOrder(String total) throws PayPalRESTException {
        // Configuración del monto
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(total);

        // Configuración de la transacción
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Pago desde tu aplicación");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        // Configuración del pago
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        // URLs de redirección
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl("https://unpropitiating-coordinately-amada.ngrok-free.dev/SoftProgWR/api/v1/payments/execute");
        redirectUrls.setCancelUrl("https://unpropitiating-coordinately-amada.ngrok-free.dev/SoftProgWR/api/v1/payments/cancel");
        payment.setRedirectUrls(redirectUrls);

        // Crear pago con PayPal
        APIContext context = Paypal.getAPIContext();
        return payment.create(context);
    }
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        APIContext context = Paypal.getAPIContext();
        return payment.execute(context, paymentExecution);
    }
}
