package com.revature.DTO;

/**
 * A DTO to map the response from the motivational quotes API.
 * For example, from ZenQuotes, 'q' holds the quote and 'a' the author.
 */
public class QuoteDto {
    private String q;
    private String a;

    public String getQ() {
        return q;
    }
    public void setQ(String q) {
        this.q = q;
    }
    public String getA() {
        return a;
    }
    public void setA(String a) {
        this.a = a;
    }
}
