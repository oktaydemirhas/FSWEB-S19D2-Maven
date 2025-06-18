package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address update(Long id, Address address) {
        Address existingAddress = findById(id);
        existingAddress.setStreet(address.getStreet());
        existingAddress.setNo(address.getNo());
        existingAddress.setCity(address.getCity());
        existingAddress.setCountry(address.getCountry());
        existingAddress.setDescription(address.getDescription());
        return addressRepository.save(existingAddress);
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
} 