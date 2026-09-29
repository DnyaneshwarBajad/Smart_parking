package parking.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
public class User {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;



    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Vehicle> vehicles = new ArrayList<>();
}
