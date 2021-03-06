package com.netflix.discovery.shared.transport;

import com.google.inject.Inject;
import com.netflix.archaius.Config;
import com.netflix.archaius.annotations.Configuration;
import com.netflix.archaius.annotations.ConfigurationSource;

/**
 * @author David Liu
 */
@Configuration(prefix = "eureka.transport")
@ConfigurationSource("eureka-client")
public class EurekaArchaius2TransportConfig implements EurekaTransportConfig {
    private static final String DEFAULT_NAMESPACE = "eureka.transport";

    private final Config config;

    @Inject
    public EurekaArchaius2TransportConfig(Config config) {
        this(config, DEFAULT_NAMESPACE);
    }

    public EurekaArchaius2TransportConfig(Config config, String namespace) {
        this.config = config.getPrefixedView(namespace);
    }

    @Override
    public int getSessionedClientReconnectIntervalSeconds() {
        return config.getInteger("sessionedClientReconnectIntervalSeconds", 30*60);
    }

    @Override
    public int getBootstrapResolverRefreshIntervalSeconds() {
        return config.getInteger("bootstrapResolverRefreshIntervalSeconds", 5 * 60);
    }

    @Override
    public int getApplicationsResolverDataStalenessThresholdSeconds() {
        return config.getInteger("applicationsResolverDataStalenessThresholdSeconds", 5*60);
    }

    @Override
    public int getAsyncResolverRefreshIntervalMs() {
        return config.getInteger("asyncResolverRefreshIntervalMs", 5 * 60 * 1000);
    }

    @Override
    public int getAsyncResolverWarmUpTimeoutMs() {
        return config.getInteger("asyncResolverWarmupTimeoutMs", 5000);
    }

    @Override
    public int getAsyncExecutorThreadPoolSize() {
        return config.getInteger("asyncExecutorThreadPoolSize", 5);
    }

    @Override
    public String getReadClusterVip() {
        return config.getString("readClusterVip", null);
    }

    @Override
    public boolean useBootstrapResolverForQuery() {
        return config.getBoolean("useBootstrapResolverForQuery", true);
    }
}
