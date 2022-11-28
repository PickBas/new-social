package com.newsocial.appuser;

import com.newsocial.appuser.exceptions.AppUserNotFoundException;
import com.newsocial.appuser.exceptions.RoleNotFoundException;

public interface AppUserService {
    AppUser saveUser(AppUser user);
    AppUser getUser(String username) throws AppUserNotFoundException;
    AppUser getUser(Long id) throws AppUserNotFoundException;
    Role saveRole(Role role);
    Role getRole(Long id) throws RoleNotFoundException;
    Role getRole(String name) throws RoleNotFoundException;
}
