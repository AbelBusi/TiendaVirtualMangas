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

    @PostMapping("/payment/create")
    public RedirectView createPayment(){

        try {

            String cancelUrl = "http://localhost:8089/paypal/payment/cancel";
            String successUrl = "http://localhost:8089/paypal/payment/success";

            Payment payment = paypalService.createPayment(
                    10.0,
                    "USD",
                    "paypal",
                    "sale",
                    "Payment description",
                    cancelUrl,
                    successUrl
            );

            for (Links links: payment.getLinks()){
                if(links.getRel().equals("approval_url")){

                    return new RedirectView(links.getHref());

                }
            }

        }catch (PayPalRESTException e){
            logger.error("Error occurred: {}",e);
        }

        return new RedirectView("/payment/error");

    }

    @GetMapping("/payment/success")
    public String paymentSuccess(@RequestParam("paymentId") String paymentId,
                                 @RequestParam("PayerID") String payerId){

        try {

            Payment payment = paypalService.executePayment(paymentId,payerId);
            if(payment.getState().equals("approved")){

                return "/paypal/paymentSuccess";

            }

        }catch (PayPalRESTException e){
            logger.info("Error occurred: {}",e);
        }

        return "/paypal/paymentSuccess";

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