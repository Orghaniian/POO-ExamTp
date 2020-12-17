import javax.management.relation.Role;
import java.util.*;

public class Canal implements Destinataire, Comparable{
    private Map<Rôle, List<Utilisateur>> mapping_role_utilisateurs;
    private Integer ordre;
    private List<Webhook> webhooks;
    private String nom;
    private Map<Rôle, List<Habilitation>> mapping_role_habilitations;
    private List<Message> historiques;

    public Canal() {
        mapping_role_utilisateurs = new HashMap<Rôle, List<Utilisateur>>();
        mapping_role_habilitations = new HashMap<Rôle, List<Habilitation>>();
        webhooks = new ArrayList<Webhook>();
        historiques = new ArrayList<Message>();
    }

    public Map<Rôle, List<Utilisateur>> getMapping_role_utilisateurs() {
        return mapping_role_utilisateurs;
    }

    public void setMapping_role_utilisateurs(Map<Rôle, List<Utilisateur>> mapping_role_utilisateurs) {
        this.mapping_role_utilisateurs = mapping_role_utilisateurs;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public List<Webhook> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(List<Webhook> webhooks) {
        this.webhooks = webhooks;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Map<Rôle, List<Habilitation>> getMapping_role_habilitations() {
        return mapping_role_habilitations;
    }

    public void setMapping_role_habilitations(Map<Rôle, List<Habilitation>> mapping_role_habilitations) {
        this.mapping_role_habilitations = mapping_role_habilitations;
    }

    public List<Message> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<Message> historiques) {
        this.historiques = historiques;
    }


    @Override
    public int compareTo(Object o) {
        Canal canal = (Canal) o;
        return this.getOrdre().compareTo(canal.getOrdre());
    }

    public void ecrireMessage(Utilisateur utilisateur, Message message) throws ActionNonAutoriseeException{
        List<Rôle> roles = new ArrayList<Rôle>();
        Boolean auth = false;
        //Récupération de la liste des rôles du serveur ayant l'habilitation ecriture
        Set<Map.Entry<Rôle, List<Habilitation>>> entries = mapping_role_habilitations.entrySet();
        for(Map.Entry<Rôle, List<Habilitation>> entry : entries){
            if(entry.getValue().contains(Habilitation.ECRITURE)){
                roles.add(entry.getKey());
            }
        }
        if(roles.isEmpty()) throw new ActionNonAutoriseeException();

        for(Map.Entry<Rôle, List<Utilisateur>> entry : mapping_role_utilisateurs.entrySet()){
            for(Rôle role : roles){
                if(entry.getKey().equals(role)  && entry.getValue().contains(utilisateur)){
                    auth = true;
                }
            }
        }

        if(auth){
            message.setAuteur(utilisateur);
            System.out.println(message.getAuteur().getPrenom() + " " + message.getAuteur().getNom() + ": " + message.getTexte());
            historiques.add(message);
        }else{
            throw new ActionNonAutoriseeException();
        }

    }
}