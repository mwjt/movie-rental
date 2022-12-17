package pl.pwr.movierental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -1L;

    private final String token;
}
