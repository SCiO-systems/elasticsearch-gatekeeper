package services.scio.qvantum.models;

import static org.junit.jupiter.api.Assertions.*;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class PropertiesTest {

    @Test
    public void testConstructor() {
        Integer dimensions = Instancio.create(Integer.class);
        Embeddings embs = new Embeddings(dimensions);
        Properties props = new Properties(embs);
        assertEquals(props.getClass(), Properties.class);
        assertEquals(embs, props.getEmbeddings());
    }

    @Test
    public void testGettersAndSetters() {
        Embeddings embeddings = Instancio.create(Embeddings.class);

        Properties props = new Properties(embeddings);

        props.setEmbeddings(embeddings);
        assertEquals(props.getClass(), Properties.class);
        assertEquals(props.getEmbeddings(), embeddings);
    }

    @Test
    public void testEqualsForEquality() {
        Embeddings embs = Instancio.create(Embeddings.class);

        Properties props1 = new Properties(embs);
        Properties props2 = new Properties(embs);

        assertTrue(props1.equals(props2));
    }

    @Test
    public void testEqualsForInequality() {
        Properties props1 = Instancio.create(Properties.class);
        Properties props2 = Instancio.create(Properties.class);

        assertFalse(props1.equals(props2));
    }

    @Test
    public void testToString() {
        Properties props = Instancio.create(Properties.class);

        Embeddings embeddings = props.getEmbeddings();

        String expectedStr = "Properties{" +
                "embeddings=" + embeddings.toString() +
                '}';

        assertEquals(expectedStr, props.toString());
    }

    @Test
    public void testHashCode() {
        Properties props = Instancio.create(Properties.class);
        Embeddings embs = props.getEmbeddings();

        int expectedHashCode = Objects.hash(embs);
        assertEquals(expectedHashCode, props.hashCode());
    }

}