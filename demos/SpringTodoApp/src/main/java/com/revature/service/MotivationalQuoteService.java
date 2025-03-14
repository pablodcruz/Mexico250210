package com.revature.service;

import com.revature.DTO.QuoteDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MotivationalQuoteService {

    // Base URL for the motivational quotes API (e.g., ZenQuotes)
    private final String quoteApiUrl = "https://zenquotes.io/api/random";

    // RestTemplate to make HTTP requests.
    private final RestTemplate restTemplate;

    public MotivationalQuoteService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Calls the external quotes API to retrieve a random quote.
     *
     * @return A motivational quote as a String.
     */
    public String getRandomQuote() {
        // The API returns an array of quotes; we take the first one.
        QuoteDto[] quotes = restTemplate.getForObject(quoteApiUrl, QuoteDto[].class);
        if (quotes != null && quotes.length > 0) {
            return quotes[0].getQ() + " - " + quotes[0].getA();
        }
        // Fallback quote in case of failure.
        return "Motivational quote failed, but keep pushing forward!";
    }
}
