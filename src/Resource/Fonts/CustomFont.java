package Resource.Fonts;

import java.awt.*;
import java.util.Objects;

public class CustomFont {


    public static void init() {
        CustomFont.Sitka();
        CustomFont.Antapani();
        CustomFont.Franklin();
    }

    public static Font Sitka() {
        Font font = Font.getFont("Arial");
        GraphicsEnvironment gc;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(CustomFont.class.getClassLoader().
                            getResourceAsStream("Resource\\Fonts\\Sitka\\Sitka.ttc"))).deriveFont(20);

            gc = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gc.getLocalGraphicsEnvironment().registerFont(Font.createFont(
                    Font.TRUETYPE_FONT, Objects.requireNonNull(
                            CustomFont.class.getClassLoader().getResourceAsStream(
                                    "Resource\\Fonts\\Sitka\\Sitka.ttc"))));
        } catch (Exception Error) {
            Error.printStackTrace();
        }

        return font;
    }

    public static Font Antapani() {

        Font font = Font.getFont("Arial");
        GraphicsEnvironment gc;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(CustomFont.class.getClassLoader().
                            getResourceAsStream("Resource\\Fonts\\Antapani-ExtraBold.otf"))).deriveFont(20);

            gc = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gc.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(CustomFont.class.getClassLoader().
                            getResourceAsStream("Resource\\Fonts\\Antapani-ExtraBold.otf"))));
        } catch (Exception Error) {
            Error.printStackTrace();
        }
        return font;
    }

    public static Font Franklin() {

        Font font = Font.getFont("Arial");
        GraphicsEnvironment gc;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(CustomFont.class.getClassLoader().
                            getResourceAsStream("Resource\\Fonts\\FRAHV.TTF"))).deriveFont(20);

            gc = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gc.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(CustomFont.class.getClassLoader().
                            getResourceAsStream("Resource\\Fonts\\FRAHV.TTF"))));
        } catch (Exception Error) {
            Error.printStackTrace();
        }
        return font;
    }

}
