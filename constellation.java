package constellation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class doubleInterface extends Application {

    private String [] dateList = {"1", "2", "3", "4",
            "5", "6", "7", "8", "9", "10", "11", "12", "13",
            "14", "15", "16", "17", "18", "19", "20", "21", "22",
            "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private String [] monthList = {"1", "2", "3", "4",
            "5", "6", "7", "8", "9", "10", "11", "12"};

    private String [] constellationList = {"水瓶座", "双鱼座", "白羊座", "金牛座",
            "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};

    private int [][] keyList = {
            {87,	60,	72,	41,		91,	58,	78,	64,		96,	51,	82,	69},
            {69,	88,	71,	78,		46,	93,	61,	65,		74,	99,	54,	82},
            {88,	79,	90,	75,		82,	47,	94,	65,		85,	70,	99,	58},
            {66,	81,	68,	88,		72,	75,	45,	97,		57,	78,	61,	93},

            {99,	48,	79,	76,		89,	71,	81,	57,		93,	69,	86,	64},
            {74,	97,	52,	82,		78,	89,	61,	84,		66,	92,	70,	87},
            {84,	62,	97,	56,		79,	69,	87,	72,		81,	45,	92,	77},
            {55,	84,	61,	91,		76,	88,	66,	89,		49,	81,	72,	95},

            {95,	64,	85,	74,		98,	58,	88,	77,		90,	71,	80,	47},
            {57,	92,	60,	80,		68,	97,	65,	84,		73,	87,	47,	76},
            {78,	44,	92,	70,		81,	65,	98,	58,		86,	68,	89,	75},
            {74,	77,	43,	97,		70,	80,	59,	92,		51,	85,	64,	88} };

    private String getConstellation = "";
    private Label keyConstellation = new Label();

    private Label finalBoyConstellation = new Label();
    private Label finalGirlConstellation = new Label();

    private Button seekConstellation = new Button("查询星座");
    private Button seekResult = new Button("开始配对");

    private ComboBox<String> cboBoyMonth = new ComboBox<String>();
    private ComboBox<String> cboBoyDay = new ComboBox<String>();
    private ComboBox<String> cboGirlMonth = new ComboBox<String>();
    private ComboBox<String> cboGirlDay = new ComboBox<String>();

    private ComboBox<String> cboBoyConstellation = new ComboBox<String>();
    private ComboBox<String> cboGirlConstellation = new ComboBox<String>();

    private ObservableList<String> items1 = FXCollections.observableArrayList(monthList);
    private ObservableList<String> items2 = FXCollections.observableArrayList(dateList);
    private ObservableList<String> items3 = FXCollections.observableArrayList(monthList);
    private ObservableList<String> items4 = FXCollections.observableArrayList(dateList);

    private ObservableList<String> items5 = FXCollections.observableArrayList(constellationList);
    private ObservableList<String> items6 = FXCollections.observableArrayList(constellationList);

    private BorderPane pane1 = new BorderPane();
    private GridPane pane2 = new GridPane();
    private VBox pane = new VBox();


    private Stage primaryStageResult = new Stage();

    private String [] constellationPictureList = {"image/shuiping.png", "image/shuangyu.png", "image/baiyang.png",
            "image/jinniu.png", "image/shuangzi.png", "image/juxie.png",
            "image/shizi.png", "image/chunv.png", "image/tianping.png",
            "image/tianxie.png",  "image/sheshou.png",  "image/mojie.png"};

    private String constellationPicture = "";
    private String constellationBoy = "";
    private String constellationGirl = "";

    private Button share = new Button("分享");
    private Button tryAgain = new Button("再次查询");

    private int times = 0;


    public void start(Stage primaryStage) throws Exception {

        pane1.setCenter(new doubleInterface.CustomPane("占星游戏"));

        Circle circle = new Circle(500, 500, 30);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Label account = new Label("用户名", circle);
        account.setContentDisplay(ContentDisplay.LEFT);
        pane1.setRight(account);

/*        Image imageAccount = new Image("");
        pane1.setRight(new ImageView(imageAccount));*/

        pane2.setAlignment(Pos.CENTER);
        pane2.setPadding(new Insets(10, 10, 10, 10));
        pane2.setHgap(8);
        pane2.setVgap(8);


        Label lblBoy = new Label("他的生日：");
        lblBoy.setFont(Font.font("Time New Roman", 20));
        pane2.add(lblBoy, 10, 10);

        cboBoyMonth.setValue("1");
        cboBoyMonth.setStyle("-fx-color-label-visible: red");
        pane2.add(cboBoyMonth, 11, 10);

        Label lblBoyBirthdayMonth = new Label("月");
        lblBoyBirthdayMonth.setFont(Font.font("Time New Roman", 20));
        pane2.add(lblBoyBirthdayMonth, 12, 10);

        cboBoyDay.setValue("1");
        cboBoyDay.setStyle("-fx-color-label-visible: red");
        pane2.add(cboBoyDay, 13, 10);

        Label lblBoyBirthdayDay = new Label("日");
        lblBoyBirthdayDay.setFont(Font.font("Time New Roman", 20));
        pane2.add(lblBoyBirthdayDay, 14, 10);


        Label lblGirl = new Label("她的生日：");
        lblGirl.setFont(Font.font("Time New Roman", 20));
        pane2.add(lblGirl, 20, 10);

        cboGirlMonth.setValue("1");
        cboGirlMonth.setStyle("-fx-color-label-visible: red");
        pane2.add(cboGirlMonth, 21, 10);

        Label lblGirlBirthdayMonth = new Label("月");
        lblGirlBirthdayMonth.setFont(Font.font("Time New Roman", 20));
        pane2.add(lblGirlBirthdayMonth, 22, 10);

        cboGirlDay.setValue("1");
        cboGirlDay.setStyle("-fx-color-label-visible: red");
        pane2.add(cboGirlDay, 23, 10);

        Label lblGirlBirthdayDay = new Label("日");
        lblGirlBirthdayDay.setFont(Font.font("Time New Roman", 20));
        pane2.add(lblGirlBirthdayDay, 24, 10);

        pane2.add(seekConstellation, 26, 10);


        cboBoyMonth.getItems().addAll(items1);
        cboBoyDay.getItems().addAll(items2);
        cboGirlMonth.getItems().addAll(items3);
        cboGirlDay.getItems().addAll(items4);

        seekConstellation.setOnAction(e -> displayConstellation(items1.indexOf(cboBoyMonth.getValue()), items2.indexOf(cboBoyDay.getValue()),
                items3.indexOf(cboGirlMonth.getValue()), items4.indexOf(cboGirlDay.getValue())));

        finalBoyConstellation.setFont(Font.font("Time New Roman", 20));
        pane2.add(finalBoyConstellation,11,18);
        finalGirlConstellation.setFont(Font.font("Time New Roman", 20));
        pane2.add(finalGirlConstellation,21,18);


        Label lblBoyConstellationTitle = new Label("他的星座：");
        lblBoyConstellationTitle.setFont(Font.font("Time New Roman", 20));
        pane2.add(lblBoyConstellationTitle, 10, 30);

        cboBoyConstellation.setValue("水瓶座");
        cboBoyConstellation.setStyle("-fx-color-label-visible: red");
        pane2.add(cboBoyConstellation, 11, 30);

        Label lblGirlConstellationTitle = new Label("她的星座：");
        lblGirlConstellationTitle.setFont(Font.font("Time New Roman", 20));
        pane2.add(lblGirlConstellationTitle, 20, 30);

        cboGirlConstellation.setValue("水瓶座");
        cboGirlConstellation.setStyle("-fx-color-label-visible: red");
        pane2.add(cboGirlConstellation, 21, 30);

        pane2.add(seekResult, 26, 30);


        cboBoyConstellation.getItems().addAll(items5);
        cboGirlConstellation.getItems().addAll(items6);

        seekResult.setOnAction(e -> {
            try {
                startResult(primaryStageResult);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().addAll(pane1, pane2);

        Scene scene = new Scene(pane, 1100, 700);
        primaryStage.setTitle("占星游戏");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    class CustomPane extends StackPane {
        public CustomPane(String title) {
            Label lbl = new Label(title);
            lbl.setFont(Font.font("Time New Roman", FontWeight.BOLD, 40));
            getChildren().add(lbl);
            setPadding(new Insets(100, 0, 10, 60));
        }
    }


    public void displayConstellation(int index1, int index2, int index3, int index4) {
        finalBoyConstellation.setText(find(index1, index2));
        finalGirlConstellation.setText(find(index3, index4));
        cboBoyConstellation.setValue(find(index1, index2));
        cboGirlConstellation.setValue(find(index3, index4));
    }

    public String find (int cboMonth, int cboDay) {
        int month = cboMonth + 1;
        int day = cboDay + 1;
        switch (month) {
            case 1:
                if ((1 <= day) && (19 >= day)) getConstellation = constellationList[11];
                else getConstellation = constellationList[0];
                break;
            case 2:
                if ((1 <= day) && (18 >= day)) getConstellation = constellationList[0];
                else getConstellation = constellationList[1];
                break;
            case 3:
                if ((1 <= day) && (20 >= day)) getConstellation = constellationList[1];
                else getConstellation = constellationList[2];
                break;
            case 4:
                if ((1 <= day) && (19 >= day)) getConstellation = constellationList[2];
                else getConstellation = constellationList[3];
                break;
            case 5:
                if ((1 <= day) && (20 >= day)) getConstellation = constellationList[3];
                else getConstellation = constellationList[4];
                break;
            case 6:
                if ((1 <= day) && (21 >= day)) getConstellation = constellationList[4];
                else getConstellation = constellationList[5];
                break;
            case 7:
                if ((1 <= day) && (22 >= day)) getConstellation = constellationList[5];
                else getConstellation = constellationList[6];
                break;
            case 8:
                if ((1 <= day) && (22 >= day)) getConstellation = constellationList[6];
                else getConstellation = constellationList[7];
                break;
            case 9:
                if ((1 <= day) && (22 >= day)) getConstellation = constellationList[7];
                else getConstellation = constellationList[8];
                break;
            case 10:
                if ((1 <= day) && (23 >= day)) getConstellation = constellationList[8];
                else getConstellation = constellationList[9];
                break;
            case 11:
                if ((1 <= day) && (22 >= day)) getConstellation = constellationList[9];
                else getConstellation = constellationList[10];
                break;
            case 12:
                if ((1 <= day) && (21 >= day)) getConstellation = constellationList[10];
                else getConstellation = constellationList[11];
                break;
        }
        return getConstellation;
    }


    public void displayKey(int index1, int index2) {
        keyConstellation.setText(findKey(index1, index2));
        keyConstellation.setFont(Font.font("Time New Roman", 20));
    }

    public String findKey (int boy, int girl) {
        return keyList[boy][girl] + "";
    }




    public void startResult(Stage primaryStageResult) throws Exception {

        constellationBoy = change(items5.indexOf(cboBoyConstellation.getValue()));
        constellationGirl = change(items6.indexOf(cboGirlConstellation.getValue()));


        BorderPane pane1 = new BorderPane();
        pane1.setCenter(new doubleResult.CustomPane("匹配结果"));

        HBox pane2 = new HBox(10);
        pane2.setPadding(new Insets(5,5,5,5));


        Image imageBoy = new Image("image/nan.jpg");
        ImageView imageViewBoy = new ImageView(imageBoy);
        imageViewBoy.setFitWidth(50);
        imageViewBoy.setFitHeight(50);
        pane2.getChildren().add(imageViewBoy);

        Image image1 = new Image(constellationBoy);
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(180);
        imageView1.setFitHeight(180);
        pane2.getChildren().add(imageView1);

        Image imageAdd = new Image("image/+.jpg");
        pane2.getChildren().add(new ImageView(imageAdd));

        Image imageGirl = new Image("image/nv.jpg");
        ImageView imageViewGirl = new ImageView(imageGirl);
        imageViewGirl.setFitWidth(50);
        imageViewGirl.setFitHeight(50);
        pane2.getChildren().add(imageViewGirl);

        Image image2 = new Image(constellationGirl);
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(180);
        imageView2.setFitHeight(180);
        pane2.getChildren().add(imageView2);

        Image imageCount = new Image("image/=.jpg");
        pane2.getChildren().add(new ImageView(imageCount));


        displayKey(items5.indexOf(cboBoyConstellation.getValue()), items6.indexOf(cboGirlConstellation.getValue()));

        keyConstellation.setFont(Font.font("Roman", 150));
        keyConstellation.setTextFill(Color.rgb(0, 255, 64));
        pane2.getChildren().add(keyConstellation);


        HBox pane3 = new HBox();
        pane3.setSpacing(10);
        pane3.setAlignment(Pos.CENTER);

        pane3.getChildren().add(share);
        pane3.getChildren().add(tryAgain);

        share.setOnAction(e -> shareDo(pane2));

        TryAgainClass eventAgain = new TryAgainClass();
        tryAgain.setOnAction(eventAgain);


        VBox pane = new VBox();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setSpacing(100);
        pane.getChildren().addAll(pane1, pane2, pane3);

        Scene scene  = new Scene(pane, 1100, 700);
        primaryStageResult.setTitle("占星游戏");
        primaryStageResult.setScene(scene);
        primaryStageResult.show();
    }


    class TryAgainClass implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Stage stage = (Stage) tryAgain.getScene().getWindow();
            stage.close();
        }
    }

    public void shareDo (HBox pane2) {
        File file = new File("src/share/share" + times++ + ".png");
        WritableImage image = pane2.snapshot(new SnapshotParameters(), null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("分享截图保存成功");
        } catch (IOException ex) {
            System.out.println("分享截图保存失败");
        }
    }


    public String change (int constellationNumber) {
        switch (constellationNumber) {
            case 0: constellationPicture = constellationPictureList[0];    break;
            case 1: constellationPicture = constellationPictureList[1];    break;
            case 2: constellationPicture = constellationPictureList[2];    break;
            case 3: constellationPicture = constellationPictureList[3];    break;
            case 4: constellationPicture = constellationPictureList[4];    break;
            case 5: constellationPicture = constellationPictureList[5];    break;
            case 6: constellationPicture = constellationPictureList[6];    break;
            case 7: constellationPicture = constellationPictureList[7];    break;
            case 8: constellationPicture = constellationPictureList[8];    break;
            case 9: constellationPicture = constellationPictureList[9];    break;
            case 10: constellationPicture = constellationPictureList[10];    break;
            case 11: constellationPicture = constellationPictureList[11];    break;
        }
        return constellationPicture;
    }
}
