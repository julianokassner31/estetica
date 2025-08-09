package br.com.jkassner.estetica.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationDto<T> {

    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<T> elements;
}
