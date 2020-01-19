package Management;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class ImageCreator {
    public static Image createImage(Fields[][] prisonPlan, int size) {
        int height = size;
        int width = size;
        BufferedImage returningImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = ImageCreator.getRGB(prisonPlan[j][i]);
                returningImage.setRGB(j, i, rgb);
            }
        }

        return SwingFXUtils.toFXImage(returningImage, null);
    }

    private static int getRGB(Fields field) {
        int returningValue = 0;
        switch (field) {
            case CAMERA:
                returningValue = 16711680;
                break;
            case DOOR:
                returningValue = 0;
                break;
            case LIGHT_BULB:
                returningValue = 16776960;
                break;
            case MONITORING_ROOM:
                returningValue = 65280; // TODO
                break;
            case PRISON_WARD:
                returningValue = 12632256; // TODO
                break;
            case SANITARY_NOOK:
                returningValue = 65280;
                break;
            case WARD:
                returningValue = 65280;
                break;
            case WINDOW:
                returningValue = 255;
                break;
            case OUTSIDE_FIELD:
                returningValue = 8421504;
                break;
            case CORRIDOR:
                returningValue = 12632256;
                break;
            case WALL:
                returningValue = 255;
                break;
            case EMPTY:
                returningValue = 0;
                break;
            case TECHNICAL_ROOM:
                returningValue = 16711680;
                break;
        }
        return returningValue;
    }
}
