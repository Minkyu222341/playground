package elkdemo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LogGeneratorScheduler {

    private static final Logger logger = LoggerFactory.getLogger(LogGeneratorScheduler.class);
    private static final String[] LOG_LEVELS = {"INFO", "DEBUG", "WARN", "ERROR"};
    private static final String[] OPERATIONS = {"CREATE", "READ", "UPDATE", "DELETE"};
    private static final String[] ENTITIES = {"USER", "PRODUCT", "ORDER", "CATEGORY"};

    private final Random random = new Random();

    @Scheduled(fixedRate = 5000) // 매 5초마다 실행
    public void generateRandomLogs() {
        String logLevel = LOG_LEVELS[random.nextInt(LOG_LEVELS.length)];
        String operation = OPERATIONS[random.nextInt(OPERATIONS.length)];
        String entity = ENTITIES[random.nextInt(ENTITIES.length)];
        Long entityId = (long) random.nextInt(100) + 1;

        switch (logLevel) {
            case "INFO" -> logger.info("{} operation on {} with id: {}", operation, entity, entityId);
            case "DEBUG" -> logger.debug("Detailed debug information for {} operation on {} with id: {}", operation, entity, entityId);
            case "WARN" -> logger.warn("Warning while performing {} operation on {} with id: {}", operation, entity, entityId);
            case "ERROR" -> {
                if (random.nextBoolean()) {
                    logger.error("Error occurred during {} operation on {} with id: {}. Transaction rolled back.", operation, entity, entityId);
                } else {
                    try {
                        throw new RuntimeException("Simulated exception for testing");
                    } catch (Exception e) {
                        logger.error("Exception during {} operation on {} with id: {}", operation, entity, entityId, e);
                    }
                }
            }
        }
    }
}