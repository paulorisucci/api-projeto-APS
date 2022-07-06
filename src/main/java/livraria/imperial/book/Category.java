package livraria.imperial.book;

import livraria.imperial.exceptions.InvalidCategoryException;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Category {

    FICCAO_LITERARIA("Ficcão Literária"),
    NAO_FICCAO("Não ficção"),
    SUSPENSE("Suspense"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    FANTASIA("Fantasia"),
    HORROR("Horror"),
    POESIA("Poesia"),
    ROMANCE("Romance");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public static Category get(String recievedName) {
        return Arrays.stream(values())
                .filter(category -> category.getName().equalsIgnoreCase(recievedName))
                .findFirst()
                .orElseThrow(() -> new InvalidCategoryException("A categoria inserida é inválida"));
    }
}
