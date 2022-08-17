package com.mobdevfc.cryptocurrencies.classes;

public class Url {
    // CONSTANTS
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String WEBSITE = "https://www.alphavantage.co/query?";

    /**
     * Get crypto information JSON.
     * @param timeSeries
     * @param symbol
     * @param market
     * @param interval
     * @return returns the url to request.
     */
    public static String getCryptocurrencies(String timeSeries, String symbol, String market, String interval){
        return WEBSITE + "function=" + timeSeries + "&symbol=" + symbol + "&market="
                + market + "&interval=" + interval + "&apikey=" + API_KEY;
    }
}
