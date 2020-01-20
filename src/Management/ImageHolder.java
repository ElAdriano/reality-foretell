package Management;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class ImageHolder {

    private static int amountOfSchemes = 0;
    private static ArrayList<Image> generatedSchemes = new ArrayList<>();
    private static ArrayList<Background> generatedSchemesBackgrounds = new ArrayList<>();
    private static ArrayList<Double> pricesOfGeneratedSchemes = new ArrayList<>();

    public static void addImage(Image image, double price) {
        generatedSchemes.add(image);
        pricesOfGeneratedSchemes.add(price);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(
                BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
        generatedSchemesBackgrounds.add(new Background(backgroundImage));
        amountOfSchemes++;
    }

    public static Background getBackground(int index) {
        return generatedSchemesBackgrounds.get(index);
    }

    public static int getGeneratedSchemesAmount() {
        return amountOfSchemes;
    }

    public static Image getImageByIndex(int index) throws IllegalArgumentException {
        if (index < 0 || index >= generatedSchemes.size()) {
            throw new IllegalArgumentException();
        }
        return generatedSchemes.get(index);
    }

    public static double getPriceToImage(int index) {
        if (index < 0 || index >= pricesOfGeneratedSchemes.size()) {
            throw new IllegalArgumentException();
        }
        return pricesOfGeneratedSchemes.get(index);
    }

}

