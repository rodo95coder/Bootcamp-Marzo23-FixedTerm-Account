package com.bootcamp.fixedtermaccount.service;


import com.bootcamp.fixedtermaccount.model.FixedTermAccount;

import java.util.List;
import java.util.Optional;

public interface FixedTermAccountService {

  List<FixedTermAccount> findAll();

  Optional<FixedTermAccount> findById(String id);

  Optional<FixedTermAccount> save(FixedTermAccount fixedTermAccount);


}
