import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class Calculator extends JFrame {

	private JPanel contentPane;
	private JTextField numField;
	private BigDecimal register[] = {null, null};
	private short index = 0;
	private char symbol = '\0';
	private boolean numDec[] = {false, false};
	private short numN[] = {0, 0};
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private BigDecimal calculate(BigDecimal arg0, char symbol, BigDecimal arg1){
		BigDecimal temp;
		switch(symbol){
		case '/':
			temp = arg0.divide(arg1);
			break;
		case '*':
			temp = arg0.multiply(arg1);
			break;
		case '-':
			temp = arg0.subtract(arg1);
			break;
		case '+':
			temp = arg0.add(arg1);
			break;
			default:
				return null;
		}
		return temp;
		
	}
	private void update(){
		if(register[0] == null) {
			numField.setText(null);
		}else if(symbol == '\0') {
			numField.setText(register[0].toString());
		}else if(register[1] == null){
			numField.setText(register[0].toString() + symbol);
		}else{
			numField.setText(register[0].toString() + symbol + register[1].toString());
		}
	}
	
	private void modify(double num){
		if(register[index] == null) {
			register[index] = new BigDecimal(num);
		}else {
			if(numDec[index]) {
				BigDecimal temp = new BigDecimal(.1).setScale(numN[index], BigDecimal.ROUND_DOWN);
				for(short i = 1; i < numN[index]; i++){
					temp = temp.multiply(new BigDecimal(.1));
				}
				numN[index]++;
				temp = temp.multiply(new BigDecimal(num));
				//System.out.println(temp.toString());
				register[index] = register[index].add(temp).setScale(numN[index]-1, BigDecimal.ROUND_DOWN);
			}else{
				register[index] = register[index].multiply(new BigDecimal(10.0));
				register[index] = register[index].add(new BigDecimal(num));
			}
		}
	}
	
	private void updateSymbol(char smb) {
		if(index == 0){
			symbol = smb;
			index = 1;
		}else if(index == 1){
			register[0] = calculate(register[0], symbol, register[1]);
			symbol = smb;
			register[1] = null;
			
		}
	}
	
	
	public Calculator() {
		setResizable(false);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				numField = new JTextField();
				numField.setBounds(0, 0, 235, 55);
				contentPane.add(numField);
				numField.setColumns(10);
		
				JButton keyCButton = new JButton("C");
				keyCButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						register[0] = null;
						register[1] = null;
						symbol = '\0';
						numDec[0] = false;
						numN[0] = 0;
						numDec[1] = false;
						numN[1] = 0;
						index = 0;
						update();
						
					}
				});
				keyCButton.setBounds(0, 60, 55, 55);
				contentPane.add(keyCButton);
		
				JButton keyDivButton = new JButton("/");
				keyDivButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						updateSymbol('/');
						update();
					}
						
				});
				keyDivButton.setBounds(60, 60, 55, 55);
				contentPane.add(keyDivButton);
		
				JButton keyMultButton = new JButton("*");
				keyMultButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						updateSymbol('*');
						update();
					}
				});
				keyMultButton.setBounds(120, 60, 55, 55);
				contentPane.add(keyMultButton);
		
				JButton keyMinButton = new JButton("-");
				keyMinButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						updateSymbol('-');
						update();
					}
				});
				keyMinButton.setBounds(180, 60, 55, 55);
				contentPane.add(keyMinButton);
		
				JButton num7Button = new JButton("7");
				num7Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modify(7.0);
						update();
					}
				});
				num7Button.setBounds(0, 120, 55, 55);
				contentPane.add(num7Button);
		
				JButton num8Button = new JButton("8");
				num8Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modify(8.0);
						update();
					}
				});
				num8Button.setBounds(60, 120, 55, 55);
				contentPane.add(num8Button);
		
				JButton num9Button = new JButton("9");
				num9Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modify(9.0);
						update();
					}
				});
				num9Button.setBounds(120, 120, 55, 55);
				contentPane.add(num9Button);
		
				JButton keyPlusButton = new JButton("+");
				keyPlusButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						updateSymbol('+');
						update();
					}
				});
				keyPlusButton.setBounds(180, 120, 55, 115);
				contentPane.add(keyPlusButton);
		
				JButton num4Button = new JButton("4");
				num4Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modify(4.0);
						update();
					}
				});
				num4Button.setBounds(0, 180, 55, 55);
				contentPane.add(num4Button);
		
				JButton num5Button = new JButton("5");
				num5Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modify(5.0);
						update();
					}
				});
				num5Button.setBounds(60, 180, 55, 55);
				contentPane.add(num5Button);
		
				JButton num6Button = new JButton("6");
				num6Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modify(6.0);
						update();
					}
				});
				num6Button.setBounds(120, 180, 55, 55);
				contentPane.add(num6Button);
		
				JButton num1Button = new JButton("1");
				num1Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modify(1.0);
						update();
					}
				});
				num1Button.setBounds(0, 240, 55, 55);
				contentPane.add(num1Button);
		
				JButton num2Button = new JButton("2");
				num2Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modify(2.0);
						update();
					}
				});
				num2Button.setBounds(60, 240, 55, 55);
				contentPane.add(num2Button);
		
				JButton num3Button = new JButton("3");
				num3Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modify(3.0);
						update();
					}
				});
				num3Button.setBounds(120, 240, 55, 55);
				contentPane.add(num3Button);
		
				JButton num0Button = new JButton("0");
				num0Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modify(0.0);
						update();
					}
				});
				
						JButton keyEqualsButton = new JButton("=");
						keyEqualsButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								if(register[0] != null && symbol != '\0' && register[1] != null){
									register[0] = calculate(register[0], symbol, register[1]);
									index = 0;
									register[1] = null;
									symbol = '\0';
									update();
								}
							}
						});
						keyEqualsButton.setBounds(180, 240, 55, 115);
						contentPane.add(keyEqualsButton);
				num0Button.setBounds(0, 300, 115, 55);
				contentPane.add(num0Button);

		JButton keyDotButton = new JButton(".");
		keyDotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!numDec[index]) {
					numDec[index] = true;
					numN[index] = 1;
				}
			}
		});
		keyDotButton.setBounds(120, 300, 55, 55);
		contentPane.add(keyDotButton);
	}
}
