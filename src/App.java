import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String url = null;

        if(args.length > 0) {
            url = args[0];
        } else {
            System.out.println("Enter an URL to start the crawler");

            Scanner scanner = new Scanner(System.in);

            url = scanner.nextLine();

            scanner.close();
        }

        try {
            long timeInit = System.currentTimeMillis();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();

            HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String[] fileName = url.split("//");

            FileWriter writer = new FileWriter(fileName[1]+".txt");
            writer.append(httpResponse.body().toString());
            writer.close();

            long timeFinished = System.currentTimeMillis();
            System.out.println("Finished");
            System.out.println("It took " + (timeFinished - timeInit) + "ms");
        } catch (IOException exception) {
            System.out.println("IOException: " + exception.getMessage());
        }
        catch (Exception exception) {
            System.out.println("Generic Exception: " + exception.getMessage());
        }
    }
}
