package mapasapi;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author sebavip
 */


public class Main {


    public static void main(String[] args) {

        System.out.println("***EJERCICIO 9, LISTA DE NOMBRE - MAIL DE /USERS***");

        List<User> usuarios = getAllUser();
        for (User aux: usuarios) {
            System.out.println(aux.getName() + " - " + aux.getEmail());
        }

        System.out.println("\n");

        System.out.println("***EJERCICIO 10, IMPRIME LISTA DE ALBUMS DEL USER CON ID 5 DE /USERS***");

        List<Album> albums = getAllAlbums();
        for (Album aux: albums) {
            if (aux.getUserId()==5) {
                System.out.println(aux.getId() + " - " + aux.getUserId() + " - " + aux.getTitle());
            }
        }

        System.out.println("\n");

        System.out.println("***EJERCICIO 11, IMPRIME ID DE ALBUM, USERID Y TITULO DEL ALBUM QUE SE INDIQUE***");


        List<Album> albums1 = getAllAlbums();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID de Album: ");
        int ingreseId = scanner.nextInt();
        for (Album aux: albums1) {
            if (ingreseId == aux.getId()) {
                System.out.println(aux.getId() + "\n" + aux.getUserId() + "\n" + aux.getTitle());
            }
        }

        System.out.println("\n");

        System.out.println("***EJERCICIO 12, IMPRIME LISTA FILTRADA Y ORDENADA DE TITULOS DE /PHOTOS***");
        /*
         * Aquí primero se filtra la lista, se guarda en una nueva
         * */
        System.out.println("Titulos");
        List<Photo> photos = getAllPhoto();
        String parteTitulo = "ipsam do";

        List<Photo> photosFiltradas = new ArrayList<>(); //Lista nueva filtrada con la parte de título que queremos
        for (Photo aux : photos) {
            if (aux.getTitle().contains(parteTitulo)) {
                photosFiltradas.add(aux);
            }
        }

        photosFiltradas.sort((p1, p2) -> p1.getTitle().compareToIgnoreCase(p2.getTitle())); //Ordenar
        for (Photo aux : photosFiltradas) {
            System.out.println(aux.getTitle());
        }


        System.out.println("\n");

        System.out.println("***EJERICIO 13, IMPRIME 2 USUARIOS Y SUS ALBUMS***");
        List<User> users = getAllUser();
        List<Album> albums2 = getAllAlbums();
        for (User aux: users) {
                for (Album aux2 : albums2) {
                    if (aux.getId()==1 && aux2.getUserId() == 1 || aux.getId()==2 && aux2.getUserId() == 2) {
                        System.out.println("id usuario: " + aux.getId() + " - nombre de usuario: " + aux.getName()
                                + " | album id: " + aux2.getId() + " - album title: " + aux2.getTitle());
                    }
                }
        }

        System.out.println("\n");

        System.out.println("***EJERICIO 14, IMPRIME MAPAS CON LOS ALBUMS DE 3 USUARIOS***");




/* 14 */ //Map<Integer, set<String>> usersAlbum = new HashMap<>(); getAllUser().stream().forEach(user -> { Set<String> albums = new HashSet<>(); getAllAlbums().stream().filter(alb -> alb.getUserId() < 4).filter(album -> album.getUserId() .equals(user.getId())).forEach(album -> {albums.add(album.getTitle()); usersAlbum.put(user.getId(), albums);}); }); usersAlbum.forEach((k,v) -> System.out.println("album: "+k+" - titulos "+v));

}

    public static List getAllUser() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://jsonplaceholder.typicode.com").path("users");
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response respuestaPublicaciones = invocationBuilder.get();
        List<User> usuarios = respuestaPublicaciones.readEntity(new GenericType<List<User>>() {});
        return usuarios;
    }

    private static List getAllAlbums() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://jsonplaceholder.typicode.com").path("albums");
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response respuestaPublicaciones = invocationBuilder.get();
        return respuestaPublicaciones.readEntity(new GenericType<List<Album>>() {});
    }

    private static Album getAlbum(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://jsonplaceholder.typicode.com").path("albums").path(id);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response respuestaPublicaciones = invocationBuilder.get();
        return respuestaPublicaciones.readEntity(new GenericType<Album>() {
        });
    }

    private static List getAllPhoto() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://jsonplaceholder.typicode.com").path("photos");
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response respuestaPublicaciones = invocationBuilder.get();
        return respuestaPublicaciones.readEntity(new GenericType<List<Photo>>() {});
    }

}



  
