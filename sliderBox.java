import java.awt.Color;
import javax.swing.*;
import javax.swing.event.ChangeListener;

import acm.gui.TableLayout;

public class sliderBox {

	JPanel myPanel;
	JLabel nameLabel;
	JLabel minLabel;
	JLabel maxLabel;
	JSlider mySlider;
	JLabel sReadout;
	Integer imin;
	Integer imax;
	Integer MaxSize;

	public sliderBox(String name, Integer min, Integer dValue, Integer max) 
	{ // Integer values
		myPanel = new JPanel();
		nameLabel = new JLabel(name);
		minLabel = new JLabel(min.toString()); 
		maxLabel = new JLabel(max.toString()); 
		mySlider = new JSlider(min,max,dValue); 
		sReadout = new JLabel(dValue.toString()); 
		sReadout.setForeground(Color.blue); 
		myPanel.setLayout(new TableLayout(1,5));
		myPanel.add(nameLabel,"width=100"); 
		myPanel.add(minLabel,"width=25"); 
		myPanel.add(mySlider,"width=100"); 
		myPanel.add(maxLabel,"width=100"); 
		myPanel.add(sReadout,"width=80"); 
		imin=min;
		imax=max;

	}
	public sliderBox(String name, double min, double dValue,double max) { // double values
		myPanel = new JPanel();
		nameLabel = new JLabel(name);
		minLabel = new JLabel(min+"");
		maxLabel = new JLabel(max+"");
		mySlider = new JSlider((int)min*10,(int)max*10,(int)dValue*10);
		sReadout = new JLabel(dValue+"");
		sReadout.setForeground(Color.blue);
		myPanel.setLayout(new TableLayout(1,5));
		myPanel.add(nameLabel,"width=100");
		myPanel.add(minLabel,"width=25");
		myPanel.add(mySlider,"width=100");
		myPanel.add(maxLabel,"width=100");
		myPanel.add(sReadout,"width=80");
		imin=(int)min;
		imax=(int)max;
	}
	public Integer getSlider() 
	{
		return mySlider.getValue();
	}
	public void setISlider(int val)//set sliders using integer value s
	{
		mySlider.setValue(val);
		sReadout.setText(val/10+"");

	}
	public void setDSlider(double val)//set up sliders using double values
	{
		mySlider.setValue((int)val);
		sReadout.setText(val/10.0+" ");
	}

}
