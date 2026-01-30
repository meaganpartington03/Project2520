// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca

//Complete par :
// Meagan Partington - 300416906
// Anastasia Sadovskyy - 300426037

// this is the (incomplete) Resident class
public class Resident {
	
	private int residentID;
	private String firstname;
	private String lastname;
	private String[] rol;

	//attributs pour le jumelage MP
	private Program matchedProgram; //programme que le resident est jumeler MP
	private int matchedRank; //rang du resident dans le program jumeler MP
	
	// constructs a Resident
    public Resident(int id, String fname, String lname) {
	
		residentID= id;
		firstname= fname;
		lastname= lname;
		matchedProgram = null; //il y a aucun jumelage au debut MP
		matchedRank = -1; //il n'y a pas de rang si pas de jumelage MP
	}

    // the rol in order of preference
	public void setROL(String[] rol) {
		
		this.rol= rol;
	}

	//retourne l'identifiant du resident MP
	public int getResidentID() {
		return residentID;
	}

	//retourne le prenon MP
	public String getFirstname() {
		return firstname;
	}

	//retourne le nom de famille MP
	public String getLastname() {
		return lastname;
	}

	//retourne la liste des programmes preferes MP
	public String[] getROL() {
		return rol;
	}

	//retourne le programm que le resident est jumele, null si aucun MP
	public Program getMatchedProgram() {
		return matchedProgram;
	}

	//defnit le programme de jumelage pour le resident MP
	public void setMatchedProgram(Program program) {
		this.matchedProgram = program;
	}

	//retourne le rang du resident dans sont programme MP
	public int getMatchedRank() {
		return matchedRank;
	}

	//definit le rang du resident dans sont programme MP
	public void setMatchedRank(int rank) {
		this.matchedRank = rank;
	}
	
	// string representation
	public String toString() {
      
       return "["+residentID+"]: "+firstname+" "+ lastname+" ("+rol.length+")\n";	  
	}
}