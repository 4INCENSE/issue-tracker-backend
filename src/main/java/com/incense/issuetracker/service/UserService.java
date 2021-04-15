package com.incense.issuetracker.service;

import com.incense.issuetracker.domain.user.AuthenticationType;
import com.incense.issuetracker.domain.user.User;
import com.incense.issuetracker.domain.user.UserRepository;
import com.incense.issuetracker.security.oauth.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User saveOrUpdate(OAuthAttributes attributes, String registrationId) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity(AuthenticationType.getType(registrationId)));

        return userRepository.save(user);

    }
}
