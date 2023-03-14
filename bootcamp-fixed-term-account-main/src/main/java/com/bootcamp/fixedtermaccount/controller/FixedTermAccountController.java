package com.bootcamp.fixedtermaccount.controller;

import com.bootcamp.fixedtermaccount.exceptions.GeneralException;
import com.bootcamp.fixedtermaccount.model.FixedTermAccount;
import com.bootcamp.fixedtermaccount.model.dto.GlobalResponse;
import com.bootcamp.fixedtermaccount.service.FixedTermAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@Slf4j
public class FixedTermAccountController {

    @Autowired
    private FixedTermAccountService fixedTermAccountService;


    @GetMapping("/findAll")
    public List<FixedTermAccount> findAll() {
        log.info("All fixed-term accounts were consulted");

        return fixedTermAccountService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<FixedTermAccount> findById(@PathVariable("id") String id) {
        log.info("Fixed term account consulted by id " + id);
        return fixedTermAccountService.findById(id);

    }

    @PostMapping("/save")
    public ResponseEntity<GlobalResponse> save(@RequestBody FixedTermAccount fixedTermAccount) {
        log.info("A fixed term account was created");
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(GlobalResponse.builder()
                            .data(fixedTermAccountService.save(fixedTermAccount)
                                    .get()).message("Registrado con exito")
                            .build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GlobalResponse.builder()
                            .data(GeneralException.builder()
                                    .message(e.getMessage())
                                    .build())
                            .build());
        }
    }


}
