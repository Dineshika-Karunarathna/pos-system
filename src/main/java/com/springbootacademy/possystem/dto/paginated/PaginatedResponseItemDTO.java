package com.springbootacademy.possystem.dto.paginated;

import com.springbootacademy.possystem.dto.response.ItemGetResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedResponseItemDTO {

    List<ItemGetResponseDTO> items;
    private long dataCount;
}
