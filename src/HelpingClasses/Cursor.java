package HelpingClasses;

import java.awt.*;
import java.net.URL;

public class Cursor {
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static void Idle(Window window)
    {
        Image image = toolkit.getImage("src\\Resource\\Images\\IdleCursor.png");
        java.awt.Cursor c = toolkit.createCustomCursor(image , new Point(window.getX(), window.getY()), "IdleCursor");
        window.setCursor(c);
    }
    public static void Select(Window window)
    {
        Image image = toolkit.getImage("src\\Resource\\Images\\SelectCursor.png");
        java.awt.Cursor c = toolkit.createCustomCursor(image , new Point(window.getX(), window.getY()), "SelectCursor");
        window.setCursor(c);
    }
    public static void Loading(Window window)
    {
        Image image = toolkit.getImage("src\\Resource\\Images\\LoadingCursor.png");
        java.awt.Cursor c = toolkit.createCustomCursor(image , new Point(window.getX(), window.getY()), "LoadingCursor");
        window.setCursor(c);
    }
    public static void Get(Window window)
    {
        Image image = toolkit.getImage("src\\Resource\\Images\\GetCursor.png");
        java.awt.Cursor c = toolkit.createCustomCursor(image , new Point(window.getX(), window.getY()), "GetCursor");
        window.setCursor(c);
    }
    public static void Got(Window window)
    {
        Image image = toolkit.getImage("src\\Resource\\Images\\GotCursor.png");
        java.awt.Cursor c = toolkit.createCustomCursor(image , new Point(window.getX(), window.getY()), "GotCursor");
        window.setCursor(c);
    }
}
