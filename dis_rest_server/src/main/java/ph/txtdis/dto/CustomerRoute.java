package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Location;
import ph.txtdis.domain.Route;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRoute {

    private long id;

    private String name;

    private String street;

    private Location barangay;

    private Location city;

    private Location province;

    private Route route;
}
