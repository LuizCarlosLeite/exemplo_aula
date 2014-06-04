package br.com.naptec.pessoa.frm;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import br.com.naptec.base_dados.beans.Pessoa;
import br.com.naptec.pessoa.services.PessoaService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmPessoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txCodigo;
	private JTextField txNome;
	private JTextField txIdade;
	private JTextField txEmail;
	private JButton btNovo;
	private PessoaService service;
	private JButton btEditar;
	private JTextField txPesquisar;
	private JButton btGravar;
	private JButton btPesquisar;
	private JButton btExcluir;
	private JLabel lbPesquisar;
	private JLabel lbCodigo;
	private JLabel lbNome;
	private JLabel lbIdade;
	private JLabel lbEmail;

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
		//Instanciar o objeto service da classe PessoaService
		service = new PessoaService();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbCodigo = new JLabel("C\u00F3digo");
		lbCodigo.setBounds(10, 41, 59, 14);
		contentPane.add(lbCodigo);
		
		txCodigo = new JTextField();
		txCodigo.setEditable(false);
		txCodigo.setToolTipText("");
		txCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
//				preencher(); //Exemplo de OnExit do txCodigo
			}
		});
		txCodigo.setBounds(79, 38, 86, 20);
		contentPane.add(txCodigo);
		txCodigo.setColumns(10);
		
		lbNome = new JLabel("Nome");
		lbNome.setBounds(10, 69, 59, 14);
		contentPane.add(lbNome);
		
		txNome = new JTextField();
		txNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txNome.setBackground(Color.WHITE);
			}
		});
		txNome.setEditable(false);
		txNome.setColumns(10);
		txNome.setBounds(79, 66, 281, 20);
		contentPane.add(txNome);
		
		lbIdade = new JLabel("Idade");
		lbIdade.setBounds(10, 97, 59, 14);
		contentPane.add(lbIdade);
		
		txIdade = new JTextField();
		txIdade.setEditable(false);
		txIdade.setColumns(10);
		txIdade.setBounds(79, 94, 86, 20);
		contentPane.add(txIdade);
		
		lbEmail = new JLabel("E-mail");
		lbEmail.setBounds(10, 125, 59, 14);
		contentPane.add(lbEmail);
		
		txEmail = new JTextField();
		txEmail.setEditable(false);
		txEmail.setColumns(10);
		txEmail.setBounds(79, 122, 281, 20);
		contentPane.add(txEmail);
		
		btGravar = new JButton("Gravar");
		btGravar.setEnabled(false);
		btGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravar();				
			}
		});
		btGravar.setBounds(280, 153, 80, 23);
		contentPane.add(btGravar);
		
		btNovo = new JButton("Novo");
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				habilitarCampos(true);
			}
		});
		btNovo.setBounds(10, 153, 80, 23);
		contentPane.add(btNovo);
		
		btPesquisar = new JButton("Pesquisar");
		btPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencher();
			}
		});
		btPesquisar.setBounds(260, 9, 100, 23);
		contentPane.add(btPesquisar);
		
		btEditar = new JButton("Editar");
		btEditar.setEnabled(false);
		btEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarCampos(true);
			}
		});
		btEditar.setBounds(100, 153, 80, 23);
		contentPane.add(btEditar);
		
		lbPesquisar = new JLabel("Pesquisar");
		lbPesquisar.setBounds(10, 13, 59, 14);
		contentPane.add(lbPesquisar);
		
		txPesquisar = new JTextField();
		txPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					preencher();
	            }				
			}
		});
		txPesquisar.setToolTipText("Digite um c\u00F3digo para pesquisar");
		txPesquisar.setColumns(10);
		txPesquisar.setBounds(79, 10, 86, 20);
		contentPane.add(txPesquisar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}			
		});
		btExcluir.setEnabled(false);
		btExcluir.setBounds(190, 153, 80, 23);
		contentPane.add(btExcluir);
	}

	protected void excluir() {
		
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Deseja excluir o registro?","Confirmação",JOptionPane.YES_NO_OPTION)) {
			if (!"".equals(txCodigo.getText().trim())) {
				Long cod = Long.parseLong(txCodigo.getText().trim());
				try {
					service.excluir(cod);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
			}
		}
	}

	protected void preencher() {
		try {
			String sCod = txPesquisar.getText();
			if ("".equals(sCod.trim())) {
				throw new Exception("Campo código é obrigatório");
			}
			Long cod = Long.parseLong(sCod);				
			Pessoa p = service.buscarPorId(cod);
			if (p != null && p.getId() > 0) {
				this.txCodigo.setText(p.getId() +"");
				this.txNome.setText(p.getNome());
				this.txIdade.setText(Integer.toString(p.getIdade()));
				this.txEmail.setText(p.getEmail());	
				
//					txCodigo.setEditable(false);
				habilitarCampos(false);
			} else {
				limparCampos();
				throw new Exception("Pessoa não encontrada");
//					JOptionPane.showMessageDialog(this, "Pessoa não encontrada","Erro",JOptionPane.ERROR_MESSAGE);					
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	protected void gravar() {
		try {
			Pessoa p = null;
			if (!"".equals(txCodigo.getText().trim())) {
				p = service.buscarPorId(Long.parseLong(txCodigo.getText()));
			}
			if (p == null) {
				p = new Pessoa();
			}
			if (!"".equals(txNome.getText().trim())) {	
				p.setNome(txNome.getText());
			} else {
				txNome.setBackground(Color.RED);
				lbNome.setForeground(Color.RED);
				throw new Exception("Campo Nome é obrigatório!");
			}
			p.setEmail(txEmail.getText());
			if (!"".equals(txIdade.getText().trim())) {
				p.setIdade(Integer.parseInt(txIdade.getText()));
			}
			service.gravar(p);
			
			JOptionPane.showMessageDialog(this, "Registro gravado com sucesso!","Informação",JOptionPane.INFORMATION_MESSAGE);
			limparCampos();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	protected void limparCampos() {
		this.txCodigo.setText("");
		this.txNome.setText("");
		this.txIdade.setText("");
		this.txEmail.setText("");
	}

	protected void alerta() {
		//this = esta instância de objeto da classe FrmPessoa
		JOptionPane.showMessageDialog(this, "Funciona!");
	}
	
	protected void habilitarCampos(Boolean habilitar) {
		txNome.setEditable(habilitar);
		txIdade.setEditable(habilitar);
		txEmail.setEditable(habilitar);	
		btEditar.setEnabled(!habilitar && !"".equals(txCodigo.getText().trim()) && Long.parseLong(txCodigo.getText().trim()) > 0);
		btExcluir.setEnabled(btEditar.isEnabled());
		btGravar.setEnabled(habilitar);
		btNovo.setEnabled(!habilitar);
	}
}
