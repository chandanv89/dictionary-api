package com.github.chandanv89.dictionaryservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OpiApiController {

    @RequestMapping("/")
    @Operation(hidden = true)
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
