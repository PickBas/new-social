package com.newsocial.appuser;

import com.newsocial.appuser.exceptions.AppUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AppUserController {
    private final AppUserService userService;

    @GetMapping("{username}")
    public ResponseEntity<AppUser> getUserByUsername(@PathVariable(name="username") String username) {
        try {
            AppUser user = userService.getUser(username);
            return ResponseEntity.ok().body(user);
        } catch (AppUserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
