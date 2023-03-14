package com.bootcamp.fixedtermaccount.service.impl;

import com.bootcamp.fixedtermaccount.model.FixedTermAccount;
import com.bootcamp.fixedtermaccount.repository.FixedTermAccountRepository;
import com.bootcamp.fixedtermaccount.service.FixedTermAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class FixedTermAccountServiceImpl implements FixedTermAccountService {
    @Autowired
    FixedTermAccountRepository fixedTermAccountRepository;

    @Override
    public List<FixedTermAccount> findAll() {
        return fixedTermAccountRepository.findAll();
    }

    @Override
    public Optional<FixedTermAccount> findById(String id) {
        return fixedTermAccountRepository.findById(id);
    }

    @Override
    public Optional<FixedTermAccount> save(FixedTermAccount fixedTermAccount) {
        //Un cliente personal solo puede tener un máximo de una cuenta de ahorro, una cuenta corriente o cuentas a plazo fijo.
        //Un cliente empresarial no puede tener una cuenta de ahorro o de plazo fijo, pero sí múltiples cuentas corrientes.

        FixedTermAccount fixedTermAccount1 = fixedTermAccountRepository.findFixedTermAccountByAccountNumberAndCustomerType(fixedTermAccount.getAccountNumber(), fixedTermAccount.getCustomerType());

        if (!ObjectUtils.isEmpty(fixedTermAccount1) && fixedTermAccount1.getCustomerType().equals("PERSONAL")) {
            throw new RuntimeException("No se puede realizar el registro, ya existe una cuenta de plazo fijo para el cliente personal!");
        } else if (fixedTermAccount.getCustomerType().equals("EMPRESARIAL") && (fixedTermAccount.getAccountType().equals("SA") || fixedTermAccount.getAccountType().equals("FTA"))) {
            throw new RuntimeException("Un cliente empresarial no puede tener una cuenta de plazo fijo o de ahorros!");
        } else {
            return Optional.of(this.fixedTermAccountRepository.save(fixedTermAccount));
        }
    }
}
