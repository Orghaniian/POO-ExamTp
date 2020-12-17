import java.util.Objects;

public class Rôle {
    private String nomRole;

    public Rôle(String nomRole) {
        this.nomRole = nomRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this.nomRole == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rôle role = (Rôle) o;
        return Objects.equals(nomRole, role.nomRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomRole);
    }
}
