package projectFile;

import javafx.scene.image.Image;

public class DevDrawLib {
    public static class Coordinate {
        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }
    }
//-------------------------------------------------------------

    public Image image = new Image("projectFile/image/lightFloat.png", 100, 100, false, true);
    public Image image2 = new Image("projectFile/image/darkFloat.png", 100, 100, false, true);
    public Image image3 = new Image("projectFile/image/chess.png", 100, 100, false, true);
}