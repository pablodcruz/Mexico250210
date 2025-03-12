package com.revature.service;

import com.revature.model.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    List<UserProfile> findAllUserProfiles();
    Optional<UserProfile> findUserProfileById(Long id);
    UserProfile createUserProfile(UserProfile profile);
    Optional<UserProfile> updateUserProfile(Long id, UserProfile profile);
    boolean deleteUserProfile(Long id);
}
