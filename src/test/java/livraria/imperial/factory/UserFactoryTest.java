package livraria.imperial.factory;

import livraria.imperial.user.dtos.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class UserFactoryTest {

    public static UserEntity createAValidUser() {
        return UserEntity.builder()
                .id(1)
                .name("test")
                .cpf("12345678910")
                .email("test@test.com")
                .login("test")
                .password("test")
                .address(null)
                .build();
    }

    public static UserEntity createAValidUserWithoutId() {
        return UserEntity.builder()
                .name("test")
                .cpf("12345678910")
                .email("test@test.com")
                .login("test")
                .password("test")
                .address(null)
                .build();
    }

    public static List<UserEntity> createAnUserList(Integer size) {

        List<UserEntity> userList = new ArrayList<>(size);

        IntStream.range(0, size).forEach(i -> userList.add(createAValidUser()));

        return userList;
    }
}
