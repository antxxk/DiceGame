package com.example.dicegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class DiceGameApp extends Application {

    private int numDice;
    private Random random = new Random();
    private Label resultLabel;
    private Button rollButton;
    private Button continueButton;
    private Button exitButton;
    private Button resetButton;
    private Label sumLabel;
    private Label totalSumLabel;
    private int totalSum = 0;  // Zmienna do przechowywania całkowitej sumy wyników

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Label chooseLabel = new Label("Wybierz liczbę kości (3-10):");
        Spinner<Integer> diceSpinner = new Spinner<>(3, 10, 3);


        rollButton = new Button("Rzuć kośćmi");
        rollButton.setDisable(false);

        continueButton = new Button("Kontynuuj");
        continueButton.setDisable(true);

        exitButton = new Button("Zakończ grę");

        resetButton = new Button("Resetuj sumę");

        resultLabel = new Label();
        sumLabel = new Label("Suma wyników: 0");
        totalSumLabel = new Label("Całkowita suma wyników: 0"); // Etykieta do wyświetlania całkowitej sumy

        diceSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            numDice = newValue;
            rollButton.setDisable(false);
        });

        rollButton.setOnAction(e -> rollDice());
        continueButton.setOnAction(e -> continueGame());
        exitButton.setOnAction(e -> primaryStage.close());
        resetButton.setOnAction(e -> resetTotalSum());

        VBox vbox = new VBox(10, chooseLabel, diceSpinner, rollButton, resultLabel, sumLabel, totalSumLabel, continueButton, resetButton, exitButton);
        vbox.setMinWidth(300);
        vbox.setMinHeight(300);
        vbox.setBackground(Background.fill(Color.LIGHTCYAN));

        Scene scene = new Scene(vbox);
        primaryStage.setTitle("Gra w kości");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void rollDice() {
        int[] diceCounts = new int[7];
        int sum = 0;
        StringBuilder resultText = new StringBuilder("Wyniki rzutów: ");


        for (int i = 0; i < numDice; i++) {
            int roll = random.nextInt(6) + 1; // Wartość od 1 do 6
            diceCounts[roll]++;
            resultText.append(roll).append(" ");
        }

        for (int i = 1; i <= 6; i++) {
            if (diceCounts[i] > 1) {
                sum += i * diceCounts[i];
            }
        }

        // Aktualizowanie sumy wyników w tej rundzie
        sumLabel.setText("Suma wyników: " + sum);

        // Dodanie sumy wyników z tej rundy do całkowitej sumy
        totalSum += sum;
        totalSumLabel.setText("Całkowita suma wyników: " + totalSum);

        resultLabel.setText(resultText.toString());
        rollButton.setDisable(true);
        continueButton.setDisable(false);
    }

    private void continueGame() {
        resultLabel.setText("");
        sumLabel.setText("Suma wyników: 0");
        continueButton.setDisable(true);
        rollButton.setDisable(false);
    }

    private void resetTotalSum() {
        totalSum = 0; // Resetowanie całkowitej sumy
        totalSumLabel.setText("Całkowita suma wyników: 0"); // Aktualizowanie etykiety
    }
}
