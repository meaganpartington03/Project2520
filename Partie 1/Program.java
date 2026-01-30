// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca

//Complete par :
// Meagan Partington - 300416906
// Anastasia Sadovskyy - 300426037

import java.util.ArrayList;

// this is the (incomplete) Program class
public class Program {
	
	private String programID;
	private String name;
	private int quota;
	private int[] rol;

	//attribut pour le jumelage MP
	private ArrayList<Resident> matchedResidents; //listes des residents actuellement jumeler 
	
	// constructs a Program
    public Program(String id, String n, int q) {
	
		programID= id;
		name= n;
		quota= q;
		matchedResidents = new ArrayList<Resident>(); //initialise la liste vide 
	}

    // the rol in order of preference
	public void setROL(int[] rol) {
		
		this.rol= rol;
	}

	//retourne l'identifiant du programme 
	public String getProgramID() {
		return programID;
	}

	//retourne le nom complet du programme 
	public String getName() {
		return name;
	}

	//retourne le quota, donc nombre max de residents 
	public int getQuota() {
		return quota;
	}

	//retourne la liste des residents preferes 
	public int[] getROL() {
		return rol;
	}

	//retourne la liste des residents actuellement jumeler 
	public ArrayList<Resident> getMatchedResidents() {
		return matchedResidents;
	}

	//verifie si un resident fait partie de la liste de preference du programme 
	public boolean member(int residentID) {
		//parcourt toute la liste de preference 
		for (int i = 0; i < rol.length; i++) {
			//si on trouver l'ID le resident est dans la liste 
			if (rol[i] == residentID) {
				return true;
			}
		}
		//si on n'as pas trouver l'id le resident n'est pas dans la liste 
		return false;
	}
	
	//retourne le rang d'un resident dans la liste de preference 
	public int rank(int residentID) {
		//parcourt toute la liste de preference 
		for (int i = 0; i < rol.length; i++) {
			//si on trouve l'id retourne sa position 
			if (rol[i] == residentID) {
				return i; //la position est le rang
			}
		}
		//si on n'as pas trouver l'ID retourne -1 pour demontrer ceci 
		return -1;
	}

	//trouve le resident jumeler qui est dans le pire rang 
	public Resident leastPreferred() {
		//si aucun resident n'est jumele sa retourne null 
		if (matchedResidents.isEmpty()) {
			return null;
		}
		//commence avec le premier resident comme le moins preferer 
		Resident worst = matchedResidents.get(0);
		int worstRank = rank(worst.getResidentID());

		//parcourt tous les residents jumelers 
		for (int i = 1; i < matchedResidents.size(); i++) {
			Resident current = matchedResidents.get(i);
			int currentRank = rank(current.getResidentID());

			//si ce resiednt a un pire rang c'est le nouveau pire 
			if (currentRank > worstRank) {
				worst = current;
				worstRank = currentRank;
			}
		}
		return worst;
	}

	//essaie d'ajouter un resident au programme, l'ajoute si le quote n'est pas atteint ou remplace le resident le moins preferer 
	public void addResident(Resident resident) {
		//verifie si le resident est dans la liste de preference du programME 
		if (!member(resident.getResidentID())) {
			//si le resident n'est pas dans le ROL on ne peut pas le jumeler 
			return;
		}

		//si le programme n'as pas encore atteint son quota 
		if (matchedResidents.size() < quota) {
			//ont peut ajouter le resident
			matchedResidents.add(resident);
			resident.setMatchedProgram(this); //update le jumelage du resident 
			resident.setMatchedRank(rank(resident.getResidentID())); //enregistre sont rang 
		}

		//si le quota est atteint il faut donc verifier si on peut remplacer quelqu'un 
		else {
			Resident worst = leastPreferred();
			int worstRank = rank(worst.getResidentID());
			int newRank = rank(resident.getResidentID());
			//si le nouveau resident a un meilleur rang AKA nombre plus petit 
			if (newRank < worstRank) {
				//retire le resident le moin preferer 
				matchedResidents.remove(worst);
				worst.setMatchedProgram(null); //le resident retirer n'as plus de jumelage 
				worst.setMatchedRank(-1);
				//ont ajoute le nouveau resident 
				matchedResidents.add(resident);
				resident.setMatchedProgram(this);
				resident.setMatchedRank(newRank);
			}
			//si le nouveau resident n'as pas un meilleur rang on fait rien car le nouveau resident n'est pas ajouter 
		}
	}

	// compare program ROL with residents ID
	public int compareProgramROLwithResID(Resident r, int[] ROL){

		for(int i = 0; i < rol.length; i++){
			if(r.getResidentID() == rol[i]){
				return i;
			}
		}

		return -1;

	}


	// string representation
	public String toString() {
      
       return "["+programID+"]: "+name+" {"+ quota+ "}" +" ("+rol.length+")\n";	  
	}
}