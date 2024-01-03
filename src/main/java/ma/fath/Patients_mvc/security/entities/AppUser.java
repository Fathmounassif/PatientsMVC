package ma.fath.Patients_mvc.security.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long userId;
    @Column(unique = true)
    @NotEmpty
    private String username;
    @NotEmpty//ne doit pas être vide
    private String password;
    @NotEmpty
    private String repassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "rolename")
    private List<AppRole> appRoles=new ArrayList<>();
}
