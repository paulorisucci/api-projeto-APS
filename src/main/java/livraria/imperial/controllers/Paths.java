package livraria.imperial.controllers;

public class Paths {

    public enum UserPaths {

        USERS("/users"),
        ID("/{idUser}");

        private String path;

        UserPaths(String path) {
            this.path = path;
        }

        public String getPath() {
            return this.path;
        }
    }
}
