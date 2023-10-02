import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {
    private JLabel titleLabel;
    private ImageIcon fortuneTellerImage;
    private JTextArea fortunesTextArea;
    private JButton readFortuneButton;
    private JButton quitButton;

    private ArrayList<String> fortunes;
    private int previousFortuneIndex;

    public FortuneTellerFrame() {
        setTitle("Fortune Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fortunes = new ArrayList<>();
        fortunes.add("Fortune 1: You will find unexpected joy today.");
        fortunes.add("Fortune 2: A great opportunity will come your way soon.");
        fortunes.add("Fortune 3: Be prepared for a pleasant surprise.");
        fortunes.add("Fortune 4: Your hard work will pay off soon.");
        fortunes.add("Fortune 5: Take risks, and you will be rewarded.");

        previousFortuneIndex = -1;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        titleLabel = new JLabel("Fortune Teller");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        fortuneTellerImage = new ImageIcon("fortune-teller.png");
        titleLabel.setIcon(fortuneTellerImage);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        topPanel.add(titleLabel);

        fortunesTextArea = new JTextArea(10, 40);
        fortunesTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(fortunesTextArea);

        JPanel bottomPanel = new JPanel();
        readFortuneButton = new JButton("Read My Fortune!");
        quitButton = new JButton("Quit");
        bottomPanel.add(readFortuneButton);
        bottomPanel.add(quitButton);

        readFortuneButton.addActionListener(e -> readFortune());
        quitButton.addActionListener(e -> System.exit(0));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width * 3 / 4;
        int height = screenSize.height * 3 / 4;
        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        setSize(width, height);
    }

    private void readFortune() {
        if (fortunes.isEmpty()) {
            fortunesTextArea.setText("No fortunes available.");
            return;
        }

        Random random = new Random();
        int randomIndex;
        do {
            randomIndex = random.nextInt(fortunes.size());
        } while (randomIndex == previousFortuneIndex);

        String fortune = fortunes.get(randomIndex);
        fortunesTextArea.append(fortune + "\n");
        previousFortuneIndex = randomIndex;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FortuneTellerFrame frame = new FortuneTellerFrame();
            frame.setVisible(true);
        });
    }
}
