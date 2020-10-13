package beertech.becks.api.controller;

import beertech.becks.api.entities.Transaction;
import beertech.becks.api.service.TransactionService;
import beertech.becks.api.share.DTO.TransactionRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static beertech.becks.api.constants.Constants.Messages.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/transactions")
@Api(value = "Bank Becks Service")
public class TransactionController {

  @Autowired private TransactionService transactionService;

  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = STATUS_201_CREATED),
        @ApiResponse(code = 400, message = STATUS_400_BAD_REQUEST),
        @ApiResponse(code = 500, message = STATUS_500_INTERNAL_SERVER_ERROR)
      })
  @PostMapping
  public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequestDTO transactionDTO) {
    return new ResponseEntity<>(transactionService.createTransaction(transactionDTO), CREATED);
  }

}
