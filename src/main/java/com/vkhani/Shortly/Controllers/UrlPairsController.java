package com.vkhani.Shortly.Controllers;

import com.vkhani.Shortly.Models.UrlPair;
import com.vkhani.Shortly.Services.UrlPairsService;

import com.vkhani.Shortly.dtos.UrlPairDto;
import com.vkhani.Shortly.exceptions.NotFoundException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@CrossOrigin
public class UrlPairsController {
    @Autowired
    UrlPairsService service;

    @GetMapping("/{shortPart}")
    public RedirectView redirectToOriginalLink(@PathVariable("shortPart") String shortPart) {
        var res = service.getUrlPairByShort(shortPart);

        if (res == null) {
            throw new NotFoundException("Shortening " + shortPart + " not found.");
        } else {
            return new RedirectView(res.getLongURL());
        }
    }

    @PutMapping("/add")
    public UrlPairDto createURlPair(@RequestParam("longUrl") String longUrl) {
        String url = longUrl.trim();
        List<UrlPair> existingPairs = service.getUrlPairsByLong(url);

        UrlPair suitablePair = existingPairs.stream()
                .filter(urlPair -> !urlPair.isCustom())
                .findFirst()
                .orElse(service.createUrlPair(url));

        return UrlPairsService.convertUrlPairToDto(suitablePair);
    }

    @PutMapping("/add/custom")
    public UrlPairDto createCustomURlPair(@RequestParam("userVersion") String userVersion, @RequestParam("longUrl") String longUrl) {
        String url = longUrl.trim();
        String customCode = userVersion.trim();

        var existingPair = service.getUrlPairsByLong(url);

        UrlPair suitablePair = existingPair.stream()
                .filter(x -> x.getShortcutCode().equals(customCode))
                .findFirst()
                .orElse(service.createCustomUrlPair(url, customCode));

        return UrlPairsService.convertUrlPairToDto(suitablePair);
    }
}
