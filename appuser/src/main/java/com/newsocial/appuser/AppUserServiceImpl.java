package com.newsocial.appuser;

import com.newsocial.appuser.exceptions.AppUserNotFoundException;
import com.newsocial.appuser.exceptions.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository userRepo;
    private final RoleRepository roleRepo;

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving AppUser with username={}", user.getUsername());
        return userRepo.save(user);
    }

    @Override
    public AppUser getUser(String username) throws AppUserNotFoundException {
        log.info("Fetching AppUser with username={}", username);
        return userRepo
                .findByUsername(username)
                .orElseThrow(
                        () -> new AppUserNotFoundException(
                                String.format("Could not find AppUser with username=%s", username)
                        )
                );
    }

    @Override
    public AppUser getUser(Long id) throws AppUserNotFoundException {
        log.info("Fetching AppUser with id={}", id);
        return userRepo
                .findById(id)
                .orElseThrow(
                        () -> new AppUserNotFoundException(String.format("Could not find AppUser with id=%d", id))
                );
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving Role with name={}", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public Role getRole(Long id) throws RoleNotFoundException {
        log.info("Fetching Role with id={}", id);
        return roleRepo
                .findById(id)
                .orElseThrow(
                        () -> new RoleNotFoundException(String.format("Could not find Role with id=%d", id))
                );
    }

    @Override
    public Role getRole(String name) throws RoleNotFoundException {
        log.info("Fetching Role with name={}", name);
        return roleRepo
                .findByName(name)
                .orElseThrow(
                        () -> new RoleNotFoundException(String.format("Could not find Role with name=%s", name))
                );
    }
}
