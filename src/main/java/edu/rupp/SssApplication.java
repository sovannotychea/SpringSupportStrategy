package edu.rupp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import edu.rupp.sss.model.ISO8583;
import edu.rupp.sss.service.support.SupportStrategy;
import edu.rupp.sss.service.support.SupportStrategyManager;
import edu.rupp.sss.service.transform.TransformStrategy;
import edu.rupp.sss.service.transform.TransformStrategyManager;

@SpringBootApplication
public class SssApplication {

	@Autowired
	private SupportStrategyManager supportStrategyManager;
	
	@Autowired
	private TransformStrategyManager transformStrategyManager;

	public static void main(String[] args) {
		SpringApplication.run(SssApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println(supportStrategyManager.services);
			
			String xml = readLineByLineJava8("D:\\eclipse-workspace-mobileTeller\\sss\\src\\main\\resources\\01.xml");
			String json = readLineByLineJava8("D:\\eclipse-workspace-mobileTeller\\sss\\src\\main\\resources\\02.json");		
			String isoMessage = "080020200000008000000000000000013239313130303031";
			SupportStrategy xmlSupportStrategy = supportStrategyManager.getSupportStrategy(xml);
			SupportStrategy jsonSupportStrategy = supportStrategyManager.getSupportStrategy(json);
			SupportStrategy isoMessageSupportStrategy = supportStrategyManager.getSupportStrategy(isoMessage);
			
			TransformStrategy isoMessageTransformStrategy = transformStrategyManager.getSupportStrategy(isoMessage);
			ISO8583 iso8583 = (ISO8583) isoMessageTransformStrategy.doTransform(isoMessage);
			System.out.println(iso8583);
			
		};
	}

	// Read file content into string with - Files.lines(Path path, Charset cs)

	private static String readLineByLineJava8(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

}
