package beans;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Sylvain on 24/04/2017.
 */
@Entity
@Table(name = "etudiant", schema = "sr03_project", catalog = "")
public class EtudiantEntity {
    private String nom;
    private String prenom;
    private String mail;
    private byte[] photo;
    private String semestre;
    private String cursus;
    private String niveau;
    private int idEtudiant;
    private Collection<ParticipationEntity> participationsByIdEtudiant;

    @Basic
    @Column(name = "Nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "Prenom")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "Mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "Photo")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "Semestre")
    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    @Basic
    @Column(name = "Cursus")
    public String getCursus() {
        return cursus;
    }

    public void setCursus(String cursus) {
        this.cursus = cursus;
    }

    @Basic
    @Column(name = "Niveau")
    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Id
    @Column(name = "IdEtudiant")
    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EtudiantEntity that = (EtudiantEntity) o;

        if (idEtudiant != that.idEtudiant) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (!Arrays.equals(photo, that.photo)) return false;
        if (semestre != null ? !semestre.equals(that.semestre) : that.semestre != null) return false;
        if (cursus != null ? !cursus.equals(that.cursus) : that.cursus != null) return false;
        if (niveau != null ? !niveau.equals(that.niveau) : that.niveau != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + (semestre != null ? semestre.hashCode() : 0);
        result = 31 * result + (cursus != null ? cursus.hashCode() : 0);
        result = 31 * result + (niveau != null ? niveau.hashCode() : 0);
        result = 31 * result + idEtudiant;
        return result;
    }

    @OneToMany(mappedBy = "etudiantByEtu")
    public Collection<ParticipationEntity> getParticipationsByIdEtudiant() {
        return participationsByIdEtudiant;
    }

    public void setParticipationsByIdEtudiant(Collection<ParticipationEntity> participationsByIdEtudiant) {
        this.participationsByIdEtudiant = participationsByIdEtudiant;
    }
}
