package com.jobportal;

import com.jobportal.dao.impl.ApplicationDaoImpl;
import com.jobportal.dao.impl.EmployerDaoImpl;
import com.jobportal.dao.impl.JobDAOImpl;
import com.jobportal.dao.impl.JobSeekerDaoImp;
import com.jobportal.model.*;
import com.jobportal.service.ApplicationService;
import com.jobportal.service.EmployerService;
import com.jobportal.service.JobSeekerService;
import com.jobportal.service.JobService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display options to the user
        System.out.println("Welcome to RevHire");
        System.out.println("Select an option:");
        System.out.println("1. Job Seeker");
        System.out.println("2. Employer");
        System.out.println("3. Exit");

        // Get user input
        System.out.print("Enter your choice: ");
        int userRole = scanner.nextInt();

        // Process user role choice
        switch (userRole) {
            case 1:
                // Job Seeker
                System.out.println("You selected Job Seeker");
                jobSeekerOptions();
                break;
            case 2:
                // Employer
                System.out.println("You selected Employer");
                employerOptions();
                break;
            case 3:
                // Exit
                System.out.println("Exiting the application. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option selected");
        }

        // Close scanner
        scanner.close();
    }

    // Method to handle Job Seeker options
    public static void jobSeekerOptions() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an option:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Back to main menu");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                // Job Seeker Login
                System.out.println("Job Seeker Login");
                // Call method/function to handle Job Seeker login
                jobseekerLogin();
                break;
            case 2:
                // Job Seeker Register
                System.out.println("Job Seeker Register");
                // Call method/function to handle Job Seeker registration
                jobseekerRegister();
                break;
            case 3:
                // Back to main menu
                System.out.println("Returning to main menu");
                main(null); // Restart the main method
                break;
            default:
                System.out.println("Invalid option selected");
        }

        scanner.close();
    }

    private static void jobseekerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        JobSeekerService jobSeekerService = new JobSeekerService(new JobSeekerDaoImp());
        JobSeeker jobSeeker = jobSeekerService.authenticateJobSeeker(username,password);
        if (jobSeeker != null) {
            System.out.println("Job seeker logged in successfully!");
            displayJobSeekerOptions(jobSeeker, scanner);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void jobseekerRegister() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Full Name:");
        String fullname = scanner.nextLine();
        System.out.println("Enter resume link:");
        String resume = scanner.nextLine();

        JobSeeker jobSeeker = new JobSeeker(username, password, email, fullname, resume);


        JobSeekerService jobSeekerService = new JobSeekerService(new JobSeekerDaoImp());
        jobSeekerService.registerJobSeeker(jobSeeker);

        System.out.println("Job Seeker registered successfully!");
        displayJobSeekerOptions(jobSeeker,scanner);
    }

    private static void displayJobSeekerOptions(JobSeeker jobSeeker, Scanner scanner) {
        // Display menu options
        System.out.println("Select an option:");
        System.out.println("1. View all jobs");
        System.out.println("2. Search for jobs");
        System.out.println("3. Apply for jobs");
        System.out.println("4. View applications and status");
        System.out.println("5. Withdraw applications");
        System.out.println("6. Logout");

        // Get user input
        System.out.print("Enter your choice: ");
        int option = scanner.nextInt();

        // Process user choice
        switch (option) {
            case 1:
                viewAllJobs(jobSeeker, scanner);
                break;
            case 2:
                searchforjobs(jobSeeker, scanner);
                break;
            case 3:
                // Apply for jobs
                applyForJob(jobSeeker, scanner);
                break;
            case 4:
                // View applications and status
                break;
            case 5:
                //Withdraw application
                break;
            case 6:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option selected");
        }
    }

    private static void applyForJob(JobSeeker jobSeeker, Scanner scanner) {
        System.out.print("Enter the job ID you want to apply for: ");
        int jobId = scanner.nextInt();

        int jobseekerId = jobSeeker.getId();

        String status = "Pending";

        Application application = new Application(jobId, jobseekerId, status);

        System.out.println("Confirm your application?");
        System.out.println("1. Yes");
        System.out.println("2. No ");

        int choice = scanner.nextInt();

        if(choice == 1) {
            ApplicationService applicationService = new ApplicationService(new ApplicationDaoImpl());
            applicationService.applyForJob(application);
            System.out.println("Application submitted successfully!");
        }
        else {
            System.out.println("Application canceled.");
        }
    }

    private static void viewAllJobs(JobSeeker jobSeeker, Scanner scanner) {
        JobService jobService = new JobService(new JobDAOImpl());
        List<Job> allJobs = jobService.getAllJobs();

        if (allJobs.isEmpty()) {
            System.out.println("No uploaded jobs found.");
        } else {
            for (Job job : allJobs) {
                System.out.println(job); // Assuming you override toString() in Job class
            }
            System.out.println("Apply for jobs? select an option");
            System.out.println("1.Yes");
            System.out.println("2.No");

            int choice = scanner.nextInt();
            if(choice == 1) {
                applyForJob(jobSeeker, scanner);
            }
            else {

            }
        }
    }

    private static void searchforjobs(JobSeeker jobSeeker, Scanner scanner) {
        System.out.println("Select an option:");
        System.out.println("1. Search job by job role");
        System.out.println("2. Search job by location");
        System.out.println("3. Search job by experience");
        System.out.println("4. Search job by company name");
        System.out.println("5. Back to main menu");

        System.out.print("Enter your choice: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        JobService jobService = new JobService(new JobDAOImpl());

        switch (option) {
            case 1:
                System.out.print("Enter job role: ");
                String jobRole = scanner.nextLine();
                List<Job> jobsByRole = jobService.searchJobsByRole(jobRole);
                displayJobs(jobsByRole, jobSeeker, scanner);
                break;
            case 2:
                System.out.print("Enter location: ");
                String location = scanner.nextLine();
//                List<Job> jobsByLocation = jobService.searchJobsByLocation(location);
//                displayJobs(jobsByLocation);
                break;
            case 3:
                System.out.print("Enter experience: ");
                int experience = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
//                List<Job> jobsByExperience = jobService.searchJobsByExperience(experience);
//                displayJobs(jobsByExperience);
                break;
            case 4:
                System.out.print("Enter company name: ");
                String companyName = scanner.nextLine();
//                List<Job> jobsByCompany = jobService.searchJobsByCompany(companyName);
//                displayJobs(jobsByCompany);
                break;
            case 5:
                System.out.println("Returning to main menu");
                break;
            default:
                System.out.println("Invalid option selected");
        }
    }

    private static void displayJobs(List<Job> jobs, JobSeeker jobSeeker, Scanner scanner) {
        if (jobs.isEmpty()) {
            System.out.println("No jobs found.");
        } else {
            System.out.println("Found jobs:");
            for (Job job : jobs) {
                System.out.println(job);
            }
            System.out.println("Apply for jobs? select an option");
            System.out.println("1.Yes");
            System.out.println("2.No");

            int choice = scanner.nextInt();
            if(choice == 1) {
                applyForJob(jobSeeker, scanner);
            }
            else {

            }
        }
    }


    // Method to handle Employer options
    public static void employerOptions() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an option:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Back to main menu");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                // Employer Login
                System.out.println("Employer Login");
                // Call method/function to handle Employer login
                employerLogin();
                break;
            case 2:
                // Employer Register
                System.out.println("Employer Register");
                // Call method/function to handle Job Seeker registration
                employerRegister();
                break;
            case 3:
                // Back to main menu
                System.out.println("Returning to main menu");
                main(null); // Restart the main method
                break;
            default:
                System.out.println("Invalid option selected");
        }

        scanner.close();

    }

    private static void employerRegister() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Company Name:");
        String companyName = scanner.nextLine();

        Employer employer = new Employer(username, password, email, companyName);

        EmployerService employerService = new EmployerService(new EmployerDaoImpl());
        employerService.registerEmployer(employer);

        System.out.println("Employer registered successfully!");
    }


    private static void employerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        EmployerService employerService = new EmployerService(new EmployerDaoImpl());
        Employer employer = employerService.authenticateEmployer(username, password);
        if (employer != null) {
            System.out.println("Employer logged in successfully!");
            displayEmployerOptions(employer, scanner);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void displayEmployerOptions(Employer employer, Scanner scanner) {
        // Display menu options
        System.out.println("Select an option:");
        System.out.println("1. Post a job");
        System.out.println("2. View applications for a job");
        System.out.println("3. Shortlist or reject applications");
        System.out.println("4. View uploaded jobs (uploaded by you)");
        System.out.println("5. Delete posted job");
        System.out.println("6. Logout");

        // Get user input
        System.out.print("Enter your choice: ");
        int option = scanner.nextInt();

        // Process user choice
        switch (option) {
            case 1:
                postJob(employer); // Call postJob method
                break;
            case 2:
                viewApplicationsForJob(employer, scanner);
                break;
            case 3:
                shortlistApplication(employer, scanner);
                break;
            case 4:
                viewUploadedJobs(employer);
                break;
            case 5:
                deleteJob(employer, scanner);
                break;
            case 6:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option selected");
        }
    }

    private static void shortlistApplication(Employer employer, Scanner scanner) {
        System.out.println("Enter the application id to shortlist");
        int applicationId = scanner.nextInt();

        System.out.println("select an option :");
        System.out.println("1. Accept application");
        System.out.println("2. Reject application");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                acceptApplication(applicationId);
                break;
            case 2:
                //rejectApplication(applicationId);
                break;
            default:
                System.out.println("Invalid option selected.");
        }
    }

    private static void acceptApplication(int applicationId) {
        ApplicationService applicationService = new ApplicationService(new ApplicationDaoImpl());
       // applicationService.acceptAppli
    }

    private static void deleteJob(Employer employer, Scanner scanner) {
        System.out.println("Enter the Job Id to delete the job");
        int jobId = scanner.nextInt();

        JobService jobService = new JobService(new JobDAOImpl());
        jobService.deleteJob(jobId);
        System.out.println("Job deleted successfully !");
    }

    private static void viewApplicationsForJob(Employer employer, Scanner scanner) {
        System.out.println("Enter the Job Id to view the applications");
        int jobId = scanner.nextInt();

        EmployerService employerService = new EmployerService(new EmployerDaoImpl());
        List<JobApplication> applications = employerService.viewApplicationsForJob(jobId);

        if (applications.isEmpty()) {
            System.out.println("No applications for this job.");
        } else {
            for (JobApplication jobApplication : applications) {
                System.out.println(jobApplication); // Assuming you override toString() in Job class
            }
        }
    }

    private static void postJob(Employer employer) {
        Scanner scanner = new Scanner(System.in);

        int empId = employer.getEmployerId();
        String companyName = employer.getCompanyName();

        System.out.println("Enter Job Title:");
        String jobTitle = scanner.nextLine();

        System.out.println("Enter Job Description:");
        String jobDescription = scanner.nextLine();

        System.out.println("Enter Location:");
        String location = scanner.nextLine();

        System.out.println("Enter Salary:");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter Experience Required:");
        int experienceRequired = scanner.nextInt();
        scanner.nextLine();

        Job job = new Job(0, empId, companyName, jobTitle, jobDescription, location, salary, experienceRequired);


        JobService jobService = new JobService(new JobDAOImpl());
        jobService.postJob(job);

        System.out.println("Job posted successfully!");
        displayEmployerOptions(employer, scanner);
    }

    private static void viewUploadedJobs(Employer employer) {
        System.out.println("Uploaded Jobs:");
        EmployerService employerService = new EmployerService(new EmployerDaoImpl());
        List<Job> uploadedJobs = employerService.getUploadedJobs(employer.getEmployerId());
        if (uploadedJobs.isEmpty()) {
            System.out.println("No uploaded jobs found.");
        } else {
            for (Job job : uploadedJobs) {
                System.out.println(job);
            }
        }
    }
}
