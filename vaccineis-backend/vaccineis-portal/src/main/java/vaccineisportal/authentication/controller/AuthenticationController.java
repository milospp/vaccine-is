package vaccineisportal.authentication.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vaccineisportal.authentication.dto.AuthenticationRequest.AuthenticationRequestDTO;
import vaccineisportal.authentication.dto.AuthenticationResponse.AuthenticationResponseDTO;
import vaccineisportal.authentication.dto.UserRegistration.UserRegistrationDTO;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.security.TokenUtils;
import zajednicko.service.UserService;
import zajednicko.support.IConverter;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/authentication")
public class AuthenticationController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    private final IConverter<UserRegistrationDTO, Korisnik> toKorisnik;

    @PermitAll
    @GetMapping(value = "/authority", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<AuthenticationResponseDTO> getAuthorizedUser()  {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Korisnik korisnik = (Korisnik) authentication.getPrincipal();

        korisnik = userService.findUserByEmail(korisnik.getEmail());
        if (korisnik == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        var retVal = new AuthenticationResponseDTO(korisnik.getId(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getEmail(), korisnik.getRola().value());
        return new ResponseEntity<>(retVal,  HttpStatus.OK);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Korisnik> register(@RequestBody String userRegistrationDTO)  {

        Korisnik korisnik = userService.create(userRegistrationDTO);
        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @PermitAll
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<AuthenticationResponseDTO> login(@Valid @RequestBody AuthenticationRequestDTO authenticationRequest)  {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getSifra()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Korisnik korisnik = (Korisnik) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(korisnik);

        var retVal = new AuthenticationResponseDTO(korisnik.getId(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getEmail(), korisnik.getRola().value(), jwt);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

}
