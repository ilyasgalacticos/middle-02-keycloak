package kz.bitlab.middle02.keycloakservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @GetMapping
    public List<String> getItems(){
        List<String> items = new ArrayList<>();
        items.add("Iphone");
        items.add("XIAOMI");
        items.add("SAMSUNG");
        items.add("NOKIA");
        return items;
    }
}
