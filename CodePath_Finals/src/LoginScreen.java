import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginScreen extends JFrame {

    // These are variables that are attached to the .form file
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton startButton;
    private JLabel errorLabel;

    // Colors
    private static final Color BTN_NORMAL = new Color(0x10, 0xB9, 0x81); // emerald green
    private static final Color BTN_HOVER  = new Color(0x06, 0x9E, 0x6C); // darker on hover
    private static final Color BTN_CLICK  = new Color(0x04, 0x7A, 0x52); // darkest on click

    public LoginScreen() {
        setupWindow();
        setupListeners();
    }

    private void setupWindow() {
        setTitle("CodePath");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 540);
        setLocationRelativeTo(null);
        setResizable(false);
        add(mainPanel);
    }

    private void setupListeners() {

        // Hover and click color effects on the Start button
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(BTN_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(BTN_NORMAL);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                startButton.setBackground(BTN_CLICK);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                startButton.setBackground(BTN_HOVER);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStart();
            }
        });

        // Allow pressing Enter to submit
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleStart();
                }
            }
        });
    }

    private void handleStart() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            errorLabel.setText("Please enter your name to continue.");
            nameField.requestFocus();
            return;
        }

        errorLabel.setText(" ");
        openQuizScreen(name);
    }

    private void openQuizScreen(String name) {
        QuizScreen quiz = new QuizScreen(name);
        quiz.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }
}
