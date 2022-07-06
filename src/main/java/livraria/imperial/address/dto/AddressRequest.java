package livraria.imperial.address.dto;

import livraria.imperial.country.dtos.CountryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressRequest implements Serializable {


    @Serial
    private static final long serialVersionUID = -5657572566748384220L;

    @NotNull(message = "{address.country.notnull}")
    private CountryEntity country;

    @NotBlank(message = "{address.state.notblank}")
    @Size(max=80, message = "{address.state.size}")
    private String state;

    @NotBlank(message = "{address.city.notblank}")
    @Size(max=80, message = "{address.city.size}")
    private String city;

    @NotBlank(message = "{address.district.notblank}")
    @Size(max=80, message = "{address.district.size}")
    private String district;

    @NotBlank(message = "{address.cep.notblank}")
    @Size(min=8, max=8, message = "{address.cep.size}")
    private String cep;

    @NotBlank(message = "{address.number.notblank}")
    @Size(max=5, message = "{address.number.size}")
    private String number;

    private String complement;
}
