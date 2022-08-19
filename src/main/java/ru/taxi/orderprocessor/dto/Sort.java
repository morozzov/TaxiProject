package ru.taxi.orderprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.SortOrder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sort {

    @NotBlank
    private String sortBy;
    @NotNull
    private SortOrder sortOrder;
}
