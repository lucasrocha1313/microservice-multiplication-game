package br.com.tonim.logs.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MarkerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogsConsumer {
    @RabbitListener(queues = "logs.queue")
    public void log(final String msg, @Header("level") String level, @Header("amqp_appId") String appId) {
        var marker = MarkerFactory.getMarker(appId);

        switch (level) {
            case "INFO" -> log.info(marker, msg);
            case "ERROR" -> log.error(marker, msg);
            case "WARN" -> log.warn(marker, msg);
        }
    }
}