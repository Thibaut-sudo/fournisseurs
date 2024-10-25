import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int MAX_FOURNISSEURS = 50;
    private static ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
    private static int dernierId = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterFournisseur(scanner);
                    break;
                case 2:
                    listerFournisseurs();
                    break;
                case 3:
                    rechercherFournisseurParNumero(scanner);
                    break;
                case 4:
                    rechercherFournisseurParNom(scanner);
                    break;
                case 5:
                    modifierFournisseur(scanner);
                    break;
                case 6:
                    supprimerFournisseur(scanner);
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 0);

        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Ajouter un fournisseur");
        System.out.println("2. Lister les fournisseurs");
        System.out.println("3. Rechercher un fournisseur par numéro");
        System.out.println("4. Rechercher un fournisseur par nom");
        System.out.println("5. Modifier les coordonnées d'un fournisseur");
        System.out.println("6. Supprimer un fournisseur");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void ajouterFournisseur(Scanner scanner) {
        if (fournisseurs.size() >= MAX_FOURNISSEURS) {
            System.out.println("Impossible d'ajouter un autre fournisseur. Nombre maximum atteint.");
            return;
        }

        System.out.print("Nom du fournisseur : ");
        String nom = scanner.nextLine().trim();
        if (nom.isEmpty() || fournisseurExiste(nom)) {
            System.out.println("Nom invalide ou déjà utilisé.");
            return;
        }

        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine().trim();

        System.out.print("Email : ");
        String email = scanner.nextLine().trim();

        System.out.print("Commande en attente (true/false) : ");
        boolean commande = scanner.nextBoolean();
        scanner.nextLine(); // Consomme la nouvelle ligne

        Fournisseur fournisseur = new Fournisseur(++dernierId, nom, telephone, email, commande);
        fournisseurs.add(fournisseur);
        System.out.println("Fournisseur ajouté avec succès.");
    }

    private static boolean fournisseurExiste(String nom) {
        return fournisseurs.stream().anyMatch(f -> f.getNom().equalsIgnoreCase(nom));
    }

    private static void listerFournisseurs() {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }

        System.out.println("\nListe des fournisseurs :");
        for (Fournisseur f : fournisseurs) {
            System.out.println("ID: " + f.getId() + ", Nom: " + f.getNom());
        }
    }

    private static void rechercherFournisseurParNumero(Scanner scanner) {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }

        System.out.print("Numéro du fournisseur : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne

        Fournisseur fournisseur = fournisseurs.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
        if (fournisseur != null) {
            System.out.println(fournisseur);
        } else {
            System.out.println("Fournisseur introuvable.");
        }
    }

    private static void rechercherFournisseurParNom(Scanner scanner) {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }

        System.out.print("Nom du fournisseur : ");
        String nom = scanner.nextLine().trim();

        Fournisseur fournisseur = fournisseurs.stream().filter(f -> f.getNom().equalsIgnoreCase(nom)).findFirst().orElse(null);
        if (fournisseur != null) {
            System.out.println(fournisseur);
        } else {
            System.out.println("Fournisseur introuvable.");
        }
    }

    private static void modifierFournisseur(Scanner scanner) {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }

        System.out.print("Numéro du fournisseur à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne

        Fournisseur fournisseur = fournisseurs.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
        if (fournisseur == null) {
            System.out.println("Fournisseur introuvable.");
            return;
        }

        System.out.println(fournisseur);
        System.out.println("\n--- Modifier ---");
        System.out.println("1. Nom");
        System.out.println("2. Téléphone");
        System.out.println("3. Email");
        System.out.println("4. Commande");
        System.out.println("5. Confirmer");
        System.out.println("6. Annuler");
        System.out.print("Votre choix : ");

        int choix = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne

        switch (choix) {
            case 1:
                System.out.print("Nouveau nom : ");
                String nouveauNom = scanner.nextLine().trim();
                if (!nouveauNom.isEmpty() && !fournisseurExiste(nouveauNom)) {
                    fournisseur.setNom(nouveauNom);
                    System.out.println("Nom mis à jour.");
                } else {
                    System.out.println("Nom invalide ou déjà utilisé.");
                }
                break;
            case 2:
                System.out.print("Nouveau téléphone : ");
                fournisseur.setTelephone(scanner.nextLine().trim());
                System.out.println("Téléphone mis à jour.");
                break;
            case 3:
                System.out.print("Nouvel email : ");
                fournisseur.setEmail(scanner.nextLine().trim());
                System.out.println("Email mis à jour.");
                break;
            case 4:
                System.out.print("Commande en attente (true/false) : ");
                fournisseur.setCommande(scanner.nextBoolean());
                scanner.nextLine(); // Consomme la nouvelle ligne
                System.out.println("Commande mise à jour.");
                break;
            case 5:
                System.out.println("Modifications enregistrées.");
                break;
            case 6:
                System.out.println("Modifications annulées.");
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }

    private static void supprimerFournisseur(Scanner scanner) {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }

        System.out.print("Numéro du fournisseur à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne

        Fournisseur fournisseur = fournisseurs.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
        if (fournisseur == null || fournisseur.isCommande()) {
            System.out.println("Fournisseur introuvable ou commande en cours.");
            return;
        }

        System.out.println(fournisseur);
        System.out.print("Confirmer la suppression (oui/non) : ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("oui")) {
            fournisseurs.remove(fournisseur);
            System.out.println("Fournisseur supprimé avec succès.");
        } else {
            System.out.println("Suppression annulée.");
        }
    }
}
