package com.unbounce.scraper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unbounce.scraper.bootstrap.MessageHandler;

public class LoggingMessageHandler implements MessageHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingMessageHandler.class);

    @Override public void handleMessage(final String message)
    {
        LOGGER.info("Received message: {}", message);
    }
}
