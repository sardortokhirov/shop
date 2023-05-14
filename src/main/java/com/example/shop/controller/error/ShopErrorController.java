package com.example.shop.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Date-5/13/2023
 * Time-7:48 AM
 */
@Controller
public class ShopErrorController implements ErrorController {
    @GetMapping("/error")
    public String handleError() {
        return "error";
    }
}
