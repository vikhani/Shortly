package com.vkhani.shortly.controllers;

import com.vkhani.shortly.dtos.UrlPairDto;
import com.vkhani.shortly.exceptions.CodeTakenException;
import com.vkhani.shortly.exceptions.NotFoundException;
import com.vkhani.shortly.models.UrlPair;
import com.vkhani.shortly.services.UrlPairsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin
@RequestMapping(UrlPairsController.URI_PAIRS_PATH)
public class UrlPairsController {

    protected static final String URI_PAIRS_PATH = "/api";

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

    @PostMapping("/add")
    public UrlPairDto createURlPair(@RequestParam("longUrl") String longUrl) {
        UrlPair suitablePair = service.getOrCreatePair(longUrl);

        return UrlPairsService.convertUrlPairToDto(suitablePair);
    }

    @PostMapping("/add/custom")
    public ResponseEntity<UrlPairDto> createCustomURlPair(@RequestParam("userVersion") String userVersion, @RequestParam("longUrl") String longUrl) {
        String url = longUrl.trim();
        String customCode = userVersion.trim();

        if (service.getUrlPairByShort(customCode) != null)
            throw new CodeTakenException("Code " + userVersion + " already taken.");

        var existingPair = service.getUrlPairsByLong(url);

        UrlPair suitablePair = existingPair.stream()
                .filter(x -> x.getShortcutCode().equals(customCode))
                .findFirst()
                .orElse(service.createCustomUrlPair(url, customCode));

        return new ResponseEntity<>(UrlPairsService.convertUrlPairToDto(suitablePair), HttpStatus.OK);
    }
}
