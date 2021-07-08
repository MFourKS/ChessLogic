package projectFile;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import static projectFile.DevDrawLib.*;

public class Controller extends DevDrawLib{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonInput;

    @FXML
    private TextField textInput;

    public int count = 0;
    FXMLLoader loader = new FXMLLoader();
    Stage stage = new Stage();

    public Byte n;
    GridPane gridpane = new GridPane();

    Button back = new Button("Назад");
    @FXML
    void initialize() {
        System.gc();
        buttonInput.setOnAction(actionEvent -> {
            //-----------------------------------------------------------------------------------------
            n = new Byte(textInput.getText());
            System.out.println(n);
            Coordinate[] coordinate = new Coordinate[n*n+1];
            for (int j = 0; j < n*n+1; j++)
            coordinate[j] = new Coordinate(0,0);
            //
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < i; j++) {
//                    coordinate[i] = new Coordinate(i, j);
////                    if (coordinate[j] == coordinate[j]) ;
//                    if (!IsStrike(coordinate[i].getX(),coordinate[i].getY(),coordinate[j].getX(),coordinate[j].getY()))
//                    gridpane.add(new ImageView(image3), i, j);
//                }
//            }



//            for (int i = 0; i < n; i++) {
//                Strike(coordinate, focus);
//            }
//------------------------------------------------------------------------------------------
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    gridpane.add(new ImageView(image), i, j);
                }
            }

            for (int j = 0; j < n; j+=2) {
                for (int i = 0; i < n; i+=2) {
                    gridpane.add(new ImageView(image2), i, j);
                    if ((i>0) && (j>0))
                        gridpane.add(new ImageView(image2), i-1, j-1);
                }
            }


            gridpane.add(new ImageView(image3), 0, 0);
            int s = 1;

            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    coordinate[s] = new Coordinate(j,i);
                    for (int k = 0; k < n*n+1; k++)
                        if (IsStrike(coordinate[s].getX(), coordinate[s].getY(), coordinate[k].getX(), coordinate[k].getY()) && (s!=k)) {
                            coordinate[s] = new Coordinate(0, 0);
                        }

//                    else {
//                //        gridpane.add(new ImageView(image3), coordinate[s].getX(),coordinate[s].getY());
//                        System.out.printf(" 0 ");
//                    }
                    s++;
                }
                System.out.printf("\n");
            }
            int value = 1;
            for (int i=0; i<n*n+1; i++){
                gridpane.add(new ImageView(image3), coordinate[i].getX(),coordinate[i].getY());
                if ((coordinate[i].getX() + coordinate[i].getY())>0) value++;
                System.out.println(coordinate[i].getX() +" "+ coordinate[i].getY());
            }

            Label answer;
            if (value > 0)
            answer = new Label("Удалось расроложить\n" + value +" ферзей\n из " + n);
            else answer = new Label("Решения не найдены");

            gridpane.add(answer, 0, n+1);
            gridpane.add(back, n-1, n+1);
//            Scene scn = new Scene(gridpane, 100 * n, 100 * n);
            //Stage primaryStage = null;
            buttonInput.getScene().getWindow().hide();
            stage.setScene(new Scene(gridpane, 100 * (n), 100 * (n+1)));
            stage.show();
        });
        back.setOnAction(actionEvent -> {
            openNewFrame("sample.fxml", back);
        });
    }

    boolean IsStrike(int x1, int y1, int x2, int y2)
    {
        // 1. Горизонталь, вертикаль
        if ((x1 == x2) || (y1 == y2)) return true;

        // 2. Главная диагональ
        int tx, ty;

        // 2.1. Влево-вверх
        tx = x1 - 1; ty = y1 - 1;
        while ((tx >= 0) && (ty >= 0))
        {
            if ((tx == x2) && (ty == y2)) return true;
            tx--; ty--;
        }

        // 2.2. Вправо-вниз
        tx = x1 + 1; ty = y1 + 1;
        while ((tx <= n) && (ty <= n))
        {
            if ((tx == x2) && (ty == y2)) return true;
            tx++; ty++;
        }

        // 3. Дополнительная диагональ
        // 3.1. Вправо-вверх
        tx = x1 + 1; ty = y1 - 1;
        while ((tx <= n) && (ty >= 0))
        {
            if ((tx == x2) && (ty == y2)) return true;
            tx++; ty--;
        }

        // 3.2. Влево-вниз
        tx = x1 - 1; ty = y1 + 1;
        while ((tx >= 0) && (ty <= n))
        {
            if ((tx == x2) && (ty == y2)) return true;
            tx--; ty++;
        }
        return false;
    }
//    boolean Strike(int M[], int p)
//    {
//        int px, py, x, y;
//        int i;
//
//        px = M[p];
//        py = p;
//
//        for (i = 1; i <= p - 1; i++)
//        {
//            x = M[i];
//            y = i;
//            if (IsStrike(x, y, px, py))
//                gridpane.add(new ImageView(image3), 0, 0);;
//        }
//        return false;
//    }
    protected void openNewFrame (String window, Node button){

        button.getScene().getWindow().hide();

        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        count++;
        stage.show();
    }
//    public void mouseMoved(MouseEvent event){
//
//    }
//    public void paint(GraphicsContext g)
//    {
//        g.drawImage(0, 0,
//                dm.width - 1, dm.height - 1);
//
//        g.drawImage(imFloppy,
//                imX + dx, imY + dy, this);
//
//        rcImage = new Rectangle(imX + dx, imY + dy,
//                imHeight, imWidth);
//    }
}
