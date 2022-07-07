package zajednicko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.repository.UserExistRepository;
import zajednicko.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserExistRepository userExistRepository;

    @Autowired
    public UserServiceImpl(UserExistRepository userExistRepository) {
        this.userExistRepository = userExistRepository;
    }

    @Override
    public Korisnik loadUserByUsername(String username) throws UsernameNotFoundException {
        return userExistRepository.findUserByEmail(username);
    }

    @Override
    public Korisnik findUserByEmail(String email) {
        return userExistRepository.findUserByEmail(email);
    }

    @Override
    public Korisnik findUserByUuid(String uuid) {
        return userExistRepository.findOne(uuid);
    }

    @Override
    public Korisnik create(String xmlString) {
        return userExistRepository.create(xmlString);
    }
}
