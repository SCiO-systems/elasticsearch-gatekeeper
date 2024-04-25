package services.scio.qvantum.models;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class IndexInfoTest {
    private String indexName;
    private String modelName;
    private String timestamp;
    private Long timeInMillis;
    private Integer dims;

    private Boolean isBetween(Long before, Long after, Long time){
        return (before <= time) && (time <= after);
    }
    @Test
    public void testConstructor() {
        Integer dimensions = Instancio.create(Integer.class);
        String modelNam = Instancio.create(String.class);
        Long tInMillisBefore = System.currentTimeMillis();
        IndexInfo info = new IndexInfo(modelNam, dimensions);
        Long tInMillisAfter = System.currentTimeMillis();
        assertEquals(info.getClass(), IndexInfo.class);
        assertEquals("cigi-" + modelNam + "-" + info.getTimestamp() , info.getIndexName());
        assertEquals(dimensions, info.getDims());
        assertEquals(modelNam, info.getModelName());
        assertTrue(isBetween(tInMillisBefore, tInMillisAfter, info.getTimeInMillis()));
    }

    @Test
    public void testGettersAndSetters() {
        Integer dimensions = Instancio.create(Integer.class);
        String modelNam = Instancio.create(String.class);
        String indexName = Instancio.create(String.class);
        String timestamp = Instancio.create(String.class);
        Long timeinMil = Instancio.create(Long.class);

        IndexInfo info = Instancio.create(IndexInfo.class);

        info.setIndexName(indexName);
        info.setModelName(modelNam);
        info.setTimestamp(timestamp);
        info.setTimeInMillis(timeinMil);
        info.setDims(dimensions);

        assertEquals(info.getClass(), IndexInfo.class);
        assertEquals(info.getDims(), dimensions);
        assertEquals(info.getIndexName(), indexName);
        assertEquals(info.getModelName(), modelNam);
        assertEquals(info.getTimestamp(), timestamp);
        assertEquals(info.getTimeInMillis(), timeinMil);
    }

    @Test
    public void testEqualsForEquality() {
        Integer dimensions = Instancio.create(Integer.class);
        String modelNam = Instancio.create(String.class);

        IndexInfo info1 = new IndexInfo(modelNam, dimensions);
        IndexInfo info2 = new IndexInfo(modelNam, dimensions);

        //have to hardcode change timestamp related properties to match to compare
        info2.setTimestamp(info1.getTimestamp());
        info2.setTimeInMillis(info1.getTimeInMillis());
        info2.setIndexName(info1.getIndexName());

        assertTrue(info1.equals(info2));
    }

    @Test
    public void testEqualsForInequality() {
        IndexInfo info1 = Instancio.create(IndexInfo.class);
        IndexInfo info2 = Instancio.create(IndexInfo.class);

        assertFalse(info1.equals(info2));
    }

    @Test
    public void testToString() {
        IndexInfo info = Instancio.create(IndexInfo.class);

        String indexName = info.getIndexName();
        String modelName =  info.getModelName();
        String timestamp = info.getTimestamp();
        Long timeInMillis = info.getTimeInMillis();
        Integer dims = info.getDims();

        String expectedStr = "IndexInfo{" +
                "indexName='" + indexName + '\'' +
                ", modelName=" + modelName +
                ", timestamp=" + timestamp +
                ", timeInMillis='" + timeInMillis + '\'' +
                ", dims='" + dims + '\'' +
                '}';

        assertEquals(expectedStr, info.toString());
    }

    @Test
    public void testHashCode() {
        IndexInfo info = Instancio.create(IndexInfo.class);
        String indexName = info.getIndexName();
        String modelName =  info.getModelName();
        String timestamp = info.getTimestamp();
        Long timeInMillis = info.getTimeInMillis();
        Integer dims = info.getDims();

        int expectedHashCode = Objects.hash(indexName, modelName, timestamp, timeInMillis, dims);
        assertEquals(expectedHashCode, info.hashCode());
    }

}