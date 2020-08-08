package grpc.bilingual;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RpcProperties {

  private static final String FileName = "app.properties";
  private static final String PORT = "port";
  private static final String DOCKER_IMAGE = "docker-image";
  // The field is used to hold Singleton.
  private static RpcProperties rpcProperties;

  private final Properties properties;

  private RpcProperties(Properties properties) {
    this.properties = properties;
  }

  private static Properties loadProperties() {
    InputStream iStream;
    Properties properties = new Properties();

    try {
      // Loading properties file from the classpath
      iStream = RpcProperties.class.getClassLoader().getResourceAsStream(FileName);

      if (iStream == null) {
        throw new IOException(FileName + " not found.");
      }
      properties.load(iStream);
      iStream.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return properties;
  }

  public static RpcProperties get() {
    if (rpcProperties == null) {
      rpcProperties = new RpcProperties(loadProperties());
    }
    return rpcProperties;
  }

  public int getPort(int defaultValue) {
    String portStr = properties.getProperty(PORT);
    try {
      return Integer.parseInt(portStr);
    } catch (NumberFormatException ignored) {
      return defaultValue;
    }
  }

  public String getImage(String defaultValue) {
    String image = properties.getProperty(DOCKER_IMAGE);
    if (image == null) {
      return defaultValue;
    }
    return image;
  }
}
