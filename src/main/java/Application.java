import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Marta Ginosyan
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan("com.recruiting")
public class Application {
    public static void main(String... args) throws Exception {
        SpringApplication.run(Application.class, args);
//        RatpackServer.start(s -> s
//                .serverConfig(c -> c
//                        .baseDir(BaseDir.find())
//                        .env())
//
//                .registry(Guice.registry(b -> b
//                        .module(TextTemplateModule.class, conf -> conf.setStaticallyCompile(true))))
//
//                .handlers(chain -> chain
//                        .get(ctx -> ctx.render(groovyTemplate("index.html")))
//
//                        .get("hello", ctx -> {
//                            RelativisticModel.select();
//                            Amount<Mass> m = Amount.valueOf("12 GeV").to(KILOGRAM);
//                            ctx.render("E=mc^2: 12 GeV = " + m.toString());
//                        })
//
//                        .get("db", ctx -> {
//                            boolean local = !"cedar-14".equals(System.getenv("STACK"));
//
//                            Blocking.get(() -> {
//                                Connection connection = null;
//
//                                try {
//                                    connection = DatabaseUrl.extract(local).getConnection();
//                                    Statement stmt = connection.createStatement();
//                                    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//                                    stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//                                    return stmt.executeQuery("SELECT tick FROM ticks");
//                                } finally {
//                                    if (connection != null) try {
//                                        connection.close();
//                                    } catch (SQLException e) {
//                                    }
//                                }
//                            }).onError(throwable -> {
//                                Map<String, Object> attributes = new HashMap<>();
//                                attributes.put("message", "There was an error: " + throwable);
//                                ctx.render(groovyTemplate(attributes, "error.html"));
//                            }).then(rs -> {
//                                ArrayList<String> output = new ArrayList<>();
//                                while (rs.next()) {
//                                    output.add("Read from DB: " + rs.getTimestamp("tick"));
//                                }
//
//                                Map<String, Object> attributes = new HashMap<>();
//                                attributes.put("results", output);
//                                ctx.render(groovyTemplate(attributes, "db.html"));
//                            });
//                        })
//
//                        .files(f -> f.dir("public"))
//                )
//        );
    }

    @Bean(name = "encryptorBean")
    static public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("password");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("Marta");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }


}

