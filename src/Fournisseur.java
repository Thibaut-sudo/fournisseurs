public class Fournisseur {
    private int id;
    private String nom;
    private String telephone;
    private String email;
    private boolean commande;

    public Fournisseur(int id, String nom, String telephone, String email, boolean commande) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.commande = commande;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCommande() {
        return commande;
    }

    public void setCommande(boolean commande) {
        this.commande = commande;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nom: " + nom + ", Téléphone: " + telephone + ", Email: " + email + ", Commande en attente: " + (commande ? "Oui" : "Non");
    }
}
