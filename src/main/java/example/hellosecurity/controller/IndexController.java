package example.hellosecurity.controller;

import example.hellosecurity.config.IgnoreRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {

    @Resource
    private IgnoreRequest ignoreRequest;

    @GetMapping("/")
    public String index() {
        return "hello";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/ignore")
    public IgnoreRequest ignoreRequest() {
        return ignoreRequest;
    }

}
