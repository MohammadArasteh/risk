package HelpingClasses;

import java.util.Random;

public class CustomMethods {
    private static final Random random = new Random();
    public static int random(int min ,int max) {
        return random.nextInt(max) + min;
    }
}
