package forma.registracija;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.domen.Profil;
import com.comtrade.forma.admin.AdminForma;
import com.comtrade.forma.profil.ProfilForma;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.Konstante;
import com.comtrade.transfer.TransferKlasa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.color.ProfileDataException;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.awt.event.ActionEvent;

public class FormaRegistracija extends JFrame {

	private JPanel contentPane;
	private JTextField tfKorisnickoImePrijava;
	private JPasswordField pfLozinkaPrijava;
	private JTextField tfKorisnicko;
	private JPasswordField pfLozinka;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfeAdresa;
	private JTextField tfZanimanje;
	private JTextField tfDatumRodjenja;
	private JComboBox cbPol;
	private String pol;
	private int status =1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormaRegistracija frame = new FormaRegistracija();
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
	public FormaRegistracija() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKorisnickoImePrijava = new JLabel("Korisnicko Ime");
		lblKorisnickoImePrijava.setBounds(247, 25, 83, 16);
		contentPane.add(lblKorisnickoImePrijava);
		
		tfKorisnickoImePrijava = new JTextField();
		tfKorisnickoImePrijava.setBounds(247, 57, 116, 22);
		contentPane.add(tfKorisnickoImePrijava);
		tfKorisnickoImePrijava.setColumns(10);
		
		JLabel lblLozinkaPrijava = new JLabel("Lozinka");
		lblLozinkaPrijava.setBounds(417, 25, 56, 16);
		contentPane.add(lblLozinkaPrijava);
		
		JButton btnPrijava = new JButton("Prijava");
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String korisnickoImePrijava = tfKorisnickoImePrijava.getText();
				String lozinkaPrijava = pfLozinkaPrijava.getText();
				
				if(korisnickoImePrijava.equals("admin") && lozinkaPrijava.equals("admin")) {
					
					AdminForma af = new AdminForma();
					af.setVisible(true);
				}
				else {
				
				Profil p = new Profil();
				p.setKorisnickoIme(korisnickoImePrijava);
				p.setLozinka(lozinkaPrijava);
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.LOGOVANJE);
				tk.setKlijentObjekat(p);
				Komunikacija.vratiKomunikaciju().posalji(tk);
				
				try {
					TransferKlasa tk1 = Komunikacija.vratiKomunikaciju().procitaj();
					if(tk1.getOperacija() == Konstante.USPESNA_OPERACIJA) {
						
						JOptionPane.showMessageDialog(null,tk1.getServerPorukaOdgovor());
						ProfilForma pf = new ProfilForma((Profil) tk1.getServerObjekat());
						pf.setVisible(true);
					}
					else {
						
						JOptionPane.showMessageDialog(null,tk1.getServerPorukaOdgovor());
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				}
			}
		});
		btnPrijava.setBounds(584, 56, 97, 25);
		contentPane.add(btnPrijava);
		
		pfLozinkaPrijava = new JPasswordField();
		pfLozinkaPrijava.setBounds(406, 57, 122, 22);
		contentPane.add(pfLozinkaPrijava);
		
		JLabel lblNewLabel = new JLabel("Registrujte se");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(76, 109, 172, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Korisnicko Ime");
		lblNewLabel_1.setBounds(22, 172, 89, 16);
		contentPane.add(lblNewLabel_1);
		
		tfKorisnicko = new JTextField();
		tfKorisnicko.setBounds(123, 169, 116, 22);
		contentPane.add(tfKorisnicko);
		tfKorisnicko.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Lozinka");
		lblNewLabel_2.setBounds(22, 226, 56, 16);
		contentPane.add(lblNewLabel_2);
		
		pfLozinka = new JPasswordField();
		pfLozinka.setBounds(123, 223, 116, 22);
		contentPane.add(pfLozinka);
		
		JLabel lblNewLabel_3 = new JLabel("Ime");
		lblNewLabel_3.setBounds(22, 272, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Prezime");
		lblNewLabel_4.setBounds(22, 313, 56, 16);
		contentPane.add(lblNewLabel_4);
		
		tfIme = new JTextField();
		tfIme.setBounds(123, 269, 116, 22);
		contentPane.add(tfIme);
		tfIme.setColumns(10);
		
		tfPrezime = new JTextField();
		tfPrezime.setBounds(123, 310, 116, 22);
		contentPane.add(tfPrezime);
		tfPrezime.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("e-adresa");
		lblNewLabel_5.setBounds(317, 172, 65, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Zanimanje");
		lblNewLabel_6.setBounds(317, 226, 83, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Datum Rodjenja");
		lblNewLabel_7.setBounds(317, 272, 97, 16);
		contentPane.add(lblNewLabel_7);
		
		tfeAdresa = new JTextField();
		tfeAdresa.setBounds(430, 169, 116, 22);
		contentPane.add(tfeAdresa);
		tfeAdresa.setColumns(10);
		
		tfZanimanje = new JTextField();
		tfZanimanje.setBounds(430, 223, 116, 22);
		contentPane.add(tfZanimanje);
		tfZanimanje.setColumns(10);
		
		tfDatumRodjenja = new JTextField();
		tfDatumRodjenja.setBounds(430, 269, 116, 22);
		contentPane.add(tfDatumRodjenja);
		tfDatumRodjenja.setColumns(10);
		
		cbPol = new JComboBox();
		cbPol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pol = (String) cbPol.getSelectedItem();
			}
		});
		cbPol.setBounds(430, 310, 116, 22);
		contentPane.add(cbPol);
		
		JLabel lblPol = new JLabel("Pol");
		lblPol.setBounds(317, 313, 56, 16);
		contentPane.add(lblPol);
		
		JButton btnRegistracija = new JButton("Registracija");
		btnRegistracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String korisnickoIme = tfKorisnicko.getText();
				String lozinka = pfLozinka.getText();
				String ime = tfIme.getText();
				String prezime = tfPrezime.getText();
				String eAdresa = tfeAdresa.getText();
				String zanimanje = tfZanimanje.getText();
				String datumRodjenja = tfDatumRodjenja.getText();
				
				Profil p = new Profil(0, korisnickoIme, lozinka, ime, prezime,eAdresa, status,zanimanje, datumRodjenja, pol);
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.SACUVAJ_PROFIL);
				tk.setKlijentObjekat(p);
				Komunikacija.vratiKomunikaciju().posalji(tk);
				try {
					TransferKlasa tfk = Komunikacija.vratiKomunikaciju().procitaj();
					if(tfk.getOperacija() == Konstante.USPESNA_OPERACIJA) {
						JOptionPane.showMessageDialog(null,tfk.getServerPorukaOdgovor());
						
						Profil pr = (Profil) tfk.getServerObjekat();
						ProfilForma pf = new ProfilForma(pr);
						pf.setVisible(true);
						
						
					}
					JOptionPane.showMessageDialog(null,tfk.getServerPorukaOdgovor());
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnRegistracija.setBounds(266, 364, 134, 25);
		contentPane.add(btnRegistracija);
		cbPol.addItem("M");
		cbPol.addItem("Z");
	}
}
