package model;

import java.util.Observer;
/**
 * @author Jerome Nicolas; Alexandre Ravaux
 *
 */
public interface ICompte {
	
	public void setSolde(double solde);
	public void addObserver(Observer o);

}
