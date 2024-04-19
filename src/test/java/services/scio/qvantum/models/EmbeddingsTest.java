package services.scio.qvantum.models;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.Objects;


import static org.junit.jupiter.api.Assertions.*;

class EmbeddingsTest {

    @Test
    public void testConstructor1() {
        Integer dimensions = Instancio.create(Integer.class);
        Embeddings embs = new Embeddings(dimensions);
        assertEquals(embs.getClass(), Embeddings.class);
        assertEquals("dense_vector", embs.getType());
        assertEquals(dimensions, embs.getDims());
        assertEquals(true, embs.getIndex());
        assertEquals("cosine", embs.getSimilarity());
    }

    @Test
    public void testConstructor2() {
        String similarity = Instancio.create(String.class);
        Integer dimensions = Instancio.create(Integer.class);
        Embeddings embs = new Embeddings(dimensions, similarity);
        assertEquals(embs.getClass(), Embeddings.class);
        assertEquals("dense_vector", embs.getType());
        assertEquals(dimensions, embs.getDims());
        assertEquals(true, embs.getIndex());
        assertEquals(similarity, embs.getSimilarity());
    }

    @Test
    public void testConstructor3() {
        String similarity = Instancio.create(String.class);
        String type = Instancio.create(String.class);
        Boolean index = Instancio.create(Boolean.class);
        Integer dimensions = Instancio.create(Integer.class);
        Embeddings embs = new Embeddings(type, dimensions, index, similarity);
        assertEquals(embs.getClass(), Embeddings.class);
        assertEquals(type, embs.getType());
        assertEquals(dimensions, embs.getDims());
        assertEquals(index, embs.getIndex());
        assertEquals(similarity, embs.getSimilarity());
    }

    @Test
    public void testGettersAndSetters() {
        String similarity = Instancio.create(String.class);
        String type = Instancio.create(String.class);
        Boolean index = Instancio.create(Boolean.class);
        Integer dimensions = Instancio.create(Integer.class);

        Embeddings embs = new Embeddings(2);

        embs.setDims(dimensions);
        embs.setIndex(index);
        embs.setSimilarity(similarity);
        embs.setType(type);

        assertEquals(embs.getClass(), Embeddings.class);
        assertEquals(embs.getDims(), dimensions);
        assertEquals(embs.getIndex(), index);
        assertEquals(embs.getSimilarity(), similarity);
        assertEquals(embs.getType(), type);
    }

    @Test
    public void testEqualsForEquality() {
        String similarity = Instancio.create(String.class);
        String type = Instancio.create(String.class);
        Boolean index = Instancio.create(Boolean.class);
        Integer dimensions = Instancio.create(Integer.class);

        Embeddings embs1 = new Embeddings(type, dimensions, index, similarity);
        Embeddings embs2 = new Embeddings(type, dimensions, index, similarity);

        assertTrue(embs1.equals(embs2));
    }

    @Test
    public void testEqualsForInequality() {
        Embeddings embs1 = Instancio.create(Embeddings.class);
        Embeddings embs2 = Instancio.create(Embeddings.class);

        assertFalse(embs1.equals(embs2));
    }

    @Test
    public void testToString() {
        Embeddings embs = Instancio.create(Embeddings.class);

        String type = embs.getType();
        Integer dimensions =  embs.getDims();
        Boolean index = embs.getIndex();
        String similarity = embs.getSimilarity();

        String expectedStr = "Embeddings{" +
                "type='" + type + '\'' +
                ", dims=" + dimensions +
                ", index=" + index +
                ", similarity='" + similarity + '\'' +
                '}';

        assertEquals(expectedStr, embs.toString());
    }

    @Test
    public void testHashCode() {
        Embeddings embs = Instancio.create(Embeddings.class);
        String type = embs.getType();
        Integer dimensions =  embs.getDims();
        Boolean index = embs.getIndex();
        String similarity = embs.getSimilarity();

        int expectedHashCode = Objects.hash(type, dimensions, index, similarity);
        assertEquals(expectedHashCode, embs.hashCode());
    }

}