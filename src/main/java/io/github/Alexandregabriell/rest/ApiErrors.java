package io.github.Alexandregabriell.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String mensagemErro) {
        this.errors = Arrays.asList(mensagemErro);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }
}
