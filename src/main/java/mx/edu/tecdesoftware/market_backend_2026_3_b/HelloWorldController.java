package mx.edu.tecdesoftware.market_backend_2026_3_b;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldController {

    @GetMapping("/saludar")
    public String helloWorld() {
        return "Hello World!";
    }


}
