package pl.pwr.movierental.model.api.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type;
    private int userId;
    private String username;
    private String password;
    private String email;
    private List roles;

    public JwtResponse(String jwt, int id, String username, String email, List<String> roles) {
        this.token = jwt;
        this.type = "Basic";
        this.userId = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
