package com.example.wbm.controller.paypal;

import com.example.wbm.implementation.PaypalServiceImpl;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/paypal")
@RequiredArgsConstructor
public class PaypalController {

    private final PaypalServiceImpl paypalService;

    private final static Logger logger = LoggerFactory.getLogger(PaypalController.class);

    @GetMapping
    public String home (){
        return "/paypal/index";
    }

    /**
     * Inicia el proceso de creación de pago en PayPal.
     * Recibe el total del pedido desde el formulario HTML.
     */
    @PostMapping("/payment/create")
    public RedirectView createPayment(@RequestParam("total") double total){ // <-- MODIFICACIÓN: Recibe el total

        try {

            String cancelUrl = "http://localhost:8089/webStoreManga/paypal/payment/cancel";
            String successUrl = "http://localhost:8089/webStoreManga/paypal/payment/success"; // Sigue apuntando aquí

            Payment payment = paypalService.createPayment(
                    total, // <-- Se usa el total dinámico
                    "USD",
                    "paypal",
                    "sale",
                    "Payment for WebStoreManga Order", // Descripción del pago
                    cancelUrl,
                    successUrl
            );

            for (Links links: payment.getLinks()){
                if(links.getRel().equals("approval_url")){

                    return new RedirectView(links.getHref()); // Redirige a PayPal

                }
            }

        }catch (PayPalRESTException e){
            logger.error("Error occurred during PayPal payment creation: {}",e);
        }

        return new RedirectView("/payment/error");

    }

    /**
     * Se llama después de que el usuario aprueba el pago en PayPal.
     * Si el pago es exitoso, redirige al endpoint para guardar el pedido.
     */
    @GetMapping("/payment/success")
    public String paymentSuccess(@RequestParam("paymentId") String paymentId,
                                 @RequestParam("PayerID") String payerId){

        try {

            Payment payment = paypalService.executePayment(paymentId,payerId);

            logger.info("PayPal Payment State: {}", payment.getState());

            if(payment.getState().equals("approved")){

                // <-- MODIFICACIÓN CRUCIAL: Redirige al controlador que guarda el pedido
                return "redirect:/tienda/guardarPedido";

            }

        }catch (PayPalRESTException e){
            logger.error("Error occurred during PayPal payment execution: {}",e);
        }

        // Si el estado no es 'approved' o hay una excepción, muestra la página de error
        return "/paypal/paymentError";

    }

    @GetMapping("/payment/cancel")
    public String paymentCancel (){

        return "/paypal/paymentCancel";

    }

    @GetMapping("/payment/error")
    public String paymentError (){

        return "/paypal/paymentError";

    }

}