public class Message {
    private Utilisateur auteur;
    private Destinataire destinaire;
    private String texte;

    public Utilisateur getAuteur() {
        return auteur;
    }

    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }

    public Destinataire getDestinaire() {
        return destinaire;
    }

    public void setDestinaire(Destinataire destinaire) {
        this.destinaire = destinaire;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
