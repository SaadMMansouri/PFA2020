package com.emsi.iir5.pfa;

import com.emsi.iir5.pfa.dao.*;
import com.emsi.iir5.pfa.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class PfaApplication implements CommandLineRunner {

    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private TrajetRepository trajetRepository;
    @Autowired
    private TrajetVilleRepository trajetVilleRepository;

    @Autowired
    private ChauffeurRepository chauffeurRepository;
    @Autowired
    private VoyageRepository voyageRepository;


    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(PfaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //pour affichier les ids
        repositoryRestConfiguration.exposeIdsFor(Vehicule.class);
        repositoryRestConfiguration.exposeIdsFor(Ville.class);
        repositoryRestConfiguration.exposeIdsFor(Trajet.class);
        repositoryRestConfiguration.exposeIdsFor(Trajetville.class);
        repositoryRestConfiguration.exposeIdsFor(Utilisateur.class);
        repositoryRestConfiguration.exposeIdsFor(Voyage.class);

        /*
        vehiculeRepository.save(new Vehicule(null,"M1221","Honda","Active","Auto",5));
        vehiculeRepository.save(new Vehicule(null,"M5841","Dacia","Non Active","Auto",6));
        vehiculeRepository.save(new Vehicule(null,"SQ651","VAIS","Active","Car",50));
        vehiculeRepository.save(new Vehicule(null,"F5652","XRS","Active","Auto",5));

        villeRepository.save(new Ville(null,"KECH","87,5745","87,5745",null));
        villeRepository.save(new Ville(null,"FES","85,9626","87,5132",null));
        villeRepository.save(new Ville(null,"RABAT","57,5120","87,4153",null));
        villeRepository.save(new Ville(null,"CASA","96,5328","87,9855",null));

        trajetRepository.save(new Trajet(null,"trajet n1","allez-retour",null,null));
        Trajet trj = new Trajet(null,"trajet n2","allez",null,null);
        trajetRepository.save(trj);

        Trajetville tv = new Trajetville();
        TrajetvillePK  pk = new TrajetvillePK();
        pk.setIdTrajet(trajetRepository.findById(1).get().getIdTrajet());
        pk.setIdVille(villeRepository.findById(1).get().getIdVille());
        tv.setId(pk);
        tv.setTypeVille("escale");
        trajetVilleRepository.save(tv);

        Chauffeur ch = new Chauffeur();
        ch.setIdUtilisateur(null);
        ch.setNom("nom"); ch.setPrenom("prenom"); ch.setEmail("email");
        ch.setNumTel("5132"); ch.setNumTel2("684554"); ch.setAdresse("adresse"); ch.setPassword("password");
        ch.setEtatConnexion(false); ch.setDisponible(true);
        ch.setUpdatedAt(new Date()); ch.setCreatedAt(new Date());
        chauffeurRepository.save(ch);

        Voyage voy = new Voyage();
        voy.setChauffeur(ch);
        voy.setTrajet(trajetRepository.findById(1).get());
        voy.setDateVoyage(new Date());
        voy.setVehicule(vehiculeRepository.findById(1).get());
        voyageRepository.save(voy);

        vehiculeRepository.findAll().forEach(vehicule -> {
            System.out.println(vehicule.toString());
        });
    */
    }
}
