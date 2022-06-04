package com.vkhani.Shortly.Controllers;

import com.vkhani.Shortly.Models.UrlPair;
import com.vkhani.Shortly.Services.UrlPairsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin()
public class UrlPairsController {
    @Autowired
    UrlPairsService service;

    @GetMapping("/{shortPart}")
    public RedirectView redirectToOriginalLink(@PathVariable("shortPart") String shortPart){
        var tet = service.getAll();
        var res = service.getUrlPairByShort(shortPart);
        if(res != null){
            return new RedirectView(res.getLongURL());
        }
        else{
            return null;
        }
    }

    @PutMapping()
    public UrlPair createURlPair(@RequestParam("longUrl") String longUrl){
        var existenceCheck = service.getUrlPairByLong(longUrl);
        if(existenceCheck != null)
            return existenceCheck;
        else
            return service.createUrlPair(longUrl);
    }
}
