package com.moodvie.business.facade;

import java.sql.Timestamp;

import com.moodvie.persistance.dao.SubscribeDao;
import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.Subscribe;

/**
 * SubscribeFacade est un singleton
 */
public class SubscribeFacade {
    private final SubscribeDao subscribeDao;
    private static SubscribeFacade instance;
    private Subscribe subscribe;


    private SubscribeFacade() {
        this.subscribeDao = AbstractDaoFactory.getFactory().getSubscribeDao();
    }

    public static synchronized SubscribeFacade getInstance() {
        if (instance == null) {
            instance = new SubscribeFacade();
        }
        return instance;
    } 
    /**
     * Cette méthode permet de créer un abonnement, elle doit être appelé à la suite de la création d'un utilisateur
     * 
     * @param startDate date de début de l'abonnement
     * @param isActive true si l'abonnement est actif, false sinon
     * @param typeOfSubscribeId id du type d'abonnement
     * @param userId id de l'utilisateur
     * 
     * @return true si l'abonnement a été créé, false sinon
     */
    public Boolean createSubscribe(Timestamp startDate, boolean isActive, int typeOfSubscribeId, int userId) {

        // Check if there is the user has already a subscribe
        if (subscribeDao.getSubscribe(userId) != null) {
            System.out.println("User already has a subscribe");
            return false;
        }

        Subscribe subscribe = new Subscribe(startDate, isActive, typeOfSubscribeId, userId);

        try{
            subscribeDao.addSubscribe(subscribe);
        } catch(Exception e){
            System.out.println(e);
            return false;
        }

        this.subscribe = subscribe;
        return true;
    }

    /**
     * Cette méthode permet de changer le type d'abonnement d'un utilisateur
     *
     * @param typeOfSubscribeId id du type d'abonnement
     * 
     * @return true si le type d'abonnement a été changé, false sinon
     */
    public Boolean changeSubscribe(int typeOfSubscribeId) {
        subscribe.setTypeOfSubscribeId(typeOfSubscribeId);

        try{
            subscribeDao.updateSubscribe(subscribe);
        } catch(Exception e){
            System.out.println(e);
            return false;
        }

        return true;
    }

    /**
     * Cette méthode permet d'arrêter un abonnement payant. Cela va rebasculer l'utilisateur sur un abonnement gratuit
     * 
     * @return true si l'abonnement a été arrêté, false sinon
     */
    public Boolean stopSubscribe() {
        boolean isSubscribeChange = this.changeSubscribe(1);
        return isSubscribeChange;
    }

    /**
     * Cette méthode permet de récupérer l'abonnement courant de l'utilisateur
     * 
     * @return l'abonnement courant ou null si aucun abonnement n'est actif
     */
    public Subscribe getSubscribe(){
        if(this.subscribe == null){
            return null;
        }

        return this.subscribe;
    }
}
