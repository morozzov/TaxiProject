package ru.taxi.orderprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCarsCriteria {

    private ECriteria criteria;
    private Sort sort;

}
