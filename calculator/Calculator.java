//
// Name: Chhura, Madhav
// Project: #2
// Due: 02/02/15
// Course: CS-245-01-w15
//
// Description:
// Integer Calculator, allows you to only perform calculation up to 10 digits,
// if number or result is over 10 digits then the Calculator will diplay an 
// Overflow-error. Calculator does not support negative signs. At anytime if 
// you Press CTRL and CLICK-CLR button combination will display
// “(c) 2015 Madhav Chhura”
//
package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**

 * @author madhavchhura
 */
public class Calculator implements ActionListener {
    
    JTextField  textField;
    JFrame frame;
    JPanel buttonPanel;
    String text = "";
    String number1 = "", oper;
    boolean operSelected = false;
    String localLabel = null;
//    private final String[]  toppings = {"7", "8", "9", "/", 
//                                        "4", "5", "6", "*",
//                                        "1", "2", "3", "-",
//                                        "0"};
    
    @SuppressWarnings("empty-statement")
    Calculator(){
    
        frame = new JFrame("Calculator");

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(("Calculator.png")));
        frame.setSize(250,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(2,1));
        
        
        textField = new JTextField ();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setBackground(Color.GRAY);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4));
        

             
       
        //Buttons Array for digits 0 ~ 9.
        JButton[] buttons = new JButton[10]; 
        
        //Mathemetical Operation Buttons.
        JButton divideButton = new JButton("/");
        JButton multiplyButton = new JButton("*");
        JButton subtractButton = new JButton("-");
        JButton equalsButton = new JButton("=");
        JButton addButton = new JButton("+");
        
        //Add action listeners to the buttons.
        OperatorAction operAction = new OperatorAction();
        divideButton.addActionListener(operAction);
        multiplyButton.addActionListener(operAction);
        subtractButton.addActionListener(operAction);
        addButton.addActionListener(operAction);
        
        //Default Clear Button.
        localLabel = "<html><a style='text-decoration:underline'>C</a></html>";
        JButton clearButton = new JButton(localLabel);
        clearButton.addActionListener(this);


        //Add text and actionListener to the Buttons 0 ~ 9.
        for(int i = 0; i <= 9; i++){
            buttons[i] = new JButton(String.valueOf(i));
            buttons[i].addActionListener(this);   
        }
        
        //Add buttons to the buttonPanel: Row - 1  Digits 7~9 & /
        for(int i = 7; i <=9; i++){
            buttonPanel.add(buttons[i]);
        }
        buttonPanel.add(divideButton);
        
        //Add buttons to the buttonPanel: Row - 2 Digits 4~6 & *
        for(int i = 4; i <=6; i++){
            buttonPanel.add(buttons[i]);
        }
        buttonPanel.add(multiplyButton);
        
        //Add buttons to the buttonPanel: Row - 3 Digits 1~3 & -
        for(int i = 1; i <=3; i++){
            buttonPanel.add(buttons[i]);
        }
        buttonPanel.add(subtractButton);
        
        //Add buttons to the buttonPanel: Row - 4 0,C,=,+ 
        buttonPanel.add(buttons[0]);
        buttonPanel.add(clearButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(addButton);
        
        //Sets equalsButton to be the default button which can also be used with
        //a enter key.
        frame.getRootPane().setDefaultButton(equalsButton);
        
        //equalsButton.setActionCommand("=");
        equalsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(number1 != null && text != null){
                    int temp = Integer.parseInt(number1);
                    int temp1 = Integer.parseInt(text);
                    operSelected = false;
                    switch (operAction.operator) {
                        case "/":
                            if(temp1 != 0){
                                int result = temp / temp1;
                                text = "" + result;
                                textField.setText("" + result);
                            }
                            else
                                textField.setText("Div by 0");
                            break;
                        case "*":
                            {
                                int result = temp * temp1;
                                text = "" + result;
                                if(text.length() > 10){
                                   textField.setText("Overflow");          
                                }
                                else 
                                   textField.setText("" + result);
                                break;
                            }
                        case "+":
                            {
                                int result = temp + temp1;
                                text = "" + result;
                                if(text.length() > 10){
                                   textField.setText("Overflow");          
                                }
                                else 
                                   textField.setText("" + result);
                                break;
                            }
                        case "-":
                            {
                                int result = temp - temp1;
                                text = "" + result;
                                textField.setText("" + result);
                                break;
                            }
                    }
                }
            }
        });
        
        //Add the text field and buttonPanel to the frame.
        frame.add(textField);
        frame.add(buttonPanel);
        
        //Set frame visibility to true.
        frame.setVisible(true);
                
    }
    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getActionCommand().equals(localLabel)){
            text = "";
            number1 = "";
            operSelected = false;
            textField.setText("");    
        }
        else if(text.length() > 9){
            textField.setText("Overflow");
            text = "";
            operSelected = false;
    
        }
        else{
            String action = event.getActionCommand();
            text =  text + action;
            textField.setText(text);     
        }
    }
    

    public class OperatorAction implements ActionListener{
        private String operator;
        
        public void OperatorAction(String operation){
            operator = operation;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(text.length() > 0 && !operSelected){
                textField.setText(text);
                number1 = text;
                text = "";
                operSelected = true;
                
                switch (e.getActionCommand()) {
                    case "/":
                        this.operator = "/";
                        break;
                    case "*":
                        this.operator = "*";
                        break;
                    case "-":
                        this.operator = "-";
                        break;
                    case "+":
                        this.operator = "+";
                        break;
                }
            }
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Calculator(); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
