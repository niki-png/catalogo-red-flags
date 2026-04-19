package com.redflags.catalogo.config;

import com.redflags.catalogo.entity.RedFlag;
import com.redflags.catalogo.repository.RedFlagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RedFlagRepository repository;

    public DataInitializer(RedFlagRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Popola il database solo se vuoto
        if (repository.count() == 0) {
            RedFlag flag1 = new RedFlag(
                    "Mi ignora quando esce con gli amici",
                    "Quando esciamo insieme e incontra i suoi amici mi ignora completamente",
                    "crush",
                    "relazione",
                    "high"
            );
            
            RedFlag flag2 = new RedFlag(
                    "Legge i messaggi e non risponde",
                    "Vede i miei messaggi su WhatsApp ma non mi risponde per ore",
                    "amico/a",
                    "chat",
                    "medium"
            );
            
            repository.save(flag1);
            repository.save(flag2);
            
            System.out.println("✅ Database inizializzato con dati di esempio");
        }
    }
}
