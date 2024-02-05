package com.springbootacademy.possystem.util.mappers;

import com.springbootacademy.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.possystem.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.possystem.dto.response.ItemGetResponseDTO;
import com.springbootacademy.possystem.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemGetResponseDTO> entityListToDtoList(List<Item> items);
    ItemGetResponseDTO entityToDto(Item item);
    Item DtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);
    List<ItemGetResponseDTO> PageToDtoList(Page<Item> items);
}
