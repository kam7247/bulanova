package ru.egprojects.sw1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egprojects.sw1_springboot.model.PhoneConfirmation;

import java.util.Optional;

public interface PhoneConfirmationRepository extends JpaRepository<PhoneConfirmation, Long> {
    Optional<PhoneConfirmation> findByPhoneNumber(String phoneNumber);

    Optional<PhoneConfirmation> findByConfirmationCode(String code);
}
