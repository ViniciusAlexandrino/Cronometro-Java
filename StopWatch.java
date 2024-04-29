import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopWatch implements ActionListener {
	JFrame frame = new JFrame();// Cria uma janela para o cronômetro
	JButton startButton = new JButton("INICIAR");// Cria um botão para iniciar/parar o cronômetro
	JButton resetButton = new JButton("RESET");// Cria um botão para reiniciar o cronômetro
	JLabel timeLabel = new JLabel();// Cria um rótulo para mostrar o tempo decorrido
	int elapsedTime=0;// Variável para armazenar o tempo decorrido em milissegundos
	int seconds = 0;// Variável para armazenar os segundos decorrido
	int minutes = 0;// Variável para armazenar os minutos decorrido
	int hours = 0;// Variável para armazenar as horas decorrido
	boolean started = false;// Variável para saber se o cronômetro está em execução ou parado
	
	// Strings formatadas para exibir segundos, minutos e horas com dois dígitos
	String seconds_string = String.format("%02d",seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);
	
	// Cria um temporizador (Timer) que dispara a cada segundo (1000 milissegundos) e atualiza o tempo decorrido
	Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// Incrementa o tempo decorrido
			elapsedTime=elapsedTime+1000;// Calcula as horas, minutos e segundos do tempo decorrido
			hours = (elapsedTime/3600000);
			minutes = (elapsedTime/60000) % 60;
			seconds = (elapsedTime/1000) % 60;
			// Atualiza as strings formatadas
			seconds_string = String.format("%02d",seconds);
			minutes_string = String.format("%02d",minutes);
			hours_string = String.format("%02d", hours);
			timeLabel.setText(hours_string+"."+minutes_string+"."+seconds_string);// Define o texto do rótulo para mostrar o tempo no formato HH:MM:SS
		}
	});
	
	// Construtor do cronômetro	
	StopWatch() {
		// Define o texto inicial do rótulo como 00:00:00
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		timeLabel.setBounds(100,100,200,100);// Define a posição e tamanho do rótulo
		timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));// Define a fonte e tamanho do texto do rótulo
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));// Define uma borda em volta do rótulo
		timeLabel.setOpaque(true);// Define que o rótulo é opaco
		timeLabel.setHorizontalAlignment(JTextField.CENTER);// Alinha o texto do rótulo ao centro
		
		// Configurações do botão de iniciar/parar
		startButton.setBounds(100,200,100,50);// Define a posição e tamanho do botão
		startButton.setFont(new Font("Ink Free",Font.PLAIN,20));// Define a fonte e tamanho do texto do botão
		startButton.setFocusable(false);// Impede que o botão receba foco
		startButton.addActionListener(this);// Adiciona o ActionListener ao botão para tratar eventos
		
		// Configurações do botão de reiniciar
		resetButton.setBounds(200,200,100,50);// Define a posição e tamanho do botão
		resetButton.setFont(new Font("Ink Free",Font.PLAIN,20));// Define a fonte e tamanho do texto do botão
		resetButton.setFocusable(false);// Impede que o botão receba foco
		resetButton.addActionListener(this);// Adiciona o ActionListener ao botão para tratar eventos
		
		// Adiciona os componentes ao frame
		frame.add(startButton);
		frame.add(resetButton);
		frame.add(timeLabel);
		
		// Configurações do frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Define que a aplicação fecha ao sair do frame
		frame.setSize(420,420);// Define o tamanho do frame
		frame.setLayout(null);// Usa layout absoluto para posicionamento dos componentes
		frame.setVisible(true);// Torna o frame visível
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {// Método para tratar eventos de ação
		
		if(e.getSource()==startButton) {// Se o botão pressionado for o de iniciar/parar
			
			if(started==false) {
				started=true;
				startButton.setText("PARAR");
				start();
			}
			else {
				started=false;
				startButton.setText("INICIAR");
				stop();
			}
			
		}
		if(e.getSource()==resetButton) {
			started=false;
			startButton.setText("INICIAR");
			reset();
		}
		
	}
	
	void start() {
		timer.start();// Inicia o temporizador
	}
	
	void stop() {
		timer.stop();// Para o temporizador
	}
	
	void reset() {
		timer.stop();// Para o temporizador
		elapsedTime=0;// Redefine o tempo decorrido para 0
		seconds=0;// Redefine os segundos
		minutes=0;// Redefine os minutos
		hours=0;// Redefine as horas
		
		// Redefine as strings formatadas
		seconds_string = String.format("%02d",seconds);
		minutes_string = String.format("%02d",minutes);
		hours_string = String.format("%02d",hours);
		
		 // Atualiza o texto do rótulo para mostrar o tempo no formato HH:MM:SS
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
	}
	
	
}
