package org.example.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
public class KVPair implements Comparable<KVPair>{

    public String key;
    private String value;

    public KVPair() {
    }

    public KVPair(String key, String value) throws NullPointerException {
        if (key == null) throw new NullPointerException();
        if (value == null) throw new NullPointerException();

        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KVPair kvPair = (KVPair) o;
        return Objects.equals(key, kvPair.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public int compareTo(KVPair o) {
        return key.compareTo(o.getKey());
    }
}
