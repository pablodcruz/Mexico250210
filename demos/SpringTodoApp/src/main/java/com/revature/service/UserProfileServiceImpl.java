package com.revature.service;

import com.revature.model.UserProfile;
import com.revature.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public List<UserProfile> findAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    @Override
    public Optional<UserProfile> findUserProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    @Override
    public UserProfile createUserProfile(UserProfile profile) {
        return userProfileRepository.save(profile);
    }

    @Override
    public Optional<UserProfile> updateUserProfile(Long id, UserProfile profileDetails) {
        return userProfileRepository.findById(id).map(existingProfile -> {
            existingProfile.setFirstName(profileDetails.getFirstName());
            existingProfile.setLastName(profileDetails.getLastName());
            // Update additional fields as necessary
            return userProfileRepository.save(existingProfile);
        });
    }

    @Override
    public boolean deleteUserProfile(Long id) {
        return userProfileRepository.findById(id).map(profile -> {
            userProfileRepository.delete(profile);
            return true;
        }).orElse(false);
    }
}

