package com.ghofrane.chansons.entities;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Chanson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChanson;

    private String titreChanson;
    private String artiste;
    private Double dureeChanson;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateSortie;

    @ManyToOne
    @JoinColumn(name = "idGen")
    private Genre genre;

    @OneToMany(mappedBy = "chanson")
    private List<Image> images;

    private String imagePath;

    public Chanson() {}

    public Chanson(String titreChanson, String artiste, Double dureeChanson, Date dateSortie) {
        this.titreChanson = titreChanson;
        this.artiste = artiste;
        this.dureeChanson = dureeChanson;
        this.dateSortie = dateSortie;
    }

    public Long getIdChanson() { return idChanson; }
    public void setIdChanson(Long idChanson) { this.idChanson = idChanson; }

    public String getTitreChanson() { return titreChanson; }
    public void setTitreChanson(String titreChanson) { this.titreChanson = titreChanson; }

    public String getArtiste() { return artiste; }
    public void setArtiste(String artiste) { this.artiste = artiste; }

    public Double getDureeChanson() { return dureeChanson; }
    public void setDureeChanson(Double dureeChanson) { this.dureeChanson = dureeChanson; }

    public Date getDateSortie() { return dateSortie; }
    public void setDateSortie(Date dateSortie) { this.dateSortie = dateSortie; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public List<Image> getImages() { return images; }
    public void setImages(List<Image> images) { this.images = images; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    @Override
    public String toString() {
        return "Chanson [idChanson=" + idChanson + ", titreChanson=" + titreChanson +
               ", artiste=" + artiste + ", dureeChanson=" + dureeChanson +
               ", dateSortie=" + dateSortie + "]";
    }
}