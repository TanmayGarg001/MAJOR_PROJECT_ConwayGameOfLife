/*
 * Build Start date: 12/Aug/2021
 *
 * John Conway's Game of Life: The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970.
 * One interacts with the Game of Life by creating an initial configuration and observing how it evolves.
 * It is Turing complete and can simulate a universal constructor or any other Turing machine.
 *
 * Technologies used:
 * Java17 (JDK: Amazon Corretto [LTS-version])
 * JavaSwing : https://www.javatpoint.com/java-swing
 * JavaAWT : https://www.javatpoint.com/java-awt
 * Adobe Illustrator 2021
 *
 * Build End date: 26/Aug/2021
 */

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameOfLifeFrame extends JFrame {
    //https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
    //Application icon depicts: The R-pentomino is a methuselah that was found by John Conway in 1969.
    //Methuselah : A methuselah is a pattern that takes a large number of generations in order to stabilize (known as its lifespan) and becomes much larger than its initial
    //configuration at some point during its evolution. There is no general agreement on the exact definition, but patterns that stabilize in less than 100 generations are not
    //generally called methuselahs.

    private static final ImageIcon instructionIcon = new ImageIcon(Objects.requireNonNull(GameOfLifeFrame.class.getClassLoader().getResource("res/controls.png")));
    private static final ImageIcon applicationIcon = new ImageIcon(Objects.requireNonNull(GameOfLifeFrame.class.getClassLoader().getResource("res/application.png")));

    public GameOfLifeFrame() {
        setTitle("Conway's Game of Life");
        add(new GameOfLifePanel());
        setSize(1607, 910);
        setVisible(true);
        setResizable(false);
        setIconImage(applicationIcon.getImage().getScaledInstance(47, 50, Image.SCALE_SMOOTH));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        keyMappings();
    }

    public static void main(String[] args) {
        new GameOfLifeFrame();
    }

    public static void keyMappings() {
        JFrame popUpFrame = new JFrame();
        popUpFrame.setSize(900, 500);

        JPanel panel = new JPanel();
        panel.setSize(900, 500);
        panel.setBackground(new Color(16, 24, 32));

        JLabel instructions = new JLabel("<html><br><br><br><strong>KEY MAPPINGS: </strong>" +
                "<br><br>Press <strong>'R'</strong> : to plot random lives on the grid." +
                "<br>Press <strong>'P'</strong> : to toggle the grid on/off." +
                "<br>Press <strong>'BackSpace'</strong> : to clear the grid." +
                "<br>Press <strong>'Enter'</strong> : to start the Game of Life simulation." +
                "<br>Press <strong>'SpaceBar'</strong> : to pause the Game of Life simulation." +
                "<br>Press <strong>'↑'</strong> : to increase the simulation speed." +
                "<br>Press <strong>'↓'</strong> : to decrease the simulation speed." +
                "<br>Use <strong>'Mouse'</strong> in order to draw custom patterns. </html>");
        instructions.setFont(new Font("Consolas", Font.PLAIN, 24));
        instructions.setForeground(new Color(242, 170, 76));

        panel.add(instructions);
        popUpFrame.add(panel, BorderLayout.CENTER);
        popUpFrame.setVisible(true);
        popUpFrame.setResizable(false);
        popUpFrame.setLocationRelativeTo(null);
        popUpFrame.setIconImage(instructionIcon.getImage().getScaledInstance(23, 30, Image.SCALE_SMOOTH));
        popUpFrame.setTitle("Key Mappings");
    }

}
