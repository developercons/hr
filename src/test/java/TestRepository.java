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


//    @Test
    public void testUser() {
//        Authority authority = (Authority) genericCrudService.save(new Authority("COMPANY"));
//        Assert.assertNotNull(authority.getId());
//
//        List authorityList = new ArrayList();
//        authorityList.add(authority);
//
//        CompanyStaff companyStaff = new CompanyStaff(DataUtils.nextEmail(), "password", "1234");
//        companyStaff  = (CompanyStaff) genericCrudService.save(companyStaff);
//        Assert.assertNotNull(companyStaff.getId());
//
//
//        org.springframework.security.core.userdetails.User userComparable
//                = (org.springframework.security.core.userdetails.User)userDetailsService.loadUserByUsername(companyStaff.getEmail());
//
//        Assert.assertNotNull(userComparable);
//        Assert.assertEquals(companyStaff.getEmail(), userComparable.getUsername());
//        Assert.assertEquals(companyStaff.getPassword(), userComparable.getPassword());
    }


}
