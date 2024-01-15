package com.transferz.service.dto.airport;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pagination {
    private Integer offset;
    private Integer limit;
}
