import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestClient {
    public static void getAllEmployees() throws IOException {
        URL url = new URL("http://localhost:8080/api/tutorial/1.0/employees");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            System.out.println("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        conn.disconnect();
    }

    public static void getEmployee(int id) throws IOException {
        URL url = new URL("http://localhost:8080/api/tutorial/1.0/employees/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            System.out.println("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        conn.disconnect();
    }

    public static void createNewEmployee(int id, String nome, String cognome, String email, String telefono) throws IOException {
        URL url = new URL("http://localhost:8080/api/tutorial/1.0/employees");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        String message = "{" +
                "\"employeeId\": " + id +
                ",\"firstName\": \"" + nome +
                "\",\"lastName\": \"" + cognome +
                "\",\"email\": \"" + email +
                "\",\"phone\": \"" + telefono +
                "\"}";

        OutputStream output = conn.getOutputStream();
        output.write(message.getBytes());
        output.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            System.out.println("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String result;
        while ((result = br.readLine()) != null) {
            System.out.println(result);
        }

        conn.disconnect();
    }

    public static void deleteEmployee(int id) throws IOException {
        URL url = new URL("http://localhost:8080/api/tutorial/1.0/employees/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            System.out.println("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        conn.disconnect();
    }

    public static void modifyEmployee(int id, String nome, String cognome, String email, String telefono) throws IOException {
        URL url = new URL("http://localhost:8080/api/tutorial/1.0/employees/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");

        String message = "{" +
                "\"employeeId\": " + id +
                ",\"firstName\": \"" + nome +
                "\",\"lastName\": \"" + cognome +
                "\",\"email\": \"" + email +
                "\",\"phone\": \"" + telefono +
                "\"}";

        OutputStream output = conn.getOutputStream();
        output.write(message.getBytes());
        output.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            System.out.println("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String result;
        while ((result = br.readLine()) != null) {
            System.out.println(result);
        }

        conn.disconnect();
    }


    public static void printMenu() {
        System.out.println(
                "Choose the operation\n" +
                        "1) Show all the employees \n" +
                        "2) Show a specific employee \n" +
                        "3) Create a new employee \n" +
                        "4) Delete an employee \n" +
                        "5) Modify employee's data \n" +
                        "0) Exit \n");
    }

    public static void main(String[] args) {

        int id = -1, functionNumber = -1;
        String name = "", surname = "", email = "", phone = "";
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while (functionNumber != 0) {
            try {

                printMenu();
                functionNumber = Integer.parseInt(input.readLine());
                switch (functionNumber) {
                    case 1: // Show all the employees
                        getAllEmployees();
                        break;

                    case 2: // Show a specific employee
                        System.out.print("Insert the employee's ID: ");
                        id = Integer.parseInt(input.readLine());
                        getEmployee(id);
                        break;

                    case 3: // Create a new employee
                        System.out.print("Insert the employee's ID: ");
                        id = Integer.parseInt(input.readLine());

                        System.out.print("Insert the employee's name: ");
                        name = input.readLine();

                        System.out.print("Insert the employee's surname: ");
                        surname = input.readLine();

                        System.out.print("Insert the employee's email: ");
                        email = input.readLine();

                        System.out.print("Insert the employee's phone number: ");
                        phone = input.readLine();

                        createNewEmployee(id, name, surname, email, phone);
                        break;

                    case 4: // Delete an employee
                        System.out.print("Insert the employee's ID: ");
                        id = Integer.parseInt(input.readLine());
                        deleteEmployee(id);
                        break;

                    case 5: // Modify employee's data
                        System.out.print("Insert the employee's ID: ");
                        id = Integer.parseInt(input.readLine());

                        System.out.print("Insert the employee's name: ");
                        name = input.readLine();

                        System.out.print("Insert the employee's surname: ");
                        surname = input.readLine();

                        System.out.print("Insert the employee's email: ");
                        email = input.readLine();

                        System.out.print("Insert the employee's phone number: ");
                        phone = input.readLine();

                        modifyEmployee(id, name, surname, email, phone);
                        break;

                    default:
                        System.out.println("The requested method doesn't exist.");
                        break;
                }

            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
