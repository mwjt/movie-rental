package pl.pwr.movierental.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.model.entity.PersonalData;
import pl.pwr.movierental.repository.PersonalDataRepository;
import pl.pwr.movierental.service.JwtUserDetailsService;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImplementation implements JwtUserDetailsService {

    private final PersonalDataRepository personalDataRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PersonalData> personalData = personalDataRepository.findByLogin(username);
        if (personalData.isPresent()) {
            return new User(personalData.get().getLogin(), personalData.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
