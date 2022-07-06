package livraria.imperial.book;

import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookEntity mapRequestToEntity(BookRequest request);

}
