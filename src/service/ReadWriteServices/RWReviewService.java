package service.ReadWriteServices;

import main.app.AppData;
import model.Review;
import model.users.Client;
import model.users.ResOwner;
import service.UserService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class RWReviewService extends ReadWriteService{
    private static final String FILE_PATH = DIRECTORY_PATH + "reviews.csv";
    private static RWReviewService INSTANCE;

    private RWReviewService() {}

    public static RWReviewService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RWReviewService();
        }
        return INSTANCE;
    }

    public void read(AppData appData) {
        try {
            checkIfDirectoryAndFileExist(FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            UserService userService = new UserService();
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line;
            String[] reviewData;
            while ((line = reader.readLine()) != null) {
                reviewData = line.split(",");

                String clientEmail = reviewData[0],
                        restaurantOwnerEmail = reviewData[1];
                int numOfStars = Integer.parseInt(reviewData[2]);
                String message = reviewData[3];
                Date date = (new SimpleDateFormat("dd-MM-yyyy hh:mm:ss")).parse(reviewData[4]);

                Client client = (Client) userService.getUserByEmail(appData.getUsers(),clientEmail);
                ResOwner owner = (ResOwner) userService.getUserByEmail(appData.getUsers(), restaurantOwnerEmail);
                if (client == null || owner == null) continue;

                Review review = new Review.Builder()
                        .withNumOfStars(numOfStars)
                        .withMessage(message)
                        .withClient(client)
                        .withDate(date)
                        .build();
                owner.getOwnedRestaurant().getReviews().add(review);
            }
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(ResOwner resOwner, Review review) {
        try {
            checkIfDirectoryAndFileExist(FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.APPEND);
            writer.newLine();
            writer.write(review.getClient().getEmail() + "," +
                    resOwner.getEmail() + "," +
                    review.getNumOfStars() + "," +
                    review.getMessage() + "," +
                    new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(review.getDate()));
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
