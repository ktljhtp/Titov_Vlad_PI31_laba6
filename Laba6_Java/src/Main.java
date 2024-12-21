import java.util.ArrayList;
import java.util.Scanner;

class Content {
    private String title;
    private String artist;
    private float duration;
    private String format;

    public void set(String title, String artist, float duration, String format) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.format = format;
    }

    public void print() {
        System.out.println("Track: " + title + " by " + artist + " (Duration: " + duration + " sec, Format: " + format + ")");
    }

    // Геттеры
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public float getDuration() {
        return duration;
    }

    public String getFormat() {
        return format;
    }
}


class PodcastContent extends Content {
    private String host;        // Ведущий подкаста
    private int episodeCount;   // Количество эпизодов
    private String description; // Краткое описание

    public PodcastContent(String title, String artist, float duration, String format,
                          String host, int episodeCount, String description) {
        set(title, artist, duration, format); // Используем метод set из базового класса
        this.host = host;
        this.episodeCount = episodeCount;
        this.description = description;
    }

    public String getInfo() {
        return "Podcast: " + getTitle() + " by " + getArtist() +
                " (Duration: " + getDuration() + " sec, Format: " + getFormat() + ")" +
                "\nHost: " + host +
                "\nEpisodes: " + episodeCount +
                "\nDescription: " + description;
    }

    @Override
    public void print() {
        System.out.println(getInfo());
    }
}

// Базовый класс
class MediaContent {
    protected String title;
    protected float duration;

    public MediaContent(String title, float duration) {
        this.title = title;
        this.duration = duration;
    }

    // Метод для вывода информации
    public void printInfo() {
        System.out.println("Media Content: " + title + " (Duration: " + duration + " sec)");
    }
}

// Производный класс
class VideoContent extends MediaContent {
    private String resolution; // Разрешение видео (например, 1080p)

    // Конструктор производного класса
    public VideoContent(String title, float duration, String resolution) {
        super(title, duration); // Вызов конструктора базового класса
        this.resolution = resolution;
        System.out.println("Конструктор VideoContent вызван.");
    }

    // Метод для вывода информации
    public void printInfo() {
        System.out.println("Video: " + title + " (Duration: " + duration + " sec, Resolution: " + resolution + ")");
    }
}

// Производный класс MusicContent
class MusicContent extends MediaContent {
    private String artist;

    public MusicContent(String title, float duration, String artist) {
        super(title, duration); // Вызов конструктора базового класса
        this.artist = artist;
    }

    // Переопределение метода с вызовом метода базового класса
    @Override
    public void printInfo() {
        super.printInfo(); // Вызов метода базового класса
        System.out.println("Artist: " + artist);
    }
}

class AudioSettings {
    private int volume;
    private int balance;

    public void set(int volume, int balance) {
        this.volume = volume;
        this.balance = balance;
    }

    public void print() {
        System.out.println("Громкость: " + volume + "\nБаланс: " + balance);
    }
}

class Device {
    private static String deviceType = "Audio Device";
    private String deviceName;
    private int maxVolume;
    private int currentVolume;

    public void set(String deviceName, int maxVolume, int currentVolume) {
        this.deviceName = deviceName;
        this.maxVolume = maxVolume;
        this.currentVolume = currentVolume;
    }

    public void print() {
        System.out.println("Название устройства: " + deviceName + ", Максимальная громкость: " + maxVolume + ", Текущая громкость: " + currentVolume);
    }

    public static void printDeviceType() {
        System.out.println("Тип устройства: " + deviceType);
    }
}

class PlaylistSettings {
    private boolean shuffle;
    private boolean repeat;

    public void inputSettings(Scanner scanner) {
        System.out.print("Введите режим случайного воспроизведения (1 - включено, 0 - выключено): ");
        shuffle = scanner.nextInt() == 1;
        System.out.print("Введите режим повторного воспроизведения (1 - включено, 0 - выключено): ");
        repeat = scanner.nextInt() == 1;
    }

    public void print() {
        System.out.println("Случ воспроизведение: " + (shuffle ? "включено" : "выключено") + "\nПовторное воспроизв: " + (repeat ? "включено" : "выключено"));
    }
}

class Playlist {
    private String name;
    private PlaylistSettings settings = new PlaylistSettings();
    private ArrayList<Content> tracks = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void inputSettings(Scanner scanner) {
        settings.inputSettings(scanner);
    }

    public void addTracks(Scanner scanner) {
        System.out.print("Введите количество треков для добавления в плейлист: ");
        int count = scanner.nextInt();
        tracks.clear();

        for (int i = 0; i < count; i++) {
            System.out.print("Введите название трека: ");
            String title = scanner.next();
            System.out.print("Введите исполнителя: ");
            String artist = scanner.next();
            System.out.print("Продолжительность в секундах: ");
            float duration = scanner.nextFloat();
            System.out.print("Введите формат трека: ");
            String format = scanner.next();
            Content track = new Content();
            track.set(title, artist, duration, format);
            tracks.add(track);
        }
    }

    public void printPlaylistInfo() {
        System.out.println("Плейлист: " + name);
        for (int i = 0; i < tracks.size(); i++) {
            System.out.print("Трек " + (i + 1) + ": ");
            tracks.get(i).print();
        }
    }

    public void printSettings() {
        settings.print();
    }
}

class User {
    private String username;
    private AudioSettings audioSettings = new AudioSettings();
    private Device device = new Device();
    private ArrayList<Playlist> playlists = new ArrayList<>();

    public void fillUserData(Scanner scanner) {
        System.out.print("Введите имя пользователя: ");
        username = scanner.next();

        System.out.print("Введите уровень громкости (0 - 100): ");
        int volume = scanner.nextInt();
        System.out.print("Введите баланс (-50 до +50): ");
        int balance = scanner.nextInt();
        audioSettings.set(volume, balance);

        System.out.print("Введите название устройства: ");
        String deviceName = scanner.next();
        System.out.print("Введите максимальную громкость устройства: ");
        int maxVolume = scanner.nextInt();
        System.out.print("Введите текущую громкость устройства: ");
        int currentVolume = scanner.nextInt();
        device.set(deviceName, maxVolume, currentVolume);
    }

    public void addPlaylist(Scanner scanner) {
        Playlist playlist = new Playlist();
        System.out.print("Введите название плейлиста: ");
        String name = scanner.next();
        playlist.setName(name);
        playlist.addTracks(scanner);
        playlist.inputSettings(scanner);
        playlists.add(playlist);
    }

    public void printUserInfo() {
        System.out.println("Имя пользователя: " + username);
        audioSettings.print();
        device.print();

        System.out.println("\nПлейлисты:");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println("Плейлист " + (i + 1) + ":");
            playlists.get(i).printPlaylistInfo();
            playlists.get(i).printSettings();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Device.printDeviceType();

        System.out.print("Введите количество пользователей: ");
        int numUsers = scanner.nextInt();
        User[] usersArray = new User[numUsers];

        for (int i = 0; i < numUsers; i++) {
            usersArray[i] = new User();
            System.out.println("\nВведите данные пользователя " + (i + 1) + ":");
            usersArray[i].fillUserData(scanner);

            char addMore;
            do {
                usersArray[i].addPlaylist(scanner);
                System.out.print("Хотите добавить еще один плейлист? (y/n): ");
                addMore = scanner.next().charAt(0);
            } while (addMore == 'y' || addMore == 'Y');
        }

        // Демонстрация использования PodcastContent
        System.out.println("\n=== Демонстрация класса PodcastContent ===");
        System.out.print("Введите название подкаста: ");
        String podcastTitle = scanner.next();
        System.out.print("Введите ведущего подкаста: ");
        String podcastHost = scanner.next();
        System.out.print("Введите количество эпизодов: ");
        int podcastEpisodes = scanner.nextInt();
        System.out.print("Введите продолжительность подкаста в секундах: ");
        float podcastDuration = scanner.nextFloat();
        System.out.print("Введите формат подкаста: ");
        String podcastFormat = scanner.next();
        System.out.print("Введите описание подкаста: ");
        String podcastDescription = scanner.next();

        PodcastContent podcast = new PodcastContent(
                podcastTitle, "Подкаст", podcastDuration, podcastFormat,
                podcastHost, podcastEpisodes, podcastDescription
        );

        System.out.println("\nИнформация о подкасте:");
        podcast.print();

        System.out.println("\nИнформация о пользователях:");
        for (User user : usersArray) {
            user.printUserInfo();
        }

        scanner.close();

        MediaContent media = new MediaContent("Generic Media", 120);
        MusicContent music = new MusicContent("Imagine", 183, "John Lennon");

        // Демонстрация вызова методов
        System.out.println("=== Media ===");
        media.printInfo();

        System.out.println("\n=== Music ===");
        music.printInfo(); // Вызов метода с использованием базового метода

        podcast.print();

        VideoContent video = new VideoContent("Nature Documentary", 1800, "1080p");
        video.printInfo();
    }
}
