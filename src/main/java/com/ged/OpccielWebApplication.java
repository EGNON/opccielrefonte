package com.ged;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ged.dao.importation.DataImportationDao;
import com.ged.service.standard.UtilisateurService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@EnableScheduling
@Component
@EnableAsync
//@ConfigurationProperties(prefix = "spring.mail")
public class OpccielWebApplication implements CommandLineRunner {
	@Value("${file.upload-dir}")
	private String chemin;
	@Autowired
	UtilisateurService utilisateurService;
	@Autowired
	private DataImportationDao dataImportationDao;

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
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper().registerModule(new JavaTimeModule());
	}

	@Override
	public void run(String... args)  throws SQLException, IOException {
		File f = new File(chemin);
		if(!f.exists())
		{
			f.mkdir();
		}
		//A NE PAS ENLEVER
//		utilisateurService.registerDefaultUsers();
		/*dataImportationDao.emetteur();
		dataImportationDao.registraire();
		dataImportationDao.depositaire();
		dataImportationDao.garant();*/
	}
}
