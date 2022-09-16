import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {
	@Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {
		BorderPane borderpane = new BorderPane();
		TextField output = new TextField();
		output.setEditable(false);
		borderpane.setTop(output);
		BorderPane.setMargin(output, new Insets(10,10,0,10));
		
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		gridpane.setHgap(5.5);
		gridpane.setVgap(5.5);
		
		Button one = new Button("1"); gridpane.add(one,0,2);
		Button two = new Button("2"); gridpane.add(two,1,2);
		Button three = new Button("3"); gridpane.add(three,2,2);
		Button four = new Button("4"); gridpane.add(four,0,1);
		Button five = new Button("5"); gridpane.add(five,1,1);
		Button six = new Button("6"); gridpane.add(six,2,1);
		Button seven = new Button("7"); gridpane.add(seven,0,0);
		Button eight = new Button("8"); gridpane.add(eight,1,0);
		Button nine = new Button("9"); gridpane.add(nine,2,0);
		Button zero = new Button("0"); gridpane.add(zero,1,3);
		Button clear = new Button("C"); gridpane.add(clear,0,3);
		Button equal = new Button("="); gridpane.add(equal,2,3);
		Button divide = new Button("/"); gridpane.add(divide,3,3);
		Button multiply = new Button("*"); gridpane.add(multiply,3,2);
		Button subtract = new Button("-"); gridpane.add(subtract,3,1);
		Button add = new Button("+"); gridpane.add(add,3,0);
		
		class CalculatorButtonsHandler implements EventHandler<ActionEvent> {
			double result = 0;
			String previousOperator = " ";
			String inputString = "0";
			
			@Override
			public void handle(ActionEvent e) {
				String buttonLabel = ((Button) e.getSource()).getText();
				
				switch (buttonLabel) {
				case "0": case "1": case "2":
				case "3": case "4": case "5":
				case "6": case "7": case "8":
				case "9": 
					if(inputString == "0") inputString = buttonLabel; //handle single digit
					else inputString += buttonLabel; //handle multiple digit 
					output.setText(inputString);
					if(previousOperator == "=") { 
						result = 0;
						previousOperator = " "; //clear variable for new computation
					}
				
				case "+": case "-": 
				case "*": case "/": 
				case "=":
					compute();
					previousOperator = buttonLabel;
				
				case "C": 
					result = 0;
					inputString = "0";
					previousOperator = " ";
					output.setText("0");
				}
			}
			
			public void compute() {
				int inputNum = Integer.parseInt(inputString);
				inputString = "0";
				
				if (previousOperator == "+")        result += inputNum;
				else if (previousOperator == "-")   result -= inputNum;
				else if (previousOperator == "*")   result *= inputNum;
				else if (previousOperator == "/")   result /= inputNum;
				else if (previousOperator == " ")   result = inputNum;
				
				output.setText(Double.toString(result));
			}
		}
		
		CalculatorButtonsHandler handler = new CalculatorButtonsHandler();
		one.setOnAction(handler);
		two.setOnAction(handler);
		three.setOnAction(handler);
		four.setOnAction(handler);
		five.setOnAction(handler);
		six.setOnAction(handler);
		seven.setOnAction(handler);
		eight.setOnAction(handler);
		nine.setOnAction(handler);
		zero.setOnAction(handler);
		clear.setOnAction(handler);
		equal.setOnAction(handler);
		divide.setOnAction(handler);
		multiply.setOnAction(handler);
		subtract.setOnAction(handler);
		add.setOnAction(handler);
	    
		Scene scene = new Scene(new VBox(borderpane, gridpane));
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
