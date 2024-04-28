package com.example.subject.response;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Error {
    String getField();
    String getRejectedValue();
    String getReason();

    /**
     *
     * @param collection
     * @param mapper
     * @return List<T>
     * @param <V>
     * @param <T>
     *
     * Collection을 받아서 순회하여 각 안에 있는 요소에 대하여 mapper적용 후 List로 반환
     */
    static <V, T> List<T> from(Collection<V> collection, Function<? super V, ? extends T> mapper) {
        return collection.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}