package beertech.becks.api.controller;

import beertech.becks.api.entities.CurrentAccount;
import beertech.becks.api.service.CurrentAccountService;
import beertech.becks.api.share.DTO.BalanceDTO;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static beertech.becks.api.constants.Constants.Messages.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/current-accounts")
public class CurrentAccountController {

    @Autowired
    private CurrentAccountService currentAccountService;

    @ApiResponses(
            value = {
        @ApiResponse(code = 200, message = STATUS_200_GET_OK),
        @ApiResponse(code = 404, message = STATUS_404_NOTFOUND),
        @ApiResponse(code = 500, message = STATUS_500_INTERNAL_SERVER_ERROR)
    })
    @PostMapping
    public ResponseEntity<CurrentAccount> createCurrentAccount() {
        return new ResponseEntity<>(currentAccountService.createCurrentAccount(), CREATED);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = STATUS_200_GET_OK),
                    @ApiResponse(code = 404, message = STATUS_404_NOTFOUND),
                    @ApiResponse(code = 500, message = STATUS_500_INTERNAL_SERVER_ERROR)
            })
    @GetMapping
    public ResponseEntity<BalanceDTO> getBalance(@RequestParam String hashAccount) {
        return ok(currentAccountService.getAccountBalanceByHash(hashAccount));
    }

}
