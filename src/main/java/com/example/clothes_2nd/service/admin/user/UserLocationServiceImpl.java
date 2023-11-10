package com.example.clothes_2nd.service.admin.user;

import com.example.clothes_2nd.model.LocationRegion;
import com.example.clothes_2nd.repository.LocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserLocationServiceImpl implements IUserLocationService {

    @Autowired
    private LocationRegionRepository userLocationRepository;


    @Override
    public List<LocationRegion> findAll() {
        return userLocationRepository.findAll();
    }

    @Override
    public Optional<LocationRegion> findById(Long id) {

        return userLocationRepository.findById(Long.valueOf(id));
    }

    @Override
    public LocationRegion save(LocationRegion locationRegion) {
        return userLocationRepository.save(locationRegion);
    }

    @Override
    public void delete(LocationRegion locationRegion) {
    }

    @Override
    public void deleteById(Long id) {
    }
}
