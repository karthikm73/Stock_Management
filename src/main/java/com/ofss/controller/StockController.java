package com.ofss.controller;

import java.util.List;

import com.ofss.exceptions.StockNotFoundException;
import com.ofss.model.Stock;
import com.ofss.service.StockService;
import com.ofss.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/stocks")
    public ResponseEntity<ApiResponse<List<Stock>>> getAllStocks() {
        try {
            List<Stock> allStocks = stockService.getAllStocks();
            return ResponseEntity.ok(ApiResponse.success("Stocks retrieved successfully", allStocks));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to retrieve stocks"));
        }
    }

    @GetMapping("/stocks/{stockId}")
    public ResponseEntity<ApiResponse<Stock>> getAStockById(@PathVariable("stockId") Long sid) {
        try {
            Stock stock = stockService.getAStockById(sid);
            return ResponseEntity.ok(ApiResponse.success("Stock retrieved successfully", stock));
        } catch (StockNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Stock not found with id: " + sid));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to retrieve stock"));
        }
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteStockById(@PathVariable(value = "id") Long id) {
        try {
            stockService.deleteAStockById(id);
            return ResponseEntity.ok(ApiResponse.success("Stock deleted successfully", null));
        } catch (StockNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Stock not found with id: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(ApiResponse.error("Failed to delete stock"));
        }
    }

    @PostMapping("/stocks")
    public ResponseEntity<ApiResponse<Stock>> addAStock(@RequestBody Stock newStock) {
        try {
            Stock createdStock = stockService.addAStock(newStock);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Stock created successfully", createdStock));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(ApiResponse.error("Failed to add stock"));
        }
    }

    @PutMapping("/stocks/{stockId}")
    public ResponseEntity<ApiResponse<Object>> updateAStockById(@PathVariable("stockId") Long sid, @RequestBody Stock newStock) {
        try {
            stockService.updateAStockById(sid, newStock);
            return ResponseEntity.ok(ApiResponse.success("Stock updated successfully", null));
        } catch (StockNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Stock not found with id: " + sid));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(ApiResponse.error("Failed to update stock"));
        }
    }

    @PatchMapping("/stocks/{stockId}")
    public ResponseEntity<ApiResponse<Object>> patchAStockById(@PathVariable("stockId") Long sid, @RequestBody Stock newStock) {
        try {
            stockService.patchStockById(sid, newStock);
            return ResponseEntity.ok(ApiResponse.success("Stock patched successfully", null));
        } catch (StockNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Stock not found with id: " + sid));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(ApiResponse.error("Failed to patch stock"));
        }
    }
}
