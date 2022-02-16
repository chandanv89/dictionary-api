package com.github.chandanv89.dictionary.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;

@Controller
public class OpiApiController {

    //@RequestMapping("/")
    @Operation(hidden = true)
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
