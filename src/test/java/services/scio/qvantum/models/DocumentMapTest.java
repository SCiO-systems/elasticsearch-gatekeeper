package services.scio.qvantum.models;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

class DocumentMapTest {
    @Test
    public void testDefaultConstructor() {
        DocumentMap docMap = new DocumentMap();
        assertEquals(null, docMap.getMetadata_id());
        assertEquals(null, docMap.getText());
        assertEquals(null, docMap.getPart());
        assertEquals(null, docMap.getChapter());
        assertEquals(null, docMap.getWord_count());
        assertEquals(null, docMap.getLanguage());
        assertEquals(null, docMap.getModel());
        assertEquals(new ArrayList<Integer>(), docMap.getEmbeddings());
    }

    @Test
    public void testParametrizedConstructor() {
        String metadataID = Instancio.create(String.class);
        String text = Instancio.create(String.class);
        Integer part = Instancio.create(Integer.class);
        String chapter = Instancio.create(String.class);
        Integer word_count = Instancio.create(Integer.class);
        String language = Instancio.create(String.class);
        String model = Instancio.create(String.class);
        ArrayList<Double> embeddings = new ArrayList<Double>();
        Double embedding = Instancio.create(Double.class);
        embeddings.add(embedding);
        embedding = Instancio.create(Double.class);
        embeddings.add(embedding);
        embedding = Instancio.create(Double.class);
        embeddings.add(embedding);

        DocumentMap docMap = new DocumentMap(metadataID,text,part,chapter,word_count,language,model,embeddings);

        assertEquals(docMap.getClass(), DocumentMap.class);
        assertEquals(docMap.getMetadata_id(), metadataID);
        assertEquals(docMap.getText(), text);
        assertEquals(docMap.getPart(), part);
        assertEquals(docMap.getChapter(), chapter);
        assertEquals(docMap.getWord_count(), word_count);
        assertEquals(docMap.getLanguage(), language);
        assertEquals(docMap.getModel(), model);
        assertEquals(docMap.getEmbeddings(), embeddings);
    }

    @Test
    public void testGettersAndSetters() {
        String metadataID = Instancio.create(String.class);
        String text = Instancio.create(String.class);
        Integer part = Instancio.create(Integer.class);
        String chapter = Instancio.create(String.class);
        Integer word_count = Instancio.create(Integer.class);
        String language = Instancio.create(String.class);
        String model = Instancio.create(String.class);
        ArrayList<Double> embeddings = new ArrayList<Double>();
        Double embedding = Instancio.create(Double.class);
        embeddings.add(embedding);
        embedding = Instancio.create(Double.class);
        embeddings.add(embedding);
        embedding = Instancio.create(Double.class);
        embeddings.add(embedding);

        DocumentMap docMap = new DocumentMap();

        docMap.setMetadata_id(metadataID);
        docMap.setText(text);
        docMap.setPart(part);
        docMap.setChapter(chapter);
        docMap.setWord_count(word_count);
        docMap.setLanguage(language);
        docMap.setModel(model);
        docMap.setEmbeddings(embeddings);

        assertEquals(docMap.getClass(), DocumentMap.class);
        assertEquals(docMap.getMetadata_id(), metadataID);
        assertEquals(docMap.getText(), text);
        assertEquals(docMap.getPart(), part);
        assertEquals(docMap.getChapter(), chapter);
        assertEquals(docMap.getWord_count(), word_count);
        assertEquals(docMap.getLanguage(), language);
        assertEquals(docMap.getModel(), model);
        assertEquals(docMap.getEmbeddings(), embeddings);
    }

    @Test
    public void testEqualsForEquality() {
        String metadataID = Instancio.create(String.class);
        String text = Instancio.create(String.class);
        Integer part = Instancio.create(Integer.class);
        String chapter = Instancio.create(String.class);
        Integer word_count = Instancio.create(Integer.class);
        String language = Instancio.create(String.class);
        String model = Instancio.create(String.class);
        ArrayList<Double> embeddings = new ArrayList<Double>();
        Double embedding = Instancio.create(Double.class);
        embeddings.add(embedding);
        embedding = Instancio.create(Double.class);
        embeddings.add(embedding);
        embedding = Instancio.create(Double.class);
        embeddings.add(embedding);

        DocumentMap docMap1 = new DocumentMap(metadataID,text,part,chapter,word_count,language,model,embeddings);
        DocumentMap docMap2 = new DocumentMap(metadataID,text,part,chapter,word_count,language,model,embeddings);

        assertTrue(docMap1.equals(docMap2));
    }

    @Test
    public void testEqualsForInequality() {
        DocumentMap docMap1 = Instancio.create(DocumentMap.class);
        DocumentMap docMap2 = Instancio.create(DocumentMap.class);

        assertFalse(docMap1.equals(docMap2));
    }

    @Test
    public void testToString() {
        DocumentMap documentMap = Instancio.create(DocumentMap.class);
        String metadataID = documentMap.getMetadata_id();
        String text = documentMap.getText();
        Integer part = documentMap.getPart();
        String chapter = documentMap.getChapter();
        Integer word_count = documentMap.getWord_count();
        String language = documentMap.getLanguage();
        String model = documentMap.getModel();
        ArrayList<Double> embeddings = documentMap.getEmbeddings();

        String expectedStr = "DocumentMap{" +
                "metadata_id='" + metadataID + '\'' +
                "text='" + text + '\'' +
                "part='" + part + '\'' +
                "chapter='" + chapter + '\'' +
                "word_count='" + word_count + '\'' +
                "language='" + language + '\'' +
                "model='" + model + '\'' +
                "embeddings='" + embeddings.toString() + '\'' +
                '}';

        assertEquals(expectedStr, documentMap.toString());
    }

    @Test
    public void testHashCode() {
        DocumentMap documentMap = Instancio.create(DocumentMap.class);
        String metadataID = documentMap.getMetadata_id();
        String text = documentMap.getText();
        Integer part = documentMap.getPart();
        String chapter = documentMap.getChapter();
        Integer word_count = documentMap.getWord_count();
        String language = documentMap.getLanguage();
        String model = documentMap.getModel();
        ArrayList<Double> embeddings = documentMap.getEmbeddings();

        int expectedHashCode = Objects.hash(metadataID, text, part, chapter, word_count, language, model, embeddings);
        assertEquals(expectedHashCode, documentMap.hashCode());
    }

}