import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOfLifePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

    static final int RES_WIDTH = 1607;
    static final int RES_HEIGHT = 910;
    static final int CELL_SIZE = 15;
    static final int X_COUNT = RES_WIDTH / CELL_SIZE;
    static final int Y_COUNT = RES_HEIGHT / CELL_SIZE;
    static final int[][] gridDataPresent = new int[X_COUNT][Y_COUNT];
    static final int[][] gridDataFuture = new int[X_COUNT][Y_COUNT];
    static final Color DEAD_COLOR = new Color(34, 46, 64);
    static final Color ALIVE_COLOR = new Color(233, 236, 239);
    static final Color LINE_COLOR = new Color(168, 218, 220);
    static int CLICK_STATUS = -1;

    static int P_TRACK_STATUS = 1;//plot grid toggle keys
    static boolean CHANGE_FLOW = false;
    Timer timer;

    public GameOfLifePanel() {
        setSize(RES_WIDTH, RES_HEIGHT);
        setBackground(DEAD_COLOR);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(100, this);
    }

    public void paintComponent(Graphics graphics) {
        if (CHANGE_FLOW) {
            paintComponent2(graphics);
        } else {
            super.paintComponent(graphics);//keeps updating/refreshing the location of the graphics
            plotGridHide(graphics);
            display(graphics);
        }
    }

    public void paintComponent2(Graphics graphics) {
        super.paintComponent(graphics);//keeps updating/refreshing the location of the graphics
        plotGridShow(graphics);
        display(graphics);
    }

    private void plotGridHide(Graphics graphics) {
        graphics.setColor(DEAD_COLOR);
        for (int i = 0; i < gridDataPresent.length; i++) {
            graphics.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, RES_HEIGHT);//vertical lines
            graphics.drawLine(0, i * CELL_SIZE, RES_WIDTH, i * CELL_SIZE);//horizontal lines
        }
    }

    private void plotGridShow(Graphics graphics) {
        graphics.setColor(LINE_COLOR);
        for (int i = 0; i < gridDataPresent.length; i++) {
            graphics.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, RES_HEIGHT);//vertical lines
            graphics.drawLine(0, i * CELL_SIZE, RES_WIDTH, i * CELL_SIZE);//horizontal lines
        }
    }

    private void giveLifeInitially() {
        for (int i = 0; i < gridDataPresent.length; i++) {
            for (int j = 0; j < gridDataPresent[0].length; j++) {
                if ((int) (Math.random() * 7) == 0) {
                    gridDataFuture[i][j] = 1;
                }
            }
        }
    }

    private void clearLife() {
        for (int i = 0; i < gridDataPresent.length; i++) {
            for (int j = 0; j < gridDataPresent[0].length; j++) {
                gridDataFuture[i][j] = 0;
            }
        }
    }

    private void display(Graphics graphics) {
        graphics.setColor(ALIVE_COLOR);
        duplicate();
        for (int i = 0; i < gridDataPresent.length; i++) {
            for (int j = 0; j < gridDataPresent[0].length; j++) {
                if (gridDataPresent[i][j] == 1) {
                    graphics.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    private void duplicate() {
        for (int i = 0; i < gridDataPresent.length; i++) {
            System.arraycopy(gridDataFuture[i], 0, gridDataPresent[i], 0, gridDataPresent[0].length);
        }
    }

    private int checkDirections(int x, int y) {
        int alive = 0;//all 8 directions possible, cyclic in nature
        alive += gridDataPresent[(x + X_COUNT + 1) % X_COUNT][(y + Y_COUNT + 1) % Y_COUNT];
        alive += gridDataPresent[(x + X_COUNT) % X_COUNT][(y + Y_COUNT + 1) % Y_COUNT];
        alive += gridDataPresent[(x + X_COUNT - 1) % X_COUNT][(y + Y_COUNT + 1) % Y_COUNT];

        alive += gridDataPresent[(x + X_COUNT + 1) % X_COUNT][(y + Y_COUNT) % Y_COUNT];
        alive += gridDataPresent[(x + X_COUNT - 1) % X_COUNT][(y + Y_COUNT) % Y_COUNT];

        alive += gridDataPresent[(x + X_COUNT + 1) % X_COUNT][(y + Y_COUNT - 1) % Y_COUNT];
        alive += gridDataPresent[(x + X_COUNT) % X_COUNT][(y + Y_COUNT - 1) % Y_COUNT];
        alive += gridDataPresent[(x + X_COUNT - 1) % X_COUNT][(y + Y_COUNT - 1) % Y_COUNT];
        return alive;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int aliveCount;
        for (int i = 0; i < gridDataPresent.length; i++) {
            for (int j = 0; j < gridDataPresent[0].length; j++) {
                aliveCount = checkDirections(i, j);
                if (aliveCount == 3) {
                    gridDataFuture[i][j] = 1;
                } else if (aliveCount == 2 && gridDataPresent[i][j] == 1) {
                    gridDataFuture[i][j] = 1;
                } else {
                    gridDataFuture[i][j] = 0;
                }
            }
        }
        repaint();//repaints each frame
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xPos = e.getX() / CELL_SIZE;
        int yPos = e.getY() / CELL_SIZE;

        if (gridDataPresent[xPos][yPos] == 0) {
            CLICK_STATUS = 0;
        } else {
            CLICK_STATUS = 1;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int xPos = e.getX() / CELL_SIZE;
        int yPos = e.getY() / CELL_SIZE;
        if (gridDataPresent[xPos][yPos] == 0 && CLICK_STATUS == 0) {
            gridDataFuture[xPos][yPos] = 1;
        } else if (gridDataPresent[xPos][yPos] == 1 && CLICK_STATUS == 1) {
            gridDataFuture[xPos][yPos] = 0;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        CLICK_STATUS = -1;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_R:
                giveLifeInitially();
                timer.start();
                break;
            case KeyEvent.VK_BACK_SPACE:
                clearLife();
                timer.stop();
                break;
            case KeyEvent.VK_ENTER:
                timer.start();
                break;
            case KeyEvent.VK_SPACE:
                timer.stop();
                break;
            case KeyEvent.VK_DOWN:
                if (timer.getDelay() >= 1500) {
                    System.out.println("Slowest speed achieved!");
                } else {
                    timer.setDelay(timer.getDelay() + 20);
                }
                break;
            case KeyEvent.VK_UP:
                if (timer.getDelay() <= 16) {
                    System.out.println("Maximum speed achieved!");//Maximum frame rate
                } else {
                    timer.setDelay(timer.getDelay() - 20);
                }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        P_TRACK_STATUS = P_TRACK_STATUS + 1;
        if (keyCode == KeyEvent.VK_P && P_TRACK_STATUS % 2 == 0) {
            CHANGE_FLOW = true;
            paintComponent2(getComponentGraphics(getGraphics()));
        } else {
            CHANGE_FLOW = false;
            paintComponent(getComponentGraphics(getGraphics()));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
