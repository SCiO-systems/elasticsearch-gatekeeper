package services.scio.qvantum.models;

import org.json.JSONObject;

import java.util.Objects;

public class ElasticIndexMapping {
    private Properties properties;

    public ElasticIndexMapping (Properties props) {
        properties = new Properties(props.getEmbeddings());
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElasticIndexMapping that = (ElasticIndexMapping) o;
        return Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties);
    }

    @Override
    public String toString() {
        return "ElasticIndexMapping{" +
                "properties=" + properties.toString() +
                '}';
    }
}
