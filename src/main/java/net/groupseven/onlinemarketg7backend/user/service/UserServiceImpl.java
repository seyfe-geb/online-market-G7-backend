package net.groupseven.onlinemarketg7backend.user.service;

import com.online.market.role.model.Role;
import com.online.market.role.repository.RoleRepository;
import com.online.market.shopping_cart.model.ShoppingCart;
import com.online.market.shopping_cart.repository.ShoppingCartRepository;
import com.online.market.user.dto.*;
import com.online.market.user.model.User;
import com.online.market.user.repository.UserRepository;
import com.online.market.util.service.LoggedInUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final ShoppingCartRepository cartRepository;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final LoggedInUserService loggedInUserService;

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(long id) {
        return modelMapper.map(repository.findById(id).orElse(null), UserDto.class);
    }

    @Override
    public UserProfileDto findProfile() {
        return modelMapper.map(repository.findById(loggedInUserService.getUserId()).orElse(null), UserProfileDto.class);
    }

    @Override
    @Transactional
    public UserDto add(CreateUserDto dto) {
        if (repository.findByUsername(dto.getUsername()).isPresent())
            throw new ValidationException("Username exists!");

        if (!dto.getPassword().equals(dto.getRePassword()))
            throw new ValidationException("Passwords don't match!");

        if (dto.getRoles() == null)
            dto.setRoles(new HashSet<>());

        User user = repository.save(modelMapper.map(dto, User.class));
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        if (!dto.getRoles().isEmpty()) {
            user.setAuthorities(new HashSet<>(3));

            for (String authority : dto.getRoles())
                user.getAuthorities().add(roleRepository.findByAuthority(authority));
        }

        user = repository.save(user);

        return  modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateById(long id, LightUserDto dto) {
        User user = repository.getById(id);

        if(!dto.getEmail().isEmpty())
            user.setEmail(dto.getEmail());

        if(!dto.getFname().isEmpty())
            user.setFname(dto.getFname());

        if(!dto.getLname().isEmpty())
            user.setLname(dto.getLname());

        return modelMapper.map(repository.save(user), UserDto.class);
    }

    @Override
    public void deleteById(long id) { repository.deleteById(id); }

    @Override
    public UserDto addBuyer(CreateLightUserDto dto) {
        CreateUserDto creatUser = modelMapper.map(dto, CreateUserDto.class);
        creatUser.setRoles(new HashSet(List.of(Role.BUYER)));

        UserDto user = add(creatUser);
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(modelMapper.map(user, User.class));
        cartRepository.save(cart);

        return user;
    }

    @Override
    public UserDto addSeller(CreateLightUserDto dto) {
        CreateUserDto user = modelMapper.map(dto, CreateUserDto.class);
        user.setRoles(new HashSet(List.of(Role.SELLER)));

        return add(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public List<UnApprovedSellerDto> findSellersByApproved(boolean approved) {
        return repository.findSellersByApproved(approved);
    }

    @Override
    public UserDto approveById(long id) {
        User user = repository.getById(id);
        user.setApprovedSeller(true);
        return modelMapper.map(repository.save(user), UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username - " + username + ", not found "));
    }
}
