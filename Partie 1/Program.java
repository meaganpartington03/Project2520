// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca
//Completer par: Meagan Partington - 300416906

// this is the (incomplete) Program class
public class Program {
	
	private String programID;
	private String name;
	private int quota;
	private int[] rol;

	//attribut pour le jumelage MP
	private ArrayList<Resident> matchedResidents; //listes des residents actuellement jumeler MP
	
	// constructs a Program
    public Program(String id, String n, int q) {
	
		programID= id;
		name= n;
		quota= q;
		matchedResidents = new ArrayList<Resident>(); //initialise la liste vide MP
	}

    // the rol in order of preference
	public void setROL(int[] rol) {
		
		this.rol= rol;
	}

	//retourne l'identifiant du programme MP
	public String getProgramID() {
		return programID;
	}

	//retourne le nom complet du programme MP
	public String getName() {
		return name;
	}

	//retourne le quota, donc nombre max de residents MP
	public int getQuota() {
		return quota;
	}

	//retourne la liste des residents preferes MP
	public int[] getROL() {
		return rol;
	}

	//retourne la liste des residents actuellement jumeler MP 
	public ArrayList<Resident> getMatchedResidents() {
		return matchedResidents;
	}

	//verifie si un resident fait partie de la liste de preference du programme MP
	public boolean member(int residentID) {
		//parcourt toute la liste de preference MP
		for (int i = 0; i < rol.lengthID) {
			//si on trouver l'ID le resident est dans la liste MP
			if (rol[i] == residentID) {
				return true;
			}
		}
		//si on n'as pas trouver l'id le resident n'est pas dans la liste MP
		return false;
	}
	
	//retourne le rang d'un resident dans la liste de preference MP
	public int rank(int residentID) {
		//parcourt toute la liste de preference MP
		for (int i = 0; i < rol.length; i++) {
			//si on trouve l'id retourne sa position MP
			if (rol[i] == residentID) {
				return i; //la position est le rang MP
			}
		}
		//si on n'as pas trouver l'ID retourne -1 pour demontrer ceci MP
		return -1;
	}

	//trouve le resident jumeler qui est dans le pire rang
	public Resident leastPreferred() {
		//si aucun resident n'est jumele sa retourne null MP
		if (matchedResidents.isEmpty()) {
			return null;
		}
		//commence avec le premier resident comme le moins preferer MP
		Resident worst = matchedResidents.get(0);
		int worstRank = rank(worst.getResidentID());

		//parcourt tous les residents jumelers MP
		for (int i = 1; i < matchedResidents.size(); i++) {
			Resident current = matchedResidents.get(i);
			int currentRank = rank(current.getResidentID());

			//si ce resiednt a un pire rang c'est le nouveau pire MP
			if (currentRank > worstRank) {
				worst = current;
				worstRank = currentRank;
			}
		}
		return worst;
	}




	// string representation
	public String toString() {
      
       return "["+programID+"]: "+name+" {"+ quota+ "}" +" ("+rol.length+")";	  
	}
}