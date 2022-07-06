package livraria.imperial.address;

import livraria.imperial.address.dto.AddressEntity;
import livraria.imperial.customRepositories.CustomRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends CustomRepository<AddressEntity, Integer> {

    @Query(value = "SELECT CASE WHEN COUNT(A) = 1 THEN TRUE ELSE FALSE END " +
            "FROM AddressEntity A " +
            "WHERE A.country = :address.country " +
            "AND A.state = :address.state " +
            "AND A.city = :address.city " +
            "AND A.district = :address.district " +
            "AND A.cep = :address.cep " +
            "AND A.number = :address.number " +
            "AND A.complement = :address.complement " +
            "AND A.id <> :address.id", nativeQuery = true)
    boolean addressAlreadyExists(AddressEntity address);
}
