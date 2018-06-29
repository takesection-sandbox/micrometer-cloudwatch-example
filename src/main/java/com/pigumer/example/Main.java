package com.pigumer.example;

import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsync;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClientBuilder;
import io.micrometer.cloudwatch.CloudWatchConfig;
import io.micrometer.cloudwatch.CloudWatchMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;

import java.time.Duration;
import java.util.Properties;
import java.util.stream.IntStream;

public class Main {
    private static AmazonCloudWatchAsync cloudWatch =
         AmazonCloudWatchAsyncClientBuilder.standard().withRegion("ap-northeast-1").build();
    private static Clock clock = Clock.SYSTEM;
    private static CloudWatchConfig config =
            new CloudWatchConfig() {
                private Properties properties =
                        new MicrometerProperties().DEFAULT;
                @Override
                public String get(String key) {
                    return properties.getProperty(key);
                }
            };

    private static Runnable sleep = () -> {
        try {
            Thread.sleep(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) throws Exception {
        CloudWatchMeterRegistry registry =
                new CloudWatchMeterRegistry(config, clock, cloudWatch);
        new JvmThreadMetrics().bindTo(registry);

        IntStream.range(1, 20).forEach(n -> new Thread(sleep).run());

        Thread.sleep(60000 * 3);
    }
}
