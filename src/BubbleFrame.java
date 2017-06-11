import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


class BubblePanel extends JPanel{

	Bubble mess, tidy;
	int R, G, B;

	public BubblePanel(Bubble mess, Bubble tidy){ 
		this.mess = mess;
		this.tidy = tidy;
		setBackground(Color.BLACK);
		R = 1;
		G = 0;
		B = 0;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int X = getWidth();
		int Y = getHeight();
		int w = getWidth()/getMess().getArray().length;
		int h = 50;

		for(int i = 0; i < getMess().getArray().length; i++){
			int val = (int)(255*getMess().getArray()[i]);
			Color clr = new Color(val*getR(), val*getG(), val*getB());
			g.setColor(clr);
			g.fillRect(w*i, Y/2 - h - 10, w, h);
		}

		for(int i = 0; i < getTidy().getArray().length; i++){
			int val = (int)(255*getTidy().getArray()[i]);
			Color clr = new Color(val*getR(), val*getG(), val*getB());
			g.setColor(clr);
			g.fillRect(w*i, Y/2 + 10, w, h);
		}
	}


	public Bubble getMess(){
		return mess;
	}
	public void newMess(int n){
		this.mess = Bubble.messArray(n);
	}
	public Bubble getTidy(){
		return Bubble.tidyArray(getMess());
	}
	public void newTidy(){
		this.tidy = Bubble.tidyArray(getMess());
	}
	public int getR(){
		return R;
	}
	public void setR(int R){
		this.R = R;
	}
	public int getG(){
		return G;
	}
	public void setG(int G){
		this.G = G;
	}
	public int getB(){
		return B;
	}
	public void setB(int B){
		this.B = B;
	}
}



public class BubbleFrame extends JFrame{

	private int X = 800, Y = 250;
	BubblePanel bpan;
	JLabel label;
	JTextField intext;
	JPanel control, buttonpanel;
	Bubble mess, tidy;
	JPanel controlframe;

	JRadioButton R = new JRadioButton("R",true);
	JRadioButton G = new JRadioButton("G");
	JRadioButton B = new JRadioButton("B");

	public BubbleFrame(Bubble mess, Bubble tidy){
		//this.mess = mess;
		//this.tidy = tidy;

		label = new JLabel("Enter a value: ");
		intext = new JTextField(20);

		intext.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				intext.setText("");
			}
		});

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		control = new JPanel();
		buttonpanel = new JPanel();
		bpan = new BubblePanel(mess, tidy);
		controlframe = new JPanel();

		ButtonGroup group = new ButtonGroup();
		group.add(R);
		group.add(G);
		group.add(B);

		control.add(label);
		control.add(intext);
		buttonpanel.add(R);
		buttonpanel.add(G);
		buttonpanel.add(B);

		setLayout(new BorderLayout());

		controlframe.add(control, BorderLayout.NORTH);
		controlframe.add(buttonpanel, BorderLayout.SOUTH);
		getContentPane().add(bpan, BorderLayout.CENTER);
		getContentPane().add(controlframe, BorderLayout.SOUTH);
		//getContentPane().add(buttonpanel, BorderLayout.SOUTH);
		pack();

		setTitle("Bubble Sort");
		setSize(X, Y);
		setLocation(200, 100);
		setVisible(true);
		setBackground(Color.LIGHT_GRAY);

		renumerate();
		redFlavour();
		greenFlavour();
		blueFlavour();
	}

	public void renumerate(){
		intext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int n = Integer.parseInt(intext.getText());
				bpan.newMess(n);
				repaint();
			}
		});
	}

	public void redFlavour(){
		R.isSelected();
		R.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(R.isSelected()){
					bpan.setR(1);
					bpan.setG(0);
					bpan.setB(0);
				}
				else if(!R.isSelected()) bpan.setR(0);
				repaint();
			}
		});
	}

	public void greenFlavour(){
		G.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(G.isSelected()){
					bpan.setR(0);
					bpan.setG(1);
					bpan.setB(0);
				}
				else if(!G.isSelected()) bpan.setG(0);
				repaint();
			}
		});
	}

	public void blueFlavour(){
		B.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(B.isSelected()){
					bpan.setR(0);
					bpan.setG(0);
					bpan.setB(1);
				}
				else if(!B.isSelected()) bpan.setB(0);
				repaint();
			}
		});
	}
}
