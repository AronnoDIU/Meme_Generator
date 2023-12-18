import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MemeGenerator {

    public static void main(String[] args) {

        try (Scanner userInput = new Scanner(System.in)) {
            System.out.println("Welcome to the Meme Generator Console App!");
            // Load the image
            System.out.print("Enter the path of the image file: ");
            String imagePath = userInput.nextLine();
            BufferedImage image = loadImage(imagePath);

            // Get user input for top and bottom text
            System.out.print("Enter top text: ");
            String topText = userInput.nextLine();

            System.out.print("Enter bottom text: ");
            String bottomText = userInput.nextLine();

            // Generate the meme
            BufferedImage meme = generateMeme(image, topText, bottomText);

            // Display the generated meme
            displayMeme();

            // Save the meme to a file
            System.out.print("Enter the path to save the generated meme: ");
            String outputFilePath = userInput.nextLine();
            saveMeme(meme, outputFilePath);

            System.out.println("Meme generated and saved successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static BufferedImage loadImage(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }

    private static BufferedImage generateMeme(BufferedImage originalImage, String topText, String bottomText) {
        BufferedImage meme = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = meme.createGraphics();
        g.drawImage(originalImage, 0, 0, null);

        Font font = new Font("Impact", Font.BOLD, 30);
        g.setFont(font);
        g.setColor(Color.WHITE);

        drawText(g, topText, meme.getWidth(), 30, true);
        drawText(g, bottomText, meme.getWidth(), meme.getHeight() - 10, false);

        g.dispose();

        return meme;
    }

    private static void drawText(Graphics2D g, String text, int width, int y, boolean isTop) {
        FontMetrics metrics = g.getFontMetrics();
        int x = (width - metrics.stringWidth(text)) / 2;
        if (isTop) {
            g.drawString(text, x, y);
        } else {
            g.drawString(text, x, y - metrics.getHeight());
        }
    }

    private static void displayMeme() {
        System.out.println("Displaying the generated meme...");
        // (Optional) You can use a library like JNativeHook to display the image in an external viewer.
    }

    private static void saveMeme(BufferedImage meme, String outputPath) throws IOException {
        File output = new File(outputPath);
        ImageIO.write(meme, "png", output);
        System.out.println("Meme saved to: " + output.getAbsolutePath());
    }
}
