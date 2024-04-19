package services.scio.qvantum.models;

import org.instancio.Instancio;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ElasticIndexMappingTest {

    @Test
    public void testConstructor() {
        Properties props = Instancio.create(Properties.class);
        ElasticIndexMapping mapping = new ElasticIndexMapping(props);
        assertEquals(mapping.getClass(), ElasticIndexMapping.class);
        assertEquals(props, mapping.getProperties());
    }

    @Test
    public void testGettersAndSetters() {
        Properties props = Instancio.create(Properties.class);

        ElasticIndexMapping mapping = new ElasticIndexMapping(props);

        mapping.setProperties(props);
        assertEquals(mapping.getClass(), ElasticIndexMapping.class);
        assertEquals(mapping.getProperties(), props);
    }

    @Test
    public void testEqualsForEquality() {
        Properties props = Instancio.create(Properties.class);

        ElasticIndexMapping mapping1 = new ElasticIndexMapping(props);
        ElasticIndexMapping mapping2 = new ElasticIndexMapping(props);
        assertTrue(mapping1.equals(mapping2));
    }

    @Test
    public void testEqualsForInequality() {
        ElasticIndexMapping mapping1 = Instancio.create(ElasticIndexMapping.class);
        ElasticIndexMapping mapping2 = Instancio.create(ElasticIndexMapping.class);

        assertFalse(mapping2.equals(mapping1));
    }

    @Test
    public void testToString() {
        ElasticIndexMapping mapping = Instancio.create(ElasticIndexMapping.class);

        Properties props = mapping.getProperties();

        String expectedStr = "ElasticIndexMapping{" +
                "properties=" + props.toString() +
                '}';

        assertEquals(expectedStr, mapping.toString());
    }

    @Test
    public void testHashCode() {
        ElasticIndexMapping mapping = Instancio.create(ElasticIndexMapping.class);
        Properties props = mapping.getProperties();

        int expectedHashCode = Objects.hash(props);
        assertEquals(expectedHashCode, mapping.hashCode());
    }

}