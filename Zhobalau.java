package zhobalau;

import com.sun.javafx.geom.Line2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author 777
 */
public class Zhobalau extends Application {
    Line line;
    Line radius;
    Line2D li;
    boolean clicked = false;
    Line line2;
    boolean LINE_BUTTON_CLICKED = false;
    Circle circle;
    boolean CIRCLE_BUTTON_CLICKED = false;
    boolean CIRCLE_CLICKED = false;
    boolean lineCreated =false;
    boolean secondLine = false;
    boolean tik_clicked = false;
    boolean trr = false;
    ArrayList<Line> linelist = new ArrayList<>();
    ArrayList<Circle> circlelist = new ArrayList<>();
    public double Uzyndyq(double x1, double x2, double y1, double y2){
        double x3 = x2;
        double y3 = y1;
        return Math.sqrt((x3-x1)*(x3-x1) + (y3-y2)*(y3-y2));
    }
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
       TextField circleField = new TextField();
       circleField.setTranslateX(60);
       circleField.setTranslateY(660);
       circleField.setVisible(false);
//rec - тиктортбурыш
        //Button- knopka
        
        //image kartinka kosu ushin
        //Image image = new Image(new FileInputStream("C:\\Users\\777\\Documents\\NetBeansProjects\\Zhobalau\\src\\zhobalau\\123.png"));
        //imageview kartinkani korsetu ushin
        ImageView imgv = new ImageView();
        Button lineButton = new Button("↗");
        
        ColorPicker cpLine = new ColorPicker(Color.BLACK);
        ColorPicker cpFill = new ColorPicker(Color.TRANSPARENT);
        ColorPicker cpBack = new ColorPicker(Color.BROWN);
        cpBack.setTranslateX(10);
        cpBack.setTranslateY(280);
        cpBack.setMaxWidth(30);
        cpLine.setTranslateX(10);
        cpLine.setTranslateY(220);
        cpFill.setTranslateX(10);
        cpFill.setTranslateY(250);
        cpLine.setMaxWidth(30);
        cpFill.setMaxWidth(30);
            
        Slider slider = new Slider(1, 9, 1);
       
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setTranslateX(510);
        slider.setTranslateY(640);
        Button circleButton = new Button("⦸");
        lineButton.setTranslateX(10);
        lineButton.setTranslateY(150);
        Button squareButton = new Button("⦸");
        lineButton.setTranslateX(10);
        lineButton.setTranslateY(150);
        
        Button tikLine = new Button("||");
        tikLine.setTranslateX(300);
        tikLine.setTranslateY(655);
        Button clearButton = new Button("✂");
        clearButton.setTranslateX(800);
        clearButton.setTranslateY(655);
        
        // shadow men light effectter
        
        DropShadow shadow = new DropShadow();
        Lighting light = new Lighting();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        
        tikLine.setEffect(shadow);
        //setEffect nastroika effecta
        if(LINE_BUTTON_CLICKED==false){
            lineButton.setEffect(shadow);
        }
        else if(LINE_BUTTON_CLICKED == true){
            lineButton.setEffect(light);
        }
        tikLine.setOnAction((ActionEvent event) -> {
            tik_clicked = !(tik_clicked);
            
            if(tik_clicked){
                tikLine.setEffect(light);
            }else if(tik_clicked == false){
                tikLine.setEffect(shadow);
            }
       });
        lineButton.setOnAction((ActionEvent event) -> {
            LINE_BUTTON_CLICKED = !(LINE_BUTTON_CLICKED);
            
            if((LINE_BUTTON_CLICKED && CIRCLE_BUTTON_CLICKED == true) || LINE_BUTTON_CLICKED == true ){
                lineButton.setEffect(light);
                circleButton.setEffect(shadow);
                CIRCLE_BUTTON_CLICKED = false;
            }
            else if(LINE_BUTTON_CLICKED==false){
                lineButton.setEffect(shadow);
            }
       });
        circleButton.setTranslateX(10);        
        circleButton.setTranslateY(180); 
        if(CIRCLE_BUTTON_CLICKED==false){
            circleButton.setEffect(shadow);
        }
        else if(CIRCLE_BUTTON_CLICKED == true){
            circleButton.setEffect(light);
        }
        
        circleButton.setOnAction((ActionEvent event) -> {
            CIRCLE_BUTTON_CLICKED = !CIRCLE_BUTTON_CLICKED;
            if((CIRCLE_BUTTON_CLICKED && LINE_BUTTON_CLICKED == true) || CIRCLE_BUTTON_CLICKED == true ){
                circleButton.setEffect(light);
                lineButton.setEffect(shadow);
                LINE_BUTTON_CLICKED = false;
            }
            else if(CIRCLE_BUTTON_CLICKED ==false){
                circleButton.setEffect(shadow);
                CIRCLE_BUTTON_CLICKED = true;
                
            }
       });
        //imgv.setImage(image);
        imgv.setTranslateX(-10);
        imgv.setTranslateY(-10);
        imgv.setFitWidth(100);
        imgv.setFitHeight(50);
        MenuBar menubar = new MenuBar();
        Menu bir = new Menu("bir");
        Menu eki = new Menu("eki");
        Menu ush = new Menu("ush");
        
        menubar.getMenus().addAll(bir,eki,ush);
        menubar.setMinWidth(2000);
        menubar.setTranslateX(100);
        //barlik elemntter group ka zhazilu kerek
        Group root = new Group();
        
        Scene scene = new Scene(root, 2000, 1024);
        Rectangle rec = new Rectangle();
        rec.setTranslateX(50);
        rec.setTranslateY(50);
        Rectangle rec2 = new Rectangle();
        rec2.setTranslateX(0);
        rec2.setTranslateY(0);
        rec2.setFill(Color.DARKGREY);
        rec.setHeight(580);
        rec.setWidth(1260);
        rec2.setHeight(800);
        rec2.setWidth(2000);
        Rectangle rect = new Rectangle();
        rec.setFill(Color.ALICEBLUE);
//        Canvas canvas = new Canvas(1080, 700);
//        canvas.setTranslateX(50);
//        canvas.setTranslateY(50);
//        root.getChildren().add(canvas);
        scene.setOnMouseClicked((MouseEvent event) -> {
            //knopkanin basilganin tekserediD
//            rect.setX(event.getSceneX());
//            rect.setY(event.getSceneY());
//            trr = true;
//                   rec.setFill(cpBack.getValue());
            if(LINE_BUTTON_CLICKED == true){
                if(clicked == false){
                    line = new Line();
                    line.setStartX(event.getSceneX());
                    line.setStartY(event.getSceneY());
                    line.setStroke(cpLine.getValue());
                    line.setStrokeWidth(slider.getValue());
                                    circleField.clear();
//                    circleField.setVisible(true);
//                    root.getChildren().add(circleField);
//                    circleField.requestFocus();
//                    -------------------------------------
                        circleField.setVisible(true);
                        root.getChildren().add(circleField);
                        circleField.requestFocus();
//                        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
//                            @Override
//                            public void handle(KeyEvent event2) {
//                                if (event2.getCode() == KeyCode.ENTER) {
//                                    clicked = false;
//                                    double ax = line.getStartX();
//                                    double ay = line.getStartY();
//                                    double bx = event.getSceneX();
//                                    double by = event.getSceneY(); 
//                                    double x3 = bx;
//                                    double y3 = ay;
//                                    double sina =  Math.sin(Math.toRadians((Math.toDegrees(Math.atan(Math.abs(by - ay) / Math.abs(bx-ax))))));
//                                    double cosa =  Math.cos(Math.toRadians((Math.toDegrees(Math.atan(Math.abs(by - ay) / Math.abs(bx-ax))))));
//                                    
//                                    double d = Double.parseDouble(circleField.getText());
//                                    double c = sina*d;
//                                    double b = cosa*d;
//                                    line.setEndX(ax+b);
//                                    line.setEndY(y3+c);
//                                    
//                                    root.getChildren().add(line);
//                                    
//                                }
//
//                            }
//
//                        });
//---------------------------------------------------

                }
                else if(clicked == true && tik_clicked == true){
                    
                    clicked = false;
                    double ax = line.getStartX();
                    double ay = line.getStartY();
                    double bx = event.getSceneX();
                    double by = event.getSceneY();
                    scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
                            @Override
                            public void handle(KeyEvent event2) {
                                if (event2.getCode() == KeyCode.ENTER) {
                                    clicked = false;
                                    double ax = line.getStartX();
                                    double ay = line.getStartY();
                                    double bx = event.getSceneX();
                                    double by = event.getSceneY(); 
                                    double x3 = bx;
                                    double y3 = ay;
                                    double sina =  Math.sin(Math.toRadians((Math.toDegrees(Math.atan(Math.abs(by - ay) / Math.abs(bx-ax))))));
                                    double cosa =  Math.cos(Math.toRadians((Math.toDegrees(Math.atan(Math.abs(by - ay) / Math.abs(bx-ax))))));
                                    
                                    double d = Double.parseDouble(circleField.getText());
                                    double c = sina*d;
                                    double b = cosa*d;
                                    if(Math.toDegrees(Math.atan(Math.abs(by - ay)/Math.abs(bx-ax))) < 45){
                                        if(line.getEndX()>line.getStartX()){
                                            line.setEndX(ax+d);
                                            line.setEndY(line.getEndY());
                                            root.getChildren().add(line);
                                            linelist.add(line);
                                        }else if(line.getEndX()<line.getStartX()){
                                            line.setEndX(ax-d);
                                            
                                            line.setEndY(line.getEndY());
                                            root.getChildren().add(line);
                                            linelist.add(line);
                                        }
                                    }
                                    
                                    
                                    
                                    

                                }

                            }

                        });
                    if(Math.toDegrees(Math.atan(Math.abs(by - ay)/Math.abs(bx-ax))) < 45){
                        line.setEndX(bx);
                        line.setEndY(ay);
                        root.getChildren().add(line);
                                            linelist.add(line);
                    }else if(Math.toDegrees(Math.atan(Math.abs(by - ay)/Math.abs(bx-ax))) > 45){                        
                        line.setEndX(ax);
                        line.setEndY(by);
                        root.getChildren().add(line);
                                            linelist.add(line);
                    }
                    
                    
                    
                }
                else if(clicked ==true){
                    line = new Line();
                    line.setStartX(event.getSceneX());
                    line.setStartY(event.getSceneY());
                    clicked =false;
                   
                }
            }
            
            if(CIRCLE_BUTTON_CLICKED == true){
                if( CIRCLE_CLICKED == false){
                    
                    System.out.println(CIRCLE_BUTTON_CLICKED);
                    circle = new Circle(1);
                    circle.setCenterX(event.getSceneX());
                    circle.setCenterY(event.getSceneY());
                    circle.setFill(Color.TRANSPARENT);
                    circle.setStroke(Color.BLACK);
                    CIRCLE_CLICKED = true;
                    circle.setStroke(cpLine.getValue());
                    circle.setStrokeWidth(slider.getValue());
                    circle.setFill(cpFill.getValue());
                    
                    //---------------
                    radius = new Line();
                    radius.setStartX(event.getSceneX());
                    radius.setStartY(event.getSceneY());
                    circleField.setVisible(true);
                    root.getChildren().add(circleField);
                    circleField.requestFocus();
                    scene.setOnKeyPressed((KeyEvent event1) -> {
                        if (event1.getCode() == KeyCode.ENTER) {
                            CIRCLE_CLICKED = false;
                            circle.setRadius(Double.parseDouble(circleField.getText())); circleField.clear();
                            root.getChildren().add(circle);
                            circlelist.add(circle);
                        }
                    });
                }
                else if(CIRCLE_CLICKED == true){
                    
                    //-----------
                    
                    double x = event.getSceneX();
                    double y = event.getSceneY();
                    radius.setEndX(x);
                    radius.setEndY(y);
                    
                    double x1 = radius.getStartX();
                    double y1 = radius.getStartY();
                    double x2 = radius.getEndX();
                    double y2 = radius.getEndY();
                    double x3 = x2;
                    double y3 = y1;
                    double radius1 = Uzyndyq(x1, x2, y1, y2);
                    circle.setRadius(radius1);
                    CIRCLE_CLICKED = false;
                    root.getChildren().add(circle);
                    circlelist.add(circle);
                    
                    
                    
                }
            }
       });
        
        root.getChildren().addAll(rec2,rec,menubar,imgv,lineButton,circleButton, tikLine, cpFill,cpLine,slider, cpBack,clearButton);
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               
                    root.getChildren().removeAll(linelist);
                    root.getChildren().removeAll(circlelist);
           }
       });
        //Myshkanin koordinatciasi
        //handle dan buryn eki text jasadik
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if(LINE_BUTTON_CLICKED || CIRCLE_BUTTON_CLICKED){
                    lineButton.setEffect(shadow);
                    circleButton.setEffect(shadow);
                    LINE_BUTTON_CLICKED = false;
                    CIRCLE_BUTTON_CLICKED = false;
                    clicked =true;
                    
                    root.getChildren().remove(line);
                }
            }
       });
        Text tx = new Text();
        Text ty = new Text();
//        rect.setFill(Color.TRANSPARENT);
//        rect.setStroke(Color.BLACK);
        
        
        scene.setOnMouseMoved((MouseEvent event) -> {
//            if(trr){
//                if(event.getSceneX()>rect.getX() || event.getSceneY() > rect.getY()){
//                    rect.setWidth(event.getSceneX()-rect.getX());
//                    rect.setHeight(event.getSceneY()-rect.getY());
//                    root.getChildren().add(rect);
//                }else if(event.getSceneX() < rect.getX() || event.getSceneY() > rect.getY()){
//                    rect.setWidth(rect.getX() - event.getSceneX());
//                    rect.setHeight(event.getSceneY()-rect.getY());
//                    root.getChildren().add(rect);
//                }else if(event.getSceneX() < rect.getX() || event.getSceneY() < rect.getY()){
//                    rect.setWidth(rect.getX() - event.getSceneX());
//                    rect.setHeight(rect.getY()-event.getSceneY());
//                    root.getChildren().add(rect);
//                }else if(event.getSceneX() > rect.getX() || event.getSceneY() < rect.getY()){
//                    rect.setWidth(event.getSceneX() - rect.getX());
//                    rect.setHeight(rect.getY()-event.getSceneY());
//                    root.getChildren().add(rect);
//                }
//            
//            }
            if(clicked && tik_clicked == true){
                double ax = line.getStartX();
                double ay = line.getStartY();
                double bx = event.getSceneX();
                double by = event.getSceneY();
                if(Math.toDegrees(Math.atan(Math.abs(by - ay)/Math.abs(bx-ax))) < 45){
                    line.setEndX(bx);
                    line.setEndY(ay);
                    root.getChildren().add(line);
                    linelist.add(line);
                }
                else if(Math.toDegrees(Math.atan(Math.abs(by - ay)/Math.abs(bx-ax))) > 45){
                    line.setEndX(ax);
                    line.setEndY(by);
                    root.getChildren().add(line);
                    linelist.add(line);
                }
            }
            else if(clicked ==false){
                line.setEndX(event.getSceneX());
                line.setEndY(event.getSceneY());
                
                root.getChildren().add(line);
                linelist.add(line);
//                        circle.setRadius(event.getSceneX()-line.getStartX());
//                        root.getChildren().add(circle);
//                        root.getChildren().add(line2);
//                        lineCreated = true;

            }
            if( CIRCLE_CLICKED == true){
                radius.setEndX(event.getSceneX());
                radius.setEndY(event.getSceneY());
                double x1 = radius.getStartX();
                double y1 = radius.getStartY();
                double x2 = radius.getEndX();
                double y2 = radius.getEndY();
                double x3 = x2;
                double y3 = y1;
                double radius1 = Uzyndyq(x1, x2, y1, y2);
                circle.setRadius(radius1);
                
                root.getChildren().add(circle);
                circlelist.add(circle);
            }
       });
        menubar.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               
           }
            
        });
        rec.setOnMouseMoved((MouseEvent event) -> {
            //setText text ornatadi
            //evetn getSceneX, Y ol myshkanin koordinatasi
            tx.setText("X- "+(event.getSceneX()-50));
            ty.setText("Y- "+(event.getSceneY()-50));
            //coordinatalari
            tx.setTranslateX(150);
            tx.setTranslateY(600);
            ty.setTranslateX(150);
            ty.setTranslateY(620);
       });
        root.getChildren().add(tx);
        root.getChildren().add(ty);
        // ---------------------
        

        
        primaryStage.setTitle("Zhobalau");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}