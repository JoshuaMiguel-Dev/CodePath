import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class QuizScreen extends JFrame {

    //Variables names attached to .form
    private JPanel mainPanel;
    private JLabel greetingLabel;
    private JLabel counterLabel;
    private JLabel questionLabel;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;
    private JButton btnD;
    private JProgressBar progressBar;

    private String userName;
    private int currentIndex = 0;
    private Map<String, Integer> scores = new HashMap<>();

    // ── Option 3 Emerald button colors
    private static final Color BTN_NORMAL = new Color(0x13, 0x2B, 0x23);
    private static final Color BTN_HOVER  = new Color(0x10, 0xB9, 0x81);
    private static final Color BTN_CLICK  = new Color(0x06, 0x9E, 0x6C);

    // ── 15 Questions — 6 career paths
    // Balanced: each career gets exactly 10 answer slots out of 60 total
    // Format: { question, A, B, C, D }
    private String[][] questions = {
        {
            "What kind of work excites you the most?",
            "A)   Building websites and web apps",
            "B)   Creating mobile apps for Android or iOS",
            "C)   Protecting systems from hackers",
            "D)   Analyzing data to find insights"
        },
        {
            "Which project sounds the most fun to build?",
            "A)   A personal portfolio website",
            "B)   A 2D platformer video game",
            "C)   A fitness tracking mobile app",
            "D)   A tool that detects network intrusions"
        },
        {
            "What topic interests you the most?",
            "A)   HTML, CSS, and JavaScript",
            "B)   Algorithms and system design",
            "C)   Game engines like Unity or Unreal",
            "D)   Statistics and machine learning"
        },
        {
            "Which of these skills would you most like to learn?",
            "A)   React or Vue.js for web development",
            "B)   Ethical hacking and penetration testing",
            "C)   Kotlin or Swift for mobile apps",
            "D)   C# or C++ for game development"
        },
        {
            "Where do you see yourself working one day?",
            "A)   A cybersecurity firm or government agency",
            "B)   A game studio creating the next big game",
            "C)   A research lab working on AI and data",
            "D)   A tech company building web products"
        },
        {
            "Which of these describes you best?",
            "A)   I love making things look great on screen",
            "B)   I enjoy solving complex logic problems",
            "C)   I want to keep systems and data safe",
            "D)   I want my app in everyone's pocket"
        },
        {
            "What type of problem do you enjoy solving?",
            "A)   Making websites faster and easier to use",
            "B)   Finding patterns hidden in large datasets",
            "C)   Designing how a game world feels and plays",
            "D)   Building reliable software used by many people"
        },
        {
            "Which of these sounds most interesting to you?",
            "A)   How mobile apps work behind the scenes",
            "B)   How hackers break into systems",
            "C)   How browsers render web pages",
            "D)   How game physics and collision work"
        },
        {
            "What kind of project would you build for fun?",
            "A)   A dashboard showing live data trends",
            "B)   A password manager or security scanner",
            "C)   An interactive website with animations",
            "D)   A large-scale back-end software system"
        },
        {
            "Which of these appeals to you the most?",
            "A)   Publishing an app on the Play Store",
            "B)   Teaching a computer to recognize images",
            "C)   Designing levels and game mechanics",
            "D)   Writing clean, efficient, and scalable code"
        },
        {
            "How do you prefer to work on problems?",
            "A)   By experimenting with design and layout",
            "B)   By thinking like an attacker to find weak spots",
            "C)   By prototyping and testing on a real device",
            "D)   By analyzing numbers and running experiments"
        },
        {
            "Which of these topics would you enjoy studying?",
            "A)   Network security and encryption",
            "B)   Game design and interactive storytelling",
            "C)   Data visualization and analytics",
            "D)   System architecture and design patterns"
        },
        {
            "What excites you about technology?",
            "A)   Creating experiences people enjoy playing",
            "B)   Making information accessible through the web",
            "C)   Helping people through useful mobile tools",
            "D)   Uncovering insights hidden in raw data"
        },
        {
            "Which kind of project would you rather spend a week building?",
            "A)   A reliable back-end service for lots of users",
            "B)   A polished website with a great user experience",
            "C)   A security tool that finds and fixes vulnerabilities",
            "D)   A mobile app with features people use every day"
        },
        {
            "Which of these also fits your personality?",
            "A)   I enjoy creative and visual problem solving",
            "B)   I like understanding how things break and why",
            "C)   I am drawn to math, logic, and patterns",
            "D)   I love the idea of building a game people enjoy"
        }
    };

    // Maps A=0, B=1, C=2, D=3 to a career for each question
    // Each career gets exactly 10 points available across 15 questions
    // WD=Web Dev, MA=Mobile App, CY=Cybersecurity, DS=Data Science, GD=Game Dev, SE=Software Eng
    private String[][] answerMap = {
        { "Web Development",       "Mobile App Development", "Cybersecurity",        "Data Science"          }, // Q1
        { "Web Development",       "Game Development",       "Mobile App Development","Cybersecurity"         }, // Q2
        { "Web Development",       "Software Engineering",   "Game Development",     "Data Science"          }, // Q3
        { "Web Development",       "Cybersecurity",          "Mobile App Development","Game Development"      }, // Q4
        { "Cybersecurity",         "Game Development",       "Data Science",         "Web Development"       }, // Q5
        { "Web Development",       "Software Engineering",   "Cybersecurity",        "Mobile App Development"}, // Q6
        { "Web Development",       "Data Science",           "Game Development",     "Software Engineering"  }, // Q7
        { "Mobile App Development","Cybersecurity",          "Web Development",      "Game Development"      }, // Q8
        { "Data Science",          "Cybersecurity",          "Web Development",      "Software Engineering"  }, // Q9
        { "Mobile App Development","Data Science",           "Game Development",     "Software Engineering"  }, // Q10
        { "Web Development",       "Cybersecurity",          "Mobile App Development","Data Science"          }, // Q11
        { "Cybersecurity",         "Game Development",       "Data Science",         "Software Engineering"  }, // Q12
        { "Game Development",      "Web Development",        "Mobile App Development","Data Science"          }, // Q13
        { "Software Engineering",  "Web Development",        "Cybersecurity",        "Mobile App Development"}, // Q14
        { "Web Development",       "Cybersecurity",          "Data Science",         "Game Development"      }  // Q15
    };

    public QuizScreen(String userName) {
        this.userName = userName;
        initScores();
        setupWindow();
        setupHoverEffects();
        setupListeners();
        loadQuestion(0);
    }

    private void initScores() {
        scores.put("Web Development",        0);
        scores.put("Mobile App Development", 0);
        scores.put("Cybersecurity",          0);
        scores.put("Data Science",           0);
        scores.put("Game Development",       0);
        scores.put("Software Engineering",   0);
    }

    private void setupWindow() {
        setTitle("CodePath — Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        add(mainPanel);
    }

    // Adds hover and click color
    private void setupHoverEffects() {
        addHover(btnA);
        addHover(btnB);
        addHover(btnC);
        addHover(btnD);
    }

    private void addHover(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(BTN_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(BTN_NORMAL);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                btn.setBackground(BTN_CLICK);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                btn.setBackground(BTN_HOVER);
            }
        });
    }

    private void setupListeners() {
        btnA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleAnswer(0); }
        });
        btnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleAnswer(1); }
        });
        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleAnswer(2); }
        });
        btnD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleAnswer(3); }
        });
    }

    // Loads question and answers
    private void loadQuestion(int index) {
        String[] q = questions[index];
        greetingLabel.setText("Hello, " + userName + "!");
        counterLabel.setText("Question " + (index + 1) + " of " + questions.length);
        questionLabel.setText(
            "<html><div style='text-align:center;'>" + q[0] + "</div></html>"
        );
        btnA.setText(q[1]);
        btnB.setText(q[2]);
        btnC.setText(q[3]);
        btnD.setText(q[4]);

        // Update progress bar
        int progress = (int) ((index / (double) questions.length) * 100);
        progressBar.setValue(progress);
    }

    private void handleAnswer(int choiceIndex) {
        String career = answerMap[currentIndex][choiceIndex];
        scores.put(career, scores.get(career) + 1);
        currentIndex++;

        if (currentIndex < questions.length) {
            loadQuestion(currentIndex);
        } else {
            progressBar.setValue(100);
            openResultScreen(getTopCareer());
        }
    }

    private String getTopCareer() {
        String top = "";
        int max = -1;
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                top = entry.getKey();
            }
        }
        return top;
    }

    private void openResultScreen(String career) {
        ResultScreen result = new ResultScreen(userName, career);
        result.setVisible(true);
        this.dispose();
    }
}
