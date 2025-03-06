package pt.jorgeduarte.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReadWriteRoutingDataSource extends AbstractRoutingDataSource {
    private static final Logger logger = LoggerFactory.getLogger(ReadWriteRoutingDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        boolean isTxActive = TransactionSynchronizationManager.isActualTransactionActive();

        logger.info("Transaction active: {}, Read-only: {}", isTxActive, isReadOnly);

        String lookupKey = isReadOnly ? "standby" : "primary";
        logger.info("Selected DataSource: {}", lookupKey);
        return lookupKey;
    }
}