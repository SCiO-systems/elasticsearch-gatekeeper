package services.scio.qvantum.models;

import java.util.ArrayList;
import java.util.Objects;

public class DocumentMap {
    String metadata_id;
    String text;
    Integer part;
    String chapter;
    Integer word_count;
    String language;
    String model;
    ArrayList<Double> embeddings;

    public DocumentMap(){
        this.embeddings = new ArrayList<Double>();
    }

    public DocumentMap(String metadata_id, String text, Integer part, String chapter, Integer word_count,
                       String language, String model, ArrayList<Double> embeddings){
        this.metadata_id = metadata_id;
        this.text = text;
        this.part = part;
        this.chapter = chapter;
        this.word_count = word_count;
        this.language = language;
        this.model = model;
        this.embeddings = new ArrayList<Double>(embeddings);
    }


    public String getMetadata_id() {
        return metadata_id;
    }

    public void setMetadata_id(String metadata_id) {
        this.metadata_id = metadata_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPart() {
        return part;
    }

    public void setPart(Integer part) {
        this.part = part;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public Integer getWord_count() {
        return word_count;
    }

    public void setWord_count(Integer word_count) {
        this.word_count = word_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ArrayList<Double> getEmbeddings() {
        return embeddings;
    }

    public void setEmbeddings(ArrayList<Double> embeddings) {
        this.embeddings = new ArrayList<Double>(embeddings);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        DocumentMap docMap = (DocumentMap) o;
        return Objects.equals(getMetadata_id(), docMap.getMetadata_id()) && Objects.equals(getText(), docMap.getText())
                && Objects.equals(getPart(), docMap.getPart()) && Objects.equals(getChapter(), docMap.getChapter())
                && Objects.equals(getWord_count(), docMap.getWord_count()) && Objects.equals(getLanguage(), docMap.getLanguage())
                && Objects.equals(getModel(), docMap.getModel()) && Objects.equals(getEmbeddings(), docMap.getEmbeddings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMetadata_id(), getText(), getPart(), getChapter(), getWord_count(),
                getLanguage(), getModel(), getEmbeddings());
    }

    @Override
    public String toString() {
        return "DocumentMap{" +
                "metadata_id='" + metadata_id + '\'' +
                "text='" + text + '\'' +
                "part='" + part + '\'' +
                "chapter='" + chapter + '\'' +
                "word_count='" + word_count + '\'' +
                "language='" + language + '\'' +
                "model='" + model + '\'' +
                "embeddings='" + embeddings.toString() + '\'' +
                '}';
    }

}
