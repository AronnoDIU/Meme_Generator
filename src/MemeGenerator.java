import javax.imageio.ImageIO; // For reading and writing images
import java.awt.*; // For drawing text on the image and other graphics operations
import java.awt.image.BufferedImage; // For representing the image in memory
import java.io.File; // For representing the file in memory
import java.io.IOException; // For handling errors
import java.util.Scanner; // For reading user input

public class MemeGenerator {

    public static void main(String[] args) {

        try (Scanner userInput = new Scanner(System.in)) {
            System.out.println("Welcome to the Meme Generator Console App!");
            // Load the image from the file path provided by the user
            System.out.print("Enter the path of the image file: ");
            // Example: "C:/Users/username/Desktop/image.png" (Windows)
            // C:\Users\aronn\IdeaProjects\zzzLinksFolder\image.png
            // or "/Users/username/Desktop/image.png" (Mac)
            // or "C:\\Users\\username\\Desktop\\image.png" (Windows)
            // or "/Users/username/Desktop/image.png" (Mac)
            // or "image.png" (if the image is in the same directory as the program)
            // or "src/image.png" (if the image is in the src directory of the program)
            // or "resources/image.png" (if the image is in the resources directory of the program)

            String imagePath = userInput.nextLine();
            BufferedImage image = loadImage(imagePath); // Load the image

            // Get user input for top and bottom text
            System.out.print("Enter top text: ");
            String topText = userInput.nextLine();

            System.out.print("Enter bottom text: ");
            String bottomText = userInput.nextLine();

            // Generate the meme image with the top and bottom text
            BufferedImage meme = generateMeme(image, topText, bottomText);

            // Display the generated meme
            displayMeme();

            // Save the meme to a file path provided by the user
            System.out.print("Enter the path to save the generated meme: ");
            String outputFilePath = userInput.nextLine();
            saveMeme(meme, outputFilePath); // Save the meme

            System.out.println("Meme generated and saved successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Helper methods for loading, generating, displaying and saving the meme
    private static BufferedImage loadImage(String filePath) throws IOException {
        return ImageIO.read(new File(filePath)); // Read the image from the file path
    }

    // This method generates the meme by drawing the top and bottom text on the image
    private static BufferedImage generateMeme(BufferedImage originalImage, String topText, String bottomText) {
        BufferedImage meme = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = meme.createGraphics(); // Get the graphics object to draw on the image
        g.drawImage(originalImage, 0, 0, null); // Draw the original image on the meme

        // Draw the top and bottom text on the meme
        Font font = new Font("Impact", Font.BOLD, 30);
        g.setFont(font); // Set the font to Impact, Bold, 30pt
        g.setColor(Color.WHITE); // Set the color to white

        // Draw the text in the center of the image with a 10px padding
        drawText(g, topText, meme.getWidth(), 30, true);
        drawText(g, bottomText, meme.getWidth(), meme.getHeight() - 10, false);

        g.dispose(); // Release any resources held by the graphics context

        return meme; // Return the generated meme
    }

    // Helper method for drawing text on the image in the center with a 10px padding
    private static void drawText(Graphics2D g, String text, int width, int y, boolean isTop) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Enable antialiasing for text to make it look smoother on the image

        // Get the font metrics to calculate the width of the text and the position to draw it
        FontMetrics metrics = g.getFontMetrics();

        // Calculate the X coordinate to draw the text at the center of the image
        // with a 10px padding on the left and right sides of the text area
        int x = (width - metrics.stringWidth(text)) / 2;
        if (isTop) {
            g.drawString(text, x, y); // Draw the text at the center of the image
        } else {
            g.drawString(text, x, y - metrics.getHeight()); // Draw the text at the center of the image
        }
    }

    private static void displayMeme() {
        System.out.println("Displaying the generated meme...");
        // (Optional) You can use a library like JNativeHook to display the image in an external viewer.
        // For example, you can use the following code to display the image in the default image viewer:
        // Desktop.getDesktop().open(new File("meme.png"));

        System.out.println("Meme displayed successfully!");

        System.out.println("Thank you for using the Meme Generator Console App!");
        System.out.println("Goodbye!");
        System.exit(0); // Exit the program
    }

    // Helper method for saving the meme to a file path provided by the user
    private static void saveMeme(BufferedImage meme, String outputPath) throws IOException {
        File output = new File(outputPath); // Create a file object from the file path
        ImageIO.write(meme, "png", output); // Write the meme to the file
        System.out.println("Meme saved to: " + output.getAbsolutePath());
        // Print the absolute path of the file to the console
    }
}
