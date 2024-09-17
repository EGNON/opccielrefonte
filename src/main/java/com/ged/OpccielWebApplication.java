package com.ged;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ged.dao.security.UtilisateurDao;
import com.ged.service.opcciel.OpcvmService;
import com.ged.service.standard.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
@Component
@EnableAsync
//@ConfigurationProperties(prefix = "spring.mail")
public class OpccielWebApplication {
	@Autowired
	DocumentService documentService;
	@Autowired
	DocumentMailService documentMailService;
	@Value("${file.upload-dir}")
	private String chemin;
	@Autowired
	StatistiqueService statistiqueService;
	@Autowired
	PersonnePhysiqueService personnePhysiqueService;
	@Autowired
	PersonneMoraleService personneMoraleService;
	@Autowired
	OpcvmService opcvmService;
	@Autowired
	UtilisateurDao utilisateurDao;
	@Autowired
	UtilisateurService utilisateurService;

	public static void main(String[] args) {
		SpringApplication.run(OpccielWebApplication.class, args);
	}

	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("Async-");
		threadPoolTaskExecutor.setCorePoolSize(2);
		threadPoolTaskExecutor.setMaxPoolSize(6);
		threadPoolTaskExecutor.setQueueCapacity(1000000000);
		return threadPoolTaskExecutor;
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper().registerModule(new JavaTimeModule());
	}
}
