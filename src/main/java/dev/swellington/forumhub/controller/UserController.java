package dev.swellington.forumhub.controller;

import dev.swellington.forumhub.domain.user.UserDetailDto;
import dev.swellington.forumhub.domain.user.UserRegisterDto;
import dev.swellington.forumhub.domain.user.UserService;
import dev.swellington.forumhub.domain.user.UserUpdateDto;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static dev.swellington.forumhub.controller.ResourceMapper.USER_RESOURCE;

@RestController
@RequestMapping(USER_RESOURCE)
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserDetailDto> create(@RequestBody @Valid UserRegisterDto dto, UriComponentsBuilder uriBuilder) {
        val user = userService.createUser(dto);
        return ResponseEntity.created(
                uriBuilder.path("/api/v1/users/{id}").buildAndExpand(user.getId()).toUri()
        ).body(UserDetailDto.fromUser(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserDetailDto>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable).map(UserDetailDto::fromUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailDto> show(@PathVariable Long id) {
        val user = userService.getUserById(id);
        user.getAuthorities().forEach(System.out::println);
        return ResponseEntity.ok(UserDetailDto.fromUser(user));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailDto> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDto dto) {
        return ResponseEntity.ok(UserDetailDto.fromUser(userService.updateUser(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
