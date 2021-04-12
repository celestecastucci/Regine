package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	
	private int N;
	private List<Integer>soluzione;
	/**
	 * METODO CHE RESTITUISCE LISTA
	 * @param N
	 * @return
	 */
	public List<Integer> risolvi(int N){
		this.N=N;
		
		//definisco ARRAYLIST PERCHE' SOTTO FACCIO .get
		//per la linkedlist aggiunge ritardo
		List<Integer>parziale= new ArrayList<Integer>();
		this.soluzione=null; // cosi se non trovo nulla mi ritorna null
		cerca(parziale,0);  //perchè al passo iniziale siamo a liv0
		return this.soluzione;
	}
	
	/**
	 * IL METODO E' UN BOOLEAN :cosi la prima soluzione che trovo va bene e mi stoppo!!!
	 * return true se ho soluzione oppure una delle chiamate ricorsive dice che lei ha in mano la soluzione
	 * return false se non ho soluzione e neanche le chiamate ricorsive, continuiamo a cercare
	 * @param parziale
	 * @param livello
	 * @return
	 */
	private boolean cerca(List<Integer>parziale, int livello) {
		if(livello==N) {
			// caso terminale
			this.soluzione=new ArrayList<>(parziale);
			return true;
		} else {
			for(int colonna=0; colonna<N; colonna++) {  //[0 6 4 7 ]
				// if la pos nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione 
				//ricorsione con metodo cerca e incremento il livello
			
			if(posValida(parziale,colonna)) {
				
				/*List<Integer>parzialeNuovo= new ArrayList<>(parziale);
				parzialeNuovo=parziale;
				parzialeNuovo.add(colonna);
				cerca(parzialeNuovo,livello+1);*/
				
				parziale.add(colonna);  //[0 6 4 7 xxxx] aggiungo colonna1
				boolean trovato=cerca(parziale,livello+1); 
				if(trovato)
					return true;
				parziale.remove(parziale.size()-1); //backtracking , poi tolgo1 perchè provo altre alternative
				
			}
			}
			return false;
		}
	}
	/**
	 * controllo se la posizione è valida
	 * @param parziale
	 * @param colonna
	 * @return
	 */
	private boolean posValida(List<Integer> parziale, int colonna) {
		int livello=parziale.size();
		
		//controlla se la posizione in cui sono è valida
		if(parziale.contains(colonna))
			return false;
		//controlla le diagonali: confronta la posizione (livello,colonna) con (riga,colonna) 
		//delle regine esistenti
		for(int r=0; r<livello; r++) {
			int c=parziale.get(r);
			
			if(r+c== livello+colonna || r-c==livello-colonna)
				return false;
			
		}
		return true;
	}
	
	
	
}
