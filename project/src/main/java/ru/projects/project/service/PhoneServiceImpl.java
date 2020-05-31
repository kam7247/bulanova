package ru.egprojects.sw1_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.egprojects.sw1_springboot.model.PhoneConfirmation;
import ru.egprojects.sw1_springboot.model.User;
import ru.egprojects.sw1_springboot.repository.PhoneConfirmationRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneConfirmationRepository repository;

    @Autowired
    private ExecutorService executor;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${sms.aero.email}")
    private String email;

    @Value("${sms.aero.api-key}")
    private String key;

    @Value("${sms.aero.from}")
    private String from;

    @Value("${sms.aero.type}")
    private String type;

    @Value("${sms.aero.url}")
    private String apiUrl;

    @Override
    public void prepareConfirmation(User user, String phoneNumber) {
        Optional<PhoneConfirmation> phoneConfirmationOptional = repository.findByPhoneNumber(phoneNumber);
        PhoneConfirmation phoneConfirmation;
        String code = UUID.randomUUID().toString();
        if (phoneConfirmationOptional.isPresent()) {
            phoneConfirmation = phoneConfirmationOptional.get();
            phoneConfirmation.setConfirmationCode(code);
        } else phoneConfirmation = PhoneConfirmation.builder()
                .user(user)
                .phoneNumber(phoneNumber)
                .confirmationCode(code)
                .build();

        sendCode(code, phoneNumber);
        repository.save(phoneConfirmation);
    }

    private void sendCode(String code, String phoneNumber) {
        executor.submit(() -> {
            String request = apiUrl + "?user="
                    + email + "&password="
                    + key + "&to="
                    + phoneNumber +
                    "&text=" + code
                    + "&from="
                    + from + "&type="
                    + type;
            request = request.replaceAll(" ", "%20");
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(request, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return true;
            } else throw new IllegalArgumentException("Incorrect phone number");
        });
    }

    @Override
    public boolean confirm(String code) {
        Optional<PhoneConfirmation> phoneConfirmationOptional = repository.findByConfirmationCode(code);
        phoneConfirmationOptional.ifPresent((phoneConfirmation) -> {
            phoneConfirmation.getUser()
                    .setPhoneNumber(phoneConfirmation.getPhoneNumber());
            repository.delete(phoneConfirmation);
        });

        return phoneConfirmationOptional.isPresent();
    }
}
