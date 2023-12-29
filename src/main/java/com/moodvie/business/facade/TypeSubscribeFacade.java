package com.moodvie.business.facade;

import java.util.ArrayList;

import com.moodvie.persistance.dao.TypeOfSubscribeDao;
import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.TypeOfSubscribe;

/**
 * TypeSubscribeFacade est un singleton
 */
public class TypeSubscribeFacade {
    private final TypeOfSubscribeDao typeOfSubscribeDao;
    private static TypeSubscribeFacade instance;
    private ArrayList<TypeOfSubscribe> typeOfSubscribeList;

    private TypeSubscribeFacade() {
        // This will drop and recreate the table every time a new instance of the facade is created
        this.typeOfSubscribeDao = AbstractDaoFactory.getFactory().getTypeOfSubscribeDao();
        typeOfSubscribeList = new ArrayList<>();
    }

    public static synchronized TypeSubscribeFacade getInstance() {
        if (instance == null) {
            instance = new TypeSubscribeFacade();
        }
        return instance;
    }

    /**
     * Cette méthode permet de créer un type d'abonnement. 
     * Elle doit être appelée (plusieurs fois, pour créer tous les types d'abonnements) 
     * avant la création de tout abonnement.
     *
     * @param label label du type d'abonnement
     * @param price prix du type d'abonnement
     * @param numberOfDays nombre de jours du type d'abonnement
     * @param features liste des fonctionnalités du type d'abonnement
     *
     * @return true si le type d'abonnement a été créé, false sinon
     */
    public Boolean createTypeOfSubscribe(String label, double price, int numberOfDays, ArrayList<String> features) {

        // Check if there is already a type of subscribe with this label
        for (TypeOfSubscribe typeOfSubscribe : typeOfSubscribeList) {
            if (typeOfSubscribe.getLabel().equals(label)) {
                System.out.println("Type of subscribe already exists");
                return false;
            }
        }

        TypeOfSubscribe typeOfSubscribe = new TypeOfSubscribe(label, price, numberOfDays);
        // Convert the features list to an array and set it to the type of subscribe
        String[] featuresArray = new String[features.size()];
        featuresArray = features.toArray(featuresArray);
        typeOfSubscribe.setFeatures(featuresArray);

        // Change the id of the type of subscribe depending of the label
        switch (label) {
            case "Basique":
                typeOfSubscribe.setId(1);
                break;
            case "Mensuel":
                typeOfSubscribe.setId(2);
                break;
            case "Annuel":
                typeOfSubscribe.setId(3);
                break;
            default:
                System.out.println("Type of subscribe not found");
                return false;
        }
        
        try {
            typeOfSubscribeDao.addTypeOfSubscribe(typeOfSubscribe);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        typeOfSubscribeList.add(typeOfSubscribe);
        return true;
    }

    /**
     * Cette méthode permet de lister tous les types d'abonnements
     *
     * @return la liste des types d'abonnements
     */
    public ArrayList<TypeOfSubscribe> getListTypeOfSubscribe() {
        return typeOfSubscribeList;
    }

    /**
     * Cette méthode permet de récupérer un type d'abonnement en fonction de son id
     *
     * @param id id du type d'abonnement
     *
     * @return le type d'abonnement
     */
    public TypeOfSubscribe getTypeOfSubscribe(int id) {
        for (TypeOfSubscribe typeOfSubscribe : typeOfSubscribeList) {
            if (typeOfSubscribe.getId() == id) {
                return typeOfSubscribe;
            }
        }
        return null;
    }
}
