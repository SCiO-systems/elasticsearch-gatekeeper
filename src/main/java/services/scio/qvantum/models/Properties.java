package services.scio.qvantum.models;

import java.util.Objects;

public class Properties {
    private Embeddings embeddings;

    public Properties (Embeddings embeddings) {
        this.embeddings = new Embeddings(embeddings.getType(), embeddings.getDims(), embeddings.getIndex(), embeddings.getSimilarity());
    }

    public Embeddings getEmbeddings() {
        return embeddings;
    }

    public void setEmbeddings(Embeddings embeddings) {
        this.embeddings = embeddings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Properties that = (Properties) o;
        return Objects.equals(embeddings, that.embeddings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(embeddings);
    }

    @Override
    public String toString() {
        return "Properties{" +
                "embeddings=" + embeddings.toString() +
                '}';
    }

}
