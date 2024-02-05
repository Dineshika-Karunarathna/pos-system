package com.springbootacademy.possystem.service.impl;

import com.springbootacademy.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.possystem.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.possystem.dto.response.ItemGetResponseDTO;
import com.springbootacademy.possystem.entity.Item;
import com.springbootacademy.possystem.exception.NotFoundException;
import com.springbootacademy.possystem.repo.ItemRepo;
import com.springbootacademy.possystem.service.ItemService;
import com.springbootacademy.possystem.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
//        Item item = modelMapper.map(itemSaveRequestDTO,Item.class);
        Item item = itemMapper.DtoToEntity(itemSaveRequestDTO);
        if(!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return "Saved "+item.getItemId();
        }
        else{
            throw new DuplicateKeyException("ID already exists");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        boolean b = true;
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveEquals(itemName,b);
        if(items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>(){}.getType());
            return itemGetResponseDTOS;
        }else{
            throw new RuntimeException("Item is Not Active");
        }
    }

    // Using MapStruct
    @Override
    public List<ItemGetResponseDTO> getAllItems() {

        List<Item> allItems = itemRepo.findAll();
        List<ItemGetResponseDTO> allItemDTOs = itemMapper.entityListToDtoList(allItems);
        return allItemDTOs;
    }

    @Override
    public List<ItemGetResponseDTO> getItemsByStatus(boolean activeStatus) {
        List<Item> itemsByStatus = itemRepo.findAllByActiveEquals(activeStatus);
        if(itemsByStatus.size()>0) {
            List<ItemGetResponseDTO> itemsByStatusDTOS = itemMapper.entityListToDtoList(itemsByStatus);
            return itemsByStatusDTOS;
        }else{
            throw new NotFoundException("No items in this status");
        }
    }

    @Override
    public PaginatedResponseItemDTO getItemsByStatusWithPaginated(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByActiveEquals(activeStatus, PageRequest.of(page,size));
        if(items.getSize()<1){
            throw new NotFoundException("No items");
        }
        PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                itemMapper.PageToDtoList(items),
                itemRepo.countAllByActiveEquals(activeStatus));

        return paginatedResponseItemDTO;
    }
}
