package net.groupseven.onlinemarketg7backend.util.service;

import com.online.market.user.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoggedInUserService {

    public long getUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) auth.getPrincipal();
        return user.getId();
    }

    public User getLoggedInUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) auth.getPrincipal();

        return user;
    }
}
