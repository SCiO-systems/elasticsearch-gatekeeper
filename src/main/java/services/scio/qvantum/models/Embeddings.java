package services.scio.qvantum.models;

import java.util.Objects;

public class Embeddings {
    private String type;
    private Integer dims;
    private Boolean index;
    private String similarity;

    public Embeddings (Integer dimensions) {
        this.type = "dense_vector";
        this.dims = dimensions;
        this.index = true;
        this.similarity = "cosine";
    }

    public Embeddings (Integer dimensions, String similarity) {
        this.type = "dense_vector";
        this.dims = dimensions;
        this.index = true;
        this.similarity = similarity;
    }

    public Embeddings (String type, Integer dimensions, Boolean index, String similarity) {
        this.type = type;
        this.dims = dimensions;
        this.index = index;
        this.similarity = similarity;
    }

    public String getType() {
        return type;
    }

    public Integer getDims() {
        return dims;
    }

    public Boolean getIndex() {
        return index;
    }

    public String getSimilarity() {
        return similarity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDims(Integer dims) {
        this.dims = dims;
    }

    public void setIndex(Boolean index) {
        this.index = index;
    }

    public void setSimilarity(String similarity) {
        this.similarity = similarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Embeddings that = (Embeddings) o;
        return Objects.equals(type, that.type) && Objects.equals(dims, that.dims) && Objects.equals(index, that.index) && Objects.equals(similarity, that.similarity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, dims, index, similarity);
    }

    @Override
    public String toString() {
        return "Embeddings{" +
                "type='" + type + '\'' +
                ", dims=" + dims +
                ", index=" + index +
                ", similarity='" + similarity + '\'' +
                '}';
    }
}
