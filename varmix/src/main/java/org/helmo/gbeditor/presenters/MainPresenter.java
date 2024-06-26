package org.helmo.gbeditor.presenters;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
	
	private List<PresenterInterface> allPresenters;// = new ArrayList<PresenterInterface>();
	
	/**
	 * Constructeur
	 * @param allPresenters2 
	 * @param allPresenters
	 * @param mainView
	 */
	public MainPresenter(List<PresenterInterface> allPresenters2) {
		this.allPresenters = new ArrayList<PresenterInterface>(allPresenters2);
	}
	
	/**
	 * Permet d'ajouter un presenter à la liste 
	 * des tous les presenters
	 * @param presenter un presenter implémentant PresenterInterface
	 */
	public void add(PresenterInterface presenter) {
		allPresenters.add(presenter);
	}
	

}
