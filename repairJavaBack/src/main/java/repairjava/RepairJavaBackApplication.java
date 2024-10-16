package repairjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RepairJavaBackApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(RepairJavaBackApplication.class, args);
    }

}
