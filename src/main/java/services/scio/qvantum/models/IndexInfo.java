package services.scio.qvantum.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class IndexInfo {
    private String indexName;
    private String modelName;
    private String timestamp;
    private Long timeInMillis;
    private Integer dims;

    public IndexInfo (String modelName, Integer dims) {
        this.modelName = modelName;
        this.dims = dims;
        long currentTimestamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedTimestamp = sdf.format(new Date(currentTimestamp));
        this.timeInMillis = currentTimestamp;
        this.timestamp = formattedTimestamp;
        this.indexName = "cigi-" + modelName + "-" + formattedTimestamp;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(Long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public Integer getDims() {
        return dims;
    }

    public void setDims(Integer dims) {
        this.dims = dims;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexInfo indexInfo = (IndexInfo) o;
        return Objects.equals(indexName, indexInfo.indexName) && Objects.equals(modelName, indexInfo.modelName) &&
                Objects.equals(timestamp, indexInfo.timestamp) && Objects.equals(timeInMillis, indexInfo.timeInMillis) &&
                Objects.equals(dims, indexInfo.dims);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexName, modelName, timestamp, timeInMillis, dims);
    }

    @Override
    public String toString(){
        return "IndexInfo{" +
                "indexName='" + indexName + '\'' +
                ", modelName=" + modelName +
                ", timestamp=" + timestamp +
                ", timeInMillis='" + timeInMillis + '\'' +
                ", dims='" + dims + '\'' +
                '}';
    }
}
