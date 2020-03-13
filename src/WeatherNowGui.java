import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class WeatherNowGui extends JPanel implements ActionListener, MouseListener {
	JButton searchBtn;
	BufferedImage image;
	JTextField searchField;
	JLabel temp, cloud, wind, location;
	private String searchQuery;
	Data weatherData;

	public WeatherNowGui() {
		searchQuery = "";
		weatherData = new Data();
		setLayout(null);
		searchBtn = new JButton();
		searchBtn.addMouseListener(this);
		searchBtn.addActionListener(this);
		setButton(550, 400, searchBtn);

		searchField = new JTextField();
		setTextField(searchField);

		temp = new JLabel();
		setLabel(95, 250, 100, 50, temp, 0);

		wind = new JLabel();
		setLabel(600, 250, 100, 50, wind, 0);

		cloud = new JLabel();
		setLabel(325, 250, 150, 50, cloud, 0);

		location = new JLabel("City or zip");
		setLabel(200, 330, 400, 50, location, 1);

		loadImage("/images/WeatherNow.jpg");
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String s) {
		searchQuery = s;
	}

	public void setLabel(int x, int y, int width, int height, JLabel lb, int val) {
		Font font1 = new Font("Kai", Font.BOLD, 20);
		Font font2 = new Font("Kai", Font.BOLD, 40);
		lb.setBounds(x, y, width, height);
		lb.setFont(font1);
		lb.setForeground(Color.WHITE);
		if (val == 0) {
			lb.setBorder(new MatteBorder(0, 0, 4, 0, Color.BLACK));
			lb.setFont(font1);
		} else {
			lb.setFont(font2);
		}

		lb.setHorizontalAlignment(JLabel.CENTER);
		lb.setVerticalAlignment(JLabel.CENTER);
		add(lb);
	}

	public void setTextField(JTextField tf) {
		Font font1 = new Font("Kai", Font.BOLD, 20);
		tf.setBounds(210, 400, 350, 50);
		tf.setFont(font1);
		tf.setBorder(BorderFactory.createEmptyBorder());
		add(tf);
	}

	public void setButton(int x, int y, JButton btn) {
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBounds(x, y, 50, 50);
		btn.setBorder(new MatteBorder(0, 0, 4, 0, Color.BLACK));

		add(btn);
	}

	// Paint the background
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}

	// Loading the image from resources
	public void loadImage(String s) {
		try {
			image = ImageIO.read(WeatherNowGui.class.getResource(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == searchBtn) {
			if (searchField.getText().isEmpty()) {
				location.setText("Put something");
			} else {
				weatherData.onSearched(searchField.getText());
				if (weatherData.getValid() == 1) {
					temp.setText(weatherData.getTemperature() + " \u00B0" + "F");
					cloud.setText(weatherData.getDescription());
					wind.setText(weatherData.getWindLevel() + " mph");
					location.setText(weatherData.getLocationName());
				} else {
					location.setText("Invalid");
					temp.setText("");
					cloud.setText("");
					wind.setText("");

				}
				searchField.setText("");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == searchBtn)
			searchBtn.setBorder(new MatteBorder(0, 0, 4, 0, Color.GRAY));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == searchBtn)
			searchBtn.setBorder(new MatteBorder(0, 0, 4, 0, Color.BLACK));
	}

}
