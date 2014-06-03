package br.com.naptec.pessoa.frm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPessoa extends JFrame {

	private JPanel contentPane;
	private JTextField txCodigo;
	private JTextField txNome;
	private JTextField txIdade;
	private JTextField txEmail;
	private JButton btLimpar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPessoa frame = new FrmPessoa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPessoa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 193);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbCodigo = new JLabel("C\u00F3digo");
		lbCodigo.setBounds(8, 8, 59, 14);
		contentPane.add(lbCodigo);
		
		txCodigo = new JTextField();
		txCodigo.setBounds(77, 5, 86, 20);
		contentPane.add(txCodigo);
		txCodigo.setColumns(10);
		
		JLabel lbNome = new JLabel("Nome");
		lbNome.setBounds(8, 36, 59, 14);
		contentPane.add(lbNome);
		
		txNome = new JTextField();
		txNome.setColumns(10);
		txNome.setBounds(77, 33, 262, 20);
		contentPane.add(txNome);
		
		JLabel lbIdade = new JLabel("Idade");
		lbIdade.setBounds(8, 64, 59, 14);
		contentPane.add(lbIdade);
		
		txIdade = new JTextField();
		txIdade.setColumns(10);
		txIdade.setBounds(77, 61, 86, 20);
		contentPane.add(txIdade);
		
		JLabel lbEmail = new JLabel("E-mail");
		lbEmail.setBounds(8, 92, 59, 14);
		contentPane.add(lbEmail);
		
		txEmail = new JTextField();
		txEmail.setColumns(10);
		txEmail.setBounds(77, 89, 262, 20);
		contentPane.add(txEmail);
		
		JButton btCarregar = new JButton("Carregar");
		btCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btCarregar.setBounds(107, 117, 89, 23);
		contentPane.add(btCarregar);
		
		btLimpar = new JButton("Limpar");
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btLimpar.setBounds(8, 117, 89, 23);
		contentPane.add(btLimpar);
	}

	protected void limparCampos() {
		this.txCodigo.setText("");
		this.txNome.setText("");
		this.txIdade.setText("");
		this.txEmail.setText("");
	}

	protected void alerta() {
		//this = esta instância de objeto da classe FrmPessoa
		JOptionPane.showMessageDialog(null, "Funciona!");
	}
}
