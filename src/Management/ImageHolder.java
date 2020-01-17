package Management;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class ImageHolder {
    private static int amountOfSchemes = 0;
    private static ArrayList<Image> generatedSchemes = new ArrayList<>();
    private static ArrayList<Background> generatedSchemesBackgrounds = new ArrayList<>();

    public static void addImage(Image image){
        generatedSchemes.add(image);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(
                BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
        generatedSchemesBackgrounds.add(new Background(backgroundImage));
        amountOfSchemes++;
    }

    public static Background getBackground(int index){
        return generatedSchemesBackgrounds.get(index);
    }

    public static int getGeneratedSchemesAmount(){
        return amountOfSchemes;
    }
}

