package livraria.imperial.controllers;

public class Paths {

    public enum UserPaths {

        USER("/user"),
        ID("/{idUser}");

        private String path;

        UserPaths(String path) {
            this.path = path;
        }
    }
}
