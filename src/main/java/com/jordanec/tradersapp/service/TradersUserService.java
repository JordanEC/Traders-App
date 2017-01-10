package com.jordanec.tradersapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.jordanec.tradersapp.model.*;

public interface TradersUserService extends UserDetailsService {
    public AuthenticatedUser registerNewUserAccount(TradersUser accountDto) throws EmailExistsException;
}
