package ro.ubb.client.ui;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.common.exceptions.RepositoryException;
import ro.ubb.common.exceptions.ServiceException;
import ro.ubb.common.model.*;
import ro.ubb.common.service.*;
import ro.ubb.common.utils.UserRoles;
import ro.ubb.common.utils.UserStatuses;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
@org.springframework.stereotype.Service
public class TextUi {

    @Autowired
    private UserService userService;
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private BandService bandService;
    static Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean inMenu = true;

        while (inMenu) {
            printMenu();

            String fullCommand;
            List<String> commandParts;

            do {
                System.out.print("Enter valid command: ");
                fullCommand = scanner.nextLine();

                commandParts = List.of(fullCommand.split(" "));
            } while (commandParts.isEmpty() || commandParts.size() > 2);

            String menu = commandParts.get(0).toLowerCase();
            String subMenu = commandParts.size() == 2 ? commandParts.get(1).toLowerCase() : "";

            switch (menu) {
                case "user" -> {
                    switch (subMenu) {
                        case "create" -> createUser();
                        case "read" -> readOneUser();
                        case "read-all" -> readAllUsers();
                        case "filter-status" -> filterStatus();
                        case "filter-role" -> filterRole();
                        case "update" -> updateUser();
                        case "delete" -> deleteUser();
                        default -> System.out.println("Invalid operation. Please Try again.");
                    }
                }
                case "song" -> {
                    switch (subMenu) {
                        case "create" -> addSong();
                        case "read" -> selectOneSong();
                        case "read-all" -> selectAllSongs();
                        case "update" -> updateSong();
                        case "delete" -> deleteSong();
                        case "sortbylength" -> sortByLength();
                        case "getsongbytitle" -> findSongByTitle();
                        case "groupbyalbum" -> groupSongByAlbum();
                        default -> System.out.println("Invalid operation. Please Try again.");
                    }
                }
                case "album" -> {
                    switch (subMenu) {
                        case "create" -> createAlbum();
                        case "read" -> readOneAlbum();
                        case "read-all" -> readAllAlbums();
                        case "update" -> updateAlbum();
                        case "delete" -> deleteAlbum();
                        case "filter-g" -> readAlbumsByGenre();
                        case "sort" -> readAlbumsSorted();
                        default -> System.out.println("Invalid operation. Please try again.");
                    }
                }
                case "artist" -> {
                    switch (subMenu) {
                        case "create" -> createArtist();
                        case "read" -> readOneArtist();
                        case "read-all" -> readAllArtists();
                        case "update" -> updateArtist();
                        case "delete" -> deleteArtist();
                        case "sort-actitity-start-date" -> sortByActivityStartDate();
                        case "alphabetical-sort-first-name" -> alfSortByFirstName();
                        default -> System.out.println("Invalid operation. Please Try again.");
                    }
                }
                case "band" -> {
                    switch (subMenu) {
                        case "create" -> createBand();
                        case "read" -> readOneBand();
                        case "read-all" -> readAllBands();
                        case "update" -> updateBand();
                        case "delete" -> deleteBand();
                        case "sortare-alfabetica" -> sortareAlfabetica();
                        case "activitate-inceputa-intre" -> activitateInceputaIntre();
                        default -> System.out.println("Invalid operation. Please try again.");
                    }
                }

                case "exit" -> inMenu = false;
                default -> System.out.println("Invalid entity. Please try again.");
            }
        }
    }

    private void groupSongByAlbum() {
        try {
            System.out.println("Enter album id:");
            Integer albumId = Integer.parseInt(scanner.nextLine());
            List<Song> songsByAlbumId = songService.groupSongsByAlbumId(albumId);
            songsByAlbumId.forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Service exception:" + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository exception:" + e.getMessage());
        }
    }

    private void findSongByTitle() {
        try {
            System.out.println("Give song title");
            String songTitle = scanner.nextLine();
            System.out.println(songService.findByTitle(songTitle));
        } catch (ServiceException e) {
            System.out.println("Service exception: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository: " + e.getMessage());
        }
    }

    private void sortByLength() {
        List<Song> songs = songService.sortByLength();
        songs.forEach(System.out::println);
    }

    private void activitateInceputaIntre() {
        System.out.println("Data1: ");
        Date data1 = Date.valueOf(scanner.nextLine());
        System.out.println("Data2: ");
        Date data2 = Date.valueOf(scanner.nextLine());
        bandService.activitateInceputaIntre(data1, data2).forEach(System.out::println);
    }

    private void sortareAlfabetica() {
        bandService.sortareAlfabetica().forEach(System.out::println);
    }

    private void sortByActivityStartDate() {
        artistService.sortArtistsByStartDateActivity().forEach(System.out::println);
    }

    private void alfSortByFirstName() {
        artistService.alphabeticalSortByFirstName().forEach(System.out::println);
    }

    private void createUser() {
        try {
            User user = readUserInformation();
            userService.create(user);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private void readOneUser() {
        try {
            Long id = readId("User id: ");
            User user = userService.readOne(id);
            System.out.println(user);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private void readAllUsers() {
        userService.readAll().forEach(System.out::println);
    }

    private void filterStatus() {
        System.out.print("Enter the status you would like to filter by [active, inactive]: ");
        String inputStatus = scanner.nextLine();
        UserStatuses status = UserStatuses.of(inputStatus);
        userService.readAll(status).forEach(System.out::println);
    }

    private void filterRole() {
        System.out.print("Enter the role you would like to filter by [user, admin]: ");
        String inputRole = scanner.nextLine();
        UserRoles role = UserRoles.of(inputRole);
        userService.readAll(role).forEach(System.out::println);
    }

    private void updateUser() {
        try {
            User user = readUserInformation();
            userService.update(user);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private void deleteUser() {
        try {
            Long id = readId("User id: ");
            userService.delete(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private static User readUserInformation() {
        Long id = readId("User id: ");
        System.out.print("First Name: ");
        final String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Role: ");
        String inputRole = scanner.nextLine();
        System.out.print("Status: ");
        String inputStatus = scanner.nextLine();

        return new User(id, firstName, lastName, email, password, UserRoles.of(inputRole), UserStatuses.of(inputStatus));
    }

    private void createAlbum() {
        try {
            Album album = getAlbumInformation();
            albumService.create(album);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readOneAlbum() {
        try {
            Long idAlbum = readId("Album id: ");
            Album response = albumService.readOne(idAlbum);
            System.out.println(response);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readAllAlbums() {
        List<Album> response = albumService.readAll();
        response.forEach(x -> System.out.println(x));
    }

    private void updateAlbum() {
        try {
            Album album = getAlbumInformation();
            albumService.update(album);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteAlbum() {
        try {
            Long idAlbum = readId("Album id: ");
            albumService.delete(idAlbum);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private Album getAlbumInformation() {
        Long id = readId("Album id: ");
        System.out.println("Album Title:");
        String titleAlbum = scanner.nextLine();
        System.out.println("Album Genre:");
        String genreAlbum = scanner.nextLine();
        System.out.println("Artist id:");
        Long artistIdScan = Long.valueOf(scanner.nextLine());
        Long artistId = artistIdScan == 0 ? null : artistIdScan;
        System.out.println("Band id:");
        Long bandIdScan = Long.valueOf(scanner.nextLine());
        Long bandId = bandIdScan == 0 ? null : bandIdScan;
        System.out.println("Date of release: ");
        Date releaseDate = Date.valueOf(scanner.nextLine());

        return new Album(id, titleAlbum, genreAlbum, artistId, bandId, releaseDate);
    }

    private void readAlbumsByGenre() {
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.println("");
        System.out.println("The albums sorted by genre are: ");
        albumService.filterByGenre(genre).forEach(x -> System.out.println(x));
    }

    private void readAlbumsSorted() {
        System.out.print("Type of desired sorting (A|Z): ");
        String typeOfSort = scanner.nextLine().toLowerCase();
        String titleMessage = typeOfSort.equals("a") ? "Albums sorted ASC: " : "Albums sorted DESC: ";
        System.out.println();
        System.out.println(titleMessage);
        albumService.sortAsc(typeOfSort).forEach(x -> System.out.println(x));
    }

    private void readAllBands() {
        bandService.readAll().forEach(System.out::println);
    }

    private void readOneBand() {
        try {
            Long id = readId("Band id: ");
            Band response = bandService.readOne(id);
            System.out.println(response);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteBand() {
        try {
            Long id = readId("Band id: ");
            bandService.delete(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private void updateBand() {
        try {
            Band band = getBandInformation();
            bandService.update(band);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private Band getBandInformation() {
        Long id = readId("Band id: ");
        System.out.println("Band Name:");
        String nameBand = scanner.nextLine();
        System.out.println("Start of activity date: ");
        Date activityStartDate = Date.valueOf(scanner.nextLine());
        System.out.println("End of activity date: ");
        Date activityEndDate = Date.valueOf(scanner.nextLine());

        return new Band(id, nameBand, activityStartDate, activityEndDate);
    }

    private void createBand() {
        try {
            Band band = getBandInformation();
            bandService.create(band);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private void createArtist() {
        try {
            Artist artist = readArtistInformation();
            artistService.create(artist);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private void readOneArtist() {
        try {
            Long id = readId("Artist id: ");
            artistService.readOne(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private void readAllArtists() {
        artistService.readAll().forEach(System.out::println);
    }

    private void updateArtist() {
        try {
            Artist artist = readArtistInformation();
            artistService.update(artist);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private void deleteArtist() {
        try {
            Long id = readId("Artist id: ");
            artistService.delete(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository error: " + e.getMessage());
        }
    }

    private static Artist readArtistInformation() {
        Long id = readId("User id: ");
        System.out.print("First Name: ");
        String first_Name = scanner.nextLine();
        System.out.print("Last Name: ");
        String last_Name = scanner.nextLine();
        System.out.print("Stage Name: ");
        String stage_name = scanner.nextLine();
        System.out.print("Activity start date: ");
        String dateStart = scanner.nextLine();
        Date activity_start_date = Date.valueOf(dateStart);
        System.out.print("Activity end date: ");
        String dateEnd = scanner.nextLine();
        Date activity_end_date = Date.valueOf(dateEnd);

        return new Artist(id, first_Name, last_Name, stage_name, activity_start_date, activity_end_date);
    }

    private void selectAllSongs() {
        List<Song> songs = songService.readAll();
        songs.forEach(System.out::println);
    }

    private void selectOneSong() {
        try {
            System.out.println("Song id: ");
            Long id = Long.parseLong(scanner.nextLine());

            Song song = songService.readOne(id);
            System.out.println(song);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service exception:" + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository exception:" + e.getMessage());
        }

    }

    private void deleteSong() {
        try {
            System.out.println("Song id: ");
            Long id = Long.parseLong(scanner.nextLine());
            songService.delete(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service exception: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository exception:" + e.getMessage());
        }
    }

    private void updateSong() {
        try {
            Song song = getSongInformation();
            songService.update(song);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private Song getSongInformation() {
        Long id = readId("Song id: ");
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Album id: ");
        Integer albumId = Integer.parseInt(scanner.nextLine());
        System.out.println("Song length (h:m:s): ");
        String timeString = scanner.nextLine();
        Time time = Time.valueOf(timeString);

        return new Song(id, title, albumId, time);
    }

    private void addSong() {
        try {
            Song song = getSongInformation();
            songService.create(song);
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect argument formats.");
        } catch (ServiceException e) {
            System.out.println("Service exception:" + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("Repository exception:" + e.getMessage());
        }
    }

    private static Long readId(String s) {
        System.out.print(s);
        return Long.parseLong(scanner.nextLine());
    }

    private void printEntitiesOnMultipleLines(String entitiesOnSingleLine) {
        String cleanedEntitiesOnSingleLine = entitiesOnSingleLine.replaceAll("[\\[\\]]", "");
        String cleanedEntitiesOnMultipleLines = cleanedEntitiesOnSingleLine.replace("}, ", "}\n");

        System.out.println(cleanedEntitiesOnMultipleLines);
    }

    private static void printMenu() {
        System.out.print("""
                            
                       AVAILABLE COMMANDS
                --------------------------------
                user [create,read,read-all,filter-status,filter-role,update,delete]
                song [create,read,read-all,update,delete,sortbylength,groupbyalbum,getsongbytitle]
                album [create,read,read-all,update,delete,sort,filter-g]
                artist [create,read,read-all,update,delete,sort-actitity-start-date,alphabetical-sort-first-name]
                band [create,read,read-all,update,delete,sortare-alfabetica,activitate-inceputa-intre]
                exit
                --------------------------------
                         
                """);
    }
}
