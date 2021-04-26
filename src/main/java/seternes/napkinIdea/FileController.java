package seternes.napkinIdea;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

// FileController klassen behandler alt om åpning og lagring av filer gjennom FileChooser
public class FileController {

    private CanvasContainer canvasContainer;
    private FileChooser fileChooser = new FileChooser();

    public FileController(CanvasContainer c) {
        this.canvasContainer = c;
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
    }

    public EventHandler < ActionEvent > getNewFileActionHandler() {
        return this.newFileActionHandler;
    }

    public EventHandler < ActionEvent > getOpenFileActionHandler() {
        return this.openFileActionHandler;
    }

    public EventHandler < ActionEvent > getSaveFileActionHandler() {
        return this.saveFileActionHandler;
    }

    public EventHandler < ActionEvent > getCloseActionHandler() {
        return this.closeFileActionHandler;
    }

    private EventHandler < ActionEvent > newFileActionHandler = new EventHandler < ActionEvent > () {
        @Override
        public void handle(ActionEvent event) {
            try {
                FXMLLoader loader = App.loadFXML("openApp");

                Stage stage = new Stage();

                stage.setScene(new Scene(loader.load(), 640, 480));

                stage.show();

                Stage ownStage = (Stage) canvasContainer.getScene().getWindow();
                ownStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler < ActionEvent > openFileActionHandler = new EventHandler < ActionEvent > () {
        @Override
        public void handle(ActionEvent event) {
            fileChooser.setTitle("Open file");

            File f = fileChooser.showOpenDialog(canvasContainer.getScene().getWindow());
            try {
                openFile(f);
                event.consume();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler < ActionEvent > saveFileActionHandler = new EventHandler < ActionEvent > () {
        @Override
        public void handle(ActionEvent event) {
            fileChooser.setTitle("Save file");

            File file = fileChooser.showSaveDialog(canvasContainer.getScene().getWindow());

            if (file != null) {
                try {
                    saveFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    private EventHandler < ActionEvent > closeFileActionHandler = new EventHandler < ActionEvent > () {
        @Override
        public void handle(ActionEvent event) {
            System.exit(0);
        }
    };

    public void openFile(File f) throws FileNotFoundException {
        FileInputStream stream;
        try {
            stream = new FileInputStream(f);
        } catch (Exception e) {
            throw new FileNotFoundException();
        }

        Image openImage = new Image(stream);

        if (openImage != null) {
            double w = openImage.getWidth();
            double h = openImage.getHeight();

            canvasContainer.setCanvasSize(w, h);
            canvasContainer.getGC().drawImage(openImage, 0, 0, w, h);

            canvasContainer.getToolController().setImage(openImage);
            canvasContainer.getToolController().drawImage();
        }
    }

    public void saveFile(File f) throws IOException {
        if(!f.exists()) throw new FileNotFoundException();
        WritableImage image = canvasContainer.getCanvas().snapshot(new SnapshotParameters(), null);
        BufferedImage renderedImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(renderedImage, "png", f);
    }
}