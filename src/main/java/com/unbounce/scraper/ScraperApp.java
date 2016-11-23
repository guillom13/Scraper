package com.unbounce.scraper;

import com.unbounce.scraper.bootstrap.ScraperQueueSender;

public final class ScraperApp
{
    private ScraperApp()
    {
        // private utility class constructor
    }

    public static void main(final String[] args)
    {
        final LoggingMessageHandler messageHandler = new LoggingMessageHandler();
        final ScraperQueueSender sender = new ScraperQueueSender(messageHandler,
                                                                 ScraperQueueSender.Mode.HAPPY_PATH);
        // Replace HAPPY_PATH with REALISTIC when you're ready to deal with error handling for some
        // messages that don't always exactly conform to the schema, or have other quirks.
        sender.run();
        sender.shutdown();
    }

}
