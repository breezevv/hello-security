package example.hellosecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "hello";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
