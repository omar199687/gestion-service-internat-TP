package JavaBeans;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import BaseDonnée.Etudiantbd;
import BaseDonnée.Payementbd;
import BaseDonnée.Reclamationbd;
import BaseDonnée.Reservationbd;

public class Etudiant {
	private String nom;
	private String prenom;
	private String CIN;
	private int numero_chambre;
	private String battiement;
	private String type_chambre;
	public String getType_chambre() {
		return type_chambre;
	}
	public void setType_chambre(String type_chambre) {
		this.type_chambre = type_chambre;
	}
	private String sexe;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	public int getNumero_chambre() {
		return numero_chambre;
	}
	public void setNumero_chambre(int numero_chambre) {
		this.numero_chambre = numero_chambre;
	}
	public String getBattiement() {
		return battiement;
	}
	public void setBattiement(String battiement) {
		this.battiement = battiement;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	public Etudiant chargeretudiant(HttpServletRequest request) {
		Etudiantbd eb=new Etudiantbd();
		HttpSession session=request.getSession();
		System.out.println((String) session.getAttribute("login"));
		Etudiant e=eb.chercheretudiantlogin((String) session.getAttribute("login"));
		eb.closeconnection();
		return e;
	}
	public boolean connexion(HttpServletRequest request) {
		Etudiantbd et=new Etudiantbd();
		HttpSession session=request.getSession();
		boolean b=et.existlog((String)session.getAttribute("login"),(String)session.getAttribute("password"));
		et.closeconnection();
		return b;
	}
	public String reserverchambre(HttpServletRequest request) {
		HttpSession session=request.getSession();
		Reservationbd rbd=new Reservationbd();
		String resultat=  rbd.messagereservation((String) session.getAttribute("CIN"));
		rbd.closeconnection();
		return resultat;
		
	}
	public ArrayList<String> chercherreserve(HttpServletRequest request){
		Reservationbd rdb=new Reservationbd();
		ArrayList<String> resultat= rdb.listerchambre(request.getParameter("type"));
		rdb.closeconnection();
		return resultat;
	}
	public String reservationchambre(HttpServletRequest request) {
		HttpSession session=request.getSession();
		Reservationbd rbd=new Reservationbd();
		String resultat= rbd.reserverchambre(request.getParameter("chambre"),(String) session.getAttribute("CIN"));
		rbd.closeconnection();
		return resultat;
	}
	public String messagepayement(HttpServletRequest request) {
		Payementbd pb=new Payementbd();
		HttpSession session=request.getSession();
		String resultat= pb.messagepayement((String) session.getAttribute("CIN"));
		pb.closeconnection();
		return resultat;
	}
	public String effectuerpayement(HttpServletRequest request) {
		Payementbd pd=new Payementbd();
		HttpSession session=request.getSession();
		String resultat= pd.payer("depot", (String) session.getAttribute("CIN"), request.getParameter("fichier"), request.getParameter("tranche"));
		pd.closeconnection();
		return resultat;
		
	}
	public String effectuerreclamation(HttpServletRequest request) {
		Reclamationbd rbd=new Reclamationbd();
		HttpSession session=request.getSession();
		String resultat= rbd.reclamation((String) session.getAttribute("CIN"), request.getParameter("battiement"), request.getParameter("description"),"EN ATTENTE");
		rbd.closeconnection();
		return resultat;
		
	}
	public String inscription(HttpServletRequest request) {
		String resultat="";
		Etudiantbd e=new Etudiantbd();
		resultat=e.inscription(request.getParameter("CIN"), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("sexe"), request.getParameter("email"), request.getParameter("password"), request.getParameter("image"));
		e.closeconnection();
		return resultat;
		
	}
}
