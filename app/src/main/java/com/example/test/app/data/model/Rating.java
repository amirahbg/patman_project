package com.example.test.app.data.model;

import com.example.test.app.utils.Objects;
import com.google.gson.annotations.SerializedName;

class Rating {
    @SerializedName("Source")
    private String source;

    @SerializedName("Value")
    private String value;

    public Rating(String source, String value) {
        this.source = source;
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(source, rating.source) &&
                Objects.equals(value, rating.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, value);
    }
}
