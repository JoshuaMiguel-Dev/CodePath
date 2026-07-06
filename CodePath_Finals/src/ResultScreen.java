import javax.swing.*;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class ResultScreen extends JFrame {
    private JPanel mainPanel;
    private JLabel resultHeading;
    private JLabel suggestedLabel;
    private JLabel careerIcon;
    private JLabel careerTitle;
    private JLabel careerDescription;
    private JButton skillsButton;
    private JButton roadmapButton;
    private JButton retakeButton;

    private String userName;
    private String career;

    // ── Button colors for consistent theming across the app
    private static final Color PRIMARY_NORMAL = new Color(0x10, 0xB9, 0x81);
    private static final Color PRIMARY_HOVER  = new Color(0x06, 0x9E, 0x6C);
    private static final Color PRIMARY_CLICK  = new Color(0x04, 0x7A, 0x52);

    private static final Color SECONDARY_NORMAL = new Color(0x10, 0xB9, 0x81);
    private static final Color SECONDARY_HOVER  = new Color(0x06, 0x9E, 0x6C);
    private static final Color SECONDARY_CLICK  = new Color(0x04, 0x7A, 0x52);

    private static final Color GHOST_NORMAL = new Color(0x10, 0xB9, 0x81);
    private static final Color GHOST_HOVER  = new Color(0x06, 0x9E, 0x6C);
    private static final Color GHOST_CLICK  = new Color(0x0A, 0x17, 0x13);

    private static final Color DIALOG_BG = new Color(0x13, 0x2B, 0x23);
    private static final Color DIALOG_SURFACE = new Color(0x10, 0x23, 0x1D);
    private static final Color DIALOG_TEXT = new Color(0xE5, 0xE7, 0xEB);

    private static final Map<String, String> icons = new HashMap<>();
    static {
        icons.put("Web Development",        "🌐");
        icons.put("Mobile App Development", "📱");
        icons.put("Cybersecurity",          "🔒");
        icons.put("Data Science",           "📊");
        icons.put("Game Development",       "🎮");
        icons.put("Software Engineering",   "⚙️");
    }

    // ── Career descriptions
    private static final Map<String, String> descriptions = new HashMap<>();
    static {
        descriptions.put("Web Development",
            "<html><div style='text-align:center;font-family:Segoe UI;'>" +
            "You enjoy building things people interact with online.<br>" +
            "Web developers create websites and apps using HTML, CSS, and JavaScript." +
            "</div></html>");

        descriptions.put("Mobile App Development",
            "<html><div style='text-align:center;font-family:Segoe UI;'>" +
            "You love the idea of building apps people use every day on their phones.<br>" +
            "Mobile developers work with Android (Kotlin) or iOS (Swift)." +
            "</div></html>");

        descriptions.put("Cybersecurity",
            "<html><div style='text-align:center;font-family:Segoe UI;'>" +
            "You are drawn to protecting systems and solving security challenges.<br>" +
            "Cybersecurity professionals defend networks, data, and software from attacks." +
            "</div></html>");

        descriptions.put("Data Science",
            "<html><div style='text-align:center;font-family:Segoe UI;'>" +
            "You enjoy finding patterns and making sense of large amounts of data.<br>" +
            "Data scientists use Python, statistics, and machine learning to uncover insights." +
            "</div></html>");

        descriptions.put("Game Development",
            "<html><div style='text-align:center;font-family:Segoe UI;'>" +
            "You are passionate about creating interactive worlds and experiences.<br>" +
            "Game developers use engines like Unity or Unreal to bring games to life." +
            "</div></html>");

        descriptions.put("Software Engineering",
            "<html><div style='text-align:center;font-family:Segoe UI;'>" +
            "You enjoy designing systems and solving complex logical problems.<br>" +
            "Software engineers build large-scale, reliable software used by many people." +
            "</div></html>");
    }

    // ── Skills per career
    private static final Map<String, String[]> skillsMap = new HashMap<>();
    static {
        skillsMap.put("Web Development", new String[]{
            "- HTML & CSS",
            "- JavaScript",
            "- React or Vue.js",
            "- Git & version control",
            "- REST APIs",
            "- Responsive design"
        });

        skillsMap.put("Mobile App Development", new String[]{
            "- Java or Kotlin (Android)",
            "- Swift (iOS)",
            "- UI/UX design basics",
            "- REST API integration",
            "- Git & version control",
            "- App Store publishing"
        });

        skillsMap.put("Cybersecurity", new String[]{
            "- Networking fundamentals (TCP/IP, DNS)",
            "- Linux command line",
            "- Ethical hacking basics",
            "- Cryptography",
            "- Security tools (Wireshark, Nmap, Burp Suite)",
            "- Incident response"
        });

        skillsMap.put("Data Science", new String[]{
            "- Python programming",
            "- Statistics and probability",
            "- Pandas and NumPy",
            "- Data visualization (Matplotlib, Seaborn)",
            "- Machine learning basics (Scikit-learn)",
            "- SQL for data queries"
        });

        skillsMap.put("Game Development", new String[]{
            "- C# or C++ programming",
            "- Unity or Unreal Engine basics",
            "- 2D and 3D math concepts",
            "- Game physics and collision",
            "- Animation and sprite systems",
            "- Version control with Git"
        });

        skillsMap.put("Software Engineering", new String[]{
            "- Data structures and algorithms",
            "- Object-oriented programming (OOP)",
            "- System design basics",
            "- SQL and databases",
            "- Git & version control",
            "- Problem-solving (LeetCode, HackerRank)"
        });
    }

    // ── Roadmap per career
    private static final Map<String, String[]> roadmapMap = new HashMap<>();
    static {
        roadmapMap.put("Web Development", new String[]{
            "Step 1: Learn HTML and CSS — build simple static pages",
            "Step 2: Learn JavaScript fundamentals",
            "Step 3: Build 2-3 small projects (portfolio, landing page)",
            "Step 4: Learn a framework — React or Vue.js",
            "Step 5: Learn back-end basics — Node.js or PHP",
            "Step 6: Learn databases — MySQL or MongoDB",
            "Step 7: Build and deploy a full-stack project"
        });

        roadmapMap.put("Mobile App Development", new String[]{
            "Step 1: Learn Java or Kotlin basics",
            "Step 2: Set up Android Studio and build your first app",
            "Step 3: Learn UI layouts and screen navigation",
            "Step 4: Connect your app to a REST API",
            "Step 5: Learn local storage (SQLite or Room)",
            "Step 6: Publish your first app on the Play Store"
        });

        roadmapMap.put("Cybersecurity", new String[]{
            "Step 1: Learn how networks work (TCP/IP, DNS, HTTP)",
            "Step 2: Get comfortable with Linux and the command line",
            "Step 3: Study common attack types and defenses",
            "Step 4: Practice on TryHackMe or HackTheBox",
            "Step 5: Learn security tools — Wireshark, Nmap, Burp Suite",
            "Step 6: Earn CompTIA Security+ certification"
        });

        roadmapMap.put("Data Science", new String[]{
            "Step 1: Learn Python basics",
            "Step 2: Study statistics and math fundamentals",
            "Step 3: Learn Pandas, NumPy, and Matplotlib",
            "Step 4: Complete a beginner machine learning course",
            "Step 5: Work on a real dataset project (Kaggle is great)",
            "Step 6: Learn SQL for querying databases",
            "Step 7: Build a portfolio with 2-3 data projects"
        });

        roadmapMap.put("Game Development", new String[]{
            "Step 1: Learn C# programming basics",
            "Step 2: Install Unity and complete beginner tutorials",
            "Step 3: Build a simple 2D game (platformer or puzzle)",
            "Step 4: Learn physics, collisions, and animations",
            "Step 5: Add UI elements (menus, score, health bar)",
            "Step 6: Build and publish a complete game on itch.io"
        });

        roadmapMap.put("Software Engineering", new String[]{
            "Step 1: Master a programming language — Java or Python",
            "Step 2: Study data structures and algorithms",
            "Step 3: Learn object-oriented design principles",
            "Step 4: Study system design basics",
            "Step 5: Practice on LeetCode or HackerRank daily",
            "Step 6: Learn SQL and database design",
            "Step 7: Build and deploy a real back-end project"
        });
    }

    public ResultScreen(String userName, String career) {
        this.userName = userName;
        this.career = career;
        setupWindow();
        populateResults();
        setupListeners();
    }

    private void setupWindow() {
        setTitle("CodePath — Your Result");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(580, 560);
        setLocationRelativeTo(null);
        setResizable(false);
        add(mainPanel);
    }

    // Fills in all labels with the user's actual result
    private void populateResults() {
        resultHeading.setText("Great job, " + userName + "!");

        // Force white color on career title using HTML
        careerTitle.setText(
            "<html><font color='white'>" + career + "</font></html>"
        );

        // Set the emoji icon for the career
        String icon = icons.getOrDefault(career, "💡");
        careerIcon.setText(icon);

        String desc = descriptions.getOrDefault(career,
            "<html><div style='text-align:center;'>Your path: " + career + "</div></html>");
        careerDescription.setText(desc);
    }

    private void setupListeners() {

        // Primary button — Skills (solid emerald)
        skillsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                skillsButton.setBackground(PRIMARY_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                skillsButton.setBackground(PRIMARY_NORMAL);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                skillsButton.setBackground(PRIMARY_CLICK);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                skillsButton.setBackground(PRIMARY_HOVER);
            }
        });

        skillsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSkillsDialog();
            }
        });

        // Secondary button — Roadmap (dark surface)
        roadmapButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roadmapButton.setBackground(SECONDARY_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                roadmapButton.setBackground(SECONDARY_NORMAL);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                roadmapButton.setBackground(SECONDARY_CLICK);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                roadmapButton.setBackground(SECONDARY_HOVER);
            }
        });

        roadmapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRoadmapDialog();
            }
        });

        // Ghost button — Retake (subtle)
        retakeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                retakeButton.setBackground(GHOST_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                retakeButton.setBackground(GHOST_NORMAL);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                retakeButton.setBackground(GHOST_CLICK);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                retakeButton.setBackground(GHOST_HOVER);
            }
        });

        retakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retakeQuiz();
            }
        });
    }

    private void showSkillsDialog() {
        String[] skills = skillsMap.getOrDefault(career,
            new String[]{ "No skills listed yet." });

        JComponent panel = buildListDialogPanel(
            "Skills to develop",
            "Focus on these fundamentals for " + career + ":",
            skills
        );
        JOptionPane.showMessageDialog(this, panel,
            "Skills — " + career, JOptionPane.PLAIN_MESSAGE);
    }

    private void showRoadmapDialog() {
        String[] steps = roadmapMap.getOrDefault(career,
            new String[]{ "No roadmap listed yet." });

        JComponent panel = buildListDialogPanel(
            "Learning roadmap",
            "A simple path to get started with " + career + ":",
            steps
        );
        JOptionPane.showMessageDialog(this, panel,
            "Roadmap — " + career, JOptionPane.PLAIN_MESSAGE);
    }

    private JComponent buildListDialogPanel(String title, String subtitle, String[] items) {
        JPanel outer = new JPanel(new BorderLayout(0, 10));
        outer.setBackground(DIALOG_BG);
        outer.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        JPanel header = new JPanel(new BorderLayout(0, 4));
        header.setOpaque(false);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setForeground(DIALOG_TEXT);
        subtitleLabel.setFont(new Font("Verdana", Font.PLAIN, 12));

        header.add(titleLabel, BorderLayout.NORTH);
        header.add(subtitleLabel, BorderLayout.SOUTH);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String item : items) {
            model.addElement(item);
        }

        JList<String> list = new JList<>(model);
        list.setFont(new Font("Verdana", Font.PLAIN, 13));
        list.setForeground(DIALOG_TEXT);
        list.setBackground(DIALOG_SURFACE);
        list.setSelectionBackground(PRIMARY_HOVER);
        list.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(440, 220));
        scroll.getViewport().setBackground(DIALOG_SURFACE);
        scroll.setBorder(makeDialogBorder());

        outer.add(header, BorderLayout.NORTH);
        outer.add(scroll, BorderLayout.CENTER);
        return outer;
    }

    private Border makeDialogBorder() {
        // Subtle border around the list area
        Border line = BorderFactory.createLineBorder(new Color(0x1F, 0x3D, 0x32));
        Border pad = BorderFactory.createEmptyBorder(6, 6, 6, 6);
        return BorderFactory.createCompoundBorder(line, pad);
    }

    private void retakeQuiz() {
        LoginScreen login = new LoginScreen();
        login.setVisible(true);
        this.dispose();
    }
}
