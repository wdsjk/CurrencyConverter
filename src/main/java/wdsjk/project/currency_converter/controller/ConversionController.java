package wdsjk.project.currency_converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import wdsjk.project.currency_converter.converter.Converter;

import java.io.IOException;

@Controller
public class ConversionController {
    private final Converter converter;

    @Autowired
    public ConversionController(Converter converter) {
        this.converter = converter;
    }

    @GetMapping
    public String index(@ModelAttribute("converter") Converter converter) {
        return "index";
    }

    @PostMapping
    public String convert(@ModelAttribute("converter") Converter converter) {
        try {
            converter.convert();
        } catch (IOException e) {
            return "error";
        }
        return "conversion";
    }
}
