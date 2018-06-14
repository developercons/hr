import com.recruiting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Martha on 4/23/2017.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@SpringBootTest(classes = Application.class)
//@AutoConfigureBefore(DataSourceAutoConfiguration.class)
//@TestPropertySource(locations = "classpath:/test.properties")
public class TestRepository {


    @Autowired
    @Qualifier("userService")
    UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;


}
