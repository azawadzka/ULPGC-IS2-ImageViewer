package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static java.awt.Image.SCALE_DEFAULT;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class View extends JFrame {

    JPanel mainPanel;
    JPanel panelImg;
    JButton buttonNext;
    JButton buttonPrev;
    Image curr;
    Image prev;
    Image next;

    public View() {
        super("Image viewer");
        setSize(500, 400);
        setResizable(false);
        // due to the fixed size of display that does not exceed Integer size limitations
        // handling excessive size of image has been omitted.

        try {
            curr = ImageIO.read(new File("bg.jpg"));
            prev = next = curr;
        } catch (IOException e) {
            System.err.println("initial image loading failed");
            System.exit(1);
        }

        adjustComponents();
        this.setVisible(true);
    }

    public void receiveNext(Image i) {
        setImage(next);
        prev = curr;
        curr = next;
        next = scaleImage(i);
    }

    public void receivePrev(Image i) {
        setImage(prev);
        next = curr;
        curr = prev;
        prev = scaleImage(i);
    }

    public void receiveInitialImages( Image p, Image c, Image n) {
        curr = scaleImage(c);
        setImage(curr);
        prev = scaleImage(p);
        next = scaleImage(n);
    }

    public void setListenerNext(ActionListener listener) {
        buttonNext.addActionListener(listener);
    }

    public void setListenerPrev(ActionListener listener) {
        buttonPrev.addActionListener(listener);
    }

    public void handleEmptyDirectory() {
        JOptionPane.showMessageDialog(this, "The directory is empty. To display images put files into the directory 'images' in the program directory." , "Error", WARNING_MESSAGE);
        System.exit(1);
    }

    private void adjustComponents() {
        mainPanel = new JPanel();
        panelImg = new JPanel();
        buttonNext = new JButton(">");
        buttonPrev = new JButton("<");

        mainPanel.setLayout(new BorderLayout());
        panelImg.setLayout(new GridLayout(1,1));

        mainPanel.add(buttonPrev, BorderLayout.LINE_START);
        mainPanel.add(panelImg, BorderLayout.CENTER);
        mainPanel.add(buttonNext, BorderLayout.LINE_END);
        this.add(mainPanel);
    }

    private Image scaleImage(Image image) {
        float scale = Float.max(image.getWidth(null) / panelImg.getWidth(),
                image.getHeight(null) / panelImg.getHeight());
        float width = image.getWidth(null) / scale;
        float height = image.getHeight(null) / scale;
        return image.getScaledInstance((int) width, (int) height, SCALE_DEFAULT);
    }

    private void setImage(Image image) {
        panelImg.removeAll();
        JLabel picLabel = new JLabel(new ImageIcon(scaleImage(image)));
        panelImg.add(picLabel);
        setVisible(true);
    }

}
