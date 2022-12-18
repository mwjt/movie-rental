package pl.pwr.movierental.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.api.request.JwtRequest;
import pl.pwr.movierental.model.api.response.JwtResponse;
import pl.pwr.movierental.security.JwtTokenUtil;
import pl.pwr.movierental.service.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Operation(summary = "Create token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Token", content = @Content),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("User disabled", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials", e);
        }
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
