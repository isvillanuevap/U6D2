package mapasapi;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author sebavip
 */


public class Main {


    public static void main(String[] args) {

        List<User> usuarios = getAllUser();
        for (User aux: usuarios) {
            System.out.println(aux.getName() + " - " + aux.getEmail());
        }

        System.out.println("\n");

        List<Album> albums = getAllAlbums();
        for (Album aux: albums) {
            if (aux.getUserId()==5) {
                System.out.println(aux.getId() + " - " + aux.getUserId() + " - " + aux.getTitle());
            }
        }

        System.out.println("\n");


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

        System.out.println("Titulos");    // FALTA EL SORT
        List<Photo> photos = getAllPhoto();
        String parteTitulo = "ipsam do";
        for (Photo aux: photos){
            if(aux.getTitle().contains(parteTitulo)){
                System.out.println(aux.getTitle());
            }
        }

        System.out.println("\n");

        //En base a los usuarios obtenidos. Se deben imprimir 2 usuarios y sus respectivos albums.
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



  
