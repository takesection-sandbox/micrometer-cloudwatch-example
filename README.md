Micrometer Cloudwatch
=====================

# 1.Installing

In sbt:

```
// https://mvnrepository.com/artifact/io.micrometer/micrometer-registry-cloudwatch
libraryDependencies += "io.micrometer" % "micrometer-registry-cloudwatch" % "1.0.5"
```

# 2.Configuring

```java_holder_method_tree
    CloudWatchConfig config =
        new CloudWatchConfig() {
            private Properties properties =
                    new MicrometerProperties().DEFAULT;
            @Override
            public String get(String key) {
                return properties.getProperty(key);
            }
        };
```

```java
import java.util.Properties;

public class MicrometerProperties {

    public Properties DEFAULT;

    public MicrometerProperties() {
        DEFAULT = new Properties();
        DEFAULT.setProperty("cloudwatch.enabled", "true");
        DEFAULT.setProperty("cloudwatch.namespace", "MICROMETER");
        DEFAULT.setProperty("cloudwatch.step", "PT1M");
        DEFAULT.setProperty("cloudwatch.batchSize", "1");
    }
}
```
