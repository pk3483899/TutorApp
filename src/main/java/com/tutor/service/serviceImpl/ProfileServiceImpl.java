package com.tutor.service.serviceImpl;


import com.tutor.entity.Profile;
import com.tutor.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Long id, Profile profileDetails) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.setName(profileDetails.getName());
            profile.setAbout(profileDetails.getAbout());
            profile.setExperience(profileDetails.getExperience());
            profile.setProjects(profileDetails.getProjects());
            profile.setSkills(profileDetails.getSkills());
            profile.setEducation(profileDetails.getEducation());
            return profileRepository.save(profile);
        }
        return null;
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}

