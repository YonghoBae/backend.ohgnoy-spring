package backend.ohgnoy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/hello")
    @ResponseBody
    public String index(){
        return "Hello, I'm ohgnoy_after";
    }
}
