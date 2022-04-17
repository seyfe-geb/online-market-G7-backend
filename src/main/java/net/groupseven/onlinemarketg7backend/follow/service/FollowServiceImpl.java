package net.groupseven.onlinemarketg7backend.follow.service;

import com.online.market.follow.dto.CreateFollowerDto;
import com.online.market.follow.dto.FollowDto;
import com.online.market.follow.model.Follow;
import com.online.market.follow.repository.FollowRepository;
import com.online.market.user.model.User;
import com.online.market.util.service.LoggedInUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final FollowRepository repository;
    private final ModelMapper modelMapper;
    private final LoggedInUserService loggedInUserService;

    @Override
    public List<FollowDto> findAll() {
        return repository.findAll().stream()
                .map(f -> modelMapper.map(f, FollowDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FollowDto findById(long id) {
        return modelMapper.map(repository.findById(id).orElse(null), FollowDto.class);
    }

    @Override
    public FollowDto add(CreateFollowerDto dto) {
        Follow follow = modelMapper.map(dto, Follow.class);

        User follower = new User();
        follower.setId(loggedInUserService.getUserId());
        follow.setFollower(follower);

        return modelMapper.map(repository.save(follow), FollowDto.class);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void unfollow(long followerId, long followeeId) throws ValidationException {
        if (loggedInUserService.getUserId() != followerId)
            throw new ValidationException("This item you are trying to delte does not belong to you");

        Follow follow = repository.findByFollowerIdAndFolloweeId(followerId, followeeId).get();
        repository.deleteById(follow.getId());
    }
}
