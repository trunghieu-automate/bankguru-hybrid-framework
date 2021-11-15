package utilities.jsonDataHelper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class JsonDataHelper {
    public static class Manager {
        @JsonProperty("userId")
        String userId;

        @JsonProperty("userPassword")
        String userPassword;

        public String getUserId() {
            return userId;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setPassword(String userPassword) {
            this.userPassword = userPassword;
        }
    }

    public static class Customer {

        public static class Account{
            @JsonProperty
            String accountId;
            @JsonProperty
            String accountType;

            public String getAccountId() {
                return accountId;
            }

            public void setAccountId(String accountId) {
                this.accountId = accountId;
            }

            public String getAccountType() {
                return accountType;
            }

            public void setAccountType(String accountType) {
                this.accountType = accountType;
            }

            public String getAccountAmount() {
                return accountAmount;
            }

            public void setAccountAmount(String accountAmount) {
                this.accountAmount = accountAmount;
            }

            @JsonProperty
            String accountAmount;
        }

        @JsonProperty("account")
        Account account;

        @JsonProperty("name")
        String name;
        @JsonProperty("gender")
        String gender;

        public void setName(String name) {
            this.name = name;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public void setEmailid(String emailid) {
            this.emailid = emailid;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setPinno(String pinno) {
            this.pinno = pinno;
        }

        public void setTelephoneno(String telephoneno) {
            this.telephoneno = telephoneno;
        }

        @JsonProperty("dob")
        String dob;
        @JsonProperty("emailid")
        String emailid;
        @JsonProperty("addr")
        String addr;
        @JsonProperty("userId")
        String userId;
        @JsonProperty("password")
        String password;
        @JsonProperty("city")
        String city;
        @JsonProperty("state")
        String state;
        @JsonProperty("pinno")
        String pinno;
        @JsonProperty("telephoneno")
        String telephoneno;

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

        public String getGender() {
            return gender;
        }

        public String getState() {
            return state;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public String getDob() {
            return dob;
        }

        public String getEmailid() {
            return emailid;
        }

        public String getAddr() {
            return addr;
        }

        public String getPassword() {
            return password;
        }

        public String getCity() {
            return city;
        }

        public String getPinno() {
            return pinno;
        }

        public String getTelephoneno() {
            return telephoneno;
        }

    }


    private static final ObjectMapper mapper = new ObjectMapper();

    public static void writeAccessDataToJsonFile(String userId, String userPassword) throws IOException {
        JsonDataHelper.Manager manager = new JsonDataHelper.Manager();
        manager.setUserId(userId);
        manager.setPassword(userPassword);
        mapper.writeValue(new FileOutputStream("src/main/resources/accessData.json"), manager);
    }

    public static Manager readAccessData(String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(filename), JsonDataHelper.Manager.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Customer readOneRandomData(String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Customer> customerList = mapper.readValue(new File(filename), new TypeReference<List<Customer>>() {
            });
            return customerList.get(new Random().nextInt(customerList.size()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeCustomerInfoToJson(String readFilename, String outputFilename, JsonDataHelper.Customer customer, String userId) {
        String cName = customer.getName();
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Customer> customerList = mapper.readValue(new File(readFilename), new TypeReference<List<Customer>>() {
            });
            for (Customer c : customerList) {
                if (c.getName().equalsIgnoreCase(cName)) {
                    c.setUserId(userId);
                    customer.setUserId(userId);
                    mapper.writeValue(new FileOutputStream(outputFilename), c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void overwriteCustomerInfoToFile(String outputFilename, JsonDataHelper.Customer customer){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(outputFilename), customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Customer readCustomerObjFromFilename(String filename){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filename), Customer.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void writeCustomerToFile(Customer customer, String outputFilename){

    }
}
