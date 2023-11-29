package org.example.LoggingExample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XMLLog {
    private static final Logger logger = LogManager.getLogger(XMLLog.class);

    public static void main(String[] args) {
        // Sử dụng log
        logger.info("Đây là một thông báo thông tin");
        logger.debug("DEBUG LOG");
    }
}

