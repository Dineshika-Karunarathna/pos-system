package com.springbootacademy.possystem.service;

import com.springbootacademy.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.possystem.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.possystem.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getAllItems();

    List<ItemGetResponseDTO> getItemsByStatus(boolean activeStatus);

    PaginatedResponseItemDTO getItemsByStatusWithPaginated(boolean activeStatus, int page, int size);
}
