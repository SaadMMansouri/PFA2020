package com.emsi.iir5.pfa;

import com.emsi.iir5.pfa.dao.*;
import com.emsi.iir5.pfa.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PfaApplication implements CommandLineRunner {

    @Autowired
    private UtilisateurRepository userRepository;
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
        repositoryRestConfiguration.exposeIdsFor(Chauffeur.class);
        repositoryRestConfiguration.exposeIdsFor(Critere.class);
        repositoryRestConfiguration.exposeIdsFor(Controle.class);
        repositoryRestConfiguration.exposeIdsFor(Entretien.class);
        repositoryRestConfiguration.exposeIdsFor(RespEntretien.class);

        Utilisateur admin = new Utilisateur();
        admin.setNom("adminLastName");
        admin.setPrenom("adminFirstName");
        admin.setEmail("admin@admin.com");
        admin.setPassword("$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG"); //password : admin
        admin.setNumTel("0000000000");
        admin.setAdresse("N 2020 Lot ADMIN, AdminCity, Country");
        admin.setEtatConnexion(false);
        admin.setCreatedAt(new Date());
        admin.setUpdatedAt(new Date());
        if (!userRepository.findByEmail("admin@admin.com").isPresent()) userRepository.save(admin);

    }
}
