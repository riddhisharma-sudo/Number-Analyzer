import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberAnalyzerApp extends JFrame {

    private JTextField inputField;
    private JButton calculateButton;
    private JTextArea resultArea;

    public NumberAnalyzerApp() {
        setTitle("Number Analyzer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        inputField = new JTextField(10);
        calculateButton = new JButton("Calculate");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        
        // Set background colors
        getContentPane().setBackground(new Color(220, 220, 240));
        resultArea.setBackground(new Color(255, 102, 178));

        // Set custom font
        Font customFont = new Font("vijaya", Font.PLAIN, 14);
        resultArea.setFont(customFont);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analyzeNumber();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(255, 102, 178)); // Background color
        panel.add(new JLabel("Enter a number: "));
        panel.add(inputField);
        panel.add(calculateButton);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void analyzeNumber() {
        try {
            int number = Integer.parseInt(inputField.getText());

            StringBuilder result = new StringBuilder();
            result.append("Number: ").append(number).append("\n");

            // Calculate Factorial
            long factorial = 1;
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }
            result.append("Factorial: ").append(factorial).append("\n");

            // Check Prime
            boolean isPrime = true;
            if (number <= 1) {
                isPrime = false;
            } else {
                for (int i = 2; i <= Math.sqrt(number); i++) {
                    if (number % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
            result.append("Prime: ").append(isPrime).append("\n");

            // Check Even/Odd
            String evenOdd = number % 2 == 0 ? "Even" : "Odd";
            result.append("Even/Odd: ").append(evenOdd).append("\n");

            // Check Armstrong
            int originalNumber = number;
            int sum = 0;
            int digits = (int) Math.log10(number) + 1;
            while (number > 0) {
                int digit = number % 10;
                sum += Math.pow(digit, digits);
                number /= 10;
            }
            boolean isArmstrong = sum == originalNumber;
            result.append("Armstrong: ").append(isArmstrong);

            resultArea.setText(result.toString());
        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberAnalyzerApp().setVisible(true);
            }
        });
    }
}
