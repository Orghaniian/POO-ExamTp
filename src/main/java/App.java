import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("Dutrou");
        utilisateur.setPrenom("Paul");
        utilisateur.setMail("Paul.Dutrou@email.fr");
        utilisateur.setLogin("Paul72");
        utilisateur.setMotDePasse("supermdp");

        ServeurDiscussion serveur = new ServeurDiscussion();
        serveur.getCanaux().add(new Canal());
        Rôle role = new Rôle("Membre");
        serveur.getMapping_role_habilitations().put(role,
                new ArrayList<Habilitation>(Arrays.asList(Habilitation.LECTURE, Habilitation.ECRITURE)));
        serveur.getMapping_role_utilisateurs().put(role, new ArrayList<Utilisateur>(Collections.singletonList(utilisateur)));

        serveur.getCanaux().get(0).setMapping_role_habilitations(serveur.getMapping_role_habilitations());
        serveur.getCanaux().get(0).setMapping_role_utilisateurs(serveur.getMapping_role_utilisateurs());

        utilisateur.getServeurs().add(serveur);



        Message message = new Message();
        message.setTexte("Test message");
        try {
            serveur.getCanaux().get(0).ecrireMessage(utilisateur, message);
        } catch (ActionNonAutoriseeException e) {
            e.printStackTrace();
        }


    }
}
