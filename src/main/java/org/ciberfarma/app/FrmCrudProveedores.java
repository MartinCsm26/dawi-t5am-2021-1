package org.ciberfarma.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.ciberfarma.modelo.Usuario;



import javax.swing.JTextArea;

public class FrmCrudUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_4;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
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
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(100, 25, 89, 23);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Listado");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listado();
			}
		});
		btnNewButton_1.setBounds(250, 25, 89, 23);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();
			}
		});
		btnBuscar.setBounds(400, 25, 89, 23);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(171, 72, 46, 14);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre :");
		lblNewLabel_1.setBounds(171, 108, 46, 14);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido :");
		lblNewLabel_2.setBounds(171, 144, 46, 14);
		
		JLabel lblNewLabel_3 = new JLabel("Clave");
		lblNewLabel_3.setBounds(171, 180, 46, 14);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha de nacimiento :");
		lblNewLabel_4.setBounds(171, 216, 115, 14);
		
		JLabel lblNewLabel_5 = new JLabel("Tipo :");
		lblNewLabel_5.setBounds(171, 252, 46, 14);
		
		JLabel lblNewLabel_6 = new JLabel("Estado :");
		lblNewLabel_6.setBounds(171, 288, 46, 14);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(280, 69, 146, 20);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(280, 105, 146, 20);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(280, 141, 146, 20);
		txtApellido.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(280, 177, 146, 20);
		textField_3.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(280, 249, 146, 20);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(280, 285, 146, 20);
		textField_6.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(280, 213, 146, 20);
		textField_4.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(txtCodigo);
		contentPane.add(lblNewLabel_1);
		contentPane.add(txtNombre);
		contentPane.add(lblNewLabel_2);
		contentPane.add(txtApellido);
		contentPane.add(lblNewLabel_3);
		contentPane.add(textField_3);
		contentPane.add(lblNewLabel_4);
		contentPane.add(textField_4);
		contentPane.add(lblNewLabel_5);
		contentPane.add(textField_5);
		contentPane.add(lblNewLabel_6);
		contentPane.add(textField_6);
		contentPane.add(btnRegistrar);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 393, 586, 241);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
	}
	void registrar() {
		String nombre= leerNombre();
		
	}

	

	void listado() {
		// Obtener un listado de los Usuarios
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAll", Usuario.class);
		//List<Usuario> lstUsuarios = consulta.getResultList();
		
		TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAllWithType", Usuario.class);
		consulta.setParameter("xtipo", 1);
		List<Usuario> lstUsuarios = consulta.getResultList();
		
		em.close();
		// pasar el listado a txt,..
		for (Usuario u : lstUsuarios) {
			txtS.append(u.getCodigo() + "\t" + u.getNombre() + "\t" + u.getApellido() + "\n");
		}	
	}

	
	void buscar() {
		int codigo = leerCodigo();
		//buscar en la tabla para obtener un usuario
		EntityManagerFactory fabrica =Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		Usuario u = em.find(Usuario.class, codigo);
		em.close();
		if(u==null) {
			aviso("Usuario " + codigo + " No existe!!!") ;
		}else {
			txtNombre.setText(u.getNombre());
			txtApellido.setText(u.getApellido());
		}
	}
	
	private void aviso(String msg) {

		// TODO Auto-generated method stub

		 JOptionPane.showMessageDialog(this, msg, "Aviso del Sistema", 2);
	}
	private int leerCodigo() {
		
		return Integer.parseInt(txtCodigo.getText());
	}
	private String leerNombre() {
		if(!txtNombre.getText().matches("")) {
			return null;
		}
		return txtNombre.getText();
		
	}
}
