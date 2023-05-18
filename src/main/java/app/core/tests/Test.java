package app.core.tests;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;

@Component
public class Test implements CommandLineRunner {

//    @Autowired
//    private LoginManager loginManager;

//    @Autowired
//    private AdminService adminService;

//    @Autowired
//    private CompanyService companyService;

//    @Autowired
//    private CustomerService customerService;

//    @Autowired
//    private CompanyRepository companyRepository;

//    @Autowired
//    private CouponRepository couponRepository;

//    @Autowired
//    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        Company company1 = Company.builder().id(1).name("Intel").email("Intel@gmail.com").password("aaabbb").build();
        Company company2 = Company.builder().id(2).name("Applied Materials").email("AMAT@gmail.com").password("aaabbb").build();

        Customer customer1 = Customer.builder().id(0).firstName("Noam").lastName("Dov").email("NoamDov@gmail.com").password("12343123").coupons(null).build();
        Customer customer2 = Customer.builder().id(0).firstName("Dov").lastName("Noam").email("DovNoam@gmail.com").password("12343123").coupons(null).build();

        Category category = Category.RESTAURANT;
        Category category1 = Category.FOOD;
        Category category2 = Category.ELECTRICITY;
        String startDate = "2023-01-15";
        String endDate = "2023-03-27";
        Date start = Date.valueOf(startDate);
        Date end = Date.valueOf(endDate);

        Coupon coupon = Coupon.builder().id(0).company(null).category(category).title("Discount on supermarket ! ").description("60% less on price ! ").startDate(start).endDate(end).amount(200).price(1000).image("image").build();
        Coupon coupon1 = Coupon.builder().id(0).company(null).category(category1).title("Discount on chef meal ! ").description("60% less on price ! ").startDate(start).endDate(end).amount(200).price(1000).image("image").build();
        Coupon coupon2 = Coupon.builder().id(0).company(null).category(category2).title("Discount on toaster oven ! ").description("60% less on price ! ").startDate(start).endDate(end).amount(200).price(1000).image("image").build();

        // Repositories //
//		  companyRepository.save(company1);
//		  companyRepository.save(company2);
//		  companyRepository.save(company3);
//		  companyRepository.save(company4);
//		  couponRepository.save(coupon);
//		  customerRepository.save(customer);

        // Customer Service //
//        CustomerService customerService = (CustomerService) loginManager.login("EldarJB@gmail.com", "12314321", ClientType.Customer);
//        customerService.login("EldarJB@gmail.com", "12314321"); // you can use it without the login manger to see if it works !
//        customerService.purchaseCoupon(1);
//        System.out.println(customerService.getCustomerCoupon());
//        System.out.println(customerService.getCustomerCoupon(category));
//        System.out.println(customerService.getCustomerCoupon(1000));
//        System.out.println(customerService.getCustomerDetails());

        // Company Service //
//        CompanyService companyService = (CompanyService) loginManager.login("Intel@gmail.com", "aaabbb", ClientType.Company);
//        companyService.login("Intel@gmail.com", "aaabbb"); // you can use it without the login manger to see if it works !
//        companyService.addCoupon(coupon1);
//        companyService.addCoupon(coupon2);
//        companyService.updateCoupon(coupon);
//        companyService.deleteCoupon(coupon);
//        System.out.println(companyService.getCompanyCoupons());
//        System.out.println(companyService.getCompanyCoupons(category));
//        System.out.println(companyService.getCompanyCoupons(1000));
//        System.out.println(companyService.getCompanyDetails());

        // Admin Service //
//        AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
//        adminService.login("admin@admin.com", "admin"); // you can use it without the login manger to see if it works !
//        adminService.addCompany(company1);
//        adminService.updateCompany(company2);
//        adminService.deleteCompany(company1);
//        System.out.println(adminService.getAllCompanies());
//        System.out.println(adminService.getOneCompany(1));
//        adminService.addCustomer(customer2);
//        adminService.updateCustomer(customer1);
//        adminService.deleteCustomer(customer1);
//        System.out.println(adminService.getAllCustomers());
//        System.out.println(adminService.getOneCustomer(1));
    }

}
