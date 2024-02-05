package com.springbootacademy.possystem.controller;

import com.springbootacademy.possystem.dto.CustomerDTO;
import com.springbootacademy.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.possystem.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.possystem.dto.response.ItemGetResponseDTO;
import com.springbootacademy.possystem.service.ItemService;
import com.springbootacademy.possystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

//    @PostMapping(
//            path = {"/save"}
//    )
//    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
//        String message = itemService.saveItem(itemSaveRequestDTO);
//        return message;
//    }

    @PostMapping(
            path = "/save"
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        String message = itemService.saveItem(itemSaveRequestDTO);

//        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
//                new StandardResponse(201,"Success",message), HttpStatus.CREATED
//        );
//        return response;
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = "/get-all-items"
    )
    public List<ItemGetResponseDTO> getAllItems(){
        List<ItemGetResponseDTO> allitemDTOs = itemService.getAllItems();
        return allitemDTOs;
    }

    @GetMapping(
            path = "/get-by-name",
            params = "name"
    )
    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value = "name") String itemName){
        List<ItemGetResponseDTO> itemDTOs = itemService.getItemByNameAndStatus(itemName);
        return itemDTOs;
    }

    @GetMapping(
            path = "/get-by-status",
            params = {"status"}
    )
    public ResponseEntity<StandardResponse> getItemsByStatus(
            @RequestParam(value = "status") boolean activeStatus) {

        List<ItemGetResponseDTO> itemsByStatus = itemService.getItemsByStatus(activeStatus);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Success", itemsByStatus),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "/get-by-status-paginated",
            params = {"status","page","size"}
    )
    public ResponseEntity<StandardResponse> getItemsByStatusWithPaginated(
            @RequestParam(value = "status") boolean activeStatus,
            @RequestParam(value="page") int page,
            @RequestParam(value = "size") int size) {


        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemsByStatusWithPaginated(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Success", paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }
}
